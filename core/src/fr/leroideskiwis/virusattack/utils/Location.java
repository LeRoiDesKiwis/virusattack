package fr.leroideskiwis.virusattack.utils;

import com.badlogic.gdx.math.Vector2;

import java.util.Objects;

public class Location {

    private float x;
    private float y;

    public Location(int x, int y){
        this.x = x;
        this.y = y;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean isIn(Location location, int width, int height){
        Interval intervalX = new Interval((int)location.getX());
        Interval intervalY = new Interval((int)location.getY());

        return intervalX.isBetween((int)x, (int)x+width) && intervalY.isBetween((int)y, (int)y+height);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Float.compare(location.x, x) == 0 &&
                Float.compare(location.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public void addX(float travelX) {
        x += travelX;
    }

    public void addY(float travelY){
        y += travelY;
    }

    public Vector2 toVector(){
        return new Vector2(x, y);
    }

    public float[] getVertices(int width, int height){
        return new float[]{x, y, x, y+height, x+width, y, x+width, y+height};
    }
}
