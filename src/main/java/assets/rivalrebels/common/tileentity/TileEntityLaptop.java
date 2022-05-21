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

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.item.weapon.ItemBinoculars;
import assets.rivalrebels.common.packet.LaptopButtonPacket;
import assets.rivalrebels.common.packet.LaptopRefreshPacket;
import assets.rivalrebels.common.packet.PacketDispatcher;
import assets.rivalrebels.common.round.RivalRebelsTeam;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class TileEntityLaptop extends TileEntity implements IInventory
{
	public String			username		= null;
	public RivalRebelsTeam	rrteam			= null;
	private ItemStack[]		chestContents	= new ItemStack[14];
	
	public double			slide			= 0;
	double					test			= Math.PI;
	public int				b2spirit		= 0;
	public int				b2carpet		= 0;
	public int				numUsingPlayers;
	private int				ticksSinceSync;
	private boolean			listed;
	
	/**
	 * Returns the number of slots in the inventory.
	 */
	@Override
	public int getSizeInventory()
	{
		return 14;
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
		return null;
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
		return null;
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
		
		b2spirit = nbt.getInteger("b2spirit");
		b2carpet = nbt.getInteger("b2carpet");
	}
	
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
		
		nbt.setInteger("b2spirit", b2spirit);
		nbt.setInteger("b2carpet", b2carpet);
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
	
	public void onGoButtonPressed()
	{
		if (isReady())
		{
			for (int j = 0; j < 3; j++)
			{
				if (getStackInSlot(j + 6) != null && getStackInSlot(j + 11) != null)
				{
					if (getStackInSlot(j + 6).getItem() == RivalRebels.nuclearelement
							&& getStackInSlot(j + 11).getItem() == RivalRebels.hydrod)
					{
						b2spirit++;
						setInventorySlotContents(j+6, null);
						setInventorySlotContents(j+11, null);
					}
					else if (getStackInSlot(j + 6).getItem() == Item.getItemFromBlock(RivalRebels.timedbomb)
							&& getStackInSlot(j + 11).getItem() == Item.getItemFromBlock(RivalRebels.timedbomb))
					{
						b2carpet++;
						setInventorySlotContents(j+6, null);
						setInventorySlotContents(j+11, null);
					}
				}
			}
			setInventorySlotContents(4, null);
			setInventorySlotContents(5, null);
			setInventorySlotContents(9, null);
			setInventorySlotContents(10, null);
		}
		if (RivalRebels.freeb83nukes) {b2spirit += 10;b2carpet += 10;}
		refreshTasks();
	}
	
	public boolean hasChips()
	{
		boolean r = true;
		rrteam = RivalRebelsTeam.NONE;
		for (int j = 0; j < 4; j++)
		{
			if (getStackInSlot(j) == null) r = false;
			else
			{
				if (getStackInSlot(j).stackTagCompound==null) getStackInSlot(j).stackTagCompound = new NBTTagCompound();
				if (rrteam == RivalRebelsTeam.NONE) rrteam = RivalRebelsTeam.getForID(getStackInSlot(j).stackTagCompound.getInteger("team"));
				else if (rrteam != RivalRebelsTeam.getForID(getStackInSlot(j).stackTagCompound.getInteger("team"))) r = false;
			}
		}
		return r;
	}
	
	/**
	 * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count ticks and creates a new spawn inside its implementation.
	 */
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		++this.ticksSinceSync;
		
		slide = (Math.cos(test) + 1) * 45;
		
		//if (!listed)
		//{
			ItemBinoculars.add(this);
		//	listed = true;
		//}
		List players = worldObj.playerEntities;
		Iterator iter = players.iterator();
		boolean i = false;
		while (iter.hasNext())
		{
			EntityPlayer player = (EntityPlayer) iter.next();
			if (player.getDistanceSq(xCoord + 0.5f, yCoord + 0.5f, zCoord + 0.5f) <= 9)
			{
				i = true;
			}
		}
		if (i)
		{
			if (slide < 89.995) test += 0.05;
		}
		else
		{
			if (slide > 0.004) test -= 0.05;
		}
		
		if (b2spirit > 0 && !hasChips())
		{
			b2spirit--;
			refreshTasks();
		}
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
		super.invalidate();
		this.updateContainingBlockInfo();
		ItemBinoculars.remove(this);
	}
	
	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return true;
	}
	
	@Override
	public String getInventoryName()
	{
		return "Laptop";
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

	public void refreshTasks() {
		PacketDispatcher.packetsys.sendToAll(new LaptopRefreshPacket(xCoord, yCoord, zCoord, b2spirit, b2carpet));
	}

	public boolean isReady()
	{
		return hasChips()
				&& getStackInSlot(4) != null
				&& getStackInSlot(5) != null
				&& getStackInSlot(9) != null
				&& getStackInSlot(10) != null;
	}
}
