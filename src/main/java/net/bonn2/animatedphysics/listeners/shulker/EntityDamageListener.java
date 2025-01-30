package net.bonn2.animatedphysics.listeners.shulker;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void protectEntities(@NotNull EntityDamageEvent event) {
        if (event.getEntity().getScoreboardTags().contains("AnimatedPhysicsAS") || event.getEntity().getScoreboardTags().contains("AnimatedPhysicsS"))
            event.setCancelled(true);
    }
}
