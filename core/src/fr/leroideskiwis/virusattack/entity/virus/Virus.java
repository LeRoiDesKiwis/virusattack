package fr.leroideskiwis.virusattack.entity.virus;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.leroideskiwis.virusattack.entity.Entity;
import fr.leroideskiwis.virusattack.entity.EntityMoveable;
import fr.leroideskiwis.virusattack.entity.Moveable;
import fr.leroideskiwis.virusattack.entity.SystemEntity;
import fr.leroideskiwis.virusattack.utils.Location;
import fr.leroideskiwis.virusattack.utils.TextureManager;

import java.util.List;

public abstract class Virus extends EntityMoveable {

    public Virus(Location location) {
        super(location);
    }

    public boolean canSpawn(List<Entity> entities){
        return true;
    }

    public float chance(List<Entity> entities){
        return 1f;
    }

    @Override
    public void draw(SpriteBatch spriteBatch, TextureManager textureManager) {
        super.draw(spriteBatch, textureManager, "virus");
    }
}
