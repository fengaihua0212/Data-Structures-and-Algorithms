/**
 * 归并排序
 * @author fah
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{10, 23, 3, 5, 6, 2, 7, 9, 8};
        mergeSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    /**
     * 归并排序算法
     *
     * @param arr 待排序数组
     * @param low 起始索引
     * @param high 结束索引
     */
    private static void mergeSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }

        int mid = (low + high) / 2;
        // 排序左边数组
        mergeSort(arr, low, mid);
        // 排序右边数组
        mergeSort(arr, mid + 1, high);
        // 归并左右数组
        merge(arr, low, mid, high);
    }

    /**
     * 归并
     * @param arr 待排序数组
     * @param low 起始索引
     * @param mid 中间索引
     * @param high 结束索引
     */
    private static void merge(int[] arr, int low, int mid, int high) {
        int i = low, j = mid + 1, k = 0;
        // 创建临时数组存放排好序的数组
        int[] temp = new int[high - low + 1];
        // 将左边子数组和右边子数组归并
        while (i <= mid && j <= high) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // 把左边剩余数组赋值给临时数组
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // 把右边剩余数组赋值给临时数组
        while (j <= high) {
            temp[k++] = arr[j++];
        }

        // 把排好序的数组赋值给原数组
        for (int l = 0; l < temp.length; l++) {
            arr[low + l] = temp[l];
        }
    }
}
