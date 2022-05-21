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
package assets.rivalrebels.common.round;

import io.netty.buffer.ByteBuf;
import net.minecraft.server.MinecraftServer;
import assets.rivalrebels.common.core.FileRW;

public class RivalRebelsPlayer
{
	public String			username;
	public RivalRebelsClass	rrclass	= RivalRebelsClass.NONE;
	public RivalRebelsTeam	rrteam	= RivalRebelsTeam.NONE;
	public RivalRebelsRank	rrrank	= RivalRebelsRank.REGULAR;
	public int				resets	= -1;
	public boolean			isreset	= true;
	public boolean			voted	= false;
	
	public RivalRebelsPlayer(String user, RivalRebelsTeam rteam, RivalRebelsClass rclass, RivalRebelsRank rrank, int r)
	{
		username = user;
		rrteam = rteam;
		rrclass = rclass;
		rrrank = rrank;
		resets = r;
	}
	
	public RivalRebelsPlayer(ByteBuf buf)
	{
		fromBytes(buf);
	}
	
	public boolean equals(RivalRebelsPlayer o)
	{
		if (username.equals(o.username)) return true;
		return false;
	}
	
	public void reset()
	{
		resets++;
		isreset = true;
	}
	
	public void clear()
	{
		rrclass = RivalRebelsClass.NONE;
		isreset = true;
		resets = -1;
	}
	
	public void clearTeam()
	{
		rrclass = RivalRebelsClass.NONE;
		rrteam = RivalRebelsTeam.NONE;
		isreset = true;
		resets = -1;
		MinecraftServer server = MinecraftServer.getServer();
		if (server != null)
		{
			//server.handleRConCommand("/scoreboard teams leave Omega " + username);
			//server.handleRConCommand("/scoreboard teams leave Sigma " + username);
		}
	}
	
	public void toBytes(ByteBuf buf)
	{
		buf.writeByte(rrclass.id);
		buf.writeByte(rrteam.id);
		buf.writeByte(rrrank.id);
		buf.writeByte(resets);
		buf.writeByte(isreset?'t':'f');
		buf.writeByte(username.length());
		buf.writeBytes(FileRW.getBytesString(username));
	}
	
	public void fromBytes(ByteBuf buf)
	{
		rrclass = RivalRebelsClass.getForID(buf.readByte());
		rrteam = RivalRebelsTeam.getForID(buf.readByte());
		rrrank = RivalRebelsRank.getForID(buf.readByte());
		resets = buf.readByte();
		isreset = buf.readByte()=='t';
		byte[] b = new byte[buf.readByte()];
		buf.readBytes(b);
		username = FileRW.getStringBytes(b);
	}
}
