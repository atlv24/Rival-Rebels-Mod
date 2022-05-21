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
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSupplies extends Block
{
	public BlockSupplies()
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
		if (world.isRemote)
		{
			player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("RivalRebels.Inventory")));
			player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.armyshovel.getUnlocalizedName() + ".name") + ". §9(" + "Ideal for special blocks." + ")"));
			player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.jump.getUnlocalizedName() + ".name") + ". §9(" + "Use at your own risk." + ")"));
			player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.quicksand.getUnlocalizedName() + ".name") + ". §9(" + "Sand that is quick" + ")"));
			player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.mario.getUnlocalizedName() + ".name") + ". §9(" + "For trap making." + ")"));
			player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.loader.getUnlocalizedName() + ".name") + ". §9(" + "Modular item container." + ")"));
			player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.steel.getUnlocalizedName() + ".name") + ". §9(" + "Climbable and blast resistant." + ")"));
			player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.expill.getUnlocalizedName() + ".name") + ". §9(" + "Take at your own risk." + ")"));
			player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.safepill.getUnlocalizedName() + ".name") + ". §9(" + "Restores health." + ")"));
			player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.breadbox.getUnlocalizedName() + ".name") + ". §9(" + "Unlimited toast! You don't say..." + ")"));
		}
		if (!world.isRemote)
		{
			EntityItem ei = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.breadbox, 1));
			EntityItem ei1 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.armyshovel));
			EntityItem ei2 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.jump, 4));
			EntityItem ei3 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.quicksandtrap, 4));
			EntityItem ei4 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.steel, 32));
			EntityItem ei5 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.loader, 2));
			EntityItem ei6 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(Items.bucket, 2));
			EntityItem ei7 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.mariotrap, 4));
			EntityItem ei8 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.expill, 6));
			EntityItem ei9 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.safepill, 3));
			world.spawnEntityInWorld(ei);
			world.spawnEntityInWorld(ei1);
			world.spawnEntityInWorld(ei2);
			world.spawnEntityInWorld(ei3);
			world.spawnEntityInWorld(ei4);
			world.spawnEntityInWorld(ei5);
			world.spawnEntityInWorld(ei6);
			world.spawnEntityInWorld(ei7);
			world.spawnEntityInWorld(ei8);
			world.spawnEntityInWorld(ei9);
			world.setBlock(x, y, z, Blocks.air);
			if (world.rand.nextInt(5) == 0)
			{
				world.spawnEntityInWorld(new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.nuclearelement, 1)));
				player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.nuclearelement.getUnlocalizedName() + ".name") + ". §9" + "(Used in nuclear weapons)"));
			}
			return true;
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
		icon3 = iconregister.registerIcon("RivalRebels:bz"); // SIDE N
		icon4 = iconregister.registerIcon("RivalRebels:bz"); // SIDE S
		icon5 = iconregister.registerIcon("RivalRebels:bz"); // SIDE W
		icon6 = iconregister.registerIcon("RivalRebels:bz"); // SIDE E
	}
}