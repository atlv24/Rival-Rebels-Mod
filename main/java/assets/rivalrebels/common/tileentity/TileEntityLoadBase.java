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

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityLoadBase extends TileEntity
{
	public Item[]			acceptable	= {};
	public ItemStack[]		input;
	public ItemStack[]		inventory;
	public ItemStack[]		output;
	public TileEntityLoader	loader		= null;
	
	public TileEntityLoadBase(int slots1, int slots2, int slots3)
	{
		input = new ItemStack[slots1];
		inventory = new ItemStack[slots2];
		output = new ItemStack[slots3];
	}
	
	@Override
	public void updateEntity()
	{
		
	}
	
	public void add(ItemStack item, ItemStack[] itemarray)
	{
		int add = item.stackSize;
		int max = item.getMaxStackSize();
		for (int i = 0; i < itemarray.length; i++)
		{
			if (itemarray[i] == null)
			{
				itemarray[i] = item;
				itemarray[i].stackSize = add;
				return;
			}
			else if (itemarray[i] == item)
			{
				itemarray[i].stackSize += add;
				if (itemarray[i].stackSize > max)
				{
					add = itemarray[i].stackSize - max;
					itemarray[i].stackSize = max;
				}
				else
				{
					return;
				}
			}
		}
	}
	
	public int remove(ItemStack item, ItemStack[] itemarray)
	{
		int remove = item.stackSize;
		for (int i = 0; i < itemarray.length; i++)
		{
			if (itemarray != null && itemarray[i] == item)
			{
				itemarray[i].stackSize -= remove;
				if (itemarray[i].stackSize < 0)
				{
					remove = -itemarray[i].stackSize;
					itemarray[i].stackSize = 0;
				}
				else
				{
					return 0;
				}
			}
		}
		return remove;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		NBTTagList nbtlist = nbt.getTagList("Items", 10); // TODO: correct the type (0)
		for (int i = 0; i < nbtlist.tagCount(); i++)
		{
			NBTTagCompound itemnbt = nbtlist.getCompoundTagAt(i);
			int slot = itemnbt.getByte("Slot") & 255;
			if (slot >= 0)
			{
				if (slot < input.length)
				{
					input[slot] = ItemStack.loadItemStackFromNBT(itemnbt);
				}
				else if (slot < inventory.length)
				{
					inventory[slot] = ItemStack.loadItemStackFromNBT(itemnbt);
				}
				else if (slot < output.length)
				{
					output[slot] = ItemStack.loadItemStackFromNBT(itemnbt);
				}
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		NBTTagList nbtlist = new NBTTagList();
		for (int i = 0; i < input.length; i++)
		{
			if (input[i] != null)
			{
				NBTTagCompound itemnbt = new NBTTagCompound();
				itemnbt.setByte("Slot", (byte) i);
				input[i].writeToNBT(itemnbt);
				nbtlist.appendTag(itemnbt);
			}
		}
		for (int i = 0; i < inventory.length; i++)
		{
			if (inventory[i] != null)
			{
				NBTTagCompound itemnbt = new NBTTagCompound();
				itemnbt.setByte("Slot", (byte) (i + input.length));
				inventory[i].writeToNBT(itemnbt);
				nbtlist.appendTag(itemnbt);
			}
		}
		for (int i = 0; i < output.length; i++)
		{
			if (output[i] != null)
			{
				NBTTagCompound itemnbt = new NBTTagCompound();
				itemnbt.setByte("Slot", (byte) (i + inventory.length + input.length));
				output[i].writeToNBT(itemnbt);
				nbtlist.appendTag(itemnbt);
			}
		}
		nbt.setTag("Items", nbtlist);
	}
}