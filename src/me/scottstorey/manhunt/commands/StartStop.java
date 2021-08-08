package me.scottstorey.manhunt.commands;

import me.scottstorey.manhunt.Main;
import me.scottstorey.manhunt.items.Compass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class StartStop implements CommandExecutor, Listener {

    private JavaPlugin plugin;
    public StartStop(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    PotionEffect glow;
    public static int GlowDuration = 72000;
    public static Location loc;
    public static BukkitTask runnable;

    public static boolean gameRunning = false;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;

            if(cmd.getName().equals("manhuntstart")){
                if(p.hasPermission("manhunt.start")) {
                    if(Main.hunter.size() < 1){
                        p.sendMessage("Could not start game. No one is assigned as a hunter");
                    }
                    if(Main.runner.size() != 1){
                        p.sendMessage("Could not start game. No one is assigned as a runner");
                    }

                    for(int a = 0; a < Main.hunter.size(); a++){
                        Main.hunter.get(a).getInventory().addItem(Compass.compass());
                    }

                    //glowing
                    glow = new PotionEffect(PotionEffectType.GLOWING, GlowDuration, 1);
                    Main.runner.get(0).addPotionEffect(glow);

                    //border
                    WorldBorder worldborder = p.getWorld().getWorldBorder();
                    worldborder.setCenter(p.getLocation().getBlockX(), p.getLocation().getBlockZ());
                    worldborder.setSize(Main.border);

                    runnable = new BukkitRunnable() {
                        public void run() {
                            if (Main.runner.size() == 0) {
                                Bukkit.broadcastMessage("You must assign a runner");
                            } else {
                                loc = Main.runner.get(0).getLocation();
                                for (int b = 0; b < Main.hunter.size(); b++) {
                                    if (Main.hunter.contains(Main.hunter.get(b))) {
                                        Main.hunter.get(b).setCompassTarget(loc);
                                    }
                                }
                            }
                        }
                    }.runTaskTimerAsynchronously(Main.plugin, 0, 0);
                }else {
                    p.sendMessage(ChatColor.RED + "Insufficient Permission");
                }

                Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "GAME HAS STARTED");
                gameRunning = true;
            }

            if (cmd.getName().equalsIgnoreCase("manhuntstop")){
                if(p.hasPermission("manhunt.stop")) {

                    int abcdef = 0;

                    if(!gameRunning){
                        p.sendMessage("Could not stop game, as game was never started");
                        abcdef += 1;
                    }

                    try {
                        if(abcdef == 1){
                            //message has already been sent
                        } else {
                            runnable.cancel();
                        }
                    } catch (NullPointerException e){
                        p.sendMessage("Could not finish game because game was not started, so Compass Runnable throws an error");
                    }
                    for (int c = 0; c < Main.hunter.size(); c++) {
                        if (Main.hunter.get(c).getBedSpawnLocation() == null) {
                            loc = Main.hunter.get(c).getBedSpawnLocation();
                        } else {
                            loc = Main.hunter.get(c).getWorld().getSpawnLocation();
                        }
                    }

                    for (int d = 0; d < Main.hunter.size(); d++){
                        Main.hunter.get(d).getInventory().clear();
                    }

                    Main.border = 100000000;
                    WorldBorder worldborder = p.getWorld().getWorldBorder();
                    worldborder.setCenter(p.getLocation().getBlockX(), p.getLocation().getBlockZ());
                    worldborder.setSize(Main.border);

                    Main.runner.get(0).getInventory().clear();
                    Main.runner.get(0).removePotionEffect(PotionEffectType.GLOWING);

                    Main.hunter.clear();
                    Main.runner.clear();

                    Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "GAME HAS ENDED");
                    gameRunning = false;
                }
            }
        }
        return true;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        if(Main.runner.contains(p)){
            StartStop.GlowDuration = 0;
            Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.GOLD + "" + p.getName() + " has won!");
        }
    }

}
