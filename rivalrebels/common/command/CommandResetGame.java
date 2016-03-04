package assets.rivalrebels.common.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.packet.PacketDispatcher;
import assets.rivalrebels.common.round.RivalRebelsPlayer;

public class CommandResetGame extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "rrreset";
	}
	
	@Override
	public String getCommandUsage(ICommandSender par1ICommandSender)
	{
		return "/" + getCommandName() + " <player>";
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
		EntityPlayer player = getCommandSenderAsPlayer(sender);
		if (array.length == 1 && array[0].length() > 0)
		{
			if (array[0].equals("all"))
			{
				RivalRebels.round.rrplayerlist.clearTeam();
				PacketDispatcher.packetsys.sendToAll(RivalRebels.round.rrplayerlist);
				player.addChatMessage(new ChatComponentText("§7All players have been reset."));
			}
			else if (RivalRebels.round.rrplayerlist.contains(array[0]))
			{
				RivalRebels.round.rrplayerlist.getForName(array[0]).clearTeam();
				PacketDispatcher.packetsys.sendToAll(RivalRebels.round.rrplayerlist);
				player.addChatMessage(new ChatComponentText("§7Player successfully reset."));
			}
			else
			{
				player.addChatMessage(new ChatComponentText("§7No player by that name."));
			}
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