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
import net.minecraft.server.MinecraftServer;
import assets.rivalrebels.common.packet.InspectPacket;
import assets.rivalrebels.common.packet.ModListPacket;
import assets.rivalrebels.common.packet.PacketDispatcher;

public class CommandInspect extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "rrinspect";
	}
	
	@Override
	public String getCommandUsage(ICommandSender par1ICommandSender)
	{
		return "/" + getCommandName() + " <player>";
	}
	
	@Override
	public List getCommandAliases()
	{
		return null;
	}
	
	@Override
	public int getRequiredPermissionLevel()
	{
		return 3;
	}
	
	@Override
	public void processCommand(ICommandSender sender, String[] array)
	{
		ModListPacket.asker = getCommandSenderAsPlayer(sender);
		PacketDispatcher.packetsys.sendTo(new InspectPacket(), getPlayer(sender, array[0]));
		//RivalRebelsServerPacketHandler.sendPacket(21, sender.getCommandSenderName().equals("Server") ? -1 : getCommandSenderAsPlayer(sender).getEntityId(), getPlayer(sender, array[0]));
	}
	
	/**
	 * Adds the strings available in this command to the given list of tab completion options.
	 */
	@Override
	public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
	{
		return par2ArrayOfStr.length >= 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, MinecraftServer.getServer().getAllUsernames()) : null;
	}
}