package com.example.st_project.st;

import java.util.Random;
import java.util.Scanner;

public class Treap {

    static class Node {
        long x;
        int y, cnt, sum, push;
        Node l, r;

        public Node() {
        }

        public Node(long x, int y, int cnt, int sum, int push, Node l, Node r) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.sum = sum;
            this.push = push;
            this.l = l;
            this.r = r;
        }
    }

    static class Pair {
        Node first, second;

        public Pair(Node first, Node second) {
            this.first = first;
            this.second = second;
        }
    }

    static Node fix(Node t) {
        if (t == null) return t;
        int p = t.push;
        if (p != 0) {
            if (t.l != null) t.l.push += p;
            if (t.r != null) t.r.push += p;
            t.x += p;
            t.push = 0;
        }
        t.sum = getSum(t.l) + t.cnt + getSum(t.r);
        return t;
    }

    static int getSum(Node t) {
        return (t != null) ? t.sum : 0;
    }

    static int getCnt(Node t) {
        return (t != null) ? t.cnt : 0;
    }

    static Pair splitKey(Node t, long x) {
        t = fix(t);
        if (t == null) return new Pair(null, null);
        if (t.x < x) {
            Pair p = splitKey(t.r, x);
            t.r = p.first;
            return new Pair(fix(t), p.second);
        } else {
            Pair p = splitKey(t.l, x);
            t.l = p.second;
            return new Pair(p.first, fix(t));
        }
    }

    static long kthMax(Node t, int k) {
        t = fix(t);
        if (t == null) return -1;
        int sumR = getSum(t.r);
        if (sumR >= k) return kthMax(t.r, k);
        if (sumR + t.cnt >= k) return t.x;
        return kthMax(t.l, k - sumR - t.cnt);
    }

    static int getCntByKey(Node t, long x) {
        t = fix(t);
        if (t == null) return 0;
        if (t.x == x) return t.cnt;
        if (t.x > x) return getCntByKey(t.l, x);
        return getCntByKey(t.r, x);
    }

    static Node increaseKey(Node t, long x, int cnt) {
        t = fix(t);
        if (t == null) return t;
        if (t.x == x) {
            t.cnt += cnt;
        } else if (t.x > x) {
            t.l = increaseKey(t.l, x, cnt);
        } else {
            t.r = increaseKey(t.r, x, cnt);
        }
        return fix(t);
    }

    static Node merge(Node a, Node b) {
        a = fix(a);
        b = fix(b);
        if (a == null) return b;
        if (b == null) return a;
        if (a.y > b.y) {
            a.r = merge(a.r, b);
            return fix(a);
        } else {
            b.l = merge(a, b.l);
            return fix(b);
        }
    }

    static boolean hasKey(Node t, long x) {
        t = fix(t);
        if (t == null) return false;
        if (t.x == x) return true;
        if (t.x < x) return hasKey(t.r, x);
        return hasKey(t.l, x);
    }

    static Node insert(Node t, long x, int y, int cnt) {
        t = fix(t);
        if (t == null || t.y < y) {
            Pair p = splitKey(t, x);
            Node res = new Node(x, y, cnt, cnt, 0, p.first, p.second);
            return fix(res);
        }
        if (t.x > x)
            t.l = insert(t.l, x, y, cnt);
        else
            t.r = insert(t.r, x, y, cnt);
        return fix(t);
    }

    static Node insertMain(Node t, long x, int cnt) {
        if (hasKey(t, x))
            return increaseKey(t, x, cnt);
        else
            return insert(t, x, new Random().nextInt(), cnt);
    }

    static Node eraseKey(Node t, long x) {
        t = fix(t);
        if (t == null) return null;
        if (t.x > x) {
            t.l = eraseKey(t.l, x);
            return fix(t);
        }
        if (t.x < x) {
            t.r = eraseKey(t.r, x);
            return fix(t);
        }
        Node tnew = merge(t.l, t.r);
        return tnew;
    }

    static Pair splitSum(Node t, int k) {
        t = fix(t);
        if (t == null) return new Pair(null, null);
        if (k == 0) return new Pair(t, null);
        long x = kthMax(t, k);
        int cnt = getCntByKey(t, x);
        t = eraseKey(t, x);
        Pair p = splitKey(t, x);
        k -= getSum(p.second);
        if (k > 0) p.second = merge(new Node(x, new Random().nextInt(), k, k, 0, null, null), p.second);
        if (k < cnt) p.first = merge(p.first, new Node(x, new Random().nextInt(), cnt - k, cnt - k, 0, null, null));
        return p;
    }

    static long minKey(Node t) {
        t = fix(t);
        if (t == null) return (long) 1e18;
        if (t.l != null) return minKey(t.l);
        return t.x;
    }

    static Node decreaseUpToKLargest(Node t, int k, int[] res) {
        if (k == 0) return t;
        t = fix(t);
        k = Math.min(k, getSum(t));
        res[0] = k;
        Pair p = splitSum(t, k);
        if (p.second != null) {
            p.second.push--;
            for (int i = 0; i < 2; i++) {
                long x = minKey(p.second);
                int cntMin = getCntByKey(p.second, x);
                p.second = eraseKey(p.second, x);
                if (x != 0 && cntMin != 0)
                    p.first = insertMain(p.first, x, cntMin);
            }
        }
        return merge(p.first, p.second);
    }

    static void destroy(Node t) {
        if (t == null) return;
        if (t.l != null) destroy(t.l);
        if (t.r != null) destroy(t.r);
    }

    static void solve() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine(); // consume the newline after reading n
        String s = scanner.nextLine();
        long[] r = new long[n];
        long[] b = new long[n];
        for (int i = 0; i < n; i++) r[i] = scanner.nextLong();
        for (int i = 0; i < n; i++) b[i] = scanner.nextLong();
        long sum = 0;
        for (int i = 0; i < n; i++) sum += r[i] + b[i];
        long flow = 0;
        Node t = null;
        for (int i = 0; i < n; i++) {
            long d = Math.min(r[i], b[i]);
            flow += d;
            r[i] -= d;
            b[i] -= d;
            if (s.charAt(i) == '0') {
                int add = 0;
                if (r[i] > 1e9) r[i] = (long) 1e9;
                int[] addArray = {add};
                t = decreaseUpToKLargest(t, (int) r[i], addArray);
                add = addArray[0];
                flow += add;
            } else {
                if (r[i] != 0) t = insertMain(t, r[i], 1);
            }
        }
        System.out.println(sum - flow);
    }
}
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int t = scanner.nextInt();
//        for (int i = 0; i < t; i++) solve();
//    }
//}
