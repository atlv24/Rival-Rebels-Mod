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
package assets.rivalrebels.common.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityTarget extends EntityLivingBase
{
	private Entity e;
	public EntityTarget(World par1World)
	{
		super(par1World);
    }
	public EntityTarget(World par1World, Entity relay)
	{
		super(par1World);
		e = relay;
		boundingBox.setBB(e.boundingBox);
		ySize=e.ySize;
		height=e.height;
		width = e.width;
		yOffset=e.yOffset;
    }
	@Override
	public void onUpdate()
	{
		if (e==null||e.isDead)
		{
			setDead();
		}
		else
		{
			setPosition(e.posX, e.posY, e.posZ);
		}
	}
	@Override
	public boolean attackEntityFrom(DamageSource ds, float f)
    {
		e.attackEntityFrom(ds, f);
		return true;
    }
	@Override
	public ItemStack getHeldItem()
	{
		return null;
	}
	@Override
	public ItemStack getEquipmentInSlot(int p_71124_1_)
	{
		return null;
	}
	@Override
	public void setCurrentItemOrArmor(int p_70062_1_, ItemStack p_70062_2_)
	{
	}
	@Override
	public ItemStack[] getLastActiveItems()
	{
		return null;
	}
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		nbt.setInteger("id", e.getEntityId());
	}
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		e = worldObj.getEntityByID(nbt.getInteger("id"));
	}
}
