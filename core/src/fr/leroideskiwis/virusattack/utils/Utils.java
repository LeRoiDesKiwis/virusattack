package fr.leroideskiwis.virusattack.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;

public class Utils {

    private static Random random = new Random();

    public static <T> T chooseRandom(List<T> list){
        int index = (int)(Math.random()*list.size());
        return list.get(index);
    }

    public static <T> T newObject(Class<T> specialObject, Location location) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return (T)specialObject.getConstructors()[0].newInstance(location);
    }

    public static int random(int max){
        return random.nextInt(max);
    }

}
