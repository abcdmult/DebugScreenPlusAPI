package dsp.api;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface IPlayerModule extends IModule{
	public void addPlayerInfo(EntityPlayer player, List<String> left, List<String> right);

}
