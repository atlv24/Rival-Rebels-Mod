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
import assets.rivalrebels.client.renderentity.RenderAntimatterBombBlast;
import assets.rivalrebels.common.entity.EntityB2Spirit;
import assets.rivalrebels.common.entity.EntityRhodes;
import assets.rivalrebels.common.explosion.TsarBomba;
import assets.rivalrebels.common.item.weapon.ItemRoda;
import assets.rivalrebels.common.tileentity.TileEntityReciever;
import assets.rivalrebels.common.tileentity.TileEntityTsarBomba;

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
			if (str.equals("b2trash"))
			{
				String str2 = array[1];
				boolean i = Boolean.parseBoolean(str2);
				EntityB2Spirit.trash = i;
				sender.addChatMessage(new ChatComponentText("§cb2trash has been set to " + i));
				return;
			}
			if (str.equals("flash"))
			{
				String str2 = array[1];
				boolean i = Boolean.parseBoolean(str2);
				RivalRebels.antimatterFlash = i;
				sender.addChatMessage(new ChatComponentText("§cflash has been set to " + i));
				return;
			}
			if (str.equals("bignuke"))
			{
				String str2 = array[1];
				float i = Float.parseFloat(str2);
				RivalRebels.nukeScale = i;
				sender.addChatMessage(new ChatComponentText("§cNuke scale is " + i + "x"));
				return;
			}
			if (str.equals("bigshroom"))
			{
				String str2 = array[1];
				float i = Float.parseFloat(str2);
				RivalRebels.shroomScale = i;
				sender.addChatMessage(new ChatComponentText("§cMushroom scale is " + i + "x"));
				return;
			}
			if (str.equals("b2leave"))
			{
				String str2 = array[1];
				boolean i = Boolean.parseBoolean(str2);
				EntityB2Spirit.leave = i;
				sender.addChatMessage(new ChatComponentText("§cb2leave has been set to " + i));
				return;
			}
			if (str.equals("nukepancake"))
			{
				String str2 = array[1];
				if (str2.equals("off"))
				{
					RivalRebels.elevation = true;
					sender.addChatMessage(new ChatComponentText("§cNew Pancake off"));
				}
				else if (str2.equals("on"))
				{
					RivalRebels.elevation = false;
					sender.addChatMessage(new ChatComponentText("§cNew Pancake on"));
				}
				else sender.addChatMessage(new ChatComponentText("§cPlease give a value of either on or off."));
				return;
			}
			if (str.equals("nukedrop"))
			{
				String str2 = array[1];
				if (str2.equals("off"))
				{
					RivalRebels.nukedrop = false;
					sender.addChatMessage(new ChatComponentText("§cNuke drop off"));
				}
				else if (str2.equals("on"))
				{
					RivalRebels.nukedrop = true;
					sender.addChatMessage(new ChatComponentText("§cNuke drop on"));
				}
				else sender.addChatMessage(new ChatComponentText("§cPlease give a value of either on or off."));
				return;
			}
			if (str.equals("b2chance"))
			{
				String str2 = array[1];
				if (str2.equals("off"))
				{
					EntityB2Spirit.randchance = false;
					sender.addChatMessage(new ChatComponentText("§cB2 chance off"));
				}
				else if (str2.equals("on"))
				{
					EntityB2Spirit.randchance = true;
					sender.addChatMessage(new ChatComponentText("§cB2 chance on"));
				}
				else sender.addChatMessage(new ChatComponentText("§cPlease give a value of either on or off."));
				return;
			}
			if (str.equals("dragon") || str.equals("b2"))
			{
				String str2 = array[1];
				int index = -1;
				for (int i = 0; i < ItemRoda.entities.length; i++)
				{
					if (str2.equals(ItemRoda.entities[i]))
					{
						index = i;
						break;
					}
				}
				if (index != -1)
				{
					if (str.equals("dragon"))
					{
						TileEntityReciever.staticEntityIndex = index;
					}
					else if (str.equals("b2"))
					{
						EntityB2Spirit.staticEntityIndex = index;
					}
				}
				else
				{
					sender.addChatMessage(new ChatComponentText("§cPlease give a value of " + String.join(", ", ItemRoda.entities) + "."));
				}
				return;
			}
		}
		sender.addChatMessage(new ChatComponentText("§cUsage: /rrconfig nuketime|nukedrop|bignuke|bigshroom|flash|b2chance|nukepancake|dragon|b2"));
	}
	/**
     * Adds the strings available in this command to the given list of tab completion options.
     */
    public List addTabCompletionOptions(ICommandSender p, String[] s)
    {
    	List l = new ArrayList();
		l.add("nuketime");
		l.add("nukedrop");
		l.add("nukepancake");
		l.add("dragon");
		l.add("b2");
		return l;
    }
}