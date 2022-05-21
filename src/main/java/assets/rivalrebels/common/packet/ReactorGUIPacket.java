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
import net.minecraft.tileentity.TileEntity;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.round.RivalRebelsClass;
import assets.rivalrebels.common.round.RivalRebelsPlayer;
import assets.rivalrebels.common.round.RivalRebelsRank;
import assets.rivalrebels.common.round.RivalRebelsTeam;
import assets.rivalrebels.common.tileentity.TileEntityLaptop;
import assets.rivalrebels.common.tileentity.TileEntityReactor;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ReactorGUIPacket implements IMessage
{
	int x;
	int y;
	int z;
	byte type;
	
	public ReactorGUIPacket()
	{
		
	}
	
	public ReactorGUIPacket(int X, int Y, int Z, byte b)
	{
		x = X;
		y = Y;
		z = Z;
		type = b;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		type = buf.readByte();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeByte(type);
	}
	
	public static class Handler implements IMessageHandler<ReactorGUIPacket, IMessage>
	{
		@Override
		public IMessage onMessage(ReactorGUIPacket m, MessageContext ctx)
		{
			if (ctx.getServerHandler().playerEntity.getDistanceSq(m.x, m.y, m.z) < 100)
			{
				TileEntity te = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(m.x, m.y, m.z);
				if (te != null && te instanceof TileEntityReactor)
				{
					TileEntityReactor ter = (TileEntityReactor)te;
					if (m.type == 0) ter.toggleOn();
					if (m.type == 1) ter.ejectCore();
				}
			}
			return null;
		}
	}
}
