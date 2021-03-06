package com.massivecraft.massivecore.command.type.primitive;

import org.bukkit.command.CommandSender;

public class TypeDouble extends TypeAbstractNumber<Double>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static TypeDouble i = new TypeDouble();
	public static TypeDouble get() { return i; }
	public TypeDouble() { super(Double.class); }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public String getName()
	{
		return "n�mero com decimais";
	}
	
	@Override
	public Double valueOf(String arg, CommandSender sender) throws Exception
	{
		return Double.parseDouble(arg);
	}

}
