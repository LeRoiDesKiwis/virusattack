package fr.leroideskiwis.virusattack.entity;

import java.util.List;

public interface Moveable {
    int getSpeed();
    void move(List<Entity> entities);
}