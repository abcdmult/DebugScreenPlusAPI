# DebugScreenPlusAPI
Minecraft mod DebugScreen+ API

How to use:

If you want to create additional category, create class that extends DebugScreenCategory, fill it with your info and register in init method with DebugScreenPlusAPI.registerCategory(new YourCategory("Your category name")).


If you want to add some info to Player/World/PointedEntity/PointedBlock category, create class that implements IPlayerModule/IWorldModule/IPointedEntityModule/IPointedBlockModule, fill it with your info and register in init method with DebugScreenPlusAPI.registerModule(new YourModule())
