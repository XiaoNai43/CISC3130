import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Dictionary<K extends Comparable, V> {

    private List<Entry<K, V>> set;

    public Dictionary() {
        this.set = new LinkedList<Entry<K, V>>();
    }

    /**
     * find(k): if the dictionary has an entry with key k, returns it, else, returns
     * null
     */
    public Entry<K, V> find(K key) {
        for (Entry<K, V> entry : this.set) {
            if (entry.key().equals(key)) {
                return entry;
            }
        }
        return null;
    }

    /**
     * findAll(k): returns an iterator of all entries with key k
     * 
     * @return
     */
    public Iterator<Entry<K, V>> findAll(K key) {
        List<Entry<K, V>> tmp = new ArrayList<Entry<K, V>>();
        for (Entry<K, V> entry : this.set) {
            if (entry.key().equals(key)) {
                tmp.add(entry);
            }
        }
        return tmp.iterator();
    }

    public Iterator<Entry<K, V>> iterator() {
        return set.iterator();
    }

    /**
     * insert(k, o): inserts and returns the entry (k, o)
     */
    @SuppressWarnings("unchecked")
    public Entry<K, V> insert(K key, V value) {
        Entry<K, V> newEntry = new Entry<K, V>(key, value);
        this.set.add(newEntry);
        Collections.sort(set, new Comparator<Entry<K, V>>() {
            @Override
            public int compare(Entry<K,V> s1, Entry<K,V> s2) {
                return s1.key().compareTo(s1.key());
            }
        });
        return newEntry;
    }

    /**
     * remove(e): remove the entry e from the dictionary
     */
    public Entry<K, V> remove(Entry<K, V> entry) {
        for (Entry<K, V> current : this.set) {
            if (current.equals(entry)) {
                set.remove(current);
            }
        }
        return entry;
    }

    public int size() {
        return set.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public String toString() {
        return set.toString();
    }
}