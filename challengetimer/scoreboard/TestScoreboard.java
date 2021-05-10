package de.jonasbro.challengetimer.scoreboard;

import de.jonasbro.challengetimer.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TestScoreboard extends ScoreboardBuilder {

    private int socialId;

    public TestScoreboard(Player player) {
        super(player, ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + "  This is a cool Server  ");
        socialId = 0;

        run();
    }

    @Override
    public void createScoreboard() {
        setScore("test", 8);
        setScore(ChatColor.DARK_GRAY.toString(), 7);
        setScore(ChatColor.GREEN.toString() + player.getName(), 6);
        setScore(ChatColor.GRAY.toString(), 5);
        setScore(ChatColor.GRAY + "Your rank" + ChatColor.DARK_GRAY + ":", 4);
        if(player.isOp()) {
            setScore(ChatColor.RED + "Admin", 3);
        } else {
            setScore(ChatColor.GRAY + "Player", 3);
        }
    }

    @Override
    public void update() {

    }

    private void run() {
        new BukkitRunnable() {
            @Override
            public void run() {

                switch (socialId) {
                    case 0:
                        setScore(ChatColor.LIGHT_PURPLE.toString() +"https://twitter.com/JonasBr38442232", 2);
                        break;
                    case 1:
                        setScore(ChatColor.AQUA + "https://discord.gg/wDsRkK2Dyc", 2);
                    case 2:
                        setScore(ChatColor.YELLOW.toString() + "https://github.com/DerAllesLiker", 2);
                }

                socialId++;

                if(socialId >= 3) {
                    socialId = 0;
                }

            }
        }.runTaskTimer(Main.getInstance(), 20, 20);
    }
}