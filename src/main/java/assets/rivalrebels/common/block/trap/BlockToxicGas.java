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

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import assets.rivalrebels.common.core.RivalRebelsDamageSource;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockToxicGas extends Block
{
	public BlockToxicGas()
	{
		super(Material.cactus);
		setTickRandomly(true);
	}
	
	@Override
    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        return 300;
    }
	
	@Override
	public int quantityDropped(Random random)
	{
		return 0;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		return null;
	}
	
	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		
		return AxisAlignedBB.getBoundingBox(par2, par3, par4, par2, par3, par4);
	}
	
	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity entity)
	{
		if (entity instanceof EntityPlayer)
		{
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.poison.id, 200, 0));
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 0));
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.blindness.id, 80, 0));
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 80, 0));
		}
		if (entity instanceof EntityMob || entity instanceof EntityAnimal || entity instanceof EntityPlayer)
		{
			entity.attackEntityFrom(RivalRebelsDamageSource.gasgrenade, 1);
		}
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		world.scheduleBlockUpdate(x, y, z, this, 8);
	}
	
	@Override
	public void updateTick(World par1World, int x, int y, int z, Random par5Random)
	{
		par1World.scheduleBlockUpdate(x, y, z, this, 8);
		if (par1World.rand.nextInt(25) == 1)
		{
			par1World.setBlock(x, y, z, Blocks.air);
		}
	}
	
	@Override
	public void randomDisplayTick(World w, int x, int y, int z, Random r)
	{
		w.spawnParticle("smoke", x + 0.5, y + 0.5, z + 0.5, (r.nextFloat() - 0.5) * 0.1, (r.nextFloat() - 0.5) * 0.1, (r.nextFloat() - 0.5) * 0.1);
		w.spawnParticle("spell", x + 0.5, y + 0.5, z + 0.5, (r.nextFloat() - 0.5) * 0.1, (r.nextFloat() - 0.5) * 0.1, (r.nextFloat() - 0.5) * 0.1);
	}
	
	@SideOnly(Side.CLIENT)
	IIcon	icon;
	
	@Override
	public final IIcon getIcon(int side, int meta)
	{
		return icon;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconregister)
	{
		icon = iconregister.registerIcon("RivalRebels:ak");
	}
}