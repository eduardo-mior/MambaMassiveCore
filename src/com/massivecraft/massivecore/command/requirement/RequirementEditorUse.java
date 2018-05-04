package com.massivecraft.massivecore.command.requirement;

import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.editor.CommandEditAbstract;
import com.massivecraft.massivecore.util.Txt;
import org.bukkit.command.CommandSender;

public class RequirementEditorUse extends RequirementAbstract
{
	private static final long serialVersionUID = 1L;
	
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static RequirementEditorUse i = new RequirementEditorUse();
	public static RequirementEditorUse get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public boolean apply(CommandSender sender, MassiveCommand command)
	{
		if ( ! (command instanceof CommandEditAbstract)) return false;
		CommandEditAbstract<?, ?> commandEditor = (CommandEditAbstract<?, ?>)command;
		return commandEditor.getSettings().getUsed(sender) != null;
	}
	
	@Override
	public String createErrorMessage(CommandSender sender, MassiveCommand command)
	{
		if ( ! (command instanceof CommandEditAbstract)) return Txt.parse("§cEste não é um editor.");
		CommandEditAbstract<?, ?> commandEditor = (CommandEditAbstract<?, ?>)command;
		
		String noun = commandEditor.getSettings().getObjectType().getName();
		String aan = Txt.aan(noun);
		
		return Txt.parse("§cVocê deve usar §c%s §c%s§c para edita-lo", aan, noun);
	}
	
}
