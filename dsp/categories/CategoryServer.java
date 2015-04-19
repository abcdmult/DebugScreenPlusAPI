package dsp.categories;

import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import dsp.api.DebugScreenCategory;

public class CategoryServer extends DebugScreenCategory {

	public CategoryServer(String displayName) {
		super(displayName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processInfo(List<String> left, List<String> right, EntityPlayer player, World world, boolean modderInfo) {
			MinecraftServer s = MinecraftServer.getServer();
			left.add("Minecraft 1.7.10");
			left.add(Minecraft.getMinecraft().debug);
			left.add(null);
			for (String brand : FMLCommonHandler.instance().getBrandings(false)){
				left.add(brand);
			}
			left.add("Language: " + FMLCommonHandler.instance().getCurrentLanguage());
			if ( Minecraft.getMinecraft().entityRenderer != null && Minecraft.getMinecraft().entityRenderer.isShaderActive()){
				left.add(String.format("Shader: %s",  Minecraft.getMinecraft().entityRenderer.getShaderGroup().getShaderGroupName()));
			}else{
				left.add("Shader: none");
			}
			left.add(null);
			left.add("Current player amount: " + s.getCurrentPlayerCount());
			left.add("Max players: " + s.getMaxPlayers());
			if(modderInfo) left.add("Current server's task: " + s.currentTask);
			
			right.add("Is singleplayer: " + booleanString(s.isSinglePlayer()));
			right.add("Is hardcore: " + booleanString(s.isHardcore()));
			right.add("Is command blocks enabled: " + booleanString(s.isCommandBlockEnabled()));
			right.add("Is flight allowed: " + booleanString(s.isFlightAllowed()));
			if(!s.isSinglePlayer())right.add("Is PVP enabled: " + booleanString(s.isPVPEnabled()) );
	}

}
