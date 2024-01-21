package com.urbigmumz.world;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class ReplaceRemovedBedrock extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        System.out.println("[ReplaceRemovedBedrock] has been enabled!");
    }

    @Override
    public void onDisable() {
        System.out.println("[ReplaceRemovedBedrock] has been disabled :(");
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        World world = event.getBlock().getWorld();
        if (world.getEnvironment() != Environment.THE_END) {
            fillBedrockAtYZero(world);
        }
    }

    public static void fillBedrockAtYZero(World world) {
        Environment environment = world.getEnvironment();
        World overworld = Bukkit.getServer().getWorld("world");
        World nether = Bukkit.getServer().getWorld("world_nether");
        if (environment == Environment.NORMAL || environment == Environment.NETHER) {
            for (Chunk chunk : world.getLoadedChunks()) {
                int chunkX = chunk.getX() << 4;
                int chunkZ = chunk.getZ() << 4;
                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        Location blockLocation;
                        Block block;
                        if (environment == Environment.NETHER) {
                            blockLocation = new Location(nether, chunkX + x, 0, chunkZ + z);
                            block = blockLocation.getBlock();
                        } else {
                            blockLocation = new Location(overworld, chunkX + x, 0, chunkZ + z);
                            block = blockLocation.getBlock();
                        }
                        if (environment == Environment.NETHER) {
                            Location blockLocation2 = new Location(nether, chunkX + x, 217, chunkZ + z);
                            Block block2 = blockLocation2.getBlock();
                            if (block2.getType() != Material.BEDROCK) {
                                block2.setType(Material.BEDROCK);
                            }
                        }
                        if (block.getType() != Material.BEDROCK) {
                            block.setType(Material.BEDROCK);
                        }
                    }
                }
            }
        }
    }
}
