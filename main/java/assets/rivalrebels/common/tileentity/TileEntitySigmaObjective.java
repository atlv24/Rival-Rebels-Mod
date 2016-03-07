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

import java.util.Iterator;
import java.util.List;

import assets.rivalrebels.RivalRebels;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntitySigmaObjective extends TileEntity/*MachineBase*/ implements IInventory, ICommandSender
{
	private ItemStack[]	chestContents	= new ItemStack[16];
	
	public double		slide			= 0;
	double				test			= Math.PI - 0.05;
	
	/** The number of players currently using this chest */
	public int			numUsingPlayers;
	
	/** Server sync counter (once per 20 ticks) */
	private int			ticksSinceSync;
	
	/**
	 * Returns the number of slots in the inventory.
	 */
	@Override
	public int getSizeInventory()
	{
		return 16;
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
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
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
	
	/**
	 * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count ticks and creates a new spawn inside its implementation.
	 */
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		++this.ticksSinceSync;
		
		slide = (Math.cos(test) + 1) / 32 * 10;
		
		super.updateEntity();
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
			if (slide < 0.621) test += 0.05;
		}
		else
		{
			if (slide > 0.004) test -= 0.05;
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
	
	public void openChest()
	{
		++this.numUsingPlayers;
	}
	
	public void closeChest()
	{
		--this.numUsingPlayers;
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
		return 16384.0D;
	}
	
	/**
	 * invalidates a tile entity
	 */
	@Override
	public void invalidate()
	{
		super.invalidate();
		this.updateContainingBlockInfo();
	}
	
	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return true;
	}
	
	@Override
	public String getCommandSenderName()
	{
		return "RivalRebelsSigma";
	}
	
	@Override
	public boolean canCommandSenderUseCommand(int i, String s)
	{
		return true;
	}
	
	@Override
	public ChunkCoordinates getPlayerCoordinates()
	{
		return new ChunkCoordinates(xCoord, yCoord, zCoord);
	}
	
	@Override
	public World getEntityWorld()
	{
		return worldObj;
	}
	
	@Override
	public IChatComponent func_145748_c_()
	{
		return null;
	}
	
	@Override
	public void addChatMessage(IChatComponent p_145747_1_)
	{
		
	}
	
	@Override
	public String getInventoryName()
	{
		return "Objective";
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

	/*@Override
	public float powered(float power, float distance)
	{
		return RivalRebels.round.addSigmaHealth(power);
	}*/
}
