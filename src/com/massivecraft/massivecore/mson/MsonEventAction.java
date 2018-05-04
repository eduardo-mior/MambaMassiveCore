package com.massivecraft.massivecore.mson;

import com.massivecraft.massivecore.util.Txt;

public enum MsonEventAction
{
	// -------------------------------------------- //
	// ENUM
	// -------------------------------------------- //

	SUGGEST_COMMAND,
	RUN_COMMAND,
	OPEN_URL,
	SHOW_TEXT,
	SHOW_ITEM
	
	// End of list
	;
	
	// -------------------------------------------- //
	// PROPERTIES
	// -------------------------------------------- //

	public MsonEventType getType()
	{
		if (this == SHOW_TEXT) return MsonEventType.HOVER;
		if (this == SHOW_ITEM) return MsonEventType.HOVER;
		return MsonEventType.CLICK;
	}
	
	public String getTooltipPrefix()
	{
		if (this == SUGGEST_COMMAND) return Txt.parse("<gray>Clique para <gold>");
		if (this == RUN_COMMAND) return Txt.parse("<gray>Clique para <gold>");
		if (this == OPEN_URL) return Txt.parse("<gray>Link: <gold>");
		return null;
	}

}
