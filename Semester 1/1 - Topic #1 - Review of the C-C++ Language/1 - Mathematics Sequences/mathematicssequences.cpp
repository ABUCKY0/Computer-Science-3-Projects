#include <iostream>

using namespace std;
/*
 * Mathematics Sequences Class
 *
 *  Includes Fibonacci Sequence, Arithmetic Sequence, 
 *  Geometric Sequence, Hailstone Sequence,
 *  Factorial Sequence, Power of Two Sequence,
 *  Pascal's Triangle Sequence, Square Number Sequence
 *  
 **************************************************
 *
 * Author: Aaron Buckner
 * License: MIT
 * Start Date: 9/5/23 @ 12:55 PM CST
 * Last Edited:  9/6/23 @ 01:42 PM CST
 *
 */
class MathSeq {
    public:
        void hailstoneSequence(int, int);
        void fibonacciSequence(int, int);
        void arthmeticSequence(int, int);
};

void MathSeq::arthmeticSequence(int startingValue, int iterations) {
    //add a set value to startingValue and store it in the array
    int arithmeticSeq[iterations];
    for (int x = 0; x < iterations; x++) {
        arithmeticSeq[x] = startingValue;
        startingValue += 12;
    }
    for (int i = 0; i < iterations; i++){
        cout << arithmeticSeq[i] << " ";
    }
}

void MathSeq::fibonacciSequence(int startingValue, int iterations) {
    //add n(a-1) and n(a-2) together and store it in n(a)
    int fibseq[iterations + 2];
    fibseq[0] = 0;
    fibseq[1] = startingValue;
    for (int i = 2; i < iterations + 2; i++) {
        fibseq[i] = fibseq[i-1] + fibseq[i-2];
    }
    cout << endl << endl << "All Values: ";
    for (int i = 2; i < iterations + 2; i++) {
        cout << fibseq[i] << " ";
    }
}

void MathSeq::hailstoneSequence(int startingValue, int iterations)
{
    //divide if even, multiply by 3 + 1 if odd
    int hailstoneSeq[iterations];
    while (startingValue != 1) {
        if ((startingValue % 2) == 0) {
            startingValue = startingValue/2;
        }
        else {
            startingValue = (startingValue*3)+1;
        }
        hailstoneSeq[iterations] = startingValue;
        iterations++;
    }
    cout << "Final Number: " << startingValue << endl;
    cout << "All Values: ";
    for (int i = 0; i < iterations; i++){
        cout << hailstoneSeq[i] << " ";
    }
}

int main()
{   
    int iterations = 0;
    int number;
    cout << "Enter a starting value: ";
    cin >> number;
    cin.ignore();
    cout << "Enter a value for the number of iterations you wish to loop through: ";
    cin >> iterations;
    MathSeq seq;
    
    seq.fibonacciSequence(number, iterations);
    seq.hailstoneSequence(number, iterations);
    seq.arithmeticSequence(number, iterations);
    return 0;
}
