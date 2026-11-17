import java.util.Scanner;
public class MatchingAlgorithms{
    // KMP Algorithm
    static void KMPSearch(String pattern, String text) {
        int m = pattern.length();
        int n = text.length();
        int[] lps = new int[m];
        computeLPSArray(pattern, m, lps);
        int i = 0;         int j = 0;
        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;                j++;
            }
            if (j == m) {
                System.out.println("KMP: Pattern found at index " + (i - j));
                j = lps[j - 1];
            }
            else if (i < n &&
                    pattern.charAt(j) != text.charAt(i)) {
                if (j != 0)
                    j = lps[j - 1];
                else
                    i++;
            }
        }
    }
  static void computeLPSArray(String pattern, int m, int[] lps) {
        int len = 0;
        lps[0] = 0;
        int i = 1;
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0)
                    len = lps[len - 1];
                else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }
  // Rabin-Karp Algorithm
    static void rabinKarp(String pattern, String text, int q) {
        int d = 256;
        int m = pattern.length();
        int n = text.length();
        int p = 0;
        int t = 0;
        int h = 1;
        for (int i = 0; i < m - 1; i++)
            h = (h * d) % q;
        for (int i = 0; i < m; i++) {
            p = (d * p + pattern.charAt(i)) % q;
            t = (d * t + text.charAt(i)) % q;
        }
        for (int i = 0; i <= n - m; i++) {
            if (p == t) {
                int j;
                for (j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j))
                        break;
                }
                if (j == m)
                    System.out.println("Rabin-Karp: Pattern found at index " + i);
            }
            if (i < n - m) {
                t = (d * (t - text.charAt(i) * h)
                        + text.charAt(i + m)) % q;
                if (t < 0)
                    t = (t + q);
            }
        }
    }
   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.print("Enter pattern: ");
        String pattern = sc.nextLine();
        System.out.println();
        KMPSearch(pattern, text);
        rabinKarp(pattern, text, 101);
        sc.close();
   }
}