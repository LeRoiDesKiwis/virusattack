package fr.leroideskiwis.virusattack.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import fr.leroideskiwis.virusattack.entity.virus.Virus;
import fr.leroideskiwis.virusattack.utils.Line;
import fr.leroideskiwis.virusattack.utils.Location;
import fr.leroideskiwis.virusattack.utils.TextureManager;

import java.awt.geom.Line2D;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Shield extends Entity {

    private final long spawnedTime;
    private Line line;

    public Shield(Location location) {
        super(4, 4, location);
        this.spawnedTime = System.currentTimeMillis();
        this.line =  new Line(0, 0, 0, 0, width);
    }

    @Override
    public void onCollide(List<Entity> entities, Entity entity) {
        if(entity instanceof SystemEntity) entities.remove(this);
        else entities.remove(entity);
    }

    @Override
    public void update(List<Entity> entities) {
        Optional<Entity> entityOpt = entities.stream().filter(entity -> !entity.equals(this) && line.intersect(entity.location, width, height)).findAny();
        entityOpt.ifPresent(entity -> onCollide(entities, entity));
    }

    @Override
    public void onCollideSystem(List<Entity> entities, SystemEntity entity) {
        entities.remove(this);
    }

    @Override
    public void draw(SpriteBatch spriteBatch, TextureManager textureManager) {
        //super.draw(spriteBatch, textureManager, "shield");
    }

    public void drawLine(ShapeRenderer shapeRenderer, Shield next){
        this.line = new Line(location, next.location, width);
        line.drawLine(shapeRenderer);
    }

    public static class ShieldComparator implements Comparator<Shield>{

        @Override
        public int compare(Shield shield, Shield shield1) {
            return (int)(shield.spawnedTime-shield1.spawnedTime);
        }
    }
}
