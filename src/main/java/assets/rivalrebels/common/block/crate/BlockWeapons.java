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

public class BlockWeapons extends Block
{
	public BlockWeapons()
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
			player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.rpg.getUnlocalizedName() + ".name") + ". §9(" + StatCollector.translateToLocal("RivalRebels.consume") + " " + StatCollector.translateToLocal(RivalRebels.rocket.getUnlocalizedName() + ".name") + ")"));
			player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.tesla.getUnlocalizedName() + ".name") + ". §9(" + StatCollector.translateToLocal("RivalRebels.consume") + " " + StatCollector.translateToLocal(RivalRebels.hydrod.getUnlocalizedName() + ".name") + ")"));
			player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.flamethrower.getUnlocalizedName() + ".name") + ". §9(" + StatCollector.translateToLocal("RivalRebels.consume") + " " + StatCollector.translateToLocal(RivalRebels.fuel.getUnlocalizedName() + ".name") + ")"));
			player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.plasmacannon.getUnlocalizedName() + ".name") + ". §9(" + StatCollector.translateToLocal("RivalRebels.consume") + " " + StatCollector.translateToLocal(RivalRebels.battery.getUnlocalizedName() + ".name") + ")"));
			player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.einsten.getUnlocalizedName() + ".name") + ". §9(" + StatCollector.translateToLocal("RivalRebels.consume") + " " + StatCollector.translateToLocal(RivalRebels.redrod.getUnlocalizedName() + ".name") + ")"));
			player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.roddisk.getUnlocalizedName() + ".name") + ". §9(" + StatCollector.translateToLocal("RivalRebels.message.use") + " /rr)"));
			// player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.bastion.getUnlocalizedName() + ".name") + ". §9(" +
			// StatCollector.translateToLocal("RivalRebels.build") + " " + StatCollector.translateToLocal(RivalRebels.barricade.getUnlocalizedName() + ".name") + ")");
			// player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.tower.getUnlocalizedName() + ".name") + ". §9(" +
			// StatCollector.translateToLocal("RivalRebels.build") + " " + StatCollector.translateToLocal(RivalRebels.tower.getUnlocalizedName() + ".name") + ")");
			player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.knife.getUnlocalizedName() + ".name") + ". §9(" + StatCollector.translateToLocal("RivalRebels.opknife") + ")"));
			player.addChatMessage(new ChatComponentText("§a" + StatCollector.translateToLocal(RivalRebels.gasgrenade.getUnlocalizedName() + ".name") + ". §9(" + StatCollector.translateToLocal("RivalRebels.chemicalweapon") + ")"));
			player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("RivalRebels.Orders") + " " + StatCollector.translateToLocal("RivalRebels.equipweapons")));
		}
		if (!world.isRemote)
		{
			EntityItem ei = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.rpg));
			EntityItem ei1 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.tesla));
			EntityItem ei2 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.plasmacannon));
			EntityItem ei3 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.flamethrower));
			EntityItem ei4 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.roddisk));
			// EntityItem ei5 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.barricade, 6));
			// EntityItem ei6 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.tower, 3));
			EntityItem ei7 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.knife, 10));
			EntityItem ei8 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.gasgrenade, 6));
			EntityItem ei9 = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.einsten));
			world.spawnEntityInWorld(ei);
			world.spawnEntityInWorld(ei1);
			world.spawnEntityInWorld(ei2);
			world.spawnEntityInWorld(ei3);
			world.spawnEntityInWorld(ei4);
			// world.spawnEntityInWorld(ei5);
			// world.spawnEntityInWorld(ei6);
			world.spawnEntityInWorld(ei7);
			world.spawnEntityInWorld(ei8);
			world.spawnEntityInWorld(ei9);
			world.setBlock(x, y, z, Blocks.air);
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
		icon3 = iconregister.registerIcon("RivalRebels:ce"); // SIDE N
		icon4 = iconregister.registerIcon("RivalRebels:ce"); // SIDE S
		icon5 = iconregister.registerIcon("RivalRebels:ce"); // SIDE W
		icon6 = iconregister.registerIcon("RivalRebels:ce"); // SIDE E
	}
}