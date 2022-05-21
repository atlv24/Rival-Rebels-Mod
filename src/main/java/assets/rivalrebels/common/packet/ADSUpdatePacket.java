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
import assets.rivalrebels.common.tileentity.TileEntityReciever;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ADSUpdatePacket implements IMessage
{
	int x;
	int y;
	int z;
	int range;
	boolean mob;
	boolean chip;
	boolean player;
	boolean haswep;
	String user;
	
	public ADSUpdatePacket()
	{
		
	}
	
	public ADSUpdatePacket(int X, int Y, int Z, int r, boolean m, boolean c, boolean p, boolean h, String u)
	{
		x = X;
		y = Y;
		z = Z;
		range = r;
		mob = m;
		chip = c;
		player = p;
		haswep = h;
		user = u;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		x=buf.readInt();
		y=buf.readInt();
		z=buf.readInt();
		
		range=buf.readInt();
		mob=buf.readBoolean();
		chip=buf.readBoolean();
		player=buf.readBoolean();
		haswep=buf.readBoolean();
		StringBuilder r = new StringBuilder();
		while (buf.isReadable())
		{
			byte b = buf.readByte();
			r.append((char) b);
		}
		user = r.toString();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		
		buf.writeInt(range);
		buf.writeBoolean(mob);
		buf.writeBoolean(chip);
		buf.writeBoolean(player);
		buf.writeBoolean(haswep);
		for (int i = 0; i < user.length(); i++)
		{
			buf.writeByte((byte)user.charAt(i));
		}
	}
	
	public static class Handler implements IMessageHandler<ADSUpdatePacket, IMessage>
	{
		@Override
		public IMessage onMessage(ADSUpdatePacket m, MessageContext ctx)
		{
			TileEntity te = Minecraft.getMinecraft().theWorld.getTileEntity(m.x, m.y, m.z);
			
			if (te instanceof TileEntityReciever)
			{
				TileEntityReciever ter = (TileEntityReciever) te;
				ter.yawLimit = m.range;
				ter.kMobs = m.mob;
				ter.kTeam = m.chip;
				ter.kPlayers = m.player;
				ter.hasWeapon = m.haswep;
				ter.username = m.user;
			}
			return null;
		}
	}
}
