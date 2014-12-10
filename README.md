## Requirements

- Java Development Kit 8.0 or newer.
- IntelliJ IDEA 12.1 or newer.

## Example

~~~java
private void initEntityBehavior() {
    Random random = new Random();
    defineEntityConsumer((Entity entity) -> {
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
~~~

## TODO

- Simplify random movement and off-field movement detection.
- ~~Add `HashMap` for `Ant`s memory.~~
- ~~Add interface for `Sprite`s.~~
- Implement enemy `Bug`s and energy bar.
- Implement target `Apple`s, transportation weight and target zone.
- Add behaviour presets and ant role assignment.
- Add simple tutorial with samples.
- Add instructions for setup with IntelliJ, Eclipse and Netbeans.
- Add API documentation and Javadocs.
- Center point and bounding box for `Sprite`s.
- `Playfield` with DSL that uses `AntFactory` and `AntBehaviourFactory`.
- Provide `Playfield` with timer and sprites counter.
- Add `AntNest` and spawn `Ant`s within this nest.
- Add visual range.
- Different paradigms: total control and events (`onAppleDetected(apple)`, `onBugDetected(bug)`).
- Simplify movement (`moveTo(apple)`, `moveAwayFrom(bug)`).
