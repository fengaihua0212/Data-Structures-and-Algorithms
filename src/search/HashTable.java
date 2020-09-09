package search;


/**
 * 散列表
 * @author fah
 */
public class HashTable<K, V> {

    /**
     * 散列表默认长度
     */
    private static final int DEFALUT_INITAL_CAPACITY = 8;

    /**
     * 装载因子
     */
    private static final float LOAD_FACTOR = 0.75f;

    /**
     * 初始化散列表数组
     */
    private Entry<K, V>[] table;

    /**
     * 实际元素数量
     */
    private int size = 0;

    /**
     * 实际使用索引数量
     */
    private int use = 0;

    public HashTable() {
        table = (Entry<K, V>[]) new Entry[DEFALUT_INITAL_CAPACITY];
    }

    static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 新增
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        int index = hash(key);
        // 位置未被引用，创建哨兵节点
        if (table[index] == null) {
            table[index] = new Entry<>(null, null, null);
        }
        Entry<K, V> temp = table[index];
        // 新增节点
        if (temp.next == null) {
            temp.next = new Entry<>(key, value, null);
            size++;
            use++;
            if (use >= table.length * LOAD_FACTOR) {
                resize();
            }
        }
        // 解决散列冲突，使用链表法
        else {
            do {
                temp = temp.next;
                // key 相同，覆盖旧的数据
                if (temp.key.equals(key)) {
                    temp.value = value;
                    return;
                }
            } while (temp.next != null);

            Entry next = table[index].next;
            table[index].next = new Entry<>(key, value, next);
            size++;
        }
    }

    /**
     * 散列函数
     * 参考HashMap的散列函数
     * @param key
     * @return
     */
    private int hash(Object key) {
        int h;
        return key == null ? 0 : ((h = key.hashCode()) ^ (h >>> 16)) % table.length;
    }

    /**
     * 扩容
     */
    private void resize() {
        Entry<K, V>[] oldTable = table;
        table = (Entry<K, V>[]) new Entry[table.length * 2];
        use = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if (null == oldTable[i] || null == oldTable[i].next) {
                continue;
            }
            Entry<K, V> e = oldTable[i];
            while (null != e.next) {
                e = e.next;
                int index = hash(e.key);
                if (null == table[index]) {
                    use++;
                    // 创建哨兵
                    table[index] = new Entry<>(null, null, null);
                }
                table[index].next = new Entry<>(e.key, e.value, e.next);
            }
        }
    }

    /**
     * 删除
     * @param key
     */
    public void delete(K key) {
        int index = hash(key);
        Entry e = table[index];
        if (null == e || null == e.next) {
            return;
        }
        Entry pre;
        Entry<K, V> headNode = table[index];
        do {
            pre = e;
            e = e.next;
            if (key.equals(e.key)) {
                pre.next = e.next;
                size--;
                if (null == headNode.next) {
                    use--;
                }
                return;
            }
        } while (null != e.next);
    }

    /**
     * 获取
     * @param key
     * @return
     */
    public V get(K key) {
        int index = hash(key);
        Entry<K, V> e = table[index];
        if (null == e || null == e.next) {
            return null;
        }

        while (null != e.next) {
            e = e.next;
            if (key.equals(e.key)) {
                return e.value;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>();
        for (int i = 0; i < 20; i++) {
            hashTable.put("key" + i, i);
        }

        for (int i = 0; i < 20; i++) {
            System.out.println(hashTable.get("key" + i));
        }

        for (int i = 0; i < 20; i++) {
            hashTable.delete("key" + i);
        }
    }
}
