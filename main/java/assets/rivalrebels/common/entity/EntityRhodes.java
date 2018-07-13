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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.gui.RivalRebelsRenderOverlay;
import assets.rivalrebels.common.core.RivalRebelsDamageSource;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.explosion.Explosion;
import assets.rivalrebels.common.packet.PacketDispatcher;
import assets.rivalrebels.common.packet.RhodesJumpPacket;
import assets.rivalrebels.common.packet.RhodesPacket;
import assets.rivalrebels.common.packet.TextPacket;
import assets.rivalrebels.common.round.RivalRebelsPlayer;
import assets.rivalrebels.common.round.RivalRebelsTeam;
import assets.rivalrebels.common.tileentity.TileEntityForceFieldNode;
import assets.rivalrebels.common.tileentity.TileEntityLaptop;
import assets.rivalrebels.common.tileentity.TileEntityMachineBase;
import assets.rivalrebels.common.tileentity.TileEntityNukeCrate;
import assets.rivalrebels.common.tileentity.TileEntityReactive;
import assets.rivalrebels.common.tileentity.TileEntityReactor;
import assets.rivalrebels.common.tileentity.TileEntityReciever;
import assets.rivalrebels.common.tileentity.TileEntityRhodesActivator;

public class EntityRhodes extends Entity
{
	public int health = RivalRebels.rhodesHealth;
	public float scale = 1;
	private int damageUntilWake = 100;
	private static HashSet blocklist = new HashSet();
	static {
		blocklist.add(Blocks.bedrock);
		blocklist.add(Blocks.dirt);
		blocklist.add(Blocks.stone);
		blocklist.add(Blocks.sand);
		blocklist.add(Blocks.sandstone);
		blocklist.add(Blocks.gravel);
		blocklist.add(Blocks.iron_block);
		blocklist.add(Blocks.lapis_block);
		blocklist.add(Blocks.stone_slab);
		blocklist.add(Blocks.stonebrick);
		blocklist.add(Blocks.cobblestone);
		blocklist.add(Blocks.obsidian);
		blocklist.add(Blocks.netherrack);
		blocklist.add(Blocks.end_stone);
		blocklist.add(Blocks.hardened_clay);
		blocklist.add(Blocks.stained_hardened_clay);
		blocklist.add(RivalRebels.reactive);
	}
	public static String[] names = 
	{
		"Rhodes", //1
		"Magnesium", //2
		"Arsenic", //3
		"Vanadium", //4
		"Aurum", //5
		"Iodine", //6
		"Iron", //7
		"Astatine", //8
		"Cobalt", //9
		"Strontium", //10
		"Bismuth", //11
		"Zinc", //12
		"Osmium", //13
		"Neon", //14
		"Argent", //15
		"Wolfram", //16
		"Space"
	};
	public float bodyyaw = 0;
	public float headyaw = 0;
	public float headpitch = 0;
	public float leftarmyaw = 0;
	public float leftarmpitch = 0;
	public float rightarmyaw = 0;
	public float rightarmpitch = 0;
	public float leftthighpitch = 0;
	public float rightthighpitch = 0;
	public float leftshinpitch = 0;
	public float rightshinpitch = 0;
	public float lastbodyyaw = 0;
	public float lastheadyaw = 0;
	public float lastheadpitch = 0;
	public float lastleftarmyaw = 0;
	public float lastleftarmpitch = 0;
	public float lastrightarmyaw = 0;
	public float lastrightarmpitch = 0;
	public float lastleftthighpitch = 0;
	public float lastrightthighpitch = 0;
	public float lastleftshinpitch = 0;
	public float lastrightshinpitch = 0;
	public boolean fire;
	public boolean endangered = false;
	public int flying = 0;
	
	public int ticksSinceLastPacket = 0;
	public byte laserOn = 0; // 0 = off, 1 = top, 2 = bottom, 3 = both
	public byte colorType = 0;
	public static byte lastct = 0;
	public static int forcecolor = -1;
	public EntityPlayer rider;
	public EntityPlayer passenger1;
	public EntityPlayer passenger2;
	public static final int recharge = 3;
	public static final int ecjet = 10+recharge;
	public static final int eclaser = 6+recharge;
	public static final int ecshield = 8+recharge;
	public static final int maxenergy = 800;
	public int energy = maxenergy;
	public int b2energy = 0;
	public int nukecount = 8;
	public int rocketcount = 5000;
	public int flamecount = 10000;
	public boolean rocket = false;
	public boolean laser = false;
	public boolean prevflame = false;
	public boolean flame = false;
	public boolean forcefield = false;
	public boolean plasma = false;
	public boolean bomb = false;
	public boolean jet = false;
	public boolean stop = true;
	public boolean guard = false;
	public boolean b2spirit = false;
	public boolean freeze = false;
	public int plasmacharge = 0;
	public int tickssincenuke = 10;
	public static String texloc;
	public static int texfolder = -1;
	public String itexloc;
	public int itexfolder;

	public int wakeX = -1;
	public int wakeY = -1;
	public int wakeZ = -1;
	
	public EntityRhodes(World w)
	{
		super(w);
		ignoreFrustumCheck = true;
		boundingBox.setBounds(-5*scale, -15*scale, -5*scale, 5*scale, 15*scale, 5*scale);
		noClip = true;
		entityCollisionReduction = 100;
		yOffset = 0;
		stepHeight = 0;
		actionqueue.add(new RhodesAction(0, 1));
		RivalRebelsSoundPlayer.playSound(this, 12, 1, 90f, 1f);
		itexfolder = texfolder;
		if (texfolder != -1)
		{
			itexloc = texloc;
		}
		if (forcecolor == -1)
		{
			colorType = (byte) RivalRebels.rhodesTeams[lastct];
			if (!w.isRemote)
			{
				lastct++;
				if (lastct == RivalRebels.rhodesTeams.length) lastct = 0;
			}
		}
		else
		{
			colorType = (byte) forcecolor;
		}
		Random random = new Random(RivalRebels.rhodesRandomSeed);
		for (int i = 0; i < colorType*3; i++)
		{
			random.nextFloat();
		}
		nukecount = RivalRebels.rhodesNukes;
		nukecount += nukecount * random.nextFloat() * RivalRebels.rhodesRandom;
		rocketcount += rocketcount * random.nextFloat() * RivalRebels.rhodesRandom;
		flamecount += flamecount * random.nextFloat() * RivalRebels.rhodesRandom;
	}
	
	public EntityRhodes(World w, double x, double y, double z, float s)
	{
		this(w);
		scale = s;
		if (scale >= 2.0)
		{
			nukecount *= 0.25;
			rocketcount *= 0.004;
		}
		health = health - 5000 + (int)(5000 * Math.min(scale,4));
		boundingBox.setBounds(-5*scale, -15*scale, -5*scale, 5*scale, 15*scale, 5*scale);
		setPosition(x, y, z);
		if (!worldObj.isRemote) PacketDispatcher.packetsys.sendToAll(new TextPacket("RivalRebels.WARNING " + getName() + " RivalRebels.tsar.armed"));
	}
	
	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate()
	{
		if ((wakeY != -1) && (worldObj.getBlock(wakeX, wakeY, wakeZ) != RivalRebels.rhodesactivator))
		{
			damageUntilWake -= 100;
			
		}
		if (posY <= 0)
		{
			setDead();
			return;
		}
        isInWeb = true;
        fallDistance = 0.0F;
		if (health > 0)
		{
			lastTickPosX = posX;
			lastTickPosY = posY;
			lastTickPosZ = posZ;
			float headY = 0;
			float syaw = MathHelper.sin(bodyyaw * 0.01745329252f);
			float cyaw = MathHelper.cos(bodyyaw * 0.01745329252f);
			float leftlegheight = 7.26756f
					+ (MathHelper.cos((leftthighpitch+11.99684962f)*0.01745329252f) * 7.331691240f)
					+ (MathHelper.cos((leftthighpitch+leftshinpitch-12.2153067f)*0.01745329252f) * 8.521366426f);
			float rightlegheight = 7.26756f
					+ (MathHelper.cos((rightthighpitch+11.99684962f)*0.01745329252f) * 7.331691240f)
					+ (MathHelper.cos((rightthighpitch+rightshinpitch-12.2153067f)*0.01745329252f) * 8.521366426f);
			leftlegheight *= scale;
			rightlegheight *= scale;
			float bodyY = (leftlegheight > rightlegheight) ? leftlegheight : rightlegheight;
			if (!worldObj.isRemote)
			{
				doAITick(syaw, cyaw);
				breakBlocks(syaw, cyaw, leftlegheight, rightlegheight, bodyY);
				if (ticksExisted % 5 == 0) PacketDispatcher.packetsys.sendToAll(new RhodesPacket(this));
			}
			posX += motionX;
			posY += motionY;
			posZ += motionZ;
			if (ticksExisted % 3 == 0) doCollisions();
			ticksSinceLastPacket++;
			setPosition(posX, posY, posZ);

			RivalRebels.proxy.setOverlay(this);
			
			if (rider!=null)
			{
				rider.setPosition(((posX+syaw*5.5*scale) - rider.posX) * 0.33f + rider.posX, ((posY + bodyY - 10*scale - (worldObj.isRemote?0:rider.getEyeHeight())) - rider.posY) * 0.33f + rider.posY, ((posZ+cyaw*5.5*scale) - rider.posZ) * 0.33f + rider.posZ);
				rider.onGround = true;
				if (worldObj.isRemote) RivalRebels.round.setInvisible(rider);
				rider.setInWeb();
				rider.capabilities.disableDamage = true;
				if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT && rider == Minecraft.getMinecraft().thePlayer) PacketDispatcher.packetsys.sendToServer(new RhodesJumpPacket(this, RivalRebels.proxy.spacebar(), RivalRebels.proxy.a(), RivalRebels.proxy.w(), RivalRebels.proxy.d(), RivalRebels.proxy.c(), RivalRebels.proxy.f(), RivalRebels.proxy.s(), RivalRebels.proxy.x(), RivalRebels.proxy.z(), RivalRebels.proxy.g()));
			}
			if (passenger1 != null)
			{
				float offset = 1.62f;
				if (worldObj.isRemote)
				{
					if (Minecraft.getMinecraft().thePlayer == passenger1)
					{
						offset = 0;
					}
				}
				passenger1.setPosition(posX+cyaw*6.5f*scale,
										posY + bodyY - 6.38f*scale - offset,
										posZ-syaw*6.5f*scale);
				passenger1.onGround = true;
				passenger1.setInWeb();
			}
			if (passenger2 != null)
			{
				float offset = 1.62f;
				if (worldObj.isRemote)
				{
					if (Minecraft.getMinecraft().thePlayer == passenger2)
					{
						offset = 0;
					}
				}
				passenger2.setPosition(posX-cyaw*6.5f*scale,
										posY + bodyY - 6.38f*scale - offset,
										posZ+syaw*6.5f*scale);
				passenger2.onGround = true;
				passenger2.setInWeb();
			}
		}
		else
		{
			if (!worldObj.isRemote)
			{
				if (health == 0) PacketDispatcher.packetsys.sendToAll(new TextPacket("RivalRebels.Status " + getName() + " RivalRebels.meltdown" + (rider == null? "" : " " + rider.getCommandSenderName())));
				if (ticksExisted % 5 == 0) PacketDispatcher.packetsys.sendToAll(new RhodesPacket(this));
				if (health < -100)
				{
					setDead();
				}
				if (health == 0)
				{
					float syaw = MathHelper.sin(bodyyaw * 0.01745329252f);
					float cyaw = MathHelper.cos(bodyyaw * 0.01745329252f);
					worldObj.spawnEntityInWorld(new EntityRhodesHead(worldObj, posX, posY+13*scale, posZ, scale, colorType));
					worldObj.spawnEntityInWorld(new EntityRhodesTorso(worldObj, posX, posY+7*scale, posZ, scale, colorType));
					worldObj.spawnEntityInWorld(new EntityRhodesLeftUpperArm(worldObj, posX+cyaw*6.4*scale, posY+7*scale, posZ+syaw*6.4*scale,scale, colorType));
					worldObj.spawnEntityInWorld(new EntityRhodesRightUpperArm(worldObj, posX-cyaw*6.4*scale, posY+7*scale, posZ-syaw*6.4*scale,scale, colorType));
					worldObj.spawnEntityInWorld(new EntityRhodesLeftLowerArm(worldObj, posX+cyaw*6.4*scale, posY+3*scale, posZ+syaw*6.4*scale,scale, colorType));
					worldObj.spawnEntityInWorld(new EntityRhodesRightLowerArm(worldObj, posX-cyaw*6.4*scale, posY+3*scale, posZ-syaw*6.4*scale,scale, colorType));
					worldObj.spawnEntityInWorld(new EntityRhodesLeftUpperLeg(worldObj, posX+cyaw*3*scale, posY-3*scale, posZ+syaw*3*scale,scale, colorType));
					worldObj.spawnEntityInWorld(new EntityRhodesRightUpperLeg(worldObj, posX-cyaw*3*scale, posY-3*scale, posZ-syaw*3*scale,scale, colorType));
					worldObj.spawnEntityInWorld(new EntityRhodesLeftLowerLeg(worldObj, posX+cyaw*3*scale, posY-10*scale, posZ+syaw*3*scale,scale, colorType));
					worldObj.spawnEntityInWorld(new EntityRhodesRightLowerLeg(worldObj, posX-cyaw*3*scale, posY-10*scale, posZ-syaw*3*scale,scale, colorType));
				}
			}
			health--;
		}
		
