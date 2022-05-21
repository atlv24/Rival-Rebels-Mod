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
package assets.rivalrebels;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import assets.rivalrebels.common.entity.EntityGore;
import assets.rivalrebels.common.entity.EntityRhodes;
import assets.rivalrebels.common.round.RivalRebelsPlayer;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}
	
	public static void registerRenderInformation()
	{
	}
	
	public int addArmor(String armor)
	{
		return 0;
	}
	
	public void closeGui()
	{
		
	}
	
	public void nextBattle()
	{
		
	}
	
	public void teamWin(boolean winner)
	{
		
	}
	
	public void guiClass()
	{
		
	}
	
	public void guiSpawn()
	{
		
	}
	
	public void flamethrowerGui(int i)
	{
		
	}
	
	public void teslaGui(int i)
	{
		
	}
	
	public void spawnGore(World world, EntityGore g, boolean greenblood)
	{
	}
	
	public boolean spacebar()
	{
		return false;
	}
	public boolean w()
	{
		return false;
	}
	public boolean a()
	{
		return false;
	}
	public boolean s()
	{
		return false;
	}
	public boolean d()
	{
		return false;
	}
	public boolean f()
	{
		return false;
	}
	public boolean c()
	{
		return false;
	}
	public boolean x()
	{
		return false;
	}
	public boolean z()
	{
		return false;
	}
	public boolean g()
	{
		return false;
	}
	
	public void setOverlay(EntityRhodes rhodes)
	{
		
	}
}