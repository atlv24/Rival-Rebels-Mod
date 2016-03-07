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
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFlag extends Block
{
	String	texpath	= "rivalrebels:";
	
	public BlockFlag(String name)
	{
		super(Material.cloth);
		texpath += name;
		//this.setCreativeTab(RivalRebels.rrarmortab);
	}
	
	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}
	
	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
		if (this == RivalRebels.flag2) return RivalRebels.trollmask;
        return Item.getItemFromBlock(this);
    }
	
	@Override
	public void setBlockBoundsForItemRender()
	{
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}
	
	@Override
	public int getRenderType()
	{
		return 20;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
	{
		int l = p_149719_1_.getBlockMetadata(p_149719_2_, p_149719_3_, p_149719_4_);
		float f1 = 1.0F;
		float f2 = 1.0F;
		float f3 = 1.0F;
		float f4 = 0.0F;
		float f5 = 0.0F;
		float f6 = 0.0F;
		boolean flag = l > 0;
		
		if ((l & 2) != 0)
		{
			f4 = Math.max(f4, 0.0625F);
			f1 = 0.0F;
			f2 = 0.0F;
			f5 = 1.0F;
			f3 = 0.0F;
			f6 = 1.0F;
			flag = true;
		}
		
		if ((l & 8) != 0)
		{
			f1 = Math.min(f1, 0.9375F);
			f4 = 1.0F;
			f2 = 0.0F;
			f5 = 1.0F;
			f3 = 0.0F;
			f6 = 1.0F;
			flag = true;
		}
		
		if ((l & 4) != 0)
		{
			f6 = Math.max(f6, 0.0625F);
			f3 = 0.0F;
			f1 = 0.0F;
			f4 = 1.0F;
			f2 = 0.0F;
			f5 = 1.0F;
			flag = true;
		}
		
		if ((l & 1) != 0)
		{
			f3 = Math.min(f3, 0.9375F);
			f6 = 1.0F;
			f1 = 0.0F;
			f4 = 1.0F;
			f2 = 0.0F;
			f5 = 1.0F;
			flag = true;
		}
		
		if (!flag && this.func_150093_a(p_149719_1_.getBlock(p_149719_2_, p_149719_3_ + 1, p_149719_4_)))
		{
			f2 = Math.min(f2, 0.9375F);
			f5 = 1.0F;
			f1 = 0.0F;
			f4 = 1.0F;
			f3 = 0.0F;
			f6 = 1.0F;
		}
		
		this.setBlockBounds(f1, f2, f3, f4, f5, f6);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
	{
		return null;
	}
	
	@Override
	public boolean canPlaceBlockOnSide(World p_149707_1_, int p_149707_2_, int p_149707_3_, int p_149707_4_, int p_149707_5_)
	{
		switch (p_149707_5_)
		{
			case 1:
				return this.func_150093_a(p_149707_1_.getBlock(p_149707_2_, p_149707_3_ + 1, p_149707_4_));
			case 2:
				return this.func_150093_a(p_149707_1_.getBlock(p_149707_2_, p_149707_3_, p_149707_4_ + 1));
			case 3:
				return this.func_150093_a(p_149707_1_.getBlock(p_149707_2_, p_149707_3_, p_149707_4_ - 1));
			case 4:
				return this.func_150093_a(p_149707_1_.getBlock(p_149707_2_ + 1, p_149707_3_, p_149707_4_));
			case 5:
				return this.func_150093_a(p_149707_1_.getBlock(p_149707_2_ - 1, p_149707_3_, p_149707_4_));
			default:
				return false;
		}
	}
	
	private boolean func_150093_a(Block p_150093_1_)
	{
		return p_150093_1_.renderAsNormalBlock();
	}
	
	private boolean func_150094_e(World p_150094_1_, int p_150094_2_, int p_150094_3_, int p_150094_4_)
	{
		int l = p_150094_1_.getBlockMetadata(p_150094_2_, p_150094_3_, p_150094_4_);
		int i1 = l;
		
		if (l > 0)
		{
			for (int j1 = 0; j1 <= 3; ++j1)
			{
				int k1 = 1 << j1;
				
				if ((l & k1) != 0 && !this.func_150093_a(p_150094_1_.getBlock(p_150094_2_ + Direction.offsetX[j1], p_150094_3_, p_150094_4_ + Direction.offsetZ[j1])) && (p_150094_1_.getBlock(p_150094_2_, p_150094_3_ + 1, p_150094_4_) != this || (p_150094_1_.getBlockMetadata(p_150094_2_, p_150094_3_ + 1, p_150094_4_) & k1) == 0))
				{
					i1 &= ~k1;
				}
			}
		}
		
		if (i1 == 0 && !this.func_150093_a(p_150094_1_.getBlock(p_150094_2_, p_150094_3_ + 1, p_150094_4_)))
		{
			return false;
		}
		else
		{
			if (i1 != l)
			{
				p_150094_1_.setBlockMetadataWithNotify(p_150094_2_, p_150094_3_, p_150094_4_, i1, 2);
			}
			
			return true;
		}
	}
	
	@Override
	public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
	{
		if (!p_149695_1_.isRemote && !this.func_150094_e(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_))
		{
			this.dropBlockAsItem(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, p_149695_1_.getBlockMetadata(p_149695_2_, p_149695_3_, p_149695_4_), 0);
			p_149695_1_.setBlockToAir(p_149695_2_, p_149695_3_, p_149695_4_);
		}
	}
	
	@Override
	public int onBlockPlaced(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_)
	{
		byte b0 = 0;
		
		switch (p_149660_5_)
		{
			case 2:
				b0 = 1;
			break;
			case 3:
				b0 = 4;
			break;
			case 4:
				b0 = 8;
			break;
			case 5:
				b0 = 2;
		}
		
		return b0 != 0 ? b0 : p_149660_9_;
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
		icon = iconregister.registerIcon(texpath);
	}
}