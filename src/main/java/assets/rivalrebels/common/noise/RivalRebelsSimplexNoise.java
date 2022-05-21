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

public class RivalRebelsSimplexNoise
{
	private static Grad			grad3[]		= { new Grad(1, 1, 0), new Grad(-1, 1, 0), new Grad(1, -1, 0), new Grad(-1, -1, 0), new Grad(1, 0, 1), new Grad(-1, 0, 1), new Grad(1, 0, -1), new Grad(-1, 0, -1), new Grad(0, 1, 1), new Grad(0, -1, 1), new Grad(0, 1, -1), new Grad(0, -1, -1) };
	
	private static Grad			grad4[]		= { new Grad(0, 1, 1, 1), new Grad(0, 1, 1, -1), new Grad(0, 1, -1, 1), new Grad(0, 1, -1, -1), new Grad(0, -1, 1, 1), new Grad(0, -1, 1, -1), new Grad(0, -1, -1, 1), new Grad(0, -1, -1, -1), new Grad(1, 0, 1, 1), new Grad(1, 0, 1, -1), new Grad(1, 0, -1, 1), new Grad(1, 0, -1, -1), new Grad(-1, 0, 1, 1), new Grad(-1, 0, 1, -1), new Grad(-1, 0, -1, 1), new Grad(-1, 0, -1, -1), new Grad(1, 1, 0, 1), new Grad(1, 1, 0, -1), new Grad(1, -1, 0, 1), new Grad(1, -1, 0, -1), new Grad(-1, 1, 0, 1), new Grad(-1, 1, 0, -1), new Grad(-1, -1, 0, 1), new Grad(-1, -1, 0, -1), new Grad(1, 1, 1, 0), new Grad(1, 1, -1, 0), new Grad(1, -1, 1, 0), new Grad(1, -1, -1, 0), new Grad(-1, 1, 1, 0), new Grad(-1, 1, -1, 0), new Grad(-1, -1, 1, 0), new Grad(-1, -1, -1, 0) };
	
	private static short		p[]			= new short[512];
	private static short		perm[]		= new short[512];
	private static short		permMod12[]	= new short[512];
	
	private static final double	F2			= 0.5 * (Math.sqrt(3.0) - 1.0);
	private static final double	G2			= (3.0 - Math.sqrt(3.0)) / 6.0;
	private static final double	F3			= 1.0 / 3.0;
	private static final double	G3			= 1.0 / 6.0;
	private static final double	F4			= (Math.sqrt(5.0) - 1.0) / 4.0;
	private static final double	G4			= (5.0 - Math.sqrt(5.0)) / 20.0;
	
	public static void refresh()
	{
		for (int i = 0; i < 512; i++)
		{
			p[i] = (short) (Math.random() * 255);
			perm[i] = p[i & 255];
			permMod12[i] = (short) (perm[i] % 12);
		}
	}
	
	private static int fastfloor(double x)
	{
		int xi = (int) x;
		return x < xi ? xi - 1 : xi;
	}
	
	private static double dot(Grad g, double x, double y)
	{
		return g.x * x + g.y * y;
	}
	
	private static double dot(Grad g, double x, double y, double z)
	{
		return g.x * x + g.y * y + g.z * z;
	}
	
	private static double dot(Grad g, double x, double y, double z, double w)
	{
		return g.x * x + g.y * y + g.z * z + g.w * w;
	}
	
	public static double noise(double xin, double yin)
	{
		double n0, n1, n2;
		double s = (xin + yin) * F2;
		int i = fastfloor(xin + s);
		int j = fastfloor(yin + s);
		double t = (i + j) * G2;
		double X0 = i - t;
		double Y0 = j - t;
		double x0 = xin - X0;
		double y0 = yin - Y0;
		int i1, j1;
		if (x0 > y0)
		{
			i1 = 1;
			j1 = 0;
		}
		else
		{
			i1 = 0;
			j1 = 1;
		}
		double x1 = x0 - i1 + G2;
		double y1 = y0 - j1 + G2;
		double x2 = x0 - 1.0 + 2.0 * G2;
		double y2 = y0 - 1.0 + 2.0 * G2;
		int ii = i & 255;
		int jj = j & 255;
		int gi0 = permMod12[ii + perm[jj]];
		int gi1 = permMod12[ii + i1 + perm[jj + j1]];
		int gi2 = permMod12[ii + 1 + perm[jj + 1]];
		double t0 = 0.5 - x0 * x0 - y0 * y0;
		if (t0 < 0) n0 = 0.0;
		else
		{
			t0 *= t0;
			n0 = t0 * t0 * dot(grad3[gi0], x0, y0);
		}
		double t1 = 0.5 - x1 * x1 - y1 * y1;
		if (t1 < 0) n1 = 0.0;
		else
		{
			t1 *= t1;
			n1 = t1 * t1 * dot(grad3[gi1], x1, y1);
		}
		double t2 = 0.5 - x2 * x2 - y2 * y2;
		if (t2 < 0) n2 = 0.0;
		else
		{
			t2 *= t2;
			n2 = t2 * t2 * dot(grad3[gi2], x2, y2);
		}
		return 70.0 * (n0 + n1 + n2);
	}
	
