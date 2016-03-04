package assets.rivalrebels.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import assets.rivalrebels.RivalRebels;

public class ItemRodRedstone extends ItemRod
{
	public ItemRodRedstone()
	{
		super();
		maxStackSize = 1;
		power = 300000;
		this.setMaxDamage(256);
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
		itemIcon = iconregister.registerIcon("RivalRebels:al");
	}
}