package assets.rivalrebels.client.tileentityrender;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import assets.rivalrebels.client.model.ModelBlastSphere;
import assets.rivalrebels.common.tileentity.TileEntityPlasmaExplosion;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityPlasmaExplosionRenderer extends TileEntitySpecialRenderer
{
	ModelBlastSphere	model;
	
	public TileEntityPlasmaExplosionRenderer()
	{
		model = new ModelBlastSphere();
	}
	
	public void renderAModelAt(TileEntityPlasmaExplosion tile, double d, double d1, double d2, float f)
	{
		float fsize = (float) Math.sin(tile.size);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 0.5F, (float) d2 + 0.5F);
		
		GL11.glPushMatrix();
		GL11.glRotatef(tile.size * 50, 0f, 1, 0f);
		model.renderModel(fsize * 5.5f, 0.45f, 0.45f, 0.65f, 0.4f);
		GL11.glRotatef(tile.size * 50, 0f, 1, 0f);
		model.renderModel(fsize * 5.6f, 0.45f, 0.35f, 0.65f, 0.4f);
		GL11.glRotatef(tile.size * 50, 0f, 1, 0f);
		model.renderModel(fsize * 5.7f, 0.45f, 0.35f, 0.95f, 0.4f);
		GL11.glRotatef(tile.size * 50, 0f, 1, 0f);
		model.renderModel(fsize * 5.8f, 0.45f, 0.35f, 0.65f, 0.4f);
		GL11.glPopMatrix();
		model.renderModel(fsize * 5.9f, 0.45f, 0.35f, 0.65f, 0.4f);
		GL11.glPopMatrix();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
	{
		renderAModelAt((TileEntityPlasmaExplosion) tileentity, d, d1, d2, f);
	}
}
