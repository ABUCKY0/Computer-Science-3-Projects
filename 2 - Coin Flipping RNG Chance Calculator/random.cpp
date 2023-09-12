#include <iostream>
#include <cmath>
using namespace std;
/*
 * Generates a Random Number being either 0 or 1
 * 0 = Heads
 * 1 = Tails
 **************************************************
 *
 * Author: Aaron Buckner
 * License: MIT
 * Start Date: 9/7/23 @ 12:55 PM CST
 * Last Edited:  9/12/23 @ 05:12 PM CST
 *
 */
class coinFlip {
  private:
  public:
    int heads;
    int tails;
    int flips;
    void flip(int flips) {
        srand(time(0));
        
        for (int x = 0; x < flips; x++) {
            srand(rand()%(2147483647));
            int randNum = rand()%(2);
            if (randNum == 0) {
                heads++;
            }
            else if (randNum == 1) {
                tails++;
            }
            else {
                throw;
            }
        }
        cout << "Heads/flips: "<< ((double)heads/(double)flips)*100 << "%" << endl;
        cout << "Tails/flips: "<< ((double)tails/(double)flips)*100 << "%" << endl;
        cout << "Heads Total: " << heads << endl;
        cout << "Tails Total: " << tails << endl;
        cout << "Number of Flips: " << flips << endl;
         
    }
};

int main()
{
    
    cout << "Flipping has begun, please be patient." << endl;
    
    int heads = 0;
    int tails = 0;
    int flips = 9999999; //9,999,999 flips
    
    coinFlip flipper;
    flipper.flip(flips);
    
    
}
