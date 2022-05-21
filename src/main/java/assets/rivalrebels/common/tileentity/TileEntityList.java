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

import java.util.Arrays;

import net.minecraft.tileentity.TileEntity;

public class TileEntityList
{
	private int				size	= 0;
	private TileEntity[]	list	= new TileEntity[0];
	
	public TileEntityList()
	{
	}
	
	public int getSize()
	{
		return size;
	}
	
	public int getFFSize()
	{
		int result = 0;
		for (int i = 0; i < list.length; i++)
		{
			if (list[i] != null && !(list[i] instanceof TileEntityReactive)) result++;
		}
		return result;
	}
	
	public TileEntity[] getArray()
	{
		return list;
	}
	
	public void add(TileEntity o)
	{
		int index = get(o);
		if (index == -1)
		{
			size++;
			if (size <= list.length) list[size - 1] = o;
			else
			{
				int nsize = ((list.length * 3) / 2) + 1;
				if (nsize < size) nsize = size;
				list = Arrays.copyOf(list, nsize);
				list[size - 1] = o;
			}
		}
		else
		{
			list[index] = o;
		}
	}
	
	public TileEntity get(int index)
	{
		return list[index];
	}
	
	public int get(TileEntity tile)
	{
		for (int i = 0; i < size; i++)
		{
			if (list[i].equals(tile))
			{
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public String toString()
	{
		StringBuilder strB = new StringBuilder();
		strB.append("tileentities:\n");
		for (int i = 0; i < size; i++)
		{
			strB.append(list[i].xCoord + ", " + list[i].yCoord + ", " + list[i].zCoord + ", " + list[i].toString());
			strB.append(",\n");
		}
		return strB.toString();
	}
	
	public void remove(TileEntityMachineBase temb)
	{
		remove(get(temb));
	}
	
	public void remove(int index)
	{
		if (index != -1)
		{
			while (index < size)
				list[index] = ++index == size ? null : list[index];
			--size;
		}
	}
	
	public void clear()
	{
		size = 0;
		list = new TileEntity[0];
	}
}