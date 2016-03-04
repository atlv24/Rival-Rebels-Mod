package assets.rivalrebels.common.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsDamageSource;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.explosion.TsarBomba;

public class EntityTsarBlast extends EntityInanimate
{
	public TsarBomba	tsar		= null;
	public boolean		tsarhole	= false;
	public double		radius;
	public int			time		= 0;
	
	public EntityTsarBlast(World par1World)
	{
		super(par1World);
		ignoreFrustumCheck = true;
	}
	
	public EntityTsarBlast(World par1World, float x, float y, float z, TsarBomba tsarBomba)
	{
		super(par1World);
		ignoreFrustumCheck = true;
		tsar = tsarBomba;
		radius = tsar.radius;
		tsarhole = true;
		motionX = Math.sqrt(tsar.radius - RivalRebels.tsarBombaStrength) / 10;
		setPosition(x, y, z);
	}
	
	public EntityTsarBlast(World par1World, double x, double y, double z, float rad)
	{
		super(par1World);
		ignoreFrustumCheck = true;
		radius = rad;
		tsarhole = false;
		motionX = Math.sqrt(rad - RivalRebels.tsarBombaStrength) / 10;
		setPosition(x, y, z);
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		if (worldObj.rand.nextInt(10) == 0)
		{
			worldObj.playSoundAtEntity(this, "ambient.weather.thunder", 10.0F, 0.50F);
		}
		else
		{
			if (worldObj.rand.nextInt(5) == 0) RivalRebelsSoundPlayer.playSound(this, 26, 0, 100, 0.7f);
		}
		
		pushAndHurtEntities();
		
		ticksExisted++;
		
		if (ticksExisted > 1200 && !tsarhole) setDead();
		
		for (int i = 0; i < RivalRebels.tsarBombaSpeed + (motionX * 50); i++)
		{
			if (tsar != null)
			{
				tsar.update(this);
				/*if (tsar.update())
				{
					tsar = null;
				}*/
			}
			else
			{
				return;
			}
		}
	}
	
	public void pushAndHurtEntities()
	{
		radius *= 2;
		int var3 = MathHelper.floor_double(posX - radius - 1.0D);
		int var4 = MathHelper.floor_double(posX + radius + 1.0D);
		int var5 = MathHelper.floor_double(posY - radius - 1.0D);
		int var28 = MathHelper.floor_double(posY + radius + 1.0D);
		int var7 = MathHelper.floor_double(posZ - radius - 1.0D);
		int var29 = MathHelper.floor_double(posZ + radius + 1.0D);
		List var9 = worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getBoundingBox(var3, var5, var7, var4, var28, var29));
		Vec3 var30 = Vec3.createVectorHelper(posX, posY, posZ);
		
		for (int var11 = 0; var11 < var9.size(); ++var11)
		{
			Entity var31 = (Entity) var9.get(var11);
			if ((var31 instanceof EntityPlayer && ((EntityPlayer) var31).capabilities.isCreativeMode)
					|| var31 instanceof EntityNuclearBlast || var31 instanceof EntityTsarBlast) continue;
			double var13 = var31.getDistance(posX, posY, posZ) / radius;
			
			if (var13 <= 1.0D)
			{
				double var15 = var31.posX - posX;
				double var17 = var31.posY + var31.getEyeHeight() - posY;
				double var19 = var31.posZ - posZ;
				double var33 = MathHelper.sqrt_double(var15 * var15 + var17 * var17 + var19 * var19);
				
				if (var33 != 0.0D)
				{
					var15 /= var33;
					var17 /= var33;
					var19 /= var33;
					double var32 = worldObj.getBlockDensity(var30, var31.boundingBox);
					double var34 = (1.0D - var13) * var32 * ((var31 instanceof EntityB83 || var31 instanceof EntityHackB83) ? -1 : 1);
					if (var31 instanceof EntityRhodes)
					{
						var31.attackEntityFrom(RivalRebelsDamageSource.nuclearblast, (int) (radius*var34*0.2f));
					}
					else
					{
						var31.attackEntityFrom(RivalRebelsDamageSource.nuclearblast, (int) ((var34 * var34 + var34) / 2.0D * 8.0D * radius + 1.0D) * 20);
						var31.motionX -= var15 * var34 * 8;
						var31.motionY -= var17 * var34 * 8;
						var31.motionZ -= var19 * var34 * 8;
					}
				}
			}
		}
		radius /= 2;
	}
	
	@Override
	public void setDead()
	{
		super.setDead();
		//RivalRebelsServerPacketHandler.sendPacketToAllPlayers(3, 0);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		
	}
	
	@Override
	public int getBrightnessForRender(float par1)
	{
		return 1000;
	}
	
	@Override
	public float getBrightness(float par1)
	{
		return 1000F;
	}
	
	@Override
	public boolean isInRangeToRenderDist(double par1)
	{
		return true;
	}
	
	@Override
	protected void entityInit()
	{
		
	}
	
	public EntityTsarBlast setTime()
	{
		ticksExisted = 920;
		return this;
	}
}
