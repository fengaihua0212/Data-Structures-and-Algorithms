package search;

/**
 * 字符串匹配
 * @author fah
 */
public class StringMatch {

    private final static int SIZE = 256;

    public static int bf(String a, String b) {
        int n = a.length();
        int m = b.length();
        char[] aArr = a.toCharArray();
        char[] bArr = b.toCharArray();

        for (int i = 0; i < n - m + 1; i++) {
            for (int j = 0; j < m; j++) {
                if (aArr[i + j] != bArr[j]) {
                    break;
                }
                if (j == m - 1) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int rk(String a, String b) {
        int n = a.length();
        int m = b.length();
        int[] hash = new int[n - m + 1];
        int[] table = new int[26];
        char[] aArr = a.toCharArray();
        char[] bArr = b.toCharArray();
        int s, j;
        s = 1;
        for (j = 0; j < 26; j++) {
            table[j] = s;
            s *= 26;
        }

        for (int i = 0; i < n - m + 1; i++) {
            s = 0;
            for (j = 0; j < m; j++) {
                s += (aArr[i + j] - 'a') * table[m - j - 1];
            }
            hash[i] = s;
        }

        s = 0;
        for (j = 0; j < m; j++) {
            s += (bArr[j] - 'a') * table[m - j - 1];
        }

        for (j = 0; j < n - m + 1; j++) {
            if (hash[j] == s) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 将模式串中的每个字符及其下标都存到散列表中
     * @param b 模式串
     * @param m 模式串的长度
     * @param bc 散列表
     */
    private static void generateBC(char[] b, int m, int[] bc) {
        // 初始化bc
        for (int i = 0; i < SIZE; i++) {
            bc[i] = -1;
        }

        for (int i = 0; i < m; i++) {
            // 计算b[i]的ASCII值
            int ascii = b[i];
            bc[ascii] = i;
        }
    }

    /**
     * bm算法
     * @param a 主串
     * @param n 主串的长度
     * @param b 模式串
     * @param m 模式串的长度
     * @return
     */
    public static int bm(char[] a, int n, char[] b, int m) {
        // 记录模式串中每个字符最后出现的位置
        int[] bc = new int[SIZE];
        // 构建坏字符哈希表
        generateBC(b, m, bc);
        int[] suffix = new int[m];
        boolean[] prefix = new boolean[m];
        generateGS(b, m, suffix, prefix);
        // i表示主串与模式串对齐的第一个字符
        int i = 0;
        while (i <= n - m) {
            int j;
            // 模式串从后往前匹配
            for (j = m - 1; j >=0; j--) {
                // 坏字符对应模式串中的下标是j
                if (a[i + j] != b[j]) {
                    break;
                }
            }

            // 匹配成功，返回主串与模式串第一个匹配的字符的位置
            if (j < 0) {
                return i;
            }

            int x = j - bc[a[i + j]];
            int y = 0;
            // 如果有好后缀的话
            if (y < m - 1) {
                y = moveByGS(j, m, suffix, prefix);
            }

            i = i + Math.max(x, y);
        }

        return -1;

    }

    // j表示坏字符对应的模式串中的字符下标; m表示模式串长度
    private static int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
        // 好后缀长度
        int k = m - 1 - j;
        if (suffix[k] != -1) {
            return j - suffix[k] + 1;
        }

        for (int r = j + 2; j < m - 1; r++) {
            if (prefix[m - r] == true) {
                return r;
            }
        }

        return m;
    }

    /**
     *
     * @param b 模式串
     * @param m 模式串长度
     * @param suffix suffix 数组的下标 k，表示后缀子串的长度，下标对应的数组值存储的是，在模式串中跟好后缀{u}相匹配的子串{u*}的起始下标值。
     * @param prefix 记录模式串的后缀子串是否能匹配模式串的前缀子串
     */
    public static void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
        // 初始化
        for (int i = 0; i < m; i++) {
            suffix[i] = -1;
            prefix[i] = false;
        }

        for (int i = 0; i < m - 1; i++) {
            int j = i;
            // 公共后缀子串长度
            int k = 0;

            // 与b[0, m-1]求公共后缀子串
            while (j >= 0 && b[j] == b[m - 1 - k]) {
                --j;
                ++k;
                //j+1表示公共后缀子串在b[0, i]中的起始下标
                suffix[i] = j + 1;
            }

            //如果公共后缀子串也是模式串的前缀子串
            if (j == -1) {
                prefix[k] = true;
            }
        }
    }

    /**
     * kmp 算法
     * @param a 主串
     * @param n 主串长度
     * @param b 模式串
     * @param m 模式串长度
     * @return
     */
    public static int kmp(char[] a, int n, char[] b, int m) {
        int[] next = getNexts(b, m);
        int j = 0;
        for (int i = 0; i < n; i++) {
            // 一直找到a[i]和b[j]
            while (j > 0 && a[i] != b[j]) {
                j = next[j - 1] + 1;
            }
            if (a[i] == b[j]) {
                ++j;
            }

            // 找到匹配模式串的了
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }

    private static int[] getNexts(char[] b, int m) {
        int[] next = new int[m];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < m; i++) {
            while (k != -1 && b[k + 1] != b[i]) {
                k = next[k];
            }
            if (b[k + 1] == b[i]) {
                ++k;
            }
            next[i] = k;
        }

        return next;
    }

    public static void main(String[] args) {
        String a = "asdfslkgjsaogdsgds";
        String b = "gds";
        int index = StringMatch.bf(a, b);
        System.out.println(index);
        index = StringMatch.rk(a, b);
        System.out.println(index);
        index = StringMatch.bm(a.toCharArray(), a.length(), b.toCharArray(), b.length());
        System.out.println(index);
        index = StringMatch.kmp(a.toCharArray(), a.length(), b.toCharArray(), b.length());
        System.out.println(index);
    }
}
