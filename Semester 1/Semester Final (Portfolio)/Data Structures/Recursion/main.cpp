#include <iostream>

using namespace std;

class Fibonacci {
public:
    short memoization[50];
    Fibonacci() {
        for (short i = 0; i < 50; i++) {
            this->memoization[i] = -1;
        }
    }
    // Function 2: Fibonacci sequence
    short recursiveFibonacci(short n)
    {
        // If the cache holds -1 at the required index, it has not yet been computed.
        if (this->memoization[n] == -1) {
            if (n <= 1) {
                this->memoization[n] = n;
            } else {
                this->memoization[n] = recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2);
            }
        }
        return this->memoization[n];
    }
};

int main() {
    Fibonacci fib;
    short n = 12;
    short fib_array[50];
    // Initialize memoization array with -1
    for (short i = 0; i < n; i++) {
        fib.memoization[i] = -1;
    }
    cout << fib.recursiveFibonacci(10) << endl;
    cout << "Fibonacci sequence up to " << n << ":" << endl;
    for (short i = 0; i < n+1; i++) {
        fib_array[i] = fib.recursiveFibonacci(i);
        cout << fib_array[i] << " ";
    }
    cout << endl;
    return 0;
}