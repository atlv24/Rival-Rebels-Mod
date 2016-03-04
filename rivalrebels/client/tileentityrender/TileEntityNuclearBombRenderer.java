package assets.rivalrebels.client.tileentityrender;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.model.ModelNuclearBomb;
import assets.rivalrebels.common.tileentity.TileEntityNuclearBomb;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityNuclearBombRenderer extends TileEntitySpecialRenderer
{
	private ModelNuclearBomb	model;
	
	public TileEntityNuclearBombRenderer()
	{
		model = new ModelNuclearBomb();
	}
	
	public void renderAModelAt(TileEntityNuclearBomb tile, double d, double d1, double d2, float f)
	{
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 0.5F, (float) d2 + 0.5F);
		int metadata = tile.getBlockMetadata();
		if (metadata == 0)
		{
			GL11.glRotatef(180, 1, 0, 0);
		}
		
		if (metadata == 2)
		{
			GL11.glRotatef(-90, 1, 0, 0);
		}
		
		if (metadata == 3)
		{
			GL11.glRotatef(90, 1, 0, 0);
		}
		
		if (metadata == 4)
		{
			GL11.glRotatef(90, 0, 0, 1);
		}
		
		if (metadata == 5)
		{
			GL11.glRotatef(-90, 0, 0, 1);
		}
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etnuke);
		model.renderModel(tile.hasFuse);
		GL11.glPopMatrix();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
	{
		renderAModelAt((TileEntityNuclearBomb) tileentity, d, d1, d2, f);
	}
}
