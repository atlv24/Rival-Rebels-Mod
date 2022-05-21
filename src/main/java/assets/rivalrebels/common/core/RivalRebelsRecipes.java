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

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import assets.rivalrebels.RivalRebels;
import cpw.mods.fml.common.registry.GameRegistry;

public class RivalRebelsRecipes
{
	public static void registerRecipes()
	{
		// Crafting
		// Tier 1
		// Ammo
		GameRegistry.addRecipe(new ItemStack(RivalRebels.fuel, 16), new Object[] { "DS", "GC", 'C', Blocks.cobblestone, 'S', Blocks.sand, 'G', Blocks.gravel, 'D', Blocks.dirt });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.rocket, 16), new Object[] { "SC", "DG", 'C', Blocks.cobblestone, 'S', Blocks.sand, 'G', Blocks.gravel, 'D', Blocks.dirt });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.battery, 4), new Object[] { "CG", "SD", 'C', Blocks.cobblestone, 'S', Blocks.sand, 'G', Blocks.gravel, 'D', Blocks.dirt });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.gasgrenade, 6), new Object[] { "GD", "CS", 'C', Blocks.cobblestone, 'S', Blocks.sand, 'G', Blocks.gravel, 'D', Blocks.dirt });
		
		// EasterEgg
		GameRegistry.addRecipe(new ItemStack(RivalRebels.easteregg, 1), new Object[] { "DD", "CC", 'C', Blocks.cobblestone, 'D', Blocks.dirt });
		
		// Armor
		GameRegistry.addRecipe(new ItemStack(RivalRebels.sigmaarmor, 1), new Object[] { "SSC", "SPB", "SSD", 'C', Blocks.cobblestone, 'S', RivalRebels.steel, 'P', RivalRebels.pliers, 'D', Blocks.dirt, 'B', Items.bone });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.omegaarmor, 1), new Object[] { "SSD", "SPB", "SSC", 'C', Blocks.cobblestone, 'S', RivalRebels.steel, 'P', RivalRebels.pliers, 'D', Blocks.dirt, 'B', Items.bone });
		
		// Flagbox
		GameRegistry.addRecipe(new ItemStack(RivalRebels.flagbox1, 1), new Object[] { "W", "P", 'W', Blocks.wool, 'P', RivalRebels.pliers });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.flagbox3, 1), new Object[] { "P", "W", 'W', Blocks.wool, 'P', RivalRebels.pliers });
		
		// Supplies
		GameRegistry.addRecipe(new ItemStack(RivalRebels.armyshovel, 1), new Object[] { "CD", "SG", 'C', Blocks.cobblestone, 'S', Blocks.sand, 'G', Blocks.gravel, 'D', Blocks.dirt });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.amario, 16), new Object[] { "SS", "GG", 'S', Blocks.sand, 'G', Blocks.gravel });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.aquicksand, 16), new Object[] { "SS", "DD", 'S', Blocks.sand, 'D', Blocks.dirt });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.jump, 8), new Object[] { "CC", "DD", 'C', Blocks.cobblestone, 'D', Blocks.dirt });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.steel, 16), new Object[] { "CC", "CC", 'C', Blocks.cobblestone });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.smartcamo, 16), new Object[] { "SCS", "CPC", "SCS", 'C', Blocks.cobblestone, 'S', RivalRebels.steel, 'P', RivalRebels.pliers });
		
		// Explosives
		GameRegistry.addRecipe(new ItemStack(RivalRebels.pliers, 1), new Object[] { " C", "C ", 'C', Blocks.cobblestone });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.roda, 1), new Object[] { "IN", "IH", "IR", 'I', Items.iron_ingot, 'N', RivalRebels.nuclearelement, 'H', RivalRebels.hydrod, 'R', RivalRebels.redrod });
		
		// Weapons
		GameRegistry.addRecipe(new ItemStack(RivalRebels.knife, 5), new Object[] { "CG", "GC", 'C', Blocks.cobblestone, 'G', Blocks.gravel });
		
		// Miscellaneous
		GameRegistry.addRecipe(new ItemStack(RivalRebels.trollmask, 8), new Object[] { "SS", 'S', Blocks.sand });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.cycle, 1), new Object[] { "TT", "TT", 'T', RivalRebels.trollmask });
		
		// End Tier 1
		// Tier 2
		// AutoBuilds
		GameRegistry.addRecipe(new ItemStack(RivalRebels.tower, 2), new Object[] { "SS", "SJ", 'S', RivalRebels.steel, 'J', RivalRebels.jump });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.barricade, 1), new Object[] { "SSS", "LPL", "RCR", 'L', RivalRebels.binoculars, 'P', RivalRebels.pliers, 'S', RivalRebels.supplies, 'R', RivalRebels.reactive, 'C', RivalRebels.conduit });
		
		// Explosives
		GameRegistry.addRecipe(new ItemStack(RivalRebels.timedbomb, 1), new Object[] { "FB", "RP", 'P', RivalRebels.pliers, 'B', RivalRebels.battery, 'R', RivalRebels.rocket, 'F', RivalRebels.fuel });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.remotecharge, 4), new Object[] { "GB", "RF", 'G', RivalRebels.gasgrenade, 'B', RivalRebels.battery, 'R', RivalRebels.rocket, 'F', RivalRebels.fuel });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.remote, 1), new Object[] { "BF", "RP", 'P', RivalRebels.pliers, 'B', RivalRebels.battery, 'R', RivalRebels.rocket, 'F', RivalRebels.fuel });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.alandmine, 16), new Object[] { "DB", "RP", 'D', Blocks.dirt, 'B', RivalRebels.battery, 'P', RivalRebels.pliers, 'R', RivalRebels.rocket });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.flare, 8), new Object[] { "FD", "RD", 'F', RivalRebels.fuel, 'D', Blocks.dirt, 'R', RivalRebels.rocket });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.nukeCrateTop, 1), new Object[] { " SS", "SP ", " SS", 'S', RivalRebels.steel, 'P', RivalRebels.pliers });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.nukeCrateBottom, 1), new Object[] { "SS ", "SP ", "SS ", 'S', RivalRebels.steel, 'P', RivalRebels.pliers });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.fuse, 1), new Object[] { "BP", "SP", 'S', RivalRebels.steel, 'P', RivalRebels.pliers, 'B', RivalRebels.battery });
		
		// Weapons
		GameRegistry.addRecipe(new ItemStack(RivalRebels.rpg, 1), new Object[] { "RRR", "BP ", "SSS", 'S', RivalRebels.steel, 'R', RivalRebels.rocket, 'B', RivalRebels.battery, 'P', RivalRebels.pliers });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.flamethrower, 1), new Object[] { "FFF", "BP ", "SSS", 'S', RivalRebels.steel, 'F', RivalRebels.fuel, 'B', RivalRebels.battery, 'P', RivalRebels.pliers });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.tesla, 1), new Object[] { "BBB", "BP ", "SSS", 'B', RivalRebels.battery, 'S', RivalRebels.steel, 'P', RivalRebels.pliers });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.plasmacannon, 1), new Object[] { "HHH", "BP ", "SSS", 'S', RivalRebels.steel, 'H', RivalRebels.hydrod, 'B', RivalRebels.battery, 'P', RivalRebels.pliers });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.einsten, 1), new Object[] { "HHH", "BP ", "SSS", 'S', RivalRebels.steel, 'H', RivalRebels.redrod, 'B', RivalRebels.battery, 'P', RivalRebels.pliers });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.roddisk, 1), new Object[] { "HPB", 'H', RivalRebels.hydrod, 'B', RivalRebels.battery, 'P', RivalRebels.pliers });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.hydrod, 1), new Object[] { "W", "S", 'S', RivalRebels.emptyrod, 'W', Items.water_bucket });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.redrod, 1), new Object[] { "W", "S", 'S', RivalRebels.emptyrod, 'W', Items.redstone });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.emptyrod, 4), new Object[] { "SPS", 'S', RivalRebels.steel, 'P', RivalRebels.pliers });
		
		// Supplies
		GameRegistry.addRecipe(new ItemStack(RivalRebels.expill, 4), new Object[] { "STG", " BR", "F W", 'S', Items.wheat_seeds, 'T', RivalRebels.trollmask, 'G', RivalRebels.gasgrenade, 'B', Items.milk_bucket, 'R', RivalRebels.rocket, 'F', RivalRebels.fuel, 'W', Items.water_bucket });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.safepill, 4), new Object[] { "S  ", " B ", "F W", 'S', Items.wheat_seeds, 'B', Items.milk_bucket, 'F', RivalRebels.fuel, 'W', Items.water_bucket });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.breadbox, 1), new Object[] { " S", "GP", " B", 'G', Items.wheat_seeds, 'S', RivalRebels.steel, 'B', Items.water_bucket, 'P', RivalRebels.pliers });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.loader, 1), new Object[] { "SBS", "SPS", "SBS", 'S', RivalRebels.steel, 'B', RivalRebels.battery, 'P', RivalRebels.pliers });
		
		// End Tier 2
		// Tier 3
		// Crates
		GameRegistry.addRecipe(new ItemStack(RivalRebels.ammunition, 1), new Object[] { "SFS", "FPF", "SFS", 'S', RivalRebels.steel, 'P', RivalRebels.pliers, 'F', RivalRebels.emptyrod });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.supplies, 1), new Object[] { "SRS", "APB", "SLS", 'S', RivalRebels.steel, 'P', RivalRebels.pliers, 'R', RivalRebels.remote, 'L', RivalRebels.loader, 'A', RivalRebels.antenna, 'B', RivalRebels.battery });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.explosives, 1), new Object[] { "SFS", "GPG", "SFS", 'S', RivalRebels.steel, 'P', RivalRebels.pliers, 'F', RivalRebels.flare, 'G', RivalRebels.gasgrenade });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.weapons, 1), new Object[] { "SFS", "BPB", "SFS", 'S', RivalRebels.steel, 'P', RivalRebels.pliers, 'B', RivalRebels.ammunition, 'F', RivalRebels.battery });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.antenna, 1), new Object[] { "RB", "SP", 'S', RivalRebels.steel, 'P', RivalRebels.pliers, 'B', RivalRebels.battery, 'R', RivalRebels.remote });

		GameRegistry.addRecipe(new ItemStack(RivalRebels.hackm202, 1), new Object[] { "HFR", " P ", "EMA", 'H', RivalRebels.hydrod, 'P', RivalRebels.pliers, 'A', RivalRebels.ammunition, 'R', RivalRebels.nuclearelement, 'M', RivalRebels.rpg, 'E', RivalRebels.explosives, 'F', RivalRebels.fuse });

		GameRegistry.addRecipe(new ItemStack(RivalRebels.seekm202, 1), new Object[] { "RRR", "BPQ", "SMS", 'S', RivalRebels.steel, 'R', RivalRebels.antenna, 'B', RivalRebels.battery, 'P', RivalRebels.pliers, 'Q', RivalRebels.remote, 'M', RivalRebels.rpg });
		
		// End Tier 3
		// Tier 4
		// Bunker
		GameRegistry.addRecipe(new ItemStack(RivalRebels.chip, 1), new Object[] { " RA", "TPB", "TLB", 'T', RivalRebels.breadbox, 'P', RivalRebels.pliers, 'B', RivalRebels.battery, 'R', RivalRebels.remote, 'L', RivalRebels.loader, 'A', RivalRebels.antenna });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.bunker, 1), new Object[] { "CSC", "SPS", "CSC", 'C', RivalRebels.smartcamo, 'P', RivalRebels.pliers, 'S', RivalRebels.conduit });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.binoculars, 1), new Object[] { " RA", "CPB", " SS", 'S', RivalRebels.steel, 'P', RivalRebels.pliers, 'B', RivalRebels.battery, 'R', RivalRebels.remote, 'C', RivalRebels.chip, 'A', RivalRebels.antenna });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.controller, 1), new Object[] { " RA", "CPB", " SB", 'S', RivalRebels.steel, 'P', RivalRebels.pliers, 'B', RivalRebels.battery, 'R', RivalRebels.remote, 'C', RivalRebels.chip, 'A', RivalRebels.antenna });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.bastion, 1), new Object[] { "RRR", "CPC", "RRR", 'R', RivalRebels.reactive, 'P', RivalRebels.pliers, 'C', RivalRebels.conduit });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.forcefieldnode, 2), new Object[] { "RCR", "HPC", "RCR", 'R', RivalRebels.reactive, 'P', RivalRebels.pliers, 'C', RivalRebels.conduit, 'H', RivalRebels.chip });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.reactive, 4), new Object[] { "CDC", "DBD", "CDC", 'S', RivalRebels.steel, 'H', RivalRebels.battery, 'D', RivalRebels.conduit, 'C', RivalRebels.smartcamo });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.conduit, 16), new Object[] { "CEC", "EBE", "CEC", 'B', RivalRebels.redrod, 'E', RivalRebels.emptyrod, 'C', RivalRebels.steel });
		// End Tier 4
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.core1, 1), new Object[] { " DS", "CPD", " DS", 'S', RivalRebels.steel, 'P', RivalRebels.pliers, 'C', RivalRebels.chip, 'D', RivalRebels.conduit });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.core2, 1), new Object[] { " DS", "CP1", " DS", 'S', RivalRebels.steel, 'P', RivalRebels.pliers, 'C', RivalRebels.chip, 'D', RivalRebels.conduit, '1', RivalRebels.core1 });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.core3, 1), new Object[] { " DS", "CP2", " DS", 'S', RivalRebels.steel, 'P', RivalRebels.pliers, 'C', RivalRebels.chip, 'D', RivalRebels.conduit, '2', RivalRebels.core2 });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.minetrap, 2), new Object[] { "SDS", "DPD", "SDS", 'S', RivalRebels.alandmine, 'P', RivalRebels.pliers, 'D', RivalRebels.conduit });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.quicksandtrap, 2), new Object[] { "SDS", "DPD", "SDS", 'S', RivalRebels.aquicksand, 'P', RivalRebels.pliers, 'D', RivalRebels.conduit });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.mariotrap, 2), new Object[] { "SDS", "DPD", "SDS", 'S', RivalRebels.amario, 'P', RivalRebels.pliers, 'D', RivalRebels.conduit });

		GameRegistry.addRecipe(new ItemStack(RivalRebels.ffreciever, 2), new Object[] { "LPS", "RCR", 'L', RivalRebels.binoculars, 'P', RivalRebels.pliers, 'S', RivalRebels.supplies, 'R', RivalRebels.reactive, 'C', RivalRebels.conduit });
		
		GameRegistry.addRecipe(new ItemStack(RivalRebels.buildrhodes, 2), new Object[] { "SAS", "CPT", "RLB", 'B', RivalRebels.binoculars, 'P', RivalRebels.pliers, 'S', RivalRebels.supplies, 'A', RivalRebels.antenna, 'C', RivalRebels.chip, 'T', RivalRebels.core3, 'R', RivalRebels.controller, 'L', RivalRebels.loader});
	}
}
