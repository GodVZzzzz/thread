package thread;

import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author Zhang
 * @date 2018/8/1
 * @Description  线程安全集合
 */
public class SafeCollections {

    private static ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        /*map原子更新*/
        String word = "word";
        String word1 = "word1";
        String word2 = "word2";
        String word3 = "word3";
        String word4 = "word4";
        Long newValue ;
        Long oldValue ;
        map.put(word, (long) 1);
        do{
            oldValue = map.get(word);
            newValue = oldValue == null ? 1 : oldValue + 1;
        }while (! map.replace(word,oldValue,newValue));

        System.out.println(map);

        /*LongAdder方法*/
        ConcurrentMap<String, LongAdder> map1 = new ConcurrentHashMap<>();
        map1.putIfAbsent(word1,new LongAdder());
        map1.get(word1).increment();
        map1.get(word1).increment();

        System.out.println(map1);

        /*compute方法*/
        map.compute(word2,(k,v) -> v == null ? 1 : v+1);

        System.out.println(map);

        /*merge方法增加键值*/
        map.merge(word3,1L, (existingValue, otherValue) -> existingValue+otherValue);
        System.out.println(map);

        map.merge(word4, 1L, Long ::sum);
        System.out.println(map);

        /*批处理*/
        String result = map.search(1, (k,v) -> v > 1 ? k:null);
        System.out.println(result);

        map.forEach(1,(k,v) -> System.out.println(k+"->"+v));

        Long sum = map.reduceValues(1,Long::sum);
        System.out.println(sum);

        Set<String> words = map.keySet(1L);
        System.out.println(words);
    }
}
