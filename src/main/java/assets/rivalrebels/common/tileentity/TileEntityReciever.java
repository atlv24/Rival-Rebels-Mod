/*******************************************************************************
 * Copyright (c) 2012, 2016 Rodol Phito.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Mozilla Public License Version 2.0
 * which accompanies this distribution, and is available at
 * https://www.mozilla.org/en-US/MPL/2.0/
 *
 * Rival Rebels Mod. All code, art, and design by Rodol Phito.
 *
 * http://RivalRebels.com/
 *******************************************************************************/
package assets.rivalrebels.common.tileentity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.entity.EntityFlameBall1;
import assets.rivalrebels.common.entity.EntityRhodes;
import assets.rivalrebels.common.item.weapon.ItemRoda;
import assets.rivalrebels.common.packet.ADSUpdatePacket;
import assets.rivalrebels.common.packet.PacketDispatcher;
import assets.rivalrebels.common.packet.ReactorUpdatePacket;
import assets.rivalrebels.common.round.RivalRebelsPlayer;
import assets.rivalrebels.common.round.RivalRebelsTeam;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityReciever extends TileEntityMachineBase implements IInventory
{
	public double			yaw;
	public double			pitch;
	public Entity			target;
	public double			xO						= 0;
	public double			zO						= 0;
	int						direction;
	double					ll						= -50;
	double					ul						= 90;
	double					scale					= 1.5;
	public ItemStack[]		chestContents			= new ItemStack[9];
	private int				ticksSinceLastTarget	= 0;
	public int				yawLimit				= 180;
	public boolean			kTeam					= true;
	public boolean			kPlayers				= false;
	public boolean			kMobs					= true;
	public boolean			hasWeapon				= false;
	private RivalRebelsTeam	team;
	private int				ammoCounter;
	private double			prevTx					= 0;
	private double			prevTy					= 0;
	private double			prevTz					= 0;
	private Entity			le						= null;
	public int				wepSelected;
	public static int		staticEntityIndex		= 1;
	public int				entityIndex				= 1;
	public String			username				= "nohbdy";
	private int ticksincepacket;
	int ticksSinceLastShot = 0;
	
	public TileEntityReciever()
	{
		entityIndex = staticEntityIndex;
		pInM = 400;
		if (RivalRebels.freeDragonAmmo)
		{
			hasWeapon = true;
			team = RivalRebelsTeam.NONE;
			kPlayers = true;
			chestContents[3] = new ItemStack(RivalRebels.battery, 64);
			chestContents[4] = new ItemStack(RivalRebels.battery, 64);
			chestContents[5] = new ItemStack(RivalRebels.battery, 64);
			chestContents[0] = new ItemStack(RivalRebels.fuel, 64);
			chestContents[1] = new ItemStack(RivalRebels.fuel, 64);
			chestContents[2] = new ItemStack(RivalRebels.fuel, 64);
		}
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (xO == zO) updateDirection();
		powered(0, 0);
		convertBatteryToEnergy();
		if (!hasWeapon && wepSelected != 0 && hasWepReqs()) setWep(wepSelected);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared()
	{
		return 16384.0D;
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox()
	{
		return AxisAlignedBB.getBoundingBox(xCoord - 1, yCoord - 1, zCoord - 1, xCoord + 2, yCoord + 2, zCoord + 2);
	}
	
	private boolean hasBattery()
	{
		return chestContents[3] != null || chestContents[4] != null || chestContents[5] != null || RivalRebels.infiniteAmmo;
	}
	
	private void convertBatteryToEnergy()
	{
		while (pInR < pInM / 2 && hasBattery())
		{
			pInR += 800;
			consumeBattery();
		}
	}
	
	private void consumeBattery()
	{
		if (chestContents[3] != null) decrStackSize(3, 1);
		else if (chestContents[4] != null) decrStackSize(4, 1);
		else if (chestContents[5] != null) decrStackSize(5, 1);
	}
	
	public boolean hasWepReqs()
	{
		return chestContents[6] != null && chestContents[7] != null && chestContents[8] != null;
	}
	
	public void setWep(int wep)
	{
		if (wep != 0)
		{
			if (chestContents[6] != null && chestContents[6].stackTagCompound != null)
			{
				team = RivalRebelsTeam.getForID(chestContents[6].stackTagCompound.getInteger("team"));
				username = chestContents[6].stackTagCompound.getString("username");
			}
			chestContents[6] = chestContents[7] = chestContents[8] = null;
			hasWeapon = true;
			wepSelected = 0;
		}
	}
	
	@Override
	public float powered(float power, float distance)
	{
		if (hasWeapon)
		{
			ticksSinceLastTarget += 1;
			if (ticksSinceLastTarget == 3)
			{
				target = getTarget();
				ticksSinceLastTarget = 0;
			}
			ticksSinceLastShot++;
			if (ticksSinceLastShot > ItemRoda.rates[entityIndex])
			{
				if (target != null)
				{
					ticksSinceLastShot = 0;
					lookAt(target);
					if (hasAmmo())
					{
						if (worldObj.rand.nextInt(3) == 0)
						{
							RivalRebelsSoundPlayer.playSound(worldObj, xCoord, yCoord, zCoord, 8, 1, 0.1f);
						}
						float rotationYaw = (float) (180 - yaw);
						float rotationPitch = (float) (-pitch);
						double motionX = (-MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI));
						double motionZ = (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI));
						double motionY = (-MathHelper.sin(rotationPitch / 180.0F * (float) Math.PI));
						ItemRoda.spawn(entityIndex,worldObj,xCoord + xO + 0.5, yCoord + 0.75, zCoord + zO + 0.5,motionX,motionY,motionZ,1.0f,0.0f);
						useAmmo();
					}
					return power - 4;
				}
			}
			ticksincepacket++;
			if (ticksincepacket > 6 && !worldObj.isRemote)
			{
				ticksincepacket = 0;
				PacketDispatcher.packetsys.sendToAll(new ADSUpdatePacket(xCoord, yCoord, zCoord, yawLimit, kMobs, kTeam, kPlayers, hasWeapon, username));
			}
		}
		return power - 1;
	}
	
	/*
	 * public Packet getDescriptionPacket() { Packet132TileEntityData p = new Packet132TileEntityData(); NBTTagCompound nbt = new NBTTagCompound(); writeToNBT(nbt); p.data = nbt; p.xPosition = xCoord;
	 * p.yPosition = yCoord; p.zPosition = zCoord; return p; } public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) { readFromNBT(pkt.data); }
	 */
	
	private boolean hasAmmo()
	{
		return chestContents[0] != null || chestContents[1] != null || chestContents[2] != null || RivalRebels.infiniteAmmo;
	}
	
	private boolean useAmmo()
	{
		ammoCounter++;
		if (ammoCounter == 9)
		{
			ammoCounter = 0;
			if (chestContents[0] != null) decrStackSize(0, 1);
			else if (chestContents[1] != null) decrStackSize(1, 1);
			else if (chestContents[2] != null) decrStackSize(2, 1);
			else return false;
			return true;
		}
		return true;
	}
	
	private Entity getTarget()
	{
		Object[] iter = worldObj.loadedEntityList.toArray();
		double ldist = 40*40;
		Entity result = null;
		for (int i = 0; i < iter.length; i++)
		{
			Entity e = (Entity) iter[i];
			double dist = e.getDistanceSq(xCoord + 0.5 + xO, yCoord + 0.5, zCoord + 0.5 + zO);
			if (dist < ldist)
			{
				if (canTarget(e))
				{
					ldist = dist;
					result = e;
				}
			}
		}
		return result;
	}
	
	private boolean canTarget(Entity e)
	{
		return e != null && ((e instanceof EntityLivingBase && ((EntityLivingBase) e).getHealth() > 0) || e instanceof EntityRhodes) && getPitchTo(e, 0) > ll && getPitchTo(e, 0) < ul && isValidTarget(e) && canSee(e);
	}
	
	private boolean isValidTarget(Entity e)
	{
		if (e == null) return false;
		else if (e instanceof EntityPlayer)
		{
			EntityPlayer p = ((EntityPlayer) e);
			if (p.capabilities.isCreativeMode) return false;
			else
			{
				if (kPlayers) return true;
				else if (!kTeam) return false;
				RivalRebelsPlayer rrp = RivalRebels.round.rrplayerlist.getForName(((EntityPlayer) e).getCommandSenderName());
				if (rrp == null) return kTeam;
				if (rrp.rrteam == RivalRebelsTeam.NONE) return !p.getCommandSenderName().equals(username);
				if (rrp.rrteam != team) return kTeam;
				else return false;
			}
		}
		else return (kMobs && (e instanceof EntityRhodes || (e instanceof EntityMob && !(e instanceof EntityAnimal) && !(e instanceof EntityBat) && !(e instanceof EntityVillager) && !(e instanceof EntitySquid)) || e instanceof EntityGhast));
	}
	
	private boolean canSee(Entity e)
	{
		int yaw = (int) (getYawTo(e, 0) - getBaseRotation() + 360) % 360;
		if (Math.abs(yaw) > yawLimit / 2 && Math.abs(yaw) < 360 - (yawLimit / 2)) return false;
		Vec3 start = Vec3.createVectorHelper(e.posX, e.posY + e.getEyeHeight(), e.posZ);
		Vec3 end = Vec3.createVectorHelper(xCoord + 0.5 + xO, yCoord + 0.5, zCoord + 0.5 + zO);
		MovingObjectPosition mop = worldObj.func_147447_a(start, end, false, true, false);
		return mop == null || (mop.blockX == xCoord && mop.blockY == yCoord && mop.blockZ == zCoord);
	}
	
	private void updateDirection()
	{
		direction = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		xO = 0.0;
		zO = 0.0;
		if (direction == 2) zO = -0.76f;
		if (direction == 4) xO = -0.76f;
		if (direction == 3) zO = 0.76f;
		if (direction == 5) xO = 0.76f;
	}
	
	public int lookAt(Entity t)
	{
		double dist = t.getDistance(xCoord + 0.5 + xO, yCoord + 0.5, zCoord + 0.5 + zO);
		double ya = getYawTo(t, le == t ? dist * 1.00 : 0);
		double pi = getPitchTo(t, le == t ? dist * 1.00 : 0);
		if (pi > ll && pi < ul)
		{
			pitch = (pitch + pitch + pitch + pi) / 4;
			if (yaw - ya < -180) yaw += 360;
			else if (yaw - ya > 180) yaw -= 360;
			yaw = (yaw + yaw + yaw + ya) / 4;
			//pitch += dist / 10;
			prevTx = t.posX;
			prevTy = t.posY;
			prevTz = t.posZ;
			le = t;
			return 1;
		}
		else return 0;
	}
	
	public double getYawTo(Entity t, double off)
	{
		double x = xCoord + 0.5 + xO - t.posX - (t.posX - prevTx) * off;
		double z = zCoord + 0.5 + zO - t.posZ - (t.posZ - prevTz) * off;
		double ya = Math.atan2(x, z);
		return ((ya / Math.PI) * 180);
	}
	
	public double getPitchTo(Entity t, double off)
	{
		double x = xCoord + 0.5 + xO - t.posX - (t.posX - prevTx) * off;
		double y = yCoord + (0.5 * scale) - t.posY - t.getEyeHeight() - (t.posY - prevTy) * off;
		double z = zCoord + 0.5 + zO - t.posZ - (t.posZ - prevTz) * off;
		double d = Math.sqrt(x * x + z * z);
		double pi = Math.atan2(d, -y);
		return 90 - ((pi / Math.PI) * 180);
	}
	
	public int getBaseRotation()
	{
		int m = getBlockMetadata();
		short r = 0;
		if (m == 2) r = 0;
		if (m == 3) r = 180;
		if (m == 4) r = 90;
		if (m == 5) r = 270;
		return r;
	}
	
	/**
	 * Returns the number of slots in the inventory.
	 */
	@Override
	public int getSizeInventory()
	{
		return 9;
	}
	
	/**
	 * Returns the stack in slot i
	 */
	@Override
	public ItemStack getStackInSlot(int par1)
	{
		if (par1 >= getSizeInventory()) return null;
		return this.chestContents[par1];
	}
	
	/**
	 * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a new stack.
	 */
	@Override
	public ItemStack decrStackSize(int par1, int par2)
	{
		if (this.chestContents[par1] != null)
		{
			ItemStack var3;
			
			if (this.chestContents[par1].stackSize <= par2)
			{
				var3 = this.chestContents[par1];
				this.chestContents[par1] = null;
				return var3;
			}
			else
			{
				var3 = this.chestContents[par1].splitStack(par2);
				
				if (this.chestContents[par1].stackSize == 0)
				{
					this.chestContents[par1] = null;
				}
				return var3;
			}
		}
		return null;
	}
	
	/**
	 * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem - like when you close a workbench GUI.
	 */
	@Override
	public ItemStack getStackInSlotOnClosing(int par1)
	{
		if (par1 >= getSizeInventory()) return null;
		if (this.chestContents[par1] != null)
		{
			ItemStack var2 = this.chestContents[par1];
			this.chestContents[par1] = null;
			return var2;
		}
		return null;
	}
	
	/**
	 * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
	 */
	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		if (par1 >= getSizeInventory()) return;
		this.chestContents[par1] = par2ItemStack;
		
		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
		{
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}
	
	/**
	 * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't this more of a set than a get?*
	 */
	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}
	
	/**
	 * Do not make give this method the name canInteractWith because it clashes with Container
	 */
	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
	}
	
	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return true;
	}
	
	/**
	 * Reads a tile entity from NBT.
	 */
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		NBTTagList nbttaglist = nbt.getTagList("Items", 10);
		this.chestContents = new ItemStack[this.getSizeInventory()];
		
		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbt1 = nbttaglist.getCompoundTagAt(i);
			int j = nbt1.getByte("Slot") & 255;
			
			if (j >= 0 && j < this.chestContents.length)
			{
				this.chestContents[j] = ItemStack.loadItemStackFromNBT(nbt1);
			}
		}
		
		yawLimit = nbt.getInteger("yawLimit");
		kPlayers = nbt.getBoolean("kPlayers");
		kTeam = nbt.getBoolean("kTeam");
		kMobs = nbt.getBoolean("kMobs");
		hasWeapon = nbt.getBoolean("hasWeapon");
		username = nbt.getString("username");
		team = RivalRebelsTeam.getForID(nbt.getInteger("team"));
		entityIndex = nbt.getInteger("entityIndex");
	}
	
	/**
	 * Writes a tile entity to NBT.
	 */
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		NBTTagList nbttaglist = new NBTTagList();
		
		for (int i = 0; i < this.chestContents.length; ++i)
		{
			if (this.chestContents[i] != null)
			{
				NBTTagCompound nbt1 = new NBTTagCompound();
				nbt1.setByte("Slot", (byte) i);
				this.chestContents[i].writeToNBT(nbt1);
				nbttaglist.appendTag(nbt1);
			}
		}
		
		nbt.setTag("Items", nbttaglist);
		
		nbt.setInteger("yawLimit", yawLimit);
		nbt.setBoolean("kPlayers", kPlayers);
		nbt.setBoolean("kTeam", kTeam);
		nbt.setBoolean("kMobs", kMobs);
		nbt.setBoolean("hasWeapon", hasWeapon);
		nbt.setString("username", username);
		nbt.setInteger("entityIndex", entityIndex);
		if (team != null) nbt.setInteger("team", team.ordinal());
	}
	
	@Override
	public String getInventoryName()
	{
		return "Automated Defense System";
	}
	
	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}
	
	@Override
	public void openInventory()
	{
		
	}
	
	@Override
	public void closeInventory()
	{
		
	}
}