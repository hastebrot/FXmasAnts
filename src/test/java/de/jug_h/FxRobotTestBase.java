package de.jug_h;

import org.junit.BeforeClass;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

public class FxRobotTestBase extends FxRobot {

    @BeforeClass
    public static void setupSpec() throws Exception {
        FxToolkit.registerPrimaryStage();
    }

}
