//Program:
import java.util.Scanner;
public class HeapOperations {
    static int[] heap = new int[20];
    static int size = 0;
    static void insert(int value) {
        heap[size] = value;
        int current = size;
        size++;
        while (current > 0) {
            int parent = (current - 1) / 2;
            if (heap[current] > heap[parent]) {
                int temp = heap[current];
                heap[current] = heap[parent];
                heap[parent] = temp;
                current = parent;
            } else {
                break;
            }
        }
    }
    static void heapify(int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < size && heap[left] > heap[largest])
            largest = left;
        if (right < size && heap[right] > heap[largest])
            largest = right;
        if (largest != i) {
            int temp = heap[i];
            heap[i] = heap[largest];
            heap[largest] = temp;
            heapify(largest);
        }
    }
   static void deleteMax() {
        if (size == 0) {
            System.out.println("Heap is Empty");
            return;
        }
        System.out.println("Deleted Element : " + heap[0]);
        heap[0] = heap[size - 1];
        size--;
        heapify(0);
    }
    static void display() {
        if (size == 0) {
            System.out.println("Heap is Empty");
            return;
        }
        System.out.print("Heap Elements : ");
        for (int i = 0; i < size; i++)
            System.out.print(heap[i] + " ");
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice, value;
        do {
            System.out.println("\n===== MAX HEAP OPERATIONS =====");
            System.out.println("1. Insert");
            System.out.println("2. Delete Maximum");
            System.out.println("3. Display");
            System.out.println("4. Exit");
            System.out.print("Enter your choice : ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter element : ");
                    value = sc.nextInt();
                    insert(value);
                    break;
                case 2:
                    deleteMax();
                    break;
                case 3:
                    display();
                    break;
                case 4:
                    System.out.println("Program Terminated.");
                    break;
                default:
                    System.out.println("Invalid Choice.");
            }
        } while (choice != 4);
        sc.close();
    }
}
