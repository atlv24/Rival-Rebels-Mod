package assets.rivalrebels.common.item;

import net.minecraft.item.Item;
import assets.rivalrebels.RivalRebels;

public class ItemCore extends Item
{
	public float	timemult	= 0;
	
	public ItemCore()
	{
		super();
		maxStackSize = 1;
		setCreativeTab(RivalRebels.rralltab);
	}
}