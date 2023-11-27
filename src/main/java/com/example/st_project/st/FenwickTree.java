package com.example.st_project.st;

import java.util.ArrayList;

public class FenwickTree {
    private int[] tree;

    public FenwickTree(int size) {
        this.tree = new int[size + 1];
    }

    public void update(int i, int delta) {
        i++;
        while (i < tree.length) {
            tree[i] += delta;
            i += (i & -i);
        }
    }

    public int query(int i) {
        i++;
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= (i & -i);
        }
        return sum;
    }

    public int rangeQuery(int left, int right) {
        if (left == 0) {
            return query(right);
        } else {
            return query(right) - query(left - 1);
        }
    }

    // The solve function encapsulating the logic
    public static ArrayList<Integer> solve(int[] array) {
        FenwickTree fenwickTree = new FenwickTree(array.length);

        // Build the Fenwick Tree
        for (int i = 0; i < array.length; i++) {
            fenwickTree.update(i, array[i]);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        // Query and print results
        ans.add( fenwickTree.query(2));
        ans.add(fenwickTree.query(5));
        ans.add(fenwickTree.query(7));
        ans.add( fenwickTree.rangeQuery(2, 5));
        fenwickTree.rangeQuery(0, 5);

        return ans;
    }

//    public static void main(String[] args) {
//
//      ArrayList<Integer> qs = solve(array);
//        for(int i = 0;i<qs.size();i++)
//        {
//            System.out.println(qs.get(i));
//        }
//    }
}