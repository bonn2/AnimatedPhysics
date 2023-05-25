package net.bonn2.animatedphysics.listeners;

import net.bonn2.animatedphysics.AnimatedPhysics;
import net.bonn2.animatedphysics.colliders.CollisionShulker;
import nl.pim16aap2.animatedarchitecture.core.animation.RotatedPosition;
import nl.pim16aap2.animatedarchitecture.core.api.IAnimatedArchitecturePlatform;
import nl.pim16aap2.animatedarchitecture.core.api.animatedblock.IAnimatedBlock;
import nl.pim16aap2.animatedarchitecture.core.api.animatedblock.IAnimatedBlockHook;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;


public class AnimatedBlockListener implements IAnimatedBlockHook
{
    public static void registerMyAnimationHookFactory(IAnimatedArchitecturePlatform animatedArchitecturePlatform) {
        animatedArchitecturePlatform.getAnimatedBlockHookManager().registerFactory(AnimatedBlockListener::new);
    }

    private final IAnimatedBlock animatedBlock;
    private CollisionShulker shulker;
    private int count = 0;

    private AnimatedBlockListener(IAnimatedBlock animatedBlock)
    {
        this.animatedBlock = animatedBlock;
    }

    @Override
    public @NotNull String getName() {
        return "AnimatedPhysicsBlockHook";
    }

    @Override
    public void preSpawn() {
        // Temp: Fix preDeleteOriginalBlock being called multiple times
        if (count++ > 0) return;
        shulker = new CollisionShulker(animatedBlock.getStartPosition(), animatedBlock.getWorld());
    }

    @Override
    public void preMove(@NotNull RotatedPosition newPosition) {
        Bukkit.getScheduler().runTask(AnimatedPhysics.plugin, () -> shulker.move(newPosition));
    }

    @Override
    public void postKill() {
        Bukkit.getScheduler().runTask(AnimatedPhysics.plugin, () -> shulker.remove());
    }
}
