import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCaches {

    private final int capacity;
    private LinkedHashMap<Integer, Integer> buckets;

    public LRUCaches(int capacity) {
        this.capacity = capacity;

        // using loadfactor and access_order
        this.buckets = new LinkedHashMap<>(capacity, 0.75f, true) {

            private static final long serialVersionUID = 931234148803451473L;

            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return isFull();
            }
        };

    }

    public boolean isFull() {
        return buckets.size() >= capacity;
    }
    public void set(int key, int value) {
        buckets.put(key, value);
    }

    public int get(int key) {
        return buckets.getOrDefault(key, -1);
    }

    public static void main(String[] arg) {
        test_given_queue_6_5_2_1_4__321_remove_key_3();
        test_given_queue_2_6_5_3_4__432_1_remove_key_1();
        test_given_queue_65_3443_1221_remove_key_2();
        test_given_queue_none_capacity_then_return_minus_one();
    }

    private static <T> void runTest(String testName, T expected, T test) {
        StringBuilder stringBuilder = new StringBuilder(testName);
        if (test == expected) {
            stringBuilder.append(": Passed");
        } else {
            stringBuilder.append(":    Failed. Expected: ").append(expected).append(". Got: ").append(test);
        }
        System.out.println(stringBuilder.toString());
    }


    private static void test_given_queue_6_5_2_1_4__321_remove_key_3() {
        LRUCaches caches = new LRUCaches(5);
        caches.set(1, 1);
        caches.set(2, 2);
        caches.set(3, 3);
        caches.set(4, 4);
    
        runTest(".", 1, caches.get(1));       
        runTest(".", 2, caches.get(2));  
        runTest(".", -1, caches.get(9));  

        caches.set(5, 5);
        caches.set(6, 6);

        runTest("test_given_queue_6_5_2_1_4_remove_key_3", -1, caches.get(3));
    }

    private static void test_given_queue_2_6_5_3_4__432_1_remove_key_1() {
        LRUCaches caches = new LRUCaches(5);
        caches.set(1, 1);
        caches.set(2, 2);
        caches.set(3, 3);
        caches.set(4, 4);
    
        runTest(".", 4, caches.get(4));       
        runTest(".", 3, caches.get(3));  
        runTest(".", -1, caches.get(9));  

        caches.set(5, 5);
        runTest(".", 2, caches.get(2));  
        runTest("test_given_queue_2_6_5_3_4__432_1_remove_key_1", -1, caches.get(1));
    }
    private static void test_given_queue_65_3443_1221_remove_key_2() {
        LRUCaches caches = new LRUCaches(5);
        caches.set(1, 1);
        caches.set(2, 2);
        runTest(".", 2, caches.get(2));       
        runTest(".", 1, caches.get(1));  
        caches.set(3, 3);
        caches.set(4, 4);
    
        runTest(".", 4, caches.get(4));       
        runTest(".", 3, caches.get(3));  
        runTest(".", -1, caches.get(9));  

        caches.set(5, 5);
        caches.set(6, 6);
        runTest("test_given_queue_65_3443_1221_remove_key_2", -1, caches.get(2));
    }

    private static void test_given_queue_none_capacity_then_return_minus_one() {
        LRUCaches caches = new LRUCaches(0);

        caches.set(2, 2);
        runTest("test_given_queue_none_capacity_then_return_minus_one", -1, caches.get(2));
    }
}
