import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class VowelsWithCall {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ConcurrentHashMap vowelsMap = new ConcurrentHashMap<String, Integer>();
        String[] arr = new String[]{"a", "e", "i", "o", "u", "y"};
        for (String anArr : arr) {
            vowelsMap.put(anArr, 0);
        }
        File dir = new File("C:\\test_hillel");

        ExecutorService service = Executors.newFixedThreadPool(5);
        List<Future> results = new ArrayList<>();
        File[] files = dir.listFiles();
        if (files != null) {
            for (File unit : files) {
                if (!unit.isDirectory()) {
                    Consumer consumer = new Consumer(unit, vowelsMap);
                    results.add(service.submit(consumer));
                }
            }
        }
        Thread.sleep(100);

        for (Future result : results) {
            result.get();
        }

        service.shutdown();
        System.out.println(vowelsMap);
    }
}
