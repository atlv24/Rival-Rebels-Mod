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
package assets.rivalrebels.common.item.weapon;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.entity.EntityAntimatterBomb;
import assets.rivalrebels.common.entity.EntityB83;
import assets.rivalrebels.common.entity.EntityBomb;
import assets.rivalrebels.common.entity.EntityCuchillo;
import assets.rivalrebels.common.entity.EntityDebris;
import assets.rivalrebels.common.entity.EntityFlameBall;
import assets.rivalrebels.common.entity.EntityFlameBall1;
import assets.rivalrebels.common.entity.EntityFlameBall2;
import assets.rivalrebels.common.entity.EntityGasGrenade;
import assets.rivalrebels.common.entity.EntityGore;
import assets.rivalrebels.common.entity.EntityHackB83;
import assets.rivalrebels.common.entity.EntityHotPotato;
import assets.rivalrebels.common.entity.EntityLaserBurst;
import assets.rivalrebels.common.entity.EntityNuclearBlast;
import assets.rivalrebels.common.entity.EntityNuke;
import assets.rivalrebels.common.entity.EntityPlasmoid;
import assets.rivalrebels.common.entity.EntityRaytrace;
import assets.rivalrebels.common.entity.EntityRocket;
import assets.rivalrebels.common.entity.EntityRoddiskRebel;
import assets.rivalrebels.common.entity.EntitySeekB83;
import assets.rivalrebels.common.entity.EntityTachyonBomb;
import assets.rivalrebels.common.entity.EntityTheoreticalTsar;
import assets.rivalrebels.common.entity.EntityTsar;
import assets.rivalrebels.common.explosion.NuclearExplosion;
import assets.rivalrebels.common.round.RivalRebelsPlayer;
import assets.rivalrebels.common.round.RivalRebelsRank;

public class ItemRoda extends Item
{
	public static String[] entities = new String[]{
			"fire1",//0
			"fire2",
			"fire3",
			"plasma",
			"gas",
			"tesla",//5
			"cuchillo",
			"rocket",
			"einsten",
			"flesh",
			"bomb",//10
			"creeper",
			"snowman",
			"roddisk",
			"seeker",
			"pigman",//15
			"zombie",
			"tnt",
			"irongolem",
			"paratrooper",
			"oreblocks",//20
			"supplies",
			"blocks",
			"roda",
			"b83",
			"hackb83",//25
			"enchantedb83",
			"nuke",
			"tsar",
			"theoreticaltsar",
			"fatnuke",
			"antimatter",
			"tachyon"
		};
	static float[] randoms = new float[]{
			0.1f,			
			0.1f,
			0.1f,
			0.0f,
			0.0f,
			0.0f,
			0.0f,
			0.0f,
			0.2f,
			0.9f,
			0.1f,
			0.0f,
			0.1f,
			0.1f,
			0.1f,
			0.0f,
			0.1f,
			0.1f,
			0.1f,
			0.1f,
			0.1f,
			0.1f,
			0.1f,
			0.1f,
			0.1f,
			0.1f,
			0.1f,
			0.1f,
			0.1f,
			0.1f,
			0.1f,
			0.1f,
			0.1f,
			0.1f
		};
	static float[] speeds = new float[]{
			1.6f,
			1.5f,
			1.2f,
			2.0f,
			1.3f,
			100.0f,
			1.3f,
			1.0f,
			3.0f,
			0.5f,
			4.3f,
			0.0f,
			1.5f,
			1.5f,
			1.7f,
			1.0f,
			1.5f,
			1.5f,
			1.5f,
			1.5f,
			1.5f,
			1.5f,
			1.5f,
			1.5f,
			3.0f,
			3.0f,
			3.0f,
			3.0f,
			3.0f,
			3.0f,
			3.0f,
			3.0f,
			3.0f,
			3.0f
		};
	public static int[] rates = new int[]{
			1,//0
			1,
			1,
			8,
			8,
			1,//5
			6,
			8,
			1,
			2,
			6,//10
			10,
			10,
			10,
			10,
			10,//15
			10,
			2,
			10,
			10,
			1,//20
			1,
			1,
			1,
			20,
			20,//25
			20,
			20,
			20,
			20,
			20,
			1,
			1
		};
	public static int rodaindex = 23;
	
