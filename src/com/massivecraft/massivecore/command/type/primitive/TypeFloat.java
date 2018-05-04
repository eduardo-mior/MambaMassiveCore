package com.massivecraft.massivecore.command.type.primitive;

import org.bukkit.command.CommandSender;

public class TypeFloat extends TypeAbstractNumber<Float>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static TypeFloat i = new TypeFloat();
	public static TypeFloat get() { return i; }
	public TypeFloat() { super(Float.class); }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public String getName()
	{
		return "número com decimais";
	}
	
	@Override
	public Float valueOf(String arg, CommandSender sender) throws Exception
	{
		return Float.parseFloat(arg);
	}

}
