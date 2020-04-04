package fr.leroideskiwis.virusattack.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class TextureManager {

    private Map<String, Texture> textures = new HashMap<>();

    public Texture get(String key){
        if(!textures.containsKey(key)) load(key);
        return textures.get(key);
    }

    private void load(String key) {
        Texture texture = new Texture(Gdx.files.internal(key+".png"));
        textures.put(key, texture);
    }

}
