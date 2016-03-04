package assets.rivalrebels.common.item.weapon;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.entity.EntityRocket;

public class ItemRPG extends Item
{
	public ItemRPG()
	{
		super();
		maxStackSize = 1;
		setCreativeTab(RivalRebels.rralltab);
	}
	
	@Override
	public boolean isFull3D()
	{
		return true;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return EnumAction.bow;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 0x00090;
	}
	
	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(RivalRebels.rocket) || RivalRebels.infiniteAmmo)
		{
			par3EntityPlayer.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));
			if (!par2World.isRemote && !par3EntityPlayer.capabilities.isCreativeMode && !RivalRebels.infiniteAmmo)
			{
				par3EntityPlayer.inventory.consumeInventoryItem(RivalRebels.rocket);
			}
			RivalRebelsSoundPlayer.playSound(par3EntityPlayer, 23, 2, 0.4f);
			if (!par2World.isRemote)
			{
				par2World.spawnEntityInWorld(new EntityRocket(par2World, (EntityPlayer) par3EntityPlayer, 0.1F));
			}
		}
		else if (!par2World.isRemote)
		{
			par3EntityPlayer.addChatMessage(new ChatComponentText("§cOut of ammunition"));
		}
		return par1ItemStack;
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:aq");
	}
}
