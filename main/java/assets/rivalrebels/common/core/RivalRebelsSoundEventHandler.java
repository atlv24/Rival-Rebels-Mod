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
package assets.rivalrebels.common.core;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * Raised by the SoundManager.loadSoundSettings, this would be a good place for adding your custom sounds to the SoundPool.
 */
public class RivalRebelsSoundEventHandler
{
	@SubscribeEvent
	public void onSoundLoad(SoundLoadEvent event)
	{
		String[] soundFiles = {
				// artillery
				"aa/a1.ogg",
				"aa/a2.ogg",
				"aa/b1.ogg",
				"aa/b2.ogg",
				"aa/b3.ogg",
				"aa/c.ogg",
				"aa/d1.ogg",
				"aa/d2.ogg",
				"aa/d3.ogg",
				// autobuild
				"ab/a1.ogg",
				"ab/a2.ogg",
				// blaster
				"ac/a.ogg",
				"ac/b1.ogg",
				"ac/b2.ogg",
				"ac/b3.ogg",
				"ac/c.ogg",
				"ac/d.ogg",
				// crate
				"ad/a1.ogg",
				"ad/a2.ogg",
				"ad/a3.ogg",
				"ad/a4.ogg",
				// cuchillo
				"ae/a1.ogg",
				"ae/a2.ogg",
				"ae/a3.ogg",
				"ae/b1.ogg",
				"ae/b2.ogg",
				"ae/b3.ogg",
				"ae/b4.ogg",
				"ae/c1.ogg",
				"ae/c2.ogg",
				"ae/c3.ogg",
				"ae/d1.ogg",
				"ae/d2.ogg",
				"ae/d3.ogg",
				"ae/e.ogg",
				// disk
				"af/a.ogg",
				"af/b1.ogg",
				"af/b2.ogg",
				"af/b3.ogg",
				"af/b4.ogg",
				"af/c.ogg",
				// diskhigh
				"af/a/a.ogg",
				"af/a/b.ogg",
				"af/a/c.ogg",
				"af/a/d.ogg",
				// disklow
				"af/b/a.ogg",
				"af/b/b.ogg",
				"af/b/c.ogg",
				"af/b/d.ogg",
				// fire
				"ag/a1.ogg",
				"ag/a2.ogg",
				"ag/b.ogg",
				"ag/c.ogg",
				// grenade
				"ah/a1.ogg",
				"ah/a2.ogg",
				"ah/b.ogg",
				"ah/c.ogg",
				"ah/d1.ogg",
				"ah/d2.ogg",
				"ah/d3.ogg",
				// gui
				"ai/a.ogg",
				"ai/b.ogg",
				"ai/c.ogg",
				"ai/d.ogg",
				"ai/e.ogg",
				"ai/f.ogg",
				"ai/g.ogg",
				"ai/h.ogg",
				"ai/i.ogg",
				// landmine
				"aj/a.ogg",
				"aj/b.ogg",
				// laptop
				"ak/a.ogg",
				"ak/b.ogg",
				"ak/c.ogg",
				// mendeleed
				"al/a.ogg",
				"al/b.ogg",
				// nuke
				"am/a.ogg",
				// pill
				"an/a1.ogg",
				"an/a2.ogg",
				"an/b1.ogg",
				"an/b2.ogg",
				// plasma
				"ao/a.ogg",
				"ao/b.ogg",
				"ao/c.ogg",
				// pliers
				"ap/a1.ogg",
				"ap/a2.ogg",
				"ap/a3.ogg",
				"ap/a4.ogg",
				// precursor
				"aq/a.ogg",
				"aq/b.ogg",
				// printer
				"ar/a.ogg",
				"ar/b.ogg",
				"ar/c.ogg",
				// quicksand
				"as/a1.ogg",
				"as/a2.ogg",
				// reactor
				"at/a.ogg",
				"at/b.ogg",
				"at/c.ogg",
				"at/d.ogg",
				// remote
				"au/a.ogg",
				"au/b1.ogg",
				"au/b2.ogg",
				"au/c.ogg",
				"au/d.ogg",
				// rocket
				"av/a1.ogg",
				"av/a2.ogg",
				"av/b.ogg",
				"av/c1.ogg",
				"av/c2.ogg",
				"av/d.ogg",
				"av/e.ogg",
				// rod
				"aw/a.ogg",
				"aw/b.ogg",
				"aw/c.ogg",
				"aw/d1.ogg",
				"aw/d2.ogg",
				"aw/e.ogg",
				// tesla
				"ax/a.ogg",
				"ax/b1.ogg",
				"ax/b2.ogg",
				"ax/b3.ogg",
				"ax/b4.ogg",
				// timedbomb
				"ay/a1.ogg",
				"ay/a2.ogg",
				"ay/b.ogg",
				"ay/c.ogg",
				// toaster
				"az/a1.ogg",
				"az/a2.ogg",
				// voice
				"ba/a1.ogg",
				"ba/a2.ogg",
				"ba/b.ogg",
				"ba/c.ogg",
				"ba/d.ogg",
				"ba/e.ogg",
				"ba/f.ogg",
				"ba/g.ogg",
				"ba/h.ogg",
				"ba/i.ogg",
				"ba/j.ogg",
				"ba/k.ogg",
				"ba/l.ogg",
				"ba/m.ogg",
				"ba/n.ogg",
				"ba/o.ogg",
				"ba/p.ogg",
				"ba/q.ogg",
				"ba/r.ogg",
				"ba/s.ogg",
		};
		for (int i = 0; i < soundFiles.length; i++)
		{
			 //manager.sndHandler.addSound("rivalrebels:" + soundFiles[i]);
		}
	}
}
