package dsp.categories;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Direction;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import dsp.api.DebugScreenCategory;

public class CategoryPlayer extends DebugScreenCategory{

	public CategoryPlayer(String displayName) {
		super(displayName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processInfo(List<String> left, List<String> right, EntityPlayer p, World world, boolean modderInfo) {
		left.add("Nickname: " + p.getCommandSenderName());
		left.add("X: " + (float)p.posX);
		left.add("Y: " + (float)p.posY + " (eyes), " + (float)p.boundingBox.minY + "(feet)");
		left.add("Z: " + (float)p.posZ);
		left.add(null);
		left.add("MotionX: " + (float)p.motionX);
		left.add("MotionY: " + (float)p.motionY);
		left.add("MotionZ: " + (float)p.motionZ);
		left.add(null);
		left.add("Health: " + p.getHealth());
		left.add("Absorption: " + p.getAbsorptionAmount());
		int heading = MathHelper.floor_double((double)(p.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		left.add("Direction: " + Direction.directions[heading]);
		left.add(null);
		
		
		right.add("Burning: " + booleanString(p.isBurning()));
		right.add("Running: " + booleanString(p.isSprinting()));
		right.add("Sneaking: " + booleanString(p.isSneaking()));
		right.add("Blocking: " + booleanString(p.isBlocking()));
		right.add("Is on ground: " + booleanString(p.onGround));
		right.add("On ladder: " + booleanString(p.isOnLadder()));
		right.add("In water: " + booleanString(p.isInWater()));
		right.add("Creative: " + booleanString(p.capabilities.isCreativeMode));
		right.add("Can fly: " + booleanString(p.capabilities.allowFlying));
		right.add("Is flying: " + booleanString(p.capabilities.isFlying));
		right.add(null);
		right.add("Expirience: " + p.experienceTotal);
		right.add("Level: " + p.experienceLevel);
		right.add(null);
		right.add("Normal walk speed: " + p.capabilities.getWalkSpeed());
		right.add("Normal fly speed: " + p.capabilities.getFlySpeed());
		

	}

}
