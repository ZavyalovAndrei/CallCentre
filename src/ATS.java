import java.util.concurrent.ConcurrentLinkedQueue;

public class ATS extends Thread {

    private final int newCallDelay;
    private final ConcurrentLinkedQueue calls;
    private final int callsQuantity;
    private static boolean atsStop = false;

    public ATS(ConcurrentLinkedQueue calls, int newCallDelay, int callsQuantity) {
        this.calls = calls;
        this.newCallDelay = newCallDelay;
        this.callsQuantity = callsQuantity;
    }

    public static boolean isAtsStop() {
        return atsStop;
    }

    @Override
    public void run() {
        int callNum = 0;
        System.out.println("Запуск АТС");

        try {
            while (callNum < callsQuantity) {
                callNum++;
                calls.add(new SubscriberCalls("Звонок №" + callNum + " от абонента"));
                System.out.println("Поступил новый звонок №" + callNum + " от абонента");
                Thread.sleep(newCallDelay);
            }
        } catch (InterruptedException err) {
            return;
        } finally {
            System.out.println("АТС завершает работу.");
            atsStop = true;
            interrupt();
        }
    }
}