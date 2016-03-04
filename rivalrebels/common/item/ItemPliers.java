package assets.rivalrebels.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.block.autobuilds.BlockAutoTemplate;
import assets.rivalrebels.common.packet.PacketDispatcher;
import assets.rivalrebels.common.packet.TextPacket;

public class ItemPliers extends Item
{
	private int	i	= 0;
	
	public ItemPliers()
	{
		super();
		maxStackSize = 1;
		setContainerItem(this);
		setCreativeTab(RivalRebels.rralltab);
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int m, float hitX, float hitZ, float hitY)
	{
		player.swingItem();
		if (!world.isRemote)
		{
			if (world.getBlock(x, y, z) == RivalRebels.remotecharge)
			{
				int t = 25;
				i = i + 1;
				PacketDispatcher.packetsys.sendTo(new TextPacket("RivalRebels.Defuse " + Integer.toString(i * 100 / t) + "§7'/."),(EntityPlayerMP) player);
				if (i >= t)
				{
					EntityItem ei = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.remotecharge, 1));
					world.spawnEntityInWorld(ei);
					world.setBlock(x, y, z, Blocks.air);
					i = 0;
					return true;
				}
			}
			if (world.getBlock(x, y, z) == RivalRebels.timedbomb)
			{
				int t = 25;
				i = i + 1;
				PacketDispatcher.packetsys.sendTo(new TextPacket("RivalRebels.Defuse " + Integer.toString(i * 100 / t) + "§7'/."),(EntityPlayerMP) player);
				if (i >= t)
				{
					EntityItem ei = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(RivalRebels.timedbomb, 1));
					world.spawnEntityInWorld(ei);
					world.setBlock(x, y, z, Blocks.air);
					world.setBlock(x, y + 1, z, Blocks.air);
					i = 0;
					return true;
				}
			}
			if (world.getBlock(x, y, z) instanceof BlockAutoTemplate)
			{
				BlockAutoTemplate block = (BlockAutoTemplate) world.getBlock(x, y, z);
				i = i + 1;
				PacketDispatcher.packetsys.sendTo(new TextPacket("RivalRebels.Status RivalRebels.building " + Integer.toString(i * 100 / block.time) + "§7'/."),(EntityPlayerMP) player);
				if (i >= block.time)
				{
					world.setBlock(x, y, z, Blocks.air);
					block.build(world, x, y, z);
					i = 0;
					return true;
				}
			}
			if (world.getBlock(x, y, z) == RivalRebels.supplies && world.getBlock(x, y - 1, z) == RivalRebels.supplies)
			{
				i++;
				PacketDispatcher.packetsys.sendTo(new TextPacket("RivalRebels.Status RivalRebels.building ToKaMaK " + Integer.toString(i * 100 / 15) + "§7'/."),(EntityPlayerMP) player);
				if (i >= 15)
				{
					world.setBlock(x, y, z, Blocks.air);
					world.setBlock(x, y - 1, z, RivalRebels.reactor);
					i = 0;
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:ap");
	}
}