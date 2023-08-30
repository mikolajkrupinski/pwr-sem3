package eu.jpereira.trainings.designpatterns.structural.bridge.protocol;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ExtendedProtocolSelector extends ProtocolSelector {
    @Override
    protected Collection<Protocol> createAditionalProtocols() {
        List<Protocol> toReturn = new ArrayList<>();
        toReturn.add(new BluetoothProtocol());
        return toReturn;
    }
}
