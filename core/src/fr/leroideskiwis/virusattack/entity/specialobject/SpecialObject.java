package fr.leroideskiwis.virusattack.entity.specialobject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.leroideskiwis.virusattack.entity.Entity;
import fr.leroideskiwis.virusattack.entity.EntityMoveable;
import fr.leroideskiwis.virusattack.utils.Location;
import fr.leroideskiwis.virusattack.utils.TextureManager;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class SpecialObject extends EntityMoveable {

    public SpecialObject(Location location) {
        super(location);
    }

    public float chance(List<Entity> entities){
        return 1f;
    }

    @Override
    public void draw(SpriteBatch spriteBatch, TextureManager textureManager) {
        super.draw(spriteBatch, textureManager, "object");
    }

}