		if (rider != null)
		{
			if ((rider.isSneaking() || rider.isDead) && RivalRebels.rhodesExit)
			{
				freeze = false;
				if (!rider.capabilities.isCreativeMode) rider.capabilities.disableDamage = false;
				rider = null;
			}
			if (health <= 0 && rider != null)
			{
				freeze = false;
				if (!rider.capabilities.isCreativeMode)
				{
					rider.capabilities.disableDamage = false;
					rider.attackEntityFrom(DamageSource.outOfWorld, 2000000);
				}
				rider = null;
			}
		}
		
		if (passenger1 != null)
		{
			if ((passenger1.isSneaking() || passenger1.isDead) && RivalRebels.rhodesExit)
			{
				if (!passenger1.capabilities.isCreativeMode) passenger1.capabilities.disableDamage = false;
				passenger1 = null;
			}
			if (health <= 0 && passenger1 != null)
			{
				if (!passenger1.capabilities.isCreativeMode)
				{
					passenger1.capabilities.disableDamage = false;
					passenger1.attackEntityFrom(DamageSource.outOfWorld, 2000000);
				}
				passenger1 = null;
			}
		}
		
		if (passenger2 != null)
		{
			if ((passenger2.isSneaking() || passenger2.isDead) && RivalRebels.rhodesExit)
			{
				if (!passenger2.capabilities.isCreativeMode) passenger2.capabilities.disableDamage = false;
				passenger2 = null;
			}
			if (health <= 0 && passenger2 != null)
			{
				if (!passenger2.capabilities.isCreativeMode)
				{
					passenger2.capabilities.disableDamage = false;
					passenger2.attackEntityFrom(DamageSource.outOfWorld, 2000000);
				}
				passenger2 = null;
			}
		}
		
