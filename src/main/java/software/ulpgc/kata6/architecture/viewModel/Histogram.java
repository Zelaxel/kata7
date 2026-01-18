package software.ulpgc.kata6.architecture.viewModel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Histogram<T> implements Iterable<T> {
    private final Map<T, Integer> map;
    private final Map<String, String> labels;

    public Histogram(Map<String, String> labels) {
        map = new HashMap<>();
        this.labels = labels;
    }
    
    public int count(T bin){
        return map.getOrDefault(bin, 0);
    }
    
    public void put(T bin){
        map.put(bin, count(bin) + 1);
    }

    @Override
    public Iterator<T> iterator() {
        return map.keySet().iterator();
    }
    
    public String title(){
        return labels.get("title");
    }

    public String x(){
        return labels.get("x");
    }

    public String y(){
        return labels.get("y");
    }

    public String legend(){
        return labels.get("legend");
    }

    public Map<T, Integer> map() {
        return new HashMap<>(map);
    }
}
