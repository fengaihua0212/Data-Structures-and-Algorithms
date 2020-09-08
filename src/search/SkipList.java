package search;

import java.util.Arrays;

/**
 * 跳表的一种实现方法。
 * 跳表中存储的是正整数，且不重复
 * @author fah
 */
public class SkipList {

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        skipList.insert(2);
        skipList.insert(3);
        skipList.insert(5);
        skipList.insert(6);
        skipList.insert(7);
        skipList.insert(9);
        skipList.insert(10);
        skipList.insert(12);
        skipList.insert(14);
        skipList.insert(20);
        skipList.insert(24);
        skipList.insert(30);
        System.out.println(skipList.find(10));
        skipList.printAll();
    }

    private static final float SKIPLIST_P = 0.5f;
    private static final int MAX_LEVEL = 16;
    private int levelCount = 1;

    // 带头链表
    private Node head = new Node();

    public Node find(int value) {
        Node p = head;
        for (int i = levelCount - 1; i >= 0 ; i--) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
        }
        if (p.forwards[0] != null && p.forwards[0].data == value) {
            return p.forwards[0];
        } else {
            return null;
        }
    }

    public void insert(int value) {
        int level = randomLevel();
        Node newNode = new Node();
        newNode.data = value;
        newNode.maxLevel = level;
        Node[] update = new Node[level];
        for (int i = 0; i < level; i++) {
            update[i] = head;
        }

        // 记录每层小于update数组插入值的最大值
        Node p = head;
        for (int i = level - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            update[i] = p;
        }

        for (int i = 0; i < level; i++) {
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;
        }

        if (levelCount < level) {
            levelCount = level;
        }
    }

    public void delete(int value) {
        Node[] update = new Node[levelCount];
        Node p = head;
        for (int i = levelCount - 1; i >=0 ; i--) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            update[i] = p;
        }

        if (p.forwards[0] != null && p.forwards[0].data == value) {
            for (int i = levelCount - 1; i >= 0; i--) {
                if (update[i].forwards[i] != null && update[i].forwards[i].data == value) {
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }
        }

        while (levelCount > 1 && head.forwards[levelCount] == null) {
            levelCount--;
        }
    }

    /**
     * 理论来讲，一级索引中元素个数应该占原始数据的 50%，二级索引中元素个数占 25%，三级索引12.5% ，一直到最顶层。
     * 因为这里每一层的晋升概率是 50%。对于每一个新插入的节点，都需要调用 randomLevel 生成一个合理的层数。
     * 该 randomLevel 方法会随机生成 1~MAX_LEVEL 之间的数，且 ：
     *      50%的概率返回 1
     *      25%的概率返回 2
     *      12.5%的概率返回 3 ...
     * @return 索引层数
     */
    private int randomLevel() {
        int level = 1;
         while (Math.random() < SKIPLIST_P && level < MAX_LEVEL) {
             level += 1;
         }

         return level;
    }

    public void printAll() {
        Node p = head;
        while (p.forwards[0] != null) {
            System.out.print(p.forwards[0] + "");
            p = p.forwards[0];
        }
        System.out.println();
    }

    public class Node {
        private int data = -1;
        private Node[] forwards = new Node[MAX_LEVEL];
        private int maxLevel = 0;

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", forwards=" + Arrays.toString(forwards) +
                    ", maxLevel=" + maxLevel +
                    '}';
        }
    }
}
