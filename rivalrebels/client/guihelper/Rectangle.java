package assets.rivalrebels.client.guihelper;

public class Rectangle
{
	public int	xMin, xMax, yMin, yMax;
	
	public Rectangle(int x, int y, int w, int h)
	{
		xMin = x;
		xMax = x + w;
		yMin = y;
		yMax = y + h;
	}
	
	public boolean isVecInside(Vector vec)
	{
		if (vec.x >= xMin && vec.x <= xMax && vec.y >= yMin && vec.y <= yMax) return true;
		return false;
	}
}
