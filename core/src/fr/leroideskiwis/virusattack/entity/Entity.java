package fr.leroideskiwis.virusattack.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.leroideskiwis.virusattack.utils.Interval;
import fr.leroideskiwis.virusattack.utils.Location;
import fr.leroideskiwis.virusattack.utils.TextureManager;

import java.util.List;
import java.util.Optional;

public abstract class Entity {
    protected Location location;
    public final int width;
    public final int height;

    public Entity(int width, int height, Location location){
        this.width = width;
        this.height = height;
        this.location = location;
    }

    public Entity(Location location){
        this(16, 16, location);
    }

    public void draw(SpriteBatch spriteBatch, TextureManager textureManager) {

    }

    protected void draw(SpriteBatch spriteBatch, TextureManager textureManager, String key){
        spriteBatch.draw(textureManager.get(key), location.getX(), location.getY(), width, height);
    }

    public void update(List<Entity> entities) {
        Optional<Entity> entityOpt = entities.stream().filter(entity -> !entity.equals(this) && entity.location.isIn(location, entity.width, entity.height)).findAny();
        entityOpt.ifPresent(entity -> onCollide(entities, entity));
    }

    public void onCollide(List<Entity> entities, Entity entity){
        if(entity instanceof SystemEntity) onCollideSystem(entities, (SystemEntity)entity);
    }

    public void onCollideSystem(List<Entity> entities, SystemEntity entity){

    }

    public void onClick(List<Entity> entities, int x, int y){

    }
}
