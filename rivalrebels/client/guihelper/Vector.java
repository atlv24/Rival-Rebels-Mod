package assets.rivalrebels.client.guihelper;

public class Vector
{
	public int	x, y;
	
	public Vector(int X, int Y)
	{
		x = X;
		y = Y;
	}
	
	public boolean isInsideRectangle(Rectangle rec)
	{
		if (x >= rec.xMin && x <= rec.xMax && y >= rec.yMin && y <= rec.yMax) return true;
		return false;
	}
}
