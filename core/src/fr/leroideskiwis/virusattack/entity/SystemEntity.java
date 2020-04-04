package fr.leroideskiwis.virusattack.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.leroideskiwis.virusattack.utils.Interval;
import fr.leroideskiwis.virusattack.utils.Location;
import fr.leroideskiwis.virusattack.utils.TextureManager;

import java.util.List;
import java.util.stream.Collectors;

public class SystemEntity extends Entity{
    private float hp = 100f;
    private static final int WIDTH = 128;
    private static final int HEIGHT = 64;

    public SystemEntity() {
        super(WIDTH, HEIGHT, new Location(Gdx.graphics.getWidth()/2-WIDTH/2, Gdx.graphics.getHeight()/2-HEIGHT/2));
    }

    @Override
    public void draw(SpriteBatch spriteBatch, TextureManager textureManager) {
        Interval interval = new Interval((int)hp);
        String key;
        if(interval.isBetween(75, 100)){
            key = "system1";
        } else if(interval.isBetween(50, 75)){
            key = "system2";
        } else if(interval.isBetween(25, 50)){
            key = "system3";
        } else if(interval.isBetween(10, 25)){
            key = "system4";
        } else {
            key = "system5";
        }
        Texture texture = textureManager.get(key);
        spriteBatch.draw(texture, location.getX(), location.getY(), WIDTH, HEIGHT);
    }

    @Override
    public void update(List<Entity> entities) {
        hp = 100;
    }

    public void heal(float hp){
        this.hp += hp;
        if(hp > 100) this.hp = 100;
    }

    public void damage(float damage){
        this.hp -= damage;
        if(hp < 0) hp = 0;
    }

    @Override
    public void onClick(List<Entity> entities, int x, int y) {
        entities.add(new Shield(new Location(x, y)));
    }
}