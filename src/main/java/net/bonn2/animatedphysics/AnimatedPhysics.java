package net.bonn2.animatedphysics;

import net.bonn2.animatedphysics.bstats.Metrics;
import net.bonn2.animatedphysics.listeners.shulker.AnimatedBlockListener;
import net.bonn2.animatedphysics.listeners.AnimationListener;
import net.bonn2.animatedphysics.listeners.shulker.EntityDamageListener;
import net.bonn2.animatedphysics.util.ModrinthUpdateChecker;
import nl.pim16aap2.animatedarchitecture.spigot.core.AnimatedArchitecturePlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AnimatedPhysics extends JavaPlugin {

    public static AnimatedPhysics plugin;

    private AnimatedArchitecturePlugin animatedArchitecturePlugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        // Register Bukkit events
        getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);

        // Get animated architecture instance
        animatedArchitecturePlugin =
                (AnimatedArchitecturePlugin) Bukkit.getPluginManager().getPlugin("AnimatedArchitecture");
        if (animatedArchitecturePlugin == null) {
            getLogger().warning("Failed to get AnimatedArchitecture instance!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        AnimatedBlockListener.registerFactory(
                getAnimatedArchitecturePlugin().getAnimatedArchitectureSpigotPlatform(this).get()
        );

        AnimationListener.registerFactory(
                getAnimatedArchitecturePlugin().getAnimatedArchitectureSpigotPlatform(this).get()
        );

        // Start metrics
        new Metrics(this, 18565);

        // Check for updates
        ModrinthUpdateChecker.check(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        while (AnimatedBlockListener.instances.size() > 0)
            AnimatedBlockListener.instances.get(0).removeNow();
    }

    public AnimatedArchitecturePlugin getAnimatedArchitecturePlugin() {
        return animatedArchitecturePlugin;
    }
}
