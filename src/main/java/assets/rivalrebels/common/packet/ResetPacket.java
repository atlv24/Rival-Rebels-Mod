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

public class ResetPacket implements IMessage
{
	public ResetPacket()
	{
		
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
	}
	
	public static class Handler implements IMessageHandler<ResetPacket, IMessage>
	{
		@Override
		public IMessage onMessage(ResetPacket m, MessageContext ctx)
		{
			RivalRebelsPlayer p = RivalRebels.round.rrplayerlist.getForName(ctx.getServerHandler().playerEntity.getCommandSenderName());
			if (!p.isreset && p.resets > 0)
			{
				p.isreset = true;
				p.resets--;
				ctx.getServerHandler().playerEntity.inventory.clearInventory(null, -1);
				PacketDispatcher.packetsys.sendToAll(RivalRebels.round.rrplayerlist);
			}
			return null;
		}
	}
}
