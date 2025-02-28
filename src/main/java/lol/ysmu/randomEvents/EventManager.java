package lol.ysmu.randomEvents;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Random;

public class EventManager implements Listener {

    private final Random random = new Random();
    private final RandomEvents plugin;

    public EventManager(RandomEvents plugin) {
        this.plugin = plugin;
    }

    public void triggerRandomEvent() {
        List<World> worlds = Bukkit.getWorlds();
        if (worlds.isEmpty()) return;

        World world = worlds.get(0);
        List<Player> players = world.getPlayers();
        if (players.isEmpty()) return;

        int eventIndex = random.nextInt(20);
        String eventName = getEventName(eventIndex);

        if (!plugin.getConfig().getBoolean("events." + eventName, true)) {
            return;
        }

        plugin.updateLastTriggerTime();

        switch (eventIndex) {
            case 0 -> startMeteorShower(world);
            case 1 -> startZombieSiege(world);
            case 2 -> startTreasureHunt(world);
            case 3 -> startSuperchargedNight(world);
            case 4 -> startRainbowSky(world);
            case 5 -> startSuddenDrought(world);
            case 6 -> startMeteorStrike(world);
            case 7 -> startDarkFog(world);
            case 8 -> startTimeFreeze(world);
            case 9 -> startGravityShift(world);
            case 10 -> startSolarEclipse(world);
            case 11 -> startChasmOpening(world);
            case 12 -> startThunderstormWithTornadoes(world);
            case 13, 18 -> startEnderworldShift(world);
            case 14, 19 -> startRainbowFireworks(world);
            case 15 -> startStormOfShards(world);
            case 16 -> startSuddenFlood(world);
            case 17 -> startSupermoon(world);
        }
    }

    private String getEventName(int eventIndex) {
        return switch (eventIndex) {
            case 0 -> "meteor_shower";
            case 1 -> "zombie_siege";
            case 2 -> "treasure_hunt";
            case 3 -> "supercharged_night";
            case 4 -> "rainbow_sky";
            case 5 -> "sudden_drought";
            case 6 -> "meteor_strike";
            case 7 -> "dark_fog";
            case 8 -> "time_freeze";
            case 9 -> "gravity_shift";
            case 10 -> "solar_eclipse";
            case 11 -> "chasm_opening";
            case 12 -> "thunderstorm_tornadoes";
            case 13, 18 -> "enderworld_shift";
            case 14, 19 -> "rainbow_fireworks";
            case 15 -> "storm_of_shards";
            case 16 -> "sudden_flood";
            case 17 -> "supermoon";
            default -> "unknown_event";
        };
    }

    private void startMeteorShower(World world) {
        Bukkit.broadcastMessage("§cMeteor shower incoming!");
        for (int i = 0; i < 5; i++) {
            Location loc = world.getSpawnLocation().add(random.nextInt(50) - 25, 30, random.nextInt(50) - 25);
            world.spawnEntity(loc, EntityType.FIREBALL);
        }
    }

    private void startZombieSiege(World world) {
        Bukkit.broadcastMessage("§4Zombies are invading!");
        for (int i = 0; i < 20; i++) {
            Location loc = world.getSpawnLocation().add(random.nextInt(50) - 25, 0, random.nextInt(50) - 25);
            world.spawnEntity(loc, EntityType.ZOMBIE);
        }
    }

    private void startTreasureHunt(World world) {
        Bukkit.broadcastMessage("§6A treasure chest has appeared!");
        Location loc = world.getSpawnLocation().add(random.nextInt(50) - 25, 0, random.nextInt(50) - 25);
        world.getBlockAt(loc).setType(Material.CHEST);
        Chest chest = (Chest) world.getBlockAt(loc).getState();
        Inventory inv = chest.getBlockInventory();
        inv.addItem(new ItemStack(Material.DIAMOND, 5));
    }

    private void startSuperchargedNight(World world) {
        Bukkit.broadcastMessage("§5Night is more dangerous!");
        world.setTime(18000);
        for (Player player : world.getPlayers()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 6000, 1));
        }
    }

    private void startRainbowSky(World world) {
        Bukkit.broadcastMessage("§bThe sky is glowing with colors!");
        world.setStorm(false);
    }

    private void startSuddenDrought(World world) {
        Bukkit.broadcastMessage("§eThe rivers have dried up!");
        world.setWeatherDuration(0);
    }

    private void startMeteorStrike(World world) {
        Bukkit.broadcastMessage("§4A meteor is crashing!");
        Location loc = world.getSpawnLocation().add(0, 20, 0);
        world.spawnEntity(loc, EntityType.FIREBALL);
    }

    private void startDarkFog(World world) {
        Bukkit.broadcastMessage("§8A thick fog covers the land...");
        world.setTime(18000);
    }

    private void startTimeFreeze(World world) {
        Bukkit.broadcastMessage("§3Time has frozen!");
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
    }

    private void startGravityShift(World world) {
        Bukkit.broadcastMessage("§fGravity feels weird...");
        for (Player player : world.getPlayers()) {
            player.setVelocity(new Vector(0, 2, 0));
        }
    }

    private void startSolarEclipse(World world) {
        Bukkit.broadcastMessage("§9A solar eclipse is happening!");
        world.setTime(18000);
    }

    private void startChasmOpening(World world) {
        Bukkit.broadcastMessage("§4The ground is shaking!");
    }

    private void startThunderstormWithTornadoes(World world) {
        Bukkit.broadcastMessage("§eThunderstorm with tornadoes incoming!");
        world.setStorm(true);
    }

    private void startEnderworldShift(World world) {
        Bukkit.broadcastMessage("§5The world is shifting into an Ender dimension...");
        world.setTime(18000);
    }

    private void startRainbowFireworks(World world) {
        Bukkit.broadcastMessage("§eRainbow fireworks are lighting up the sky!");
        for (int i = 0; i < 10; i++) {
            Location loc = world.getSpawnLocation().add(random.nextInt(50) - 25, 10, random.nextInt(50) - 25);
            Firework firework = (Firework) world.spawnEntity(loc, EntityType.FIREWORK_ROCKET);
            FireworkMeta meta = firework.getFireworkMeta();
            FireworkEffect effect = FireworkEffect.builder()
                    .withColor(Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PURPLE)
                    .with(FireworkEffect.Type.BALL_LARGE)
                    .trail(true)
                    .build();
            meta.addEffect(effect);
            firework.setFireworkMeta(meta);
        }
    }

    private void startStormOfShards(World world) {
        Bukkit.broadcastMessage("§cA storm of shards is falling from the sky!");
        for (int i = 0; i < 20; i++) {
            Location loc = world.getSpawnLocation().add(random.nextInt(50) - 25, 30, random.nextInt(50) - 25);
            FallingBlock shard = world.spawnFallingBlock(loc, Material.GLASS.createBlockData());
            shard.setDropItem(false); // Prevent shards from dropping items
            shard.setVelocity(new Vector(0, -1, 0)); // Make shards fall straight down
        }
    }

    private void startSuddenFlood(World world) {
        Bukkit.broadcastMessage("§bA sudden flood is occurring!");
        for (int i = 0; i < 10; i++) {
            Location loc = world.getSpawnLocation().add(random.nextInt(50) - 25, 0, random.nextInt(50) - 25);
            loc.getBlock().setType(Material.WATER);
        }
    }

    private void startSupermoon(World world) {
        Bukkit.broadcastMessage("§dA supermoon is rising!");
        world.setFullTime(18000); // Set time to night
        world.setFullTime(0); // Set moon phase to full moon
        for (Player player : world.getPlayers()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 6000, 0)); // Give night vision
        }
    }
}