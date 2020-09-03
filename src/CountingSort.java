/**
 * 计数排序
 * @author fah
 */
public class CountingSort {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 4, 3, 2, 5, 2, 0};
        countingSort(arr, arr.length);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    /**
     * 计数排序
     * @param arr 待排序数组
     * @param n 数组长度
     */
    public static void countingSort(int[] arr, int n) {
        if (n <= 1) {
            return;
        }

        // 查找数组中数据的范围
        int maxValue = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }

        // 申请一个计数数组c，下标大小[0,maxValue]
        int[] c = new int[maxValue + 1];
        for (int i = 0; i < c.length; i++) {
            c[i] = 0;
        }

        // 计算每个元素的个数，放入c中
        for (int i = 0; i < arr.length; i++) {
            c[arr[i]]++;
        }

        // 依次累加
        for (int i = 1; i < c.length; i++) {
            c[i] = c[i - 1] + c[i];
        }

        // 临时数组r，存储排序后的结果
        int[] r = new int[n];
        // 计算排序的关键步骤
        for (int i = n - 1; i >= 0; i--) {
            int index = c[arr[i]] - 1;
            r[index] = arr[i];
            c[arr[i]]--;
        }

        // 将结果拷贝给原始数组
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r[i];
        }
    }
}
