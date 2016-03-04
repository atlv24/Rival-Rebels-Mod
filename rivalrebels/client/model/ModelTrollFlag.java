package assets.rivalrebels.client.model;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelTrollFlag
{
	public void renderModel(World world, double x, double y, double z, int metadata)
	{
		Tessellator var5 = Tessellator.instance;
		double var18 = 0.05;
		
		if (metadata == 5)
		{
			var5.startDrawingQuads();
			var5.addVertexWithUV(var18, 1, 1, 0, 0);
			var5.addVertexWithUV(var18, 0, 1, 0, 1);
			var5.addVertexWithUV(var18, 0, 0, 1, 1);
			var5.addVertexWithUV(var18, 1, 0, 1, 0);
			var5.draw();
			var5.startDrawingQuads();
			var5.addVertexWithUV(var18, 1, 0, 1, 0);
			var5.addVertexWithUV(var18, 0, 0, 1, 1);
			var5.addVertexWithUV(var18, 0, 1, 0, 1);
			var5.addVertexWithUV(var18, 1, 1, 0, 0);
			var5.draw();
		}
		
		if (metadata == 4)
		{
			var5.startDrawingQuads();
			var5.addVertexWithUV(1 - var18, 0, 1, 1, 1);
			var5.addVertexWithUV(1 - var18, 1, 1, 1, 0);
			var5.addVertexWithUV(1 - var18, 1, 0, 0, 0);
			var5.addVertexWithUV(1 - var18, 0, 0, 0, 1);
			var5.draw();
			var5.startDrawingQuads();
			var5.addVertexWithUV(1 - var18, 0, 0, 0, 1);
			var5.addVertexWithUV(1 - var18, 1, 0, 0, 0);
			var5.addVertexWithUV(1 - var18, 1, 1, 1, 0);
			var5.addVertexWithUV(1 - var18, 0, 1, 1, 1);
			var5.draw();
		}
		
		if (metadata == 3)
		{
			var5.startDrawingQuads();
			var5.addVertexWithUV(1, 0, var18, 1, 1);
			var5.addVertexWithUV(1, 1, var18, 1, 0);
			var5.addVertexWithUV(0, 1, var18, 0, 0);
			var5.addVertexWithUV(0, 0, var18, 0, 1);
			var5.draw();
			var5.startDrawingQuads();
			var5.addVertexWithUV(0, 0, var18, 0, 1);
			var5.addVertexWithUV(0, 1, var18, 0, 0);
			var5.addVertexWithUV(1, 1, var18, 1, 0);
			var5.addVertexWithUV(1, 0, var18, 1, 1);
			var5.draw();
		}
		
		if (metadata == 2)
		{
			var5.startDrawingQuads();
			var5.addVertexWithUV(1, 1, 1 - var18, 0, 0);
			var5.addVertexWithUV(1, 0, 1 - var18, 0, 1);
			var5.addVertexWithUV(0, 0, 1 - var18, 1, 1);
			var5.addVertexWithUV(0, 1, 1 - var18, 1, 0);
			var5.draw();
			var5.startDrawingQuads();
			var5.addVertexWithUV(0, 1, 1 - var18, 1, 0);
			var5.addVertexWithUV(0, 0, 1 - var18, 1, 1);
			var5.addVertexWithUV(1, 0, 1 - var18, 0, 1);
			var5.addVertexWithUV(1, 1, 1 - var18, 0, 0);
			var5.draw();
		}
	}
}
