package sort;

import java.util.Arrays;

/**
 * 堆排序
 * @author fah
 * @Date 2021/3/23 14:20
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[] {23, 100, 39, 24, 56, 90};

        buildHeap(arr, arr.length);
        sort(arr, arr.length);
        Arrays.stream(arr).forEach(System.out::println);
    }

    /**
     * 排序
     * @param arr
     * @param len
     */
    private static void sort(int[] arr, int len) {
        for (int i = len - 1; i > 0; i--) {
            // 交换堆顶和堆最后一个元素
            swap(arr, 0, i);
            len--;
            heapify(arr, 0, len);
        }
    }

    /**
     * 建堆
     * @param arr
     * @param len
     */
    private static void buildHeap(int[] arr, int len) {
        for (int i = len / 2 - 1; i >= 0; i--) {
            heapify(arr, i, len);
        }
    }

    /**
     * 调整堆
     * @param arr
     * @param i
     * @param len
     */
    private static void heapify(int[] arr, int i, int len) {
        // 左节点下标
        int left = 2 * i + 1;
        // 右节点下标
        int right = 2 *i + 2;
        // 最大节点下标
        int largest = i;

        // 左节点与最大节点比较
        if (left < len && arr[left] < arr[largest]) {
            largest = left;
        }

        // 右节点与最大节点比较
        if (right < len && arr[right] < arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            // 交换最大节点位置
            swap(arr, i, largest);
            // 调整子堆
            heapify(arr, largest, len);
        }
    }

    /**
     * 交换
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
