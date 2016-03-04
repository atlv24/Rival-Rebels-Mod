package assets.rivalrebels.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import assets.rivalrebels.common.item.ItemCore;
import assets.rivalrebels.common.item.ItemRod;
import assets.rivalrebels.common.tileentity.TileEntityReactor;

public class ContainerReactor extends Container
{
	protected TileEntityReactor	entity;
	public SlotRR				fuel;
	public SlotRR				core;
	
	public ContainerReactor(IInventory par1iInventory, IInventory par2iInventory)
	{
		entity = (TileEntityReactor) par2iInventory;
		fuel = new SlotRR(entity, 0, 58, 139, 1, ItemRod.class);
		core = new SlotRR(entity, 1, 103, 139, 1, ItemCore.class);
		addSlotToContainer(fuel);
		addSlotToContainer(core);
		bindPlayerInventory(par1iInventory);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return entity.isUseableByPlayer(player);
	}
	
	protected void bindPlayerInventory(IInventory inventoryPlayer)
	{
		for (int i = 0; i < 9; i++)
		{
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 172));
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		return null;
	}
}
