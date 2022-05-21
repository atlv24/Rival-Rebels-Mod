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
package assets.rivalrebels.client.model;

import org.lwjgl.opengl.GL11;

import assets.rivalrebels.client.renderhelper.RenderHelper;
import assets.rivalrebels.client.renderhelper.TextureFace;
import assets.rivalrebels.client.renderhelper.TextureVertice;
import assets.rivalrebels.client.renderhelper.Vertice;

public class ModelPlasmaCannon
{
	TextureFace	bodytop				= new TextureFace(
											new TextureVertice(14f / 64f, 23f / 32f),
											new TextureVertice(37f / 64f, 23f / 32f),
											new TextureVertice(37f / 64f, 29f / 32f),
											new TextureVertice(14f / 64f, 29f / 32f));
	
	TextureFace	bodytopfront		= new TextureFace(
											new TextureVertice(37f / 64f, 29f / 32f),
											new TextureVertice(43f / 64f, 29f / 32f),
											new TextureVertice(43f / 64f, 23f / 32f),
											new TextureVertice(37f / 64f, 23f / 32f));
	
	TextureFace	bodytopback			= new TextureFace(
											new TextureVertice(10f / 64f, 20f / 32f),
											new TextureVertice(14f / 64f, 23f / 32f),
											new TextureVertice(14f / 64f, 29f / 32f),
											new TextureVertice(10f / 64f, 32f / 32f));
	
	TextureFace	bodyback			= new TextureFace(
											new TextureVertice(7f / 64f, 20f / 32f),
											new TextureVertice(10f / 64f, 20f / 32f),
											new TextureVertice(10f / 64f, 32f / 32f),
											new TextureVertice(7f / 64f, 32f / 32f));
	
	TextureFace	bodybottomback		= new TextureFace(
											new TextureVertice(0f / 64f, 23f / 32f),
											new TextureVertice(7f / 64f, 20f / 32f),
											new TextureVertice(7f / 64f, 32f / 32f),
											new TextureVertice(0f / 64f, 29f / 32f));
	
	TextureFace	bodybottom			= new TextureFace(
											new TextureVertice(7f / 64f, 11f / 32f),
											new TextureVertice(27f / 64f, 11f / 32f),
											new TextureVertice(27f / 64f, 17f / 32f),
											new TextureVertice(7f / 64f, 17f / 32f));
	
	TextureFace	bodybottomfront		= new TextureFace(
											new TextureVertice(27f / 64f, 11f / 32f),
											new TextureVertice(33f / 64f, 11f / 32f),
											new TextureVertice(33f / 64f, 17f / 32f),
											new TextureVertice(27f / 64f, 17f / 32f));
	
	TextureFace	bodytopside			= new TextureFace(
											new TextureVertice(0f / 64f, 4f / 32f),
											new TextureVertice(4f / 64f, 0f / 32f),
											new TextureVertice(27f / 64f, 0f / 32f),
											new TextureVertice(25f / 64f, 4f / 32f));
	
	TextureFace	bodyside			= new TextureFace(
											new TextureVertice(0f / 64f, 7f / 32f),
											new TextureVertice(0f / 64f, 4f / 32f),
											new TextureVertice(25f / 64f, 4f / 32f),
											new TextureVertice(25f / 64f, 7f / 32f));
	
	TextureFace	bodybottomside		= new TextureFace(
											new TextureVertice(0f / 64f, 7f / 32f),
											new TextureVertice(25f / 64f, 7f / 32f),
											new TextureVertice(27f / 64f, 11f / 32f),
											new TextureVertice(7f / 64f, 11f / 32f));
	
	TextureFace	bodysidefront		= new TextureFace(
											new TextureVertice(27f / 64f, 7f / 32f),
											new TextureVertice(27f / 64f, 4f / 32f),
											new TextureVertice(33f / 64f, 4f / 32f),
											new TextureVertice(31f / 64f, 7f / 32f));
	
	TextureFace	bodysidefrontedge	= new TextureFace(
											new TextureVertice(25f / 64f, 7f / 32f),
											new TextureVertice(25f / 64f, 4f / 32f),
											new TextureVertice(27f / 64f, 4f / 32f),
											new TextureVertice(27f / 64f, 7f / 32f));
	
	TextureFace	bodytopfrontside	= new TextureFace(
											new TextureVertice(37f / 64f, 23f / 32f),
											new TextureVertice(43f / 64f, 23f / 32f),
											new TextureVertice(37f / 64f, 19f / 32f),
											new TextureVertice(37f / 64f, 19f / 32f));
	
	TextureFace	detailpurple		= new TextureFace(
											new TextureVertice(21f / 64f, 23f / 32f),
											new TextureVertice(30f / 64f, 23f / 32f),
											new TextureVertice(30f / 64f, 21f / 32f),
											new TextureVertice(21f / 64f, 21f / 32f));
	
	TextureFace	detailedge1			= new TextureFace(
											new TextureVertice(20f / 64f, 23f / 32f),
											new TextureVertice(21f / 64f, 23f / 32f),
											new TextureVertice(21f / 64f, 21f / 32f),
											new TextureVertice(20f / 64f, 21f / 32f));
	
