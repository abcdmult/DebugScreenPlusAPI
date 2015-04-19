package dsp.categories;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import dsp.api.DebugScreenCategory;

public class CategoryComputer extends DebugScreenCategory {

	public CategoryComputer(String displayName) {
		super(displayName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processInfo(List<String> left, List<String> right, EntityPlayer player, World world, boolean modderInfo) {
		left.add("Is runnning on MacOS: " + booleanString(Minecraft.isRunningOnMac));
		long max = Runtime.getRuntime().maxMemory();
        long total = Runtime.getRuntime().totalMemory();
        long free = Runtime.getRuntime().freeMemory();
        long used = total - free;
        left.add("Used memory: " + used * 100L / max + "% (" + used / 1024L / 1024L + "MB) of " + max / 1024L / 1024L + "MB");
        left.add("Allocated memory: " + total * 100L / max + "% (" + total / 1024L / 1024L + "MB)");


	}

}
