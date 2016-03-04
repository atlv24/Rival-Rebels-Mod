package assets.rivalrebels.common.tileentity;

public enum ADSEnum
{
	NONE(0),
	FLAMETHROWER(1),
	M202RR(2),
	EINSTEN(3),
	PLASMACANNON(4),
	TESLA(5);
	public int	id;
	
	ADSEnum(int i)
	{
		id = i;
	}
	
	public static ADSEnum getForID(int i)
	{
		switch (i)
		{
			case 1:
				return FLAMETHROWER;
			case 2:
				return M202RR;
			case 3:
				return EINSTEN;
			case 4:
				return PLASMACANNON;
			case 5:
				return TESLA;
		}
		return NONE;
	}
}
