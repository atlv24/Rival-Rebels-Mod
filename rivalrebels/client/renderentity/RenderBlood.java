package assets.rivalrebels.client.renderentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import assets.rivalrebels.RivalRebels;

public class RenderBlood extends Render
{
	@Override
	public void doRender(Entity entity, double x, double y, double z, float f, float f1)
	{
		GL11.glEnable(GL11.GL_LIGHTING);
		if (entity.ticksExisted > 1)
		{
			GL11.glPushMatrix();
			GL11.glTranslatef((float) x, (float) y, (float) z);
			GL11.glScalef(0.25F, 0.25F, 0.25F);
			Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etblood);
			renderFaceMe();
			GL11.glPopMatrix();
		}
	}
	
	private void renderFaceMe()
	{
		float var7 = 1.0F;
		float var8 = 0.5F;
		float var9 = 0.25F;
		Tessellator t = Tessellator.instance;
		GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		t.startDrawingQuads();
		t.setNormal(0.0F, 1.0F, 0.0F);
		t.addVertexWithUV((0.0F - var8), (0.0F - var9), 0.0D, 0, 0);
		t.addVertexWithUV((var7 - var8), (0.0F - var9), 0.0D, 1, 0);
		t.addVertexWithUV((var7 - var8), (var7 - var9), 0.0D, 1, 1);
		t.addVertexWithUV((0.0F - var8), (var7 - var9), 0.0D, 0, 1);
		t.draw();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}
