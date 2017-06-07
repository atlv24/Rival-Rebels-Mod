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
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.packet.PacketDispatcher;
import assets.rivalrebels.common.round.RivalRebelsClass;
import assets.rivalrebels.common.round.RivalRebelsPlayer;
import assets.rivalrebels.common.round.RivalRebelsRank;
import assets.rivalrebels.common.round.RivalRebelsTeam;

public class CommandPassword extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "rr";
	}
	
	@Override
	public String getCommandUsage(ICommandSender par1ICommandSender)
	{
		return "/" + getCommandName() + " <code> [player]";
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
	
	String[] rhashes = new String[]{
			"23742137371982715120014159120241255637172",
			"1518918615824625494170109603025017352201241"
	};
	String[] ohashes = new String[]{
			"127254246888283236291831726971211129135192",
			"17314923891217222431372172462419922385201191"
	};
	String[] lhashes = new String[]{
			"612401057716617559272422511992314614422575",
			"99188249382921446717719913013762206120"
	};
	String[] shashes = new String[]{
			"222152281681152820718419502273648223209",
			"107170188164102246158207236028166217204217177"
	};
	
	@Override
	public void processCommand(ICommandSender sender, String[] array)
	{
		ChatComponentText message = new ChatComponentText("nope.");
		if (array.length == 0)
		{
			sender.addChatMessage(message);
			return;
		}
		EntityPlayer person = getCommandSenderAsPlayer(sender);
		String code = array[0];
		String encrypted = encrypt(code);
		
		System.out.println(encrypted);
		RivalRebelsRank rank = RivalRebelsRank.REGULAR;
		if (rhashes[0].equals(encrypted)||rhashes[1].equals(encrypted))
		{
			rank = RivalRebelsRank.REBEL;
			message = new ChatComponentText("Welcome, rebel!");
		}
		else if (ohashes[0].equals(encrypted)||ohashes[1].equals(encrypted))
		{
			rank = RivalRebelsRank.OFFICER;
			message = new ChatComponentText("Welcome, officer!");
		}
		else if (lhashes[0].equals(encrypted)||lhashes[1].equals(encrypted))
		{
			rank = RivalRebelsRank.LEADER;
			message = new ChatComponentText("Welcome, leader!");
		}
		else if (shashes[0].equals(encrypted)||shashes[1].equals(encrypted))
		{
			rank = RivalRebelsRank.REP;
			message = new ChatComponentText("Welcome, representative!");
		}
		RivalRebelsPlayer p = RivalRebels.round.rrplayerlist.getForName(person.getCommandSenderName());
		if (p.rrrank != rank || rank == RivalRebelsRank.REGULAR)
		{
			p.rrrank = rank;
			RivalRebelsSoundPlayer.playSound(person, 28, rank.snf);
			PacketDispatcher.packetsys.sendToAll(RivalRebels.round.rrplayerlist);
			sender.addChatMessage(message);
		}
	}
	
	private String getString(byte[] bytes)
	{
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++)
		{
			byte b = bytes[i];
			sb.append((0x00FF & b));
		}
		return sb.toString();
	}
	
	public String encrypt(String source)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(source.getBytes());
			return getString(bytes);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
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