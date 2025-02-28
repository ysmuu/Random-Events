package lol.ysmu.randomEvents;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TriggerRandomEventCommand implements CommandExecutor {

    private final RandomEvents plugin;

    public TriggerRandomEventCommand(RandomEvents plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("randomevents.use")) {
            player.sendMessage("§cYou do not have permission to use this command.");
            return true;
        }

        // Access the EventManager instance and call triggerRandomEvent()
        plugin.eventManager.triggerRandomEvent();
        plugin.updateLastTriggerTime(); // Update the last trigger time.
        player.sendMessage("§aA random event has been triggered!");
        return true;
    }
}