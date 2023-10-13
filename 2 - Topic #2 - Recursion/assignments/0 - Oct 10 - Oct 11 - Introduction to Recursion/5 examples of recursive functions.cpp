#include <iostream>

using namespace std;

class RecursiveFunctions {
public:
    // Function 1: Factorial
    short factorial(short n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    // Function 2: Fibonacci sequence
    short fibonacci(short n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
    
    // Function 3: Hailstone Sequence
    short hailstone(short n) {
        // Base case: n is 1
        if (n == 1) {
            return 1;
        }
        // Recursive case: n is even
        else if (n % 2 == 0) {
            cout << n << " ";
            return hailstone(n / 2);
        }
        // Recursive case: n is odd
        else {
            cout << n << " ";
            return hailstone(3 * n + 1);
        }
    }

    // Function 4: Greatest common divisor (Euclid's algorithm)
    short gcd(short a, short b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    // Function 5: Merge sort
    void merge(short arr[], short l, short m, short r) {
        short i, j, k;
        short n1 = m - l + 1;
        short n2 = r - m;

        short L[n1], R[n2];

        for (i = 0; i < n1; i++) {
            L[i] = arr[l + i];
        }
        for (j = 0; j < n2; j++) {
            R[j] = arr[m + 1 + j];
        }

        i = 0;
        j = 0;
        k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    void mergeSort(short arr[], short l, short r) {
        if (l < r) {
            short m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }
};
