#include <string>
#include <iostream>
#include <cstring>

using namespace std;

short factorial(short num) {
    if(num == 1) {
        return 1;
    }
    else {
        short value = num * factorial(num - 1);
        cout << value << endl;
        return value;
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

void bomb() {
    while (1) {
    bomb();
    }
}

int main() {
    bomb();
    return 0;
}