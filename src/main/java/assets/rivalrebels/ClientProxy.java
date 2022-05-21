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
package assets.rivalrebels;

import javafx.scene.input.KeyCode;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import assets.rivalrebels.client.blockrenderer.SteelBlockRenderer;
import assets.rivalrebels.client.gui.GuiClass;
import assets.rivalrebels.client.gui.GuiFlameThrower;
import assets.rivalrebels.client.gui.GuiNextBattle;
import assets.rivalrebels.client.gui.GuiOmegaWin;
import assets.rivalrebels.client.gui.GuiSigmaWin;
import assets.rivalrebels.client.gui.GuiSpawn;
import assets.rivalrebels.client.gui.GuiTesla;
import assets.rivalrebels.client.gui.RivalRebelsRenderOverlay;
import assets.rivalrebels.client.renderentity.RenderAntimatterBomb;
import assets.rivalrebels.client.renderentity.RenderAntimatterBombBlast;
import assets.rivalrebels.client.renderentity.RenderB2Frag;
import assets.rivalrebels.client.renderentity.RenderB2Spirit;
import assets.rivalrebels.client.renderentity.RenderB83;
import assets.rivalrebels.client.renderentity.RenderBlood;
import assets.rivalrebels.client.renderentity.RenderBomb;
import assets.rivalrebels.client.renderentity.RenderBullet;
import assets.rivalrebels.client.renderentity.RenderCuchillo;
import assets.rivalrebels.client.renderentity.RenderDebris;
import assets.rivalrebels.client.renderentity.RenderFlame;
import assets.rivalrebels.client.renderentity.RenderFlameBallGreen;
import assets.rivalrebels.client.renderentity.RenderFlameBlue;
import assets.rivalrebels.client.renderentity.RenderFlameRedBlue;
import assets.rivalrebels.client.renderentity.RenderGasGrenade;
import assets.rivalrebels.client.renderentity.RenderGoo;
import assets.rivalrebels.client.renderentity.RenderGore;
import assets.rivalrebels.client.renderentity.RenderHackB83;
import assets.rivalrebels.client.renderentity.RenderHotPotato;
import assets.rivalrebels.client.renderentity.RenderLaptop;
import assets.rivalrebels.client.renderentity.RenderLaserBurst;
import assets.rivalrebels.client.renderentity.RenderLaserLink;
import assets.rivalrebels.client.renderentity.RenderLightningBolt2;
import assets.rivalrebels.client.renderentity.RenderLightningLink;
import assets.rivalrebels.client.renderentity.RenderNuclearBlast;
import assets.rivalrebels.client.renderentity.RenderNuke;
import assets.rivalrebels.client.renderentity.RenderPlasmoid;
import assets.rivalrebels.client.renderentity.RenderRhodes;
import assets.rivalrebels.client.renderentity.RenderRhodesHead;
import assets.rivalrebels.client.renderentity.RenderRhodesLeftLowerArm;
import assets.rivalrebels.client.renderentity.RenderRhodesLeftLowerLeg;
import assets.rivalrebels.client.renderentity.RenderRhodesLeftUpperArm;
import assets.rivalrebels.client.renderentity.RenderRhodesLeftUpperLeg;
import assets.rivalrebels.client.renderentity.RenderRhodesRightLowerArm;
import assets.rivalrebels.client.renderentity.RenderRhodesRightLowerLeg;
import assets.rivalrebels.client.renderentity.RenderRhodesRightUpperArm;
import assets.rivalrebels.client.renderentity.RenderRhodesRightUpperLeg;
import assets.rivalrebels.client.renderentity.RenderRhodesTorso;
import assets.rivalrebels.client.renderentity.RenderRocket;
import assets.rivalrebels.client.renderentity.RenderRoddiskLeader;
import assets.rivalrebels.client.renderentity.RenderRoddiskOfficer;
import assets.rivalrebels.client.renderentity.RenderRoddiskRebel;
import assets.rivalrebels.client.renderentity.RenderRoddiskRegular;
import assets.rivalrebels.client.renderentity.RenderRoddiskRep;
import assets.rivalrebels.client.renderentity.RenderSeeker;
import assets.rivalrebels.client.renderentity.RenderSphereBlast;
import assets.rivalrebels.client.renderentity.RenderTachyonBomb;
import assets.rivalrebels.client.renderentity.RenderTachyonBombBlast;
import assets.rivalrebels.client.renderentity.RenderTheoreticalTsar;
import assets.rivalrebels.client.renderentity.RenderTheoreticalTsarBlast;
import assets.rivalrebels.client.renderentity.RenderTsar;
import assets.rivalrebels.client.renderentity.RenderTsarBlast;
import assets.rivalrebels.client.tileentityrender.TileEntityAntimatterBombRenderer;
import assets.rivalrebels.client.tileentityrender.TileEntityForceFieldNodeRenderer;
import assets.rivalrebels.client.tileentityrender.TileEntityGoreRenderer;
import assets.rivalrebels.client.tileentityrender.TileEntityJumpBlockRenderer;
import assets.rivalrebels.client.tileentityrender.TileEntityLaptopRenderer;
import assets.rivalrebels.client.tileentityrender.TileEntityLoaderRenderer;
import assets.rivalrebels.client.tileentityrender.TileEntityMeltdownRenderer;
import assets.rivalrebels.client.tileentityrender.TileEntityNuclearBombRenderer;
import assets.rivalrebels.client.tileentityrender.TileEntityNukeCrateRenderer;
import assets.rivalrebels.client.tileentityrender.TileEntityOmegaObjectiveRenderer;
import assets.rivalrebels.client.tileentityrender.TileEntityPlasmaExplosionRenderer;
import assets.rivalrebels.client.tileentityrender.TileEntityReactorRenderer;
import assets.rivalrebels.client.tileentityrender.TileEntityRecieverRenderer;
import assets.rivalrebels.client.tileentityrender.TileEntitySigmaObjectiveRenderer;
import assets.rivalrebels.client.tileentityrender.TileEntityTachyonBombRenderer;
import assets.rivalrebels.client.tileentityrender.TileEntityTheoreticalTsarBombaRenderer;
import assets.rivalrebels.client.tileentityrender.TileEntityTsarBombaRenderer;
import assets.rivalrebels.common.entity.EntityAntimatterBomb;
import assets.rivalrebels.common.entity.EntityAntimatterBombBlast;
import assets.rivalrebels.common.entity.EntityB2Frag;
import assets.rivalrebels.common.entity.EntityB2Spirit;
import assets.rivalrebels.common.entity.EntityB83;
import assets.rivalrebels.common.entity.EntityB83NoShroom;
import assets.rivalrebels.common.entity.EntityBlood;
import assets.rivalrebels.common.entity.EntityBloodFX;
import assets.rivalrebels.common.entity.EntityBomb;
import assets.rivalrebels.common.entity.EntityCuchillo;
import assets.rivalrebels.common.entity.EntityDebris;
import assets.rivalrebels.common.entity.EntityFlameBall;
import assets.rivalrebels.common.entity.EntityFlameBall1;
import assets.rivalrebels.common.entity.EntityFlameBall2;
import assets.rivalrebels.common.entity.EntityFlameBallGreen;
import assets.rivalrebels.common.entity.EntityGasGrenade;
import assets.rivalrebels.common.entity.EntityGoo;
import assets.rivalrebels.common.entity.EntityGore;
import assets.rivalrebels.common.entity.EntityHackB83;
import assets.rivalrebels.common.entity.EntityHotPotato;
import assets.rivalrebels.common.entity.EntityLaptop;
import assets.rivalrebels.common.entity.EntityLaserBurst;
import assets.rivalrebels.common.entity.EntityLaserLink;
import assets.rivalrebels.common.entity.EntityLightningBolt2;
import assets.rivalrebels.common.entity.EntityLightningLink;
import assets.rivalrebels.common.entity.EntityNuclearBlast;
import assets.rivalrebels.common.entity.EntityNuke;
import assets.rivalrebels.common.entity.EntityPassiveFire;
import assets.rivalrebels.common.entity.EntityPlasmoid;
import assets.rivalrebels.common.entity.EntityPropulsionFX;
import assets.rivalrebels.common.entity.EntityRaytrace;
import assets.rivalrebels.common.entity.EntityRhodes;
import assets.rivalrebels.common.entity.EntityRhodesHead;
import assets.rivalrebels.common.entity.EntityRhodesLeftLowerArm;
import assets.rivalrebels.common.entity.EntityRhodesLeftLowerLeg;
import assets.rivalrebels.common.entity.EntityRhodesLeftUpperArm;
import assets.rivalrebels.common.entity.EntityRhodesLeftUpperLeg;
import assets.rivalrebels.common.entity.EntityRhodesRightLowerArm;
import assets.rivalrebels.common.entity.EntityRhodesRightLowerLeg;
import assets.rivalrebels.common.entity.EntityRhodesRightUpperArm;
import assets.rivalrebels.common.entity.EntityRhodesRightUpperLeg;
import assets.rivalrebels.common.entity.EntityRhodesTorso;
import assets.rivalrebels.common.entity.EntityRocket;
import assets.rivalrebels.common.entity.EntityRoddiskLeader;
import assets.rivalrebels.common.entity.EntityRoddiskOfficer;
import assets.rivalrebels.common.entity.EntityRoddiskRebel;
import assets.rivalrebels.common.entity.EntityRoddiskRegular;
import assets.rivalrebels.common.entity.EntityRoddiskRep;
import assets.rivalrebels.common.entity.EntitySeekB83;
import assets.rivalrebels.common.entity.EntitySphereBlast;
import assets.rivalrebels.common.entity.EntityTachyonBomb;
import assets.rivalrebels.common.entity.EntityTachyonBombBlast;
import assets.rivalrebels.common.entity.EntityTheoreticalTsar;
import assets.rivalrebels.common.entity.EntityTheoreticalTsarBlast;
import assets.rivalrebels.common.entity.EntityTsar;
import assets.rivalrebels.common.entity.EntityTsarBlast;
import assets.rivalrebels.common.round.RivalRebelsPlayer;
import assets.rivalrebels.common.tileentity.TileEntityAntimatterBomb;
import assets.rivalrebels.common.tileentity.TileEntityConduit;
import assets.rivalrebels.common.tileentity.TileEntityForceFieldNode;
import assets.rivalrebels.common.tileentity.TileEntityGore;
import assets.rivalrebels.common.tileentity.TileEntityJumpBlock;
import assets.rivalrebels.common.tileentity.TileEntityLaptop;
import assets.rivalrebels.common.tileentity.TileEntityLoader;
import assets.rivalrebels.common.tileentity.TileEntityMeltDown;
import assets.rivalrebels.common.tileentity.TileEntityNuclearBomb;
import assets.rivalrebels.common.tileentity.TileEntityNukeCrate;
import assets.rivalrebels.common.tileentity.TileEntityOmegaObjective;
import assets.rivalrebels.common.tileentity.TileEntityPlasmaExplosion;
import assets.rivalrebels.common.tileentity.TileEntityReactor;
import assets.rivalrebels.common.tileentity.TileEntityReciever;
import assets.rivalrebels.common.tileentity.TileEntitySigmaObjective;
import assets.rivalrebels.common.tileentity.TileEntityTachyonBomb;
import assets.rivalrebels.common.tileentity.TileEntityTheoreticalTsarBomba;
import assets.rivalrebels.common.tileentity.TileEntityTsarBomba;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy
{
	
	@SideOnly(Side.CLIENT)
	public static void registerRenderInformation()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNukeCrate.class, new TileEntityNukeCrateRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNuclearBomb.class, new TileEntityNuclearBombRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlasmaExplosion.class, new TileEntityPlasmaExplosionRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityReactor.class, new TileEntityReactorRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityJumpBlock.class, new TileEntityJumpBlockRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLoader.class, new TileEntityLoaderRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOmegaObjective.class, new TileEntityOmegaObjectiveRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySigmaObjective.class, new TileEntitySigmaObjectiveRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTsarBomba.class, new TileEntityTsarBombaRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityForceFieldNode.class, new TileEntityForceFieldNodeRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGore.class, new TileEntityGoreRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLaptop.class, new TileEntityLaptopRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityReciever.class, new TileEntityRecieverRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMeltDown.class, new TileEntityMeltdownRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTheoreticalTsarBomba.class, new TileEntityTheoreticalTsarBombaRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAntimatterBomb.class, new TileEntityAntimatterBombRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTachyonBomb.class, new TileEntityTachyonBombRenderer());
		RenderingRegistry.registerEntityRenderingHandler(EntityGasGrenade.class, new RenderGasGrenade());
		RenderingRegistry.registerEntityRenderingHandler(EntityPropulsionFX.class, new RenderBullet("fire"));
		RenderingRegistry.registerEntityRenderingHandler(EntityPassiveFire.class, new RenderBullet("fire"));
		RenderingRegistry.registerEntityRenderingHandler(EntityCuchillo.class, new RenderCuchillo());
		RenderingRegistry.registerEntityRenderingHandler(EntityFlameBall.class, new RenderFlame());
		RenderingRegistry.registerEntityRenderingHandler(EntityFlameBall1.class, new RenderFlameRedBlue());
		RenderingRegistry.registerEntityRenderingHandler(EntityFlameBall2.class, new RenderFlameBlue());
		RenderingRegistry.registerEntityRenderingHandler(EntityRocket.class, new RenderRocket());
		RenderingRegistry.registerEntityRenderingHandler(EntityPlasmoid.class, new RenderPlasmoid());
		RenderingRegistry.registerEntityRenderingHandler(EntityLightningLink.class, new RenderLightningLink());
		RenderingRegistry.registerEntityRenderingHandler(EntityNuclearBlast.class, new RenderNuclearBlast());
		RenderingRegistry.registerEntityRenderingHandler(EntityLightningBolt2.class, new RenderLightningBolt2());
		RenderingRegistry.registerEntityRenderingHandler(EntityLaptop.class, new RenderLaptop());
		RenderingRegistry.registerEntityRenderingHandler(EntityRoddiskRegular.class, new RenderRoddiskRegular());
		RenderingRegistry.registerEntityRenderingHandler(EntityRoddiskRebel.class, new RenderRoddiskRebel());
		RenderingRegistry.registerEntityRenderingHandler(EntityRoddiskOfficer.class, new RenderRoddiskOfficer());
		RenderingRegistry.registerEntityRenderingHandler(EntityRoddiskLeader.class, new RenderRoddiskLeader());
		RenderingRegistry.registerEntityRenderingHandler(EntityTsarBlast.class, new RenderTsarBlast());
		RenderingRegistry.registerEntityRenderingHandler(EntityLaserLink.class, new RenderLaserLink());
		RenderingRegistry.registerEntityRenderingHandler(EntityGore.class, new RenderGore());
		RenderingRegistry.registerEntityRenderingHandler(EntityBlood.class, new RenderBlood());
		RenderingRegistry.registerEntityRenderingHandler(EntityGoo.class, new RenderGoo());
		RenderingRegistry.registerEntityRenderingHandler(EntityLaserBurst.class, new RenderLaserBurst());
		RenderingRegistry.registerEntityRenderingHandler(EntityB83.class, new RenderB83());
		RenderingRegistry.registerEntityRenderingHandler(EntityB2Spirit.class, new RenderB2Spirit());
		RenderingRegistry.registerEntityRenderingHandler(EntityB2Frag.class, new RenderB2Frag());
		RenderingRegistry.registerEntityRenderingHandler(EntityDebris.class, new RenderDebris());
		RenderingRegistry.registerEntityRenderingHandler(EntityHackB83.class, new RenderHackB83());
		RenderingRegistry.registerEntityRenderingHandler(EntitySeekB83.class, new RenderSeeker());
		RenderingRegistry.registerEntityRenderingHandler(EntityRhodes.class, new RenderRhodes());
		RenderingRegistry.registerEntityRenderingHandler(EntityRhodesHead.class, new RenderRhodesHead());
		RenderingRegistry.registerEntityRenderingHandler(EntityRhodesTorso.class, new RenderRhodesTorso());
		RenderingRegistry.registerEntityRenderingHandler(EntityRhodesLeftUpperArm.class, new RenderRhodesLeftUpperArm());
		RenderingRegistry.registerEntityRenderingHandler(EntityRhodesRightUpperArm.class, new RenderRhodesRightUpperArm());
		RenderingRegistry.registerEntityRenderingHandler(EntityRhodesLeftLowerArm.class, new RenderRhodesLeftLowerArm());
		RenderingRegistry.registerEntityRenderingHandler(EntityRhodesRightLowerArm.class, new RenderRhodesRightLowerArm());
		RenderingRegistry.registerEntityRenderingHandler(EntityRhodesLeftUpperLeg.class, new RenderRhodesLeftUpperLeg());
		RenderingRegistry.registerEntityRenderingHandler(EntityRhodesRightUpperLeg.class, new RenderRhodesRightUpperLeg());
		RenderingRegistry.registerEntityRenderingHandler(EntityRhodesLeftLowerLeg.class, new RenderRhodesLeftLowerLeg());
		RenderingRegistry.registerEntityRenderingHandler(EntityRhodesRightLowerLeg.class, new RenderRhodesRightLowerLeg());
		RenderingRegistry.registerEntityRenderingHandler(EntityB83NoShroom.class, new RenderB83());
		RenderingRegistry.registerEntityRenderingHandler(EntitySphereBlast.class, new RenderSphereBlast());
		RenderingRegistry.registerEntityRenderingHandler(EntityNuke.class, new RenderNuke());
		RenderingRegistry.registerEntityRenderingHandler(EntityTsar.class, new RenderTsar());
		RenderingRegistry.registerEntityRenderingHandler(EntityRoddiskRep.class, new RenderRoddiskRep());
		RenderingRegistry.registerEntityRenderingHandler(EntityHotPotato.class, new RenderHotPotato());
		RenderingRegistry.registerEntityRenderingHandler(EntityBomb.class, new RenderBomb());
		RenderingRegistry.registerEntityRenderingHandler(EntityTheoreticalTsar.class, new RenderTheoreticalTsar());
		RenderingRegistry.registerEntityRenderingHandler(EntityTheoreticalTsarBlast.class, new RenderTheoreticalTsarBlast());
		RenderingRegistry.registerEntityRenderingHandler(EntityFlameBallGreen.class, new RenderFlameBallGreen());
		RenderingRegistry.registerEntityRenderingHandler(EntityAntimatterBomb.class, new RenderAntimatterBomb());
		RenderingRegistry.registerEntityRenderingHandler(EntityAntimatterBombBlast.class, new RenderAntimatterBombBlast());
		RenderingRegistry.registerEntityRenderingHandler(EntityTachyonBomb.class, new RenderTachyonBomb());
		RenderingRegistry.registerEntityRenderingHandler(EntityTachyonBombBlast.class, new RenderTachyonBombBlast());
		RenderingRegistry.registerBlockHandler(new SteelBlockRenderer());
	}
	
	@Override
	public int addArmor(String armor)
	{
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}
	
	@Override
	public void closeGui()
	{
		Minecraft.getMinecraft().displayGuiScreen(null);
	}

	@Override
	public void nextBattle()
	{
		Minecraft.getMinecraft().displayGuiScreen(new GuiNextBattle());
	}

	@Override
	public void teamWin(boolean winner)
	{
		Minecraft.getMinecraft().displayGuiScreen(winner?new GuiOmegaWin():new GuiSigmaWin());
	}
	
	@Override
	public void guiClass()
	{
		Minecraft.getMinecraft().displayGuiScreen(new GuiClass(RivalRebels.round.rrplayerlist.getForName(Minecraft.getMinecraft().thePlayer.getCommandSenderName()).rrclass));
	}
	
	@Override
	public void guiSpawn()
	{
		Minecraft.getMinecraft().displayGuiScreen(new GuiSpawn(RivalRebels.round.rrplayerlist.getForName(Minecraft.getMinecraft().thePlayer.getCommandSenderName()).rrclass));
	}

	@Override
	public void flamethrowerGui(int i)
	{
		Minecraft.getMinecraft().displayGuiScreen(new GuiFlameThrower(i));
	}

	@Override
	public void teslaGui(int i)
	{
		Minecraft.getMinecraft().displayGuiScreen(new GuiTesla(i));
	}

	@Override
	public void spawnGore(World world, EntityGore g, boolean greenblood)
	{
		Minecraft.getMinecraft().effectRenderer.addEffect(new EntityBloodFX(world, g, !greenblood));
	}
	
	@Override
	public boolean spacebar()
	{
		return Keyboard.isKeyDown(Keyboard.KEY_SPACE) && Minecraft.getMinecraft().currentScreen == null;
	}
	@Override
	public boolean w()
	{
		return Keyboard.isKeyDown(Keyboard.KEY_W) && Minecraft.getMinecraft().currentScreen == null;
	}
	@Override
	public boolean a()
	{
		return Keyboard.isKeyDown(Keyboard.KEY_A) && Minecraft.getMinecraft().currentScreen == null;
	}
	@Override
	public boolean s()
	{
		return Keyboard.isKeyDown(Keyboard.KEY_X) && Minecraft.getMinecraft().currentScreen == null;
	}
	@Override
	public boolean d()
	{
		return Keyboard.isKeyDown(Keyboard.KEY_D) && Minecraft.getMinecraft().currentScreen == null;
	}
	@Override
	public boolean f()
	{
		return Keyboard.isKeyDown(Keyboard.KEY_F) && Minecraft.getMinecraft().currentScreen == null;
	}
	boolean prevc = false;
	public boolean c()
	{
		boolean isdown = Keyboard.isKeyDown(Keyboard.KEY_C) && Minecraft.getMinecraft().currentScreen == null;
		boolean x = (prevc==false) && (isdown==true);
		prevc = isdown;
		return x;
	}
	boolean prevx = false;
	@Override
	public boolean x()
	{
		boolean isdown = Keyboard.isKeyDown(Keyboard.KEY_S) && Minecraft.getMinecraft().currentScreen == null;
		boolean x = (prevx==false) && (isdown==true);
		prevx = isdown;
		return x;
	}
	@Override
	public boolean z()
	{
		return Keyboard.isKeyDown(Keyboard.KEY_Z) && Minecraft.getMinecraft().currentScreen == null;
	}
	public boolean g()
	{
		return Keyboard.isKeyDown(Keyboard.KEY_G) && Minecraft.getMinecraft().currentScreen == null;
	}
	
	
	@Override
	public void setOverlay(EntityRhodes rhodes)
	{
		if (rhodes.rider == Minecraft.getMinecraft().thePlayer)
		{
			RivalRebels.rrro.counter = 10;
			RivalRebels.rrro.rhodes = rhodes;
		}
	}
}