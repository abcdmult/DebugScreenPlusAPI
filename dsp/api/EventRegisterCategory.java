package dsp.api;

import cpw.mods.fml.common.eventhandler.Event;

public class EventRegisterCategory extends Event{
	
	public DebugScreenCategory category;
	
	public EventRegisterCategory(DebugScreenCategory c){
		this.category = c;
	}
}
