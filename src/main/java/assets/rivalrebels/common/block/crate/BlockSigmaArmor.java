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
package assets.rivalrebels.common.block.crate;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSigmaArmor extends Block
{
	public BlockSigmaArmor()
	{
		super(Material.wood);
	}
	
	@Override
	public int quantityDropped(Random par1Random)
	{
		return 0;
	}
	
	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
	{
		blockActivated(world, x, y, z, player);
	}
	
	public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player)
	{
		if (!world.isRemote)
		{
			player.addChatMessage(new ChatComponentText("§7[§2Inventory§7]"));
			player.addChatMessage(new ChatComponentText("§aArmor. §9(Sigma's color armor.)"));
			player.addChatMessage(new ChatComponentText("§7[§4Orders§7] §cEquipt your set of armor."));
			EntityItem ei7 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.camohat2));
			EntityItem ei8 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.camoshirt2));
			EntityItem ei9 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.camopants2));
			EntityItem ei10 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.camoshoes2));
			world.spawnEntityInWorld(ei7);
			world.spawnEntityInWorld(ei8);
			world.spawnEntityInWorld(ei9);
			world.spawnEntityInWorld(ei10);
			world.setBlock(x, y, z, Blocks.air);
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
		icon1 = iconregister.registerIcon("RivalRebels:ah"); // BOTTOM
		icon2 = iconregister.registerIcon("RivalRebels:ai"); // TOP
		icon3 = iconregister.registerIcon("RivalRebels:bo"); // SIDE N
		icon4 = iconregister.registerIcon("RivalRebels:bo"); // SIDE S
		icon5 = iconregister.registerIcon("RivalRebels:ah"); // SIDE W
		icon6 = iconregister.registerIcon("RivalRebels:ah"); // SIDE E
	}
}