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
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.entity.EntityRhodes;
import assets.rivalrebels.common.entity.EntityRhodesPiece;
import assets.rivalrebels.common.round.RivalRebelsPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class RhodesPiecePacket implements IMessage
{
	int	id = 0;
	float scale;
	int color;
	public RhodesPiecePacket()
	{
		
	}
	
	public RhodesPiecePacket(EntityRhodesPiece piece)
	{
		id = piece.getEntityId();
		scale = piece.scale;
		color = piece.color;
	}
	@Override
	public void fromBytes(ByteBuf buf)
	{
		id = buf.readInt();
		scale = buf.readFloat();
		color = buf.readByte();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(id);
		buf.writeFloat(scale);
		buf.writeByte(color);
	}
	
	public static class Handler implements IMessageHandler<RhodesPiecePacket, IMessage>
	{
		@Override
		public IMessage onMessage(RhodesPiecePacket m, MessageContext ctx)
		{
			Entity e = Minecraft.getMinecraft().theWorld.getEntityByID(m.id);
			if (e instanceof EntityRhodesPiece)
			{
				EntityRhodesPiece er = (EntityRhodesPiece) e;
				er.color = m.color;
				er.scale = m.scale;
			}
			return null;
		}
	}
}
