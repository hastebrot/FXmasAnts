package de.jug_h.demo;

import java.util.Objects;
import java.util.Random;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import de.jug_h.Playfield;
import de.jug_h.entity.Entity;
import de.jug_h.entity.EntityBuilder;

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

        Scene scene = new Scene(playfieldPane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle(getClass().getSimpleName());
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    //---------------------------------------------------------------------------------------------
    // PRIVATE METHODS.
    //---------------------------------------------------------------------------------------------

    private void initEntities() {
        EntityBuilder.register(playfield);

        //Entity nest = EntityBuilder.build()
        //    .id(0).name("nest").image(null).position(200, 100)
        //    .getEntity();

        //for (int index = 1; index <= 9; index += 1) {
        //    Entity ant = EntityBuilder.buildAnt().id(index).position(200, 100).getEntity();
        //    playfield.getEntities().add(ant);
        //}

        Entity ant = EntityBuilder.buildAnt().id(10).position(200, 100).getEntity();
        Entity bug = EntityBuilder.buildBug().id(11).position(200, 350).getEntity();
        Entity fruit = EntityBuilder.buildFruit().id(12).position(50, 350).getEntity();
        playfield.getEntities().addAll(ant, bug, fruit);
    }

    private void initEntityBehavior() {
        Random random = new Random();
        playfield.define((Entity entity) -> {
            // move to bug.
            if (entity.id() == 10) {
                if (!entity.behavior().walks()) {
                    entity.memory().put("distance", 10.0);
                    entity.behavior().turnAt(180);
                    entity.behavior().walk();
                }
                Entity target = entity.behavior().lookFor("bug");
                if (target != null) {
                    System.out.println("turn away");
                    entity.behavior().turnAwayFrom(target);
                }
                if (entity.memory().getDouble("distance") > 0) {
                    entity.memory().putDouble("distance", value -> value - 1);
                }
                else {
                    entity.behavior().stop();
                }
            }

            // bug attacks ant.
            if (Objects.equals(entity.sprite().getName(), "bug")) {
                if (!entity.memory().has("target")) {
                    Entity target = entity.behavior().lookFor("ant");
                    entity.memory().put("target", target);
                }
                if (entity.memory().has("target")) {
                    Entity target = entity.memory().getObject("target");
                    entity.memory().put("distance", entity.behavior().distanceTo(target));
                    entity.behavior().turnTo(target);
                    entity.behavior().walk();
                    entity.memory().remove("target");
                }
                if (entity.memory().getDouble("distance") >= 0) {
                    entity.memory().putDouble("distance", value -> value - 1);
                }
                else {
                    entity.behavior().stop();
                }
            }

            // move in circle.
            if (entity.id() == 1) {
                if (entity.memory().getDouble("distance") == 0.0) {
                    entity.memory().put("distance", 10.0);
                    entity.behavior().turnBy(10);
                    entity.behavior().walk();
                }
                entity.memory().putDouble("distance", value -> value - 1);
            }

            // move randomly.
            if (entity.id() == 2) {
                if (entity.memory().getDouble("distance") == 0.0) {
                    entity.memory().put("distance", 50.0);
                    entity.behavior().turnAt(random.nextDouble() * 360);
                    entity.behavior().walk();
                }
                entity.memory().putDouble("distance", value -> value - 1);
            }

            // move left and right.
            if (entity.id() == 3) {
                if (entity.memory().getDouble("angle") == 0.0) {
                    entity.memory().put("angle", 90.0);
                }
                if (entity.memory().getDouble("distance") == 0.0) {
                    entity.behavior().turnAt(entity.memory().getDouble("angle"));
                    entity.behavior().walk();
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
