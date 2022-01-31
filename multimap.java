package weblab;

import java.util.*;
import java.util.stream.Collectors;

class MultiMap {

    private Map<Integer, List<Integer>> map;
    int size = 0;

    /**
     * Creates a new MultiMap.
     */
    public MultiMap() {
        this.map = new HashMap<>();
    }

    /**
     * Gets the size of this MultiMap.
     *
     * @return The number of (key, value) pairs in the MultiMap.
     */
    public int size() {
        return size;
    }

    /**
     * Gets whether this MultiMap is empty.
     *
     * @return True if the MultiMap is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds the given (key, value) pair to the MultiMap.
     *
     * @param key Key for the new item.
     * @param value New item to add to the MultiMap.
     */
    public void put(int key, int value) {

        Optional<Integer> first = map.keySet().stream()
                .filter(k -> k == key).findFirst();

        if(first.isPresent()){
//            if(map.get(key).contains(value)){
//                return;
//            }
            map.get(key).add(value);
            size++;
        }
        else{
            List<Integer> gg = new ArrayList<>();
            gg.add(value);
            map.put(key,gg);
            size++;
        }

    }

    /**
     * Returns all values in the MultiMap for the given key.
     *
     * @param key Key to return all entries for.
     * @return A list of all entries for the given key.
     *         If the key is not in the map, return an empty list.
     */
    public List<Integer> get(int key) {
        Optional<Integer> first = map.keySet().stream().filter(k -> k == key).findFirst();

        if(first.isPresent()){
            return map.get(key);
        }
        return new ArrayList<>();
    }

    /**
     * Removes the given (key, value) pair from the MultiMap.
     *
     * @param key Key for the value that should be removed.
     * @param value Value to remove.
     * @return True if removal was successful, false otherwise.
     */
    public boolean remove(int key, int value) {

        Optional<Integer> first = map.keySet().stream().filter(k -> k == key).findFirst();

        if(first.isPresent()){

            if(!map.get(key).contains(value)){
                return false;
            }
            //List<Integer> refined = map.get(key).stream().filter(x -> x != value).collect(Collectors.toList());
            List<Integer> tt = map.get(key);
            List<Integer> neww = new ArrayList<>(tt);

            for (int i = 0; i < map.get(key).size(); i++) {

                if(map.get(key).get(i) == value){
                    neww.remove(i);
                    map.replace(key,neww);
                    break;
                }

            }

            if(map.get(key).isEmpty()){
                map.remove(key);
                size--;
                return true;
            }
            size--;
            return true;

        }
        else{
            return false;
        }

    }
}