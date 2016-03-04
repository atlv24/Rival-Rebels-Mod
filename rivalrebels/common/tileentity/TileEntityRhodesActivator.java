package assets.rivalrebels.common.tileentity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.block.autobuilds.BlockRhodesScaffold;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.entity.EntityRhodes;
import assets.rivalrebels.common.round.RivalRebelsPlayer;
import assets.rivalrebels.common.round.RivalRebelsTeam;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityRhodesActivator extends TileEntityMachineBase
{
	int charge = 0;
	public TileEntityRhodesActivator()
	{
		pInM = 40;
		pInR = 20;
	}
	
	@Override
	public float powered(float power, float distance)
	{
		if (!worldObj.isRemote)
		{
			if (charge == 100)
			{
				TileEntity te = worldObj.getTileEntity(xCoord-3, yCoord-21, zCoord);
				if (te instanceof TileEntityRhodesActivator)
				{
					// all 4 main charge points are valid
					boolean buildrhodes = true;
					int x = xCoord - 2;
					int y = yCoord + 2;
					int z = zCoord;
					for (int i = 0; i < 31*9; i++)
					{
						byte b = BlockRhodesScaffold.binimg[i];
						if (b == 1 && (worldObj.getBlock(x-8+(i%9), y-(i/9), z) != RivalRebels.conduit || worldObj.getBlock(x+9-(i%9), y-(i/9), z) != RivalRebels.conduit))
						{
							buildrhodes = false;
							break;
						}
						if (b == 2 && (worldObj.getBlock(x-8+(i%9), y-(i/9), z) != RivalRebels.rhodesactivator || worldObj.getBlock(x+9-(i%9), y-(i/9), z) != RivalRebels.rhodesactivator))
						{
							buildrhodes = false;
							break;
						}
					}
					if (buildrhodes)
					{
						for (int i = 0; i < 31*9; i++)
						{
							byte b = BlockRhodesScaffold.binimg[i];
							if (b == 1)
							{
								int f = y-(i/9);
								worldObj.setBlock(x-8+(i%9), f, z, Blocks.air);
								worldObj.setBlock(x+9-(i%9), f, z, Blocks.air);
							}
						}
						EntityRhodes er = new EntityRhodes(worldObj, x+1f, y-15, z);
						if (zCoord > this.z) er.bodyyaw = 180;
						worldObj.spawnEntityInWorld(er);
					}
				}
				return power*0.5f;
			}
			else
			{
				charge++;
				return 0;
			}
		}
		return power;
	}
}