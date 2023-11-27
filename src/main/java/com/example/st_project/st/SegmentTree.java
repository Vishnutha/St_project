package com.example.st_project.st;

import java.io.*;
import java.util.*;

public class SegmentTree {

    static class Node {
        long sum;
        int mx, mn;
        long andd;

        Node() {
            sum = 0;
            mx = -MX;
            mn = MX;
            andd = (1L << 32) - 1;
        }

        Node(int val) {
            sum = val;
            mx = val;
            mn = val;
            andd = val;
        }

        void merge(Node left, Node right) {
            sum = left.sum + right.sum;
            mx = Math.max(left.mx, right.mx);
            mn = Math.min(left.mn, right.mn);
            andd = left.andd & right.andd;
        }
    }

    static class Update {
        int val;

        Update() {
            val = 0;
        }

        Update(int v) {
            val = v;
        }

        void apply(Node node) {
            node.sum = val;
            node.mn = val;
            node.mx = val;
            node.andd = val;
        }
    }

    static class Segment_Tree<T> {

        private int size = 0;
        private Node[] seg;

        Segment_Tree() {

        }

        Segment_Tree(int n) {
            size = n;
            seg = new Node[4 * size + 1];
            for (int i = 0; i < seg.length; i++) {
                seg[i] = new Node();
            }
        }

        Segment_Tree(List<T> arr) {
            size = arr.size();
            seg = new Node[4 * size + 1];
            for (int i = 0; i < seg.length; i++) {
                seg[i] = new Node();
            }
            build(arr);
        }

        private void build(int start, int end, int ind, List<T> arr) {
            if (start == end) {
                seg[ind] = new Node((int) arr.get(start));
                return;
            }
            int mid = (start + end) / 2;
            int leftInd = 2 * ind + 1, rightInd = 2 * ind + 2;
            build(start, mid, leftInd, arr);
            build(mid + 1, end, rightInd, arr);
            seg[ind].merge(seg[leftInd], seg[rightInd]);
        }

        void build(List<T> arr) {
            build(0, size - 1, 0, arr);
        }

        private Node query(int start, int end, int ind, int left, int right) {
            if (start > right || end < left) {
                return new Node();
            }
            if (start >= left && end <= right) {
                return seg[ind];
            }
            int mid = (start + end) / 2;
            int leftInd = 2 * ind + 1, rightInd = 2 * ind + 2;
            Node res = new Node();
            Node leftItem = query(start, mid, leftInd, left, right);
            Node rightItem = query(mid + 1, end, rightInd, left, right);
            res.merge(leftItem, rightItem);
            return res;
        }

        Node query(int left, int right) {
            return query(0, size - 1, 0, left, right);
        }

        private void update(int start, int end, int ind, int index, Update u) {
            if (start == end) {
                u.apply(seg[ind]);
                return;
            }
            int mid = (start + end) / 2;
            int leftInd = 2 * ind + 1, rightInd = 2 * ind + 2;
            if (index <= mid) update(start, mid, leftInd, index, u);
            else update(mid + 1, end, rightInd, index, u);
            seg[ind].merge(seg[leftInd], seg[rightInd]);
        }

        void update(int index, int value) {
            Update u = new Update(value);
            update(0, size - 1, 0, index, u);
        }
    }

    static final int MX = (int) 1e9;

    static ArrayList<Integer> solve(int n , int q, Long [] a, int l[] , int limits[]) {

        List<Long> arr = new ArrayList<>(Arrays.asList(a));
        Segment_Tree<Long> sg = new Segment_Tree<>(arr);
        ArrayList<Integer> ansList = new ArrayList<>();
        int i = 0;
        while (i++ < q) {
            int left = l[i];
            int limit = limits[i];
            left--;

            int start = left, end = n - 1;
            int ans = -1;

            while (start <= end) {
                int mid = (start + end) / 2;
                long andd = sg.query(left, mid).andd;
                boolean check = andd >= limit;
                if (check) {
                    ans = mid;
                    start = mid + 1;
                } else
                    end = mid - 1;
            }
            ansList.add( (ans == -1 )? -1 : ans + 1);

        }
        return ansList;
    }

//    public static void main(String[] args) throws IOException {
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//
//        int t = Integer.parseInt(reader.readLine().trim());
//
//        for (int _t = 1; _t <= t; _t++) {
//            solve();
//        }
//    }
}