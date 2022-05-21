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

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.entity.EntityNuclearBlast;
import assets.rivalrebels.common.entity.EntityNuke;
import assets.rivalrebels.common.packet.PacketDispatcher;
import assets.rivalrebels.common.packet.TextPacket;
import assets.rivalrebels.common.round.RivalRebelsTeam;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityNuclearBomb extends TileEntity implements IInventory
{
	public String			username		= null;
	public RivalRebelsTeam	rrteam			= null;
	private ItemStack[]		chestContents	= new ItemStack[36];
	
	/** The number of players currently using this chest */
	public int				numUsingPlayers;
	
	/** Server sync counter (once per 20 ticks) */
	private int				ticksSinceSync;
	
	public int				Countdown		= RivalRebels.nuclearBombCountdown * 20;
	
	public int				AmountOfCharges	= 0;
	public boolean			hasTrollface	= false;
	public boolean			hasExplosive	= false;
	public boolean			hasFuse			= false;
	public boolean			hasChip			= false;
	
	/**
	 * Returns the number of slots in the inventory.
	 */
	@Override
	public int getSizeInventory()
	{
		return 13;
	}
	
	/**
	 * Returns the stack in slot i
	 */
	@Override
	public ItemStack getStackInSlot(int par1)
	{
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
		else
		{
			return null;
		}
	}
	
	/**
	 * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem - like when you close a workbench GUI.
	 */
	@Override
	public ItemStack getStackInSlotOnClosing(int par1)
	{
		if (this.chestContents[par1] != null)
		{
			ItemStack var2 = this.chestContents[par1];
			this.chestContents[par1] = null;
			return var2;
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
	 */
	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		this.chestContents[par1] = par2ItemStack;
		
		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
		{
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}
	
	/**
	 * Reads a tile entity from NBT.
	 */
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
		this.blockMetadata = par1NBTTagCompound.getInteger("NuclearBombMetadata");
		NBTTagList var2 = par1NBTTagCompound.getTagList("Items", 10); // TODO: !!
		this.chestContents = new ItemStack[this.getSizeInventory()];
		
		for (int var3 = 0; var3 < var2.tagCount(); ++var3)
		{
			NBTTagCompound var4 = var2.getCompoundTagAt(var3);
			int var5 = var4.getByte("Slot") & 255;
			
			if (var5 >= 0 && var5 < this.chestContents.length)
			{
				this.chestContents[var5] = ItemStack.loadItemStackFromNBT(var4);
			}
		}
	}
	
	/**
	 * Writes a tile entity to NBT.
	 */
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("NuclearBombMetadata", this.blockMetadata);
		NBTTagList var2 = new NBTTagList();
		
		for (int var3 = 0; var3 < this.chestContents.length; ++var3)
		{
			if (this.chestContents[var3] != null)
			{
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte) var3);
				this.chestContents[var3].writeToNBT(var4);
				var2.appendTag(var4);
			}
		}
		par1NBTTagCompound.setTag("Items", var2);
	}
	
	/**
	 * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't this more of a set than a get?*
	 */
	@Override
	public int getInventoryStackLimit()
	{
		return 1;
	}
	
	/**
	 * Do not make give this method the name canInteractWith because it clashes with Container
	 */
	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
	}
	
	/**
	 * Causes the TileEntity to reset all it's cached values for it's container block, blockID, metaData and in the case of chests, the adjcacent chest check
	 */
	@Override
	public void updateContainingBlockInfo()
	{
		super.updateContainingBlockInfo();
	}
	
	/**
	 * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count ticks and creates a new spawn inside its implementation.
	 */
	@Override
	public void updateEntity()
	{
		AmountOfCharges = 0;
		hasTrollface = false;
		for (int i = 1; i <= 10; i++)
		{
			if (getStackInSlot(i) != null && getStackInSlot(i).getItem() == RivalRebels.nuclearelement)
			{
				AmountOfCharges++;
			}
			if (getStackInSlot(i) != null && getStackInSlot(i).getItem() == RivalRebels.trollmask)
			{
				hasTrollface = true;
			}
		}
		
		if (getStackInSlot(0) != null)
		{
			hasFuse = getStackInSlot(0).getItem() == RivalRebels.fuse;
		}
		else
		{
			hasFuse = false;
		}
		
		if (getStackInSlot(12) != null)
		{
			hasChip = getStackInSlot(12).getItem() == RivalRebels.chip;
			if (hasChip)
			{
				rrteam = RivalRebelsTeam.getForID(getStackInSlot(12).stackTagCompound.getInteger("team"));
				username = getStackInSlot(12).stackTagCompound.getString("username");
			}
		}
		else
		{
			hasChip = false;
		}
		
		if (getStackInSlot(11) != null)
		{
			hasExplosive = true;// getStackInSlot(11).func_150998_b(RivalRebels.timedbomb);
		}
		else
		{
			hasExplosive = false;
		}
		
		boolean sp = false;
		try
		{
			sp = !MinecraftServer.getServer().isDedicatedServer() && MinecraftServer.getServer().getConfigurationManager().playerEntityList.size() == 1;
		}
		catch (NullPointerException e)
		{
			
		}
		if (hasFuse && hasExplosive && hasChip)
		{
			double dist = 10000000;
			if (!sp || RivalRebels.stopSelfnukeinSP)
			{
				if (rrteam == RivalRebelsTeam.OMEGA)
				{
					dist = getDistanceFrom(RivalRebels.round.oObjx, yCoord, RivalRebels.round.oObjz);
				}
				if (rrteam == RivalRebelsTeam.SIGMA)
				{
					dist = getDistanceFrom(RivalRebels.round.sObjx, yCoord, RivalRebels.round.sObjz);
				}
			}
			if (dist > (RivalRebels.nuclearBombStrength + (AmountOfCharges * AmountOfCharges) + 29) * (RivalRebels.nuclearBombStrength + (AmountOfCharges * AmountOfCharges) + 29))
			{
				if (Countdown > 0) Countdown--;
			}
			else if (!worldObj.isRemote)
			{
				this.chestContents[0] = null;
				PacketDispatcher.packetsys.sendToAll(new TextPacket("RivalRebels.WARNING " + username));
				PacketDispatcher.packetsys.sendToAll(new TextPacket("RivalRebels.Status " + (rrteam == RivalRebelsTeam.OMEGA ? RivalRebels.omegaobj.getUnlocalizedName() : rrteam == RivalRebelsTeam.SIGMA ? RivalRebels.sigmaobj.getUnlocalizedName() : "NONE") + ".name RivalRebels.Defuse RivalRebels.nuke.name"));
				//ChatMessageComponent.createFromText(StatCollector.translateToLocal("RivalRebels.spawn.join" + rrteam.name().toLowerCase()) + " " +
				//StatCollector.translateToLocal("RivalRebels.nukedefuse")));
			}
		}
		else
		{
			Countdown = RivalRebels.nuclearBombCountdown * 20;
		}
		
		if (Countdown == 200 && !worldObj.isRemote && RivalRebels.nuclearBombCountdown > 10)
		{
			PacketDispatcher.packetsys.sendToAll(new TextPacket("RivalRebels.WARNING RivalRebels.warning1"));
			PacketDispatcher.packetsys.sendToAll(new TextPacket("RivalRebels.WARNING RivalRebels.warning2"));
			PacketDispatcher.packetsys.sendToAll(new TextPacket("RivalRebels.WARNING RivalRebels.warning3"));
		}
		
		if (Countdown == 0 && AmountOfCharges != 0 && !worldObj.isRemote)
		{
			worldObj.lastLightningBolt = 2;
			float pitch = 0;
			float yaw = 0;
			switch(this.getBlockMetadata())
			{
			default:
				pitch = -90;
				break;
			case 1:
				pitch = 90;
				break;
			case 2:
				yaw = 180;
				break;
			case 3:
				yaw = 0;
				break;
			case 4:
				yaw = 270;
				break;
			case 5:
				yaw = 90;
				break;
			}
			
			worldObj.spawnEntityInWorld(new EntityNuke(worldObj, xCoord + 0.5f, yCoord + 0.5f, zCoord + 0.5f, yaw, pitch, AmountOfCharges, hasTrollface));
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		
		if (Countdown == 0 && AmountOfCharges == 0)
		{
			worldObj.createExplosion(null, xCoord, yCoord, zCoord, 4, true);
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		
		super.updateEntity();
	}
	
	/**
	 * Called when a client event is received with the event number and argument, see World.sendClientEvent
	 * 
	 * @return
	 */
	@Override
	public boolean receiveClientEvent(int par1, int par2)
	{
		if (par1 == 1)
		{
			this.numUsingPlayers = par2;
			return true;
		}
		return false;
	}
	
	/**
	 * invalidates a tile entity
	 */
	@Override
	public void invalidate()
	{
		this.updateContainingBlockInfo();
		super.invalidate();
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox()
	{
		return AxisAlignedBB.getBoundingBox(xCoord - 1, yCoord - 1, zCoord - 1, xCoord + 2, yCoord + 2, zCoord + 2);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared()
	{
		return 65536.0D;
	}
	
	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return true;
	}
	
	@Override
	public String getInventoryName()
	{
		return "Nuclear Bomb";
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
