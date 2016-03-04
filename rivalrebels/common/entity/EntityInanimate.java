package assets.rivalrebels.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityInanimate extends Entity
{
	public EntityInanimate(World par1World)
	{
		super(par1World);
	}

	@Override
	protected void entityInit()
	{
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt)
	{
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt)
	{
	}
}
