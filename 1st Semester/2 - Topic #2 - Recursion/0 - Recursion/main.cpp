#include <string>
#include <iostream>
#include <cstring>

using namespace std;

short factorial(short num) {
    if(num == 1) {
        return 1;
    }
    else {
        return num * factorial(num - 1);
    }
}

short factorialtwo(short num) {
    if (num > 0) {
        return num*factorialtwo(num-1);
    }
    else {
        return 1;
    }
}


int main() {
    short num = 99;
    cout << "Factorial of " << num << " is " << factorial(num) << endl;
    return 0;
}