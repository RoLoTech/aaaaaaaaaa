package labTic.services.exceptions;

public class ClientAlreadyExists extends Exception {

    public ClientAlreadyExists(String message) {
        super(message);
    }

    public ClientAlreadyExists() {
    }
}
