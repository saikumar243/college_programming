//Program:
class AVLTree {
    class Node {
        int key, height;
        Node left, right;
        Node(int key) {
            this.key = key;
            height = 1;
        }
    }
    Node root;
    int height(Node node) {
        return node == null ? 0 : node.height;
    }
    int max(int a, int b) {
        return a > b ? a : b;
    }
    int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
        return x;
    }
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        return y;
    }
    Node insert(Node node, int key) {
        if (node == null)
            return new Node(key);
        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;
        node.height = 1 + max(height(node.left), height(node.right));
        int balance = getBalance(node);
        if (balance > 1 && key < node.left.key) {
            System.out.println("LL Rotation");
            return rightRotate(node);
        }
        if (balance < -1 && key > node.right.key) {
            System.out.println("RR Rotation");
            return leftRotate(node);
        }
        if (balance > 1 && key > node.left.key) {
            System.out.println("LR Rotation");
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key < node.right.key) {
            System.out.println("RL Rotation");
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }
    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }
    Node delete(Node node, int key) {
        if (node == null)
            return null;
        if (key < node.key)
            node.left = delete(node.left, key);
        else if (key > node.key)
            node.right = delete(node.right, key);
        else {
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;
            Node temp = minValueNode(node.right);
            node.key = temp.key;
            node.right = delete(node.right, temp.key);
        }
        node.height = 1 + max(height(node.left), height(node.right));
        int balance = getBalance(node);
        if (balance > 1 && getBalance(node.left) >= 0) {
            System.out.println("LL Rotation");
            return rightRotate(node);
        }
        if (balance > 1 && getBalance(node.left) < 0) {
            System.out.println("LR Rotation");
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && getBalance(node.right) <= 0) {
            System.out.println("RR Rotation");
            return leftRotate(node);
        }
        if (balance < -1 && getBalance(node.right) > 0) {
            System.out.println("RL Rotation");
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }
    void printTree(Node node) {
        if (node != null) {
            printTree(node.left);
            System.out.print(node.key + " ");
            printTree(node.right);
        }
    }
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        int[] insertValues = {30, 20, 10, 25, 28, 40, 50};
        for (int value : insertValues) {
            System.out.println("\nInsert: " + value);
            tree.root = tree.insert(tree.root, value);
            System.out.print("Tree: ");
            tree.printTree(tree.root);
            System.out.println();
        }
        int[] deleteValues = {50, 40, 30};
        for (int value : deleteValues) {
            System.out.println("\nDelete: " + value);
            tree.root = tree.delete(tree.root, value);
            System.out.print("Tree: ");
            tree.printTree(tree.root);
            System.out.println();
        }
    }
}
