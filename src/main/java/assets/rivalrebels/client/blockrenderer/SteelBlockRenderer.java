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
package assets.rivalrebels.client.blockrenderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class SteelBlockRenderer implements ISimpleBlockRenderingHandler
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		Tessellator tessellator = Tessellator.instance;
		
		block.setBlockBoundsForItemRender();
		renderer.setRenderBoundsFromBlock(block);
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);
		renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 2, metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 3, metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, metadata));
		tessellator.draw();
		
		double offset = 1 - 0.124;
		double ooffset = -offset;
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);
		renderer.renderFaceYNeg(block, 0.0D, offset, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0.0D, ooffset, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, offset, renderer.getBlockIconFromSideAndMetadata(block, 2, metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, ooffset, renderer.getBlockIconFromSideAndMetadata(block, 3, metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		renderer.renderFaceXNeg(block, offset, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceXPos(block, ooffset, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, metadata));
		tessellator.draw();
		
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		if (block != RivalRebels.steel) return false;
		renderer.enableAO = true;
		float f3 = 0.0F;
		float f4 = 0.0F;
		float f5 = 0.0F;
		float f6 = 0.0F;
		int l = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(0);
		boolean flag2;
		boolean flag3;
		boolean flag4;
		boolean flag5;
		float f7;
		int i1;
		IIcon icon = block.getIcon(0, 0);
		
		renderer.aoBrightnessXYNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
		renderer.aoBrightnessYZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
		renderer.aoBrightnessYZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
		renderer.aoBrightnessXYPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
		renderer.aoLightValueScratchXYNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
		renderer.aoLightValueScratchYZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
		renderer.aoLightValueScratchYZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
		renderer.aoLightValueScratchXYPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
		flag3 = renderer.blockAccess.getBlock(x + 1, y - 1, z).getCanBlockGrass();
		flag2 = renderer.blockAccess.getBlock(x - 1, y - 1, z).getCanBlockGrass();
		flag5 = renderer.blockAccess.getBlock(x, y - 1, z + 1).getCanBlockGrass();
		flag4 = renderer.blockAccess.getBlock(x, y - 1, z - 1).getCanBlockGrass();
		
		if (!flag4 && !flag2)
		{
			renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXYNN;
			renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXYNN;
		}
		else
		{
			renderer.aoLightValueScratchXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z - 1);
			renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z - 1);
		}
		
		if (!flag5 && !flag2)
		{
			renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXYNN;
			renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXYNN;
		}
		else
		{
			renderer.aoLightValueScratchXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z + 1);
			renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z + 1);
		}
		
		if (!flag4 && !flag3)
		{
			renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXYPN;
			renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXYPN;
		}
		else
		{
			renderer.aoLightValueScratchXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z - 1);
			renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z - 1);
		}
		
		if (!flag5 && !flag3)
		{
			renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXYPN;
			renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXYPN;
		}
		else
		{
			renderer.aoLightValueScratchXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z + 1);
			renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z + 1);
		}
		
		i1 = l;
		
		if (renderer.renderMinY <= 0.0D || !renderer.blockAccess.getBlock(x, y - 1, z).isOpaqueCube())
		{
			i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
		}
		
		f7 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
		f3 = (renderer.aoLightValueScratchXYZNNP + renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchYZNP + f7) / 4.0F;
		f6 = (renderer.aoLightValueScratchYZNP + f7 + renderer.aoLightValueScratchXYZPNP + renderer.aoLightValueScratchXYPN) / 4.0F;
		f5 = (f7 + renderer.aoLightValueScratchYZNN + renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXYZPNN) / 4.0F;
		f4 = (renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchXYZNNN + f7 + renderer.aoLightValueScratchYZNN) / 4.0F;
		renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXYNN, renderer.aoBrightnessYZNP, i1);
		renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessYZNP, renderer.aoBrightnessXYZPNP, renderer.aoBrightnessXYPN, i1);
		renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessYZNN, renderer.aoBrightnessXYPN, renderer.aoBrightnessXYZPNN, i1);
		renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessXYNN, renderer.aoBrightnessXYZNNN, renderer.aoBrightnessYZNN, i1);
		
		renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.5F;
		renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.5F;
		renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.5F;
		
		renderer.colorRedTopLeft *= f3;
		renderer.colorGreenTopLeft *= f3;
		renderer.colorBlueTopLeft *= f3;
		renderer.colorRedBottomLeft *= f4;
		renderer.colorGreenBottomLeft *= f4;
		renderer.colorBlueBottomLeft *= f4;
		renderer.colorRedBottomRight *= f5;
		renderer.colorGreenBottomRight *= f5;
		renderer.colorBlueBottomRight *= f5;
		renderer.colorRedTopRight *= f6;
		renderer.colorGreenTopRight *= f6;
		renderer.colorBlueTopRight *= f6;
		renderer.renderFaceYNeg(block, x, y, z, icon);
		
		renderer.aoBrightnessXYNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
		renderer.aoBrightnessXYPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
		renderer.aoBrightnessYZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
		renderer.aoBrightnessYZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
		renderer.aoLightValueScratchXYNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
		renderer.aoLightValueScratchXYPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
		renderer.aoLightValueScratchYZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
		renderer.aoLightValueScratchYZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
		flag3 = renderer.blockAccess.getBlock(x + 1, y + 1, z).getCanBlockGrass();
		flag2 = renderer.blockAccess.getBlock(x - 1, y + 1, z).getCanBlockGrass();
		flag5 = renderer.blockAccess.getBlock(x, y + 1, z + 1).getCanBlockGrass();
		flag4 = renderer.blockAccess.getBlock(x, y + 1, z - 1).getCanBlockGrass();
		
		if (!flag4 && !flag2)
		{
			renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXYNP;
			renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXYNP;
		}
		else
		{
			renderer.aoLightValueScratchXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z - 1);
			renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z - 1);
		}
		
		if (!flag4 && !flag3)
		{
			renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXYPP;
			renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXYPP;
		}
		else
		{
			renderer.aoLightValueScratchXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z - 1);
			renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z - 1);
		}
		
		if (!flag5 && !flag2)
		{
			renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXYNP;
			renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXYNP;
		}
		else
		{
			renderer.aoLightValueScratchXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z + 1);
			renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z + 1);
		}
		
		if (!flag5 && !flag3)
		{
			renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXYPP;
			renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXYPP;
		}
		else
		{
			renderer.aoLightValueScratchXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z + 1);
			renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z + 1);
		}
		
		i1 = l;
		
		if (renderer.renderMaxY >= 1.0D || !renderer.blockAccess.getBlock(x, y + 1, z).isOpaqueCube())
		{
			i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
		}
		
		f7 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
		f6 = (renderer.aoLightValueScratchXYZNPP + renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchYZPP + f7) / 4.0F;
		f3 = (renderer.aoLightValueScratchYZPP + f7 + renderer.aoLightValueScratchXYZPPP + renderer.aoLightValueScratchXYPP) / 4.0F;
		f4 = (f7 + renderer.aoLightValueScratchYZPN + renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPN) / 4.0F;
		f5 = (renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchXYZNPN + f7 + renderer.aoLightValueScratchYZPN) / 4.0F;
		renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXYZNPP, renderer.aoBrightnessXYNP, renderer.aoBrightnessYZPP, i1);
		renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessYZPP, renderer.aoBrightnessXYZPPP, renderer.aoBrightnessXYPP, i1);
		renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessYZPN, renderer.aoBrightnessXYPP, renderer.aoBrightnessXYZPPN, i1);
		renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessXYNP, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessYZPN, i1);
		renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 1;
		renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 1;
		renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 1;
		renderer.colorRedTopLeft *= f3;
		renderer.colorGreenTopLeft *= f3;
		renderer.colorBlueTopLeft *= f3;
		renderer.colorRedBottomLeft *= f4;
		renderer.colorGreenBottomLeft *= f4;
		renderer.colorBlueBottomLeft *= f4;
		renderer.colorRedBottomRight *= f5;
		renderer.colorGreenBottomRight *= f5;
		renderer.colorBlueBottomRight *= f5;
		renderer.colorRedTopRight *= f6;
		renderer.colorGreenTopRight *= f6;
		renderer.colorBlueTopRight *= f6;
		renderer.renderFaceYPos(block, x, y, z, icon);
		
		renderer.aoLightValueScratchXZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
		renderer.aoLightValueScratchYZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
		renderer.aoLightValueScratchYZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
		renderer.aoLightValueScratchXZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
		renderer.aoBrightnessXZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
		renderer.aoBrightnessYZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
		renderer.aoBrightnessYZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
		renderer.aoBrightnessXZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
		flag3 = renderer.blockAccess.getBlock(x + 1, y, z - 1).getCanBlockGrass();
		flag2 = renderer.blockAccess.getBlock(x - 1, y, z - 1).getCanBlockGrass();
		flag5 = renderer.blockAccess.getBlock(x, y + 1, z - 1).getCanBlockGrass();
		flag4 = renderer.blockAccess.getBlock(x, y - 1, z - 1).getCanBlockGrass();
		
		if (!flag2 && !flag4)
		{
			renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXZNN;
			renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXZNN;
		}
		else
		{
			renderer.aoLightValueScratchXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y - 1, z);
			renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y - 1, z);
		}
		
		if (!flag2 && !flag5)
		{
			renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXZNN;
			renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXZNN;
		}
		else
		{
			renderer.aoLightValueScratchXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y + 1, z);
			renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y + 1, z);
		}
		
		if (!flag3 && !flag4)
		{
			renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXZPN;
			renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXZPN;
		}
		else
		{
			renderer.aoLightValueScratchXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y - 1, z);
			renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y - 1, z);
		}
		
		if (!flag3 && !flag5)
		{
			renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXZPN;
			renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXZPN;
		}
		else
		{
			renderer.aoLightValueScratchXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y + 1, z);
			renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y + 1, z);
		}
		
		i1 = l;
		
		if (renderer.renderMinZ <= 0.0D || !renderer.blockAccess.getBlock(x, y, z - 1).isOpaqueCube())
		{
			i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
		}
		
		f7 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
		f3 = (renderer.aoLightValueScratchXZNN + renderer.aoLightValueScratchXYZNPN + f7 + renderer.aoLightValueScratchYZPN) / 4.0F;
		f4 = (f7 + renderer.aoLightValueScratchYZPN + renderer.aoLightValueScratchXZPN + renderer.aoLightValueScratchXYZPPN) / 4.0F;
		f5 = (renderer.aoLightValueScratchYZNN + f7 + renderer.aoLightValueScratchXYZPNN + renderer.aoLightValueScratchXZPN) / 4.0F;
		f6 = (renderer.aoLightValueScratchXYZNNN + renderer.aoLightValueScratchXZNN + renderer.aoLightValueScratchYZNN + f7) / 4.0F;
		renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXZNN, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessYZPN, i1);
		renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessYZPN, renderer.aoBrightnessXZPN, renderer.aoBrightnessXYZPPN, i1);
		renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessYZNN, renderer.aoBrightnessXYZPNN, renderer.aoBrightnessXZPN, i1);
		renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXYZNNN, renderer.aoBrightnessXZNN, renderer.aoBrightnessYZNN, i1);
		
		renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.8F;
		renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.8F;
		renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.8F;
		
		renderer.colorRedTopLeft *= f3;
		renderer.colorGreenTopLeft *= f3;
		renderer.colorBlueTopLeft *= f3;
		renderer.colorRedBottomLeft *= f4;
		renderer.colorGreenBottomLeft *= f4;
		renderer.colorBlueBottomLeft *= f4;
		renderer.colorRedBottomRight *= f5;
		renderer.colorGreenBottomRight *= f5;
		renderer.colorBlueBottomRight *= f5;
		renderer.colorRedTopRight *= f6;
		renderer.colorGreenTopRight *= f6;
		renderer.colorBlueTopRight *= f6;
		renderer.renderFaceZNeg(block, x, y, z, icon);
		
		renderer.aoLightValueScratchXZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
		renderer.aoLightValueScratchXZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
		renderer.aoLightValueScratchYZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
		renderer.aoLightValueScratchYZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
		renderer.aoBrightnessXZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
		renderer.aoBrightnessXZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
		renderer.aoBrightnessYZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
		renderer.aoBrightnessYZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
		flag3 = renderer.blockAccess.getBlock(x + 1, y, z + 1).getCanBlockGrass();
		flag2 = renderer.blockAccess.getBlock(x - 1, y, z + 1).getCanBlockGrass();
		flag5 = renderer.blockAccess.getBlock(x, y + 1, z + 1).getCanBlockGrass();
		flag4 = renderer.blockAccess.getBlock(x, y - 1, z + 1).getCanBlockGrass();
		
		if (!flag2 && !flag4)
		{
			renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXZNP;
			renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXZNP;
		}
		else
		{
			renderer.aoLightValueScratchXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y - 1, z);
			renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y - 1, z);
		}
		
		if (!flag2 && !flag5)
		{
			renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXZNP;
			renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXZNP;
		}
		else
		{
			renderer.aoLightValueScratchXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y + 1, z);
			renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y + 1, z);
		}
		
		if (!flag3 && !flag4)
		{
			renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXZPP;
			renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXZPP;
		}
		else
		{
			renderer.aoLightValueScratchXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y - 1, z);
			renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y - 1, z);
		}
		
		if (!flag3 && !flag5)
		{
			renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXZPP;
			renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXZPP;
		}
		else
		{
			renderer.aoLightValueScratchXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y + 1, z);
			renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y + 1, z);
		}
		
		i1 = l;
		
		if (renderer.renderMaxZ >= 1.0D || !renderer.blockAccess.getBlock(x, y, z + 1).isOpaqueCube())
		{
			i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
		}
		
		f7 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
		f3 = (renderer.aoLightValueScratchXZNP + renderer.aoLightValueScratchXYZNPP + f7 + renderer.aoLightValueScratchYZPP) / 4.0F;
		f6 = (f7 + renderer.aoLightValueScratchYZPP + renderer.aoLightValueScratchXZPP + renderer.aoLightValueScratchXYZPPP) / 4.0F;
		f5 = (renderer.aoLightValueScratchYZNP + f7 + renderer.aoLightValueScratchXYZPNP + renderer.aoLightValueScratchXZPP) / 4.0F;
		f4 = (renderer.aoLightValueScratchXYZNNP + renderer.aoLightValueScratchXZNP + renderer.aoLightValueScratchYZNP + f7) / 4.0F;
		renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXZNP, renderer.aoBrightnessXYZNPP, renderer.aoBrightnessYZPP, i1);
		renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessYZPP, renderer.aoBrightnessXZPP, renderer.aoBrightnessXYZPPP, i1);
		renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessYZNP, renderer.aoBrightnessXYZPNP, renderer.aoBrightnessXZPP, i1);
		renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXZNP, renderer.aoBrightnessYZNP, i1);
		
		renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.8F;
		renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.8F;
		renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.8F;
		
		renderer.colorRedTopLeft *= f3;
		renderer.colorGreenTopLeft *= f3;
		renderer.colorBlueTopLeft *= f3;
		renderer.colorRedBottomLeft *= f4;
		renderer.colorGreenBottomLeft *= f4;
		renderer.colorBlueBottomLeft *= f4;
		renderer.colorRedBottomRight *= f5;
		renderer.colorGreenBottomRight *= f5;
		renderer.colorBlueBottomRight *= f5;
		renderer.colorRedTopRight *= f6;
		renderer.colorGreenTopRight *= f6;
		renderer.colorBlueTopRight *= f6;
		renderer.renderFaceZPos(block, x, y, z, icon);
		
		renderer.aoLightValueScratchXYNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
		renderer.aoLightValueScratchXZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
		renderer.aoLightValueScratchXZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
		renderer.aoLightValueScratchXYNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
		renderer.aoBrightnessXYNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
		renderer.aoBrightnessXZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
		renderer.aoBrightnessXZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
		renderer.aoBrightnessXYNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
		flag3 = renderer.blockAccess.getBlock(x - 1, y + 1, z).getCanBlockGrass();
		flag2 = renderer.blockAccess.getBlock(x - 1, y - 1, z).getCanBlockGrass();
		flag5 = renderer.blockAccess.getBlock(x - 1, y, z - 1).getCanBlockGrass();
		flag4 = renderer.blockAccess.getBlock(x - 1, y, z + 1).getCanBlockGrass();
		
		if (!flag5 && !flag2)
		{
			renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXZNN;
			renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXZNN;
		}
		else
		{
			renderer.aoLightValueScratchXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z - 1);
			renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z - 1);
		}
		
		if (!flag4 && !flag2)
		{
			renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXZNP;
			renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXZNP;
		}
		else
		{
			renderer.aoLightValueScratchXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z + 1);
			renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z + 1);
		}
		
		if (!flag5 && !flag3)
		{
			renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXZNN;
			renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXZNN;
		}
		else
		{
			renderer.aoLightValueScratchXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z - 1);
			renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z - 1);
		}
		
		if (!flag4 && !flag3)
		{
			renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXZNP;
			renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXZNP;
		}
		else
		{
			renderer.aoLightValueScratchXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z + 1);
			renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z + 1);
		}
		
		i1 = l;
		
		if (renderer.renderMinX <= 0.0D || !renderer.blockAccess.getBlock(x - 1, y, z).isOpaqueCube())
		{
			i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
		}
		
		f7 = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
		f6 = (renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchXYZNNP + f7 + renderer.aoLightValueScratchXZNP) / 4.0F;
		f3 = (f7 + renderer.aoLightValueScratchXZNP + renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchXYZNPP) / 4.0F;
		f4 = (renderer.aoLightValueScratchXZNN + f7 + renderer.aoLightValueScratchXYZNPN + renderer.aoLightValueScratchXYNP) / 4.0F;
		f5 = (renderer.aoLightValueScratchXYZNNN + renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchXZNN + f7) / 4.0F;
		renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXYNN, renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXZNP, i1);
		renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXZNP, renderer.aoBrightnessXYNP, renderer.aoBrightnessXYZNPP, i1);
		renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessXZNN, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessXYNP, i1);
		renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessXYZNNN, renderer.aoBrightnessXYNN, renderer.aoBrightnessXZNN, i1);
		
		renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.6F;
		renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.6F;
		renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.6F;
		
		renderer.colorRedTopLeft *= f3;
		renderer.colorGreenTopLeft *= f3;
		renderer.colorBlueTopLeft *= f3;
		renderer.colorRedBottomLeft *= f4;
		renderer.colorGreenBottomLeft *= f4;
		renderer.colorBlueBottomLeft *= f4;
		renderer.colorRedBottomRight *= f5;
		renderer.colorGreenBottomRight *= f5;
		renderer.colorBlueBottomRight *= f5;
		renderer.colorRedTopRight *= f6;
		renderer.colorGreenTopRight *= f6;
		renderer.colorBlueTopRight *= f6;
		renderer.renderFaceXNeg(block, x, y, z, icon);
		
		renderer.aoLightValueScratchXYPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
		renderer.aoLightValueScratchXZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
		renderer.aoLightValueScratchXZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
		renderer.aoLightValueScratchXYPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
		renderer.aoBrightnessXYPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
		renderer.aoBrightnessXZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
		renderer.aoBrightnessXZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
		renderer.aoBrightnessXYPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
		flag3 = renderer.blockAccess.getBlock(x + 1, y + 1, z).getCanBlockGrass();
		flag2 = renderer.blockAccess.getBlock(x + 1, y - 1, z).getCanBlockGrass();
		flag5 = renderer.blockAccess.getBlock(x + 1, y, z + 1).getCanBlockGrass();
		flag4 = renderer.blockAccess.getBlock(x + 1, y, z - 1).getCanBlockGrass();
		
		if (!flag2 && !flag4)
		{
			renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXZPN;
			renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXZPN;
		}
		else
		{
			renderer.aoLightValueScratchXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z - 1);
			renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z - 1);
		}
		
		if (!flag2 && !flag5)
		{
			renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXZPP;
			renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXZPP;
		}
		else
		{
			renderer.aoLightValueScratchXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z + 1);
			renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z + 1);
		}
		
		if (!flag3 && !flag4)
		{
			renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXZPN;
			renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXZPN;
		}
		else
		{
			renderer.aoLightValueScratchXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z - 1);
			renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z - 1);
		}
		
		if (!flag3 && !flag5)
		{
			renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXZPP;
			renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXZPP;
		}
		else
		{
			renderer.aoLightValueScratchXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z + 1);
			renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z + 1);
		}
		
		i1 = l;
		
		if (renderer.renderMaxX >= 1.0D || !renderer.blockAccess.getBlock(x + 1, y, z).isOpaqueCube())
		{
			i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
		}
		
		f7 = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
		f3 = (renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXYZPNP + f7 + renderer.aoLightValueScratchXZPP) / 4.0F;
		f4 = (renderer.aoLightValueScratchXYZPNN + renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXZPN + f7) / 4.0F;
		f5 = (renderer.aoLightValueScratchXZPN + f7 + renderer.aoLightValueScratchXYZPPN + renderer.aoLightValueScratchXYPP) / 4.0F;
		f6 = (f7 + renderer.aoLightValueScratchXZPP + renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPP) / 4.0F;
		renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXYPN, renderer.aoBrightnessXYZPNP, renderer.aoBrightnessXZPP, i1);
		renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXZPP, renderer.aoBrightnessXYPP, renderer.aoBrightnessXYZPPP, i1);
		renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessXZPN, renderer.aoBrightnessXYZPPN, renderer.aoBrightnessXYPP, i1);
		renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessXYZPNN, renderer.aoBrightnessXYPN, renderer.aoBrightnessXZPN, i1);
		
		renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.6F;
		renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.6F;
		renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.6F;
		
		renderer.colorRedTopLeft *= f3;
		renderer.colorGreenTopLeft *= f3;
		renderer.colorBlueTopLeft *= f3;
		renderer.colorRedBottomLeft *= f4;
		renderer.colorGreenBottomLeft *= f4;
		renderer.colorBlueBottomLeft *= f4;
		renderer.colorRedBottomRight *= f5;
		renderer.colorGreenBottomRight *= f5;
		renderer.colorBlueBottomRight *= f5;
		renderer.colorRedTopRight *= f6;
		renderer.colorGreenTopRight *= f6;
		renderer.colorBlueTopRight *= f6;
		renderer.renderFaceXPos(block, x, y, z, icon);
		
		// ==========================================================================================================================================
		// INSIDE OF THE BLOCK
		// ==========================================================================================================================================
		
		double offset = 0.124;
		
		y++;
		renderer.aoBrightnessXYNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
		renderer.aoBrightnessYZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
		renderer.aoBrightnessYZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
		renderer.aoBrightnessXYPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
		renderer.aoLightValueScratchXYNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
		renderer.aoLightValueScratchYZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
		renderer.aoLightValueScratchYZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
		renderer.aoLightValueScratchXYPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
		flag3 = renderer.blockAccess.getBlock(x + 1, y - 1, z).getCanBlockGrass();
		flag2 = renderer.blockAccess.getBlock(x - 1, y - 1, z).getCanBlockGrass();
		flag5 = renderer.blockAccess.getBlock(x, y - 1, z + 1).getCanBlockGrass();
		flag4 = renderer.blockAccess.getBlock(x, y - 1, z - 1).getCanBlockGrass();
		
		if (!flag4 && !flag2)
		{
			renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXYNN;
			renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXYNN;
		}
		else
		{
			renderer.aoLightValueScratchXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z - 1);
			renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z - 1);
		}
		
		if (!flag5 && !flag2)
		{
			renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXYNN;
			renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXYNN;
		}
		else
		{
			renderer.aoLightValueScratchXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z + 1);
			renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z + 1);
		}
		
		if (!flag4 && !flag3)
		{
			renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXYPN;
			renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXYPN;
		}
		else
		{
			renderer.aoLightValueScratchXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z - 1);
			renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z - 1);
		}
		
		if (!flag5 && !flag3)
		{
			renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXYPN;
			renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXYPN;
		}
		else
		{
			renderer.aoLightValueScratchXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z + 1);
			renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z + 1);
		}
		
		i1 = l;
		
		if (renderer.renderMinY <= 0.0D || !renderer.blockAccess.getBlock(x, y - 1, z).isOpaqueCube())
		{
			i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
		}
		
		f7 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
		f3 = (renderer.aoLightValueScratchXYZNNP + renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchYZNP + f7) / 4.0F;
		f6 = (renderer.aoLightValueScratchYZNP + f7 + renderer.aoLightValueScratchXYZPNP + renderer.aoLightValueScratchXYPN) / 4.0F;
		f5 = (f7 + renderer.aoLightValueScratchYZNN + renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXYZPNN) / 4.0F;
		f4 = (renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchXYZNNN + f7 + renderer.aoLightValueScratchYZNN) / 4.0F;
		renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXYNN, renderer.aoBrightnessYZNP, i1);
		renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessYZNP, renderer.aoBrightnessXYZPNP, renderer.aoBrightnessXYPN, i1);
		renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessYZNN, renderer.aoBrightnessXYPN, renderer.aoBrightnessXYZPNN, i1);
		renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessXYNN, renderer.aoBrightnessXYZNNN, renderer.aoBrightnessYZNN, i1);
		
		renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.5F;
		renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.5F;
		renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.5F;
		
		renderer.colorRedTopLeft *= f3;
		renderer.colorGreenTopLeft *= f3;
		renderer.colorBlueTopLeft *= f3;
		renderer.colorRedBottomLeft *= f4;
		renderer.colorGreenBottomLeft *= f4;
		renderer.colorBlueBottomLeft *= f4;
		renderer.colorRedBottomRight *= f5;
		renderer.colorGreenBottomRight *= f5;
		renderer.colorBlueBottomRight *= f5;
		renderer.colorRedTopRight *= f6;
		renderer.colorGreenTopRight *= f6;
		renderer.colorBlueTopRight *= f6;
		renderer.renderFaceYNeg(block, x, y - offset, z, icon);
		y--;
		y--;
		renderer.aoBrightnessXYNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
		renderer.aoBrightnessXYPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
		renderer.aoBrightnessYZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
		renderer.aoBrightnessYZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
		renderer.aoLightValueScratchXYNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
		renderer.aoLightValueScratchXYPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
		renderer.aoLightValueScratchYZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
		renderer.aoLightValueScratchYZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
		flag3 = renderer.blockAccess.getBlock(x + 1, y + 1, z).getCanBlockGrass();
		flag2 = renderer.blockAccess.getBlock(x - 1, y + 1, z).getCanBlockGrass();
		flag5 = renderer.blockAccess.getBlock(x, y + 1, z + 1).getCanBlockGrass();
		flag4 = renderer.blockAccess.getBlock(x, y + 1, z - 1).getCanBlockGrass();
		
		if (!flag4 && !flag2)
		{
			renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXYNP;
			renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXYNP;
		}
		else
		{
			renderer.aoLightValueScratchXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z - 1);
			renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z - 1);
		}
		
		if (!flag4 && !flag3)
		{
			renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXYPP;
			renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXYPP;
		}
		else
		{
			renderer.aoLightValueScratchXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z - 1);
			renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z - 1);
		}
		
		if (!flag5 && !flag2)
		{
			renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXYNP;
			renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXYNP;
		}
		else
		{
			renderer.aoLightValueScratchXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z + 1);
			renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z + 1);
		}
		
		if (!flag5 && !flag3)
		{
			renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXYPP;
			renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXYPP;
		}
		else
		{
			renderer.aoLightValueScratchXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z + 1);
			renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z + 1);
		}
		
		i1 = l;
		
		if (renderer.renderMaxY >= 1.0D || !renderer.blockAccess.getBlock(x, y + 1, z).isOpaqueCube())
		{
			i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
		}
		
		f7 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
		f6 = (renderer.aoLightValueScratchXYZNPP + renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchYZPP + f7) / 4.0F;
		f3 = (renderer.aoLightValueScratchYZPP + f7 + renderer.aoLightValueScratchXYZPPP + renderer.aoLightValueScratchXYPP) / 4.0F;
		f4 = (f7 + renderer.aoLightValueScratchYZPN + renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPN) / 4.0F;
		f5 = (renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchXYZNPN + f7 + renderer.aoLightValueScratchYZPN) / 4.0F;
		renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXYZNPP, renderer.aoBrightnessXYNP, renderer.aoBrightnessYZPP, i1);
		renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessYZPP, renderer.aoBrightnessXYZPPP, renderer.aoBrightnessXYPP, i1);
		renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessYZPN, renderer.aoBrightnessXYPP, renderer.aoBrightnessXYZPPN, i1);
		renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessXYNP, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessYZPN, i1);
		renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 1;
		renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 1;
		renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 1;
		renderer.colorRedTopLeft *= f3;
		renderer.colorGreenTopLeft *= f3;
		renderer.colorBlueTopLeft *= f3;
		renderer.colorRedBottomLeft *= f4;
		renderer.colorGreenBottomLeft *= f4;
		renderer.colorBlueBottomLeft *= f4;
		renderer.colorRedBottomRight *= f5;
		renderer.colorGreenBottomRight *= f5;
		renderer.colorBlueBottomRight *= f5;
		renderer.colorRedTopRight *= f6;
		renderer.colorGreenTopRight *= f6;
		renderer.colorBlueTopRight *= f6;
		renderer.renderFaceYPos(block, x, y + offset, z, icon);
		y++;
		z++;
		renderer.aoLightValueScratchXZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
		renderer.aoLightValueScratchYZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
		renderer.aoLightValueScratchYZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
		renderer.aoLightValueScratchXZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
		renderer.aoBrightnessXZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
		renderer.aoBrightnessYZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
		renderer.aoBrightnessYZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
		renderer.aoBrightnessXZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
		flag3 = renderer.blockAccess.getBlock(x + 1, y, z - 1).getCanBlockGrass();
		flag2 = renderer.blockAccess.getBlock(x - 1, y, z - 1).getCanBlockGrass();
		flag5 = renderer.blockAccess.getBlock(x, y + 1, z - 1).getCanBlockGrass();
		flag4 = renderer.blockAccess.getBlock(x, y - 1, z - 1).getCanBlockGrass();
		
		if (!flag2 && !flag4)
		{
			renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXZNN;
			renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXZNN;
		}
		else
		{
			renderer.aoLightValueScratchXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y - 1, z);
			renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y - 1, z);
		}
		
		if (!flag2 && !flag5)
		{
			renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXZNN;
			renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXZNN;
		}
		else
		{
			renderer.aoLightValueScratchXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y + 1, z);
			renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y + 1, z);
		}
		
		if (!flag3 && !flag4)
		{
			renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXZPN;
			renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXZPN;
		}
		else
		{
			renderer.aoLightValueScratchXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y - 1, z);
			renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y - 1, z);
		}
		
		if (!flag3 && !flag5)
		{
			renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXZPN;
			renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXZPN;
		}
		else
		{
			renderer.aoLightValueScratchXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y + 1, z);
			renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y + 1, z);
		}
		
		i1 = l;
		
		if (renderer.renderMinZ <= 0.0D || !renderer.blockAccess.getBlock(x, y, z - 1).isOpaqueCube())
		{
			i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
		}
		
		f7 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
		f3 = (renderer.aoLightValueScratchXZNN + renderer.aoLightValueScratchXYZNPN + f7 + renderer.aoLightValueScratchYZPN) / 4.0F;
		f4 = (f7 + renderer.aoLightValueScratchYZPN + renderer.aoLightValueScratchXZPN + renderer.aoLightValueScratchXYZPPN) / 4.0F;
		f5 = (renderer.aoLightValueScratchYZNN + f7 + renderer.aoLightValueScratchXYZPNN + renderer.aoLightValueScratchXZPN) / 4.0F;
		f6 = (renderer.aoLightValueScratchXYZNNN + renderer.aoLightValueScratchXZNN + renderer.aoLightValueScratchYZNN + f7) / 4.0F;
		renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXZNN, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessYZPN, i1);
		renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessYZPN, renderer.aoBrightnessXZPN, renderer.aoBrightnessXYZPPN, i1);
		renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessYZNN, renderer.aoBrightnessXYZPNN, renderer.aoBrightnessXZPN, i1);
		renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXYZNNN, renderer.aoBrightnessXZNN, renderer.aoBrightnessYZNN, i1);
		
		renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.8F;
		renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.8F;
		renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.8F;
		
		renderer.colorRedTopLeft *= f3;
		renderer.colorGreenTopLeft *= f3;
		renderer.colorBlueTopLeft *= f3;
		renderer.colorRedBottomLeft *= f4;
		renderer.colorGreenBottomLeft *= f4;
		renderer.colorBlueBottomLeft *= f4;
		renderer.colorRedBottomRight *= f5;
		renderer.colorGreenBottomRight *= f5;
		renderer.colorBlueBottomRight *= f5;
		renderer.colorRedTopRight *= f6;
		renderer.colorGreenTopRight *= f6;
		renderer.colorBlueTopRight *= f6;
		renderer.renderFaceZNeg(block, x, y, z - offset, icon);
		z--;
		z--;
		renderer.aoLightValueScratchXZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
		renderer.aoLightValueScratchXZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
		renderer.aoLightValueScratchYZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
		renderer.aoLightValueScratchYZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
		renderer.aoBrightnessXZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
		renderer.aoBrightnessXZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
		renderer.aoBrightnessYZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
		renderer.aoBrightnessYZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
		flag3 = renderer.blockAccess.getBlock(x + 1, y, z + 1).getCanBlockGrass();
		flag2 = renderer.blockAccess.getBlock(x - 1, y, z + 1).getCanBlockGrass();
		flag5 = renderer.blockAccess.getBlock(x, y + 1, z + 1).getCanBlockGrass();
		flag4 = renderer.blockAccess.getBlock(x, y - 1, z + 1).getCanBlockGrass();
		
		if (!flag2 && !flag4)
		{
			renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXZNP;
			renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXZNP;
		}
		else
		{
			renderer.aoLightValueScratchXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y - 1, z);
			renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y - 1, z);
		}
		
		if (!flag2 && !flag5)
		{
			renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXZNP;
			renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXZNP;
		}
		else
		{
			renderer.aoLightValueScratchXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y + 1, z);
			renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y + 1, z);
		}
		
		if (!flag3 && !flag4)
		{
			renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXZPP;
			renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXZPP;
		}
		else
		{
			renderer.aoLightValueScratchXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y - 1, z);
			renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y - 1, z);
		}
		
		if (!flag3 && !flag5)
		{
			renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXZPP;
			renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXZPP;
		}
		else
		{
			renderer.aoLightValueScratchXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y + 1, z);
			renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y + 1, z);
		}
		
		i1 = l;
		
		if (renderer.renderMaxZ >= 1.0D || !renderer.blockAccess.getBlock(x, y, z + 1).isOpaqueCube())
		{
			i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
		}
		
		f7 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
		f3 = (renderer.aoLightValueScratchXZNP + renderer.aoLightValueScratchXYZNPP + f7 + renderer.aoLightValueScratchYZPP) / 4.0F;
		f6 = (f7 + renderer.aoLightValueScratchYZPP + renderer.aoLightValueScratchXZPP + renderer.aoLightValueScratchXYZPPP) / 4.0F;
		f5 = (renderer.aoLightValueScratchYZNP + f7 + renderer.aoLightValueScratchXYZPNP + renderer.aoLightValueScratchXZPP) / 4.0F;
		f4 = (renderer.aoLightValueScratchXYZNNP + renderer.aoLightValueScratchXZNP + renderer.aoLightValueScratchYZNP + f7) / 4.0F;
		renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXZNP, renderer.aoBrightnessXYZNPP, renderer.aoBrightnessYZPP, i1);
		renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessYZPP, renderer.aoBrightnessXZPP, renderer.aoBrightnessXYZPPP, i1);
		renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessYZNP, renderer.aoBrightnessXYZPNP, renderer.aoBrightnessXZPP, i1);
		renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXZNP, renderer.aoBrightnessYZNP, i1);
		
		renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.8F;
		renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.8F;
		renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.8F;
		
		renderer.colorRedTopLeft *= f3;
		renderer.colorGreenTopLeft *= f3;
		renderer.colorBlueTopLeft *= f3;
		renderer.colorRedBottomLeft *= f4;
		renderer.colorGreenBottomLeft *= f4;
		renderer.colorBlueBottomLeft *= f4;
		renderer.colorRedBottomRight *= f5;
		renderer.colorGreenBottomRight *= f5;
		renderer.colorBlueBottomRight *= f5;
		renderer.colorRedTopRight *= f6;
		renderer.colorGreenTopRight *= f6;
		renderer.colorBlueTopRight *= f6;
		renderer.renderFaceZPos(block, x, y, z + offset, icon);
		z++;
		x++;
		renderer.aoLightValueScratchXYNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
		renderer.aoLightValueScratchXZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
		renderer.aoLightValueScratchXZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
		renderer.aoLightValueScratchXYNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
		renderer.aoBrightnessXYNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
		renderer.aoBrightnessXZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
		renderer.aoBrightnessXZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
		renderer.aoBrightnessXYNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
		flag3 = renderer.blockAccess.getBlock(x - 1, y + 1, z).getCanBlockGrass();
		flag2 = renderer.blockAccess.getBlock(x - 1, y - 1, z).getCanBlockGrass();
		flag5 = renderer.blockAccess.getBlock(x - 1, y, z - 1).getCanBlockGrass();
		flag4 = renderer.blockAccess.getBlock(x - 1, y, z + 1).getCanBlockGrass();
		
		if (!flag5 && !flag2)
		{
			renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXZNN;
			renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXZNN;
		}
		else
		{
			renderer.aoLightValueScratchXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z - 1);
			renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z - 1);
		}
		
		if (!flag4 && !flag2)
		{
			renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXZNP;
			renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXZNP;
		}
		else
		{
			renderer.aoLightValueScratchXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z + 1);
			renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z + 1);
		}
		
		if (!flag5 && !flag3)
		{
			renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXZNN;
			renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXZNN;
		}
		else
		{
			renderer.aoLightValueScratchXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z - 1);
			renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z - 1);
		}
		
		if (!flag4 && !flag3)
		{
			renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXZNP;
			renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXZNP;
		}
		else
		{
			renderer.aoLightValueScratchXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z + 1);
			renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z + 1);
		}
		
		i1 = l;
		
		if (renderer.renderMinX <= 0.0D || !renderer.blockAccess.getBlock(x - 1, y, z).isOpaqueCube())
		{
			i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
		}
		
		f7 = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
		f6 = (renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchXYZNNP + f7 + renderer.aoLightValueScratchXZNP) / 4.0F;
		f3 = (f7 + renderer.aoLightValueScratchXZNP + renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchXYZNPP) / 4.0F;
		f4 = (renderer.aoLightValueScratchXZNN + f7 + renderer.aoLightValueScratchXYZNPN + renderer.aoLightValueScratchXYNP) / 4.0F;
		f5 = (renderer.aoLightValueScratchXYZNNN + renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchXZNN + f7) / 4.0F;
		renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXYNN, renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXZNP, i1);
		renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXZNP, renderer.aoBrightnessXYNP, renderer.aoBrightnessXYZNPP, i1);
		renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessXZNN, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessXYNP, i1);
		renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessXYZNNN, renderer.aoBrightnessXYNN, renderer.aoBrightnessXZNN, i1);
		
		renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.6F;
		renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.6F;
		renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.6F;
		
		renderer.colorRedTopLeft *= f3;
		renderer.colorGreenTopLeft *= f3;
		renderer.colorBlueTopLeft *= f3;
		renderer.colorRedBottomLeft *= f4;
		renderer.colorGreenBottomLeft *= f4;
		renderer.colorBlueBottomLeft *= f4;
		renderer.colorRedBottomRight *= f5;
		renderer.colorGreenBottomRight *= f5;
		renderer.colorBlueBottomRight *= f5;
		renderer.colorRedTopRight *= f6;
		renderer.colorGreenTopRight *= f6;
		renderer.colorBlueTopRight *= f6;
		renderer.renderFaceXNeg(block, x - offset, y, z, icon);
		x--;
		x--;
		renderer.aoLightValueScratchXYPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
		renderer.aoLightValueScratchXZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
		renderer.aoLightValueScratchXZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
		renderer.aoLightValueScratchXYPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
		renderer.aoBrightnessXYPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
		renderer.aoBrightnessXZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
		renderer.aoBrightnessXZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
		renderer.aoBrightnessXYPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
		flag3 = renderer.blockAccess.getBlock(x + 1, y + 1, z).getCanBlockGrass();
		flag2 = renderer.blockAccess.getBlock(x + 1, y - 1, z).getCanBlockGrass();
		flag5 = renderer.blockAccess.getBlock(x + 1, y, z + 1).getCanBlockGrass();
		flag4 = renderer.blockAccess.getBlock(x + 1, y, z - 1).getCanBlockGrass();
		
		if (!flag2 && !flag4)
		{
			renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXZPN;
			renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXZPN;
		}
		else
		{
			renderer.aoLightValueScratchXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z - 1);
			renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z - 1);
		}
		
		if (!flag2 && !flag5)
		{
			renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXZPP;
			renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXZPP;
		}
		else
		{
			renderer.aoLightValueScratchXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z + 1);
			renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z + 1);
		}
		
		if (!flag3 && !flag4)
		{
			renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXZPN;
			renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXZPN;
		}
		else
		{
			renderer.aoLightValueScratchXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z - 1);
			renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z - 1);
		}
		
		if (!flag3 && !flag5)
		{
			renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXZPP;
			renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXZPP;
		}
		else
		{
			renderer.aoLightValueScratchXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z + 1);
			renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z + 1);
		}
		
		i1 = l;
		
		if (renderer.renderMaxX >= 1.0D || !renderer.blockAccess.getBlock(x + 1, y, z).isOpaqueCube())
		{
			i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
		}
		
		f7 = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
		f3 = (renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXYZPNP + f7 + renderer.aoLightValueScratchXZPP) / 4.0F;
		f4 = (renderer.aoLightValueScratchXYZPNN + renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXZPN + f7) / 4.0F;
		f5 = (renderer.aoLightValueScratchXZPN + f7 + renderer.aoLightValueScratchXYZPPN + renderer.aoLightValueScratchXYPP) / 4.0F;
		f6 = (f7 + renderer.aoLightValueScratchXZPP + renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPP) / 4.0F;
		renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXYPN, renderer.aoBrightnessXYZPNP, renderer.aoBrightnessXZPP, i1);
		renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXZPP, renderer.aoBrightnessXYPP, renderer.aoBrightnessXYZPPP, i1);
		renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessXZPN, renderer.aoBrightnessXYZPPN, renderer.aoBrightnessXYPP, i1);
		renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessXYZPNN, renderer.aoBrightnessXYPN, renderer.aoBrightnessXZPN, i1);
		
		renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.6F;
		renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.6F;
		renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.6F;
		
		renderer.colorRedTopLeft *= f3;
		renderer.colorGreenTopLeft *= f3;
		renderer.colorBlueTopLeft *= f3;
		renderer.colorRedBottomLeft *= f4;
		renderer.colorGreenBottomLeft *= f4;
		renderer.colorBlueBottomLeft *= f4;
		renderer.colorRedBottomRight *= f5;
		renderer.colorGreenBottomRight *= f5;
		renderer.colorBlueBottomRight *= f5;
		renderer.colorRedTopRight *= f6;
		renderer.colorGreenTopRight *= f6;
		renderer.colorBlueTopRight *= f6;
		renderer.renderFaceXPos(block, x + offset, y, z, icon);
		x++;
		
		renderer.enableAO = true;
		
		return true;
	}
	
	@Override
	public int getRenderId()
	{
		return 485;
	}
	
	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return true;
	}
}