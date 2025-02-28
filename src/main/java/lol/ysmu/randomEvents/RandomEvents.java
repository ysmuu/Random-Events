package lol.ysmu.randomEvents;

import lol.ysmu.randomEvents.ReloadConfigCommand;
import lol.ysmu.randomEvents.TriggerRandomEventCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class RandomEvents extends JavaPlugin {

    private EventManager eventManager;
    private long lastTriggerTime = 0;

    @Override
    public void onEnable() {
        // Save and load configuration
        saveDefaultConfig();
        reloadConfig();

        // Initialize EventManager
        eventManager = new EventManager(this);

        // Register event listeners
        getServer().getPluginManager().registerEvents(eventManager, this);

        // Register commands
        getCommand("triggerrandomevent").setExecutor(new TriggerRandomEventCommand(this));
        getCommand("randomevent").setExecutor(new ReloadConfigCommand(this));

        // Schedule automatic random events
        int eventInterval = getConfig().getInt("settings.event-interval", 300);
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            if (System.currentTimeMillis() - lastTriggerTime >= eventInterval * 1000L) {
                eventManager.triggerRandomEvent();
                lastTriggerTime = System.currentTimeMillis();
            }
        }, 0, eventInterval * 20L);

        getLogger().info("Random Events Plugin has been enabled successfully!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Random Events Plugin has been disabled.");
    }

    public boolean canTriggerEvent(long cooldown, boolean hasBypass) {
        if (hasBypass) return true;
        return System.currentTimeMillis() - lastTriggerTime >= cooldown * 1000L;
    }

    public void updateLastTriggerTime() {
        lastTriggerTime = System.currentTimeMillis();
    }
}
