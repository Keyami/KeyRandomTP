package io.github.Keyami.KeyRandomTP;

import org.bukkit.plugin.java.JavaPlugin;

public class KeyRandomTP extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("RandomTP by Keyami has been enabled!");
    }
    @Override
    public void onDisable() {
        getLogger().info("RandomTP by Keyami has been disabled!");
    }
}
