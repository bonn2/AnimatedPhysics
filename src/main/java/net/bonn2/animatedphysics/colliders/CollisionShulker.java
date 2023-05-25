package net.bonn2.animatedphysics.colliders;

import io.papermc.paper.entity.TeleportFlag;
import net.kyori.adventure.text.Component;
import nl.pim16aap2.animatedarchitecture.core.animation.RotatedPosition;
import nl.pim16aap2.animatedarchitecture.core.api.IWorld;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Shulker;

public class CollisionShulker {

    public static final double SHULKER_OFFSET = 0.74;

    private final ArmorStand armorStand;
    private final Shulker shulker;

    public CollisionShulker(RotatedPosition rotatedPosition, IWorld iWorld) {
        World world = Bukkit.getWorld(iWorld.worldName());
        armorStand = world.spawn(
                new Location(world, rotatedPosition.position().x() + 0.5, rotatedPosition.position().y() - SHULKER_OFFSET, rotatedPosition.position().z() + 0.5),
                ArmorStand.class,
                entity -> {
                    entity.setPersistent(true);
                    entity.customName(Component.text("AnimatedPhysicsAS"));
                    entity.setCustomNameVisible(false);
                    entity.setGravity(false);
                    entity.setBasePlate(false);
                    entity.setInvisible(true);
                    entity.setInvulnerable(true);
                    entity.setAI(false);
                    entity.setSilent(true);
                    entity.setCanPickupItems(false);
                    entity.setSmall(true);
        });
        shulker = world.spawn(
                new Location(world, rotatedPosition.position().x() + 0.5, rotatedPosition.position().y(), rotatedPosition.position().z() + 0.5),
                Shulker.class,
                entity -> {
                    entity.setPersistent(true);
                    entity.customName(Component.text("AnimatedPhysicsS"));
                    entity.setPeek(0);
                    entity.setAware(false);
                    entity.setCustomNameVisible(false);
                    entity.setGravity(false);
                    entity.setInvisible(true);
                    entity.setInvulnerable(true);
                    entity.setAI(false);
                    entity.setSilent(true);
                    entity.setCanPickupItems(false);
                    armorStand.addPassenger(entity);
        });
    }

    public void move(RotatedPosition rotatedPosition) {
        armorStand.teleport(
                new Location(armorStand.getWorld(), rotatedPosition.position().x() + 0.5, rotatedPosition.position().y() - SHULKER_OFFSET, rotatedPosition.position().z() + 0.5),
                TeleportFlag.EntityState.RETAIN_PASSENGERS
        );
    }

    public void remove() {
        shulker.remove();
        armorStand.remove();
    }
}
