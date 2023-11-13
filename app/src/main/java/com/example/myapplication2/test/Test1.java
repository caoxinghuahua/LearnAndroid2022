package com.example.myapplication2.test;


import java.util.ArrayList;
import java.util.List;

import static com.example.myapplication2.test.Test2.*;//静态导包，不需要ClassName

public class Test1 {


    public static void main(String[] args) {
        int[] n = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(getMaxArray(n));

//        //多态研究
//        ParentClass parentClass = new ChildClass();
//        System.out.println("id:" + parentClass.id);
//        parentClass.show();
//
//
//        //算法，两个字符数组去重
//        char[] a = {'h', 'e', 'l', 'l', 'o', 'w', 'o', 'r', 'l', 'd'};
//        char[] b = {'e', 'o'};
//        diffAB(a, b);
//        //静态导包，不需要ClassName
//        print();
//        //对象clone
//        Test2 test2 = new Test2();
//        try {
//            test2.clone();
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }


        drinkCount(60);
        System.out.println("喝"+sodaWater(30,0,0));

    }
//    给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

    public static int getMaxArray(int[] nums) {
        if (nums.length == 0) return 0;

        int min = nums[0];
        int sum = min;
        for (int i = 1; i < nums.length; i++) {
            if (min >= 0) {
                min += nums[i];
            } else {
                min = nums[i];

            }
            sum = Math.max(min, sum);
        }
//        int sum=nums[0],res=sum;
//        for(int i=1;i<nums.length;i++)
//        {
//            if(sum<0)
//                sum=nums[i];
//            else sum+=nums[i];
//            res= Math.max(res,sum);
//        }
//
//        return res;
//        final int a = 10;
//        class My {
//            public void my() {
//                System.out.println("a:" + a);
//            }
//        }
//        new My().my();
        return sum;
    }

//
//    public static int test(int num[]){
//        if(num==null||num.length==0)return 0;
//        int sum=num[0];
//        int res=sum;
//        for(int i=1;i<=num.length-1;i++){
//            if(sum>=0){
//                sum+=num[i];
//            }else{
//                sum=num[i];
//            }
//            res =Math.max(sum,res);
//        }
//        return res;
//    }

    public static void diffAB(char[] a, char[] b) {
        if (a == null || b == null || a.length == 0 || b.length == 0) return;
        List<Character> list = new ArrayList<Character>();
        StringBuffer buffer = new StringBuffer();
        for (Character s : b) {
            list.add(s);
        }

        for (Character s : a) {
            if (!list.contains(s)) {
                buffer.append(s);
            }
        }
        System.out.println(buffer.toString());

    }

    //链表反转
//    public static Node<Integer> headInsertReserveLink(Node<Integer> head){
//        if(head==null||head.next==null){
//            return head;
//        }
//        Node<Integer> temp = null,newHead=null;
//        while(head!=null){
//            temp=head;
//            head=head.next;
//            temp.next=newHead;
//            newHead =temp;
//        }
//        return  newHead;
//    }

    //链表找环
//    public boolean hasCycle(ListNode head) {
//        if(head == null || head.next == null) return false;
//        ListNode slow = head;
//        ListNode fast = head;
//        while(fast!= null && fast.next != null){
//            slow = slow.next;
//            fast = fast.next.next;
//            if(slow == fast) return true;
//        }
//        return false;
//    }


    /**
     * 2元喝一瓶汽水，四个瓶盖可以喝一瓶汽水，2个瓶子可以喝一瓶
     * 10元能喝几瓶
     */

    public static int drinkCount(int monery) {
        int pzCount = monery / 2;
        int count = 0;

        //饮料数
        int m = pzCount;
        //当前剩下空瓶子数
        int n = 0;
        //瓶盖数
        int p = 0;



        while (true) {

            //喝一次
            if (m > 0) {
                count += m;
                n += m;
                p += m;
                m=0;
            }

            m = n / 2 + p / 4;
            n = n % 2;
            p = p % 4;
            if (m == 0) {
                break;
            }
        }
        System.out.printf("%d元可以喝%d瓶饮料", monery, count);
        return count;
    }

    private static int sodaWater(int n, int bottle, int cap) {

// 兑换剩下的空瓶数

        bottle %= 2;

// 喝完饮料剩下的空瓶

        bottle += n;

// 兑换剩下的瓶盖数

        cap %= 4;

// 喝完饮料剩下的瓶盖

        cap += n;

        if (bottle < 2 && cap <4) { // 如果空瓶数<2 并且 瓶盖数<4, 那么停止兑换

            return n;

        } else {

            return n + sodaWater(bottle / 2 + cap / 4, bottle, cap);

        }


    }


    /**
     * n饮料数，
     * bottle空瓶数
     * 瓶盖数
     * */
    public static int testWater(int n,int bottle,int cap){
        bottle%=2;
        bottle+=n;
        cap%=4;
        cap+=n;
        if(bottle<2||cap<4){
            return n;
        }else {
            return n+testWater(bottle/2+cap/4,bottle,cap);
        }
    }

}
