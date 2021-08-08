package me.scottstorey.manhunt;

import me.scottstorey.manhunt.commands.*;
import me.scottstorey.manhunt.listeners.HunterRespawn;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin implements Listener {

    public static ArrayList<Player> runner = new ArrayList<>();
    public static ArrayList<Player> hunter = new ArrayList<>();
    public static double border = 0;
    public static int repeat = 6000;
    public static int hour = 72000;

    public static Main plugin;

    public void onEnable () {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Manhunt Enabled");
        plugin = this;

        getCommand("border").setExecutor(new Border(this));
        getCommand("hunter").setExecutor(new Hunter(this));
        getCommand("runner").setExecutor(new Runner(this));
        getCommand("manhuntstart").setExecutor(new StartStop(this));
        getCommand("manhuntstop").setExecutor(new StartStop(this));

        getServer().getPluginManager().registerEvents(new HunterRespawn(), this);
        getServer().getPluginManager().registerEvents(new StartStop(this), this);
    }

    public void onDisable () {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Manhunt Disabled");
    }

}
