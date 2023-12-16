#include <iostream>

using namespace std;

class Fibonacci {
public:
    unsigned long long int memoization[100];
    Fibonacci() {
        for (unsigned long long int i = 0; i < 100; i++) {
            this->memoization[i] = -1;
        }
    }
    // Function 2: Fibonacci sequence
    unsigned long long int recursiveFibonacci(unsigned long long int n)
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
    unsigned long long int n = 100;
    unsigned long long int fib_array[100];
    // Initialize memoization array with -1
    for (unsigned long long int i = 0; i < n; i++) {
        fib.memoization[i] = -1;
    }
    cout << fib.recursiveFibonacci(10) << endl;
    cout << "Fibonacci sequence up to " << n << ":" << endl;
    for (unsigned long long int i = 0; i < n+1; i++) {
        fib_array[i] = fib.recursiveFibonacci(i);
        cout << fib_array[i] << " ";
    }
    cout << endl;
    return 0;
}