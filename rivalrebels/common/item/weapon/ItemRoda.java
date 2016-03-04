package assets.rivalrebels.common.item.weapon;

import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.entity.EntityCuchillo;
import assets.rivalrebels.common.entity.EntityFlameBall;
import assets.rivalrebels.common.entity.EntityFlameBall1;
import assets.rivalrebels.common.entity.EntityFlameBall2;
import assets.rivalrebels.common.entity.EntityGasGrenade;
import assets.rivalrebels.common.entity.EntityGore;
import assets.rivalrebels.common.entity.EntityLaserBurst;
import assets.rivalrebels.common.entity.EntityNuclearBlast;
import assets.rivalrebels.common.entity.EntityPlasmoid;
import assets.rivalrebels.common.entity.EntityRocket;
import assets.rivalrebels.common.round.RivalRebelsPlayer;
import assets.rivalrebels.common.round.RivalRebelsRank;

public class ItemRoda extends Item
{
	boolean pass = false;
	public ItemRoda()
	{
		super();
		maxStackSize = 1;
		setCreativeTab(RivalRebels.rralltab);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		if (!pass)
		{
			player.addChatMessage(new ChatComponentText("Password?"));
			pass = true;
		}
		player.swingItem();
		if (world.isRemote) return item;
		RivalRebelsPlayer rrp = RivalRebels.round.rrplayerlist.getForName(player.getCommandSenderName());
		if (rrp != null && (rrp.rrrank == RivalRebelsRank.LEADER || rrp.rrrank == RivalRebelsRank.OFFICER))
		{
			if (world.isRemote) return item;
			player.setItemInUse(item, 256);
			item.stackTagCompound.setInteger("happynewyear",item.stackTagCompound.getInteger("happynewyear")+10);
			if (item.stackTagCompound.getInteger("happynewyear") > 1400) //EXPLODE
			{
				world.spawnEntityInWorld(new EntityNuclearBlast(world, player.posX, player.posY, player.posZ, 6, true));
				player.inventory.mainInventory[player.inventory.currentItem] = null;
				return item;
			}
			Entity[] entity = null;
			switch (world.rand.nextInt(7))
			{
				case 0:
					entity = new Entity[]{new EntityPlasmoid(world, player, 1.5f, false)};
				break;
				case 1:
					entity = new Entity[]{new EntityRocket(world, player, 1.5f)};
				break;
				case 2:
					entity = new Entity[]{new EntityGasGrenade(world, player, 1.5f)};
				break;
				case 3:
					entity = new Entity[]{new EntityCuchillo(world, player, 1.5f)};
				break;
				case 4:
					entity = new Entity[]{new EntityFlameBall(world, player, 1.5f),
										new EntityFlameBall1(world, player, 1.5f),
										new EntityFlameBall2(world, player, 1.5f)};
				break;
				case 5:
					entity = new Entity[]{new EntityLaserBurst(world, player),
										new EntityLaserBurst(world, player),
										new EntityLaserBurst(world, player),
										new EntityLaserBurst(world, player)};
				break;
				case 6:
					entity = new Entity[]{new EntityGore(world, player, world.rand.nextInt(3), world.rand.nextInt(11)+1),
										new EntityGore(world, player, world.rand.nextInt(3), world.rand.nextInt(11)+1),
										new EntityGore(world, player, world.rand.nextInt(3), world.rand.nextInt(11)+1),
										new EntityGore(world, player, world.rand.nextInt(3), world.rand.nextInt(11)+1)};
				break;
			}
			if (entity != null) for (int i = 0; i < entity.length; i++) world.spawnEntityInWorld(entity[i]);
		}
		return item;
	}
	
	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int par4, boolean par5)
	{
		if (world.isRemote) return;
		if (item.getTagCompound() == null) item.stackTagCompound = new NBTTagCompound();
		if (item.stackTagCompound.getInteger("happynewyear")>0)item.stackTagCompound.setInteger("happynewyear",item.stackTagCompound.getInteger("happynewyear")-1);
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (player.worldObj.isRemote) return true;
		Random r = player.worldObj.rand;
		double x = entity.posX - player.posX;
		double y = entity.posY - player.posY;
		double z = entity.posZ - player.posZ;
		
		double dist = Math.sqrt(x * x + y * y + z * z);
		
		switch (r.nextInt(4))
		{
			case 0:
				x /= -dist;
				y /= -dist;
				z /= -dist;
				
				entity.motionX = x * 3 + (r.nextFloat() - 0.5f) * 0.1;
				entity.motionY = y * 3 + (r.nextFloat() - 0.5f) * 0.1;
				entity.motionZ = z * 3 + (r.nextFloat() - 0.5f) * 0.1;
			break;
			case 1:
				x /= dist;
				y /= dist;
				z /= dist;
				
				entity.motionX = x * 2 + (r.nextFloat() - 0.5f) * 0.1;
				entity.motionY = y * 2 + (r.nextFloat() - 0.5f) * 0.1;
				entity.motionZ = z * 2 + (r.nextFloat() - 0.5f) * 0.1;
			break;
		}
		return true;
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister)
	{
		itemIcon = iconregister.registerIcon("RivalRebels:be");
	}
	
	@Override
	public boolean isFull3D()
	{
		return true;
	}
}