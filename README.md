~~~java
Random rng = new Random();
defineAntConsumer((Ant ant) -> {
    // Move in circle.
    if (ant == ant0) {
        ant.setAngle(ant.getAngle() + 10);
        ant.move(10);
    }
    // Move randomly.
    if (ant == ant1) {
        ant.setAngle(rng.nextDouble() * 360);
        ant.move(25);
    }
});
~~~
