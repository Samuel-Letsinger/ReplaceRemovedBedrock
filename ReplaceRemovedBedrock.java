package com.urbigmumz.world;

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
        System.out.println("ReplaceRemovedBedrock has been enabled!");
    }

    @Override
    public void onDisable() {
        System.out.println("ReplaceRemovedBedrock has been disabled :(");
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
