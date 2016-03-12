package de.juyas.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import de.juyas.mysql.api.NickCoinAPI;

public final class PlayerCoins 
{

	private static boolean sqlAPI;
	
	private PlayerCoins() 
	{
		sqlAPI = Bukkit.getPluginManager().isPluginEnabled("TSQL");
	}
	
	public static boolean createNickNamer()
	{
		new PlayerCoins();
		return sqlAPI;
	}
	
	public static boolean canUseSQL()
	{
		return sqlAPI;
	}
	
	public static PlayerCoins getInstance()
	{	
		PlayerCoins pc = new PlayerCoins();
		if(sqlAPI)
		{			
			return pc;
		}
		else return null;	
	}
	
	public boolean setCoins(Player player, int coins)
	{
		if(coins < 0) return false;
		NickCoinAPI.setCoins(player.getUniqueId(), coins);
		return true;
	}
	
	public int getCoins(Player player)
	{
		return NickCoinAPI.getCoins(player.getUniqueId());
	}
	
	public boolean addCoins(Player player, int coins)
	{
		if(coins < 0) return remCoins(player, -coins);
		else 
		{
			setCoins(player, getCoins(player) + coins);
			return true;
		}
	}
	
	public boolean remCoins(Player player, int coins)
	{
		int c = getCoins(player) - coins;
		if(c < 0) return false;
		setCoins(player, c);
		return true;
	}
	
	public boolean hasEnoughCoins(Player player, int coins)
	{
		int c = getCoins(player);
		return c >= coins;
	}
	
}
