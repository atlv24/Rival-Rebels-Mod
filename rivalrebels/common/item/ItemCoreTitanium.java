package assets.rivalrebels.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import assets.rivalrebels.RivalRebels;

public class ItemCoreTitanium extends ItemCore
{
	public ItemCoreTitanium()
	{
		super();
		maxStackSize = 1;
		timemult = 1.0f;
		setCreativeTab(RivalRebels.rralltab);
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:ba");
	}
}