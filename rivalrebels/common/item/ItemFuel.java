package assets.rivalrebels.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import assets.rivalrebels.RivalRebels;

public class ItemFuel extends Item
{
	public ItemFuel()
	{
		super();
		maxStackSize = 64;
		setCreativeTab(RivalRebels.rralltab);
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:af");
	}
	
	@Override
	public boolean isFull3D()
	{
		return true;
	}
}