/*******************************************************************************
 * Copyright (c) 2012, 2016 Rodol Phito.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Mozilla Public License Version 2.0
 * which accompanies this distribution, and is available at
 * https://www.mozilla.org/en-US/MPL/2.0/
 *
 * Rival Rebels Mod. All code, art, and design by Rodol Phito.
 *
 * http://RivalRebels.com/
 *******************************************************************************/
package assets.rivalrebels.common.noise;

public class RivalRebelsCellularNoise
{
	public static int		pointa2D	= 32;
	public static Point[]	points2D	= new Point[pointa2D];
	public static int		pointa3D	= 32;
	public static Point[]	points3D	= new Point[pointa3D];
	
	public static void refresh2D()
	{
		for (int i = 0; i < pointa2D; i++)
		{
			points2D[i] = new Point(Math.random(), Math.random());
		}
	}
	
	public static void refresh3D()
	{
		for (int i = 0; i < pointa3D; i++)
		{
			points3D[i] = new Point(Math.random(), Math.random(), Math.random());
		}
	}
	
	public static double noise(double xin, double yin)
	{
		double result = 1;
		for (int i = 0; i < pointa2D; i++)
		{
			Point point = points2D[i];
			double dist = getDist(point, xin, yin);
			if (dist <= result)
			{
				result = dist;
			}
		}
		return (Math.sqrt(result) * 2d) - 1d;
	}
	
	public static double noise(double xin, double yin, double zin)
	{
		double result = 1;
		for (int i = 0; i < pointa3D; i++)
		{
			Point point = points3D[i];
			double dist = getDist(point, xin, yin, zin);
			if (dist <= result)
			{
				result = dist;
			}
		}
		return (Math.sqrt(result) * 4) - 0.75d;
	}
	
	private static double getDist(Point point, double xin, double yin)
	{
		double result = 1;
		for (int x = -1; x <= 1; x++)
		{
			double xx = point.x - (xin + x);
			double X = xx * xx;
			for (int y = -1; y <= 1; y++)
			{
				double yy = point.y - (yin + y);
				double Y = yy * yy + X;
				if (Y < result) result = Y;
			}
		}
		return result;
	}
	
	private static double getDist(Point point, double xin, double yin, double zin)
	{
		double result = 1;
		for (int x = -1; x <= 1; x++)
		{
			double xx = point.x - (xin + x);
			double X = xx * xx;
			for (int y = -1; y <= 1; y++)
			{
				double yy = point.y - (yin + y);
				double Y = yy * yy + X;
				for (int z = -1; z <= 1; z++)
				{
					double zz = point.z - (zin + z);
					double Z = zz * zz + Y;
					if (Z < result) result = Z;
				}
			}
		}
		return result;
	}
	
	private static class Point
	{
		double	x, y, z;
		
		Point(double x, double y)
		{
			this.x = x;
			this.y = y;
		}
		
		Point(double x, double y, double z)
		{
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
}