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

import java.util.Iterator;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.FileRW;
import assets.rivalrebels.common.entity.EntityGore;
import assets.rivalrebels.common.entity.EntityRhodes;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class RhodesJumpPacket implements IMessage
{
	int		id			= 0;
	boolean jump = false;
	boolean rocket = false;
	boolean laser = false;
	boolean fire = false;
	boolean forcefield = false;
	boolean plasma = false;
	boolean nuke = false;
	boolean stop = false;
	boolean b2spirit = false;
	boolean guard = false;
	public RhodesJumpPacket()
	{
	}
	
	public RhodesJumpPacket(EntityRhodes er, boolean j, boolean r, boolean l, boolean f, boolean p, boolean c, boolean n, boolean s, boolean z, boolean g)
	{
		id = er.getEntityId();
		jump = j;
		rocket = r;
		laser = l;
		fire = f;
		forcefield = c;
		plasma = p;
		nuke = n;
		stop = s;
		b2spirit = z;
		guard = g;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		id = buf.readInt();
		jump = buf.readBoolean();
		rocket = buf.readBoolean();
		laser = buf.readBoolean();
		fire = buf.readBoolean();
		forcefield = buf.readBoolean();
		plasma = buf.readBoolean();
		nuke = buf.readBoolean();
		stop = buf.readBoolean();
		b2spirit = buf.readBoolean();
		guard = buf.readBoolean();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(id);
		buf.writeBoolean(jump);
		buf.writeBoolean(rocket);
		buf.writeBoolean(laser);
		buf.writeBoolean(fire);
		buf.writeBoolean(forcefield);
		buf.writeBoolean(plasma);
		buf.writeBoolean(nuke);
		buf.writeBoolean(stop);
		buf.writeBoolean(b2spirit);
		buf.writeBoolean(guard);
	}
	
	public static class Handler implements IMessageHandler<RhodesJumpPacket, IMessage>
	{
		@Override
		public IMessage onMessage(RhodesJumpPacket m, MessageContext ctx)
		{
			Entity e = MinecraftServer.getServer().worldServers[0].getEntityByID(m.id);
			if (e instanceof EntityRhodes)
			{
				EntityRhodes er = (EntityRhodes) e;
				er.stop ^= m.stop;
				er.rocket = m.rocket;
				er.laser = m.laser;
				er.flame = m.fire;
				er.forcefield = m.forcefield;
				er.plasma ^= m.plasma;
				er.bomb = m.nuke;
				er.jet = m.jump;
				er.b2spirit = m.b2spirit;
				er.guard = m.guard;
				if (m.guard && RivalRebels.rhodesExit)
				{
					if (er.rider != null)
					{
						er.rider.capabilities.disableDamage = false;
						er.rider = null;
					}
				}
				if (m.jump && !er.stop)
				{
					er.flying = 2;
				}
			}
			return null;
		}
	}
}