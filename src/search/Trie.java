package search;

/**
 * Trie数
 * @author fah
 * @Date 2020/9/21 16:02
 */
public class Trie {

    /**
     * 存储无意义字符
     */
    private TrieNode root = new TrieNode('/');

    /**
     * 插入字符串
     * @param text
     */
    public void insert(char[] text) {
        TrieNode p = root;
        for (int i = 0; i < text.length; i++) {
            int index = text[i] - 'a';
            if (p.children[index] == null) {
                TrieNode newNode = new TrieNode(text[i]);
                p.children[index] = newNode;
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
    }

    /**
     * 查找字符串
     * @param pattern
     * @return
     */
    public boolean find(char[] pattern) {
        TrieNode p = root;
        for (int i = 0; i < pattern.length; i++) {
            int index = pattern[i] - 'a';
            if (p.children[index] == null) {
                // 不存在
                return false;
            }
            p = p.children[index];
        }

        if (p.isEndingChar == false) {
            // 不能完全匹配，只是前缀
            return false;
        } else {
            // 找到了
            return true;
        }
    }

    public class TrieNode {
        public char data;
        public TrieNode[] children = new TrieNode[26];
        public boolean isEndingChar = false;
        public TrieNode(char data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("sadlfjslfslf".toCharArray());
        trie.insert("sadlf".toCharArray());
        trie.insert("sad".toCharArray());
        trie.insert("sadlfjs".toCharArray());
        System.out.println(trie.find("sad".toCharArray()));
    }
}
