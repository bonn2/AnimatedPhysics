package net.bonn2.animatedphysics;

import net.bonn2.animatedphysics.listeners.StructureListener;
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

        // Get animated architecture instance
        animatedArchitecturePlugin =
                (AnimatedArchitecturePlugin) Bukkit.getPluginManager().getPlugin("AnimatedArchitecture");
        if (animatedArchitecturePlugin == null) {
            getLogger().warning("Failed to get AnimatedArchitecture instance!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        StructureListener.registerMyAnimationHookFactory(
                getAnimatedArchitecturePlugin().getAnimatedArchitectureSpigotPlatform(this).get()
        );
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public AnimatedArchitecturePlugin getAnimatedArchitecturePlugin() {
        return animatedArchitecturePlugin;
    }
}
