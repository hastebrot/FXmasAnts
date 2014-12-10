package de.jug_h.demo;

import java.util.Random;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import de.jug_h.Playfield;
import de.jug_h.entity.Behavior;
import de.jug_h.entity.Entity;
import de.jug_h.entity.Memory;
import de.jug_h.entity.Resources;
import de.jug_h.entity.Sprite;

public class FXmasAnts extends Application {

    //---------------------------------------------------------------------------------------------
    // MAIN METHOD.
    //---------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        Application.launch(FXmasAnts.class, args);
    }

    //---------------------------------------------------------------------------------------------
    // PRIVATE FIELDS.
    //---------------------------------------------------------------------------------------------

    private Playfield playfield;

    //---------------------------------------------------------------------------------------------
    // METHODS.
    //---------------------------------------------------------------------------------------------

    public void start(Stage primaryStage) {
        playfield = new Playfield();
        Pane playfieldPane = playfield.buildPane();
        playfield.run();

        initEntities();
        initEntityBehavior();
        playfield.refresh();

        Scene scene = new Scene(playfieldPane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle(getClass().getSimpleName());
        primaryStage.show();
    }

    //---------------------------------------------------------------------------------------------
    // PRIVATE METHODS.
    //---------------------------------------------------------------------------------------------

    private void initEntities() {
        for (int index = 0; index <= 4; index++) {
            Entity entity = new Entity(index);
            Sprite sprite = new Sprite("ant", Resources.antImage());
            sprite.xProperty().set(200);
            sprite.yProperty().set(100);
            entity.setSprite(sprite);
            entity.setMemory(new Memory());
            entity.setBehavior(new Behavior(sprite));
            playfield.getEntities().add(entity);
        }

        Entity bugEntity = new Entity(10);
        Sprite bugSprite = new Sprite("bug", Resources.bugImage());
        bugSprite.xProperty().set(350);
        bugSprite.yProperty().set(350);
        bugEntity.setSprite(bugSprite);
        bugEntity.setBehavior(new Behavior(bugSprite));
        playfield.getEntities().add(bugEntity);

        Entity fruitEntity = new Entity(11);
        Sprite fruitSprite = new Sprite("fruit", Resources.fruitImage());
        fruitSprite.xProperty().set(50);
        fruitSprite.yProperty().set(350);
        fruitEntity.setSprite(fruitSprite);
        fruitEntity.setBehavior(new Behavior(fruitSprite));
        playfield.getEntities().add(fruitEntity);
    }

    private void initEntityBehavior() {
        Random random = new Random();
        playfield.define((Entity entity) -> {
            // move in circle.
            if (entity.id() == 0) {
                if (entity.memory().getDouble("distance") == 0.0) {
                    entity.memory().put("distance", 10.0);
                    entity.behavior().turnBy(10);
                    entity.behavior().moveWalk();
                }
                entity.memory().putDouble("distance", value -> value - 1);
            }

            // move randomly.
            if (entity.id() == 1) {
                if (entity.memory().getDouble("distance") == 0.0) {
                    entity.memory().put("distance", 50.0);
                    entity.behavior().turnTo(random.nextDouble() * 360);
                    entity.behavior().moveWalk();
                }
                entity.memory().putDouble("distance", value -> value - 1);
            }

            // move left and right.
            if (entity.id() == 2) {
                if (entity.memory().getDouble("angle") == 0.0) {
                    entity.memory().put("angle", 90.0);
                }
                if (entity.memory().getDouble("distance") == 0.0) {
                    entity.behavior().turnTo(entity.memory().getDouble("angle"));
                    entity.behavior().moveWalk();
                    if (entity.behavior().position().getX() >= (500 - 25 - 40)) {
                        entity.memory().put("angle", -90.0);
                    }
                    if (entity.behavior().position().getX() <= 0) {
                        entity.memory().put("angle", 90.0);
                    }
                    entity.memory().put("distance", 10.0);
                }
                entity.memory().putDouble("distance", value -> value - 1);
            }
        });
    }

}
