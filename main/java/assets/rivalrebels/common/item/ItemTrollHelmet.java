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
package assets.rivalrebels.common.item;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import assets.rivalrebels.RivalRebels;

public class ItemTrollHelmet extends ItemArmor
{
	public ItemTrollHelmet()
	{
		super(EnumHelper.addArmorMaterial("Troll", 5000, new int[] { 0, 0, 0, 0 }, 1000), 0, 0);
		setCreativeTab(RivalRebels.rrarmortab);
		setMaxDamage(5000);
		maxStackSize = 64;
	}
	
	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String layer)
	{
		return "rivalrebels:textures/armors/o.png";
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:bf");
	}
	
	@Override
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int s, float px, float py, float pz)
    {
        Block block = world.getBlock(x, y, z);

        if (block == Blocks.snow_layer && (world.getBlockMetadata(x, y, z) & 7) < 1)
        {
            s = 1;
        }
        else if (block != Blocks.vine && block != Blocks.tallgrass && block != Blocks.deadbush && !block.isReplaceable(world, x, y, z))
        {
            if (s == 0)
            {
                --y;
            }

            if (s == 1)
            {
                ++y;
            }

            if (s == 2)
            {
                --z;
            }

            if (s == 3)
            {
                ++z;
            }

            if (s == 4)
            {
                --x;
            }

            if (s == 5)
            {
                ++x;
            }
        }

        if (item.stackSize == 0)
        {
            return false;
        }
        else if (!player.canPlayerEdit(x, y, z, s, item))
        {
            return false;
        }
        else if (world.canPlaceEntityOnSide(RivalRebels.flag2, x, y, z, false, s, player, item))
        {
            int i1 = this.getMetadata(item.getItemDamage());
            int j1 = RivalRebels.flag2.onBlockPlaced(world, x, y, z, s, px, py, pz, i1);
            world.setBlock(x, y, z, RivalRebels.flag2, j1, 3);
            world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), RivalRebels.flag2.stepSound.func_150496_b(), (RivalRebels.flag2.stepSound.getVolume() + 1.0F) / 2.0F, RivalRebels.flag2.stepSound.getPitch() * 0.8F);
            --item.stackSize;
            return true;
        }
        else
        {
            return false;
        }
    }
}