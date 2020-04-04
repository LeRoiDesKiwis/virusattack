package fr.leroideskiwis.virusattack;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.leroideskiwis.virusattack.entity.Entity;
import fr.leroideskiwis.virusattack.entity.Shield;
import fr.leroideskiwis.virusattack.entity.SystemEntity;
import fr.leroideskiwis.virusattack.entity.specialobject.HealSpecial;
import fr.leroideskiwis.virusattack.entity.specialobject.SpecialObject;
import fr.leroideskiwis.virusattack.entity.virus.Virus;
import fr.leroideskiwis.virusattack.entity.virus.VirusDamage;
import fr.leroideskiwis.virusattack.utils.Location;
import fr.leroideskiwis.virusattack.utils.TextureManager;
import fr.leroideskiwis.virusattack.entity.Shield.ShieldComparator;
import fr.leroideskiwis.virusattack.utils.Utils;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main extends ApplicationAdapter {

	private ShapeRenderer shapeRenderer;
	private SpriteBatch spriteBatch;
	private List<Entity> entities = new ArrayList<>();
	private TextureManager textureManager;
	private float delta = 0;
	private List<Class<? extends Virus>> virus = new ArrayList<>();
	private List<Class<? extends SpecialObject>> specialobjects = new ArrayList<>();
	private final int TICKS_PER_SECOND = 60;

	@Override
	public void create () {
		this.spriteBatch = new SpriteBatch();
		this.textureManager = new TextureManager();
		this.shapeRenderer = new ShapeRenderer();
		entities.add(new SystemEntity());

		virus.add(VirusDamage.class);

		specialobjects.add(HealSpecial.class);
	}

	@Override
	public void render () {
		try {
			Gdx.gl.glClearColor( 0, 0, 0.8f, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
			spriteBatch.begin();
			shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
			delta += Gdx.graphics.getDeltaTime();
			if(delta > (1f/TICKS_PER_SECOND)){
				checkInputs();

				update();

				delta = 0;
			}
			draw();
			spriteBatch.end();
			shapeRenderer.end();
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
		}

	}

	private void draw() {
		getEntities().forEach(entity -> entity.draw(spriteBatch, textureManager));
		List<Shield> shields = entities.stream().filter(entity -> entity instanceof Shield).map(entity -> (Shield)entity).sorted(new Shield.ShieldComparator()).collect(Collectors.toList());

		for(int i = 0; i < shields.size()-1; i++){
			int next = i+1 >= shields.size() ? 0 : i+1;

			Shield current = shields.get(i);
			Shield nextShield = shields.get(next);

			current.drawLine(shapeRenderer, nextShield);
		}
	}

	private void update() throws IllegalAccessException, InvocationTargetException, InstantiationException {
		List<Shield> shields = entities.stream().filter(entity -> entity instanceof Shield).map(entity -> (Shield)entity).sorted(new Shield.ShieldComparator()).collect(Collectors.toList());
		if(shields.size() > 50) entities.remove(shields.get(0));

		for(int i = 0; i < shields.size()-1; i++){
			int next = i+1 >= shields.size() ? 0 : i+1;

			Shield current = shields.get(i);
			Shield nextShield = shields.get(next);

			current.drawLine(shapeRenderer, nextShield);
		}

		spawnVirus();
		spawnObjects();

		getEntities().forEach(entity -> entity.update(entities));
	}

	private void spawnObjects() throws IllegalAccessException, InstantiationException, InvocationTargetException {
		if(Math.random() < 0.01){
			SpecialObject specialObject;
			do{
				specialObject = Utils.newObject(Utils.chooseRandom(specialobjects), new Location(0, 0));
			}while(Math.random() > specialObject.chance(entities));
			specialObject.spawn(entities);
		}
	}

	private void spawnVirus() throws IllegalAccessException, InstantiationException, InvocationTargetException {
		if(Math.random() < 0.25) {
			Virus virus;
			do {
				virus = Utils.newObject(Utils.chooseRandom(this.virus), new Location(0, 0));
			} while (!virus.canSpawn(entities) && Math.random() > virus.chance(entities));
			virus.spawn(entities);
		}
	}

	private void checkInputs() {
		Input input = Gdx.input;
		if(input.isButtonPressed(Input.Buttons.LEFT)){
			getEntities().forEach(entity -> entity.onClick(entities, input.getX(), Gdx.graphics.getHeight()-input.getY()));
		}
	}

	public ArrayList<Entity> getEntities(){
		return new ArrayList<>(entities);
	}
	@Override
	public void dispose () {
		spriteBatch.dispose();
	}
}
