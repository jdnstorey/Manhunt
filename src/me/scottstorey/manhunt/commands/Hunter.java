package me.scottstorey.manhunt.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static me.scottstorey.manhunt.Main.hunter;

public class Hunter implements CommandExecutor {

    private JavaPlugin plugin;
    public Hunter(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("hunter")) {
                if (args.length != 1) {
                    p.sendMessage(ChatColor.RED + "Try /hunter <player>");
                } else {
                    Player h = Bukkit.getPlayer(args[0]);
                    hunter.add(h);
                    Bukkit.broadcastMessage(ChatColor.BLUE + "" + h.getName() + " set as hunter");
                }
            }
        }

        return false;
    }

}
