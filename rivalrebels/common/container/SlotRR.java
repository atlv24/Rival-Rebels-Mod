package assets.rivalrebels.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import assets.rivalrebels.RivalRebels;

public class SlotRR extends Slot
{
	int				maxStack			= 64;
	Class<?>		limit				= Item.class;
	boolean			acceptsTrollFace	= false;
	public boolean	locked				= false;
	
	public SlotRR(IInventory inv, int id, int x, int y, int mstack, Class<?> only)
	{
		super(inv, id, x, y);
		maxStack = mstack;
		limit = only;
	}
	
	@Override
	public boolean canTakeStack(EntityPlayer par1EntityPlayer)
	{
		return !locked;
	}
	
	@Override
	public boolean isItemValid(ItemStack item)
	{
		if (locked) return false;
		if (item == null) return false;
		boolean isblock = item.getItem() instanceof ItemBlock;
		boolean trollface = acceptsTrollFace && (item.getItem() == RivalRebels.trollmask);
		boolean itemmatch = limit.isAssignableFrom(item.getItem().getClass());
		boolean blockmatch = isblock && limit.isAssignableFrom(((ItemBlock) item.getItem()).field_150939_a.getClass());
		if (itemmatch || blockmatch || trollface) return true;
		return false;
	}
	
	@Override
	public int getSlotStackLimit()
	{
		return maxStack;
	}
	
	public SlotRR setAcceptsTrollface(boolean t)
	{
		acceptsTrollFace = t;
		
		return this;
	}
}
