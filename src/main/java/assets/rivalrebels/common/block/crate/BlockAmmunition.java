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
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAmmunition extends Block
{
	public BlockAmmunition()
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
			player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.rocket.getUnlocalizedName() + ".name") + ". §9(" + StatCollector.translateToLocal(RivalRebels.rpg.getUnlocalizedName() + ".name") + " " + StatCollector.translateToLocal("RivalRebels.ammunition") + ")"));
			player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.battery.getUnlocalizedName() + ".name") + ". §9(" + StatCollector.translateToLocal(RivalRebels.tesla.getUnlocalizedName() + ".name") + " " + StatCollector.translateToLocal("RivalRebels.ammunition") + ")"));
			player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.hydrod.getUnlocalizedName() + ".name") + ". §9(" + StatCollector.translateToLocal(RivalRebels.plasmacannon.getUnlocalizedName() + ".name") + " " + StatCollector.translateToLocal("RivalRebels.ammunition") + ")"));
			player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.fuel.getUnlocalizedName() + ".name") + ". §9(" + StatCollector.translateToLocal(RivalRebels.flamethrower.getUnlocalizedName() + ".name") + " " + StatCollector.translateToLocal("RivalRebels.ammunition") + ")"));
			player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.redrod.getUnlocalizedName() + ".name") + ". §9(" + StatCollector.translateToLocal(RivalRebels.einsten.getUnlocalizedName() + ".name") + " " + StatCollector.translateToLocal("RivalRebels.ammunition") + ")"));
			player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.gasgrenade.getUnlocalizedName() + ".name") + ". §9(" + StatCollector.translateToLocal("RivalRebels.chemicalweapon") + ")"));
		}
		if (!world.isRemote)
		{
			EntityItem ei = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.rocket, 32));
			EntityItem ei1 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.battery, 16));
			EntityItem ei2 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.hydrod, 1));
			EntityItem ei3 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.hydrod, 1));
			EntityItem ei4 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.hydrod, 1));
			EntityItem ei5 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.hydrod, 1));
			EntityItem ei10 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.fuel, 64));
			EntityItem ei11 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.gasgrenade, 6));
			EntityItem ei12 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.redrod, 1));
			EntityItem ei13 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.redrod, 1));
			EntityItem ei14 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.redrod, 1));
			EntityItem ei15 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.redrod, 1));
			world.spawnEntityInWorld(ei);
			world.spawnEntityInWorld(ei1);
			world.spawnEntityInWorld(ei2);
			world.spawnEntityInWorld(ei3);
			world.spawnEntityInWorld(ei4);
			world.spawnEntityInWorld(ei5);
			world.spawnEntityInWorld(ei10);
			world.spawnEntityInWorld(ei11);
			world.spawnEntityInWorld(ei12);
			world.spawnEntityInWorld(ei13);
			world.spawnEntityInWorld(ei14);
			world.spawnEntityInWorld(ei15);
			world.setBlock(x, y, z, Blocks.air);
			if (world.rand.nextInt(3) == 0)
			{
				world.spawnEntityInWorld(new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.nuclearelement, 1)));
				player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.nuclearelement.getUnlocalizedName() + ".name") + ". §9(" + "Used in nuclear weapons" + ")"));
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
		icon3 = iconregister.registerIcon("RivalRebels:aa"); // SIDE N
		icon4 = iconregister.registerIcon("RivalRebels:aa"); // SIDE S
		icon5 = iconregister.registerIcon("RivalRebels:aa"); // SIDE W
		icon6 = iconregister.registerIcon("RivalRebels:aa"); // SIDE E
	}
}