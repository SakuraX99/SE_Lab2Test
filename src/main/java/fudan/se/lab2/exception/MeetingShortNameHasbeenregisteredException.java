package fudan.se.lab2.exception;

public class MeetingShortNameHasbeenregisteredException extends RuntimeException {
    private static final long serialVersionUID = -6074753940710869970L;

    public MeetingShortNameHasbeenregisteredException(String shortName) {
        super("Meeting Short Name"+ shortName +" has been registered!");
    }
}
