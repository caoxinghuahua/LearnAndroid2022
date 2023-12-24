package com.example.myapplication2.test;

import java.util.HashMap;

public class TestSf {
        public static void main(String[] args) {
                int a[]={2,11,7,15};
                int b[]=getIndex(a,13);
                System.out.println(b[0]+","+b[1]);
        }

        public static int[] getIndex(int []nums,int target){
                int m[]=new int[2];
                if(nums==null||nums.length==0) return null;
                HashMap<Integer,Integer>  map=new HashMap<>();
                for(int i=0;i<nums.length;i++){
                    if(!map.containsKey(target-nums[i])){
                          map.put(nums[i],i);
                    }else{
                         m[0] =i;
                         m[1]=map.get(target-nums[i]);
                    }
                }
                return m;
        }
}
