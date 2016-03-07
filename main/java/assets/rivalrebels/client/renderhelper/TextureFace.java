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
package assets.rivalrebels.client.renderhelper;

public class TextureFace
{
	TextureVertice	v1;
	TextureVertice	v2;
	TextureVertice	v3;
	TextureVertice	v4;
	
	public TextureFace(TextureVertice V1, TextureVertice V2, TextureVertice V3, TextureVertice V4)
	{
		v1 = V1;
		v2 = V2;
		v3 = V3;
		v4 = V4;
	}
}