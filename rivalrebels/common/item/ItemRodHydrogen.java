package assets.rivalrebels.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import assets.rivalrebels.RivalRebels;

public class ItemRodHydrogen extends ItemRod
{
	public ItemRodHydrogen()
	{
		super();
		maxStackSize = 1;
		power = 250000;
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
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:au");
	}
}