	public static double noise(double xin, double yin, double zin)
	{
		double n0, n1, n2, n3;
		double s = (xin + yin + zin) * F3;
		int i = fastfloor(xin + s);
		int j = fastfloor(yin + s);
		int k = fastfloor(zin + s);
		double t = (i + j + k) * G3;
		double X0 = i - t;
		double Y0 = j - t;
		double Z0 = k - t;
		double x0 = xin - X0;
		double y0 = yin - Y0;
		double z0 = zin - Z0;
		int i1, j1, k1;
		int i2, j2, k2;
		if (x0 >= y0)
		{
			if (y0 >= z0)
			{
				i1 = 1;
				j1 = 0;
				k1 = 0;
				i2 = 1;
				j2 = 1;
				k2 = 0;
			} // X Y Z order
			else if (x0 >= z0)
			{
				i1 = 1;
				j1 = 0;
				k1 = 0;
				i2 = 1;
				j2 = 0;
				k2 = 1;
			} // X Z Y order
			else
			{
				i1 = 0;
				j1 = 0;
				k1 = 1;
				i2 = 1;
				j2 = 0;
				k2 = 1;
			} // Z X Y order
		}
		else
		{ // x0<y0
			if (y0 < z0)
			{
				i1 = 0;
				j1 = 0;
				k1 = 1;
				i2 = 0;
				j2 = 1;
				k2 = 1;
			} // Z Y X order
			else if (x0 < z0)
			{
				i1 = 0;
				j1 = 1;
				k1 = 0;
				i2 = 0;
				j2 = 1;
				k2 = 1;
			} // Y Z X order
			else
			{
				i1 = 0;
				j1 = 1;
				k1 = 0;
				i2 = 1;
				j2 = 1;
				k2 = 0;
			} // Y X Z order
		}
		double x1 = x0 - i1 + G3;
		double y1 = y0 - j1 + G3;
		double z1 = z0 - k1 + G3;
		double x2 = x0 - i2 + 2.0 * G3;
		double y2 = y0 - j2 + 2.0 * G3;
		double z2 = z0 - k2 + 2.0 * G3;
		double x3 = x0 - 1.0 + 3.0 * G3;
		double y3 = y0 - 1.0 + 3.0 * G3;
		double z3 = z0 - 1.0 + 3.0 * G3;
		int ii = i & 255;
		int jj = j & 255;
		int kk = k & 255;
		int gi0 = permMod12[ii + perm[jj + perm[kk]]];
		int gi1 = permMod12[ii + i1 + perm[jj + j1 + perm[kk + k1]]];
		int gi2 = permMod12[ii + i2 + perm[jj + j2 + perm[kk + k2]]];
		int gi3 = permMod12[ii + 1 + perm[jj + 1 + perm[kk + 1]]];
		double t0 = 0.6 - x0 * x0 - y0 * y0 - z0 * z0;
		if (t0 < 0) n0 = 0.0;
		else
		{
			t0 *= t0;
			n0 = t0 * t0 * dot(grad3[gi0], x0, y0, z0);
		}
		double t1 = 0.6 - x1 * x1 - y1 * y1 - z1 * z1;
		if (t1 < 0) n1 = 0.0;
		else
		{
			t1 *= t1;
			n1 = t1 * t1 * dot(grad3[gi1], x1, y1, z1);
		}
		double t2 = 0.6 - x2 * x2 - y2 * y2 - z2 * z2;
		if (t2 < 0) n2 = 0.0;
		else
		{
			t2 *= t2;
			n2 = t2 * t2 * dot(grad3[gi2], x2, y2, z2);
		}
		double t3 = 0.6 - x3 * x3 - y3 * y3 - z3 * z3;
		if (t3 < 0) n3 = 0.0;
		else
		{
			t3 *= t3;
			n3 = t3 * t3 * dot(grad3[gi3], x3, y3, z3);
		}
		return 32.0 * (n0 + n1 + n2 + n3);
	}
	
