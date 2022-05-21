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

import java.io.File;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class InspectPacket implements IMessage
{
	public InspectPacket()
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
	
	public static class Handler implements IMessageHandler<InspectPacket, IMessage>
	{
		@Override
		public IMessage onMessage(InspectPacket m, MessageContext ctx)
		{
			StringBuilder str = new StringBuilder();//"-t§e======[§6RRInspect§e]======\n");
			str.append(Minecraft.getMinecraft().thePlayer.getCommandSenderName());
//			str.append(" has:\n");
			File file = Minecraft.getMinecraft().mcDataDir;
			String[] coremods = new File(file, "coremods").list();
			String[] mods = new File(file, "mods").list();
			if (coremods != null) for (int i = 0; i < coremods.length; i ++)
			{
				str.append(",");
				str.append(coremods[i]);
			}
			if (mods != null) for (int i = 0; i < mods.length; i ++)
			{
				str.append(",");
				str.append(mods[i]);
			}
			List modlist = Loader.instance().getActiveModList();
			Iterator iter = modlist.iterator();
			while (iter.hasNext())
			{
				ModContainer c = ((ModContainer) iter.next());
//				str.append("§6");
				str.append(",");
//				str.append(c.getName());
//				str.append("§e: ");
				str.append(c.getModId());
				//str.append("@");
//				str.append(" v");
				//str.append(c.getVersion());
//				if (iter.hasNext()) str.append(",");
			}
			String s = str.toString();
			
			return new ModListPacket(s);
		}
	}
}
