package assets.rivalrebels.common.tileentity;

public class TileEntityReactive extends TileEntityMachineBase
{
	int	cooldown	= 0;
	
	public TileEntityReactive()
	{
		pInM = 1;
	}
	
	@Override
	public float powered(float power, float distance)
	{
		int metadata = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		if (metadata > 0)
		{
			if (cooldown <= 0)
			{
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, metadata - 1, 2);
				cooldown = 10;
				return power - 1;
			}
			cooldown--;
		}
		return power;
	}
}
