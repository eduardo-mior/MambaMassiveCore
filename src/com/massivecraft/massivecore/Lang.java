package com.massivecraft.massivecore;

import com.massivecraft.massivecore.mson.Mson;
import org.bukkit.ChatColor;

import static com.massivecraft.massivecore.mson.Mson.mson;

public class Lang
{
	public static final String PERM_DEFAULT_DENIED_FORMAT = "§cVocê não tem permissão para fazer isso.";
	public static final String PERM_DEFAULT_DESCRIPTION = "§cfazer isso";
	
	public static final String COMMAND_SENDER_MUST_BE_PLAYER = "§cEste comando nao pode ser usado pelo console.";
	public static final String COMMAND_SENDER_MUSNT_BE_PLAYER = "§cEste comando não pode ser usado por jogadores.";
	public static final String COMMAND_TITLES_MUST_BE_AVAILABLE = "§cEste comando requer o Minecraft versão 1.8 pois ele usa titles.";
	public static final String COMMAND_TOO_FEW_ARGUMENTS = "<i>Você usou argumentos inválidos para este comando.";
	public static final String COMMAND_TOO_MANY_ARGUMENTS = "<i>Você usou muitos argumentos para este comando.";
	public static final String COMMAND_TOO_MANY_ARGUMENTS2 = "<i>Tente usar §6/f ajuda §epara obter ajuda.";
	
	public static final Mson COMMAND_REPLACEMENT = mson("REPLACEMENT").color(ChatColor.YELLOW);
	
	public static final Mson COMMAND_CHILD_AMBIGUOUS = mson("§eComando não encontrado.").color(ChatColor.YELLOW);
	public static final Mson COMMAND_CHILD_NONE = mson("§eComando não encontrado.").color(ChatColor.YELLOW);
	public static final Mson COMMAND_CHILD_HELP = mson("§eUse §6/f §epara abrir o menu de ajuda.").color(ChatColor.YELLOW);
	
	public static final String COMMAND_TOO_MANY_TAB_SUGGESTIONS = "§cHá §c%d §cpossibilidades de auto-completamento para isto. Tente ser mais especifico.";
}
