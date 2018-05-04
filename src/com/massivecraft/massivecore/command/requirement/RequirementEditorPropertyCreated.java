package com.massivecraft.massivecore.command.requirement;

import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.editor.CommandEditAbstract;
import com.massivecraft.massivecore.command.editor.Property;
import com.massivecraft.massivecore.util.Txt;
import org.bukkit.command.CommandSender;

public class RequirementEditorPropertyCreated extends RequirementAbstract
{
	private static final long serialVersionUID = 1L;
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	protected final boolean createdTarget;
	public boolean isCreatedTarget() { return this.createdTarget; }
	
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	public static RequirementEditorPropertyCreated get(boolean createdTarget) { return new RequirementEditorPropertyCreated(createdTarget); }
	public RequirementEditorPropertyCreated(boolean createdTarget)
	{
		this.createdTarget = createdTarget;
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public boolean apply(CommandSender sender, MassiveCommand command)
	{
		return this.applyInner(sender, command);
	}
	
	public <O, V> boolean applyInner(CommandSender sender, MassiveCommand command)
	{
		if ( ! (command instanceof CommandEditAbstract)) return false;
		
		@SuppressWarnings("unchecked")
		CommandEditAbstract<O, V> commandEditor = (CommandEditAbstract<O, V>)command;
		
		Property<O, V> property = commandEditor.getProperty();
		if (property == null) return false;
		
		O used = commandEditor.getObject(sender);
		if (used == null) return false;
		
		boolean created = (property.getRaw(used) != null);
		
		return created == this.isCreatedTarget();
	}
	
	@Override
	public String createErrorMessage(CommandSender sender, MassiveCommand command)
	{
		if ( ! (command instanceof CommandEditAbstract)) return Txt.parse("<b>Este nao e um editor!");
		CommandEditAbstract<?, ?> commandEditor = (CommandEditAbstract<?, ?>)command;
		
		Property<?, ?> property = commandEditor.getProperty();
		return Txt.parse("§cVocê deve " + (this.isCreatedTarget() ? "criar" : "deletar") + " " + (property != null ? property.getName() : "a propriedade")  + " antes de você fazer isso.");
	}
	
}
