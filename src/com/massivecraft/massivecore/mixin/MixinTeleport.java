package com.massivecraft.massivecore.mixin;

import com.massivecraft.massivecore.MassiveCoreMConf;
import com.massivecraft.massivecore.engine.EngineMassiveCoreTeleportMixinCause;
import com.massivecraft.massivecore.event.EventMassiveCorePlayerPSTeleport;
import com.massivecraft.massivecore.ps.PS;
import com.massivecraft.massivecore.teleport.Destination;
import com.massivecraft.massivecore.teleport.ScheduledTeleport;
import com.massivecraft.massivecore.util.IdUtil;
import com.massivecraft.massivecore.util.Txt;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.permissions.Permissible;
import org.bukkit.util.Vector;

public class MixinTeleport extends Mixin
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static MixinTeleport d = new MixinTeleport();
	private static MixinTeleport i = d;
	public static MixinTeleport get() { return i; }

	// -------------------------------------------- //
	// METHODS
	// -------------------------------------------- //
	
	public boolean isCausedByMixin(PlayerTeleportEvent event)
	{
		return EngineMassiveCoreTeleportMixinCause.get().isCausedByTeleportMixin(event);
	}

	public void teleport(Object teleportee, Destination destination) throws TeleporterException
	{
		this.teleport(teleportee, destination, 0);
	}
	
	public void teleport(Object teleportee, Destination destination, Permissible delayPermissible) throws TeleporterException
	{
		int delaySeconds = MassiveCoreMConf.get().getTpdelay(delayPermissible);
		this.teleport(teleportee, destination, delaySeconds);
	}
	
	// -------------------------------------------- //
	// CORE LOGIC
	// -------------------------------------------- //
	
	public static void teleportPlayer(Player player, PS ps) throws TeleporterException
	{
		// Base the PS location on the entity location
		ps = ps.getEntity(true);
		ps = PS.valueOf(player.getLocation()).with(ps);
		
		// Bukkit Location
		Location location = null;
		try
		{
			location = ps.asBukkitLocation();
			
		}
		catch (Throwable e)
		{
			throw new TeleporterException(Txt.parse("§cNão foi possivel calcular a localização:§c %s", e.getMessage()));
		}
		
		// eject passengers and unmount before transport
		player.eject();
		Entity vehicle = player.getVehicle();
		if (vehicle != null) vehicle.eject();
		
		// Do the teleport
		EngineMassiveCoreTeleportMixinCause.get().setMixinCausedTeleportIncoming(true);
		player.teleport(location);
		EngineMassiveCoreTeleportMixinCause.get().setMixinCausedTeleportIncoming(false);
		
		// Bukkit velocity
		Vector velocity = null;
		try
		{
			velocity = ps.asBukkitVelocity();
		}
		catch (Throwable e)
		{
			return;
		}
		player.setVelocity(velocity);
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	public void teleport(Object teleporteeObject, Destination destination, int delaySeconds) throws TeleporterException
	{
		String teleporteeId = IdUtil.getId(teleporteeObject);
		if (!IdUtil.isPlayerId(teleporteeId)) throw new TeleporterException(Txt.parse("§c%s não é um player.", MixinDisplayName.get().getDisplayName(teleporteeId, IdUtil.getConsole())));
		
		PS ps;
		try
		{
			ps = destination.getPs(teleporteeId);
		}
		catch (Throwable e)
		{
			throw new TeleporterException(e.getMessage());
		}
		
		String desc = destination.getDesc(teleporteeId);
		if (delaySeconds > 0)
		{
			// With delay
			if (desc != null && ! desc.isEmpty())
			{
				MixinMessage.get().msgOne(teleporteeId, "§eTeleportando para §e"+ desc + "§e em §e"+delaySeconds+"§es. Não se mova!");
			}
			else
			{
				MixinMessage.get().msgOne(teleporteeId, "§eIniciando teleporte em §e"+delaySeconds+" segundos§e. Não se mova!");
			}
			
			new ScheduledTeleport(teleporteeId, destination, delaySeconds).schedule();
		}
		else
		{
			// Without delay AKA "now"/"at once"
			
			// Run event
			EventMassiveCorePlayerPSTeleport event = new EventMassiveCorePlayerPSTeleport(teleporteeId, MixinSenderPs.get().getSenderPs(teleporteeId), destination);
			event.run();
			if (event.isCancelled()) return;
			destination = event.getDestination();
			desc = destination.getDesc(teleporteeId);
			
			if (desc != null && ! desc.isEmpty())
			{
				MixinMessage.get().msgOne(teleporteeId, "§eTeleportado com sucesso para "+desc+"§e.");
			}
			
			Player teleportee = IdUtil.getPlayer(teleporteeId);
			if (teleportee != null)
			{
				teleportPlayer(teleportee, ps);
			}
			else
			{
				MixinSenderPs.get().setSenderPs(teleporteeId, ps);
			}
		}
	}
	
}
