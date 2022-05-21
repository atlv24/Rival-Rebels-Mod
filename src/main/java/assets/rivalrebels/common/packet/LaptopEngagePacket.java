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
package assets.rivalrebels.common.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.entity.EntityB2Spirit;
import assets.rivalrebels.common.round.RivalRebelsClass;
import assets.rivalrebels.common.round.RivalRebelsPlayer;
import assets.rivalrebels.common.round.RivalRebelsRank;
import assets.rivalrebels.common.round.RivalRebelsTeam;
import assets.rivalrebels.common.tileentity.TileEntityLaptop;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LaptopEngagePacket implements IMessage
{
	int tx = -1;
	int ty = -1;
	int tz = -1;
	int lx = -1;
	int ly = -1;
	int lz = -1;
	boolean c = false;
	
	public LaptopEngagePacket()
	{
		
	}
	
	public LaptopEngagePacket(int tX, int tY, int tZ, int lX, int lY, int lZ, boolean carpet)
	{
		tx = tX;
		ty = tY;
		tz = tZ;
		lx = lX;
		ly = lY;
		lz = lZ;
		c = carpet;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		tx = buf.readInt();
		ty = buf.readInt();
		tz = buf.readInt();
		lx = buf.readInt();
		ly = buf.readInt();
		lz = buf.readInt();
		c = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(tx);
		buf.writeInt(ty);
		buf.writeInt(tz);
		buf.writeInt(lx);
		buf.writeInt(ly);
		buf.writeInt(lz);
		buf.writeBoolean(c);
	}
	
	public static class Handler implements IMessageHandler<LaptopEngagePacket, IMessage>
	{
		@Override
		public IMessage onMessage(LaptopEngagePacket m, MessageContext ctx)
		{
			EntityPlayer player = ctx.getServerHandler().playerEntity;
			World world = player.worldObj;
			if (player.getDistanceSq(m.lx, m.ly, m.lz) < 100)
			{
				TileEntity te = world.getTileEntity(m.lx, m.ly, m.lz);
				if (te != null && te instanceof TileEntityLaptop)
				{
					TileEntityLaptop tel = ((TileEntityLaptop)te);
					if (!m.c && tel.b2spirit > 0)
					{
						int XX = 11;
						int ZZ = 10;
						if (tel.rrteam == RivalRebelsTeam.OMEGA)
						{
							XX = (m.tx - RivalRebels.round.oObjx);
							ZZ = (m.tz - RivalRebels.round.oObjz);
						}
						if (tel.rrteam == RivalRebelsTeam.SIGMA)
						{
							XX = (m.tx - RivalRebels.round.sObjx);
							ZZ = (m.tz - RivalRebels.round.sObjz);
						}
						int xx = m.tx-m.lx;
						int zz = m.tz-m.lz;
						if (xx*xx+zz*zz > 625 && XX*XX+ZZ*ZZ > 200)
						{
							tel.b2spirit--;
							world.spawnEntityInWorld(new EntityB2Spirit(world, m.tx, m.ty, m.tz, player.posX, player.posY, player.posZ, false, player.isSneaking()));
						}
					}
					if (m.c && tel.b2carpet > 0)
					{
						int XX = 11;
						int ZZ = 10;
						int xx = m.tx-m.lx;
						int zz = m.tz-m.lz;
						if (xx*xx+zz*zz > 625 && XX*XX+ZZ*ZZ > 200)
						{
							tel.b2carpet--;
							world.spawnEntityInWorld(new EntityB2Spirit(world, m.tx, m.ty, m.tz, player.posX, player.posY, player.posZ, true, false));
						}
					}
				}
			}
			return null;
		}
	}
}
