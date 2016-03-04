package assets.rivalrebels.common.round;

public enum RivalRebelsRank
{
	REGULAR(0,13),
	REBEL(1,17),
	OFFICER(2,16),
	LEADER(3,3),
	REP(4,5);
	public int	id	= 0;
	public int	snf	= 0;
	
	RivalRebelsRank(int i, int s)
	{
		id = i;
		snf = s;
	}
	
	public static RivalRebelsRank getForID(int i)
	{
		switch (i)
		{
			case 1:
				return REBEL;
			case 2:
				return OFFICER;
			case 3:
				return LEADER;
			case 4:
				return REP;
		}
		return REGULAR;
	}
}