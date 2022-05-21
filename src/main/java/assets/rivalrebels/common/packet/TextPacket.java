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
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import assets.rivalrebels.common.core.FileRW;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class TextPacket implements IMessage
{
	String text;
	
	public TextPacket()
	{
		
	}
	
	public TextPacket(String t)
	{
		text = t;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		byte[] dst = new byte[buf.readInt()];
		buf.readBytes(dst);
		text = FileRW.getStringBytes(dst);
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(text.length());
		buf.writeBytes(FileRW.getBytesString(text));
	}
	
	public static class Handler implements IMessageHandler<TextPacket, IMessage>
	{
		@Override
		public IMessage onMessage(TextPacket m, MessageContext ctx)
		{
			if (m.text.startsWith("-t"))
			{
				String[] str = m.text.substring(2, m.text.length()).split("\n");
				for (int i = 0; i < str.length; i++) Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(str[i]));
			}
			else
			{
				String[] s = m.text.split(" ");
				StringBuilder strb = new StringBuilder();
				for (int i = 0; i < s.length; i++)
				{
					strb.append(StatCollector.translateToLocal(s[i]));
					strb.append(" ");
				}
				Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(strb.toString()));
			}
			return null;
		}
	}
}
