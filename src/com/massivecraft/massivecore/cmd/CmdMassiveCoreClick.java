package com.massivecraft.massivecore.cmd;

import com.massivecraft.massivecore.MassiveCoreMConf;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.Visibility;
import com.massivecraft.massivecore.command.type.TypeStringCommand;

public class CmdMassiveCoreClick extends MassiveCoreCommand
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static CmdMassiveCoreClick i = new CmdMassiveCoreClick();
	public static CmdMassiveCoreClick get() { return i; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdMassiveCoreClick()
	{
		
		this.setDesc("§6 click §8-§7 Click para executar um comando.");
		
		// Parameters
		this.addParameter(null, TypeStringCommand.get(), "command", "none", true).setDesc("o comando para executar");
		
		this.setVisibility(Visibility.INVISIBLE);
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		MassiveCoreMConf.get().clickSound.run(me);
		String command = this.readArg();
		if (command == null) return;
		//MixinCommand.get().dispatchCommand(sender, command);
	}
	
}
