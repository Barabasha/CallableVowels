import java.io.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class Consumer implements Callable<ConcurrentHashMap<String, Integer>> {
    private ConcurrentHashMap<String, Integer> vowelsMap;
    private File file;

    Consumer(File file, ConcurrentHashMap<String, Integer> vowelsMap) {
        this.file = file;
        this.vowelsMap = vowelsMap;
    }

    @Override
    public ConcurrentHashMap<String, Integer> call() throws Exception {
        ConcurrentHashMap<String, Integer> result = this.vowelsMap;
        String line;
        BufferedReader br;
        br = new BufferedReader(new FileReader(file.getAbsolutePath()));
        while ((line = br.readLine()) != null) {
            for (int i = 0; i < line.length(); i++) {
                if (result.containsKey(String.valueOf(line.charAt(i)))) {
                    int count = result.get(String.valueOf(line.charAt(i)));
                    result.put(String.valueOf(line.charAt(i)), ++count);
                }
            }
        }
        br.close();
        return result;
    }
}



