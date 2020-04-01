package io.github.Keyami.KeyRandomTP;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class KeyRandomTP extends JavaPlugin implements Listener{
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        config.addDefault("MaxX", 10000);
        config.addDefault("MaxZ", 10000);
        config.addDefault("MinX", -10000);
        config.addDefault("MinZ", -10000);
        config.addDefault("worlds", "world");
        config.options().copyDefaults(true);
        saveConfig();

        this.getCommand("rtp").setExecutor(new CommandRTP());
        getLogger().info("RandomTP by Keyami has been enabled!");
        getServer().getPluginManager().registerEvents((Listener) this, this);
    }
    @Override
    public void onDisable() {
        getLogger().info("RandomTP by Keyami has been disabled!");
    }


}
