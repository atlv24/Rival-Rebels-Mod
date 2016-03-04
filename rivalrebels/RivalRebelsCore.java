package assets.rivalrebels;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.Name(value = RivalRebels.rrname)
@IFMLLoadingPlugin.MCVersion(value = RivalRebels.mcversion)
@IFMLLoadingPlugin.TransformerExclusions(value = {RivalRebels.packagename})
@IFMLLoadingPlugin.SortingIndex(value = 1001)
public class RivalRebelsCore implements IFMLLoadingPlugin
{
	@Override
	public String[] getASMTransformerClass()
	{
		return new String[] {ASMTransformer.class.getName()};
	}

	@Override
	public String getModContainerClass()
	{
		return null;//RivalRebels.class.getName();
	}

	@Override
	public String getSetupClass()
	{
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data)
	{
	}

	@Override
	public String getAccessTransformerClass()
	{
		return null;
	}
}
