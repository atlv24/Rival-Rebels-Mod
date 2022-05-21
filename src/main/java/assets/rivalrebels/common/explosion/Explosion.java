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
package assets.rivalrebels.common.explosion;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.BlackList;
import assets.rivalrebels.common.core.RivalRebelsDamageSource;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.entity.EntityDebris;
import assets.rivalrebels.common.entity.EntityFlameBall;
import assets.rivalrebels.common.entity.EntityRhodes;

public class Explosion
{
	public Explosion(World world, double x, double y, double z, int strength, boolean fire, boolean crater, DamageSource dmgsrc)
	{
		world.spawnParticle("hugeexplosion", x, y, z, 0, 0, 0);
		if (!world.isRemote)
		{
			if (fire)
			{
				fireSpread(world, x, y, z, strength);
			}
			else
			{
				createHole(world, x, y, z, strength * strength, crater, 4);
			}
			pushAndHurtEntities(world, x, y, z, strength, dmgsrc);
		}
	}
	
	private void fireSpread(World world, double x, double y, double z, int radius)
	{
		Random rand = new Random();
		int halfradius = radius / 2;
		int tworadius = radius * 2;
		for (int X = -tworadius; X <= tworadius; X++)
		{
			for (int Y = -tworadius; Y <= tworadius; Y++)
			{
				for (int Z = -tworadius; Z <= tworadius; Z++)
				{
					int xx = (int) x + X;
					int yy = (int) y + Y;
					int zz = (int) z + Z;
					Block block = world.getBlock(xx, yy, zz);
					if (block == Blocks.air)
					{
						int dist = (int) Math.sqrt(X * X + Y * Y + Z * Z);
						if (dist < radius)
						{
							int varrand = 1 + dist - halfradius;
							if (dist < halfradius)
							{
								world.setBlock(xx, yy, zz, Blocks.fire);
							}
							else if (varrand > 0)
							{
								if (rand.nextInt(varrand) == 0 || rand.nextInt(varrand / 2 + 1) == 0)
								{
									world.setBlock(xx, yy, zz, Blocks.fire);
								}
							}
						}
					}
				}
			}
		}
	}
	
	private void createHole(World world, double x, double y, double z, int radius, boolean crater, int delete)
	{
		Random rand = new Random();
		int halfradius = radius >> 2;
		int tworadius = radius << 2;
		for (int X = -tworadius; X <= tworadius; X++)
		{
			int xx = (int) x + X;
			for (int Y = -tworadius; Y <= tworadius; Y++)
			{
				int yy = (int) y + Y;
				for (int Z = -tworadius; Z <= tworadius; Z++)
				{
					int zz = (int) z + Z;
					Block block = world.getBlock(xx, yy, zz);
					if (block != null && block != Blocks.air && block != Blocks.bedrock)
					{
						int dist = X * X + Y * Y + Z * Z;
						if (dist <= delete && block == RivalRebels.camo1 && block == RivalRebels.camo2 && block == RivalRebels.camo3)
						{
							world.setBlock(xx, yy, zz, Blocks.air);
						}
						else if (dist < radius)
						{
							int varrand = 1 + dist - halfradius;
							if (dist < halfradius)
							{
								breakBlock(world, xx, yy, zz, radius, x, y, z);
							}
							else if (varrand > 0)
							{
								if ((rand.nextInt(varrand) == 0 || rand.nextInt(varrand / 2 + 1) == 0))
								{
									breakBlock(world, xx, yy, zz, radius, x, y, z);
								}
							}
						}
						else if (dist < tworadius)
						{
							if ((Y >= 2 || (dist < radius * 1.5 && Y >= 1)) && crater)
							{
								breakBlock(world, xx, yy, zz, radius, x, y, z);
							}
						}
					}
				}
			}
		}
	}
	
	private void breakBlock(World world, int xx, int yy, int zz, int strength, double x, double y, double z)
	{
		Block block = world.getBlock(xx, yy, zz);
		if (block == RivalRebels.remotecharge)
		{
			world.setBlock(xx, yy, zz, Blocks.air);
			RivalRebelsSoundPlayer.playSound(world, 22, 0, xx, yy, zz, 0.5f, 0.3f);
			new Explosion(world, x + 0.5f, y + 0.5f, z + 0.5f, RivalRebels.chargeExplodeSize, false, false, RivalRebelsDamageSource.charge);
			return;
		}
		if (block == RivalRebels.toxicgas || block == Blocks.chest || block == Blocks.vine || block == Blocks.tallgrass || block == RivalRebels.flare || block == RivalRebels.light || block == RivalRebels.light2 || block == RivalRebels.reactive || block == RivalRebels.timedbomb)
		{
			world.setBlock(xx, yy, zz, Blocks.air);
			return;
		}
		if (block == Blocks.oak_stairs || block == Blocks.acacia_stairs || block == Blocks.dark_oak_stairs || block == Blocks.birch_stairs) world.setBlock(xx, yy, zz, Blocks.planks);
		if (block == RivalRebels.camo1 || block == RivalRebels.camo2 || block == RivalRebels.camo3 || block == RivalRebels.conduit)
		{
			if (world.rand.nextInt(20) != 0) return;
		}
		if (BlackList.explosion(block))
		{
			return;
		}
		
		EntityDebris e = new EntityDebris(world, xx, yy, zz);
		double xmo = x - xx;
		double ymo = y - yy;
		double zmo = z - zz;
		e.addVelocity(xmo * 0.2f, ymo * 0.2f, zmo * 0.2f);
		world.spawnEntityInWorld(e);
	}
	
	private void pushAndHurtEntities(World world, double x, double y, double z, int radius, DamageSource dmgsrc)
	{
		int var3 = MathHelper.floor_double(x - radius - 1.0D);
		int var4 = MathHelper.floor_double(x + radius + 1.0D);
		int var5 = MathHelper.floor_double(y - radius - 1.0D);
		int var28 = MathHelper.floor_double(y + radius + 1.0D);
		int var7 = MathHelper.floor_double(z - radius - 1.0D);
		int var29 = MathHelper.floor_double(z + radius + 1.0D);
		List var9 = world.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBox(var3, var5, var7, var4, var28, var29));
		Vec3 var30 = Vec3.createVectorHelper(x, y, z);
		
		radius *= 4;
		
		for (int var11 = 0; var11 < var9.size(); ++var11)
		{
			Entity var31 = (Entity) var9.get(var11);
			if (!(var31 instanceof EntityDebris)&&!(var31 instanceof EntityFlameBall)&&!(var31 instanceof EntityRhodes))
			{
				double var13 = var31.getDistance(x, y, z) / radius;
				
				if (var13 <= 1.0D)
				{
					double var15 = var31.posX - x;
					double var17 = var31.posY + var31.getEyeHeight() - y;
					double var19 = var31.posZ - z;
					double var33 = MathHelper.sqrt_double(var15 * var15 + var17 * var17 + var19 * var19);
					
					if (var33 != 0.0D)
					{
						var15 /= var33;
						var17 /= var33;
						var19 /= var33;
						double var32 = world.getBlockDensity(var30, var31.boundingBox);
						double var34 = (1.0D - var13) * var32;
						var31.attackEntityFrom(dmgsrc, (int) ((var34 * var34 + var34) / 2.0D * radius + 1.0D));
						var31.motionX += var15 * var34;
						var31.motionY += var17 * var34;
						var31.motionZ += var19 * var34;
					}
				}
			}
		}
	}
}
