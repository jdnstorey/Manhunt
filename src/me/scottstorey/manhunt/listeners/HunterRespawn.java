package me.scottstorey.manhunt.listeners;

import me.scottstorey.manhunt.Main;
import me.scottstorey.manhunt.items.Compass;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class HunterRespawn implements Listener {

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        if(Main.hunter.contains(p)){
            p.getInventory().addItem(Compass.compass());
        }
    }

}
