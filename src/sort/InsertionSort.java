package sort;

/**
 * 插入排序
 * @author fah
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = new int[]{23, 2, 5, 1, 6, 3, 7};
        insertionSort(arr, arr.length);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    /**
     * 插入排序
     * @param arr 待排序数组
     * @param n 数组长度
     */
    private static void insertionSort(int[] arr, int n) {
        if (n <= 1) {
            return;
        }

        for (int i = 1; i < n; i++) {
            int value = arr[i];
            int j = i - 1;
            // 查找插入的位置
            for (; j >= 0; j--) {
                if (value < arr[j]) {
                    // 交换数据
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            // 插入数据
            arr[j + 1] = value;
        }
    }
}
