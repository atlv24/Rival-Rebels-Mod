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
package assets.rivalrebels.common.block.trap;

import java.util.Random;

import net.minecraft.block.BlockFalling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsDamageSource;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.explosion.Explosion;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTimedBomb extends BlockFalling
{
	int	ticksSincePlaced;
	
	public BlockTimedBomb()
	{
		super();
		ticksSincePlaced = 0;
	}
	
	@Override
	public int quantityDropped(Random par1Random)
	{
		return 0;
	}
	
	@Override
	public void onBlockDestroyedByExplosion(World par1World, int par2, int par3, int par4, net.minecraft.world.Explosion explosion)
	{
		par1World.setBlock(par2, par3, par4, Blocks.air);
		new Explosion(par1World, par2 + 0.5f, par3 + 0.5f, par4 + 0.5f, RivalRebels.timedbombExplodeSize, false, true, RivalRebelsDamageSource.timebomb);
		RivalRebelsSoundPlayer.playSound(par1World, 26, 0, par2 + 0.5f, par3 + 0.5f, par4 + 0.5f, 2f, 0.3f);
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World world, int i, int j, int k, int l)
	{
		world.setBlock(i, j, k, Blocks.air);
		new Explosion(world, i + 0.5f, j + 0.5f, k + 0.5f, RivalRebels.timedbombExplodeSize, false, true, RivalRebelsDamageSource.timebomb);
		RivalRebelsSoundPlayer.playSound(world, 26, 0, i + 0.5f, j + 0.5f, k + 0.5f, 2f, 0.3f);
	}
	
	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		ticksSincePlaced = 0;
		par1World.scheduleBlockUpdate(par2, par3, par4, this, 8);
	}
	
	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		par1World.scheduleBlockUpdate(par2, par3, par4, this, 8);
		ticksSincePlaced += 1;
		if (ticksSincePlaced >= RivalRebels.timedbombTimer * 2.5)
		{
			par1World.setBlock(par2, par3, par4, Blocks.air);
			new Explosion(par1World, par2 + 0.5f, par3 + 0.5f, par4 + 0.5f, RivalRebels.timedbombExplodeSize, false, true, RivalRebelsDamageSource.timebomb);
			RivalRebelsSoundPlayer.playSound(par1World, 26, 0, par2 + 0.5f, par3 + 0.5f, par4 + 0.5f, 2f, 0.3f);
		}
		if (ticksSincePlaced == 100)
		{
			ticksSincePlaced = 0;
		}
		if (par1World.getBlock(par2, par3 + 1, par4) == RivalRebels.light && ticksSincePlaced <= 93)
		{
			par1World.setBlock(par2, par3 + 1, par4, Blocks.air);
			par1World.playSoundEffect(par2 + 0.5, par3 + 0.5, par4 + 0.5, "random.click", 1, 1);
		}
		else
		{
			if (ticksSincePlaced <= 93)
			{
				par1World.setBlock(par2, par3 + 1, par4, RivalRebels.light);
				par1World.playSoundEffect(par2 + 0.5, par3 + 0.5, par4 + 0.5, "random.click", 1, 0.7F);
			}
			else
			{
				par1World.playSoundEffect(par2 + 0.5, par3 + 0.5, par4 + 0.5, "random.click", 2F, 2F);
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	IIcon	icon1;
	@SideOnly(Side.CLIENT)
	IIcon	icon2;
	@SideOnly(Side.CLIENT)
	IIcon	icon3;
	@SideOnly(Side.CLIENT)
	IIcon	icon4;
	@SideOnly(Side.CLIENT)
	IIcon	icon5;
	@SideOnly(Side.CLIENT)
	IIcon	icon6;
	
	@SideOnly(Side.CLIENT)
	@Override
	public final IIcon getIcon(int side, int meta)
	{
		if (side == 0) return icon1;
		if (side == 1) return icon2;
		if (side == 2) return icon3;
		if (side == 3) return icon4;
		if (side == 4) return icon5;
		if (side == 5) return icon6;
		return icon1;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconregister)
	{
		icon1 = iconregister.registerIcon("RivalRebels:ac"); // BOTTOM
		icon2 = iconregister.registerIcon("RivalRebels:ae"); // TOP
		icon3 = iconregister.registerIcon("RivalRebels:ac"); // SIDE N
		icon4 = iconregister.registerIcon("RivalRebels:ac"); // SIDE S
		icon5 = iconregister.registerIcon("RivalRebels:ab"); // SIDE W
		icon6 = iconregister.registerIcon("RivalRebels:ab"); // SIDE E
	}
	
	/**
	 * Called when the falling block entity for this block hits the ground and turns back into a block
	 */
	public void onFinishFalling(World par1World, int par2, int par3, int par4, int par5)
	{
		par1World.setBlock(par2, par3, par4, Blocks.air);
		new Explosion(par1World, par2 + 0.5f, par3 + 0.5f, par4 + 0.5f, RivalRebels.timedbombExplodeSize, false, true, RivalRebelsDamageSource.timebomb);
		RivalRebelsSoundPlayer.playSound(par1World, 26, 0, par2 + 0.5f, par3 + 0.5f, par4 + 0.5f, 2f, 0.3f);
	}
}