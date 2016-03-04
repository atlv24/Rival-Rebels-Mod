package assets.rivalrebels.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import assets.rivalrebels.RivalRebels;

public class ItemCoreCopper extends ItemCore
{
	public ItemCoreCopper()
	{
		super();
		maxStackSize = 1;
		timemult = 0.25f;
		setCreativeTab(RivalRebels.rralltab);
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:ay");
	}
}