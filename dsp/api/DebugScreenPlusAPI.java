package dsp.api;

import java.util.Hashtable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

/**
 * DebugScreen+ API.
 * @author abcdmult 
 * @version 1.1.1710.b1
 */
public class DebugScreenPlusAPI {
	
	public static Hashtable<Material, String> materials = new Hashtable<Material, String>();
	public static Hashtable<CreativeTabs, String> cretiveTabs = new Hashtable<CreativeTabs, String>();
	public static String [] colors = {"white", "orange", "magenta", "light blue", "yellow", "lime", "pink", "gray", "light gray", "cyan", "purple", "blue", "brown", "green", "red", "black"};

	
	/**
	 * Registers category.
	 * @param category Category to register.
	 */
	public static void registerCategory(DebugScreenCategory category){
		MinecraftForge.EVENT_BUS.post(new EventRegisterCategory(category));
	}
	/**
	 * Registers module.
	 * @param module
	 */
	public static void registerModule(IModule module){
		MinecraftForge.EVENT_BUS.post(new EventRegisterModule(module));
	}
	
	/**
	 * Fancy string.
	 * @param b Value
	 * @return Green (if b is true) or red (if b is false) string with value of b.
	 */
	public static String booleanString(boolean b){
		return b? EnumChatFormatting.GREEN + "true" : EnumChatFormatting.RED + "false";	
	}
	/**
	 * Minecraft instance.
	 * @return Minecraft.
	 */
	public static Minecraft getMinecraft(){
		return Minecraft.getMinecraft();
	}
	/**
	 * Pointed entity.
	 * @return Pointed entity.
	 */
	public static Entity getPointedEntity(){
		return getMinecraft().pointedEntity;
	}
	
	/**
	 * Minecraft Server instance.
	 * @return Minecraft server.
	 */
	public static MinecraftServer getServer(){
		return MinecraftServer.getServer();
	}
	
	/**
	 * Pointed block.
	 * @param w
	 * @param p
	 * @return
	 */
	public static Block pointedBlock(World w, EntityPlayer p){
		float f = 1.0F;
        float f1 = p.prevRotationPitch + (p.rotationPitch - p.prevRotationPitch) * f;
        float f2 = p.prevRotationYaw + (p.rotationYaw - p.prevRotationYaw) * f;        
        double d = p.prevPosX + (p.posX - p.prevPosX) * (double)f;
        double d1 = (p.prevPosY + (p.posY - p.prevPosY) * (double)f + 1.6200000000000001D) - (double)p.yOffset;
        double d2 = p.prevPosZ + (p.posZ - p.prevPosZ) * (double)f;
        
        Vec3 vec3d = Vec3.createVectorHelper(d, d1, d2);
        
        float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
        float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
        float f5 = -MathHelper.cos(-f1 * 0.01745329F);
        float f6 = MathHelper.sin(-f1 * 0.01745329F);
        float f7 = f4 * f5;
        float f8 = f6;
        float f9 = f3 * f5;
        
        double d3 = 5000D;
       
        Vec3 vec3d1 = vec3d.addVector((double)f7 * d3, (double)f8 * d3, (double)f9 * d3);
        
        MovingObjectPosition mop = w.rayTraceBlocks(vec3d, vec3d1, false);
        
        return w.getBlock(mop.blockX, mop.blockY, mop.blockZ);
	}
	
	public static MovingObjectPosition pointedObject(EntityPlayer p, World w){
		float f = 1.0F;
        float f1 = p.prevRotationPitch + (p.rotationPitch - p.prevRotationPitch) * f;
        float f2 = p.prevRotationYaw + (p.rotationYaw - p.prevRotationYaw) * f;        
        double d = p.prevPosX + (p.posX - p.prevPosX) * (double)f;
        double d1 = (p.prevPosY + (p.posY - p.prevPosY) * (double)f + 1.6200000000000001D) - (double)p.yOffset;
        double d2 = p.prevPosZ + (p.posZ - p.prevPosZ) * (double)f;
        
        Vec3 vec3d = Vec3.createVectorHelper(d, d1, d2);
        
        float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
        float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
        float f5 = -MathHelper.cos(-f1 * 0.01745329F);
        float f6 = MathHelper.sin(-f1 * 0.01745329F);
        float f7 = f4 * f5;
        float f8 = f6;
        float f9 = f3 * f5;
        
        double d3 = 5000D;
       
        Vec3 vec3d1 = vec3d.addVector((double)f7 * d3, (double)f8 * d3, (double)f9 * d3);
        
        MovingObjectPosition mop = w.rayTraceBlocks(vec3d, vec3d1, false);
        return mop;
	}
}