	public static void spawn(int index, World world, double x, double y, double z, double mx, double my, double mz, double speed, double random)
	{
		if (entities[index] == "roda")
		{
			int newindex = world.rand.nextInt(index);
			spawn(newindex, world, x,y,z,mx,my,mz,speed,random);
			return;
		}
		speed *= speeds[index];
		random += randoms[index];
		double rx = world.rand.nextGaussian() * random;
		double ry = world.rand.nextGaussian() * random;
		double rz = world.rand.nextGaussian() * random;
		mx *= speed;
		my *= speed;
		mz *= speed;
		mx += rx;
		my += ry;
		mz += rz;
		Entity e = null;
		switch(index)
		{
		case 0:
			e = new EntityFlameBall(world, x,y,z,mx,my,mz);
		break;
		case 1:
			e = new EntityFlameBall1(world, x,y,z,mx,my,mz);
		break;
		case 2:
			e = new EntityFlameBall2(world, x,y,z,mx,my,mz);
		break;
		case 3:
			e = new EntityPlasmoid(world, x,y,z,mx,my,mz,1.0f);
		break;
		case 4:
			e = new EntityGasGrenade(world, x,y,z,mx,my,mz);
		break;
		case 5:
			e = new EntityRaytrace(world, x,y,z,mx,my,mz);
		break;
		case 6:
			e = new EntityCuchillo(world, x,y,z,mx,my,mz);
		break;
		case 7:
			e = new EntityRocket(world, x,y,z,mx,my,mz);
		break;
		case 8:
			e = new EntityLaserBurst(world, x,y,z,mx,my,mz);
		break;
		case 9:
			e = new EntityGore(world, x,y,z,mx,my,mz,world.rand.nextInt(3), world.rand.nextInt(11)+1);
		break;
		case 10:
			e = new EntityBomb(world, x,y,z,mx,my,mz);
		break;
		case 11:
			e = new EntityCreeper(world);
			e.setPosition(x,y,z);
			e.motionX = mx;
			e.motionY = my;
			e.motionZ = mz;
		break;
		case 12:
			e = new EntitySnowman(world);
			e.setPosition(x,y,z);
			e.motionX = mx;
			e.motionY = my;
			e.motionZ = mz;
		break;
		case 13:
			e = new EntityRoddiskRebel(world);
			e.setPosition(x,y,z);
			e.motionX = mx;
			e.motionY = my;
			e.motionZ = mz;
		break;
		case 14:
			e = new EntitySeekB83(world);
			e.setPosition(x,y,z);
			e.motionX = mx;
			e.motionY = my;
			e.motionZ = mz;
		break;
		case 15:
			e = new EntityPigZombie(world);
			e.setPosition(x,y,z);
			e.motionX = mx;
			e.motionY = my;
			e.motionZ = mz;
		break;
		case 16:
			e = new EntityZombie(world);
			e.setPosition(x,y,z);
			e.motionX = mx;
			e.motionY = my;
			e.motionZ = mz;
		break;
		case 17:
			e = new EntityTNTPrimed(world);
			e.setPosition(x,y,z);
			e.motionX = mx;
			e.motionY = my;
			e.motionZ = mz;
			((EntityTNTPrimed)e).fuse = 80;
		break;
		case 18:
			e = new EntityIronGolem(world);
			e.setPosition(x,y,z);
			e.motionX = mx;
			e.motionY = my;
			e.motionZ = mz;
		break;
		case 19:
			Entity zomb = new EntityPigZombie(world);
			zomb.setPosition(x,y,z);
			zomb.motionX = mx;
			zomb.motionY = my;
			zomb.motionZ = mz;
			world.spawnEntityInWorld(zomb);
			e = new EntityChicken(world);
			e.setPosition(x,y,z);
			e.motionX = mx;
			e.motionY = my;
			e.motionZ = mz;
			zomb.mountEntity(e);
		break;
		case 20:
			Block[] blocks1 = NuclearExplosion.prblocks;
			Block b1 = blocks1[world.rand.nextInt(blocks1.length)];
			e = new EntityDebris(world,x,y,z,mx,my,mz,b1);
		break;
		case 21:
			Block[] blocks2 = new Block[]{RivalRebels.ammunition,RivalRebels.supplies,RivalRebels.weapons,RivalRebels.explosives,RivalRebels.omegaarmor,RivalRebels.sigmaarmor};
			Block b2 = blocks2[world.rand.nextInt(blocks2.length)];
			e = new EntityDebris(world,x,y,z,mx,my,mz,b2);
		break;
		case 22:
			Block[] blocks3 = new Block[]{Blocks.sand,Blocks.gravel,Blocks.cobblestone,Blocks.dirt};
			Block b3 = blocks3[world.rand.nextInt(blocks3.length)];
			e = new EntityDebris(world,x,y,z,mx,my,mz,b3);
		break;
		case 23:
		break;
		case 24:
			e = new EntityB83(world, x,y,z,mx,my,mz);
		break;
		case 25:
			e = new EntityHackB83(world, x,y,z,mx,my,mz,false);
		break;
		case 26:
			e = new EntityHackB83(world, x,y,z,mx,my,mz,true);
		break;
		case 27:
			e = new EntityNuke(world, x,y,z,mx,my,mz);
		break;
		case 28:
			e = new EntityTsar(world, x,y,z,mx,my,mz,1);
		break;
		case 29:
			e = new EntityTheoreticalTsar(world, x,y,z,mx,my,mz,1);
		break;
		case 30:
			e = new EntityHotPotato(world, x,y,z,mx,my,mz);
		break;
		case 31:
			e = new EntityAntimatterBomb(world, x,y,z,mx,my,mz,1);
		break;
		case 32:
			e = new EntityTachyonBomb(world, x,y,z,mx,my,mz,1);
		break;
		}
		if (world.isRemote) return;
		if (e != null)
		{
			world.spawnEntityInWorld(e);
		}
	}
	
	
	
