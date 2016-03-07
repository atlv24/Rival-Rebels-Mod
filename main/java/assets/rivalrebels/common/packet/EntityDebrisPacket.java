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

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import assets.rivalrebels.common.entity.EntityDebris;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class EntityDebrisPacket implements IMessage
{
	Block	block;
	int		metadata	= 0;
	int		id			= 0;
	
	public EntityDebrisPacket()
	{
		
	}
	
	public EntityDebrisPacket(EntityDebris ed)
	{
		id = ed.getEntityId();
		block = ed.block;
		metadata = ed.metadata;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		id = buf.readInt();
		block = Block.getBlockById(buf.readInt());
		metadata = buf.readByte();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(id);
		buf.writeInt(Block.getIdFromBlock(block));
		buf.writeByte(metadata);
	}
	
	public static class Handler implements IMessageHandler<EntityDebrisPacket, IMessage>
	{
		@Override
		public IMessage onMessage(EntityDebrisPacket m, MessageContext ctx)
		{
			Iterator iter = Minecraft.getMinecraft().theWorld.loadedEntityList.iterator();
			while (iter.hasNext())
			{
				Entity e = (Entity) iter.next();
				if (e.getEntityId() == m.id && e instanceof EntityDebris)
				{
					EntityDebris ed = (EntityDebris) e;
					ed.block = m.block;
					ed.metadata = m.metadata;
					break;
				}
			}
			return null;
		}
	}
}