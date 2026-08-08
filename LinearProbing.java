import java.util.Scanner;
public class LinearProbing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter hash table size: ");
        int size = sc.nextInt();
        int[] hashTable = new int[size];
        // Initialize table with -1
        for (int i = 0; i < size; i++) {
            hashTable[i] = -1;
        }
        System.out.print("Enter number of keys: ");
        int n = sc.nextInt();
        System.out.println("Enter the keys:");
        for (int i = 0; i < n; i++) {
            int key = sc.nextInt();
            int index = key % size;
            // Linear Probing
            while (hashTable[index] != -1) {
                index = (index + 1) % size;
            }
            hashTable[index] = key;
        }
        // Display Hash Table
        System.out.println("\nHash Table:");
        for (int i = 0; i < size; i++) {
            System.out.println(i + " --> " + hashTable[i]);
        }
        sc.close();
    }
}
