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
package assets.rivalrebels.common.block.machine;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.tileentity.TileEntityMachineBase;
import assets.rivalrebels.common.tileentity.TileEntityReactor;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockReactor extends BlockContainer
{
	public BlockReactor()
	{
		super(Material.iron);
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
	
	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}
	
	/**
	 * Called when the block is placed in the world.
	 */
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack)
	{
		int l = MathHelper.floor_double((par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		
		if (l == 0)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
		}
		
		if (l == 1)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
		}
		
		if (l == 2)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
		}
		
		if (l == 3)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
		}
		
		if (par6ItemStack.hasDisplayName())// TODO: what the hell
		{
			// ((TileEntityFurnace)par1World.getTileEntity(par2, par3, par4)).setGuiDisplayName(par6ItemStack.getDisplayName());
		}
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
	public TileEntity createNewTileEntity(World var1, int var)
	{
		return new TileEntityReactor();
	}
	
	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if (!par1World.isRemote)
		{
			FMLNetworkHandler.openGui(par5EntityPlayer, RivalRebels.instance, 0, par1World, par2, par3, par4);
			// par5EntityPlayer.openGui(RivalRebels.instance, RivalRebels.tokamakID, par1World, par2, par3, par4);
		}
		RivalRebelsSoundPlayer.playSound(par1World, 10, 3, par2, par3, par4);
		
		return true;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block id, int meta)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null && te instanceof TileEntityReactor)
		{
			TileEntityReactor ter = (TileEntityReactor) te;
			ter.on = false;
			List<?> list = world.loadedTileEntityList;
			Iterator<?> iter = list.iterator();
			while (iter.hasNext())
			{
				te = (TileEntity) iter.next();
				if (te != null && te instanceof TileEntityMachineBase)
				{
					TileEntityMachineBase temb = (TileEntityMachineBase) te;
					if (temb.x == x && temb.y == y && temb.z == z)
					{
						temb.x = temb.y = temb.z = 0;
						temb.edist = 0;
					}
				}
			}
		}
		super.breakBlock(world, x, y, z, id, meta);
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
		icon = iconregister.registerIcon("RivalRebels:bj");
	}
}