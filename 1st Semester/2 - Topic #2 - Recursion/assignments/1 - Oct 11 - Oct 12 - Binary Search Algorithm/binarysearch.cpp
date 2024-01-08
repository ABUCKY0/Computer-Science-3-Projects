#include <iostream>
#include <string>
#include <cstring>

using namespace std;

bool binarySearch(short arr[], short num, short lo, short hi) {
    short mid = (lo + hi) / 2;
    if (lo > hi) {
        return false;
    }
    else if (arr[mid] == num) {
        return true;
    }
    else if (arr[mid] < num) {
        return binarySearch(arr, num, lo, mid + 1);
    }
    else {
        return binarySearch(arr, num, mid - 1, hi);
    }
}

int main() {
    short arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    short num = 0;
    cout << binarySearch(arr, num, 0, 8) << endl;
    return 0;
}