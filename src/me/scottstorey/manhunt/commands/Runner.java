package me.scottstorey.manhunt.commands;

import me.scottstorey.manhunt.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class Runner implements CommandExecutor {

    private JavaPlugin plugin;
    public Runner(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;

            if(cmd.getName().equalsIgnoreCase("runner")){

                if(Main.runner.size() == 1) {
                    Bukkit.broadcastMessage(ChatColor.BLUE + "" + p.getName() + " can not be a runner because the runner is " + Main.runner.get(0).getName());
                } else {
                    if (args.length != 1) {
                        p.sendMessage(ChatColor.RED + "Try /runner <player>");
                    } else {
                        Player r = Bukkit.getPlayer(args[0]);
                        Main.runner.add(r);
                        Bukkit.broadcastMessage(ChatColor.BLUE + "" + r.getName() + " set as runner");
                    }
                }
            }
        }

        return false;
    }
}
