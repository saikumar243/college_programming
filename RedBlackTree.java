class RedBlackTree {

    static final boolean RED = true;
    static final boolean BLACK = false;

    class Node {
        String data;
        boolean color;
        Node left, right, parent;

        Node(String data) {
            this.data = data;
            color = RED;
        }
    }

    Node root;

    // Insert
    void insert(String data) {
        Node n = new Node(data);

        if (root == null) {
            root = n;
            root.color = BLACK;
            return;
        }

        Node p = root;

        while (true) {
            if (data.compareTo(p.data) < 0) {
                if (p.left == null) {
                    p.left = n;
                    n.parent = p;
                    break;
                }
                p = p.left;
            } else {
                if (p.right == null) {
                    p.right = n;
                    n.parent = p;
                    break;
                }
                p = p.right;
            }
        }

        // Simple balancing
        if (n.parent.color == RED)
            n.parent.color = BLACK;
    }

    // Search
    Node search(String data) {
        Node p = root;

        while (p != null) {
            if (p.data.equals(data))
                return p;

            if (data.compareTo(p.data) < 0)
                p = p.left;
            else
                p = p.right;
        }
        return null;
    }

    // Delete
    void delete(String data) {
        Node n = search(data);

        if (n == null) {
            System.out.println(data + " not found");
            return;
        }

        if (n.left == null && n.right == null) {
            if (n.parent == null)
                root = null;
            else if (n == n.parent.left)
                n.parent.left = null;
            else
                n.parent.right = null;
        } else if (n.left == null) {
            replace(n, n.right);
        } else if (n.right == null) {
            replace(n, n.left);
        } else {
            Node s = n.right;

            while (s.left != null)
                s = s.left;

            n.data = s.data;
            replace(s, s.right);
        }

        System.out.println(data + " deleted");
    }

    void replace(Node a, Node b) {
        if (a.parent == null)
            root = b;
        else if (a == a.parent.left)
            a.parent.left = b;
        else
            a.parent.right = b;

        if (b != null)
            b.parent = a.parent;
    }

    // Inorder
    void display(Node n) {
        if (n != null) {
            display(n.left);
            System.out.print(n.data + " ");
            display(n.right);
        }
    }

    public static void main(String[] args) {

        RedBlackTree t = new RedBlackTree();

        String[] data = {
            "Apple", "Mango", "Banana", "Orange", "Grapes"
        };

        System.out.println("After Insertion:");
        for (String s : data)
            t.insert(s);

        t.display(t.root);

        System.out.println("\n\nAfter Deletion:");
        t.delete("Banana");
        t.display(t.root);
    }
}
