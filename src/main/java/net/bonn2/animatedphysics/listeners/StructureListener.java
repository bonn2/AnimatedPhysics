package net.bonn2.animatedphysics.listeners;

import nl.pim16aap2.animatedarchitecture.core.animation.Animation;
import nl.pim16aap2.animatedarchitecture.core.api.animatedblock.IAnimatedBlock;
import nl.pim16aap2.animatedarchitecture.core.api.animatedblock.IAnimationHook;
import nl.pim16aap2.animatedarchitecture.spigot.core.AnimatedArchitectureSpigotPlatform;
import org.jetbrains.annotations.NotNull;


public class StructureListener implements IAnimationHook
{
    public static void registerMyAnimationHookFactory(AnimatedArchitectureSpigotPlatform animatedArchitecturePlatform) {
        animatedArchitecturePlatform.getAnimationHookManager().registerFactory(StructureListener::new);
    }

    private final Animation<@NotNull IAnimatedBlock> animation;

    private StructureListener(Animation<@NotNull IAnimatedBlock> animation) {
        this.animation = animation;
    }

    @Override
    public @NotNull String getName() {
        return "AnimatedPhysicsHook";
    }

    @Override
    public void onPrepare() {
        animation.getAnimatedBlocks().forEach(iAnimatedBlock -> System.out.println(iAnimatedBlock.getLocation()));
    }

    @Override
    public void onPostAnimationStep() {
        // Some code that is executed after each step of the animation.
    }
}
