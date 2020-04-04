package fr.leroideskiwis.virusattack.entity.virus;

import fr.leroideskiwis.virusattack.entity.Entity;
import fr.leroideskiwis.virusattack.entity.SystemEntity;
import fr.leroideskiwis.virusattack.utils.Location;

import java.util.List;

public class VirusDamage extends Virus {

    public VirusDamage(Location location) {
        super(location);
    }

    @Override
    public void onCollideSystem(List<Entity> entities, SystemEntity entity) {
        entity.damage(1);
        super.onCollideSystem(entities, entity);
    }
}