	TextureFace	detailedge2			= new TextureFace(
											new TextureVertice(30f / 64f, 20f / 32f),
											new TextureVertice(30f / 64f, 21f / 32f),
											new TextureVertice(21f / 64f, 21f / 32f),
											new TextureVertice(21f / 64f, 20f / 32f));
	
	TextureFace	handleside			= new TextureFace(
											new TextureVertice(0f / 64f, 11f / 32f),
											new TextureVertice(0f / 64f, 18f / 32f),
											new TextureVertice(4f / 64f, 18f / 32f),
											new TextureVertice(4f / 64f, 11f / 32f));
	
	TextureFace	handlefront			= new TextureFace(
											new TextureVertice(4f / 64f, 11f / 32f),
											new TextureVertice(4f / 64f, 18f / 32f),
											new TextureVertice(7f / 64f, 18f / 32f),
											new TextureVertice(7f / 64f, 11f / 32f));
	
	TextureFace	handlebottom		= new TextureFace(
											new TextureVertice(4f / 64f, 18f / 32f),
											new TextureVertice(0f / 64f, 18f / 32f),
											new TextureVertice(0f / 64f, 21f / 32f),
											new TextureVertice(4f / 64f, 21f / 32f));
	
	TextureFace	bottomside			= new TextureFace(
											new TextureVertice(49f / 64f, 10f / 32f),
											new TextureVertice(46f / 64f, 13f / 32f),
											new TextureVertice(36f / 64f, 13f / 32f),
											new TextureVertice(36f / 64f, 10f / 32f));
	
	TextureFace	bottomfront			= new TextureFace(
											new TextureVertice(46f / 64f, 13f / 32f),
											new TextureVertice(50f / 64f, 13f / 32f),
											new TextureVertice(50f / 64f, 17f / 32f),
											new TextureVertice(46f / 64f, 17f / 32f));
	
	TextureFace	bottombottom		= new TextureFace(
											new TextureVertice(46f / 64f, 17f / 32f),
											new TextureVertice(36f / 64f, 17f / 32f),
											new TextureVertice(36f / 64f, 13f / 32f),
											new TextureVertice(46f / 64f, 13f / 32f));
	
	TextureFace	bottomback			= new TextureFace(
											new TextureVertice(33f / 64f, 13f / 32f),
											new TextureVertice(36f / 64f, 13f / 32f),
											new TextureVertice(36f / 64f, 17f / 32f),
											new TextureVertice(33f / 64f, 17f / 32f));
	
	Vertice		vt1					= new Vertice(3f, 12f, 3f);
	Vertice		vt2					= new Vertice(27f, 12f, 3f);
	Vertice		vt3					= new Vertice(27f, 12f, -3f);
	Vertice		vt4					= new Vertice(3f, 12f, -3f);
	Vertice		vf1					= new Vertice(34f, 9f, 3f);
	Vertice		vf2					= new Vertice(34f, 9f, -3f);
	Vertice		vs1					= new Vertice(0f, 9f, 6f);
	Vertice		vs2					= new Vertice(0f, 6f, 6f);
	Vertice		vs3					= new Vertice(25f, 6f, 6f);
	Vertice		vs4					= new Vertice(25f, 9f, 6f);
	Vertice		vs5					= new Vertice(0f, 9f, -6f);
	Vertice		vs6					= new Vertice(0f, 6f, -6f);
	Vertice		vs7					= new Vertice(25f, 6f, -6f);
	Vertice		vs8					= new Vertice(25f, 9f, -6f);
	Vertice		vfs1				= new Vertice(25f, 6f, 4f);
	Vertice		vfs2				= new Vertice(25f, 9f, 4f);
	Vertice		vfs3				= new Vertice(25f, 6f, -4f);
	Vertice		vfs4				= new Vertice(25f, 9f, -4f);
	Vertice		vff1				= new Vertice(30f, 6f, 0f);
	Vertice		vff2				= new Vertice(32f, 9f, 0f);
	Vertice		vb1					= new Vertice(7f, 3f, 3f);
	Vertice		vb2					= new Vertice(27f, 3f, 3f);
	Vertice		vb3					= new Vertice(27f, 3f, -3f);
	Vertice		vb4					= new Vertice(7f, 3f, -3f);
	Vertice		vfb1				= new Vertice(31f, 6f, 3f);
	Vertice		vfb2				= new Vertice(31f, 6f, -3f);
	Vertice		vdt1				= new Vertice(10.25f, 12f, 1f);
	Vertice		vdt2				= new Vertice(19.75f, 12f, 1f);
	Vertice		vdt3				= new Vertice(19.75f, 12f, -1f);
	Vertice		vdt4				= new Vertice(10.25f, 12f, -1f);
	Vertice		vdb1				= new Vertice(10.25f, 11.5f, 1f);
	Vertice		vdb2				= new Vertice(19.75f, 11.5f, 1f);
	Vertice		vdb3				= new Vertice(19.75f, 11.5f, -1f);
	Vertice		vdb4				= new Vertice(10.25f, 11.5f, -1f);
	Vertice		vht1				= new Vertice(11f, 0f, 2f);
	Vertice		vht2				= new Vertice(15f, 0f, 2f);
	Vertice		vht3				= new Vertice(15f, 0f, -2f);
	Vertice		vht4				= new Vertice(11f, 0f, -2f);
	Vertice		vhb1				= new Vertice(7f, -7f, 2f);
	Vertice		vhb2				= new Vertice(11f, -7f, 2f);
	Vertice		vhb3				= new Vertice(11f, -7f, -2f);
	Vertice		vhb4				= new Vertice(7f, -7f, -2f);
	Vertice		vbt1				= new Vertice(8f, 3f, 2f);
	Vertice		vbt2				= new Vertice(23f, 3f, 2f);
	Vertice		vbt3				= new Vertice(23f, 3f, -2f);
	Vertice		vbt4				= new Vertice(8f, 3f, -2f);
	Vertice		vbb1				= new Vertice(8f, 0f, 2f);
	Vertice		vbb2				= new Vertice(20f, 0f, 2f);
	Vertice		vbb3				= new Vertice(20f, 0f, -2f);
	Vertice		vbb4				= new Vertice(8f, 0f, -2f);
	
