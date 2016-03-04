package assets.rivalrebels.client.itemrenders;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.objfileloader.ModelFromObj;

public class FlamethrowerRenderer implements IItemRenderer
{
	ModelFromObj	ft;
	
	public FlamethrowerRenderer()
	{
		try
		{
			ft = ModelFromObj.readObjFile("n.obj");
		}
		catch (Exception e)
		{
			System.err.println("Please make sure the model files are in the correct directory.");
		}
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		if (type == ItemRenderType.FIRST_PERSON_MAP || type == ItemRenderType.EQUIPPED || type == ItemRenderType.ENTITY || type == ItemRenderType.EQUIPPED_FIRST_PERSON) return true;
		return false;
	}
	
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return false;
	}
	
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etflamethrower);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPushMatrix();
		GL11.glRotatef(35, 0.0F, 0.0F, 1.0F);
		GL11.glTranslatef(0.7f, 0.1f, 00f);
		GL11.glRotatef(270, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(0.18f, 0.18f, 0.18f);
		// GL11.glTranslatef(0.3f, 0.05f, -0.1f);
		
		ft.render();
		
		GL11.glPopMatrix();
	}
}
