package assets.rivalrebels.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import assets.rivalrebels.RivalRebels;

public class ItemBattery extends Item
{
	public ItemBattery()
	{
		super();
		maxStackSize = 64;
		setCreativeTab(RivalRebels.rralltab);
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:ac");
	}
}