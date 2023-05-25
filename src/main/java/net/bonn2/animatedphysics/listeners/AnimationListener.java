package net.bonn2.animatedphysics.listeners;

import nl.pim16aap2.animatedarchitecture.core.animation.Animation;
import nl.pim16aap2.animatedarchitecture.core.api.IAnimatedArchitecturePlatform;
import nl.pim16aap2.animatedarchitecture.core.api.animatedblock.IAnimatedBlock;
import nl.pim16aap2.animatedarchitecture.core.api.animatedblock.IAnimationHook;
import org.jetbrains.annotations.NotNull;

public class AnimationListener implements IAnimationHook {

    public static void registerFactory(@NotNull IAnimatedArchitecturePlatform animatedArchitecturePlatform) {
        animatedArchitecturePlatform.getAnimationHookManager().registerFactory(AnimationListener::new);
    }

    private final Animation<@NotNull IAnimatedBlock> animation;

    private AnimationListener(Animation<@NotNull IAnimatedBlock> animation) {
        this.animation = animation;
    }

    @Override
    public @NotNull String getName() {
        return "AnimatedPhysicsAnimationHook";
    }

    @Override
    public void onPostAnimationStep() {
        // Todo: move interacting entities
    }
}
