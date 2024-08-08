package com.xiaou.xiaoueasyprojectbackend.module.support.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/two-sum
 * 题目描述
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 */
public class A1 {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0;; ++i) {
            int x = nums[i];
            int y = target - x;
            if (m.containsKey(y)) {
                return new int[] {m.get(y), i};
            }
            m.put(x, i);
        }
    }

    public static void main(String[] args) {
        twoSum(new int[]{2, 7, 11, 15}, 9);
    }
}
