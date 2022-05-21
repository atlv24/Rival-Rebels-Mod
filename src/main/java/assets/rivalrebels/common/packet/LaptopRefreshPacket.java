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
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LaptopRefreshPacket implements IMessage
{
	int x;
	int y;
	int z;
	int tasks;
	int carpet;
	
	public LaptopRefreshPacket()
	{
		
	}
	
	public LaptopRefreshPacket(int X, int Y, int Z, int T, int C)
	{
		x = X;
		y = Y;
		z = Z;
		tasks = T;
		carpet = C;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		tasks = buf.readInt();
		carpet = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(tasks);
		buf.writeInt(carpet);
	}
	
	public static class Handler implements IMessageHandler<LaptopRefreshPacket, IMessage>
	{
		@Override
		public IMessage onMessage(LaptopRefreshPacket m, MessageContext ctx)
		{
			TileEntity te = Minecraft.getMinecraft().theWorld.getTileEntity(m.x, m.y, m.z);
			//System.out.println("packet recieved");
			if (te != null && te instanceof TileEntityLaptop)
			{
				((TileEntityLaptop)te).b2spirit=m.tasks;
				((TileEntityLaptop)te).b2carpet=m.carpet;
			}
			return null;
		}
	}
}
