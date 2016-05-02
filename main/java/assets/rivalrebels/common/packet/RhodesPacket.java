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

import java.util.Iterator;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import assets.rivalrebels.common.core.FileRW;
import assets.rivalrebels.common.entity.EntityGore;
import assets.rivalrebels.common.entity.EntityRhodes;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class RhodesPacket implements IMessage
{
	int		id			= 0;
	boolean	fire		= false;
	boolean forcefield = false;
	boolean plasma = false;
	float scale = 0;
	float headyaw = 0;
	float headpitch = 0;
	float leftarmyaw = 0;
	float leftarmpitch = 0;
	float rightarmyaw = 0;
	float rightarmpitch = 0;
	float leftthighpitch = 0;
	float rightthighpitch = 0;
	float leftshinpitch = 0;
	float rightshinpitch = 0;
	int health;
	byte laserOn;
	byte colorType;
	int b2energy;
	int riderid;
	int pass1id;
	int pass2id;
	int rocketcount;
	int energy;
	int flamecount;
	int nukecount;
	public float bodyyaw;
	String texloc;
	int texfolder;
	
	public RhodesPacket()
	{
	}
	
	public RhodesPacket(EntityRhodes er)
	{
		id = er.getEntityId();
		fire = er.fire;
		plasma = er.plasma;
		bodyyaw         = er.bodyyaw;
		headyaw         = er.headyaw         ;
		headpitch       = er.headpitch       ;
		leftarmyaw      = er.leftarmyaw      ;
		leftarmpitch    = er.leftarmpitch    ;
		rightarmyaw     = er.rightarmyaw     ;
		rightarmpitch   = er.rightarmpitch   ;
		leftthighpitch  = er.leftthighpitch  ;
		rightthighpitch = er.rightthighpitch ;
		leftshinpitch   = er.leftshinpitch   ;
		rightshinpitch  = er.rightshinpitch  ;
		health = er.health;
		laserOn = er.laserOn;
		forcefield = er.forcefield;
		colorType = er.colorType;
		b2energy = er.b2energy;
		riderid = er.rider != null ? er.rider.getEntityId() : -1;
		pass1id = er.passenger1 != null ? er.passenger1.getEntityId() : -1;
		pass2id = er.passenger2 != null ? er.passenger2.getEntityId() : -1;
		rocketcount = er.rocketcount;
		energy = er.energy;
		flamecount = er.flamecount;
		nukecount = er.nukecount;
		texloc = er.itexloc;
		texfolder = er.itexfolder;
		scale = er.scale;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		id = buf.readInt();
		fire = buf.readBoolean();
		plasma = buf.readBoolean();
		forcefield = buf.readBoolean();
		bodyyaw         = buf.readShort() * 0.00549324788281f; //360/65535
		headyaw         = buf.readShort() * 0.00549324788281f;
		headpitch       = buf.readShort() * 0.00549324788281f;
		leftarmyaw      = buf.readShort() * 0.00549324788281f;
		leftarmpitch    = buf.readShort() * 0.00549324788281f;
		rightarmyaw     = buf.readShort() * 0.00549324788281f;
		rightarmpitch   = buf.readShort() * 0.00549324788281f;
		leftthighpitch  = buf.readShort() * 0.00549324788281f;
		rightthighpitch = buf.readShort() * 0.00549324788281f;
		leftshinpitch   = buf.readShort() * 0.00549324788281f;
		rightshinpitch  = buf.readShort() * 0.00549324788281f;
		health = buf.readShort();
		laserOn = buf.readByte();
		colorType = buf.readByte();
		b2energy = buf.readShort();
		riderid = buf.readInt();
		pass1id = buf.readInt();
		pass2id = buf.readInt();
		scale = buf.readFloat();
		rocketcount = buf.readShort();
		energy = buf.readShort();
		flamecount = buf.readShort();
		nukecount = buf.readByte();
		texfolder = buf.readByte();
		if (texfolder != 0)
		{
			byte[] dst = new byte[texfolder%10];
			buf.readBytes(dst);
			texloc = FileRW.getStringBytes(dst);
			texfolder -= texfolder%10;
			texfolder /= 10;
		}
		else
		{
			texfolder = -1;
		}
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(id);
		buf.writeBoolean(fire);
		buf.writeBoolean(plasma);
		buf.writeBoolean(forcefield);
		buf.writeShort((short)(bodyyaw         * 182.04166666666667f)); //65535/360
		buf.writeShort((short)(headyaw         * 182.04166666666667f));
		buf.writeShort((short)(headpitch       * 182.04166666666667f));
		buf.writeShort((short)(leftarmyaw      * 182.04166666666667f));
		buf.writeShort((short)(leftarmpitch    * 182.04166666666667f));
		buf.writeShort((short)(rightarmyaw     * 182.04166666666667f));
		buf.writeShort((short)(rightarmpitch   * 182.04166666666667f));
		buf.writeShort((short)(leftthighpitch  * 182.04166666666667f));
		buf.writeShort((short)(rightthighpitch * 182.04166666666667f));
		buf.writeShort((short)(leftshinpitch   * 182.04166666666667f));
		buf.writeShort((short)(rightshinpitch  * 182.04166666666667f));
		buf.writeShort(health);
		buf.writeByte(laserOn);
		buf.writeByte(colorType);
		buf.writeShort(b2energy);
		buf.writeInt(riderid);
		buf.writeInt(pass1id);
		buf.writeInt(pass2id);
		buf.writeFloat(scale);
		buf.writeShort(rocketcount);
		buf.writeShort(energy);
		buf.writeShort(flamecount);
		buf.writeByte(nukecount);
		if (texfolder == -1)
		{
			buf.writeByte(0);
		}
		else
		{
			buf.writeByte(texfolder*10+texloc.length());
			buf.writeBytes(FileRW.getBytesString(texloc));
		}
	}
	
	public static class Handler implements IMessageHandler<RhodesPacket, IMessage>
	{
		@Override
		public IMessage onMessage(RhodesPacket m, MessageContext ctx)
		{
			Entity e = Minecraft.getMinecraft().theWorld.getEntityByID(m.id);
			if (e instanceof EntityRhodes)
			{
				EntityRhodes er = (EntityRhodes) e;
				er.lastbodyyaw = er.bodyyaw;
				er.lastheadyaw = er.headyaw;
				er.lastheadpitch = er.headpitch;
				er.lastleftarmyaw = er.leftarmyaw;
				er.lastleftarmpitch = er.leftarmpitch;
				er.lastrightarmyaw = er.rightarmyaw;
				er.lastrightarmpitch = er.rightarmpitch;
				er.lastleftthighpitch = er.leftthighpitch;
				er.lastrightthighpitch = er.rightthighpitch;
				er.lastleftshinpitch = er.leftshinpitch;
				er.lastrightshinpitch = er.rightshinpitch;
				er.fire = m.fire;
				er.plasma = m.plasma;
				if (Math.abs(er.bodyyaw-m.bodyyaw)>90)
				{
					er.lastbodyyaw = m.bodyyaw;
				}
				if (Math.abs(er.rightarmyaw-m.rightarmyaw)>90)
				{
					er.lastrightarmyaw = m.rightarmyaw;
				}
				if (Math.abs(er.leftarmyaw-m.leftarmyaw)>90)
				{
					er.lastleftarmyaw = m.leftarmyaw;
				}
				er.bodyyaw 		   = m.bodyyaw         ;
				er.headyaw         = m.headyaw         ;
				er.headpitch       = m.headpitch       ;
				er.leftarmyaw      = m.leftarmyaw      ;
				er.leftarmpitch    = m.leftarmpitch    ;
				er.rightarmyaw     = m.rightarmyaw     ;
				er.rightarmpitch   = m.rightarmpitch   ;
				er.leftthighpitch  = m.leftthighpitch  ;
				er.rightthighpitch = m.rightthighpitch ;
				er.leftshinpitch   = m.leftshinpitch   ;
				er.rightshinpitch  = m.rightshinpitch  ;
				er.health = m.health;
				er.laserOn = m.laserOn;
				er.forcefield = m.forcefield;
				er.colorType = m.colorType;
				er.b2energy = m.b2energy;
				er.ticksSinceLastPacket = 0;
				er.rocketcount = m.rocketcount;
				er.energy = m.energy;
				er.flamecount = m.flamecount;
				er.nukecount = m.nukecount;
				er.itexloc = m.texloc;
				er.itexfolder = m.texfolder;
				er.scale = m.scale;
				if (er.health <= 0 && er.rider != null)
				{
					er.rider.setPosition(er.posX+5, er.posY-12, er.posZ);
					er.rider.capabilities.disableDamage = false;
					er.rider = null;
				}
				else
				{
					er.rider = m.riderid == -1 ? null : (EntityPlayer) er.worldObj.getEntityByID(m.riderid);
					er.passenger1 = m.pass1id == -1 ? null : (EntityPlayer) er.worldObj.getEntityByID(m.pass1id);
					er.passenger2 = m.pass2id == -1 ? null : (EntityPlayer) er.worldObj.getEntityByID(m.pass2id);
				}
			}
			return null;
		}
	}
}