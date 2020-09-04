package search;

/**
 * 二分查找
 * @author fah
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 5, 6, 6, 7, 8, 9, 10, 12};
        System.out.println(binarySearchLastLessThanOrEqual(arr, arr.length, 10));
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

    /**
     * 二分查找算法 - 查找第一个值等于给定值的元素
     * @param arr 数组
     * @param n 数组长度
     * @param value 目标值
     * @return 目标值下标
     */
    public static int binarySearchFirst(int[] arr, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < value) {
                low = mid + 1;
            } else if (arr[mid] > value) {
                high = mid - 1;
            } else {
                if (mid == 0 || arr[mid - 1] != value) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 二分查找算法 - 查找最后一个值等于给定值的元素
     * @param arr 数组
     * @param n 数组长度
     * @param value 目标值
     * @return 目标值下标
     */
    public static int binarySearchLast(int[] arr, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < value) {
                low = mid + 1;
            } else if (arr[mid] > value) {
                high = mid - 1;
            } else {
                if (mid == n - 1 || arr[mid + 1] != value) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 二分查找算法 - 查找第一个大于等于给定值的元素
     * @param arr 数组
     * @param n 数组长度
     * @param value 目标值
     * @return 目标值下标
     */
    public static int binarySearchFirstGreaterOrEqual(int[] arr, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < value) {
                low = mid + 1;
            } else if (arr[mid] >= value) {
                if (mid == 0 || arr[mid - 1] < value) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 二分查找算法 - 查找最后一个小于等于给定值的元素
     * @param arr 数组
     * @param n 数组长度
     * @param value 目标值
     * @return 目标值下标
     */
    public static int binarySearchLastLessThanOrEqual(int[] arr, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= value) {
                if (mid == n - 1 || arr[mid + 1] > value) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            } else if (arr[mid] > value) {
                high = mid - 1;
            }
        }
        return -1;
    }
}
