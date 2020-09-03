package sort;

/**
 * 快速排序
 * @author fah
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{10, 23, 3, 5, 6, 2, 7, 9, 8};
        quickSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    /**
     * 快速排序算法
     * @param arr 待排序数组
     * @param start 起始索引
     * @param end 结束索引
     */
    private static void quickSort(int[] arr, int start, int end) {
        // 终止条件
        if (start >= end) {
            return;
        }

        // 获取分区点下标
        int index = partition(arr, start, end);
        // 分区点左边排序
        quickSort(arr, start, index - 1);
        // 分区点右边排序
        quickSort(arr, index + 1, end);
    }

    /**
     * 分区函数：获取分区点
     * @param arr 待排序数组
     * @param start 起始索引
     * @param end 结束索引
     * @return 分区点下标
     */
    private static int partition(int[] arr, int start, int end) {
        // 定义分区数
        int pivot = arr[end];
        int i = start;
        // 进行分区
        for (int j = start; j < end; j++) {
            if (arr[j] < pivot) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }

        arr[end] = arr[i];
        arr[i] = pivot;
        return i;
    }
}
