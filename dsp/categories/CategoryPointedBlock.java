package dsp.categories;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCarpet;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import dsp.api.DebugScreenCategory;
import dsp.api.DebugScreenPlusAPI;

public class CategoryPointedBlock extends DebugScreenCategory{

	public CategoryPointedBlock(String displayName) {
		super(displayName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processInfo(List<String> left, List<String> right, EntityPlayer p, World w, boolean showModderInfo) {
		MovingObjectPosition mop = DebugScreenPlusAPI.pointedObject(p, w);
		 if(mop != null && mop.typeOfHit == MovingObjectType.BLOCK){
         	Block block = w.getBlock(mop.blockX, mop.blockY, mop.blockZ);
         	int x1 = mop.blockX, y1 = mop.blockY, z1 = mop.blockZ;
         	int meta = w.getBlockMetadata(mop.blockX, mop.blockY, mop.blockZ);
         	if(block !=null && block != Blocks.air){
         		String l = block.getLocalizedName().endsWith(".name")? " (author of this block was lazy)":"";
         		left.add("Block: " + block.getLocalizedName() + l);
         		left.add("Meta: " + meta);
         		left.add(null);
         		
         		if(showModderInfo)left.add("Class: " + block.getClass().getName());
         		if(showModderInfo)left.add("Material: " + DebugScreenPlusAPI.materials.get(block.getMaterial()));
         		if(showModderInfo)left.add("Unlocalized name: " + block.getUnlocalizedName());
         		
         		if(showModderInfo)left.add(null);
         		
         		left.add("X: " + mop.blockX + " Y: " + mop.blockY + " Z: " + mop.blockZ);
         		left.add("Hardness: " + block.getBlockHardness(w, x1, y1, z1));
         		if(block.getCreativeTabToDisplayOn() !=null && DebugScreenPlusAPI.cretiveTabs.containsKey(block.getCreativeTabToDisplayOn()))left.add("Creative tab: " + DebugScreenPlusAPI.cretiveTabs.get(block.getCreativeTabToDisplayOn()));
         		left.add("Tool: " + block.getHarvestTool(meta));
         		left.add("Harvest level: " + block.getHarvestLevel(meta));
         		
         		if(block.getBlockColor() != 16777215) left.add("Color: " + block.getBlockColor());
         		if(block instanceof BlockColored || block instanceof BlockStainedGlass || block instanceof BlockStainedGlassPane || block instanceof BlockCarpet) left.add("Color: " + DebugScreenPlusAPI.colors[meta]);
         		if(block instanceof BlockDoor){
         	
         			left.add("Half: " + (meta >= 8? "upper":"lower"));
         		}
         		right.add("Shearable: " + booleanString(block instanceof IShearable));
         		right.add("Replaceable: " + booleanString(block.getMaterial().isReplaceable()));
         		right.add("Solid: " + booleanString(block.getMaterial().isSolid()));
         		right.add("Can burn: " + booleanString(block.getMaterial().getCanBurn()));
         		right.add("Harvestable now: " + booleanString(block.canHarvestBlock(p, meta)));
         		if(showModderInfo)right.add("Has TileEntity: " + booleanString(block instanceof BlockContainer));
         		
         	}
         }else{
         	left.add("Beautiful nothing, right?");
         }
		
	}



}
