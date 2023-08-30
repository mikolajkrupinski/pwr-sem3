package eu.jpereira.trainings.designpatterns.structural.proxy;

import eu.jpereira.trainings.designpatterns.structural.proxy.fakes.FakeProxyTrafficLightFactory;
import eu.jpereira.trainings.designpatterns.structural.proxy.testconfig.TestConfiguration;
import org.junit.Before;

public class ProxyTrafficLightsManagerTest extends TrafficLightsManagerTest {
    @Before
    public void setUp() {
        TestConfiguration.fakeFailuresInController = true;
    }

    @Override
    protected TrafficLightsManager createTrafficLightsManager() {
        TrafficLightsManager manager = new TrafficLightsManager(new
                FakeProxyTrafficLightFactory());
        return manager;
    }
}
