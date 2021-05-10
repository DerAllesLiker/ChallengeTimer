package de.jonasbro.challengetimer;

import de.jonasbro.challengetimer.utils.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import de.jonasbro.challengetimer.backpack.BackpackManager;
import de.jonasbro.challengetimer.commands.BackpackCommand;
import de.jonasbro.challengetimer.commands.TimerCommand;
import de.jonasbro.challengetimer.listeners.ExplosionListeners;
import de.jonasbro.challengetimer.listeners.JoinListener;
import de.jonasbro.challengetimer.listeners.QuitListener;
import de.jonasbro.challengetimer.timer.Timer;
import de.jonasbro.challengetimer.utils.Config;

public final class Main extends JavaPlugin {

    private static Main instance;

    private Timer timer;
    private Config config;
    private BackpackManager backpackManager;

    @Override
    public void onLoad() {
        instance = this;
        config = new Config();
    }

    @Override
    public void onEnable() {
        System.out.println("[ChallengeTimer] Plugin is loading!");
        System.out.println("[ChallengeTimer] Loading Listeners");


        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new JoinListener(), this);
        manager.registerEvents(new QuitListener(), this);
        manager.registerEvents(new ExplosionListeners(), this);

        System.out.println("[ChallengeTimer] Listeners loaded");

        System.out.println("[ChallengeTimer] Loading Commands");

        getCommand("timer").setExecutor(new TimerCommand());
        getCommand("backpack").setExecutor(new BackpackCommand());

        timer = new Timer();
        backpackManager = new BackpackManager();

        System.out.println("[ChallengeTimer] Commands loaded");
        System.out.println("[ChallengeTimer] Plugin can now be used");

        new UpdateChecker(this, 92229).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                getLogger().info("Congrats your on the latest version.");
            } else {
                getLogger().info("There is a new update available.");
                getLogger().info("Your Version: 1.0"); //TODO Alte Version durch das die letzte ersetzen
                getLogger().info("New Version: 1.1"); //TODO Neue Version hier rein packen
                getLogger().info("Download new version here: https://www.spigotmc.org/resources/challengetimer.92229/");
            }
        });
    }

    @Override
    public void onDisable() {

        System.out.println("[ChallengeTimer] Plugin disabling");
        System.out.println("[ChallengeTimer] Saving to config");


        timer.save();
        backpackManager.save();

        config.save();

        System.out.println("[ChallengeTimer] Stats successfully saved");
        System.out.println("[ChallengeTimer] Thanks for using");
    }

    public static Main getInstance() {
        return instance;
    }

    public Config getConfiguration() {
        return config;
    }

    public Timer getTimer() {
        return timer;
    }

    public BackpackManager getBackpackManager() {
        return backpackManager;
    }
}