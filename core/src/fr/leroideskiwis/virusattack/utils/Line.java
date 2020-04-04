package fr.leroideskiwis.virusattack.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class Line {

    private Vector2 vector1;
    private Vector2 vector2;
    private int width;

    public Line(float x, float y, float x1, float y1, int width){
        this.vector1 = new Vector2(x, y);
        this.vector2 = new Vector2(x1, y1);
        this.width = width;
    }

    public Line(Location location, Location location2, int width){
        this(location.getX(), location.getY(), location2.getX(), location2.getY(), width);
    }

    public boolean intersect(Location location, int width, int height){
        return Intersector.intersectLinePolygon(vector1, vector2, new Polygon(location.getVertices(width, height)));
    }

    public void drawLine(ShapeRenderer shapeRenderer){
        shapeRenderer.rectLine(vector1.x, vector1.y, vector2.x, vector2.y, width, Color.BLACK, Color.BLACK);
    }
}
