#include <iostream>

using namespace std;
int main()
{
    /*
     *  Hailstone Sequence
     *
     *  If Even, divide by 2
     *  If Odd, multiply by 3 and add 1
     *  
     **************************************************
     *
     * Author: Aaron Buckner
     * License: MIT
     * Start Date: 8/30/23 @ 1:40 PM CST
     * Last Edited:  8/31/23 @ 1:43 PM CST
     */
    int hailstone;
    int iterations = 0;
    cout << "Enter a number: ";
    cin >> hailstone;
    cin.ignore();
    while (hailstone != 1) {
        if ((hailstone % 2) == 0) {
            hailstone = hailstone/2;
        }
        else {
            hailstone = (hailstone*3)+1;
        }
        iterations++;
    }
     cout << "Iterations: " << iterations << endl;
     cout << "Final Number: " << hailstone;
    return 0;
}
