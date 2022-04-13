public class SubscriberCalls extends Thread {
    private final String name;

    public SubscriberCalls(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(name);
        return sb.toString();
    }
}