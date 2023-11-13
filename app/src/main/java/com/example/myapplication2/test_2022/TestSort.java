package com.example.myapplication2.test_2022;

public class TestSort {

    public static void main(String[] args) {
        int[] arr = {3, 8, 2, 6, 4, 10, 30, 15, 6};
        int[] arr2 = {6, 12, 18, 22, 3, 6, 9, 30, 7, 31};
        bubbleSort(arr);
//        bubbleSort(arr2);
//        quick(arr);
//        quick(arr2);
//        selectSort(arr);
//        selectSort(arr2);
//        insertSort(arr);
//        insertSort(arr2);
        shellSort(arr);
//        shellSort(arr2);
        heapSort(arr);
    }

    /**
     * 冒泡排序
     *
     * @param nums
     */
    public static void bubbleSort(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
        printResult(nums, "冒泡");
    }

    /*
    快速排序
     */
    public static void quick(int[] nums) {
        if (nums == null || nums.length == 0) return;
        quickSort(nums, 0, nums.length - 1);
        printResult(nums, "快速");
    }

    public static void quickSort(int[] nums, int low, int height) {
        if (low < height) {
            int middle = getMiddle(nums, low, height);
            quickSort(nums, 0, middle - 1);
            quickSort(nums, middle + 1, height);

        }

    }

    public static int getMiddle(int[] nums, int low, int height) {
        int temp = nums[low];
        while (low < height) {
            while (low < height && nums[height] >= temp) {
                height--;
            }
            nums[low] = nums[height];
            while (low < height && nums[low] < temp) {
                low++;
            }
            nums[height] = nums[low];

        }
        nums[low] = temp;
        return low;
    }

    /*
      选择排序
     */
    public static void selectSort(int nums[]) {
        if (nums == null || nums.length == 0) return;
        int len = nums.length;
        int k;
        int temp;
        for (int i = 0; i < len - 1; i++) {
            k = i;
            for (int j = len - 1; j > i; j--) {
                if (nums[j] < nums[k]) {
                    k = j;
                }
            }
            temp = nums[i];
            nums[i] = nums[k];
            nums[k] = temp;
        }
        printResult(nums, "选择");
    }

    /*
      插入排序
     */
    public static void insertSort(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int len = nums.length;
        int temp;
        int j;
        for (int i = 0; i < len - 1; i++) {
            temp = nums[i];

            for (j = i; j > 0 && nums[j - 1] > temp; j--) {
                nums[j] = nums[j - 1];
            }
            nums[j] = temp;
        }
        printResult(nums, "插入");
    }

    /*
    希尔排序
    * */
    public static void shellSort(int[] data) {
        if (data == null || data.length == 0) return;
        int len = data.length;
        int temp;
        int j;
        for (int step = len / 2; step > 0; step /= 2) {
            for (int i = step; i < len; i++) {
                temp = data[i];
                for (j = i; j >= step; j -= step) {
                    if (temp > data[j - step]) {
                        data[j] = data[j-step];
                    } else {
                        break;
                    }
                }
                data[j]=temp;
            }
        }
        printResult(data, "希尔");
    }

    /*
      归并排序
    * */
    public static void mergerSort(int[] nums) {

    }

    /*
      堆排序
    * */
    public static void heapSort(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int len = nums.length;
        for (int i = 0; i <= len - 1; i++) {
            buildMaxHeap(nums, len - i - 1);
            swap(nums, 0, len - i - 1);
        }
        printResult(nums, "堆");
    }

    //对data数组从0到lastIndex建大顶堆
    public static void buildMaxHeap(int[] data, int lastIndex) {
        //从lastIndex处节点（最后一个节点）的父节点开始
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            //k保存正在判断的节点
            int k = i;
            //如果当前k节点的子节点存在
            while (k * 2 + 1 <= lastIndex) {
                //k节点的左子节点的索引
                int biggerIndex = 2 * k + 1;
                //如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
                if (biggerIndex < lastIndex) {
                    //若果右子节点的值较大
                    if (data[biggerIndex] < data[biggerIndex + 1]) {
                        //biggerIndex总是记录较大子节点的索引
                        biggerIndex++;
                    }
                }
                //如果k节点的值小于其较大的子节点的值
                if (data[k] < data[biggerIndex]) {
                    //交换他们
                    swap(data, k, biggerIndex);
                    //将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值
                    k = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }

    //交换
    private static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public static void printResult(int[] nums, String title) {
        if (nums == null || nums.length == 0) {
            System.out.println("排序数组不能为空");
            return;
        }
        System.out.println(title + "排序数组:");
        for (int num : nums) {
            System.out.print(num + ",");
        }
        System.out.println("");
    }
}
