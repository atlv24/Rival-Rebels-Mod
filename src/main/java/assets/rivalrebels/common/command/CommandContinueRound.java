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
import net.minecraft.util.ChatComponentText;
import assets.rivalrebels.RivalRebels;

public class CommandContinueRound extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "rrstartround";
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
		RivalRebels.round.stopRounds();
		RivalRebels.round.newRound();
		sender.addChatMessage(new ChatComponentText("The current round has been successfully started."));
	}
}