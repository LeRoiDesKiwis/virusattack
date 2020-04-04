package fr.leroideskiwis.virusattack.entity;

import com.badlogic.gdx.Gdx;
import fr.leroideskiwis.virusattack.utils.Location;
import fr.leroideskiwis.virusattack.utils.Utils;

import java.util.List;

public abstract class EntityMoveable extends Entity implements Moveable{

    private int speed = 2;

    public EntityMoveable(Location location){
        super(8, 8, location);
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    public void spawn(List<Entity> entities){
        int x = 0;
        int y = 0;
        int width = Gdx.graphics.getWidth()-this.width;
        int height = Gdx.graphics.getHeight()-this.height;

        int choice = Utils.random(4);

        switch(choice){
            case 2:
                y = height;
            case 0:
                x = Utils.random(width);
                break;
            case 3:
                x = width;
            case 1:
                y = Utils.random(height);
                break;
            default:
                break;
        }
        location.setX(x);
        location.setY(y);
        entities.add(this);
    }

    @Override
    public void update(List<Entity> entities) {
        super.update(entities);
        move(entities);
    }

    @Override
    public void onCollideSystem(List<Entity> entities, SystemEntity entity) {
        entities.remove(this);
    }

    @Override
    public void move(List<Entity> entities) {
        float goalX = Gdx.graphics.getWidth()/2f;
        float goalY = Gdx.graphics.getHeight()/2f;
        float destX = goalX - location.getX();
        float destY = goalY - location.getY();

        double dist = Math.sqrt(destX * destX + destY * destY);
        destX = (float) (destX / dist);
        destY = (float) (destY / dist);

        float travelX = destX * this.speed;
        float travelY = destY * this.speed;

        double distTravel = Math.sqrt(travelX * travelX + travelY * travelY);

        if ( distTravel <= dist ) {
            location.addX(travelX);
            location.addY(travelY);
        }
    }
}
