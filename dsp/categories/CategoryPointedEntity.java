package dsp.categories;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import dsp.api.DebugScreenCategory;

public class CategoryPointedEntity extends DebugScreenCategory{

	

	public CategoryPointedEntity(String displayName) {
		super(displayName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processInfo(List<String> left, List<String> right,EntityPlayer player, World world, boolean showModderInfo) {
		if(Minecraft.getMinecraft().pointedEntity!=null){
        	Entity entity = Minecraft.getMinecraft().pointedEntity;
        	left.add("Entity: " + entity.getClass().getSimpleName().substring(6));
        	
        	if(showModderInfo) left.add("UUID: " + entity.getUniqueID());
        	left.add(null);
        	left.add("X: " + (float)entity.posX + " Y: " + (float)entity.posY + " Z: " + (float)entity.posZ);
        	left.add("Motion: X: " + (float)entity.motionX + " Y: " + (float)entity.motionY + " Z: " + (float)entity.motionZ);
        	left.add(null);
        
        	if(entity instanceof EntityLiving){
        		EntityLiving el = (EntityLiving)entity;
        		
        		left.add("Health: " + (el.getHealth() < el.getMaxHealth()? EnumChatFormatting.RED + "" + (int)el.getHealth() + EnumChatFormatting.RESET : EnumChatFormatting.GREEN + "" + (int)el.getHealth() + EnumChatFormatting.RESET) + "/" +EnumChatFormatting.GREEN +(int)el.getMaxHealth());
        		left.add(null);
        		String nametag = (el.hasCustomNameTag()? " (" + el.getCustomNameTag() + ")": "");
        		right.add("Has custom name: " + booleanString(el.hasCustomNameTag()) +EnumChatFormatting.RESET +  nametag);
        		right.add("Eating: " + booleanString(el.isEating()));
        		right.add("On ground: " + booleanString(entity.onGround));
        		right.add("Shearable: " + booleanString(entity instanceof IShearable));
        		right.add("Undead: " + booleanString(el.isEntityUndead()));
        		right.add("Wet: " + booleanString(el.isWet()));
        		right.add("Burning: " + booleanString(el.isBurning()));
        		right.add("Riding: " + booleanString(el.isRiding()));
        		left.add("Type: " + (el instanceof IMob? EnumChatFormatting.RED + "agressive": EnumChatFormatting.GREEN + "passive"));
        		if(el instanceof EntityTameable){
        			EntityTameable eh = (EntityTameable)el;
        			right.add("Tamed: " + booleanString(eh.isTamed()));
        			if(eh.isTamed() && eh.getOwner() !=null) left.add("Owner: " + eh.getOwner().getCommandSenderName());
        			right.add("Sitting: " + booleanString(eh.isSitting()));
        		}
        		if(el instanceof EntityHorse){
        			left.add(null);
        			left.add("Jump strength: " + (float)((EntityHorse)el).getHorseJumpStrength());
        			left.add("Speed: " + (float)((EntityHorse)el).getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
        		}
        		if(el instanceof EntityVillager){
        			EntityVillager v = (EntityVillager)el;
        			String profession = (v.getProfession() == 0? "farmer": v.getProfession() == 1? "librarian": v.getProfession() == 2? "priest": v.getProfession() == 3? "blacksmith": v.getProfession() == 4? "butcher": "unknown");
        			left.add("Profession: " + profession);
        		}
        		if(el instanceof EntityAgeable){
        			if (((EntityAgeable)el).getGrowingAge() < 0) left.add("Time until full growth: around " + Math.abs(((EntityAgeable)el).getGrowingAge() / 20) + " seconds");
        			
        		}
        		if(el instanceof EntitySlime){
        			EntitySlime slime =(EntitySlime)el;
        			left.add("Size: " +slime.getSlimeSize());
        				
        		}
        	}
        	
        }else left.add("There isn't pointed entity.");
		
	}

}
