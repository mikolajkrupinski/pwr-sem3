package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;

public class ThirdPartyDoorObjectAdapter implements Door {
    private final ThirdPartyDoor thirdPartyDoor = new ThirdPartyDoor();

    @Override
    public void open(String code) throws IncorrectDoorCodeException {
        try {
            thirdPartyDoor.unlock(code);
            thirdPartyDoor.setState(ThirdPartyDoor.DoorState.OPEN);
        } catch (CannotChangeStateOfLockedDoor | CannotUnlockDoorException e) {
            throw new IncorrectDoorCodeException(e);
        }

    }

    @Override
    public void close() {
        try {
            thirdPartyDoor.setState(ThirdPartyDoor.DoorState.CLOSED);
        } catch (CannotChangeStateOfLockedDoor e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean isOpen() {
        return thirdPartyDoor.getState().equals(ThirdPartyDoor.DoorState.OPEN);
    }

    @Override
    public void changeCode(String oldCode, String newCode, String newCodeConfirmation) throws IncorrectDoorCodeException, CodeMismatchException {
        try {
            thirdPartyDoor.unlock(oldCode);
            if(newCode.equals(newCodeConfirmation)) {
                thirdPartyDoor.setNewLockCode(newCode);
            }
            else throw new CodeMismatchException();
        }
        catch (CannotUnlockDoorException | CannotChangeCodeForUnlockedDoor e) {
            throw new IncorrectDoorCodeException();
        }

    }

    @Override
    public boolean testCode(String code) {
        try {
            thirdPartyDoor.unlock(code);
            return true;
        } catch (CannotUnlockDoorException e) {
            return false;
        }
    }
}
