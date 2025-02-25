package lol.ysmu.randomEvents;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Arrow;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class RandomEvents extends JavaPlugin {

    private final Random random = new Random();  // Define the random instance

    @Override
    public void onEnable() {
        getLogger().info("RandomEvents plugin enabled!");
        // Register the command executor for the random event command
        getCommand("triggerRandomEvent").setExecutor(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("RandomEvents plugin disabled!");
    }

    // Handle the triggerRandomEvent command
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("triggerRandomEvent")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                World world = player.getWorld();  // Get the world of the player

                // Call the triggerRandomEvent method that triggers a random event
                triggerRandomEvent(world);

                // Notify the player that the event has been triggered
                player.sendMessage("§aA random event has been triggered!");

                return true;
            } else {
                sender.sendMessage("§cThis command can only be used by a player.");
                return false;
            }
        }

        return false;
    }

    // Random event trigger (this will choose an event randomly)
    public void triggerRandomEvent(World world) {
        int eventId = random.nextInt(15); // Choose a random event from 0 to 14

        switch (eventId) {
            case 0:
                startSuperchargedNight(world);
                break;
            case 1:
                startRainbowSky(world);
                break;
            case 2:
                startSuddenDrought(world);
                break;
            case 3:
                startMeteorStrike(world);
                break;
            case 4:
                startDarkFog(world);
                break;
            case 5:
                startTimeFreeze(world);
                break;
            case 6:
                startGravityShift(world);
                break;
            case 7:
                startSolarEclipse(world);
                break;
            case 8:
                startChasmOpening(world);
                break;
            case 9:
                startThunderstormWithTornadoes(world);
                break;
            case 10:
                startEnderworldShift(world);
                break;
            case 11:
                startRainbowFireworks(world);
                break;
            case 12:
                startStormOfShards(world);
                break;
            case 13:
                startSuddenFlood(world);
                break;
            case 14:
                startSupermoon(world);
                break;
        }
    }

    // Event 1: Supercharged Night
    public void startSuperchargedNight(World world) {
        Bukkit.broadcastMessage("§5A supercharged night has begun!");
        for (Player player : world.getPlayers()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 6000, 1));  // Increase damage for 5 minutes
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6000, 1));  // Speed boost for 5 minutes
        }
    }

    // Event 2: Rainbow Sky
    public void startRainbowSky(World world) {
        Bukkit.broadcastMessage("§6A beautiful rainbow sky has appeared!");
        for (Player player : world.getPlayers()) {
            player.sendTitle("§6Rainbow Sky!", "Enjoy the view!", 10, 70, 20);  // Display a rainbow title to all players
        }
    }

    // Event 3: Sudden Drought
    public void startSuddenDrought(World world) {
        Bukkit.broadcastMessage("§cA sudden drought has begun, water sources are drying up!");
        for (Player player : world.getPlayers()) {
            world.getBlockAt(player.getLocation()).setType(Material.AIR);  // Remove water-related blocks in the world
        }
    }

    // Event 4: Meteor Strike
    public void startMeteorStrike(World world) {
        Bukkit.broadcastMessage("§6A meteor has struck the ground!");
        world.spawn(world.getSpawnLocation().add(random.nextInt(50) - 25, 100, random.nextInt(50) - 25), Fireball.class);  // Spawn fireball as meteor
    }

    // Event 5: Dark Fog
    public void startDarkFog(World world) {
        Bukkit.broadcastMessage("§7A dark fog has covered the land!");
        for (Player player : world.getPlayers()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 600, 1));  // Add blindness for 30 seconds
        }
    }

    // Event 6: Time Freeze
    public void startTimeFreeze(World world) {
        Bukkit.broadcastMessage("§bTime has frozen for a moment!");
        for (Player player : world.getPlayers()) {
            player.setWalkSpeed(0);  // Stop player movement
        }
        Bukkit.getScheduler().runTaskLater(this, () -> {
            for (Player player : world.getPlayers()) {
                player.setWalkSpeed(0.2f);  // Restore player movement after 10 seconds
            }
        }, 200L);
    }

    // Event 7: Gravity Shift
    public void startGravityShift(World world) {
        Bukkit.broadcastMessage("§5Gravity has shifted!");
        for (Player player : world.getPlayers()) {
            player.setGravity(false);  // Disable gravity for the player
        }
        Bukkit.getScheduler().runTaskLater(this, () -> {
            for (Player player : world.getPlayers()) {
                player.setGravity(true);  // Restore gravity after 10 seconds
            }
        }, 200L);
    }

    // Event 8: Solar Eclipse
    public void startSolarEclipse(World world) {
        Bukkit.broadcastMessage("§6A solar eclipse is occurring!");
        world.setStorm(true);  // Enable storm
        world.setThundering(true);  // Add thunder and simulate an eclipse
    }

    // Event 9: Chasm Opening
    public void startChasmOpening(World world) {
        Bukkit.broadcastMessage("§cA chasm has opened in the world!");
        org.bukkit.Location loc = world.getSpawnLocation();
        for (int y = 0; y < 10; y++) {
            for (int x = -20; x < 20; x++) {
                for (int z = -20; z < 20; z++) {
                    world.getBlockAt(loc.clone().add(x, -y, z)).setType(Material.AIR);  // Create a chasm by removing blocks
                }
            }
        }
    }

    // Event 10: Thunderstorm with Tornadoes
    public void startThunderstormWithTornadoes(World world) {
        Bukkit.broadcastMessage("§7A thunderstorm with tornadoes has appeared!");
        world.setStorm(true);  // Enable storm
        world.setThundering(true);  // Add thunder
        // Simulate tornado-like effects here, like moving entities or creating whirlwind particles
    }

    // Event 11: Enderworld Shift
    public void startEnderworldShift(World world) {
        Bukkit.broadcastMessage("§5An Enderworld shift has begun!");
        for (Player player : world.getPlayers()) {
            player.teleport(world.getSpawnLocation());  // Teleport players to a random location in the End
        }
    }

    // Event 12: Rainbow Fireworks
    public void startRainbowFireworks(World world) {
        Bukkit.broadcastMessage("§6Rainbow fireworks are lighting up the sky!");
        // Simulate a fireworks show with random colors
        Bukkit.getScheduler().runTaskLater(this, () -> {
            for (int i = 0; i < 10; i++) {
                world.spawn(world.getSpawnLocation().add(random.nextInt(50) - 25, 100, random.nextInt(50) - 25), Fireball.class);
            }
        }, 0L);
    }

    // Event 13: Storm of Shards
    public void startStormOfShards(World world) {
        Bukkit.broadcastMessage("§7A storm of shards is raining down!");
        // Spawn sharp objects like arrows or shards falling from the sky
        for (int i = 0; i < 20; i++) {
            world.spawn(world.getSpawnLocation().add(random.nextInt(50) - 25, 100, random.nextInt(50) - 25), Arrow.class);
        }
    }

    // Event 14: Sudden Flood
    public void startSuddenFlood(World world) {
        Bukkit.broadcastMessage("§bA sudden flood is sweeping through!");
        for (int x = -50; x < 50; x++) {
            for (int z = -50; z < 50; z++) {
                world.getBlockAt(world.getSpawnLocation().add(x, 0, z)).setType(Material.WATER);  // Flood an area with water
            }
        }
    }

    // Event 15: Supermoon
    public void startSupermoon(World world) {
        Bukkit.broadcastMessage("§dA Supermoon has appeared!");
        world.setFullTime(8);  // Set full moon
        // Add custom effects such as giving players special abilities
        for (Player player : world.getPlayers()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 6000, 1));  // Power up players
        }
    }
}