	public static double noise(double x, double y, double z, double w)
	{
		double n0, n1, n2, n3, n4;
		double s = (x + y + z + w) * F4;
		int i = fastfloor(x + s);
		int j = fastfloor(y + s);
		int k = fastfloor(z + s);
		int l = fastfloor(w + s);
		double t = (i + j + k + l) * G4;
		double X0 = i - t;
		double Y0 = j - t;
		double Z0 = k - t;
		double W0 = l - t;
		double x0 = x - X0;
		double y0 = y - Y0;
		double z0 = z - Z0;
		double w0 = w - W0;
		int rankx = 0;
		int ranky = 0;
		int rankz = 0;
		int rankw = 0;
		if (x0 > y0) rankx++;
		else ranky++;
		if (x0 > z0) rankx++;
		else rankz++;
		if (x0 > w0) rankx++;
		else rankw++;
		if (y0 > z0) ranky++;
		else rankz++;
		if (y0 > w0) ranky++;
		else rankw++;
		if (z0 > w0) rankz++;
		else rankw++;
		int i1, j1, k1, l1;
		int i2, j2, k2, l2;
		int i3, j3, k3, l3;
		i1 = rankx >= 3 ? 1 : 0;
		j1 = ranky >= 3 ? 1 : 0;
		k1 = rankz >= 3 ? 1 : 0;
		l1 = rankw >= 3 ? 1 : 0;
		i2 = rankx >= 2 ? 1 : 0;
		j2 = ranky >= 2 ? 1 : 0;
		k2 = rankz >= 2 ? 1 : 0;
		l2 = rankw >= 2 ? 1 : 0;
		i3 = rankx >= 1 ? 1 : 0;
		j3 = ranky >= 1 ? 1 : 0;
		k3 = rankz >= 1 ? 1 : 0;
		l3 = rankw >= 1 ? 1 : 0;
		double x1 = x0 - i1 + G4;
		double y1 = y0 - j1 + G4;
		double z1 = z0 - k1 + G4;
		double w1 = w0 - l1 + G4;
		double x2 = x0 - i2 + 2.0 * G4;
		double y2 = y0 - j2 + 2.0 * G4;
		double z2 = z0 - k2 + 2.0 * G4;
		double w2 = w0 - l2 + 2.0 * G4;
		double x3 = x0 - i3 + 3.0 * G4;
		double y3 = y0 - j3 + 3.0 * G4;
		double z3 = z0 - k3 + 3.0 * G4;
		double w3 = w0 - l3 + 3.0 * G4;
		double x4 = x0 - 1.0 + 4.0 * G4;
		double y4 = y0 - 1.0 + 4.0 * G4;
		double z4 = z0 - 1.0 + 4.0 * G4;
		double w4 = w0 - 1.0 + 4.0 * G4;
		int ii = i & 255;
		int jj = j & 255;
		int kk = k & 255;
		int ll = l & 255;
		int gi0 = perm[ii + perm[jj + perm[kk + perm[ll]]]] % 32;
		int gi1 = perm[ii + i1 + perm[jj + j1 + perm[kk + k1 + perm[ll + l1]]]] % 32;
		int gi2 = perm[ii + i2 + perm[jj + j2 + perm[kk + k2 + perm[ll + l2]]]] % 32;
		int gi3 = perm[ii + i3 + perm[jj + j3 + perm[kk + k3 + perm[ll + l3]]]] % 32;
		int gi4 = perm[ii + 1 + perm[jj + 1 + perm[kk + 1 + perm[ll + 1]]]] % 32;
		double t0 = 0.6 - x0 * x0 - y0 * y0 - z0 * z0 - w0 * w0;
		if (t0 < 0) n0 = 0.0;
		else
		{
			t0 *= t0;
			n0 = t0 * t0 * dot(grad4[gi0], x0, y0, z0, w0);
		}
		double t1 = 0.6 - x1 * x1 - y1 * y1 - z1 * z1 - w1 * w1;
		if (t1 < 0) n1 = 0.0;
		else
		{
			t1 *= t1;
			n1 = t1 * t1 * dot(grad4[gi1], x1, y1, z1, w1);
		}
		double t2 = 0.6 - x2 * x2 - y2 * y2 - z2 * z2 - w2 * w2;
		if (t2 < 0) n2 = 0.0;
		else
		{
			t2 *= t2;
			n2 = t2 * t2 * dot(grad4[gi2], x2, y2, z2, w2);
		}
		double t3 = 0.6 - x3 * x3 - y3 * y3 - z3 * z3 - w3 * w3;
		if (t3 < 0) n3 = 0.0;
		else
		{
			t3 *= t3;
			n3 = t3 * t3 * dot(grad4[gi3], x3, y3, z3, w3);
		}
		double t4 = 0.6 - x4 * x4 - y4 * y4 - z4 * z4 - w4 * w4;
		if (t4 < 0) n4 = 0.0;
		else
		{
			t4 *= t4;
			n4 = t4 * t4 * dot(grad4[gi4], x4, y4, z4, w4);
		}
		return 27.0 * (n0 + n1 + n2 + n3 + n4);
	}
	
	private static class Grad
	{
		double	x, y, z, w;
		
		Grad(double x, double y, double z)
		{
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		Grad(double x, double y, double z, double w)
		{
			this.x = x;
			this.y = y;
			this.z = z;
			this.w = w;
		}
	}
}