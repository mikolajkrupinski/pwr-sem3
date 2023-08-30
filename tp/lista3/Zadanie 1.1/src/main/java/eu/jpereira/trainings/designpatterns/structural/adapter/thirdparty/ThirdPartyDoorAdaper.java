package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;

public class ThirdPartyDoorAdaper extends ThirdPartyDoor implements Door {

    @Override
    public void open(String code) throws IncorrectDoorCodeException {
        try {
            unlock(code);
            setState(DoorState.OPEN);
        } catch (CannotUnlockDoorException e) {
            throw new IncorrectDoorCodeException();
        } catch (CannotChangeStateOfLockedDoor e) {
            throw new IncorrectDoorCodeException();
        }
    }

    @Override
    public void close() throws CannotChangeStateOfLockedDoor {
        setState(DoorState.CLOSED);
    }

    @Override
    public boolean isOpen() {
        return getState().equals(DoorState.OPEN);
    }

    @Override
    public void changeCode(String oldCode, String newCode, String newCodeConfirmation) throws IncorrectDoorCodeException, CodeMismatchException {
        try {
            if (getLockStatus().equals(LockStatus.LOCKED)) {
                unlock(oldCode);
            }
            if (!newCode.equals(newCodeConfirmation)) {
                lock();
            }
            setNewLockCode(newCode);
        } catch (CannotChangeCodeForUnlockedDoor e) {
            throw new CodeMismatchException();
        } catch (CannotUnlockDoorException e) {
            throw new IncorrectDoorCodeException();
        }

    }

    @Override
    public boolean testCode(String code) {
        LockStatus ls = getLockStatus();
        lock();

        try {
            unlock(code);
        } catch (CannotUnlockDoorException e) {
            return false;
        }

        if (ls.equals(LockStatus.LOCKED))
            lock();

        return true;
    }
}
