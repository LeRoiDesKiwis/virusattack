package fr.leroideskiwis.virusattack.entity;

import fr.leroideskiwis.virusattack.utils.Location;

import java.util.List;

public class Good extends EntityMoveable {
    public Good(Location location) {
        super(location);
    }

    @Override
    public void onCollideSystem(List<Entity> entities, SystemEntity entity) {
        entity.heal(0.5f);
    }
}
