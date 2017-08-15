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

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;

public class CommandPlaySound extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "rrsoundsystem";
	}
	
	@Override
	public String getCommandUsage(ICommandSender par1ICommandSender)
	{
		return "/" + getCommandName();
	}
	
	@Override
	public List getCommandAliases()
	{
		return null;
	}
	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender par1)
	{
		return true;
	}
	
	@Override
	public void processCommand(ICommandSender sender, String[] array)
	{
		if (array.length == 4)
		{
			int dir = 0;
			int num = 0;
			float vol = 0f;
			float pit = 0f;
			try
			{
				dir = Integer.parseInt(array[0].trim());
				num = Integer.parseInt(array[1].trim());
				vol = Float.parseFloat(array[2].trim());
				pit = Float.parseFloat(array[3].trim());
			}
			catch (Exception E)
			{
				sender.addChatMessage(new ChatComponentText("No!"));
			}
			ChunkCoordinates cc = sender.getPlayerCoordinates();
			RivalRebelsSoundPlayer.playSound(sender.getEntityWorld(), dir, num, cc.posX, cc.posY, cc.posZ, vol, pit);
		}
		else
		{
			sender.addChatMessage(new ChatComponentText("No!"));
		}
	}
	
	/**
	 * Adds the strings available in this command to the given list of tab completion options.
	 */
	@Override
	public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
	{
		return null;
	}
}