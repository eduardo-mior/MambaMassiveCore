package com.massivecraft.massivecore.cmd;

import com.massivecraft.massivecore.ConfServer;

public class CmdMassiveCoreId extends MassiveCoreCommand
{
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform()
	{
		this.msg("<i>O id deste servidor e \"<h>%s<i>\".", ConfServer.serverid);
	}

}
