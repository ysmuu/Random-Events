package lol.ysmu.randomEvents;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Random;

public class EventManager {

    private final JavaPlugin plugin;
    private final Random random = new Random();

    public EventManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void triggerRandomEvent() {
        List<World> worlds = Bukkit.getWorlds();
        if (worlds.isEmpty()) return;

        World world = worlds.get(0); // Use the first world (can be modified)
        List<Player> players = world.getPlayers();
        if (players.isEmpty()) return;

        // Randomly select an event
        int eventIndex = random.nextInt(20); // Add more events as needed
        switch (eventIndex) {
            case 0:
                startMeteorShower(world);
                break;
            case 1:
                startZombieSiege(world);
                break;
            case 2:
                startTreasureHunt(world);
                break;
            case 3:
                startSuperchargedNight(world);
                break;
            case 4:
                startRainbowSky(world);
                break;
            case 5:
                startSuddenDrought(world);
                break;
            case 6:
                startMeteorStrike(world);
                break;
            case 7:
                startDarkFog(world);
                break;
            case 8:
                startTimeFreeze(world);
                break;
            case 9:
                startGravityShift(world);
                break;
            case 10:
                startSolarEclipse(world);
                break;
            case 11:
                startChasmOpening(world);
                break;
            case 12:
                startThunderstormWithTornadoes(world);
                break;
            case 13:
                startEnderworldShift(world);
                break;
            case 14:
                startRainbowFireworks(world);
                break;
            case 15:
                startStormOfShards(world);
                break;
            case 16:
                startSuddenFlood(world);
                break;
            case 17:
                startSupermoon(world);
                break;
            case 18:
                startEnderworldShift(world);
                break;
            case 19:
                startRainbowFireworks(world);
                break;
            default:
                break;
        }
    }

    private void startMeteorShower(World world) {
        Bukkit.broadcastMessage("§6A meteor shower has begun!");

        for (Player player : world.getPlayers()) {
            // Spawn fireballs above the player
            for (int i = 0; i < 10; i++) { // Spawn 10 meteors per player
                world.spawn(player.getLocation().add(random.nextInt(50) - 25, 50, random.nextInt(50) - 25), org.bukkit.entity.Fireball.class);
            }
        }
    }

    private void startZombieSiege(World world) {
        Bukkit.broadcastMessage("§cA zombie siege is approaching!");

        for (Player player : world.getPlayers()) {
            // Spawn 10 zombies around each player
            for (int i = 0; i < 10; i++) {
                world.spawn(player.getLocation().add(random.nextInt(10) - 5, 0, random.nextInt(10) - 5), org.bukkit.entity.Zombie.class);
            }
        }
    }

    private void startTreasureHunt(World world) {
        Bukkit.broadcastMessage("§aA treasure chest has been hidden nearby!");

        for (Player player : world.getPlayers()) {
            // Find a random location near the player
            org.bukkit.Location chestLocation = player.getLocation().add(random.nextInt(20) - 10, 0, random.nextInt(20) - 10);

            // Ensure the chest is placed on a solid block
            chestLocation.setY(world.getHighestBlockYAt(chestLocation));

            // Place the chest
            world.getBlockAt(chestLocation).setType(org.bukkit.Material.CHEST);

            // Add loot to the chest
            org.bukkit.block.Chest chest = (org.bukkit.block.Chest) world.getBlockAt(chestLocation).getState();
            org.bukkit.inventory.Inventory chestInventory = chest.getBlockInventory();
            chestInventory.addItem(new org.bukkit.inventory.ItemStack(org.bukkit.Material.DIAMOND, 3));
            chestInventory.addItem(new org.bukkit.inventory.ItemStack(org.bukkit.Material.GOLD_INGOT, 5));
        }
    }

