package com.massivecraft.massivecore;

public enum SenderType
{
	PLAYER, // A player. Such as Notch or Dinnerbone. @console não é um player.
	NONPLAYER, // A sender which não é um player. Such as @console.
	ANY, // Anyone. Both players, and nonplayers.
	
	;
}
