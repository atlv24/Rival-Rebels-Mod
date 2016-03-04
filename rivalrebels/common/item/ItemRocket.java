package assets.rivalrebels.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import assets.rivalrebels.RivalRebels;

public class ItemRocket extends Item
{
	public ItemRocket()
	{
		super();
		maxStackSize = 32;
		setCreativeTab(RivalRebels.rralltab);
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:ar");
	}
}