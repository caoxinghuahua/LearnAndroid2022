package com.example.myapplication2.test;


import java.util.ArrayList;
import java.util.List;

public class Test1 {


    public static void main(String[] args) {
      int[] n= {-2,1,-3,4,-1,2,1,-5,4};
      System.out.println(getMaxArray(n));


      //多态研究
        ParentClass parentClass=new ChildClass();
        System.out.println("id:"+parentClass.id);
        parentClass.show();


      //算法，两个字符数组去重
        char[] a={'h','e','l','l','o','w','o','r','l','d'};
        char[] b={'e','o'};
        diffAB(a,b);

    }
//    给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

    public static int getMaxArray(int[] nums){
       if(nums.length==0)return 0;

       int min=nums[0];
       int sum=min;
       for(int i=1;i<nums.length;i++){
           if(min>=0){
               min+=nums[i];
           }else{
               min=nums[i];

           }
           sum=Math.max(min,sum);
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
        final int a=10;
        class My{
           public void my(){
               System.out.println("a:"+a);
           }
        }
        new My().my();
        return sum;
    }
    public static void diffAB(char[] a,char[] b){
        if(a==null||b==null||a.length==0||b.length==0)return;
        List<Character>list=new ArrayList<Character>();
        StringBuffer buffer=new StringBuffer();
        for(Character s:b){
           list.add(s);
        }

        for(Character s:a){
            if(!list.contains(s)){
                buffer.append(s);
            }
        }
        System.out.println(buffer.toString());

    }

}
