package de.juyas.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.inventivetalent.nicknamer.api.NickManager;
import de.juyas.mysql.api.NickCoinAPI;

public final class NickNamer 
{

	private static boolean sqlAPI;
	private static boolean nicker;
	
	private NickNamer() 
	{
		nicker = Bukkit.getPluginManager().isPluginEnabled("NickNamer");
		sqlAPI = Bukkit.getPluginManager().isPluginEnabled("TSQL");
	}
	
	public static boolean createNickNamer()
	{
		new NickNamer();
		return nicker && sqlAPI;
	}
	
	public static boolean canUseSQL()
	{
		return sqlAPI;
	}
	
	public static boolean canUseNicker()
	{
		return nicker;
	}
	
	public static NickNamer getInstance()
	{	
		NickNamer nn = new NickNamer();
		if(nicker && sqlAPI)
		{			
			return nn;	
		}
		else return null;	
	}
	
	private NickManager getNicker()
	{
		return org.inventivetalent.nicknamer.NickNamer.getNickManager();
	}
	
	public void setNick(Player player, String name)
	{
		if(player.getName().equals(name) || name == null)
		{
			removeNick(player);
			return;
		}
		getNicker().setNick(player.getUniqueId(), name);	
	}
	
	public void removeNick(Player player)
	{
		getNicker().removeNick(player.getUniqueId());
	}
	
	public boolean isNicked(Player player)
	{
		return getNicker().isNicked(player.getUniqueId());
	}
	
	public boolean isNickUsed(String name)
	{
		return getNicker().isNickUsed(name);
	}
	
	public void setSkin(Player player, String skin)
	{
		if(player.getName().equals(skin) || skin == null)
		{
			removeSkin(player);
			return;
		}
		getNicker().setSkin(player.getUniqueId(), skin);
	}
	
	public void removeSkin(Player player)
	{
		getNicker().removeSkin(player.getUniqueId());
	}

	public String getSQLNick(Player player)
	{
		String n = NickCoinAPI.getNick(player.getUniqueId());
		if(!n.isEmpty()) return n;
		else return null;	
	}
	
}
