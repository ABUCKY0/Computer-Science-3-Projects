#include <iostream>
#include <string>
#include <cstring>

using namespace std;
class Sort {
private:
    short* data;
    short size;
public:
    short used;
    Sort(short n) {
        this->size = n;
        this->data = new short[this->size];
        this-> used = 0;
    }
    
    ~Sort() {
        delete[] data;
    }
    
    bool insert(short number) {
        if(this->used < this->size) {
            this->data[used] = number;
            this->used++;
        }
        return false;
    }
    short indexOf(short number) {
        for(short i = 0; i < this->used;i++) {
            if(this->data[i] == number)
                return i;
        }
        return -1;
    }
    
    
    short del(short number) {
        short index = indexOf(number);
        if (index != -1) {
            for(short i = index; this->used - 1; i++) {
                this->data[i] = this->data[i+1];
            }
            this->used--;
            return number;
        }
        else {
            return -1;
        }
    }
    
    void swap(short index1, short index2) {
        // this->data[index1] = this->data[index1] + this->data[index2];
        // this->data[index2] = this->data[index1] - this->data[index2];
        // this->data[index1] = this->data[index1] - this->data[index2];
        short* tmp = new short(this->data[index1]); //Dynamically Allocate
        this->data[index1]=this->data[index2]; 
        this->data[index2]=*tmp; //swap values, not pointers
        
        //when you're done, delete tmp;
        delete tmp;
    }
    void bubbleSort() {
        for(short i = 0; i < this->used; i++) {
            for(short y = 0; y < this->used-i-1; y++) {
                //indexes we are comparing y and y+1
                if (this->data[y] > this->data[y + 1]) {
                    swap(y, y + 1);
                }
            }
        }
    }
    
    void insertionSort() { // best case scenario O(n); worst case: O(n^2)
        for (short i = 0; i < this->used-1; i++) {
            short index = i;
            while(this->data[index+1] < this->data[index] && index>=0) {
                this->display();
                this->swap(index+1, index);
                index--;
            }
        }
    }

    void selectionSort() {
        for (short i = 0; i < this->used; i++) {
            for (short y = 0; y < this->used-i-1; y++) {
                //Indexes we are comparing are y and y+1
                if (this->data[y] > this->data[y + 1]) {
                    swap (y, y + 1);
                }
            }
        }
    }
    void display() {
        if (this->used == 0) {
            cout << "Array is empty." << endl; 
            return;
        }
        
        cout << this->data[0]; //print first element
        
        for (int i = 1; i < this->used; i++) {
            cout << ", " << this->data[i]; //Print a comma followed by the element
        }
        cout << endl; // Print a newline to finish the output
    }
};

int main()
{
    Sort bubble(5);
    
    bubble.insert(5);
    bubble.insert(3);
    bubble.insert(2);
    bubble.insert(1);
    bubble.insert(7);
    
    cout<<"Unsorted Data: " << endl;
    bubble.display();
    cout << endl;
    bubble.insertionSort();
    cout << endl;
    cout << "Sorted Data: " << endl;
    bubble.display();
    return 0;
}