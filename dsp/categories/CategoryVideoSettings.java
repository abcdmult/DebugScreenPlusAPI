package dsp.categories;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import dsp.api.DebugScreenCategory;

public class CategoryVideoSettings extends DebugScreenCategory {

	public CategoryVideoSettings(String displayName) {
		super(displayName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processInfo(List<String> left, List<String> right, EntityPlayer player, World world, boolean modderInfo) {
		left.add("Smooth lightning: " +Minecraft.getMinecraft().gameSettings.ambientOcclusion);
		left.add("Anisotroptic filtering: " +Minecraft.getMinecraft().gameSettings.anisotropicFiltering);
		left.add("Chat opacity: " +Minecraft.getMinecraft().gameSettings.chatOpacity);
		left.add("FOV: " +Minecraft.getMinecraft().gameSettings.fovSetting);
		left.add("Gamma: " +Minecraft.getMinecraft().gameSettings.gammaSetting);
		left.add("Mipmap levels: " +Minecraft.getMinecraft().gameSettings.mipmapLevels);
		left.add("Mouse sensevity: " +Minecraft.getMinecraft().gameSettings.mouseSensitivity);
		left.add("Particles: " +Minecraft.getMinecraft().gameSettings.particleSetting);

	}

}
