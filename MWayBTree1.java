//Program:
import java.util.Scanner;
public class MWayBTree1{
    static int m;
    static Node root;
    // Insert
    static void insert(int x) {
        // Special case for m = 2
        if (m == 2) {
            insertBST(x);
            return;
        }
        // Empty tree
        if (root == null) {
            root = new Node(m, true);
            root.key[0] = x;
            root.n = 1;
            return;
        }
        // Root is full
        if (root.n == m - 1) {
            Node newRoot = new Node(m, false);
            newRoot.child[0] = root;
            split(newRoot, 0);
            root = newRoot;
        }
        insertNonFull(root, x);
    }
    // Insertion for m = 2
    static void insertBST(int x) {
        if (root == null) {
            root = new Node(2, true);
            root.key[0] = x;
            root.n = 1;
            return;
        }
        Node p = root;
        while (true) {
            if (x == p.key[0]) {
                System.out.println("Duplicate value");
                return;
            }
            if (x < p.key[0]) {
                if (p.child[0] == null) {
                    p.child[0] = new Node(2, true);
                    p.child[0].key[0] = x;
                    p.child[0].n = 1;
                    return;
                }
                p = p.child[0];
            } else {
                if (p.child[1] == null) {
                    p.child[1] = new Node(2, true);
                    p.child[1].key[0] = x;
                    p.child[1].n = 1;
                    return;
                }
                p = p.child[1];
            }
        }
    }
    // Insert into a non-full node
    static void insertNonFull(Node p, int x) {
        int i = p.n - 1;
        if (p.leaf) {
            while (i >= 0 && x < p.key[i]) {
                p.key[i + 1] = p.key[i];
                i--;
            }
            if (i >= 0 && x == p.key[i]) {
                System.out.println("Duplicate value");
                return;
            }
            p.key[i + 1] = x;
            p.n++;
        }
        else {
            while (i >= 0 && x < p.key[i])
                i--;
            i++;
            if (i < p.n && x == p.key[i]) {
                System.out.println("Duplicate value");
                return;
            }
            // Split full child
            if (p.child[i].n == m - 1) {
                split(p, i);
                if (x > p.key[i])
                    i++;
                else if (x == p.key[i]) {
                    System.out.println("Duplicate value");
                    return;
                }
            }
            insertNonFull(p.child[i], x);
        }
    }
    // Split a full node
    static void split(Node parent, int pos) {
        Node old = parent.child[pos];
        int mid = (m - 1) / 2;
        Node right = new Node(m, old.leaf);
        int middleKey = old.key[mid];
        // Move keys to right node
        int j = 0;
        for (int i = mid + 1; i < old.n; i++) {
            right.key[j] = old.key[i];
            j++;
        }
        right.n = j;
        // Move children
        if (!old.leaf) {
            j = 0;
            for (int i = mid + 1; i <= old.n; i++) {
                right.child[j] = old.child[i];
                j++;
            }
        }
        // Left node keeps keys before middle
        old.n = mid;
        // Shift children of parent
        for (int i = parent.n; i >= pos + 1; i--)
            parent.child[i + 1] = parent.child[i];
        parent.child[pos + 1] = right;
        // Shift keys of parent
        for (int i = parent.n - 1; i >= pos; i--)
            parent.key[i + 1] = parent.key[i];
        parent.key[pos] = middleKey;
        parent.n++;
    }
    // Display
    static void display(Node p, int level) {
        if (p == null)
            return;
        System.out.print("Level " + level + ": ");
        for (int i = 0; i < p.n; i++)
            System.out.print(p.key[i] + " ");
        System.out.println();
        if (m == 2) {
            display(p.child[0], level + 1);
            display(p.child[1], level + 1);
        } else {
            for (int i = 0; i <= p.n; i++)
                display(p.child[i], level + 1);
        }
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter m (2, 4 or 6): ");
        m = sc.nextInt();
        if (m != 2 && m != 4 && m != 6) {
            System.out.println("Please enter only 2, 4 or 6.");
            return;
        }
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            insert(x);
        }
        System.out.println("\nM-Way B-Tree:");
        display(root, 0);
        sc.close();
    }
}
class Node {
    int[] key;
    Node[] child;
    int n;
    boolean leaf;
    Node(int m, boolean leaf) {
        key = new int[m];
        child = new Node[m + 1];
        n = 0;
        this.leaf = leaf;
    }
}
