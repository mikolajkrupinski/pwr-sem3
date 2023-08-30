package eu.jpereira.trainings.designpatterns.structural.bridge;

import eu.jpereira.trainings.designpatterns.structural.bridge.protocol.BluetoothProtocol;
import eu.jpereira.trainings.designpatterns.structural.bridge.protocol.Protocol;

public class BluetoothProtocolTest extends ProtocolTest {
    @Override
    protected Protocol createProtocolUnderTest() {
        return new BluetoothProtocol();
    }
}
