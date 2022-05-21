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
import assets.rivalrebels.common.item.ItemChip;
import assets.rivalrebels.common.round.RivalRebelsTeam;
import assets.rivalrebels.common.tileentity.TileEntityForceFieldNode;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockForceFieldNode extends BlockContainer
{
	public BlockForceFieldNode()
	{
		super(Material.iron);
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
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof TileEntityForceFieldNode && !world.isRemote)
		{
			TileEntityForceFieldNode teffn = (TileEntityForceFieldNode) te;
			ItemStack is = player.inventory.getCurrentItem();
			if (is != null && is.getItem() instanceof ItemChip && (teffn.username == null || teffn.username.equals("")) && (teffn.rrteam == null || teffn.rrteam == RivalRebelsTeam.NONE))
			{
				teffn.rrteam = RivalRebels.round.rrplayerlist.getForName(player.getCommandSenderName()).rrteam;
				if (teffn.rrteam == RivalRebelsTeam.NONE || teffn.rrteam == null)
				{
					teffn.username = player.getCommandSenderName();
					teffn.rrteam = null;
				}
				RivalRebelsSoundPlayer.playSound(world, 10, 5, x, y, z);
			}
		}
		return false;
	}
	
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
	}
	
	@Override
	public boolean hasTileEntity(int metadata)
	{
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var)
	{
		return new TileEntityForceFieldNode();
	}
	
	@SideOnly(Side.CLIENT)
	IIcon	icon;
	@SideOnly(Side.CLIENT)
	IIcon	icon2;
	@SideOnly(Side.CLIENT)
	IIcon	icontop1;
	@SideOnly(Side.CLIENT)
	IIcon	icontop2;
	@SideOnly(Side.CLIENT)
	IIcon	icontop3;
	@SideOnly(Side.CLIENT)
	IIcon	icontop4;
	
	@Override
	public final IIcon getIcon(int side, int meta)
	{
		if (meta == 0)
		{
			if (side == 0 || side == 1) return icontop2;
			if (side == 4) return icon2;
			return icon;
		}
		if (side == 0 || side == 1) return meta == 3 ? icontop1 : meta == 4 ? icontop2 : meta == 2 ? icontop3 : icontop4;
		if (side == meta) return icon2;
		return icon;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconregister)
	{
		icon = iconregister.registerIcon("RivalRebels:cf");
		icon2 = iconregister.registerIcon("RivalRebels:cg");
		icontop1 = iconregister.registerIcon("RivalRebels:cj");
		icontop2 = iconregister.registerIcon("RivalRebels:ck");
		icontop3 = iconregister.registerIcon("RivalRebels:cl");
		icontop4 = iconregister.registerIcon("RivalRebels:cm");
	}
}