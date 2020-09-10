package search;

import java.util.Random;

/**
 * 二叉搜索树
 * @author fah
 */
public class BinarySearchTree {

    private Node tree;

    public Node find(int data) {
        Node p = tree;
        while (null != p) {
            if (data < p.data) {
                p = p.left;
            } else if (data > p.data) {
                p = p.right;
            } else {
                return p;
            }
        }

        return null;
    }

    public void insert(int data) {
        if (null == tree) {
            tree = new Node(data);
            return;
        }

        Node p = tree;
        while (null != p) {
            if (data > p.data) {
                if (null == p.right) {
                    p.right = new Node(data);
                    return;
                }
                p = p.right;
            } else if (data < p.data) {
                if (null == p.left) {
                    p.left = new Node(data);
                    return;
                }
                p = p.left;
            }
        }
    }

    public void delete(int data) {
        // p指向要删除的节点，初始化指向根节点
        Node p = tree;
        // pp记录p的父节点
        Node pp = null;
        while (null != p && p.data != data) {
            pp = p;
            if (data > p.data) {
                p = p.right;
            } else {
                p = p.left;
            }
        }

        // 没有找到
        if (null == p) {
            return;
        }

        // 删除的节点有两个子节点
        if (null != p.left && null != p.right) {
            // 查找右子树的最小节点
            Node minP = p.right;
            // minPP 记录 minP 的父节点
            Node minPP = p;
            while (null != minP.left) {
                minP = minP.left;
                minPP = minP;
            }

            // 将minP的数据替换到p
            p.data = minP.data;
            // 下面就变成删除minP
            p = minP;
            pp = minPP;
        }

        // 删除的节点是叶节点或只有一个节点
        // p的子节点
        Node child;
        if (null != p.left) {
            child = p.left;
        } else if (null != p.right) {
            child = p.right;
        } else {
            child = null;
        }

        // 删除根节点
        if (null == pp) {
            tree = child;
        } else if (p == pp.left) {
            pp.left = child;
        } else {
            pp.right = child;
        }
    }

    public Node finMin() {
        if (null == tree) {
            return null;
        }
        Node p = tree;
        while (null != p.left) {
            p = p.left;
        }

        return p;
    }

    public Node finMax() {
        if (null == tree) {
            return null;
        }

        Node p = tree;
        while (null != p.right) {
            p = p.right;
        }

        return p;
    }

    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", left=" + (null == left ? null : left.data) +
                    ", right=" + (null == right ? null : right.data) +
                    '}';
        }
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        Random random = new Random();
        for (int i = 0; i < 900; i++) {
            int data = random.nextInt(900);
            if (null == tree.find(data)) {
                tree.insert(data);
            }
        }

        System.out.println(tree.find(10));
        System.out.println(tree.finMin());
        System.out.println(tree.finMax());
    }
}
