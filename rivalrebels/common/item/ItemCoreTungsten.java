package assets.rivalrebels.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import assets.rivalrebels.RivalRebels;

public class ItemCoreTungsten extends ItemCore
{
	public ItemCoreTungsten()
	{
		super();
		maxStackSize = 1;
		timemult = 0.75f;
		setCreativeTab(RivalRebels.rralltab);
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:az");
	}
}