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
package assets.rivalrebels.common.round;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.FileRW;
import assets.rivalrebels.common.entity.EntityRhodes;
import assets.rivalrebels.common.packet.GuiSpawnPacket;
import assets.rivalrebels.common.packet.InspectPacket;
import assets.rivalrebels.common.packet.PacketDispatcher;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RivalRebelsRound implements IMessage
{
	private File					rounddata;
	private int						cSpawnx			= -1, cSpawnz = -1;
	public int						oObjx			= -1, oObjy = -1, oObjz = -1;
	public int						sObjx			= -1, sObjy = -1, sObjz = -1;
	private String					MotD			= "Select your class and nuke the enemy team's objective to win.";
	public RivalRebelsPlayerList	rrplayerlist	= new RivalRebelsPlayerList();
	public World					world;
	private int						winCountdown	= 0;
	private int						omegaWins		= 0;
	private int						sigmaWins		= 0;
	private boolean					lastwinomega 	= false; //t: omega f: sigma
	private boolean					fatnuke			= false;
	public int						waitVotes		= 0;
	public int						newBattleVotes	= 0;
	private int						spawnRadius 	= 20;
	private int						spawnRadius2	= 20*20;
	private int						objRadius		= 29;
	private int						objRadius2		= 29*29;
	private int						spawnDist		= 150;
	private int						objDist			= 200;
	private boolean					roundstarted	= false;
	public int omegaHealth=RivalRebels.objectiveHealth;
	public int sigmaHealth=RivalRebels.objectiveHealth;
	
	public RivalRebelsRound()
	{
		
	}
	
	public RivalRebelsRound(int spr, int spd, int obd)
	{
		this();
		spawnRadius = spr;
		spawnRadius2 = spr * spr;
		spawnDist = spd;
		objDist = obd;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		roundstarted = buf.readBoolean();
		cSpawnx = buf.readInt();
		cSpawnz = buf.readInt();
		oObjx = buf.readInt();
		oObjy = buf.readInt();
		oObjz = buf.readInt();
		sObjx = buf.readInt();
		sObjy = buf.readInt();
		sObjz = buf.readInt();
		winCountdown = buf.readInt();
		omegaWins = buf.readInt();
		sigmaWins = buf.readInt();
		lastwinomega = buf.readBoolean();
		fatnuke = buf.readBoolean();
		byte[] bytes = new byte[buf.readInt()];
		buf.readBytes(bytes);
		MotD = FileRW.getStringBytes(bytes);
		rrplayerlist.fromBytes(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeBoolean(roundstarted);
		buf.writeInt(cSpawnx);
		buf.writeInt(cSpawnz);
		buf.writeInt(oObjx);
		buf.writeInt(oObjy);
		buf.writeInt(oObjz);
		buf.writeInt(sObjx);
		buf.writeInt(sObjy);
		buf.writeInt(sObjz);
		buf.writeInt(winCountdown);
		buf.writeInt(omegaWins);
		buf.writeInt(sigmaWins);
		buf.writeBoolean(lastwinomega);
		buf.writeBoolean(fatnuke);
		buf.writeInt(MotD.length());
		buf.writeBytes(FileRW.getBytesString(MotD));
		rrplayerlist.toBytes(buf);
	}
	
	public static class Handler implements IMessageHandler<RivalRebelsRound, IMessage>
	{
		@Override
		public IMessage onMessage(RivalRebelsRound m, MessageContext ctx)
		{
			RivalRebels.round.copy(m);
			return null;
		}
	}
	
	public void copy(RivalRebelsRound m)
	{
		roundstarted = m.roundstarted;
		cSpawnx = m.cSpawnx;
		cSpawnz = m.cSpawnz;
		oObjx = m.oObjx;
		oObjy = m.oObjy;
		oObjz = m.oObjz;
		sObjx = m.sObjx;
		sObjy = m.sObjy;
		sObjz = m.sObjz;
		winCountdown = m.winCountdown;
		omegaWins = m.omegaWins;
		sigmaWins = m.sigmaWins;
		lastwinomega = m.lastwinomega;
		MotD = m.MotD;
		rrplayerlist = m.rrplayerlist;
	}
	
	public void newRound()
	{
		fatnuke = false;
		if (!roundstarted)
		{
			startRound(0,0);
			return;
		}
		rrplayerlist = new RivalRebelsPlayerList();
		Iterator iter = world.playerEntities.iterator();
		while (iter.hasNext())
		{
			EntityPlayer player = (EntityPlayer) iter.next();
			player.attackEntityFrom(DamageSource.outOfWorld, 20000);
			player.inventory.clearInventory(null, -1);
		}
		cSpawnz += spawnDist;
		world.setSpawnLocation(cSpawnx, 200, cSpawnz);
		float f = RivalRebels.rhodesChance;
		while (f >= 1)
		{
			f--;
			world.spawnEntityInWorld(new EntityRhodes(world, cSpawnx+Math.random()-0.5f, 170, cSpawnz+Math.random()-0.5f,1));
		}
		if (f > Math.random()) world.spawnEntityInWorld(new EntityRhodes(world, cSpawnx+Math.random()-0.5f, 170, cSpawnz+Math.random()-0.5f,1));
		buildSpawn();
		omegaHealth=RivalRebels.objectiveHealth;
		sigmaHealth=RivalRebels.objectiveHealth;
		PacketDispatcher.packetsys.sendToAll(this);
	}

	public void startRound(int x, int z)
	{
		fatnuke = false;
		roundstarted = true;
		cSpawnx = x;
		cSpawnz = z - spawnDist;
		newRound();
	}
	
	public void roundManualStart()
	{
		if (!roundstarted || fatnuke)
		{
			fatnuke = true;
			roundstarted = true;
			rrplayerlist = new RivalRebelsPlayerList();
			cSpawnx = (oObjx+sObjx)/2;
			cSpawnz = (oObjz+sObjz)/2;
			world.setSpawnLocation(cSpawnx, world.getHeightValue(cSpawnx, cSpawnz), cSpawnz);
			omegaHealth=RivalRebels.objectiveHealth;
			sigmaHealth=RivalRebels.objectiveHealth;
			PacketDispatcher.packetsys.sendToAll(this);
		}
	}
	
	public class PlayerInvisibility
	{
		public PlayerInvisibility(EntityPlayer p, int i)
		{
			player = p;
			durationleft = i;
		}
		public EntityPlayer player;
		public int durationleft;
	}
	
	private List<PlayerInvisibility> players = new ArrayList<PlayerInvisibility>();
	
	public void setInvisible(EntityPlayer player)
	{
		if (player != null)
		{
			boolean contained = false;
			for (int i = players.size()-1; i >= 0; i--)
			{
				PlayerInvisibility t = players.get(i);
				if (t.player == player)
				{
					t.durationleft = 120;
					contained = true;
				}
			}
			if (!contained)
			{
				players.add(new PlayerInvisibility(player, 120));
			}
		}
	}
	
	@SubscribeEvent
	public void updateRound(TickEvent event)
	{
		if (roundstarted && winCountdown > 0 && event.phase.equals(Phase.END))
		{
			if (event.type == TickEvent.Type.SERVER) updateServer();
			if (event.type == TickEvent.Type.CLIENT) updateClient();
		}
		if (event.type == TickEvent.Type.CLIENT && event.phase.equals(Phase.END)) updateInvisible();
	}
	
	public void updateServer()
	{
		winCountdown--;
		if (winCountdown == 0 && !fatnuke)
		{
			if (newBattleVotes >= waitVotes)
			{
				newBattleVotes = 0;
				waitVotes = 0;
				rrplayerlist.clearVotes();
				newRound();
			}
			else
			{
				newBattleVotes = 0;
				waitVotes = 0;
				rrplayerlist.clearVotes();
				winCountdown = 1199;
				PacketDispatcher.packetsys.sendToAll(this);
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void updateInvisible()
	{
		if (Minecraft.getMinecraft().theWorld == null) return;
		List playerlist = Minecraft.getMinecraft().theWorld.playerEntities;
		for (int i = 0; i < playerlist.size(); i++)
		{
			EntityPlayer e = (EntityPlayer)playerlist.get(i);
			if (e.inventory.armorInventory[3] != null && e.inventory.armorInventory[3].getItem() == RivalRebels.camera) setInvisible(e);
		}
		for (int i = players.size()-1; i >= 0; i--)
		{
			PlayerInvisibility t = players.get(i);
			t.durationleft--;
			if (t.durationleft <= 0)
			{
				t.player.renderDistanceWeight = 1;
				players.remove(i);
			}
			else
			{
				t.player.renderDistanceWeight = 0;
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void updateClient()
	{
		winCountdown--;
		if (winCountdown == 0 && !fatnuke) RivalRebels.proxy.closeGui();//cleargui
		else if (winCountdown == 400 && !fatnuke) RivalRebels.proxy.nextBattle();//open vote gui
		else if (winCountdown == 1000) RivalRebels.proxy.closeGui();//close gui
		else if (winCountdown == 1200) RivalRebels.proxy.teamWin(lastwinomega);//open winner gui
	}
	
	public void load()
	{
		MinecraftServer server = MinecraftServer.getServer();
		if (server != null)
		{
			world = server.worldServerForDimension(0);
			
			Scoreboard scrb = world.getScoreboard();
			try {
			ScorePlayerTeam omega = scrb.createTeam(RivalRebelsTeam.OMEGA.toString());
			ScorePlayerTeam sigma = scrb.createTeam(RivalRebelsTeam.SIGMA.toString());
			omega.setNamePrefix("§aΩ");
			sigma.setNamePrefix("§9Σ");
			omega.setAllowFriendlyFire(false);
			sigma.setAllowFriendlyFire(false);
			ScoreObjective kills = scrb.addScoreObjective("kills", IScoreObjectiveCriteria.playerKillCount);
			scrb.func_96530_a(0, kills);
			ScoreObjective deaths = scrb.addScoreObjective("deaths", IScoreObjectiveCriteria.deathCount);
			if (RivalRebels.scoreboardenabled)
			scrb.func_96530_a(1, deaths);
			deaths.setDisplayName("§8R§7I§fV§7A§8L R§7E§fBE§7L§8S");
			} catch(Exception e) {} //just in case teams already exist etc
			
			world.getGameRules().setOrCreateGameRule("keepInventory", "true");
			rounddata = new File(world.getSaveHandler().getWorldDirectory(), "rivalrebelsgamedata");
			
			if (rounddata.exists())
			{
				fromBytes(Unpooled.wrappedBuffer(FileRW.readbytes(rounddata)));
			}
		}
	}
	
	public void save()
	{
		ByteBuf data = Unpooled.buffer();
		toBytes(data);
		FileRW.writebytes(rounddata, Arrays.copyOf(data.array(), data.writerIndex()));
	}
	
	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent event)
	{
		PacketDispatcher.packetsys.sendTo(new InspectPacket(), (EntityPlayerMP) event.player);
		if (!roundstarted) return;
		if (!rrplayerlist.contains(event.player.getCommandSenderName()))
		{
			event.player.inventory.clearInventory(null, -1);
			event.player.setLocationAndAngles(cSpawnx, 200, cSpawnz,0,0);
			//rrplayerlist.add(new RivalRebelsPlayer(event.player.getCommandSenderName(), RivalRebelsTeam.NONE, RivalRebelsClass.NONE, RivalRebelsRank.REGULAR, RivalRebels.resetMax));
		}
		PacketDispatcher.packetsys.sendTo(rrplayerlist, (EntityPlayerMP) event.player);
		if (isInSpawn(event.player)) PacketDispatcher.packetsys.sendTo(new GuiSpawnPacket(), (EntityPlayerMP) event.player);
	}
	
	@SubscribeEvent
	public void onPlayerRespawn(PlayerRespawnEvent event)
	{
		if (!roundstarted) return;
		event.player.setLocationAndAngles(cSpawnx, 200, cSpawnz,0,0);
		PacketDispatcher.packetsys.sendTo(new GuiSpawnPacket(), (EntityPlayerMP) event.player);
	}
	
	private void buildSpawn()
	{
		int r = spawnRadius;
		int rs4 = (spawnRadius - 2) * (spawnRadius - 2);
		int rs1 = (spawnRadius - 1) * (spawnRadius - 1);
		int hrs = (int) (spawnRadius*0.65f);
		for (int x1 = -spawnRadius; x1 < spawnRadius; x1++)
		{
			int XX = x1 * x1;
			for (int z1 = -spawnRadius; z1 < spawnRadius; z1++)
			{
				int ZZ = z1 * z1 + XX;
				for (int y1 = -5; y1 < hrs; y1++)
				{
					int YY = y1 * y1 + ZZ;
					if ((YY > rs4 && YY < spawnRadius2) || ((y1 == -2 || y1 == -3 || y1 == -4) && YY < rs1)) world.setBlock(cSpawnx + x1, 200 + y1, cSpawnz + z1, RivalRebels.fshield);
				}
			}
		}
		
		oObjx = cSpawnx + objDist;
		oObjz = cSpawnz;
		sObjx = cSpawnx - objDist;
		sObjz = cSpawnz;
		
		Chunk chunk = world.getChunkFromBlockCoords(oObjx, oObjz);
        int x = oObjx&15;
        int z = oObjz&15;
        for (oObjy = chunk.getTopFilledSegment() + 15; oObjy > 0; --oObjy)
        {
            Block block = chunk.getBlock(x, oObjy, z);
            if (block != Blocks.air)
            {
                break;
            }
        }
        chunk = world.getChunkFromBlockCoords(sObjx, sObjz);
        x = sObjx&15;
        z = sObjz&15;
        for (sObjy = chunk.getTopFilledSegment() + 15; sObjy > 0; --sObjy)
        {
            Block block = chunk.getBlock(x, sObjy, z);
            if (block != Blocks.air)
            {
                break;
            }
        }
		
		for (int xx = -objRadius; xx <= objRadius; xx++)
		{
			int XX = xx * xx;
			for (int zz = -objRadius; zz <= objRadius; zz++)
			{
				int ZZ = zz * zz + XX;
				if (ZZ <= objRadius2)
				{
					if (Math.abs(xx) == 15 && Math.abs(zz) == 15)
					{
						world.setBlock(oObjx + xx, oObjy, oObjz + zz, RivalRebels.fshield);
						world.setBlock(sObjx + xx, sObjy, sObjz + zz, RivalRebels.fshield);
					}
					else
					{
						world.setBlock(oObjx + xx, oObjy, oObjz + zz, RivalRebels.reactive);
						world.setBlock(sObjx + xx, sObjy, sObjz + zz, RivalRebels.reactive);
					}
					for (int yy = 1; yy <= 7; yy++)
					{
						world.setBlock(oObjx + xx, oObjy + yy, oObjz + zz, Blocks.air);
						world.setBlock(sObjx + xx, sObjy + yy, sObjz + zz, Blocks.air);
					}
				}
			}
		}
		
		world.setBlock(oObjx + 21, oObjy, oObjz + 21, RivalRebels.conduit);
		world.setBlock(oObjx + 21, oObjy, oObjz - 21, RivalRebels.conduit);
		world.setBlock(oObjx - 21, oObjy, oObjz + 21, RivalRebels.conduit);
		world.setBlock(oObjx - 21, oObjy, oObjz - 21, RivalRebels.conduit);
		world.setBlock(sObjx + 21, sObjy, sObjz + 21, RivalRebels.conduit);
		world.setBlock(sObjx + 21, sObjy, sObjz - 21, RivalRebels.conduit);
		world.setBlock(sObjx - 21, sObjy, sObjz + 21, RivalRebels.conduit);
		world.setBlock(sObjx - 21, sObjy, sObjz - 21, RivalRebels.conduit);
		
		for (int i = 0; i < 4; i++)
		{
			world.setBlock(oObjx + 21, oObjy + 1 + i, oObjz + 21, Blocks.air);
			world.setBlock(oObjx + 21, oObjy + 1 + i, oObjz - 21, Blocks.air);
			world.setBlock(oObjx - 21, oObjy + 1 + i, oObjz + 21, Blocks.air);
			world.setBlock(oObjx - 21, oObjy + 1 + i, oObjz - 21, Blocks.air);
			world.setBlock(sObjx + 21, sObjy + 1 + i, sObjz + 21, Blocks.air);
			world.setBlock(sObjx + 21, sObjy + 1 + i, sObjz - 21, Blocks.air);
			world.setBlock(sObjx - 21, sObjy + 1 + i, sObjz + 21, Blocks.air);
			world.setBlock(sObjx - 21, sObjy + 1 + i, sObjz - 21, Blocks.air);
		}
		
		world.setBlock(oObjx, oObjy + 1, oObjz, RivalRebels.omegaobj);
		world.setBlock(sObjx, sObjy + 1, sObjz, RivalRebels.sigmaobj);
		if (RivalRebels.rhodesRoundsBase)
		{
			world.setBlock(oObjx, oObjy + 2, oObjz, RivalRebels.buildrhodes);
			world.setBlock(sObjx, sObjy + 2, sObjz, RivalRebels.buildrhodes);
		}
	}
	
	private boolean isInSpawn(EntityPlayer player)
	{
		return player.posY < 203 && player.posY > 198 && player.getDistanceSq(cSpawnx, 200, cSpawnz) < spawnRadius2;
	}
	
	public void winSigma()
	{
		if (!roundstarted) return;
		winCountdown = 1400;
		sigmaWins++;
		lastwinomega = false;
		world.setBlock(oObjx, oObjy, oObjz, RivalRebels.plasmaexplosion);
		world.setBlock(sObjx, sObjy, sObjz, RivalRebels.plasmaexplosion);
		for (int xpl = -objRadius; xpl < objRadius; xpl++)
		{
			int xxpl = xpl * xpl;
			for (int zpl = -objRadius; zpl < objRadius; zpl++)
			{
				int zzpl = zpl * zpl + xxpl;
				if (zzpl < objRadius2) for (int ypl = -1; ypl < 7; ypl++)
				{
					for (int i = 0; i < 16; i++)
					{
						world.setBlock(oObjx + xpl, oObjy + ypl, oObjz + zpl, Blocks.air);
					}
				}
			}
		}
		PacketDispatcher.packetsys.sendToAll(this);
	}
	
	public void winOmega()
	{
		if (!roundstarted) return;
		winCountdown = 1400;
		omegaWins++;
		lastwinomega = true;
		world.setBlock(oObjx, oObjy, oObjz, RivalRebels.plasmaexplosion);
		world.setBlock(sObjx, sObjy, sObjz, RivalRebels.plasmaexplosion);
		for (int xpl = -objRadius; xpl < objRadius; xpl++)
		{
			int xxpl = xpl * xpl;
			for (int zpl = -objRadius; zpl < objRadius; zpl++)
			{
				int zzpl = zpl * zpl + xxpl;
				if (zzpl < objRadius2) for (int ypl = -1; ypl < 7; ypl++)
				{
					for (int i = 0; i < 16; i++)
					{
						world.setBlock(sObjx + xpl, sObjy + ypl, sObjz + zpl, Blocks.air);
					}
				}
			}
		}
		PacketDispatcher.packetsys.sendToAll(this);
	}
	
	public void stopRounds()
	{
		if (!roundstarted) return;
		world.setBlock(oObjx, oObjy, oObjz, RivalRebels.plasmaexplosion);
		world.setBlock(sObjx, sObjy, sObjz, RivalRebels.plasmaexplosion);
		winCountdown = 0;
		roundstarted = false;
		rrplayerlist.clearTeam();
		PacketDispatcher.packetsys.sendToAll(this);
	}
	
	public int takeOmegaHealth(int amnt)
	{
		if (amnt > omegaHealth)
		{
			int tmp = omegaHealth;
			omegaHealth = 0;
			winSigma();
			return tmp;
		}
		else
		{
			omegaHealth-=amnt;
			return amnt;
		}
	}
	
	public int takeSigmaHealth(int amnt)
	{
		if (amnt > sigmaHealth)
		{
			int tmp = sigmaHealth;
			sigmaHealth = 0;
			winOmega();
			return tmp;
		}
		else
		{
			sigmaHealth-=amnt;
			return amnt;
		}
	}
	
	public int getOmegaWins()
	{
		return omegaWins;
	}
	
	public int getSigmaWins()
	{
		return sigmaWins;
	}
	
	public String getMotD()
	{
		return MotD;
	}

	public void setMotD(String[] array)
	{
		StringBuilder strb = new StringBuilder();
		for (int i = 0; i < array.length; i++)
		{
			strb.append(array[i]);
			strb.append(" ");
		}
		MotD = strb.toString();
		PacketDispatcher.packetsys.sendToAll(this);
	}
	
	public boolean isStarted()
	{
		return roundstarted;
	}

	public float addOmegaHealth(float power)
	{
		omegaHealth += power;
		if (omegaHealth > RivalRebels.objectiveHealth)
		{
			int tmp = omegaHealth-RivalRebels.objectiveHealth;
			omegaHealth = RivalRebels.objectiveHealth;
			return tmp;
		}
		return 0;
	}

	public float addSigmaHealth(float power)
	{
		sigmaHealth += power;
		if (sigmaHealth > RivalRebels.objectiveHealth)
		{
			int tmp = sigmaHealth-RivalRebels.objectiveHealth;
			sigmaHealth = RivalRebels.objectiveHealth;
			return tmp;
		}
		return 0;
	}
}
