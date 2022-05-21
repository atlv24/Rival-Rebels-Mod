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

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBreadBox extends Block
{
	public BlockBreadBox()
	{
		super(Material.iron);
	}
	
	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}
	
	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
	{
		blockActivated(world, x, y, z, player);
	}
	
	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player)
	{
		if (!player.isSneaking())
		{
			EntityItem ei = new EntityItem(world, x + .5, y + 1, z + .5, new ItemStack(Items.bread, 1));
			if (!world.isRemote)
			{
				world.spawnEntityInWorld(ei);
				if (world.rand.nextInt(64) == 0) player.addChatMessage(new ChatComponentText("§7[§4Orders§7] §cShift-click (Sneak) to pack up toaster."));
			}
		}
		else
		{
			world.setBlock(x, y, z, Blocks.air);
			EntityItem ei = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(this));
			if (!world.isRemote)
			{
				world.spawnEntityInWorld(ei);
			}
		}
		return true;
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
		icon1 = iconregister.registerIcon("RivalRebels:ca"); // BOTTOM
		icon2 = iconregister.registerIcon("RivalRebels:cc"); // TOP
		icon3 = iconregister.registerIcon("RivalRebels:cb"); // SIDE N
		icon4 = iconregister.registerIcon("RivalRebels:ca"); // SIDE S
		icon5 = iconregister.registerIcon("RivalRebels:ca"); // SIDE W
		icon6 = iconregister.registerIcon("RivalRebels:ca"); // SIDE E
	}
}