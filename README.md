## Requirements

- Java Development Kit 8.0 or newer.
- IntelliJ IDEA 12.1 or newer.

## Example

~~~java
private void initAntBehaviour() {
    Random rng = new Random();
    defineAntConsumer((Ant ant) -> {
        if (ant.id == 0) {
            ant.setAngle(ant.getAngle() + 10);
            ant.move(10);
        }
        if (ant.id == 1) {
            ant.setAngle(rng.nextDouble() * 360);
            ant.move(25);
        }
        if (ant.id == 2) {
            if (ant.data("backwards") == null) {
                ant.data("backwards", false);
            }
            double value = ant.data("backwards") ? 180 : 0;
            ant.setAngle(90 + value);
            ant.move(25);
            if (ant.getX() > 500) {
                ant.data("backwards", true);
            }
            if (ant.getX() < 0) {
                ant.data("backwards", false);
            }
        }
    });
}
~~~

## TODO

- Simplify random movement and off-field movement detection.
- ~~Add `HashMap` for `Ant` data.~~
- Add interface for `Sprite`s.
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
