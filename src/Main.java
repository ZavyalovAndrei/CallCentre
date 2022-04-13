import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    private static final int OPERATOR_QUANTITY = 4;
    private static final int CALLS_QUANTITY = 20;
    private static final int NEW_CALL_DELAY = 1000;
    private static final int CALL_PROCESSING = 5000;

    public static void main(String[] args) {
        ConcurrentLinkedQueue calls = new ConcurrentLinkedQueue();
        Collection<Thread> threads = new ArrayList<>(OPERATOR_QUANTITY);
        for (int i = 1; i <= OPERATOR_QUANTITY; i++) {
            threads.add(new Operator("Оператор №" + (i), CALL_PROCESSING, calls));
        }
        ATS ats = new ATS(calls, NEW_CALL_DELAY, CALLS_QUANTITY);
        ats.start();
        for (Thread thread : threads) {
            thread.start();
        }
    }
}