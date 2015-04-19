package dsp.api;

import cpw.mods.fml.common.eventhandler.Event;

public class EventRegisterModule extends Event{
	
	public IModule module;
	
	public EventRegisterModule(IModule module){
		this.module = module;
	}

}