    private void startSuperchargedNight(World world) {
        Bukkit.broadcastMessage("§5A supercharged night has begun!");
        for (Player player : world.getPlayers()) {
            // Give players increased power or effects during the night
            player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 6000, 1));  // Increase damage for 5 minutes
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 6000, 1));  // Speed boost for 5 minutes
        }
    }

    private void startRainbowSky(World world) {
        Bukkit.broadcastMessage("§6A beautiful rainbow sky has appeared!");
        // Here, you could change the sky color (to simulate a rainbow) or just broadcast a message
        for (Player player : world.getPlayers()) {
            player.sendTitle("§6Rainbow Sky!", "Enjoy the view!", 10, 70, 20);  // Display a rainbow title to all players
        }
    }

    private void startSuddenDrought(World world) {
        Bukkit.broadcastMessage("§cA sudden drought has begun, water sources are drying up!");
        // Prevent water from flowing or evaporate water blocks
        for (World world1 : Bukkit.getWorlds()) {
            for (org.bukkit.entity.Entity entity : world1.getEntities()) {
                if (entity instanceof org.bukkit.entity.WaterMob) {
                    entity.remove(); // Remove any water mobs
                }
            }
        }
    }

    private void startMeteorStrike(World world) {
        Bukkit.broadcastMessage("§6A meteor has struck the ground!");
        // Spawn a meteor (use Fireball or similar to simulate meteor strike)
        world.spawn(world.getSpawnLocation().add(random.nextInt(50) - 25, 100, random.nextInt(50) - 25), org.bukkit.entity.Fireball.class);
    }

    private void startDarkFog(World world) {
        Bukkit.broadcastMessage("§7A dark fog has covered the land!");
        // Darken the world and add a fog effect (can use potion effects to simulate this)
        for (Player player : world.getPlayers()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 600, 1));  // Add blindness effect for 30 seconds
        }
    }

    private void startTimeFreeze(World world) {
        Bukkit.broadcastMessage("§bTime has frozen for a moment!");
        // Freeze time by stopping mobs and players from moving or doing actions
        for (Player player : world.getPlayers()) {
            player.setWalkSpeed(0);  // Stop player movement
        }
        Bukkit.getScheduler().runTaskLater(this.plugin, () -> {
            for (Player player : world.getPlayers()) {
                player.setWalkSpeed(0.2f);  // Restore player movement
            }
        }, 200L);  // Duration of the time freeze (10 seconds)
    }

    private void startGravityShift(World world) {
        Bukkit.broadcastMessage("§5Gravity has shifted!");
        // Make players float or fall rapidly by modifying gravity
        for (Player player : world.getPlayers()) {
            player.setGravity(false);  // Disable gravity for the player
        }
        Bukkit.getScheduler().runTaskLater(this.plugin, () -> {
            for (Player player : world.getPlayers()) {
                player.setGravity(true);  // Restore gravity for the player
            }
        }, 200L);  // Duration of the gravity shift (10 seconds)
    }

    private void startSolarEclipse(World world) {
        Bukkit.broadcastMessage("§6A solar eclipse is occurring!");
        // Darken the world as if it’s a solar eclipse
        world.setStorm(true);  // Simulate darkness by enabling a storm
        world.setThundering(true);  // Add thunder to the atmosphere
    }

    private void startChasmOpening(World world) {
        Bukkit.broadcastMessage("§cA chasm has opened in the world!");
        // Create a large hole or chasm in the world
        org.bukkit.Location loc = world.getSpawnLocation();
        for (int y = 0; y < 10; y++) {
            for (int x = -20; x < 20; x++) {
                for (int z = -20; z < 20; z++) {
                    world.getBlockAt(loc.clone().add(x, -y, z)).setType(org.bukkit.Material.AIR);  // Remove blocks to create a chasm
                }
            }
        }
    }

    private void startThunderstormWithTornadoes(World world) {
        Bukkit.broadcastMessage("§7A thunderstorm with tornadoes has appeared!");
        // Spawn lightning and potentially create tornado-like effects
        world.setStorm(true);  // Enable storm
        world.setThundering(true);  // Add thunder
        // You could simulate tornadoes with particle effects or mobs in a whirling motion, but creating a true tornado effect is complicated
    }

    private void startEnderworldShift(World world) {
        Bukkit.broadcastMessage("§5An Enderworld Shift has occurred!");
        // Add effects, like teleporting players to random locations, or changing the world to end-like structures
    }

    private void startRainbowFireworks(World world) {
        Bukkit.broadcastMessage("§6Rainbow Fireworks are going off!");
        // Fire fireworks with rainbow colors
        world.spawn(world.getSpawnLocation(), org.bukkit.entity.Firework.class);
    }

    private void startStormOfShards(World world) {
        Bukkit.broadcastMessage("§7A storm of shards is raining down!");
        // Simulate shards falling from the sky (use glass or sharp items)
    }

    private void startSuddenFlood(World world) {
        Bukkit.broadcastMessage("§bA sudden flood is happening!");
        // Flood the area with water, rising at a rapid pace
    }

    private void startSupermoon(World world) {
        Bukkit.broadcastMessage("§6A Supermoon is shining brightly!");
        // Change the moon's size or add special effects to simulate a supermoon
    }
}
