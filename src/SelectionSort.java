/**
 * 选择排序
 * @author fah
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = new int[]{23, 45, 6, 3, 6, 7, 9, 10};
        selectionSort(arr, arr.length);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    /**
     * 选择排序算法
     * @param arr 待排序数组
     * @param n 数组长度
     */
    private static void selectionSort(int[] arr, int n) {
        if (n <= 1) {
            return;
        }

        int minIndex, temp;
        for (int i = 0; i < n; i++) {
            minIndex = i;
            for (int j = i; j < n; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
}
