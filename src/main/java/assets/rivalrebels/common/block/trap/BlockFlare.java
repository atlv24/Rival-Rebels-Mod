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
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsDamageSource;
import assets.rivalrebels.common.explosion.Explosion;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFlare extends Block
{
	public BlockFlare()
	{
		super(Material.wood);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int i)
	{
		return null;
	}
	
	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether or not to render the shared face of two adjacent blocks and also whether the player can attach torches, redstone wire,
	 * etc to this block.
	 */
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	/**
	 * The type of render function that is called for this block
	 */
	@Override
	public int getRenderType()
	{
		return 2;
	}
	
	private boolean canPlaceTorchOn(World par1World, int par2, int par3, int par4)
	{
		if (par1World.isBlockNormalCubeDefault(par2, par3, par4, true))
		{
			return true;
		}
		
		Block i = par1World.getBlock(par2, par3, par4);
		
		if (i == Blocks.fence || i == Blocks.nether_brick_fence || i == Blocks.glass)
		{
			return true;
		}
		
		if (i != null && (i instanceof BlockStairs))
		{
			int j = par1World.getBlockMetadata(par2, par3, par4);
			
			if ((4 & j) != 0)
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		return canPlaceTorchOn(par1World, par2, par3 - 1, par4);
	}
	
	/**
	 * Called when a block is placed using an item. Used often for taking the facing and figuring out how to position the item. Args: x, y, z, facing
	 */
	public void onBlockPlaced(World par1World, int par2, int par3, int par4, int par5)
	{
		int i = par1World.getBlockMetadata(par2, par3, par4);
		
		if (par5 == 1 && canPlaceTorchOn(par1World, par2, par3 - 1, par4))
		{
			i = 5;
		}
		
		if (par5 == 2 && par1World.isBlockNormalCubeDefault(par2, par3, par4 + 1, true))
		{
			i = 4;
		}
		
		if (par5 == 3 && par1World.isBlockNormalCubeDefault(par2, par3, par4 - 1, true))
		{
			i = 3;
		}
		
		if (par5 == 4 && par1World.isBlockNormalCubeDefault(par2 + 1, par3, par4, true))
		{
			i = 2;
		}
		
		if (par5 == 5 && par1World.isBlockNormalCubeDefault(par2 - 1, par3, par4, true))
		{
			i = 1;
		}
		par1World.setBlockMetadataWithNotify(par2, par3, par4, i, 0);
		par1World.scheduleBlockUpdate(par2, par3, par4, this, 1);
	}
	
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random par5Random)
	{
		world.spawnParticle("lava", x + .5, y + .6, z + .5, 0, 0.5, 0);
		world.spawnParticle("lava", x + .5, y + .8, z + .5, 0, 0.5, 0);
		world.spawnParticle("lava", x + .5, y + 1, z + .5, 0, 0.5, 0);
		world.spawnParticle("flame", x + .5, y + 1.2, z + .5, (-0.5 + world.rand.nextFloat()) * 0.1, 0.5 + world.rand.nextFloat() * 0.5, (-0.5 + world.rand.nextFloat()) * 0.1);
		world.spawnParticle("flame", x + .5, y + 1.4, z + .5, (-0.5 + world.rand.nextFloat()) * 0.1, 0.5 + world.rand.nextFloat() * 0.5, (-0.5 + world.rand.nextFloat()) * 0.1);
		world.spawnParticle("smoke", x + .5, y + 1.6, z + .5, (-0.5 + world.rand.nextFloat()) * 0.1, 0.5 + world.rand.nextFloat() * 0.5, (-0.5 + world.rand.nextFloat()) * 0.1);
		world.playSoundEffect(x, y, z, "random.fizz", 3F, 2);
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		world.scheduleBlockUpdate(x, y, z, this, 1);
		if (world.getBlock(x + 1, y, z) == Blocks.water || world.getBlock(x - 1, y, z) == Blocks.water || world.getBlock(x, y, z + 1) == Blocks.water || world.getBlock(x, y, z - 1) == Blocks.water)
		{
			world.setBlock(x, y, z, Blocks.air);
			world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(RivalRebels.flare)));
		}
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int m)
	{
		if (RivalRebels.flareExplode)
		{
			world.setBlock(x, y, z, Blocks.air);
			new Explosion(world, x, y, z, 3, true, false, RivalRebelsDamageSource.flare);
			world.playSoundEffect(x, y, z, "random.explode", 0.5f, 0.3f);
		}
	}
	
	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		par5Entity.attackEntityFrom(RivalRebelsDamageSource.flare, 1);
		par5Entity.setFire(5);
	}
	
	@Override
	public int quantityDropped(Random random)
	{
		return 0;
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
		icon = iconregister.registerIcon("RivalRebels:an");
	}
}