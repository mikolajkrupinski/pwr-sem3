package eu.jpereira.trainings.designpatterns.structural.bridge.protocol;

public class BluetoothProtocol extends Protocol {
    @Override
    public void startSession() {
        // SOME HARD WORK

        super.setSessionActive(true);
    }

    @Override
    public void endSession() {
        // SOME HARD WORK

        super.setSessionActive(false);
    }
}
