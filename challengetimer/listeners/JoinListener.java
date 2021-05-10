package de.jonasbro.challengetimer.listeners;

import de.jonasbro.challengetimer.scoreboard.TestScoreboard;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.setJoinMessage(ChatColor.GREEN.toString() + ChatColor.UNDERLINE + player.getName() + " joined the server.");

        player.sendMessage(ChatColor.GOLD + "Welcome to the Server! \n\"Have fun (^:");

        new TestScoreboard(player);
    }
}