package de.jonasbro.challengetimer.commands;

import de.jonasbro.challengetimer.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import de.jonasbro.challengetimer.timer.Timer;

public class TimerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0) {
            sendUsage(sender);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "resume": {
                Timer timer = Main.getInstance().getTimer();

                if (timer.isRunning()) {
                    sender.sendMessage(ChatColor.RED + "The Timer is already running.");
                    break;
                }

                timer.setRunning(true);
                sender.sendMessage(ChatColor.GRAY + "The timer was successfully started.");
                break;
            }
            case "pause": {
                Timer timer = Main.getInstance().getTimer();

                if (!timer.isRunning()) {
                    sender.sendMessage(ChatColor.RED + "The Timer isnt running.");
                    break;
                }

                timer.setRunning(false);
                sender.sendMessage(ChatColor.GRAY + "The Timer was stopped.");
                break;
            }
            case "time": {
                if(args.length != 2) {
                    sender.sendMessage(ChatColor.GRAY + "usage" + ChatColor.DARK_GRAY + ": " + ChatColor.BLUE +
                            "/timer time <time>");
                    return true;
                }

                try {
                    Timer timer = Main.getInstance().getTimer();

                    timer.setRunning(false);
                    timer.setTime(Integer.parseInt(args[1]));
                    sender.sendMessage(ChatColor.GRAY + "The time was set to " + args[1]);
                } catch (NumberFormatException e) {
                    sender.sendMessage(ChatColor.RED + "The parameter 2 must be a number.");
                }
                break;
            }
            case "reset": {
                Timer timer = Main.getInstance().getTimer();

                timer.setRunning(false);
                timer.setTime(0);
                sender.sendMessage(ChatColor.GRAY + "The Timer was reset.");
                break;
            }
            default:
                sendUsage(sender);
                break;
        }
        return false;
    }

    private void sendUsage(CommandSender sender) {
        sender.sendMessage(ChatColor.GRAY + "usage" + ChatColor.DARK_GRAY + ": " + ChatColor.BLUE +
                "/timer resume, /timer pause, /timer time <time>, /timer reset");
    }

}