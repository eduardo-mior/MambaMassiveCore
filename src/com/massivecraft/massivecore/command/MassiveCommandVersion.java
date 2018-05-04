package com.massivecraft.massivecore.command;

import com.massivecraft.massivecore.util.Txt;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import java.util.List;

public class MassiveCommandVersion extends MassiveCommand
{
	// -------------------------------------------- //
	// CONSTANTS
	// -------------------------------------------- //
	
	public static final String NOT_SPECIFIED = Txt.parse("<em><silver>não especificada.");
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	protected final Plugin plugin;
	public Plugin getPlugin() { return this.plugin; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public MassiveCommandVersion(Plugin plugin)
	{
		this.plugin = plugin;
		
		this.setAliases("versão", "v", "versao", "version");
		
		this.setDesc("§6 v,versao §8-§7 Mostra a versão do plugin.");
		
		// Priority
		this.setPriority(-1);
		
        // VisibilityMode
        this.setVisibility(Visibility.SECRET);
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform()
	{
		PluginDescriptionFile pdf = this.getPlugin().getDescription();
		
		String name = pdf.getName();
		String version = pdf.getVersion();
		String website = pdf.getWebsite();
		
		String description = pdf.getDescription();
		if (description != null) description = Txt.parse("<i>"+description);
		
		String authors = null;
		List<String> authorList = pdf.getAuthors();
		if (authorList != null && authorList.size() > 0)
		{
			authors = Txt.implodeCommaAndDot(authorList, "<aqua>%s", "<i>, ", " <i>e ", "");
			authors = Txt.parse(authors);
		}
		
		this.sendTitle();
		this.sendEntry("nome", name);
		this.sendEntry("versão", version);
		this.sendEntry("website", website);
		this.sendEntry("autores", authors);
		this.sendEntry("descrição", description);
	}
	
	public void sendTitle()
	{
		message(Txt.titleize("§eInformações do plugin"));
	}
	
	public void sendEntry(String key, String value)
	{
		message(Txt.parse("§6%s: §e%s", Txt.upperCaseFirst(key), value == null ? NOT_SPECIFIED : value));
	}

}
