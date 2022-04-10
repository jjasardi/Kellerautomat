package ch.zhaw.THIN.kellerautomat;

public class StackEmptyException extends Exception{

    public StackEmptyException() {
        super("The stack is empty.");
    }
}