	public void render()
	{
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_CULL_FACE);
		// body
		RenderHelper.addFace(vt1, vt2, vt3, vt4, bodytop);
		RenderHelper.addFace(vs1, vt1, vt2, vs4, bodytopside);
		RenderHelper.addFace(vs5, vt4, vt3, vs8, bodytopside);
		RenderHelper.addFace(vs2, vs1, vs4, vs3, bodyside);
		RenderHelper.addFace(vs6, vs5, vs8, vs7, bodyside);
		RenderHelper.addFace(vs1, vt1, vt4, vs5, bodytopback);
		RenderHelper.addFace(vs2, vs1, vs5, vs6, bodyback);
		RenderHelper.addFace(vt2, vf1, vf2, vt3, bodytopfront);
		RenderHelper.addFace(vt2, vf1, vs4, vs4, bodytopfrontside);
		RenderHelper.addFace(vt3, vf2, vs8, vs8, bodytopfrontside);
		RenderHelper.addFace(vs3, vs4, vfs2, vfs1, bodysidefrontedge);
		RenderHelper.addFace(vs7, vs8, vfs4, vfs3, bodysidefrontedge);
		RenderHelper.addFace(vf2, vs8, vs4, vf1, bodybottomback);
		RenderHelper.addFace(vfs2, vfs1, vff1, vff2, bodysidefront);
		RenderHelper.addFace(vfs4, vfs3, vff1, vff2, bodysidefront);
		RenderHelper.addFace(vb1, vb2, vb3, vb4, bodybottom);
		RenderHelper.addFace(vs2, vs3, vb2, vb1, bodybottomside);
		RenderHelper.addFace(vs6, vs7, vb3, vb4, bodybottomside);
		RenderHelper.addFace(vb2, vfb1, vs3, vs3, bodytopfrontside);
		RenderHelper.addFace(vb3, vfb2, vs7, vs7, bodytopfrontside);
		RenderHelper.addFace(vb2, vfb1, vfb2, vb3, bodybottomfront);
		RenderHelper.addFace(vb1, vs2, vs6, vb4, bodybottomback);
		RenderHelper.addFace(vfb2, vs7, vs3, vfb1, bodybottomback);
		
		// bottom
		RenderHelper.addFace(vbb1, vbt1, vbt4, vbb4, bottomfront);
		RenderHelper.addFace(vbb3, vbt3, vbt2, vbb2, bottomback);
		RenderHelper.addFace(vbt2, vbb2, vbb1, vbt1, bottomside);
		RenderHelper.addFace(vbt3, vbb3, vbb4, vbt4, bottomside);
		RenderHelper.addFace(vbb3, vbb4, vbb1, vbb2, bottombottom);
		
		// detail
		RenderHelper.addFace(vdt4, vdb4, vdb1, vdt1, detailedge1);
		RenderHelper.addFace(vdt3, vdb3, vdb2, vdt2, detailedge1);
		RenderHelper.addFace(vdt2, vdb2, vdb1, vdt1, detailedge2);
		RenderHelper.addFace(vdt4, vdb4, vdb3, vdt3, detailedge2);
		RenderHelper.addFace(vdb1, vdb2, vdb3, vdb4, detailpurple);
		
		// handle
		RenderHelper.addFace(vht4, vhb4, vhb1, vht1, handlefront);
		RenderHelper.addFace(vht2, vhb2, vhb3, vht3, handlefront);
		RenderHelper.addFace(vht1, vhb1, vhb2, vht2, handleside);
		RenderHelper.addFace(vht3, vhb3, vhb4, vht4, handleside);
		RenderHelper.addFace(vhb2, vhb1, vhb4, vhb3, handlebottom);
		GL11.glPopMatrix();
	}
}
