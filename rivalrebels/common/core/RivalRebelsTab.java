package assets.rivalrebels.common.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import assets.rivalrebels.RivalRebels;

public class RivalRebelsTab extends CreativeTabs
{
	private String	name;
	private int		icon;
	
	public RivalRebelsTab(String name, int icon)
	{
		super(name);
		this.name = name;
		this.icon = icon;
	}
	
	@Override
	public Item getTabIconItem()
	{
		if (icon == 0) return RivalRebels.nuclearelement;
		else return RivalRebels.hydrod;
	}
	
	@Override
	public String getTranslatedTabLabel()
	{
		return this.name;
	}
	
	@Override
	public String getTabLabel()
	{
		return this.name;
	}
}
