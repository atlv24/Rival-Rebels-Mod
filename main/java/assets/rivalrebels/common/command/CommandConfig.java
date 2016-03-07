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
package assets.rivalrebels.common.command;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.entity.EntityRhodes;

public class CommandConfig extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "rrconfig";
	}
	
	@Override
	public String getCommandUsage(ICommandSender par1ICommandSender)
	{
		return "/" + getCommandName();
	}
	
	/**
	 * Return the required permission level for this command.
	 */
	@Override
	public int getRequiredPermissionLevel()
	{
		return 3;
	}
	
	@Override
	public List getCommandAliases()
	{
		return null;
	}
	
	@Override
	public void processCommand(ICommandSender sender, String[] array)
	{
		if (array.length == 2)
		{
			String str = array[0];
			if (str.equals("nuketime"))
			{
				String str2 = array[1];
				int i = Integer.parseInt(str2);
				if (i < 1) i = 1;
				RivalRebels.nuclearBombCountdown = i;
				sender.addChatMessage(new ChatComponentText("§cnuketime has been set to " + i));
				return;
			}
		}
		sender.addChatMessage(new ChatComponentText("§cUsage: /rrconfig nuketime 1"));
	}
	/**
     * Adds the strings available in this command to the given list of tab completion options.
     */
    public List addTabCompletionOptions(ICommandSender p, String[] s)
    {
    	List l = new ArrayList();
		l.add("nuketime");
		return l;
    }
}