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
import assets.rivalrebels.common.core.FileRW;
import assets.rivalrebels.common.entity.EntityGore;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class EntityGorePacket implements IMessage
{
	byte	mob			= 0;
	byte	type		= 0;
	int		id			= 0;
	boolean	green		= false;
	String	username	= "Steve";
	float	size		= -1;
	
	public EntityGorePacket()
	{
	}
	
	public EntityGorePacket(EntityGore eg)
	{
		mob = (byte) eg.mob;
		type = (byte) eg.type;
		id = eg.getEntityId();
		green = eg.greenblood;
		username = eg.username;
		size = (float) eg.size;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		id = buf.readInt();
		mob = buf.readByte();
		type = buf.readByte();
		green = buf.readBoolean();
		if (mob == 0)
		{
			byte[] b = new byte[buf.readByte()];
			buf.readBytes(b);
			username = FileRW.getStringBytes(b);
		}
		if (mob == 11) size = buf.readFloat();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(id);
		buf.writeByte(mob);
		buf.writeByte(type);
		buf.writeBoolean(green);
		if (mob == 0)
		{
			buf.writeByte(username.length());
			buf.writeBytes(FileRW.getBytesString(username));
		}
		if (mob == 11) buf.writeFloat(size);
	}
	
	public static class Handler implements IMessageHandler<EntityGorePacket, IMessage>
	{
		@Override
		public IMessage onMessage(EntityGorePacket m, MessageContext ctx)
		{
			Iterator iter = Minecraft.getMinecraft().theWorld.loadedEntityList.iterator();
			while (iter.hasNext())
			{
				Entity e = (Entity) iter.next();
				if (e.getEntityId() == m.id && e instanceof EntityGore)
				{
					EntityGore eg = (EntityGore) e;
					eg.mob = m.mob;
					eg.type = m.type;
					eg.greenblood = m.green;
					
					if (m.mob == 0) eg.username = m.username;
					if (m.mob == 11) eg.size = m.size;
					break;
				}
			}
			return null;
		}
	}
}