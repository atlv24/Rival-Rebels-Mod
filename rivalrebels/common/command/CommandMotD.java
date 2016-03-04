package assets.rivalrebels.common.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import assets.rivalrebels.RivalRebels;

public class CommandMotD extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "rrmotd";
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
		if (array.length==0)sender.addChatMessage(new ChatComponentText(RivalRebels.round.getMotD()));
		else RivalRebels.round.setMotD(array);
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