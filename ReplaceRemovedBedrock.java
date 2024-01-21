package com.urbigmumz.replaceremovedbedrock;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ReplaceRemovedBedrock extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("ReplaceBedrockFloorAndRoof has been enabled!");
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        World world = event.getBlock().getWorld();
        if (world.getEnvironment() != Environment.END) {
            fillBedrockAtYZero(world);
        }
    }

    public static void fillBedrockAtYZero(World world) {
        Environment environment = world.getEnvironment();
        if (environment == Environment.NORMAL || environment == Environment.NETHER) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    Block block = world.getBlockAt(x, 0, z);
                    if (environment == Environment.NETHER) {
                        Block block2 = world.getBlockAt(x, 127, z);
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
