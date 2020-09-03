package search;

/**
 * 二分查找
 * @author fah
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(binarySearch(arr, arr.length, 5));
    }

    /**
     * 二分查找算法
     * @param arr 数组
     * @param n 数组长度
     * @param value 目标值
     * @return 目标值下标
     */
    public static int binarySearch(int[] arr, int n, int value) {
        int low = 0;
        int high = n - 1;

        while (high >= low) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == value) {
                return mid;
            } else if (arr[mid] > value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }
}
