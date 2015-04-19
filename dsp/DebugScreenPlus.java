package dsp;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import dsp.api.DebugScreenCategory;
import dsp.api.DebugScreenPlusAPI;
import dsp.api.EventRegisterCategory;
import dsp.api.EventRegisterModule;
import dsp.api.IPlayerModule;
import dsp.api.IPointedBlockModule;
import dsp.api.IPointedEntityModule;
import dsp.api.IWorldModule;
import dsp.categories.CategoryComputer;
import dsp.categories.CategoryNone;
import dsp.categories.CategoryPlayer;
import dsp.categories.CategoryPointedBlock;
import dsp.categories.CategoryPointedEntity;
import dsp.categories.CategoryServer;
import dsp.categories.CategoryVideoSettings;
import dsp.categories.CategoryWorld;



@Mod(modid="debugscreenplus", version = "1.1.1710.b1", name = "DebugScreen+")
public class DebugScreenPlus {
	public static boolean showModderInfo = false;
	public static boolean show = false;
	public int t = 0, t1 = 0, t3 = 0;
	private int c = 0;
	private List<DebugScreenCategory> categories;
	
	private List<IPlayerModule> playerModules;
	private List<IWorldModule> worldModules;
	private List<IPointedBlockModule> pointedBlockModules;
	private List<IPointedEntityModule> pointedEntityModules;
	
	@Mod.Instance
	public static DebugScreenPlus instance;
	@Mod.EventHandler
	public void preinit(FMLPreInitializationEvent e){
		categories = new ArrayList<DebugScreenCategory>();
		playerModules = new ArrayList<IPlayerModule>();
		worldModules = new ArrayList<IWorldModule>();
		pointedBlockModules = new ArrayList<IPointedBlockModule>();
		pointedEntityModules = new ArrayList<IPointedEntityModule>();
		registerMaterial(Material.air, "air");
		registerMaterial(Material.anvil, "anvil");
		registerMaterial(Material.cactus, "cactus");
		registerMaterial(Material.cake, "cake");
		registerMaterial(Material.carpet, "carpet");
		registerMaterial(Material.circuits, "circuts");
		registerMaterial(Material.clay, "clay");
		registerMaterial(Material.cloth, "cloth");
		registerMaterial(Material.coral, "coral");
		registerMaterial(Material.craftedSnow, "crafted snow");
		registerMaterial(Material.dragonEgg, "dragon egg");
		registerMaterial(Material.fire, "fire");
		registerMaterial(Material.glass, "glass");
		registerMaterial(Material.gourd, "gourd");
		registerMaterial(Material.grass, "grass");
		registerMaterial(Material.ground, "ground");
		registerMaterial(Material.ice, "ice");
		registerMaterial(Material.iron, "iron");
		registerMaterial(Material.lava, "lava");
		registerMaterial(Material.leaves, "leaves");
		registerMaterial(Material.packedIce, "packed ice");
		registerMaterial(Material.piston, "piston");
		registerMaterial(Material.plants, "plants");
		registerMaterial(Material.portal, "portal");
		registerMaterial(Material.redstoneLight, "lit redstone ore");
		registerMaterial(Material.rock, "rock");
		registerMaterial(Material.sand, "sand");
		registerMaterial(Material.snow, "snow");
		registerMaterial(Material.sponge, "sponge");
		registerMaterial(Material.tnt, "tnt");
		registerMaterial(Material.vine, "vine");
		registerMaterial(Material.water, "water");
		registerMaterial(Material.web, "web");
		registerMaterial(Material.wood, "wood");
		registerCreativeTab(CreativeTabs.tabBlock, "blocks");
		registerCreativeTab(CreativeTabs.tabBrewing, "brewing");
		registerCreativeTab(CreativeTabs.tabCombat, "combat");
		registerCreativeTab(CreativeTabs.tabDecorations, "decorations");
		registerCreativeTab(CreativeTabs.tabFood, "food");
		registerCreativeTab(CreativeTabs.tabMaterials, "materials");
		registerCreativeTab(CreativeTabs.tabMisc, "misc");
		registerCreativeTab(CreativeTabs.tabRedstone, "redstone");
		registerCreativeTab(CreativeTabs.tabTools, "tools");
		registerCreativeTab(CreativeTabs.tabTransport, "transport");
		MinecraftForge.EVENT_BUS.register(this);
		DebugScreenPlusAPI.registerCategory(new CategoryNone("None"));
		DebugScreenPlusAPI.registerCategory(new CategoryPlayer("Player"));
		DebugScreenPlusAPI.registerCategory(new CategoryWorld("World"));
		DebugScreenPlusAPI.registerCategory(new CategoryServer("Game & Server"));
		DebugScreenPlusAPI.registerCategory(new CategoryComputer("Computer"));
		DebugScreenPlusAPI.registerCategory(new CategoryVideoSettings("Video settings"));
		DebugScreenPlusAPI.registerCategory(new CategoryPointedBlock("Pointed block"));
		DebugScreenPlusAPI.registerCategory(new CategoryPointedEntity("Pointed entity"));
		
	}
	
