package com.example.myapplication2.test_2022;

public class TestSort1 {


    public static void main(String[] args) {
        int[] arr = {-13, -5, -5, -1, -6, -3, -10, -5, -3, -4, -1, -13, -6};
//        MaxSubsequenceSum1(arr);
//        MaxSubsequenceSum2(arr);
//        int[] arr2 = {0, 1, 3};
//        printStr(queryNum(arr2));

        printArray2(makeNN(6));

        int[] num1={1,3,5,7};
        int[] num2={2,4,6,8,12,16,20};
        merge(num1,4,num2,7);
    }

    /*
    连续子数组最大和问题
    https://www.cnblogs.com/allzy/p/5162815.html
    * */
    public static void MaxSubsequenceSum1(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int len = nums.length;
        int MaxSum = 0, ThisSum = 0;
        for (int i = 0; i < len; i++) {
            ThisSum = 0;
            for (int j = i; j < len; j++) {
                ThisSum += nums[j];
                if (ThisSum > MaxSum) {
                    MaxSum = ThisSum;
                }
            }
        }
        printStr(MaxSum);

    }

    /**
     * 从0～n-1个有序数中找出缺失的数
     * https://leetcode.cn/problems/que-shi-de-shu-zi-lcof/
     */
    public static int queryNum(int[] nums) {
        if (nums == null || nums.length <= 1) return -1;
        int len = nums.length;
        int low = 0, height = len - 1;
        while (low < height) {
            int mid = (low + height) / 2;
            if (nums[mid] == mid) {
                low = mid + 1;
            } else {
                height = mid - 1;
            }

        }
        return low;
    }

    /**
     * 分治
     *
     * @param nums
     */
    public static void MaxSubsequenceSum2(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int len = nums.length;
        int MaxSum = 0, ThisSum = 0;
        for (int i = 0; i < len; i++) {
            ThisSum += nums[i];
            if (ThisSum > MaxSum) {
                MaxSum = ThisSum;
            } else if (ThisSum < 0) {
                ThisSum = 0;
            }
        }
        printStr(MaxSum);
    }

    public static void printStr(int sum) {
        System.out.println("结果=" + sum);
    }


    public static int[][] makeNN(int count) {
        int[][] a = new int[count][count];
        final int left = 1, right = 2, top = 3, down = 4;
        int type = right;
        int width = count;
        int height = count;

        int x=0;
        int y=0;
        for (int i = 1; i <=count * count; i++) {
            switch (type) {
                case right:
                    a[x][y]=i;
                    y++;
                    if(y==width){
                        y--;
                        x++;
                        type=down;
                    }
                    break;
                case down:
                    a[x][y]=i;
                    x++;
                    if(x==height){
                        x--;
                        y--;
                        height--;
                        type=left;
                    }
                    break;
                case left:
                    a[x][y]=i;
                    y--;
                    if(y<count-width){
                        x--;
                        y++;
                        width--;
                        type=top;
                    }
                    break;
                case top:
                    a[x][y]=i;
                    x--;
                    if(x<count-height){
                        x++;
                        y++;
                        type=right;
                    }
                    break;
            }
        }
        return a;
    }

//    private void count(int num) {
//        int colNum[][] = new int[num][num];
//        final int RIGHT = 0x0, DOWN = 0x1, LEFT = 0x2, UP = 0x3;
//        int type, width, height, x, y;
//        type = RIGHT;
//        width = height = num;
//        x = y = 0;
//        for (int i = 1; i <= num * num; i++) {
//            switch (type) {
//                case RIGHT:
//                    colNum[x][y] = i;
//                    y++;
//                    if (y == width) {
//                        y--;
//                        x++;
//                        type = DOWN;
//                    }
//                    break;
//                case DOWN:
//                    colNum[x][y] = i;
//                    x++;
//                    if (x == height) {
//                        x--;
//                        y--;
//
//                        height--;
//
//                        type = LEFT;
//                    }
//                    break;
//                case LEFT:
//                    colNum[x][y] = i;
//                    y--;
//                    if (y < num - width) {
//                        y++;
//                        x--;
//                        width--;
//                        type = UP;
//                    }
//                    break;
//                case UP:
//                    colNum[x][y] = i;
//                    x--;
//                    if (x < num - height) {
//                        x++;
//                        y++;
//                        type = RIGHT;
//                    }
//                    break;
//                default:
//                    break;
//            }
//        }
//
//    }

    /**
     * 打印二维数组
     * */
    public static void printArray2(int[][] nums){
        if(nums==null||nums.length==0)return;
        int len=nums.length;
        for(int i=0;i<len;i++){
            int col=nums[i].length;
            for(int j=0;j<col;j++){
                System.out.print(nums[i][j]+"");
            }
            System.out.println("");
        }
    }

    /*
    * 链表反转*/
//    public ListNode reverseList(ListNode head) {
//        if(head==null||head.next==null)return null;
//        ListNode pre=null,tmp;
//
//        while(head!=null){
//            tmp=head.next;
//            head.next=pre;
//            pre=head;
//            head=tmp;
//        }
//        return pre;
//    }


    /**
     * 合并两个有序数组*/
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if(nums1==null)return ;
        if(nums2==null)return ;
        int i=0;
        int j=0;
        int []a=new int[m+n];
        while(i<m&&j<n){
            if(nums1[i]<=nums2[j]){
                a[i+j]=nums1[i];
                i++;
            }else{
                a[i+j]=nums2[j];
                j++;
            }
        }
        if(i<=m-1){
            for(int k=i+j;k<m+n;k++){
                a[i+j]=nums1[i++];
            }
        }
        if(j<=n-1){
            for(int k=i+j;k<m+n;k++){
                a[i+j]=nums2[j++];
            }
        }
        TestSort.printResult(a,"合并两个有序数组");

    }

    /*
      斐波拉起
    * */
    public static int fib(int n){
        if(n==0)return 0;
        if(n==1)return 1;
        return fib(n-1)+fib(n-2);
    }


//    public void reverse(ListNode head){
//        if(head==null||head.next==null)return head;
//        ListNode pre=temp=null;
//        while(head!=null){
//            temp=head.next;
//            head.next=pre;
//            pre=head;
//            head=tmp;
//        }
//        return pre;
//    }
}
