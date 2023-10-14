#include <iostream>
#include <string>

using namespace std;
class MergeSort {
    public:
    //  Merge sort
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

    // Helper: Print array
    void printArray(short arr[], short size) {
        short i;
        for (i = 0; i < size; i++) {
            cout << arr[i] << " ";
        }
        cout << endl;
    }
};

int main() {
    // Setting up array
    short arr[] = { 12, 11, 13, 5, 6, 7 };
    short arr_size = 6;

    // Setting up MergeSort Class
    MergeSort ms;

    // Printing array
    cout << "Given array is \n";
    ms.printArray(arr, arr_size);

    // Sorting array
    ms.mergeSort(arr, 0, arr_size - 1);

    // Printing sorted array
    cout << "\nSorted array is \n";
    ms.printArray(arr, arr_size);

    return 0;
}