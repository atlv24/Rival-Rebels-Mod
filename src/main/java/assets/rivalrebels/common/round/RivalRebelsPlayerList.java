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

import java.util.Arrays;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.packet.PacketDispatcher;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class RivalRebelsPlayerList implements IMessage
{
	private int					size	= 0;
	private RivalRebelsPlayer[]	list	= new RivalRebelsPlayer[0];
	
	public RivalRebelsPlayerList()
	{
	}
	
	public int getSize()
	{
		return size;
	}
	
	public RivalRebelsPlayer add(RivalRebelsPlayer o)
	{
		size++;
		if (size <= list.length) list[size - 1] = o;
		else
		{
			int nsize = ((list.length * 3) / 2) + 1;
			if (nsize < size) nsize = size;
			list = Arrays.copyOf(list, nsize);
			list[size - 1] = o;
		}
		return o;
	}
	
	public void clear()
	{
		for (int i = 0; i < size; i++) list[i].clear();
	}
	
	public void clearTeam()
	{
		for (int i = 0; i < size; i++) list[i].clearTeam();
	}
	
	public boolean contains(String o)
	{
		for (int i = 0; i < size; i++) if (list[i].username.equals(o)) return true;
		return false;
	}
	
	public RivalRebelsPlayer getForName(String user)
	{
		for (int i = 0; i < size; i++) if (list[i].username.equals(user)) return list[i];
		return add(new RivalRebelsPlayer(user, RivalRebelsTeam.NONE, RivalRebelsClass.NONE, RivalRebelsRank.REGULAR, RivalRebels.resetMax));
	}
	
	public void clearVotes()
	{
		for (int i = 0; i < size; i++)
		{
			list[i].voted = false;
		}
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		size = buf.readInt();
		list = new RivalRebelsPlayer[size];
		for (int i = 0; i < size; i++) list[i] = new RivalRebelsPlayer(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(size);
		for (int i = 0; i < size; i++) list[i].toBytes(buf);
	}
	
	public static class Handler implements IMessageHandler<RivalRebelsPlayerList, IMessage>
	{
		@Override
		public IMessage onMessage(RivalRebelsPlayerList m, MessageContext ctx)
		{
			RivalRebels.round.rrplayerlist = m;
			return null;
		}
	}
	
	public RivalRebelsPlayer[] getArray()
	{
		return list;
	}
}
