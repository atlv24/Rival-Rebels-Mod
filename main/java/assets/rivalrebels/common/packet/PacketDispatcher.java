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

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.round.RivalRebelsPlayerList;
import assets.rivalrebels.common.round.RivalRebelsRound;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketDispatcher
{
	public static final SimpleNetworkWrapper	packetsys	= NetworkRegistry.INSTANCE.newSimpleChannel(RivalRebels.MODID);
	
	public static final void registerPackets()
	{
		int packetCount = 0;
		packetsys.registerMessage(EntityGorePacket.Handler.class,		EntityGorePacket.class,			packetCount++, Side.CLIENT);
		packetsys.registerMessage(EntityDebrisPacket.Handler.class,		EntityDebrisPacket.class,		packetCount++, Side.CLIENT);
		packetsys.registerMessage(ItemUpdate.Handler.class,				ItemUpdate.class,				packetCount++, Side.SERVER);
		packetsys.registerMessage(RivalRebelsRound.Handler.class,		RivalRebelsRound.class,			packetCount++, Side.CLIENT);
		packetsys.registerMessage(RivalRebelsPlayerList.Handler.class,	RivalRebelsPlayerList.class,	packetCount++, Side.CLIENT);
		packetsys.registerMessage(GuiSpawnPacket.Handler.class,			GuiSpawnPacket.class,			packetCount++, Side.CLIENT);
		packetsys.registerMessage(JoinTeamPacket.Handler.class,			JoinTeamPacket.class,			packetCount++, Side.SERVER);
		packetsys.registerMessage(ResetPacket.Handler.class,			ResetPacket.class,				packetCount++, Side.SERVER);
		packetsys.registerMessage(TextPacket.Handler.class,				TextPacket.class,				packetCount++, Side.CLIENT);
		packetsys.registerMessage(InspectPacket.Handler.class,			InspectPacket.class,			packetCount++, Side.CLIENT);
		packetsys.registerMessage(ModListPacket.Handler.class,			ModListPacket.class,			packetCount++, Side.SERVER);
		packetsys.registerMessage(VotePacket.Handler.class,				VotePacket.class,				packetCount++, Side.SERVER);
		packetsys.registerMessage(LaptopButtonPacket.Handler.class,		LaptopButtonPacket.class,		packetCount++, Side.SERVER);
		packetsys.registerMessage(LaptopRefreshPacket.Handler.class,	LaptopRefreshPacket.class,		packetCount++, Side.CLIENT);
		packetsys.registerMessage(LaptopEngagePacket.Handler.class,		LaptopEngagePacket.class,		packetCount++, Side.SERVER);
		packetsys.registerMessage(ReactorGUIPacket.Handler.class,		ReactorGUIPacket.class,			packetCount++, Side.SERVER);
		packetsys.registerMessage(ReactorUpdatePacket.Handler.class,	ReactorUpdatePacket.class,		packetCount++, Side.CLIENT);
		packetsys.registerMessage(ADSClosePacket.Handler.class,			ADSClosePacket.class,			packetCount++, Side.SERVER);
		packetsys.registerMessage(ADSWeaponPacket.Handler.class,		ADSWeaponPacket.class,			packetCount++, Side.SERVER);
		packetsys.registerMessage(ADSUpdatePacket.Handler.class,		ADSUpdatePacket.class,			packetCount++, Side.CLIENT);
		packetsys.registerMessage(RhodesPacket.Handler.class,			RhodesPacket.class,				packetCount++, Side.CLIENT);
		packetsys.registerMessage(RhodesJumpPacket.Handler.class,		RhodesJumpPacket.class,			packetCount++, Side.SERVER);
		packetsys.registerMessage(RhodesPiecePacket.Handler.class,		RhodesPiecePacket.class,		packetCount++, Side.CLIENT);
	}
}
