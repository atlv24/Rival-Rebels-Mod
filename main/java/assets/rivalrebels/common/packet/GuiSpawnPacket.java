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
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.round.RivalRebelsClass;
import assets.rivalrebels.common.round.RivalRebelsPlayer;
import assets.rivalrebels.common.round.RivalRebelsRank;
import assets.rivalrebels.common.round.RivalRebelsTeam;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GuiSpawnPacket implements IMessage
{
	@Override
	public void fromBytes(ByteBuf buf)
	{
		
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		
	}
	
	public static class Handler implements IMessageHandler<GuiSpawnPacket, IMessage>
	{
		@Override
		public IMessage onMessage(GuiSpawnPacket m, MessageContext ctx)
		{
			if (RivalRebels.round.rrplayerlist.getForName(Minecraft.getMinecraft().thePlayer.getCommandSenderName()).isreset) RivalRebels.proxy.guiClass();
			else RivalRebels.proxy.guiSpawn();
			return null;
		}
	}
}
