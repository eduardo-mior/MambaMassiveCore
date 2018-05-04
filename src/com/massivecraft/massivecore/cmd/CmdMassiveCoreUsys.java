package com.massivecraft.massivecore.cmd;

public class CmdMassiveCoreUsys extends MassiveCoreCommand
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static CmdMassiveCoreUsys i = new CmdMassiveCoreUsys();
	public static CmdMassiveCoreUsys get() { return i; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public CmdMassiveCoreUsysMultiverse cmdMassiveCoreUsysMultiverse = new CmdMassiveCoreUsysMultiverse();
	public CmdMassiveCoreUsysUniverse cmdMassiveCoreUsysUniverse = new CmdMassiveCoreUsysUniverse();
	public CmdMassiveCoreUsysWorld cmdMassiveCoreUsysWorld = new CmdMassiveCoreUsysWorld();
	public CmdMassiveCoreUsysAspect cmdMassiveCoreUsysAspect = new CmdMassiveCoreUsysAspect();
	
}
