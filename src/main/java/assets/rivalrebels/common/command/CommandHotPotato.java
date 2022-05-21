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
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.entity.EntityHotPotato;
import assets.rivalrebels.common.entity.EntityRhodes;
import assets.rivalrebels.common.entity.EntityTsar;

public class CommandHotPotato extends CommandBase
{
	public static int x = -1;
	public static int y = -1;
	public static int z = -1;
	public static World world = null;
	public static boolean roundinprogress = false;
	@Override
	public String getCommandName()
	{
		return "rrhotpotato";
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
		if (world == null)
		{
			sender.addChatMessage(new ChatComponentText("§cPlace a jump block and use pliers on it to set the hot potato drop point."));
			return;
		}
		if (array.length == 1)
		{
			String str = array[0];
			if ("stop".equals(str))
			{
				roundinprogress = false;
				sender.addChatMessage(new ChatComponentText("§cRound stopped."));
				return;
			}
			else
			{
				if (roundinprogress)
				{
					sender.addChatMessage(new ChatComponentText("§cRound already in progress! Do /rrhotpotato stop to end the current round."));
					return;
				}
				int n = Integer.parseInt(array[0]);
				sender.addChatMessage(new ChatComponentText("§cLet the Hot Potato games begin! " + n + " rounds."));
				EntityHotPotato tsar = new EntityHotPotato(world,x,y,z,n);
				world.spawnEntityInWorld(tsar);
				roundinprogress = true;
				return;
			}
		}
		sender.addChatMessage(new ChatComponentText("§cUsage: /rrhotpotato [number of rounds]"));
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