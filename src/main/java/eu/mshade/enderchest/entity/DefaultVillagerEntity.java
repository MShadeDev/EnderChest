package eu.mshade.enderchest.entity;

import eu.mshade.enderframe.entity.Villager;
import eu.mshade.enderframe.entity.VillagerType;
import eu.mshade.enderframe.world.Location;
import eu.mshade.enderframe.world.Vector;

import java.util.UUID;

public class DefaultVillagerEntity extends Villager {

    public DefaultVillagerEntity(Location location, Vector velocity, int entityId, boolean isFire, boolean isSneaking, boolean isSprinting, boolean isEating, boolean isInvisible, short airTicks, String customName, boolean isCustomNameVisible, boolean isSilent, UUID uuid, float health, int potionEffectColor, boolean isPotionEffectAmbient, byte numberOfArrowInEntity, boolean isAIDisable, VillagerType villagerType, int age, boolean isAgeLocked) {
        super(location, velocity, entityId, isFire, isSneaking, isSprinting, isEating, isInvisible, airTicks, customName, isCustomNameVisible, isSilent, uuid, health, potionEffectColor, isPotionEffectAmbient, numberOfArrowInEntity, isAIDisable, villagerType, age, isAgeLocked);
    }

    public DefaultVillagerEntity(Location location, int entityId, float health, VillagerType villagerType) {
        super(location, entityId, health, villagerType);
    }

    public DefaultVillagerEntity(Location location, int entityId) {
        super(location, entityId);
    }

    @Override
    public void tick() {

    }
}
