/**
 * 冒泡排序
 * @author fah
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 4, 3, 7, 10, 6};
        bubbleSort(arr, arr.length);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    /**
     * 冒泡排序算法
     * @param arr 待排序数组
     * @param n 数组长度
     */
    private static void bubbleSort(int[] arr, int n) {

        if (n < 1) {
            return;
        }

        for (int i = 0; i < n; i++) {
            // 提前结束排序标志
            boolean flag = false;
            for (int j = 0; j < n - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 交换
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    // 表示有数据交换
                    flag = true;
                }
            }
            // 没有数据交换，提前结束排序
            if (!flag) {
                break;
            }
        }
    }
}