		prevflame = flame;
	}
	
	private void breakBlocks(float syaw, float cyaw, float leftlegheight, float rightlegheight, float bodyY)
	{
		float leftlegstride = (MathHelper.sin((leftthighpitch+11.99684962f)*0.01745329252f) * 7.331691240f)
				+ (MathHelper.sin((leftthighpitch+leftshinpitch-12.2153067f)*0.01745329252f) * 8.521366426f);
		float rightlegstride = (MathHelper.sin((rightthighpitch+11.99684962f)*0.01745329252f) * 7.331691240f)
				+ (MathHelper.sin((rightthighpitch+rightshinpitch-12.2153067f)*0.01745329252f) * 8.521366426f);
		leftlegstride *= scale;
		rightlegstride *= scale;
		
		float lpx = (float)posX - syaw * leftlegstride + cyaw * 3.6846f*scale;
		float lpy = (float)posY-15*scale + bodyY - leftlegheight;
		float lpz = (float)posZ - cyaw * leftlegstride - syaw * 3.6846f*scale;
		float rpx = (float)posX - syaw * rightlegstride - cyaw * 3.6846f*scale;
		float rpy = (float)posY-15*scale + bodyY - rightlegheight;
		float rpz = (float)posZ - cyaw * rightlegstride + syaw * 3.6846f*scale;
		int ilpx = (int)lpx;
		int ilpy = (int)lpy;
		int ilpz = (int)lpz;
		int irpx = (int)rpx;
		int irpy = (int)rpy;
		int irpz = (int)rpz;
		
		if (ac > 1)
		{
			if (RivalRebels.rhodesBlockBreak > 0.0f)
			{
				int sx = (int) (posX - 5.0f * scale);
				int sy = (int) (posY - 15.0f * scale);
				int sz = (int) (posZ - 5.0f * scale);
				int ex = (int) (posX + 5.0f * scale);
				int ey = (int) (posY + 15.0f * scale);
				int ez = (int) (posZ + 5.0f * scale);
				for (int y = sy; y < ey; y++)
				{
					if ((y + ticksExisted) % 8 == 0)
					{
						for (int x = sx; x < ex; x++)
						{
							for (int z = sz; z < ez; z++)
							{
								worldObj.setBlock(x,y,z, Blocks.air);
							}
						}
					}
				}
			}
			else
			{
				int irpyyoff = irpy + (ticksExisted % 6);
				int ilpyyoff = ilpy + (ticksExisted % 6);
				Block b = worldObj.getBlock(irpx, irpyyoff, irpz);
				if (b != Blocks.water && b != Blocks.flowing_water && b != Blocks.air)
				{
					worldObj.setBlock(irpx-2, irpyyoff, irpz-2, Blocks.air);
					worldObj.setBlock(irpx-2, irpyyoff, irpz-1, Blocks.air);
					worldObj.setBlock(irpx-2, irpyyoff, irpz+0, Blocks.air);
					worldObj.setBlock(irpx-2, irpyyoff, irpz+1, Blocks.air);
					worldObj.setBlock(irpx-2, irpyyoff, irpz+2, Blocks.air);
					worldObj.setBlock(irpx-1, irpyyoff, irpz-2, Blocks.air);
					worldObj.setBlock(irpx-1, irpyyoff, irpz-1, Blocks.air);
					worldObj.setBlock(irpx-1, irpyyoff, irpz+0, Blocks.air);
					worldObj.setBlock(irpx-1, irpyyoff, irpz+1, Blocks.air);
					worldObj.setBlock(irpx-1, irpyyoff, irpz+2, Blocks.air);
					worldObj.setBlock(irpx-0, irpyyoff, irpz-2, Blocks.air);
					worldObj.setBlock(irpx-0, irpyyoff, irpz-1, Blocks.air);
					worldObj.setBlock(irpx-0, irpyyoff, irpz+0, Blocks.air);
					worldObj.setBlock(irpx-0, irpyyoff, irpz+1, Blocks.air);
					worldObj.setBlock(irpx-0, irpyyoff, irpz+2, Blocks.air);
					worldObj.setBlock(irpx+1, irpyyoff, irpz-2, Blocks.air);
					worldObj.setBlock(irpx+1, irpyyoff, irpz-1, Blocks.air);
					worldObj.setBlock(irpx+1, irpyyoff, irpz+0, Blocks.air);
					worldObj.setBlock(irpx+1, irpyyoff, irpz+1, Blocks.air);
					worldObj.setBlock(irpx+1, irpyyoff, irpz+2, Blocks.air);
					worldObj.setBlock(irpx+2, irpyyoff, irpz-2, Blocks.air);
					worldObj.setBlock(irpx+2, irpyyoff, irpz-1, Blocks.air);
					worldObj.setBlock(irpx+2, irpyyoff, irpz+0, Blocks.air);
					worldObj.setBlock(irpx+2, irpyyoff, irpz+1, Blocks.air);
					worldObj.setBlock(irpx+2, irpyyoff, irpz+2, Blocks.air);
				}
				b = worldObj.getBlock(ilpx, ilpyyoff, ilpz);
				if (b != Blocks.water && b != Blocks.flowing_water && b != Blocks.air)
				{
					worldObj.setBlock(ilpx-2, ilpyyoff, ilpz-2, Blocks.air);
					worldObj.setBlock(ilpx-2, ilpyyoff, ilpz-1, Blocks.air);
					worldObj.setBlock(ilpx-2, ilpyyoff, ilpz+0, Blocks.air);
					worldObj.setBlock(ilpx-2, ilpyyoff, ilpz+1, Blocks.air);
					worldObj.setBlock(ilpx-2, ilpyyoff, ilpz+2, Blocks.air);
					worldObj.setBlock(ilpx-1, ilpyyoff, ilpz-2, Blocks.air);
					worldObj.setBlock(ilpx-1, ilpyyoff, ilpz-1, Blocks.air);
					worldObj.setBlock(ilpx-1, ilpyyoff, ilpz+0, Blocks.air);
					worldObj.setBlock(ilpx-1, ilpyyoff, ilpz+1, Blocks.air);
					worldObj.setBlock(ilpx-1, ilpyyoff, ilpz+2, Blocks.air);
					worldObj.setBlock(ilpx-0, ilpyyoff, ilpz-2, Blocks.air);
					worldObj.setBlock(ilpx-0, ilpyyoff, ilpz-1, Blocks.air);
					worldObj.setBlock(ilpx-0, ilpyyoff, ilpz+0, Blocks.air);
					worldObj.setBlock(ilpx-0, ilpyyoff, ilpz+1, Blocks.air);
					worldObj.setBlock(ilpx-0, ilpyyoff, ilpz+2, Blocks.air);
					worldObj.setBlock(ilpx+1, ilpyyoff, ilpz-2, Blocks.air);
					worldObj.setBlock(ilpx+1, ilpyyoff, ilpz-1, Blocks.air);
					worldObj.setBlock(ilpx+1, ilpyyoff, ilpz+0, Blocks.air);
					worldObj.setBlock(ilpx+1, ilpyyoff, ilpz+1, Blocks.air);
					worldObj.setBlock(ilpx+1, ilpyyoff, ilpz+2, Blocks.air);
					worldObj.setBlock(ilpx+2, ilpyyoff, ilpz-2, Blocks.air);
					worldObj.setBlock(ilpx+2, ilpyyoff, ilpz-1, Blocks.air);
					worldObj.setBlock(ilpx+2, ilpyyoff, ilpz+0, Blocks.air);
					worldObj.setBlock(ilpx+2, ilpyyoff, ilpz+1, Blocks.air);
					worldObj.setBlock(ilpx+2, ilpyyoff, ilpz+2, Blocks.air);
				}
				int px = (int) posX;
				int py = (int) (posY-5*scale + (ticksExisted%20)*scale);
				int pz = (int) posZ;
				for (int x = -4; x < 5; x++)
				{
					for (int z = -4; z < 5; z++)
					{
						b = worldObj.getBlock(px+x, py, pz+z);
						if (b != Blocks.air && b != Blocks.water && b != Blocks.flowing_water)
						{
							worldObj.setBlock(px+x, py, pz+z, Blocks.air);
							if (rand.nextInt(333)==0)
							{
								new Explosion(worldObj, px, py, pz, 3, false, true, RivalRebelsDamageSource.rocket);
								RivalRebelsSoundPlayer.playSound(this, 23, 3, 4.5f, (float) (0.8f + Math.random()*0.3f));
							}
						}
					}
				}
			}
		}
		
		boolean bl = blocklist.contains(worldObj.getBlock(ilpx, ilpy-1, ilpz));
		boolean ol = blocklist.contains(worldObj.getBlock(ilpx, ilpy, ilpz));
		boolean br = blocklist.contains(worldObj.getBlock(irpx, irpy-1, irpz));
		boolean or = blocklist.contains(worldObj.getBlock(irpx, irpy, irpz));
		
		if (bl||br)
		{
			if (ol||or)
			{
				if (motionY < 0.125)
				{
					motionY = 0.125;
					if (!fire && rand.nextInt(32)==0)
					{
						RivalRebelsSoundPlayer.playSound(this, 23, 1, 4.5f, (float) (0.8f + Math.random()*0.2f));
						fire = true;
					}
				}
			}
			else if (motionY < 0)
			{
				motionY = 0f;
				fire = false;
				posY = (int) posY;
			}
		}
		else if (flying == 0)
		{
			motionY -= 0.03f;
			if (!fire && rand.nextInt(32)==0)
			{
				RivalRebelsSoundPlayer.playSound(this, 23, 1, 4.5f, (float) (0.8f + Math.random()*0.2f));
				fire = true;
			}
		}
		if (flying!=0)
		{
			flying--;
			if (b2spirit)
			{
				motionY = 0.03;
			}
			else
			{
				motionY += 0.03f;
			}
			if (rand.nextInt(32)==0)
			{
				RivalRebelsSoundPlayer.playSound(this, 23, 1, 4.5f, (float) (0.8f + Math.random()*0.2f));
			}
			fire = true;
		}
	}

	private void doCollisions()
	{
		Iterator iter = worldObj.loadedEntityList.iterator();
		
		while(iter.hasNext())
		{
			Entity e = (Entity) iter.next();
			if (e == rider || e == passenger1 || e == passenger2) continue;
			double bbd = (e.width+10*scale)*0.5;
			double bbh = (e.height+30*scale)*0.5;
			if (e instanceof EntityRhodes)
			{
				bbd = 10 * (((EntityRhodes)e).scale+scale) * 0.5;
				bbh = 30 * (((EntityRhodes)e).scale+scale) * 0.5;
			}
			double dist = (e.posX-posX)*(e.posX-posX)+(e.posZ-posZ)*(e.posZ-posZ);
			if ((ac == 0 || ac == 1 || ac == 11 || !RivalRebels.rhodesAI) && e instanceof EntityPlayer && dist < bbd*bbd*0.25f && e.posY < posY+bbh+1 && e.posY > posY-bbh+1)
			{
				if (rider == null)
				{
					rider = (EntityPlayer) e;
					RivalRebelsSoundPlayer.playSound(this, 12, 1, 90f, 1f);
				}
				else if (passenger1 == null)
				{
					passenger1 = (EntityPlayer) e;
					RivalRebelsSoundPlayer.playSound(this, 12, 1, 90f, 1f);
				}
				else if (passenger2 == null)
				{
					passenger2 = (EntityPlayer) e;
					RivalRebelsSoundPlayer.playSound(this, 12, 1, 90f, 1f);
				}
			}
			else if (dist < bbd*bbd && e.posY > posY-bbh && e.posY < posY+bbh)
			{
				if (e != this && !(e instanceof EntityFallingBlock) && !(e instanceof EntityDebris))
				{
					if (e.posY > posY+15)
					{
						e.motionY *= -0.5;
						e.posY = posY+bbh;
					}
					else if (e.posY < posY-15)
					{
						e.motionY *= -0.5;
						e.posY = posY-bbh;
					}
					else
					{
						e.motionX *= -1;
						e.motionZ *= -1;
						double d3 = bbd/Math.sqrt(dist);
						e.posX = posX+(e.posX - posX)*d3;
						e.posZ = posZ+(e.posZ - posZ)*d3;
					}
					e.setPosition(e.posX, e.posY, e.posZ);
				}
				
				if (e instanceof EntityRocket)
				{
					((EntityRocket) e).ticksExisted = RivalRebels.rpgDecay;
					this.attackEntityFrom(DamageSource.generic, 20);
				}
				else if (e instanceof EntitySeekB83)
				{
					((EntitySeekB83) e).ticksExisted = 800;
					this.attackEntityFrom(DamageSource.generic, 24);
				}
				else if (e instanceof EntityHackB83)
				{
					((EntityHackB83) e).ticksInAir = -100;
					this.attackEntityFrom(DamageSource.generic, 40);
				}
				else if (e instanceof EntityB83)
				{
					((EntityB83) e).ticksInAir = -100;
					this.attackEntityFrom(DamageSource.generic, 40);
				}
				else if (e instanceof EntityBomb)
				{
					((EntityBomb) e).explode(true);
					for (int i = 0; i < RivalRebels.bombDamageToRhodes; i++) this.attackEntityFrom(DamageSource.generic, 50);
				}
				else if (e instanceof EntityNuke)
				{
					((EntityNuke) e).ticksInAir = -100;
					this.attackEntityFrom(DamageSource.generic, 80);
				}
				else if (e instanceof EntityTsar)
				{
					((EntityTsar) e).ticksInAir = -100;
					this.attackEntityFrom(DamageSource.generic, 100);
				}
				else if (e instanceof EntityTheoreticalTsar)
				{
					((EntityTheoreticalTsar) e).ticksInAir = -100;
					this.attackEntityFrom(DamageSource.generic, 100);
				}
				else if (e instanceof EntityAntimatterBomb)
				{
					((EntityAntimatterBomb) e).ticksInAir = -100;
					this.attackEntityFrom(DamageSource.generic, 100);
				}
				else if (e instanceof EntityTachyonBomb)
				{
					((EntityTachyonBomb) e).ticksInAir = -100;
					this.attackEntityFrom(DamageSource.generic, 100);
				}
				else if (e instanceof EntityHotPotato)
				{
					((EntityHotPotato) e).ticksExisted = -100;
					this.attackEntityFrom(DamageSource.generic, 100);
				}
				else if (e instanceof EntityB83NoShroom)
				{
					((EntityB83NoShroom) e).ticksInAir = -100;
					this.attackEntityFrom(DamageSource.generic, 40);
				}
				else if (e instanceof EntityPlasmoid)
				{
					((EntityPlasmoid) e).explode();
					this.attackEntityFrom(DamageSource.generic, 8);
				}
				else if (e instanceof EntityFlameBall)
				{
					((EntityFlameBall) e).setDead();
					this.attackEntityFrom(DamageSource.generic, 3);
				}
				else if (e instanceof EntityFlameBall1)
				{
					((EntityFlameBall1) e).setDead();
					this.attackEntityFrom(DamageSource.generic, 4);
				}
				else if (e instanceof EntityFlameBall2)
				{
					((EntityFlameBall2) e).setDead();
					this.attackEntityFrom(DamageSource.generic, 2);
				}
				else if (e instanceof EntityLaserBurst)
				{
					((EntityLaserBurst) e).setDead();
					this.attackEntityFrom(DamageSource.generic, 4);
				}
			}
		}
	}
	
	private class RhodesAction
	{
		public int action;
		public int duration;
		public RhodesAction(int a, int d)
		{
			action = a;
			duration = d;
		}
	}
	
	private int ac = 0;
	private int counter = 0;
	private Queue<RhodesAction> actionqueue = new LinkedList<RhodesAction>();
	private int teamToRaid;
	private boolean raidedOmegaAlready = false;
	private boolean raidedSigmaAlready = false;
	private void doAITick(float syaw, float cyaw)
	{
		if (health*2 < RivalRebels.rhodesHealth) endangered = true;
		if (!b2spirit)
		{
			motionX = 0;
			motionZ = 0;
		}
		if (rider != null)
		{
			if (b2spirit && !freeze && b2energy == 0 && scale < 1.5f && scale > 0.5f)
			{
				freeze = true;
				nukecount--;
				health -= 1000;
				worldObj.spawnEntityInWorld(new EntityB2Spirit(this));
			}
			if (RivalRebels.rhodesHold) return;
			if (energy < maxenergy) energy += recharge;
			if (!RivalRebels.infiniteAmmo)
			{
				rocket &= rocketcount > 0;
				flame &= flamecount > 0;
			}
			if (!RivalRebels.infiniteNukes)
			{
				bomb &= nukecount > 0;
			}
			forcefield &= energy > ecshield;
			laser &= energy > eclaser;
			jet &= (energy+b2energy) > ecjet;
			b2spirit &= b2energy > 0;
			
			if (forcefield)
			{
				energy -= ecshield;
				if (ticksExisted%8==0)	RivalRebelsSoundPlayer.playSound(this, 5, 0, 10f, 1f);
			}
			if (laser) energy -= eclaser;
			if (jet || b2spirit)
			{
				if (b2energy > 0)
				{
					b2energy -= ecjet;
					if (b2energy <= 0)
					{
						b2energy = 0;
						worldObj.createExplosion((Entity) null, this.posX, this.posY, this.posZ, 6.0F, true);
						worldObj.spawnEntityInWorld(new EntityB2Frag(worldObj, this, 0));
						worldObj.spawnEntityInWorld(new EntityB2Frag(worldObj, this, 1));
					}
				}
				else
				{
					energy -= ecjet;
				}
				flying = 3;
			}
			else
			{
				flying = 0;
			}
			laserOn = 0;
			if (!stop)
			{
				float goal = ((((rider.rotationYawHead+bodyyaw)%360)+360+360)%360)-180;
				bodyyaw += Math.max(Math.min(goal, 2), -2);
				if (flying > 0)
				{
					if (b2spirit)
					{
						motionX += syaw * 0.1f;
						motionZ += cyaw * 0.1f;
						double speed = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
						if (speed > 0.7)
						{
							motionX /= speed;
							motionZ /= speed;
							motionX *= 0.7f;
							motionZ *= 0.7f;
						}
					}
					else
					{
						motionX = syaw * 0.5f;
						motionZ = cyaw * 0.5f;
					}
					rightthighpitch = approach(rightthighpitch,-30);
					leftthighpitch  = approach(leftthighpitch, -30);
					rightshinpitch  = approach(rightshinpitch, 60);
					leftshinpitch   = approach(leftshinpitch,  60);
				}
				else
				{
					doWalkingAnimation(syaw, cyaw);
				}
			}
			else
			{
				flying = 0;
				rightthighpitch = approach(rightthighpitch,0);
				leftthighpitch  = approach(leftthighpitch, 0);
				rightshinpitch  = approach(rightshinpitch, 0);
				leftshinpitch   = approach(leftshinpitch,  0);
			}
			double startx = rider.posX;
			double starty = rider.posY + rider.getEyeHeight();
			double startz = rider.posZ;
			double range = 100;
			double endx = startx + range * (-MathHelper.sin(rider.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rider.rotationPitch / 180.0F * (float) Math.PI));
			double endy = starty + range * (-MathHelper.sin(rider.rotationPitch / 180.0F * (float) Math.PI));
			double endz = startz + range * (MathHelper.cos(rider.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rider.rotationPitch / 180.0F * (float) Math.PI));
			
			Vec3 hit = rayTraceBlocks((float)startx, (float)starty, (float)startz, (float)endx, (float)endy, (float)endz);
			
			if (hit != null)
			{
				endx = hit.xCoord;
				endy = hit.yCoord;
				endz = hit.zCoord;
			}
			/*if (b2spirit && tickssincenuke >= 40 && nukecount > 0 && health > 2000)
 			{
				tickssincenuke = 0;
				nukecount--;
				health -= 1000;
				worldObj.spawnEntityInWorld(new EntityB2Spirit(worldObj, endx, endy, endz, posX, posY, posZ, null, false, false));
 			}*/
			
			if (laser)
			{
				laserOn = (byte)(worldObj.rand.nextInt(2)+1);
				RivalRebelsSoundPlayer.playSound(this, 22, 1, 30f, 0f);
				float x = (float) (posX - endx);
				float y = (float) (posY + 13*scale - endy);
				float z = (float) (posZ - endz);
				float pitch = (float) (720f-atan2((float)Math.sqrt(x*x+z*z)*(syaw*x+cyaw*z>0?-1f:1f), y))%360-270;
				
				headpitch += Math.max(-20, Math.min(20, (pitch-headpitch)));
				
				if (Math.abs(headpitch-pitch) < 10f && ticksExisted % 3 == 0)
				{
					range = 70*scale;
					Vec3 start = Vec3.createVectorHelper(posX, posY+13*scale, posZ);
					Vec3 end = Vec3.createVectorHelper(0, 0, range);
					end.rotateAroundX(-headpitch / 180.0F * (float) Math.PI);
					end.rotateAroundY(bodyyaw / 180.0F * (float) Math.PI);
					end = end.addVector(posX, posY+13*scale, posZ);
					Iterator iter = worldObj.getEntitiesWithinAABBExcludingEntity(this,AxisAlignedBB.getBoundingBox(Math.min(start.xCoord,end.xCoord)-5,
																													Math.min(start.yCoord,end.yCoord)-5,
																													Math.min(start.zCoord,end.zCoord)-5,
																													Math.max(start.xCoord,end.xCoord)+5,
																													Math.max(start.yCoord,end.yCoord)+5,
																													Math.max(start.zCoord,end.zCoord)+5)).iterator();
					while(iter.hasNext())
					{
						Entity e = (Entity) iter.next();
						if (!e.isDead && (!(e instanceof EntityLivingBase) || ((EntityLivingBase)e).getHealth()>0) &&
							!( e instanceof EntityThrowable
							|| e instanceof EntityInanimate
							|| e instanceof EntityItem
							|| e instanceof EntityBoat
							|| e instanceof EntityMinecart)
							&& e != rider)
						{
							Vec3 entity = Vec3.createVectorHelper(e.posX, e.posY, e.posZ);
							double bbx = 1;
							if (e instanceof EntityRhodes) bbx = 20 * ((EntityRhodes)e).scale;
							if (entity.subtract(start).crossProduct(entity.subtract(end)).squareDistanceTo(0, 0, 0) < 10000 * bbx)
							{
								e.attackEntityFrom(RivalRebelsDamageSource.laserburst, 24);
								if (e instanceof EntityPlayer)
								{
									EntityPlayer player = (EntityPlayer) e;
									ItemStack armorSlots[] = player.inventory.armorInventory;
									int i = worldObj.rand.nextInt(4);
									if (armorSlots[i] != null)
									{
										armorSlots[i].damageItem(24, player);
									}
									if (player.getHealth() < 3 && player.isEntityAlive())
									{
										player.attackEntityFrom(RivalRebelsDamageSource.laserburst, 2000000);
										player.deathTime = 0;
										worldObj.spawnEntityInWorld(new EntityGore(worldObj, e, 0, 0));
										worldObj.spawnEntityInWorld(new EntityGore(worldObj, e, 1, 0));
										worldObj.spawnEntityInWorld(new EntityGore(worldObj, e, 2, 0));
										worldObj.spawnEntityInWorld(new EntityGore(worldObj, e, 2, 0));
										worldObj.spawnEntityInWorld(new EntityGore(worldObj, e, 3, 0));
										worldObj.spawnEntityInWorld(new EntityGore(worldObj, e, 3, 0));
									}
								}
								else
								{
									if (e.isDead || (e instanceof EntityLivingBase && ((EntityLivingBase)e).getHealth() < 3))
									{
										int legs = -1;
										int arms = -1;
										int mobs = -1;
										RivalRebelsSoundPlayer.playSound(this, 2, 1, 4);
										if (e instanceof EntityPigZombie)
										{
											legs = 2;
											arms = 2;
											mobs = 2;
										}
										else if (e instanceof EntityZombie)
										{
											legs = 2;
											arms = 2;
											mobs = 1;
										}
										else if (e instanceof EntitySkeleton)
										{
											legs = 2;
											arms = 2;
											mobs = 3;
										}
										else if (e instanceof EntityEnderman)
										{
											legs = 2;
											arms = 2;
											mobs = 4;
										}
										else if (e instanceof EntityCreeper)
										{
											legs = 4;
											arms = 0;
											mobs = 5;
										}
										else if (e instanceof EntityMagmaCube)
										{
											legs = 0;
											arms = 0;
											mobs = 7;
										}
										else if (e instanceof EntitySlime)
										{
											legs = 0;
											arms = 0;
											mobs = 6;
										}
										else if (e instanceof EntityCaveSpider)
										{
											legs = 8;
											arms = 0;
											mobs = 9;
										}
										else if (e instanceof EntitySpider)
										{
											legs = 8;
											arms = 0;
											mobs = 8;
										}
										else if (e instanceof EntityGhast)
										{
											legs = 9;
											arms = 0;
											mobs = 10;
										}
										else if (e instanceof EntityB2Spirit
										      || e instanceof EntityRhodes
										      || e instanceof EntityRhodesPiece)
										{
											return;
										}
										else
										{
											legs = (int) (e.boundingBox.getAverageEdgeLength() * 2);
											arms = (int) (e.boundingBox.getAverageEdgeLength() * 2);
											mobs = 11;
										}
										worldObj.spawnEntityInWorld(new EntityGore(worldObj, e, 0, mobs));
										worldObj.spawnEntityInWorld(new EntityGore(worldObj, e, 1, mobs));
										for (int i = 0; i < arms; i++)
											worldObj.spawnEntityInWorld(new EntityGore(worldObj, e, 2, mobs));
										for (int i = 0; i < legs; i++)
											worldObj.spawnEntityInWorld(new EntityGore(worldObj, e, 3, mobs));
										e.setDead();
									}
								}
							}
						}
					}
				}
			}
			else
			{
				headpitch = rider.rotationPitch;
			}
			
			if (flame || prevflame)
			{
				float px = (float)posX-cyaw*6.4f*scale;
				float py = (float)posY+6.26759f*scale;
				float pz = (float)posZ+syaw*6.4f*scale;
				float x = px - (float)endx;
				float y = py - (float)endy;
				float z = pz - (float)endz;
				float yaw = ((atan2(x, z)-bodyyaw+810)%360)-270;
				float pitch = -(atan2((float)Math.sqrt(x*x+z*z), y));
				rightarmyaw += Math.max(-3, Math.min(3, (yaw-rightarmyaw)));
				rightarmpitch += Math.max(-3, Math.min(3, (pitch-rightarmpitch)));
				
				if (plasma)
				{
					plasmacharge++;
					if (Math.abs(rightarmyaw-yaw) < 3f && Math.abs(rightarmpitch-pitch) < 3f)
					{
						if (!flame && prevflame)
						{
							float f = plasmacharge / 20.0F;
							f = (f * f + f * 2) * 0.3333f;
							if (f > 1.0F) f = 1.0F;
							f+=0.7f;
							RivalRebelsSoundPlayer.playSound(this, 16, 2, 1, 0.5f);
							float cp = -f/(float)Math.sqrt(x*x+y*y+z*z)*scale;
							x*=cp;
							y*=cp;
							z*=cp;
							worldObj.spawnEntityInWorld(new EntityPlasmoid(worldObj, px, py, pz, 
									x, y, z, 8));
							flamecount -= plasmacharge;
							plasmacharge = 0;
						}
					}
					if (flame && !prevflame)
					{
						RivalRebelsSoundPlayer.playSound(this, 16, 1, 0.0f);
					}
				}
				else
				{
					if (Math.abs(rightarmyaw-yaw) < 3f && Math.abs(rightarmpitch-pitch) < 3f)
					{
						flamecount--;
						RivalRebelsSoundPlayer.playSound(this, 8, 1, 1f);
						float cp = -1f/(float)Math.sqrt(x*x+y*y+z*z);
						x*=cp;
						y*=cp;
						z*=cp;
						worldObj.spawnEntityInWorld(new EntityFlameBall(worldObj, px, py, pz, 
								x, y, z, (8+Math.random()*8)*scale, 0.4f));
						worldObj.spawnEntityInWorld(new EntityFlameBall(worldObj, px, py, pz, 
								x, y, z, (8+Math.random()*8)*scale, 0.4f));
					}
				}
			}
			tickssincenuke++;
			if (rocket || bomb)
			{
				float px = (float)posX+cyaw*6.4f*scale;
				float py = (float)posY+6.26759f*scale;
				float pz = (float)posZ-syaw*6.4f*scale;
				float x = px - (float)endx;
				float y = py - (float)endy;
				float z = pz - (float)endz;
				float yaw = ((atan2(x, z)-bodyyaw+630)%360)-90;
				float pitch = -(atan2((float)Math.sqrt(x*x+z*z), (float)y));
				leftarmyaw += Math.max(-3, Math.min(3, (yaw-leftarmyaw)));
				leftarmpitch += Math.max(-3, Math.min(3, (pitch-leftarmpitch)));
				
				if (Math.abs(leftarmyaw-yaw) < 3f && Math.abs(leftarmpitch-pitch) < 3f)
				{
					if (bomb && !rocket)
					{
						if (tickssincenuke >= 10)
						{
							tickssincenuke = 0;
							nukecount--;
							RivalRebelsSoundPlayer.playSound(this, 23, 10, 1f);
							float cp = -0.5f/(float)Math.sqrt(x*x+y*y+z*z);
							if (scale >= 3.0)
								worldObj.spawnEntityInWorld(new EntityHotPotato(worldObj, px, py, pz, 
										x*cp*5.0f, y*cp*5.0f, z*cp*5.0f));
							else if (scale >= 2.0)
								worldObj.spawnEntityInWorld(new EntityTsar(worldObj, px, py, pz, 
										x*cp*5.0f, y*cp*5.0f, z*cp*5.0f));
							else
								worldObj.spawnEntityInWorld(new EntityB83NoShroom(worldObj, px, py, pz, 
										x*cp, y*cp, z*cp));
						}
					}
					
					if (rocket && ticksExisted-lastshot > ((scale >= 2.0)?30:((shotstaken == 21)?80:5)))
					{
						rocketcount--;
						lastshot = ticksExisted;
						if (shotstaken == 21) shotstaken = 0;
						shotstaken++;
						RivalRebelsSoundPlayer.playSound(this, 23, 10, 1f);
						float cp = -0.5f/(float)Math.sqrt(x*x+y*y+z*z);
						
						if (scale >= 2.0)
							worldObj.spawnEntityInWorld(new EntityB83NoShroom(worldObj, px, py, pz, 
									x*cp, y*cp, z*cp));
						else
							worldObj.spawnEntityInWorld(new EntitySeekB83(worldObj, px, py, pz, 
									x*cp, y*cp, z*cp));
					}
				}
			}
			ac = 11;
			return;
		}
		
		if (RivalRebels.rhodesHold || guard)
		{
			rightthighpitch = approach(rightthighpitch,0);
			leftthighpitch  = approach(leftthighpitch, 0);
			rightshinpitch  = approach(rightshinpitch, 0);
			leftshinpitch   = approach(leftshinpitch,  0);
			if (RivalRebels.rhodesAI)
			{
				shootRocketsAtBestTarget(-syaw, cyaw);
				shootFlameAtBestTarget(-syaw, cyaw);
				shootLaserAtBestTarget(-syaw, cyaw);
			}
			return;
		}
		
		if (!RivalRebels.rhodesAI && ac != 0 && ac != 1 && ac != 11) return;
		
		if (counter == 0)
		{
			if (!actionqueue.isEmpty())
			{
				RhodesAction nextaction = actionqueue.remove();
				ac = nextaction.action;
				counter = nextaction.duration;
			}
			else ac = 2;
		}

		float movescale = scale;
		if (RivalRebels.rhodesScaleSpeed) movescale *= RivalRebels.rhodesSpeedScale;
		else movescale = RivalRebels.rhodesSpeedScale;
		switch (ac)
		{
		case 0: //Spawned
			if (damageUntilWake < 100)
			{
				RivalRebelsSoundPlayer.playSound(this, 13, 0, 900f, 1f);
				actionqueue.add(new RhodesAction(1,1));
			} else counter++;
			rightthighpitch = approach(rightthighpitch,0);
			leftthighpitch  = approach(leftthighpitch, 0);
			rightshinpitch  = approach(rightshinpitch, 0);
			leftshinpitch   = approach(leftshinpitch,  0);
			rightarmpitch = approach(rightarmpitch,0);
			leftarmpitch  = approach(leftarmpitch, 0);
			rightarmyaw  = approach(rightarmyaw,   0);
			leftarmyaw   = approach(leftarmyaw,    0);
			headpitch   = approach(headpitch, 0);
			break;
			
		case 1: //Ready to engage
			if (damageUntilWake <= 0)
			{
				RivalRebelsSoundPlayer.playSound(this, 12, 1, 90f, 1f);
				actionqueue.add(new RhodesAction(2,1));
			} else counter++;
			rightthighpitch = approach(rightthighpitch,0);
			leftthighpitch  = approach(leftthighpitch, 0);
			rightshinpitch  = approach(rightshinpitch, 0);
			leftshinpitch   = approach(leftshinpitch,  0);
			rightarmpitch = approach(rightarmpitch,0);
			leftarmpitch  = approach(leftarmpitch, 0);
			rightarmyaw  = approach(rightarmyaw,   0);
			leftarmyaw   = approach(leftarmyaw,    0);
			headpitch   = approach(headpitch, 0);
			break;
			
		case 2: //Idle
			if (raidedOmegaAlready == raidedSigmaAlready) raidedSigmaAlready = raidedOmegaAlready = false;
			if (endangered && RivalRebels.round.isStarted())
			{
				if (teamToRaid == 0)
				{
					if (raidedOmegaAlready && RivalRebels.round.sigmaHealth > 0) teamToRaid = 2;
					else if (raidedSigmaAlready && RivalRebels.round.omegaHealth > 0) teamToRaid = 1;
					else if (RivalRebels.round.omegaHealth > 0 && RivalRebels.round.sigmaHealth > 0)
					{
						RivalRebelsPlayer[] rrps = RivalRebels.round.rrplayerlist.getArray();
						int o = 0;
						int s = 0;
						for (int i = 0; i < rrps.length; i++)
						{
							if (rrps[i] != null && rrps[i].rrteam==RivalRebelsTeam.OMEGA) o++;
							else if (rrps[i] != null && rrps[i].rrteam==RivalRebelsTeam.SIGMA) s++;
						}
						if (o>s)
						{
							teamToRaid = 1;
							raidedOmegaAlready = true;
						}
						if (o<s)
						{
							teamToRaid = 2;
							raidedSigmaAlready = true;
						}
						if (o==s)
						{
							teamToRaid = rand.nextBoolean()?2:1;
							if (teamToRaid == 1) raidedOmegaAlready = true;
							else raidedSigmaAlready = true;
						}
					}
				}
				if (teamToRaid != 0)
				{
					float dx = (float) ((teamToRaid==1?RivalRebels.round.oObjx:RivalRebels.round.sObjx)-posX);
					float dz = (float) ((teamToRaid==1?RivalRebels.round.oObjz:RivalRebels.round.sObjz)-posZ);
					float angle = ((atan2(dx, dz) - bodyyaw)%360);
					if (angle > 1f)
					{
						actionqueue.add(new RhodesAction(5,(int)Math.abs(angle)));
					}
					else if (angle < -1f)
					{
						actionqueue.add(new RhodesAction(7,(int)Math.abs(angle)));
					}
					else
					{
						float d = Math.abs(dx)+Math.abs(dz);
						if (d<5)
						{
							RivalRebelsSoundPlayer.playSound(this, 13, 0, 900f, 1f);
							actionqueue.add(new RhodesAction(10,1));
						}
						else
						{
							if (d > 50 && rand.nextBoolean())
							{
								actionqueue.add(new RhodesAction(9,100));
								flying = 50;
							}
							else actionqueue.add(new RhodesAction(3,20));
						}
					}
					actionqueue.add(new RhodesAction(2,1));
				}
			}
			else
			{
				Entity t = findTarget();
				if (t != null)
				{
					float dx = (float) (t.posX-posX);
					float dz = (float) (t.posZ-posZ);
					float angle = ((atan2(dx, dz) - bodyyaw)%360);
					if (angle > 1 && rand.nextBoolean())
					{
						actionqueue.add(new RhodesAction(5,(int)Math.abs(angle)));
					}
					else if (angle < -1 && rand.nextBoolean())
					{
						actionqueue.add(new RhodesAction(7,(int)Math.abs(angle)));
					}
					else
					{
						if (rand.nextInt(20)<(endangered?2:1))
						{
							actionqueue.add(new RhodesAction(9,100));
							flying = 50;
						}
						else actionqueue.add(new RhodesAction(3,60));
					}
					actionqueue.add(new RhodesAction(2,1));
				}
				else
				{
					rightthighpitch = approach(rightthighpitch,0);
					leftthighpitch  = approach(leftthighpitch, 0);
					rightshinpitch  = approach(rightshinpitch, 0);
					leftshinpitch   = approach(leftshinpitch,  0);
					rightarmpitch = approach(rightarmpitch,0);
					leftarmpitch  = approach(leftarmpitch, 0);
					rightarmyaw  = approach(rightarmyaw,   0);
					leftarmyaw   = approach(leftarmyaw,    0);
					headpitch   = approach(headpitch, 0);
					counter++;
				}
			}
			
			break;
			
		case 3:
			shootRocketsAtBestTarget(-syaw, cyaw);
			shootFlameAtBestTarget(-syaw, cyaw);
			shootLaserAtBestTarget(-syaw, cyaw);
		case 4:
			doWalkingAnimation(syaw, cyaw);
			break;
			
		case 5:
			shootRocketsAtBestTarget(-syaw, cyaw);
			shootFlameAtBestTarget(-syaw, cyaw);
			shootLaserAtBestTarget(-syaw, cyaw);
		case 6:
			bodyyaw += 1.5f * movescale;
			doWalkingAnimation(syaw, cyaw);
			break;
			
		case 7:
			shootRocketsAtBestTarget(-syaw, cyaw);
			shootFlameAtBestTarget(-syaw, cyaw);
			shootLaserAtBestTarget(-syaw, cyaw);
		case 8:
			bodyyaw -= 1.5f * movescale;
			doWalkingAnimation(syaw, cyaw);
			break;
			
		case 9:
			shootRocketsAtBestTarget(-syaw, cyaw);
			shootFlameAtBestTarget(-syaw, cyaw);
			shootLaserAtBestTarget(-syaw, cyaw);
			motionX = syaw * 0.5f * movescale;
			motionZ = cyaw * 0.5f * movescale;
			rightthighpitch = approach(rightthighpitch,-30);
			leftthighpitch  = approach(leftthighpitch, -30);
			rightshinpitch  = approach(rightshinpitch, 60);
			leftshinpitch   = approach(leftshinpitch,  60);
			break;
		case 10:
			if (teamToRaid == 1 ? 
			(RivalRebels.round.omegaHealth > 0 && worldObj.getBlock(RivalRebels.round.oObjx, RivalRebels.round.oObjy, RivalRebels.round.oObjz) == RivalRebels.omegaobj)
			: (teamToRaid == 2 ?
			(RivalRebels.round.sigmaHealth > 0 && worldObj.getBlock(RivalRebels.round.sObjx, RivalRebels.round.sObjy, RivalRebels.round.sObjz) == RivalRebels.sigmaobj) : false))
			{
				rightthighpitch = approach(rightthighpitch,0);
				leftthighpitch  = approach(leftthighpitch, 0);
				rightshinpitch  = approach(rightshinpitch, 0);
				leftshinpitch   = approach(leftshinpitch,  0);
				rightarmpitch = approach(rightarmpitch,0);
				leftarmpitch  = approach(leftarmpitch, 0);
				rightarmyaw  = approach(rightarmyaw,   0);
				leftarmyaw   = approach(leftarmyaw,    0);
				headpitch   = approach(headpitch, 0);
				laserOn = 0;
				if (teamToRaid == 1)
				{
					health += RivalRebels.round.takeOmegaHealth(Math.min(50, RivalRebels.rhodesHealth-health));
				}
				if (teamToRaid == 2)
				{
					health += RivalRebels.round.takeSigmaHealth(Math.min(50, RivalRebels.rhodesHealth-health));
				}
				if (health != RivalRebels.rhodesHealth) counter++;
				else
				{
					endangered = false;
					teamToRaid = 0;
					actionqueue.add(new RhodesAction(2,1));
				}
			}
			else
			{
				health = 0;
				RivalRebelsSoundPlayer.playSound(this, 0, 0, 30, 1);
			}
			break;
		case 11:
			ac = 1;
			counter = 2;
			break;
		}
		counter--;
	}
	
	public static float atan2(float y, float x)
	{
		float dx = ((float)Math.sqrt(x*x+y*y)+x);
		if (y > dx)
		{
			float r = dx/y;
			return 180-(110.8653352702f-20.8654f*r*r)*r;
		}
		else if (y > -dx)
		{
			float r = y/dx;
			return (110.8653352702f-20.8654f*r*r)*r;
		}
		else
		{
			float r = dx/y;
			return -180-(110.8653352702f-20.8654f*r*r)*r;
		}
	}
	
	private Entity findTarget()
	{
		Iterator iter = worldObj.loadedEntityList.iterator();
		Entity target = null;
		double priority = 0;
		while(iter.hasNext())
		{
			Entity e = (Entity) iter.next();
			if (e != this && !e.isDead && (!(e instanceof EntityLivingBase) || ((EntityLivingBase)e).getHealth()>0) && (!(e instanceof EntityRhodes) || (RivalRebels.rhodesFF && (RivalRebels.rhodesCC || ((EntityRhodes)e).colorType != colorType))) &&
				!( e instanceof EntityThrowable
				|| e instanceof EntityInanimate
				|| e instanceof EntityItem
				|| e instanceof EntityAnimal
				|| e instanceof EntityVillager
				|| e instanceof EntityBat
				|| e instanceof EntitySquid
				|| e instanceof EntityBoat
				|| e instanceof EntityMinecart))
			{
				double prio = getPriority(e)-getDistanceToEntity(e);
				if (prio > priority)
				{
					target = e;
					priority = prio;
				}
			}
		}
		return target;
	}

	private int walkstate = 0;
	private void doWalkingAnimation(float syaw, float cyaw)
	{
		float movescale = scale;
		if (RivalRebels.rhodesScaleSpeed) movescale *= RivalRebels.rhodesSpeedScale;
		else movescale = RivalRebels.rhodesSpeedScale;
		motionX = syaw * 0.125f*movescale;
		motionZ = cyaw * 0.125f*movescale;
		switch (walkstate)
		{
		case 0: {
			int rtg = -60;
			int ltg = 0;
			int rsg = 60;
			int lsg = 30;
			rightthighpitch = approach(rightthighpitch,rtg);
			leftthighpitch  = approach(leftthighpitch, ltg);
			rightshinpitch  = approach(rightshinpitch, rsg);
			leftshinpitch   = approach(leftshinpitch,  lsg);
			if((int)(rightthighpitch) == rtg
			&& (int)(leftthighpitch ) == ltg
			&& (int)(rightshinpitch ) == rsg
			&& (int)(leftshinpitch  ) == lsg) walkstate++;
			break; }
		case 1: {
			int rtg = -60;
			int ltg = 0;
			int rsg = 60;
			int lsg = 60;
			rightthighpitch = approach(rightthighpitch,rtg);
			leftthighpitch  = approach(leftthighpitch, ltg);
			rightshinpitch  = approach(rightshinpitch, rsg);
			leftshinpitch   = approach(leftshinpitch,  lsg);
			if((int)(rightthighpitch) == rtg
			&& (int)(leftthighpitch ) == ltg
			&& (int)(rightshinpitch ) == rsg
			&& (int)(leftshinpitch  ) == lsg)
			{
				walkstate++;
				RivalRebelsSoundPlayer.playSound(this, 0, 2, 4.5f, 0.8f);
			}
			break; }
		case 2: {
			int rtg = -30;
			int ltg = 0;
			int rsg = 30;
			int lsg = 90;
			rightthighpitch = approach(rightthighpitch,rtg);
			leftthighpitch  = approach(leftthighpitch, ltg);
			rightshinpitch  = approach(rightshinpitch, rsg);
			leftshinpitch   = approach(leftshinpitch,  lsg);
			if((int)(rightthighpitch) == rtg
			&& (int)(leftthighpitch ) == ltg
			&& (int)(rightshinpitch ) == rsg
			&& (int)(leftshinpitch  ) == lsg) walkstate++;
			break; }
		case 3: {
			int rtg = 0;
			int ltg = -30;
			int rsg = 00;
			int lsg = 90;
			rightthighpitch = approach(rightthighpitch,rtg);
			leftthighpitch  = approach(leftthighpitch, ltg);
			rightshinpitch  = approach(rightshinpitch, rsg);
			leftshinpitch   = approach(leftshinpitch,  lsg);
			if((int)(rightthighpitch) == rtg
			&& (int)(leftthighpitch ) == ltg
			&& (int)(rightshinpitch ) == rsg
			&& (int)(leftshinpitch  ) == lsg) walkstate++;
			break; }
		case 4: {
			int rtg = 0;
			int ltg = -60;
			int rsg = 30;
			int lsg = 60;
			rightthighpitch = approach(rightthighpitch,rtg);
			leftthighpitch  = approach(leftthighpitch, ltg);
			rightshinpitch  = approach(rightshinpitch, rsg);
			leftshinpitch   = approach(leftshinpitch,  lsg);
			if((int)(rightthighpitch) == rtg
			&& (int)(leftthighpitch ) == ltg
			&& (int)(rightshinpitch ) == rsg
			&& (int)(leftshinpitch  ) == lsg) walkstate++;
			break; }
		case 5: {
			int rtg = 0;
			int ltg = -60;
			int rsg = 60;
			int lsg = 60;
			rightthighpitch = approach(rightthighpitch,rtg);
			leftthighpitch  = approach(leftthighpitch, ltg);
			rightshinpitch  = approach(rightshinpitch, rsg);
			leftshinpitch   = approach(leftshinpitch,  lsg);
			if((int)(rightthighpitch) == rtg
			&& (int)(leftthighpitch ) == ltg
			&& (int)(rightshinpitch ) == rsg
			&& (int)(leftshinpitch  ) == lsg)
			{
				walkstate++;
				RivalRebelsSoundPlayer.playSound(this, 0, 2, 4.5f, 0.8f);
			}
			break; }
		case 6: {
			int rtg = 0;
			int ltg = -30;
			int rsg = 90;
			int lsg = 30;
			rightthighpitch = approach(rightthighpitch,rtg);
			leftthighpitch  = approach(leftthighpitch, ltg);
			rightshinpitch  = approach(rightshinpitch, rsg);
			leftshinpitch   = approach(leftshinpitch,  lsg);
			if((int)(rightthighpitch) == rtg
			&& (int)(leftthighpitch ) == ltg
			&& (int)(rightshinpitch ) == rsg
			&& (int)(leftshinpitch  ) == lsg) walkstate++;
			break; }
		case 7: {
			int rtg = -30;
			int ltg = 0;
			int rsg = 90;
			int lsg = 00;
			rightthighpitch = approach(rightthighpitch,rtg);
			leftthighpitch  = approach(leftthighpitch, ltg);
			rightshinpitch  = approach(rightshinpitch, rsg);
			leftshinpitch   = approach(leftshinpitch,  lsg);
			if((int)(rightthighpitch) == rtg
			&& (int)(leftthighpitch ) == ltg
			&& (int)(rightshinpitch ) == rsg
			&& (int)(leftshinpitch  ) == lsg) walkstate++;
			break; }
		case 8: {
			int rtg = -60;
			int ltg = 0;
			int rsg = 60;
			int lsg = 30;
			rightthighpitch = approach(rightthighpitch,rtg);
			leftthighpitch  = approach(leftthighpitch, ltg);
			rightshinpitch  = approach(rightshinpitch, rsg);
			leftshinpitch   = approach(leftshinpitch,  lsg);
			if((int)(rightthighpitch) == rtg
			&& (int)(leftthighpitch ) == ltg
			&& (int)(rightshinpitch ) == rsg
			&& (int)(leftshinpitch  ) == lsg) walkstate=0;
			break; }
		}
	}
	
	private float approach(float f, float t)
	{
		float r = f-t;
		if (r>1)r=1;
		if (r<-1)r=-1;
		return f-r;
	}

	private Entity lastRocketTarget = null;
	private int lastshot;
	private int shotstaken;
	private int lastrockettargetting = 0;
	boolean nuke = false;
	private void shootRocketsAtBestTarget(float syaw, float cyaw)
	{
		if (rocketcount < 0) return;
		float px = (float)posX+cyaw*6.4f*scale;
		float py = (float)posY+6.26759f*scale;
		float pz = (float)posZ+syaw*6.4f*scale;
		TileEntity te = null;
		lastrockettargetting--;
		if (lastrockettargetting<0||lastRocketTarget==null||lastRocketTarget.isDead || (lastRocketTarget instanceof EntityLivingBase && ((EntityLivingBase)lastRocketTarget).getHealth()<=0))
		{
			lastrockettargetting = 10;
			float priority = 0;
			if (lastRocketTarget != null && !lastRocketTarget.isDead)
			{
				float dx = ((float)lastRocketTarget.posX-px);
				float dz = ((float)lastRocketTarget.posZ-pz);
				float dot = (cyaw * dx + syaw * dz);
				if (dot*Math.abs(dot) > -0.25 * (dx*dx+dz*dz))
				{
					float dy = ((float)lastRocketTarget.posY-(float)posY-6.2f*scale);
					float dist = dx*dx+dy*dy+dz*dz;
					if (dist < 100*100*scale*scale && rayTraceBlocks(px, py, pz, (float)lastRocketTarget.posX, (float)lastRocketTarget.posY+lastRocketTarget.height/2f, (float)lastRocketTarget.posZ) == null)
					{
						priority = getPriority(lastRocketTarget)-(float)Math.sqrt(dist)+10;
					}
				}
			}
			if (priority <= 0) lastRocketTarget = null;
			Iterator iter = worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getBoundingBox(px-100*scale, py-100*scale, pz-100*scale, px+100*scale, py+100*scale, pz+100*scale)).iterator();
			while(iter.hasNext())
			{
				Entity e = (Entity) iter.next();
				if (!e.isDead && (!(e instanceof EntityLivingBase) || ((EntityLivingBase)e).getHealth()>0) &&
					!( e instanceof EntityThrowable
					|| e instanceof EntityInanimate
					|| e instanceof EntityItem
					|| e instanceof EntityAnimal
					|| e instanceof EntityVillager
					|| e instanceof EntityBat
					|| e instanceof EntitySquid
					|| e instanceof EntityBoat
					|| e instanceof EntityMinecart))
				{
					float dx = (float)e.posX-px;
					float dz = (float)e.posZ-pz;
					float dot = (cyaw * dx + syaw * dz);
					if (dot*Math.abs(dot) > -0.25 * (dx*dx+dz*dz))
					{
						float dy = (float)e.posY-py;
						float dist = dx*dx+dy*dy+dz*dz;
						if (dist < 100*100*scale*scale)
						{
							float prio = getPriority(e)-(float)Math.sqrt(dist);
							if (prio > priority && rayTraceBlocks(px, py, pz, (float)e.posX, (float)e.posY+e.height/2f, (float)e.posZ) == null)
							{
								lastRocketTarget = e;
								priority = prio;
							}
						}
					}
				}
			}
			
			Object[] tel = worldObj.loadedTileEntityList.toArray();
			for (int teli = 0; teli < tel.length; teli++)
			{
				Object eo = tel[teli];
				if (eo instanceof TileEntityRhodesActivator || eo instanceof TileEntityNukeCrate || eo instanceof TileEntityReactor)
				{
					TileEntity e = (TileEntity) eo;
					float dx = (float)e.xCoord-px;
					float dz = (float)e.zCoord-pz;
					float dot = (cyaw * dx + syaw * dz);
					if (dot*Math.abs(dot) > -0.25 * (dx*dx+dz*dz))
					{
						float dy = (float)e.yCoord-py;
						float dist = dx*dx+dy*dy+dz*dz;
						if (dist < 100*100)
						{
							float prio = 300-(float)Math.sqrt(dist);
							if (prio > priority && rayTraceBlocks(px, py, pz, (float)e.xCoord, (float)e.yCoord, (float)e.zCoord) == null)
							{
								te = e;
								lastRocketTarget = null;
								priority = prio;
							}
						}
					}
				}
			}
		}
		if (te != null && !nuke)
		{
			float x = px - (float)te.xCoord;
			float y = py - (float)te.yCoord;
			float z = pz - (float)te.zCoord;
			float yaw = ((atan2(x, z)-bodyyaw+630)%360)-90;
			float pitch = -(atan2((float)Math.sqrt(x*x+z*z), (float)y));
			boolean pointing = true;
			if (Math.abs(leftarmyaw-yaw) >= 0.001f)
			{
				leftarmyaw += Math.max(-3, Math.min(3, (yaw-leftarmyaw)));
				if (Math.abs(leftarmyaw-yaw) < 3f) pointing &= true;
				else pointing = false;
			}
			if (Math.abs(leftarmpitch-pitch) >= 0.001f)
			{
				leftarmpitch += Math.max(-3, Math.min(3, (pitch-leftarmpitch)));
				if (Math.abs(leftarmpitch-pitch) < 3f) pointing &= true;
				else pointing = false;
			}
			
			if (pointing && ticksExisted-lastshot > ((scale >= 2.0)?30:((shotstaken == 21)?80:5)))
			{
				rocketcount--;
				lastshot = ticksExisted;
				if (shotstaken == 21) shotstaken = 0;
				shotstaken++;
				RivalRebelsSoundPlayer.playSound(this, 23, 10, 1f);
				float cp = -0.5f/(float)Math.sqrt(x*x+y*y+z*z);
				if (scale >= 2.0)
					worldObj.spawnEntityInWorld(new EntityB83NoShroom(worldObj, px, py, pz, 
							x*cp, y*cp, z*cp));
				else
					worldObj.spawnEntityInWorld(new EntitySeekB83(worldObj, px, py, pz, 
							x*cp, y*cp, z*cp));
			}
		}
		else if (lastRocketTarget != null && !lastRocketTarget.isDead)
		{
			float x = px - (float)lastRocketTarget.posX;
			float y = py - (float)lastRocketTarget.posY;
			float z = pz - (float)lastRocketTarget.posZ;
			float yaw = ((atan2(x, z)-bodyyaw+630)%360)-90;
			float pitch = -(atan2((float)Math.sqrt(x*x+z*z), (float)y));
			boolean pointing = true;
			if (Math.abs(leftarmyaw-yaw) >= 0.001f)
			{
				leftarmyaw += Math.max(-3, Math.min(3, (yaw-leftarmyaw)));
				if (Math.abs(leftarmyaw-yaw) < 3f) pointing &= true;
				else pointing = false;
			}
			if (Math.abs(leftarmpitch-pitch) >= 0.001f)
			{
				leftarmpitch += Math.max(-3, Math.min(3, (pitch-leftarmpitch)));
				if (Math.abs(leftarmpitch-pitch) < 3f) pointing &= true;
				else pointing = false;
			}

			if (lastRocketTarget instanceof EntityLivingBase && ((EntityLivingBase)lastRocketTarget).getMaxHealth() > 1000)
			{
				if (pointing && ticksExisted % 100 == 0)
				{
					RivalRebelsSoundPlayer.playSound(this, 23, 10, 1f);
					float cp = -0.5f/(float)Math.sqrt(x*x+y*y+z*z);
					if (scale >= 2.0)
						worldObj.spawnEntityInWorld(new EntityTsar(worldObj, px, py, pz, 
								x*cp*5.0f, y*cp*5.0f, z*cp*5.0f));
					else
						worldObj.spawnEntityInWorld(new EntityB83NoShroom(worldObj, px, py, pz, 
								x*cp, y*cp, z*cp));
				}
			}
			else
			{
				if (pointing && ticksExisted-lastshot > ((scale >= 2.0)?30:((shotstaken == 21)?80:5)))
				{
					rocketcount--;
					lastshot = ticksExisted;
					if (shotstaken == 21) shotstaken = 0;
					shotstaken++;
					RivalRebelsSoundPlayer.playSound(this, 23, 10, 1f);
					float cp = -0.5f/(float)Math.sqrt(x*x+y*y+z*z);
					if (scale >= 2.0)
						worldObj.spawnEntityInWorld(new EntityB83NoShroom(worldObj, px, py, pz, 
								x*cp, y*cp, z*cp));
					else
						worldObj.spawnEntityInWorld(new EntitySeekB83(worldObj, px, py, pz, 
								x*cp, y*cp, z*cp));
				}
			}
		}
	}
	
	private Entity lastFlameTarget = null;
	private int lastflametargetting = 0;
	private void shootFlameAtBestTarget(float syaw, float cyaw)
	{
		float px = (float)posX-cyaw*6.4f*scale;
		float py = (float)posY+6.26759f*scale;
		float pz = (float)posZ-syaw*6.4f*scale;
		lastflametargetting--;
		if (lastflametargetting<0||lastFlameTarget==null||lastFlameTarget.isDead || (lastFlameTarget instanceof EntityLivingBase && ((EntityLivingBase)lastFlameTarget).getHealth()<=0))
		{
			lastflametargetting = 10;
			float priority = 0;
			if (lastFlameTarget != null && !lastFlameTarget.isDead)
			{
				float dx = ((float)lastFlameTarget.posX-px);
				float dz = ((float)lastFlameTarget.posZ-pz);
				float dot = (-cyaw * dx + -syaw * dz);
				if (dot*Math.abs(dot) > -0.25 * (dx*dx+dz*dz))
				{
					float dy = ((float)lastFlameTarget.posY-(float)posY-6.2f);
					float dist = dx*dx+dy*dy+dz*dz;
					if (dist < 40*40*scale*scale && rayTraceBlocks(px, py, pz, (float)lastFlameTarget.posX, (float)lastFlameTarget.posY+lastFlameTarget.height, (float)lastFlameTarget.posZ) == null)
					{
						priority = getPriority(lastFlameTarget)-(float)Math.sqrt(dist)+10;
					}
				}
			}
			if (priority <= 0) lastFlameTarget = null;
			Iterator iter = worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getBoundingBox(px-40*scale, py-40*scale, pz-40*scale, px+40*scale, py+40*scale, pz+40*scale)).iterator();
			while(iter.hasNext())
			{
				Entity e = (Entity) iter.next();
				if (!e.isDead && (!(e instanceof EntityLivingBase) || ((EntityLivingBase)e).getHealth()>0) &&
					!( e instanceof EntityThrowable
					|| e instanceof EntityInanimate
					|| e instanceof EntityItem
					|| e instanceof EntityAnimal
					|| e instanceof EntityVillager
					|| e instanceof EntityBat
					|| e instanceof EntitySquid
					|| e instanceof EntityBoat
					|| e instanceof EntityMinecart))
				{
					float dx = (float)e.posX-px;
					float dz = (float)e.posZ-pz;
					float dot = (-cyaw * dx + -syaw * dz);
					if (dot*Math.abs(dot) > -0.25 * (dx*dx+dz*dz))
					{
						float dy = (float)e.posY-py;
						float dist = dx*dx+dy*dy+dz*dz;
						if (dist < 40*40*scale*scale)
						{
							float prio = getPriority(e)-(float)Math.sqrt(dist);
							if (prio > priority && rayTraceBlocks(px, py, pz, (float)e.posX, (float)e.posY+e.height, (float)e.posZ) == null)
							{
								lastFlameTarget = e;
								priority = prio;
							}
						}
					}
				}
			}
		}
		
		if (lastFlameTarget != null && !lastFlameTarget.isDead)
		{
			float x = px - (float)lastFlameTarget.posX;
			float y = py - (float)lastFlameTarget.posY - (lastFlameTarget.height*0.5f);
			float z = pz - (float)lastFlameTarget.posZ;
			float yaw = ((atan2(x, z)-bodyyaw+810)%360)-270;
			float pitch = -(atan2((float)Math.sqrt(x*x+z*z), y));
			boolean pointing = true;
			if (Math.abs(rightarmyaw-yaw) >= 0.001f)
			{
				rightarmyaw += Math.max(-3, Math.min(3, (yaw-rightarmyaw)));
				if (Math.abs(rightarmyaw-yaw) < 0.001f) pointing &= true;
				else pointing = false;
			}
			if (Math.abs(rightarmpitch-pitch) >= 0.001f)
			{
				rightarmpitch += Math.max(-3, Math.min(3, (pitch-rightarmpitch)));
				if (Math.abs(rightarmpitch-pitch) < 0.001f) pointing &= true;
				else pointing = false;
			}
			
			if (pointing)
			{
				RivalRebelsSoundPlayer.playSound(this, 8, 1, 1f);
				float cp = -1f/(float)Math.sqrt(x*x+y*y+z*z);
				x*=cp;
				y*=cp;
				z*=cp;
				worldObj.spawnEntityInWorld(new EntityFlameBall(worldObj, px, py, pz, 
						x, y, z, (8+Math.random()*8)*scale, 0.4f));
				worldObj.spawnEntityInWorld(new EntityFlameBall(worldObj, px, py, pz, 
						x, y, z, (8+Math.random()*8)*scale, 0.4f));
			}
		}
	}
	
	private Entity lastLaserTarget = null;
	private int lastlasertargetting = 0;
	private void shootLaserAtBestTarget(float syaw, float cyaw)
	{
		lastlasertargetting--;
		if (lastlasertargetting<0||lastLaserTarget==null||lastLaserTarget.isDead || (lastLaserTarget instanceof EntityLivingBase && ((EntityLivingBase)lastLaserTarget).getHealth()<=0))
		{
			lastlasertargetting=10;
			float priority = 0;
			if (lastLaserTarget != null && !lastLaserTarget.isDead)
			{
				float tempdi = (float) ((lastLaserTarget.posX-posX)*(lastLaserTarget.posX-posX)
						+(lastLaserTarget.posY-13-posY)*(lastLaserTarget.posY-13-posY)
						+(lastLaserTarget.posZ-posZ)*(lastLaserTarget.posZ-posZ));
				if (Math.abs(cyaw*(lastLaserTarget.posX-posX)+syaw*(lastLaserTarget.posZ-posZ))<2&&tempdi<70*70
						&&rayTraceBlocks((float)posX, (float)posY + 13, (float)posZ, (float)lastLaserTarget.posX, (float)lastLaserTarget.posY+lastLaserTarget.height/2f, (float)lastLaserTarget.posZ) == null)
				{
					priority = getPriority(lastLaserTarget)-(float)Math.sqrt(tempdi);
					if (priority > 0) priority += 10;
				}
			}
			if (priority <= 0) lastLaserTarget = null;

			Iterator iter = worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getBoundingBox(posX-70*scale, posY+13*scale-70*scale, posZ-70*scale, posX+70*scale, posY+13*scale+70*scale, posZ+70*scale)).iterator();
			while(iter.hasNext())
			{
				Entity e = (Entity) iter.next();
				if (!e.isDead && (!(e instanceof EntityLivingBase) || ((EntityLivingBase)e).getHealth()>0) &&
					!( e instanceof EntityThrowable
					|| e instanceof EntityInanimate
					|| e instanceof EntityItem
					|| e instanceof EntityAnimal
					|| e instanceof EntityVillager
					|| e instanceof EntityBat
					|| e instanceof EntitySquid
					|| e instanceof EntityBoat
					|| e instanceof EntityMinecart))
				{
					float x = (float) (e.posX-posX);
					float z = (float) (e.posZ-posZ);
					if (Math.abs(cyaw*x+syaw*z)<2)
					{
						float y = (float) (e.posY-13-posY);
						float dist = x*x+y*y+z*z;
						if (dist < 70*70*scale*scale)
						{
							if (y*Math.abs(y)>-0.64f*dist)
							{
								float prio = getPriority(e)-(float)Math.sqrt(dist);
								if (prio > priority && rayTraceBlocks((float)posX, (float)posY + 13, (float)posZ, (float)e.posX, (float)e.posY+e.height/2f, (float)e.posZ) == null)
								{
									lastLaserTarget = e;
									priority = prio;
								}
							}
						}
					}
				}
			}
		}
		
		laserOn = (byte) 0;
		if (lastLaserTarget != null && !lastLaserTarget.isDead)
		{
			float x = (float) (posX - lastLaserTarget.posX);
			float y = (float) (posY + 13 - lastLaserTarget.posY);
			float z = (float) (posZ - lastLaserTarget.posZ);
			float dot = -syaw*x+cyaw*z;
			float pitch = (float) (720f-atan2((float)Math.sqrt(x*x+z*z)*(dot>0?-1f:1f), y))%360-270;
			
			boolean pointing = true;
			if (Math.abs(headpitch-pitch) >= 0.001f)
			{
				headpitch += Math.max(-20, Math.min(20, (pitch-headpitch)));
				if (Math.abs(headpitch-pitch) < 3f) pointing = true;
				else pointing = false;
			}
			
			if (pointing)
			{
				laserOn = (byte) (endangered?3:rand.nextInt(2)+1);
				RivalRebelsSoundPlayer.playSound(this, 22, 1, 30f, 0f);
				if (lastLaserTarget instanceof EntityPlayer)
				{
					EntityPlayer player = (EntityPlayer) lastLaserTarget;
					ItemStack armorSlots[] = player.inventory.armorInventory;
					int i = worldObj.rand.nextInt(4);
					if (armorSlots[i] != null)
					{
						armorSlots[i].damageItem(24, player);
					}
					lastLaserTarget.attackEntityFrom(RivalRebelsDamageSource.laserburst, laserOn==3?16:8);
					if (player.getHealth() < 3 && player.isEntityAlive())
					{
						player.attackEntityFrom(RivalRebelsDamageSource.laserburst, 2000000);
						player.deathTime = 0;
						worldObj.spawnEntityInWorld(new EntityGore(worldObj, lastLaserTarget, 0, 0));
						worldObj.spawnEntityInWorld(new EntityGore(worldObj, lastLaserTarget, 1, 0));
						worldObj.spawnEntityInWorld(new EntityGore(worldObj, lastLaserTarget, 2, 0));
						worldObj.spawnEntityInWorld(new EntityGore(worldObj, lastLaserTarget, 2, 0));
						worldObj.spawnEntityInWorld(new EntityGore(worldObj, lastLaserTarget, 3, 0));
						worldObj.spawnEntityInWorld(new EntityGore(worldObj, lastLaserTarget, 3, 0));
					}
				}
				else
				{
					lastLaserTarget.attackEntityFrom(RivalRebelsDamageSource.laserburst, laserOn==3?16:8);
					if (lastLaserTarget.isDead || (lastLaserTarget instanceof EntityLivingBase && ((EntityLivingBase)lastLaserTarget).getHealth() < 3))
					{
						int legs = -1;
						int arms = -1;
						int mobs = -1;
						RivalRebelsSoundPlayer.playSound(this, 2, 1, 4);
						if (lastLaserTarget instanceof EntityPigZombie)
						{
							legs = 2;
							arms = 2;
							mobs = 2;
						}
						else if (lastLaserTarget instanceof EntityZombie)
						{
							legs = 2;
							arms = 2;
							mobs = 1;
						}
						else if (lastLaserTarget instanceof EntitySkeleton)
						{
							legs = 2;
							arms = 2;
							mobs = 3;
						}
						else if (lastLaserTarget instanceof EntityEnderman)
						{
							legs = 2;
							arms = 2;
							mobs = 4;
						}
						else if (lastLaserTarget instanceof EntityCreeper)
						{
							legs = 4;
							arms = 0;
							mobs = 5;
						}
						else if (lastLaserTarget instanceof EntityMagmaCube)
						{
							legs = 0;
							arms = 0;
							mobs = 7;
						}
						else if (lastLaserTarget instanceof EntitySlime)
						{
							legs = 0;
							arms = 0;
							mobs = 6;
						}
						else if (lastLaserTarget instanceof EntityCaveSpider)
						{
							legs = 8;
							arms = 0;
							mobs = 9;
						}
						else if (lastLaserTarget instanceof EntitySpider)
						{
							legs = 8;
							arms = 0;
							mobs = 8;
						}
						else if (lastLaserTarget instanceof EntityGhast)
						{
							legs = 9;
							arms = 0;
							mobs = 10;
						}
						else if (lastLaserTarget instanceof EntityB2Spirit
						      || lastLaserTarget instanceof EntityRhodes
						      || lastLaserTarget instanceof EntityRhodesPiece)
						{
							return;
						}
						else
						{
							legs = (int) (lastLaserTarget.boundingBox.getAverageEdgeLength() * 2);
							arms = (int) (lastLaserTarget.boundingBox.getAverageEdgeLength() * 2);
							mobs = 11;
						}
						worldObj.spawnEntityInWorld(new EntityGore(worldObj, lastLaserTarget, 0, mobs));
						worldObj.spawnEntityInWorld(new EntityGore(worldObj, lastLaserTarget, 1, mobs));
						for (int i = 0; i < arms; i++)
							worldObj.spawnEntityInWorld(new EntityGore(worldObj, lastLaserTarget, 2, mobs));
						for (int i = 0; i < legs; i++)
							worldObj.spawnEntityInWorld(new EntityGore(worldObj, lastLaserTarget, 3, mobs));
						lastLaserTarget.setDead();
					}
				}
			}
		}
	}
	
	//lower is less prior
	private float getPriority(Entity e)
	{
		if (e instanceof EntityPlayer) return  ((EntityPlayer) e).capabilities.isCreativeMode?-100:600;
		if (e instanceof EntityLivingBase) return ((EntityLivingBase)e).getMaxHealth()+100;
		if ((e instanceof EntityRhodes && (RivalRebels.rhodesFF && (RivalRebels.rhodesCC || ((EntityRhodes)e).colorType != colorType))) || e instanceof EntityB2Spirit) return 800;
		if (e.boundingBox.getAverageEdgeLength() > 3) return (float) (e.boundingBox.getAverageEdgeLength()*3 + 500 + e.height);
		return 0;
	}
	
	private Vec3 rayTraceBlocks(float sx, float sy, float sz, float ex, float ey, float ez)
	{
		int X = (int) sx;
		int Y = (int) sy;
		int Z = (int) sz;
		float dx = ex-sx;
		float dy = ey-sy;
		float dz = ez-sz;
		float distsq = (dx*dx+dy*dy+dz*dz);
		int stepX = dx>0?1:-1;
		int stepY = dy>0?1:-1;
		int stepZ = dz>0?1:-1;
		int x = X-stepX;
		int y = Y-stepY;
		int z = Z-stepZ;
		float tDeltaX = stepX/dx;
		float tDeltaY = stepY/dy;
		float tDeltaZ = stepZ/dz;
		float tMaxX = ((1+X)-sx)*tDeltaX;
		float tMaxY = ((1+Y)-sy)*tDeltaY;
		float tMaxZ = ((1+Z)-sz)*tDeltaZ;
		while((X-x)*(X-x)+(Y-y)*(Y-y)+(Z-z)*(Z-z) < distsq)
		{
			if (worldObj.getBlock(X, Y, Z).isOpaqueCube())
			{
				return Vec3.createVectorHelper(X, Y, Z);
			}
			if(tMaxX < tMaxY)
			{
				if(tMaxX < tMaxZ)
				{
					X += stepX;
					tMaxX += tDeltaX;
				}
				else
				{
					Z += stepZ;
					tMaxZ += tDeltaZ;
				}
			}
			else
			{
				if(tMaxY < tMaxZ)
				{
					Y += stepY;
					tMaxY += tDeltaY;
				}
				else
				{
					Z += stepZ;
					tMaxZ += tDeltaZ;
				}
			}
		}
		return null;
	}
	
	public float getbodyyaw(float f)
	{
		return lastbodyyaw + (bodyyaw - lastbodyyaw) * f;
	}
	
	public float getheadyaw(float f)
	{
		return lastheadyaw + (headyaw - lastheadyaw) * f;
	}

	public float getheadpitch(float f)
	{
		return lastheadpitch + (headpitch - lastheadpitch) * f;
	}

	public float getleftarmyaw(float f)
	{
		return lastleftarmyaw + (leftarmyaw - lastleftarmyaw) * f;
	}

	public float getleftarmpitch(float f)
	{
		return lastleftarmpitch + (leftarmpitch - lastleftarmpitch) * f;
	}

	public float getrightarmyaw(float f)
	{
		return lastrightarmyaw + (rightarmyaw - lastrightarmyaw) * f;
	}

	public float getrightarmpitch(float f)
	{
		return lastrightarmpitch + (rightarmpitch - lastrightarmpitch) * f;
	}

	public float getleftthighpitch(float f)
	{
		return lastleftthighpitch + (leftthighpitch - lastleftthighpitch) * f;
	}

	public float getrightthighpitch(float f)
	{
		return lastrightthighpitch + (rightthighpitch - lastrightthighpitch) * f;
	}

	public float getleftshinpitch(float f)
	{
		return lastleftshinpitch + (leftshinpitch - lastleftshinpitch) * f;
	}

	public float getrightshinpitch(float f)
	{
		return lastrightshinpitch + (rightshinpitch - lastrightshinpitch) * f;
	}
	
	public String getName()
	{
		String name = names[colorType];
		return name;
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		nbt.setFloat("bodyyaw", bodyyaw);
		nbt.setFloat("headyaw", headyaw);
		nbt.setFloat("headpitch", headpitch);
		nbt.setFloat("leftarmyaw", leftarmyaw);
		nbt.setFloat("leftarmpitch", leftarmpitch);
		nbt.setFloat("rightarmyaw", rightarmyaw);
		nbt.setFloat("rightarmpitch", rightarmpitch);
		nbt.setFloat("leftthighpitch", leftthighpitch);
		nbt.setFloat("rightthighpitch", rightthighpitch);
		nbt.setFloat("leftshinpitch", leftshinpitch);
		nbt.setFloat("rightshinpitch", rightshinpitch);
		nbt.setBoolean("endangered", endangered);
		nbt.setInteger("ac", ac);
		nbt.setInteger("counter", counter);
		nbt.setInteger("walkstate", walkstate);
		nbt.setInteger("health", health);
		nbt.setInteger("damageuntilwake", damageUntilWake);
		nbt.setByte("color", colorType);
		nbt.setInteger("rocketcount", rocketcount);
		nbt.setInteger("energy", energy);
		nbt.setInteger("b2energy", b2energy);
		nbt.setInteger("flamecount", flamecount);
		nbt.setInteger("nukecount", nukecount);
		nbt.setInteger("texfolder", itexfolder);
		nbt.setFloat("scale", scale);
		if (itexfolder != -1) nbt.setString("texloc", itexloc);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		bodyyaw = nbt.getFloat("bodyyaw");
		headyaw = nbt.getFloat("headyaw");
		headpitch = nbt.getFloat("headpitch");
		leftarmyaw = nbt.getFloat("leftarmyaw");
		leftarmpitch = nbt.getFloat("leftarmpitch");
		rightarmyaw = nbt.getFloat("rightarmyaw");
		rightarmpitch = nbt.getFloat("rightarmpitch");
		leftthighpitch = nbt.getFloat("leftthighpitch");
		rightthighpitch = nbt.getFloat("rightthighpitch");
		leftshinpitch = nbt.getFloat("leftshinpitch");
		rightshinpitch = nbt.getFloat("rightshinpitch");
		endangered = nbt.getBoolean("endangered");
		ac = nbt.getInteger("ac");
		counter = nbt.getInteger("counter");
		walkstate = nbt.getInteger("walkstate");
		health = nbt.getInteger("health");
		damageUntilWake = nbt.getInteger("damageuntilwake");
		colorType = nbt.getByte("color");
		rocketcount = nbt.getInteger("rocketcount");
		energy = nbt.getInteger("energy");
		b2energy = nbt.getInteger("b2energy");
		flamecount = nbt.getInteger("flamecount");
		nukecount = nbt.getInteger("nukecount");
		itexfolder = nbt.getInteger("texfolder");
		scale = nbt.getFloat("scale");
		if (itexfolder != -1) itexloc = nbt.getString("texloc");
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		super.attackEntityFrom(par1DamageSource, par2);
		if (!isDead && !worldObj.isRemote && health > 0 && !forcefield)
		{
			if (par2 > 50)
			{
				health -= 50;
				if (rider == null) damageUntilWake -= 50;
				endangered = true;
			}
			else
			{
				health -= par2;
				if (rider == null) damageUntilWake -= par2;
			}
			
			if (health <= 0)
			{
				health = 0;
				RivalRebelsSoundPlayer.playSound(this, 0, 0, 30, 1);
			}
		}
		
		return true;
	}
	
	@Override
	protected void entityInit()
	{
	}
	
	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity)
	{
		return null;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox()
	{
		return null;
	}
	
	@Override
	public boolean canBeCollidedWith()
	{
		return false;
	}
	
	@Override
	public boolean canBePushed()
	{
		return false;
	}
	
	@SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float p_70070_1_)
    {
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.posZ);

        if (this.worldObj.blockExists(i, 0, j))
        {
            return this.worldObj.getLightBrightnessForSkyBlocks(i, 255, j, 0);
        }
        else
        {
            return 0;
        }
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float p_70013_1_)
    {
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.posZ);

        if (this.worldObj.blockExists(i, 0, j))
        {
            return this.worldObj.getLightBrightness(i, 255, j);
        }
        else
        {
            return 0.0F;
        }
    }
	
	@Override
	public boolean isInRangeToRenderDist(double par1)
	{
		return true;
	}
}
