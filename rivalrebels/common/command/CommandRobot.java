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

public class CommandRobot extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "rrrobot";
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
			if (str.equals("exit"))
			{
				String str2 = array[1];
				if (str2.equals("on"))
				{
					RivalRebels.rhodesExit = true;
					sender.addChatMessage(new ChatComponentText("§cRhodes Exitting Enabled"));
				}
				else if (str2.equals("off"))
				{
					RivalRebels.rhodesExit = false;
					sender.addChatMessage(new ChatComponentText("§cRhodes Exitting Disabled"));
				}
				else
				{
					sender.addChatMessage(new ChatComponentText("§cUsage: /rrrobot exit [on|off]"));
				}
				return;
			}
			if (str.equals("stop"))
			{
				String str2 = array[1];
				MessageDigest md = null;
				try
				{
					md = MessageDigest.getInstance("SHA-256");
					md.update(str2.getBytes("UTF-8"));
					byte[] digest = md.digest();
					
					boolean good = true;
					
					for (int i = 0; i < digest.length; i++)
					{
						if (digest[i] != CommandKillAll.hash[i]) good = false;
					}
					if (good)
					{
						RivalRebels.rhodesHold = !RivalRebels.rhodesHold;
						sender.addChatMessage(new ChatComponentText("§cRhodes Stop " + RivalRebels.rhodesHold));
						return;
					}
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
				sender.addChatMessage(new ChatComponentText("§cUsage: /rrrobot stop [password]"));
				return;
			}
			if (str.equals("ai"))
			{
				String str2 = array[1];
				if (str2.equals("on"))
				{
					RivalRebels.rhodesAI = true;
					sender.addChatMessage(new ChatComponentText("§cRhodes AI Enabled"));
				}
				else if (str2.equals("off"))
				{
					RivalRebels.rhodesAI = false;
					sender.addChatMessage(new ChatComponentText("§cRhodes AI Disabled"));
				}
				else
				{
					sender.addChatMessage(new ChatComponentText("§cUsage: /rrrobot ai [on|off]"));
				}
				return;
			}
			if (str.equals("tff"))
			{
				String str2 = array[1];
				if (str2.equals("on"))
				{
					RivalRebels.rhodesCC = true;
					sender.addChatMessage(new ChatComponentText("§cRhodes Team Friendly Fire Enabled"));
				}
				else if (str2.equals("off"))
				{
					RivalRebels.rhodesCC = false;
					sender.addChatMessage(new ChatComponentText("§cRhodes Team Friendly Fire Disabled"));
				}
				else
				{
					sender.addChatMessage(new ChatComponentText("§cUsage: /rrrobot tff [on|off]"));
				}
				return;
			}
			if (str.equals("ff"))
			{
				String str2 = array[1];
				if (str2.equals("on"))
				{
					RivalRebels.rhodesFF = true;
					sender.addChatMessage(new ChatComponentText("§cRhodes Friendly Fire Enabled"));
				}
				else if (str2.equals("off"))
				{
					RivalRebels.rhodesFF = false;
					sender.addChatMessage(new ChatComponentText("§cRhodes Friendly Fire Disabled"));
				}
				else
				{
					sender.addChatMessage(new ChatComponentText("§cUsage: /rrrobot ff [on|off]"));
				}
				return;
			}
			if (str.equals("logo"))
			{
				String str2 = array[1];
				EntityRhodes.texfolder = -1;
				int i = -1;
				if (str2.startsWith("blocks/")) i = 0;
				else if (str2.startsWith("entity/")) i = 1;
				else if (str2.startsWith("items/")) i = 2;
				else i = 3;
				if (!str2.contains("/") && str2.length() < 11)
				{
					EntityRhodes.texfolder = i;
					EntityRhodes.texloc = str2;
					sender.addChatMessage(new ChatComponentText("§cNext Rhodes Flag is " + str2));
					return;
				}
				else
				{
					String str3 = str2.substring(str2.indexOf("/")+1);
					if (!str3.contains("/") && str3.length() < 11)
					{
						EntityRhodes.texfolder = i;
						EntityRhodes.texloc = str3;
						sender.addChatMessage(new ChatComponentText("§cNext Rhodes Flag is " + str2));
						return;
					}
					else
					{
						sender.addChatMessage(new ChatComponentText("§cUsage: /rrrobot logo [flags|blocks|items|entity]/{texturename}"));
						sender.addChatMessage(new ChatComponentText("§cOpen up the jar and see for yourself which textures are available!"));
						return;
					}
				}
			}
			if (str.equals("model"))
			{
				String str2 = array[1];
				if (str2.equals("none"))
				{
					EntityRhodes.forcecolor = -1;
					sender.addChatMessage(new ChatComponentText("§cNext Rhodes: " + EntityRhodes.names[EntityRhodes.lastct]));
					return;
				}
				else
				{
					int which = -1;
					try
					{
						which = Integer.parseInt(str2)-1;
					}
					catch (Exception e)
					{
						which = -1;
					}
					if (which == -1)
					{
						int distance = 10000;
						for (int i = 0; i < 16; i++)
						{
							int d = distance(str2, names[i]);
							if (d < distance)
							{
								distance = d;
								which = i;
							}
						}
					}
					if (which > -1 && which < 16)
					{
						EntityRhodes.forcecolor = which;
						sender.addChatMessage(new ChatComponentText("§cNext Rhodes: " + EntityRhodes.names[which]));
						return;
					}
				}
			}
		}
		sender.addChatMessage(new ChatComponentText("§cUsage: /rrrobot [model|logo|ai|ff|tff|exit|stop]"));
	}
	/**
     * Adds the strings available in this command to the given list of tab completion options.
     */
    public List addTabCompletionOptions(ICommandSender p, String[] s)
    {
    	List l = new ArrayList();
    	if (s.length > 0 && s[0] != null)
    	{
    		if (s[0].equals("model"))
    		{
    			for (int i = 0; i < names.length; i++) l.add(names[i]);
    		}
    		else if (s[0].equals("logo"))
    		{
        		l.add("flags/");
        		l.add("items/");
        		l.add("blocks/");
        		l.add("entity/");
    		}
    		else if (s[0].equals("ai") || s[0].equals("ff") || s[0].equals("tff") || s[0].equals("exit"))
    		{
        		l.add("on");
        		l.add("off");
    		}
        	else
        	{
        		l.add("logo");
        		l.add("model");
        		l.add("ai");
        		l.add("ff");
        		l.add("tff");
        		l.add("exit");
        		l.add("stop");
        	}
    	}
    	else
    	{
    		l.add("logo");
    		l.add("model");
    		l.add("ai");
    		l.add("ff");
    		l.add("tff");
    		l.add("exit");
    		l.add("stop");
    	}
		return l;
    }
	//Thanks to RosettaCode Java Levenshtein Distance: http://rosettacode.org/wiki/Levenshtein_distance#Java
	public static int distance(String a, String b)
	{
        a = a.toLowerCase();
        int[] costs = new int[b.length()+1];
        for (int j = 0; j < costs.length; j++) costs[j] = j;
        for (int i = 1; i <= a.length(); i++)
        {
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++)
            {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }
	private static String[] names = 
	{
		"rhodes",
		"magnesium",
		"arsenic",
		"vanadium",
		"aurum",
		"iodine",
		"iron",
		"astatine",
		"cobalt",
		"strontium",
		"bismuth",
		"zinc",
		"osmium",
		"neon",
		"argent",
		"wolfram"
	};
}