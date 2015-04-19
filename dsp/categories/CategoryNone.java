package dsp.categories;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import dsp.api.DebugScreenCategory;

public class CategoryNone extends DebugScreenCategory{

	public CategoryNone(String displayName) {
		super(displayName);
	}

	@Override
	public void processInfo(List<String> left, List<String> right, EntityPlayer player, World world, boolean modder) {	
		left.add(EnumChatFormatting.BOLD + "DebugScren+ " + EnumChatFormatting.RESET + "mod made by Abcdmult. Thanks for playing!");
		left.add(null);
		left.add("Please, dont repost this mod anywhere!");
		left.add("You're stealing my money when you repost my mods :(");
		left.add(null);
		left.add("If you have any questions or you found");
		left.add("a bug, please, send me email: abcdmult@gmail.com");
	}

}
