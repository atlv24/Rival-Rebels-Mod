package assets.rivalrebels.common.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import assets.rivalrebels.RivalRebels;
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

public class LaptopRefreshPacket implements IMessage
{
	int x;
	int y;
	int z;
	int tasks;
	
	public LaptopRefreshPacket()
	{
		
	}
	
	public LaptopRefreshPacket(int X, int Y, int Z, int T)
	{
		x = X;
		y = Y;
		z = Z;
		tasks = T;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		tasks = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(tasks);
	}
	
	public static class Handler implements IMessageHandler<LaptopRefreshPacket, IMessage>
	{
		@Override
		public IMessage onMessage(LaptopRefreshPacket m, MessageContext ctx)
		{
			TileEntity te = Minecraft.getMinecraft().theWorld.getTileEntity(m.x, m.y, m.z);
			if (te != null && te instanceof TileEntityLaptop)
			{
				((TileEntityLaptop)te).b2spirit=m.tasks;
			}
			return null;
		}
	}
}
