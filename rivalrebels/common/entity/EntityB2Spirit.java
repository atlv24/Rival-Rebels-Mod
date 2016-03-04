package assets.rivalrebels.common.entity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.tileentity.TileEntityLaptop;

public class EntityB2Spirit extends Entity
{
	private int					ticksInAir		= 0;
	private int					ticksSinceBomb	= 0;
	private boolean				hasBombed		= false;
	private TileEntityLaptop	t;
	private double				tx				= 0;
	private double				tz				= 0;
	public int					health;
	public boolean 				dropallbombs	= false;
	public boolean dropanyway = false;
	
	public EntityB2Spirit(World par1World)
	{
		super(par1World);
		this.setSize(30F, 4F);
		this.ignoreFrustumCheck = true;
		boundingBox.setBounds(-10, -3, -10, 10, 4, 10);
		health = RivalRebels.b2spirithealth;
	}
	
	public EntityB2Spirit(World par1World, double x, double y, double z, double x1, double y1, double z1, TileEntityLaptop tel, boolean dropall)
	{
		super(par1World);
		this.setSize(30F, 4F);
		this.ignoreFrustumCheck = true;
		boundingBox.setBounds(-10, -3, -10, 10, 4, 10);
		double x2 = x1 - x;
		double z2 = z1 - z;
		double dist = Math.sqrt(x2 * x2 + z2 * z2);
		x2 /= dist;
		z2 /= dist;
		if (tel != null) this.setPosition(x + (x2 * 120), Math.max(tel.yCoord + 60, 120), z + (z2 * 120));
		else this.setPosition(x + (x2 * 120), Math.max(y + 60, 120), z + (z2 * 120));
		motionX = -x2;
		motionZ = -z2;
		dropanyway = tel == null;
		tx = x;
		tz = z;
		
		this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(motionX, motionZ) * 180.0D / Math.PI);
		
		this.yOffset = 0.0F;
		health = RivalRebels.b2spirithealth;
		t = tel;
		if (t != null) t.canBomb = false;
		dropallbombs = dropall;
	}
	
	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity)
	{
		return par1Entity.boundingBox;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox()
	{
		return this.boundingBox;
	}
	
	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}
	
	@Override
	public boolean canBePushed()
	{
		return false;
	}
	
	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		this.lastTickPosX = this.posX;
		this.lastTickPosY = this.posY;
		this.lastTickPosZ = this.posZ;
		++this.ticksInAir;
		
		if (ticksInAir == 1000 && !hasBombed) setDead();
		
		if (health > 100) RivalRebelsSoundPlayer.playSound(this, 8, 0, 4.5f, 1.3f);
		
		double dist = (int) Math.sqrt((posX - tx) * (posX - tx) + (posZ - tz) * (posZ - tz));
		int moe = 5;
		if (dist <= 8 + moe && dist >= 8 - moe)
		{
			dist -= 3;
			
			double chance = dist / (moe * 2.5d);
			if (Math.random() > chance && !hasBombed && ((t != null && t.b2spirit > 0) || dropanyway))
			{
				worldObj.spawnEntityInWorld(new EntityB83(worldObj, posX+Math.random()*4-2, posY - 3, posZ+Math.random()*4-2, rotationYaw, rotationPitch + (float)(90*Math.random())));
				hasBombed = !dropallbombs;
				if (t != null) t.b2spirit--;
			}
		}
		else if (dist < 8 - moe)
		{
			hasBombed = true;
		}
		
		if (!this.worldObj.isRemote)
		{
			List var5 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
			Iterator var8 = var5.iterator();
			
			while (var8.hasNext())
			{
				Entity var9 = (Entity) var8.next();
				
				if (var9 instanceof EntityRocket)
				{
					((EntityRocket) var9).explode(null);
				}
				
				if (var9 instanceof EntityPlasmoid)
				{
					((EntityPlasmoid) var9).explode();
				}
				
				if (var9 instanceof EntityLaserBurst)
				{
					((EntityLaserBurst) var9).setDead();
					this.attackEntityFrom(DamageSource.generic, 6);
				}
			}
		}
		
		if (hasBombed)
		{
			ticksSinceBomb++;
			if (ticksSinceBomb > 70) setDead();
		}
		
		this.posX += this.motionX;
		this.posY += this.motionY;
		this.posZ += this.motionZ;
		float var16 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
		this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
		
		for (this.rotationPitch = (float) (Math.atan2(this.motionY, var16) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
		{
			;
		}
		
		while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
		{
			this.prevRotationPitch += 360.0F;
		}
		
		while (this.rotationYaw - this.prevRotationYaw < -180.0F)
		{
			this.prevRotationYaw -= 360.0F;
		}
		
		while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
		{
			this.prevRotationYaw += 360.0F;
		}
		
		this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
		this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
		this.setPosition(this.posX, this.posY, this.posZ);
		
		// worldObj.getChunkProvider().provideChunk((int)this.posX >> 4, (int)this.posZ >> 4);
		// worldObj.getChunkProvider().provideChunk(((int)this.posX >> 4) + 1, (int)this.posZ >> 4);
		// worldObj.getChunkProvider().provideChunk(((int)this.posX >> 4) - 1, (int)this.posZ >> 4);
		// worldObj.getChunkProvider().provideChunk((int)this.posX >> 4, ((int)this.posZ >> 4) + 1);
		// worldObj.getChunkProvider().provideChunk((int)this.posX >> 4, ((int)this.posZ >> 4) - 1);
		// worldObj.getChunkProvider().provideChunk(((int)this.posX >> 4) + 1, ((int)this.posZ >> 4) + 1);
		// worldObj.getChunkProvider().provideChunk(((int)this.posX >> 4) + 1, ((int)this.posZ >> 4) - 1);
		// worldObj.getChunkProvider().provideChunk(((int)this.posX >> 4) - 1, ((int)this.posZ >> 4) + 1);
		// worldObj.getChunkProvider().provideChunk(((int)this.posX >> 4) - 1, ((int)this.posZ >> 4) - 1);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		
	}
	
	@Override
	public boolean isInRangeToRenderDist(double par1)
	{
		return true;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		super.attackEntityFrom(par1DamageSource, par2);
		if (!this.isDead && !this.worldObj.isRemote)
		{
			this.health -= par2;
			
			if (this.health <= 0)
			{
				this.setDead();
				this.worldObj.createExplosion((Entity) null, this.posX, this.posY, this.posZ, 6.0F, true);
				worldObj.spawnEntityInWorld(new EntityB2Frag(worldObj, this, 0));
				worldObj.spawnEntityInWorld(new EntityB2Frag(worldObj, this, 1));
				EntityZombie pz = new EntityZombie(worldObj);
				pz.setPosition(posX, posY, posZ);
				worldObj.spawnEntityInWorld(pz);
				RivalRebelsSoundPlayer.playSound(this, 0, 0, 30, 1);
				if (t != null)
				{
					t.b2spirit--;
					if (t.b2spirit < 0) t.b2spirit = 0;
				}
				if (!hasBombed)
				{
					worldObj.spawnEntityInWorld(new EntityItem(worldObj, posX, posY - 10, posZ, new ItemStack(RivalRebels.nuclearelement, 1)));
				}
			}
		}
		
		return true;
	}
	
	@Override
	protected void entityInit()
	{
	}
	
	@Override
	public void setDead()
	{
		if (t != null) t.canBomb = true;
		super.setDead();
	}
}
