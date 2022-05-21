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
package assets.rivalrebels.common.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.Scoreboard;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.round.RivalRebelsClass;
import assets.rivalrebels.common.round.RivalRebelsPlayer;
import assets.rivalrebels.common.round.RivalRebelsRank;
import assets.rivalrebels.common.round.RivalRebelsTeam;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class JoinTeamPacket implements IMessage
{
	RivalRebelsClass rrclass;
	RivalRebelsTeam rrteam;
	
	public JoinTeamPacket()
	{
		
	}
	
	public JoinTeamPacket(RivalRebelsTeam rrt, RivalRebelsClass rrc)
	{
		rrclass = rrc;
		rrteam = rrt;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		rrclass = RivalRebelsClass.getForID(buf.readByte());
		rrteam = RivalRebelsTeam.getForID(buf.readByte());
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeByte(rrclass.id);
		buf.writeByte(rrteam.id);
	}
	
	public static class Handler implements IMessageHandler<JoinTeamPacket, IMessage>
	{
		@Override
		public IMessage onMessage(JoinTeamPacket m, MessageContext ctx)
		{
			RivalRebelsPlayer p = RivalRebels.round.rrplayerlist.getForName(ctx.getServerHandler().playerEntity.getCommandSenderName());
			
			if (p.isreset)
			{
				p.isreset = false;
				p.rrclass=m.rrclass;
				p.rrteam=m.rrteam;
				Scoreboard scrb = RivalRebels.round.world.getScoreboard();
				scrb.removePlayerFromTeams(p.username);
				scrb.func_151392_a(p.username, p.rrteam.toString());
				ItemStack[] inventory = m.rrclass.inventory;
				
				for (int i = 0; i < inventory.length; i++)
				{
					ctx.getServerHandler().playerEntity.inventory.addItemStackToInventory(inventory[i].copy());
				}
				
				if (m.rrteam == RivalRebelsTeam.OMEGA)
				{
					switch (m.rrclass)
					{
						case REBEL:
							ctx.getServerHandler().playerEntity.inventory.armorInventory = new ItemStack[] { new ItemStack(RivalRebels.orebelboots), new ItemStack(RivalRebels.orebelpants), new ItemStack(RivalRebels.orebelchest), new ItemStack(RivalRebels.orebelhelmet) };
						break;
						
						case NUKER:
							ctx.getServerHandler().playerEntity.inventory.armorInventory = new ItemStack[] { new ItemStack(RivalRebels.onukerboots), new ItemStack(RivalRebels.onukerpants), new ItemStack(RivalRebels.onukerchest), new ItemStack(RivalRebels.onukerhelmet) };
						break;
						
						case INTEL:
							ctx.getServerHandler().playerEntity.inventory.armorInventory = new ItemStack[] { new ItemStack(RivalRebels.ointelboots), new ItemStack(RivalRebels.ointelpants), new ItemStack(RivalRebels.ointelchest), new ItemStack(RivalRebels.ointelhelmet) };
						break;
						
						case HACKER:
							ctx.getServerHandler().playerEntity.inventory.armorInventory = new ItemStack[] { new ItemStack(RivalRebels.ohackerboots), new ItemStack(RivalRebels.ohackerpants), new ItemStack(RivalRebels.ohackerchest), new ItemStack(RivalRebels.ohackerhelmet) };
						break;
						case NONE:
							
						break;
					}
				}
				else if (m.rrteam == RivalRebelsTeam.SIGMA)
				{
					switch (m.rrclass)
					{
						case REBEL:
							ctx.getServerHandler().playerEntity.inventory.armorInventory = new ItemStack[] { new ItemStack(RivalRebels.srebelboots), new ItemStack(RivalRebels.srebelpants), new ItemStack(RivalRebels.srebelchest), new ItemStack(RivalRebels.srebelhelmet) };
						break;
						
						case NUKER:
							ctx.getServerHandler().playerEntity.inventory.armorInventory = new ItemStack[] { new ItemStack(RivalRebels.snukerboots), new ItemStack(RivalRebels.snukerpants), new ItemStack(RivalRebels.snukerchest), new ItemStack(RivalRebels.snukerhelmet) };
						break;
						
						case INTEL:
							ctx.getServerHandler().playerEntity.inventory.armorInventory = new ItemStack[] { new ItemStack(RivalRebels.sintelboots), new ItemStack(RivalRebels.sintelpants), new ItemStack(RivalRebels.sintelchest), new ItemStack(RivalRebels.sintelhelmet) };
						break;
						
						case HACKER:
							ctx.getServerHandler().playerEntity.inventory.armorInventory = new ItemStack[] { new ItemStack(RivalRebels.shackerboots), new ItemStack(RivalRebels.shackerpants), new ItemStack(RivalRebels.shackerchest), new ItemStack(RivalRebels.shackerhelmet) };
						break;
						case NONE:
							
						break;
					}
				}
				PacketDispatcher.packetsys.sendToAll(RivalRebels.round.rrplayerlist);
			}
			if (m.rrteam == RivalRebelsTeam.OMEGA)
			{
				double sx = RivalRebels.round.oObjx + (RivalRebels.round.world.rand.nextInt(2)-0.5)*30+0.5f;
				double sy = RivalRebels.round.oObjy + 1;
				double sz = RivalRebels.round.oObjz + (RivalRebels.round.world.rand.nextInt(2)-0.5)*30+0.5f;
				ctx.getServerHandler().playerEntity.playerNetServerHandler.setPlayerLocation(sx, sy, sz,0,0);
			}
			else if (m.rrteam == RivalRebelsTeam.SIGMA)
			{
				double sx = RivalRebels.round.sObjx + (RivalRebels.round.world.rand.nextInt(2)-0.5)*30+0.5f;
				double sy = RivalRebels.round.sObjy + 1;
				double sz = RivalRebels.round.sObjz + (RivalRebels.round.world.rand.nextInt(2)-0.5)*30+0.5f;
				ctx.getServerHandler().playerEntity.playerNetServerHandler.setPlayerLocation(sx, sy, sz,0,0);
			}
			return null;
		}
	}
}