	public void registerMaterial(Material material, String displayName){
		DebugScreenPlusAPI.materials.put(material, displayName);
	}
	
	public void registerCreativeTab(CreativeTabs t, String name){
		DebugScreenPlusAPI.cretiveTabs.put(t, name);
	}

	public void updateDelays(){
		t++;
		t1++;
		t3++;
	}
	
	@SubscribeEvent
	public void onDrawDebugText(RenderGameOverlayEvent.Text event) {	
		updateDelays();
		/*FIELDS*/
		
		MinecraftServer s = MinecraftServer.getServer();
		World w = Minecraft.getMinecraft().theWorld;
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_F7) && t1 >= 10){
			Minecraft.getMinecraft().gameSettings.showDebugInfo = false;
			show = !show; t1 = 0; 
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_TAB) && t3 >=20){ showModderInfo = !showModderInfo; t3 = 0;}
		if(show && !Minecraft.getMinecraft().gameSettings.showDebugInfo) {
			event.left.add(EnumChatFormatting.BOLD + "To switch category use arrow keys on your keyboard.");
			event.left.add("Press " + (!Keyboard.isKeyDown(Keyboard.KEY_TAB)? EnumChatFormatting.BOLD : "") + "<TAB> " + EnumChatFormatting.RESET +  "to toggle visibility of special modder info (now " + (showModderInfo? "shown).": "hidden)."));
			event.left.add(null);
			for(DebugScreenCategory c1 : categories){
				if(categories.get(c).getName().equals(c1.getName())){
					event.left.add(EnumChatFormatting.BOLD + "[" + c1.getName() + "]");
				}else event.left.add("[" + c1.getName() + "]");
			}
			event.left.add(EnumChatFormatting.BOLD + "------------IMPORTANT INFORMATION------------");
			for(int i = 0; i < (4+categories.size()); i++) event.right.add(null);
			categories.get(c).processInfo(event.left, event.right, p, w, showModderInfo);
			if(categories.get(c).getName().equals("Player")) for(IPlayerModule m : playerModules) m.addPlayerInfo(p, event.left, event.right);
			if(categories.get(c).getName().equals("World")) for(IWorldModule m : worldModules) m.addWorldInfo(p, w, event.left, event.right);
			if(DebugScreenPlusAPI.pointedBlock(w, p)!=null && categories.get(c).getName().equals("Pointed block")) for(IPointedBlockModule m : pointedBlockModules) m.addBlockInfo(p, DebugScreenPlusAPI.pointedBlock(w, p), event.left, event.right);
			if(DebugScreenPlusAPI.getPointedEntity() != null && categories.get(c).getName().equals("Pointed entity")) for(IPointedEntityModule m : pointedEntityModules) m.addWorldInfo(p, DebugScreenPlusAPI.getPointedEntity(), event.left, event.right);
			if(Keyboard.isKeyDown(Keyboard.KEY_UP))         switchCategory(true);
			else if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) switchCategory(false);
		}
	}

	public void switchCategory(boolean up){
		if(t>=10){
			if(up){
				if(c == 0) c = categories.size()-1; else c--;
			}else{
				if(c == categories.size()-1) c = 0; else c++;
			}
			t=0;
		}
		return;
	}
	
	@SubscribeEvent
	public void onRegisterCategoryEvent(EventRegisterCategory e){
		categories.add(e.category);
	}
	
	@SubscribeEvent 
	public void onRegisterModuleEvent(EventRegisterModule e){
		if(e.module instanceof IPlayerModule) playerModules.add((IPlayerModule) e.module);
		else if(e.module instanceof IWorldModule) worldModules.add((IWorldModule)e.module);
		else if(e.module instanceof IPointedBlockModule) pointedBlockModules.add((IPointedBlockModule) e.module);
		else if(e.module instanceof IPointedEntityModule) pointedEntityModules.add((IPointedEntityModule) e.module);
		else throw new IllegalArgumentException("Unknown module type, cannot be handled.");
	}
}
