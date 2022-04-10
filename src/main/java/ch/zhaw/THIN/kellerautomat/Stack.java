package ch.zhaw.THIN.kellerautomat;

public class Stack {

    private StackItem currentItem;
    public static final String STACK_EMPTY = "$";

    public Stack() {
        this.currentItem = new StackItem(STACK_EMPTY, null);
    }
    public void push(int value) {
        push(String.valueOf(value));
    }

    public void push(String value) {
        currentItem = new StackItem(value, currentItem);
    }

    public void pop() {
        this.currentItem = this.currentItem.getNextItem();
    }

    public String popAndReturn() {
        String returnItem;
        try {
            returnItem = currentItem.getValue();
            this.pop();
        } catch (NullPointerException e) {
            returnItem = STACK_EMPTY;
        }
        return returnItem;
    }
}

