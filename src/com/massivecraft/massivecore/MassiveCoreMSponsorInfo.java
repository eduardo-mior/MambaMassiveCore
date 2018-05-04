package com.massivecraft.massivecore;

import com.massivecraft.massivecore.collections.MassiveList;
import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.TimeUnit;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivecore.util.WebUtil;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MassiveCoreMSponsorInfo extends Entity<MassiveCoreMSponsorInfo>
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	
	protected static transient MassiveCoreMSponsorInfo i;
	public static MassiveCoreMSponsorInfo get() { return i; }
	
	// -------------------------------------------- //
	// COMMON
	// -------------------------------------------- //
	
	public boolean enabled = false;
	
	public long enabledToMillis = 2473890400000L;
	
	public List<String> indicatorFileNames = new MassiveList<>(
		"/home/smpicnic"
	);
	
	// -------------------------------------------- //
	// CONSOLE
	// -------------------------------------------- //
	
	public boolean consoleEnabled = false;
	
	public int consoleDelayTicks = 100;
	
	public List<String> consoleMsgs = new MassiveList<>(
		"",
		"<pink>____----====[ <aqua>Anuncio by MassiveCore<pink> ]====----____",
		"§aEsta versao do MassiveCore foi totalmente adaptada por RUSHyoutuber.",
		"",
		"§eReporte qualquer tipo de BUG atraves dos meios de comunicacao:",
		"",
		"§bSkype:§f live:eduardo-mior.",
		"§9Facebook:§f www.fb.com/eduardo.mior.3",
		"§3TeamSpeak:§f rush.ts3elite.com",
		"§bTwiiter:§f @CanalDaRUSH",
		"§aWhatsApp:§f (54) 91343192",
		"§cE-Mail:§f 172422@upf.br",
		"",
		"<i>Voce desativar esta mensagem acessando na config do plugin.",
		"" // NOTE: an empty line like this makes sense in console but not for players.
	);
	
	// -------------------------------------------- //
	// INGAME
	// -------------------------------------------- //
	
	public boolean ingameEnabled = false;
	
	public int ingameDelayTicks = 600;
	
	public List<String> ingameMsgs = new MassiveList<>(
		"",
		"<pink>____----====[ <aqua>Anúncio by MassiveCore<pink> ]====----____",
		"§aEsta versão do MassiveCore foi totalmente adaptada por RUSHyoutuber.",
		"",
		"§eReporte qualquer tipo de BUG através dos meios de comunicacao:",
		"",
		"§bSkype:§f live:eduardo-mior.",
		"§9Facebook:§f www.fb.com/eduardo.mior.3",
		"§3TeamSpeak:§f rush.ts3elite.com",
		"§bTwiiter:§f @CanalDaRUSH",
		"§aWhatsApp:§f (54) 91343192",
		"§cE-Mail:§f 172422@upf.br",
		"",
		"<i>Você desativar esta mensagem acessando na config do plugin."
	);
	
	public List<SoundEffect> ingameSoundEffects = new MassiveList<>(
		SoundEffect.valueOf(
			"ENTITY_PLAYER_LEVELUP",
			1.0F,
			0.8F
		),
		SoundEffect.valueOf(
			"ENTITY_EXPERIENCE_ORB_PICKUP",
			1.0F,
			0.8F
		)
	);
	
	public String ingameLink = "https://www.youtube.com/iRUSHeSports";

	// -------------------------------------------- //
	// UPDATE
	// -------------------------------------------- //
	
	public static final transient long SPONSOR_INFO_UPDATE_MILLIS = TimeUnit.MILLIS_PER_HOUR;
	public static final transient URL SPONSOR_INFO_URL;
	static
	{
		URL url = null;
		try
		{
			url = new URL("https://pastebin.com/raw/eUmnqLqU");
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		SPONSOR_INFO_URL = url;
	}
	
	public static void update()
	{
		// If enough time has passed since last update ...
		long now = System.currentTimeMillis();
		long last = MassiveCoreMConf.get().sponsorUpdateMillis;
		long since = now - last;
		if (since < SPONSOR_INFO_UPDATE_MILLIS) return;
		
		// ... then mark update ...
		MassiveCoreMConf.get().sponsorUpdateMillis = now;
		MassiveCoreMConf.get().changed();
		
		// ... and start the update.
		updateInner();
	}
	
	public static void updateInner()
	{
		Bukkit.getScheduler().runTaskAsynchronously(MassiveCore.get(), new Runnable()
		{
			@Override
			public void run()
			{
				List<String> lines;
				try
				{
					lines = WebUtil.getLines(SPONSOR_INFO_URL);
				}
				catch (IOException e)
				{
					e.printStackTrace();
					return;
				}
				final String json = Txt.implode(lines, "\n");
				Bukkit.getScheduler().runTask(MassiveCore.get(), new Runnable()
				{
					@Override
					public void run()
					{
						MassiveCoreMSponsorInfo web = MassiveCore.get().getGson().fromJson(json, MassiveCoreMSponsorInfo.class);
						MassiveCoreMSponsorInfo live = MassiveCoreMSponsorInfo.get();
						live.load(web);
						live.changed();
					}
				});
			}
		});
	}
	
}
