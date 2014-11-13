## Example

~~~java
private void initAntBehaviour() {
    Random rng = new Random();
    BooleanProperty backwards = new SimpleBooleanProperty(false);
    defineAntConsumer((Ant ant) -> {
        // Move in circle.
        if (ant.id == 0) {
            ant.setAngle(ant.getAngle() + 10);
            ant.move(10);
        }
        // Move randomly.
        if (ant.id == 1) {
            ant.setAngle(rng.nextDouble() * 360);
            ant.move(25);
        }
        // Move back and forth.
        if (ant.id == 2) {
            double value = backwards.get() ? 180 : 0;
            ant.setAngle(90 + value);
            ant.move(25);
            if (ant.getX() > 500) {
                backwards.set(true);
            }
            if (ant.getX() < 0) {
                backwards.set(false);
            }
        }
    });
}
~~~

## TODO

- Simplify random movement and off-field movement detection.
- Add `HashMap` for `Ant` data.
- Add interface for `Sprite`s.
- Implement enemy `Bug`s and energy bar.
- Implement target `Apple`s, transportation weight and target zone.
- Add behaviour presets and ant role assignment.
- Add simple tutorial with samples.
- Add instructions for setup with IntelliJ, Eclipse and Netbeans.
- Add API documentation and Javadocs.