	boolean pass = false;
	public ItemRoda()
	{
		super();
		maxStackSize = 1;
		setCreativeTab(RivalRebels.rralltab);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		if (!pass)
		{
			player.addChatMessage(new ChatComponentText("Password?"));
			pass = true;
		}
		player.swingItem();
		//if (world.isRemote) return item;
		RivalRebelsPlayer rrp = RivalRebels.round.rrplayerlist.getForName(player.getCommandSenderName());
		if ((MinecraftServer.getServer() != null && MinecraftServer.getServer().isSinglePlayer())
		 || (rrp != null && (rrp.rrrank == RivalRebelsRank.LEADER || rrp.rrrank == RivalRebelsRank.OFFICER || rrp.rrrank == RivalRebelsRank.REP)))
		{
			//if (world.isRemote) return item;
			player.setItemInUse(item, 256);
			item.stackTagCompound.setInteger("happynewyear",item.stackTagCompound.getInteger("happynewyear")+10);
			if (item.stackTagCompound.getInteger("happynewyear") > 1400 && !world.isRemote) //EXPLODE
			{
				world.spawnEntityInWorld(new EntityNuclearBlast(world, player.posX, player.posY, player.posZ, 6, true));
				player.inventory.mainInventory[player.inventory.currentItem] = null;
				return item;
			}
			double motionX = (-MathHelper.sin(player.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float) Math.PI));
			double motionZ = (MathHelper.cos(player.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(player.rotationPitch / 180.0F * (float) Math.PI));
			double motionY = (-MathHelper.sin(player.rotationPitch / 180.0F * (float) Math.PI));
			spawn(rodaindex, world, player.posX,player.posY + 3.0,player.posZ,motionX,motionY,motionZ, 1.0,0.0);
		}
		return item;
	}
	
	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int par4, boolean par5)
	{
		if (world.isRemote) return;
		if (item.getTagCompound() == null) item.stackTagCompound = new NBTTagCompound();
		if (item.stackTagCompound.getInteger("happynewyear")>0)item.stackTagCompound.setInteger("happynewyear",item.stackTagCompound.getInteger("happynewyear")-1);
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (player.worldObj.isRemote) return true;
		Random r = player.worldObj.rand;
		double x = entity.posX - player.posX;
		double y = entity.posY - player.posY;
		double z = entity.posZ - player.posZ;
		
		double dist = Math.sqrt(x * x + y * y + z * z);
		
		switch (r.nextInt(4))
		{
			case 0:
				x /= -dist;
				y /= -dist;
				z /= -dist;
				
				entity.motionX = x * 3 + (r.nextFloat() - 0.5f) * 0.1;
				entity.motionY = y * 3 + (r.nextFloat() - 0.5f) * 0.1;
				entity.motionZ = z * 3 + (r.nextFloat() - 0.5f) * 0.1;
			break;
			case 1:
				x /= dist;
				y /= dist;
				z /= dist;
				
				entity.motionX = x * 2 + (r.nextFloat() - 0.5f) * 0.1;
				entity.motionY = y * 2 + (r.nextFloat() - 0.5f) * 0.1;
				entity.motionZ = z * 2 + (r.nextFloat() - 0.5f) * 0.1;
			break;
		}
		return true;
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:be");
	}
	
	@Override
	public boolean isFull3D()
	{
		return true;
	}
}