package me.scottstorey.manhunt.commands;

import me.scottstorey.manhunt.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Border implements CommandExecutor {

    private JavaPlugin plugin;
    public Border(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("border")) {
                if (p.hasPermission("manhunt.border")) {
                    if (args.length < 2) {
                        p.sendMessage(ChatColor.RED + "Try /border set <value>");
                    } else if (args.length == 2) {
                        if (args[0].equalsIgnoreCase("set")) {
                            try {
                                Main.border = Integer.parseInt(args[1]);
                                Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "Border set to " + Main.border + " blocks");
                            } catch (NumberFormatException e) {
                                p.sendMessage(ChatColor.RED + "You did not enter a value");
                            }
                        } else {
                            p.sendMessage(ChatColor.RED + "Try /border set <value>");
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + "Try /border set <value>");
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "Insufficient Permission");
                }
            }
        }

        return false;
    }
}
