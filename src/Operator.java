import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Operator extends Thread {

    private final int callProcessing;
    private final ConcurrentLinkedQueue calls;

    public Operator(String name, int callProcessing, ConcurrentLinkedQueue calls) {
        super(name);
        this.callProcessing = callProcessing;
        this.calls = calls;
    }

    @Override
    public void run() {
        System.out.println(getName() + " готов.");
        Object currentCall;
        try {
            while (true) {
                currentCall = calls.poll();
                if (!(currentCall == null)) {
                    System.out.println(currentCall + " обрабатывает " + getName().toLowerCase(Locale.ROOT));
                    Thread.sleep(callProcessing);
                } else if (ATS.isAtsStop()) {
                    break;
                }
            }
        } catch (InterruptedException err) {
            return;
        } finally {
            System.out.println(getName() + " завершает работу.");
            interrupt();
        }
    }
}