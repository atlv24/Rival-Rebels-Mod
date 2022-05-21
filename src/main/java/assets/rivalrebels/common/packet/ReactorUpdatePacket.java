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
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.round.RivalRebelsClass;
import assets.rivalrebels.common.round.RivalRebelsPlayer;
import assets.rivalrebels.common.round.RivalRebelsRank;
import assets.rivalrebels.common.round.RivalRebelsTeam;
import assets.rivalrebels.common.tileentity.TileEntityLaptop;
import assets.rivalrebels.common.tileentity.TileEntityList;
import assets.rivalrebels.common.tileentity.TileEntityMachineBase;
import assets.rivalrebels.common.tileentity.TileEntityReactive;
import assets.rivalrebels.common.tileentity.TileEntityReactor;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ReactorUpdatePacket implements IMessage
{
	int x;
	int y;
	int z;
	float consumed;
	float lasttick;
	boolean melt;
	boolean eject;
	boolean on;
	TileEntityList machines;
	ByteBuf bbuf;
	
	public ReactorUpdatePacket()
	{
		
	}
	
	public ReactorUpdatePacket(int X, int Y, int Z, float cons, float last, boolean mt, boolean ej, boolean o, TileEntityList mach)
	{
		x = X;
		y = Y;
		z = Z;
		consumed = cons;
		lasttick = last;
		melt = mt;
		eject = ej;
		on = o;
		machines = mach;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		bbuf = buf.copy();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		
		buf.writeFloat(consumed);
		buf.writeFloat(lasttick);
		buf.writeBoolean(melt);
		buf.writeBoolean(eject);
		buf.writeBoolean(on);
		if (machines == null) return;
		for (int i = 0; i < machines.getSize(); i++)
		{
			TileEntityMachineBase te = (TileEntityMachineBase) machines.get(i);
			if (te == null || te instanceof TileEntityReactive) continue;
			buf.writeInt(te.xCoord);
			buf.writeInt(te.yCoord);
			buf.writeInt(te.zCoord);
			buf.writeFloat(te.powerGiven);
			buf.writeFloat(te.pInR);
		}
	}
	
	public static class Handler implements IMessageHandler<ReactorUpdatePacket, IMessage>
	{
		@Override
		public IMessage onMessage(ReactorUpdatePacket m, MessageContext ctx)
		{
			int x = m.bbuf.readInt();
			int y = m.bbuf.readInt();
			int z = m.bbuf.readInt();
			TileEntity te = Minecraft.getMinecraft().theWorld.getTileEntity(x, y, z);
			
			if (te instanceof TileEntityReactor)
			{
				TileEntityReactor ter = (TileEntityReactor) te;
				ter.consumed = m.bbuf.readFloat();
				ter.lasttickconsumed = m.bbuf.readFloat();
				ter.melt = m.bbuf.readBoolean();
				ter.eject = m.bbuf.readBoolean();
				ter.on = m.bbuf.readBoolean();
				ter.machines.clear();
				while (m.bbuf.isReadable())
				{
					int xx = m.bbuf.readInt();
					int yy = m.bbuf.readInt();
					int zz = m.bbuf.readInt();
					TileEntity temb = Minecraft.getMinecraft().theWorld.getTileEntity(xx, yy, zz);
					if (temb instanceof TileEntityMachineBase)
					{
						TileEntityMachineBase tembr = ((TileEntityMachineBase) temb);
						tembr.powerGiven = m.bbuf.readFloat();
						tembr.pInR = m.bbuf.readFloat();
						tembr.x = x;
						tembr.y = y;
						tembr.z = z;
						tembr.edist = (float) Math.sqrt(tembr.getDistanceFrom(x, y, z));
						ter.machines.add(temb);
					}
					else
					{
						m.bbuf.readFloat();
						m.bbuf.readFloat();
					}
				}
			}
			return null;
		}
	}
}
