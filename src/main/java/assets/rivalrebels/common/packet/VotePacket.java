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
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.round.RivalRebelsPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class VotePacket implements IMessage
{
	public boolean newround;
	
	public VotePacket()
	{
		
	}
	
	public VotePacket(boolean vote)
	{
		newround = vote;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		newround = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeBoolean(newround);
	}
	
	public static class Handler implements IMessageHandler<VotePacket, IMessage>
	{
		@Override
		public IMessage onMessage(VotePacket m, MessageContext ctx)
		{
			RivalRebelsPlayer p = RivalRebels.round.rrplayerlist.getForName(ctx.getServerHandler().playerEntity.getCommandSenderName());
			if (!p.voted)
			{
				p.voted = true;
				if (m.newround) RivalRebels.round.newBattleVotes++;
				else RivalRebels.round.waitVotes++;
			}
			return null;
		}
	}
}
