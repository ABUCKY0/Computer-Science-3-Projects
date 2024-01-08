#include <iostream>

using namespace std;

/**
 * @class RecursiveFunctions
 * @brief A class that implements various recursive functions.
 */
class RecursiveFunctions
{
public:
    /**
     * @brief Calculate the factorial of a number.
     * @param n The number to calculate the factorial of.
     * @return The factorial of the number.
     * @example factorial(5) returns 120.
     * @time_complexity O(n), where n is the input number.
     */
    short factorial(short n)
    {
        if (n == 0)
        {
            return 1;
        }
        else
        {
            return n * factorial(n - 1);
        }
    }

    /**
     * @brief Calculate the nth number in the Fibonacci sequence.
     * @param n The position in the Fibonacci sequence to calculate.
     * @return The nth number in the Fibonacci sequence.
     * @example fibonacci(7) returns 13.
     * @time_complexity O(2^n), where n is the input number.
     */
    short fibonacci(short n)
    {
        if (n <= 1)
        {
            return n;
        }
        else
        {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    /**
     * @brief Calculate the Hailstone sequence starting from a number.
     * @param n The number to start the Hailstone sequence from.
     * @return The next number in the Hailstone sequence.
     * @example hailstone(6) prints "6 3 10 5 16 8 4 2 1".
     * @time_complexity O(log n), where n is the input number.
     */
    short hailstone(short n)
    {
        // Base case: n is 1
        if (n == 1)
        {
            return 1;
        }
        // Recursive case: n is even
        else if (n % 2 == 0)
        {
            cout << n << " ";
            return hailstone(n / 2);
        }
        // Recursive case: n is odd
        else
        {
            cout << n << " ";
            return hailstone(3 * n + 1);
        }
    }

    /**
     * @brief Calculate the greatest common divisor of two numbers using Euclid's algorithm.
     * @param a The first number.
     * @param b The second number.
     * @return The greatest common divisor of the two numbers.
     * @example gcd(48, 18) returns 6.
     * @time_complexity O(log min(a, b)), where a and b are the input numbers.
     */
    short gcd(short a, short b)
    {
        if (b == 0)
        {
            return a;
        }
        else
        {
            return gcd(b, a % b);
        }
    }
    /**
     * @brief Sort an array using the merge sort algorithm.
     * @param arr The array to sort.
     * @param l The left index of the sub-array of arr to be sorted.
     * @param m The middle index of the sub-array of arr to be sorted.
     * @param r The right index of the sub-array of arr to be sorted.
     * @example merge({12, 11, 13, 5, 6, 7}, 0, 2, 5) sorts the array in ascending order.
     * @time_complexity O(n), where n is the number of elements in the array.
     */
    void merge(short arr[], short l, short m, short r)
    {
        short i, j, k;
        short n1 = m - l + 1;
        short n2 = r - m;

        short L[n1], R[n2];

        for (i = 0; i < n1; i++)
        {
            L[i] = arr[l + i];
        }
        for (j = 0; j < n2; j++)
        {
            R[j] = arr[m + 1 + j];
        }

        i = 0;
        j = 0;
        k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    /**
     * @brief Sort an array using the merge sort algorithm.
     * @param arr The array to sort.
     * @param l The left index of the sub-array of arr to be sorted.
     * @param r The right index of the sub-array of arr to be sorted.
     * @example mergeSort({12, 11, 13, 5, 6, 7}, 0, 5) sorts the array in ascending order.
     * @time_complexity O(n log n), where n is the number of elements in the array.
     */
    void mergeSort(short arr[], short l, short r)
    {
        if (l < r)
        {
            short m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    /**
     * @brief Print an array.
     * @param arr The array to print.
     * @param size The size of the array.
     * @example printArray({12, 11, 13, 5, 6, 7}, 6) prints "12 11 13 5 6 7".
     * @time_complexity O(n), where n is the number of elements in the array.
     */
    void printArray(short arr[], short size)
    {
        short i;
        for (i = 0; i < size; i++)
        {
            cout << arr[i] << " ";
        }
        cout << endl;
    }
};

int main()
{
    RecursiveFunctions rf;
    short arr[] = {12, 11, 13, 5, 6, 7};
    short arr_size = 6;
    rf.mergeSort(arr, 0, arr_size - 1);
    rf.printArray(arr, arr_size);
}