package assets.rivalrebels.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import assets.rivalrebels.RivalRebels;

public class ItemRod extends Item
{
	public int	power;
	
	public ItemRod()
	{
		super();
		maxStackSize = 1;
		this.setMaxDamage(32);
		setCreativeTab(RivalRebels.rralltab);
	}
	
	@Override
	public boolean isItemTool(ItemStack is)
	{
		return false;
	}
	
	@Override
	public boolean isDamageable()
	{
		return true;
	}
}