package fr.leroideskiwis.virusattack.entity.specialobject;

import fr.leroideskiwis.virusattack.entity.Entity;
import fr.leroideskiwis.virusattack.entity.SystemEntity;
import fr.leroideskiwis.virusattack.utils.Location;

import java.util.List;

public class HealSpecial extends SpecialObject {

    public HealSpecial(Location location) {
        super(location);
    }

    @Override
    public void onCollideSystem(List<Entity> entities, SystemEntity entity) {
        entity.heal(2f);
        super.onCollideSystem(entities, entity);
    }
}
