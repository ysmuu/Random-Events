package lol.ysmu.randomEvents;

import lol.ysmu.randomEvents.RandomEvents;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadConfigCommand implements CommandExecutor {
    private final RandomEvents plugin;

    public ReloadConfigCommand(RandomEvents plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("randomevents.reload")) {
            player.sendMessage("§cYou do not have permission to reload the config.");
            return true;
        }

        plugin.reloadConfig();
        player.sendMessage("§aConfiguration reloaded successfully!");

        return true;
    }
}
