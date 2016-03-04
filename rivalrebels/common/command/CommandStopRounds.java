package assets.rivalrebels.common.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import assets.rivalrebels.RivalRebels;

public class CommandStopRounds extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "rrstopround";
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
		sender.addChatMessage(new ChatComponentText("The current round has been successfully stopped."));
	}
}