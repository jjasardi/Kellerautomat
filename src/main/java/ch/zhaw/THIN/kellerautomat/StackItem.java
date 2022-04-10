package ch.zhaw.THIN.kellerautomat;

public class StackItem {

    private final String value;
    private final StackItem nextItem;


    public StackItem(String value, StackItem item) {
        this.value = value;
        this.nextItem = item;

    }

    public StackItem getNextItem() {
        return this.nextItem;
    }


    public String getValue() {
        return value;
    }


}
