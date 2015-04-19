package dsp.categories;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import dsp.api.DebugScreenCategory;

public class CategoryWorld extends DebugScreenCategory {

	public CategoryWorld(String displayName) {
		super(displayName);

	}

	@Override
	public void processInfo(List<String> left, List<String> right, EntityPlayer p, World w, boolean modderInfo) {
		int x = MathHelper.floor_double(p.posX);
        int y = MathHelper.floor_double(p.posY);
        int z = MathHelper.floor_double(p.posZ);
        Chunk chunk = Minecraft.getMinecraft().theWorld.getChunkFromBlockCoords(x, z);
		GameRules r = MinecraftServer.getServer().worldServerForDimension(0).getGameRules();
				
		left.add("Raining: " + booleanString(w.isRaining()));
		left.add("Thundering: " + booleanString(w.isThundering()));
		left.add("Moon phase: " + w.getCurrentMoonPhaseFactor());
		String dim =  p.dimension == 0? "overworld" : p.dimension == 1? "end": p.dimension == -1? "nether" : "unknown";
		left.add("Dimension: " + dim);
		left.add("Seed: " + Long.valueOf(MinecraftServer.getServer().worldServerForDimension(0).getSeed()));
		left.add("World name: " + MinecraftServer.getServer().worldServerForDimension(0).getWorldInfo().getWorldName());
		left.add("Can structures spawn: " + booleanString(MinecraftServer.getServer().canStructuresSpawn()));
		left.add(null);
		left.add("Current biome: " +  chunk.getBiomeGenForWorldCoords(x & 15, z & 15, Minecraft.getMinecraft().theWorld.getWorldChunkManager()).biomeName);
		left.add("Current light value (from blocks): " + chunk.getSavedLightValue(EnumSkyBlock.Block, x & 15, y, z & 15));
		left.add("Current light value (from sky) :" + chunk.getSavedLightValue(EnumSkyBlock.Sky, x & 15, y, z & 15));
		left.add("Current light value (total): " + chunk.getBlockLightValue(x & 15, y, z & 15, 0));
		
		right.add("Game rules: ");
		right.add("doFireTick: " + booleanString(r.getGameRuleBooleanValue("doFireTick")));
		right.add("mobGriefing: " + booleanString(r.getGameRuleBooleanValue("mobGriefing")));
		right.add("keepInventory: " + booleanString(r.getGameRuleBooleanValue("keepInventory")));
		right.add("doMobSpawning: " + booleanString(r.getGameRuleBooleanValue("doMobSpawning")));
		right.add("doMobLoot: " + booleanString(r.getGameRuleBooleanValue("doMobLoot")));
		right.add("doTileDrops: " + booleanString(r.getGameRuleBooleanValue("doTileDrops")));
		right.add("commandBlockOutput: " + booleanString(r.getGameRuleBooleanValue("commandBlockOutput")));
		right.add("naturalRegeneration: " + booleanString(r.getGameRuleBooleanValue("naturalRegeneration")));
		right.add("doDaylightCycle: " + booleanString(r.getGameRuleBooleanValue("doDaylightCycle")));


	}

}
