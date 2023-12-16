#include <iostream>
#include <string>
#include <cstring>
#include <ctime>
#include <chrono>
#include <thread>

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

    // 1: Bubble Sort
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
    // 2: Insertion Sort
    void insertionSort() { // best case scenario O(n); worst case: O(n^2)
        for (short i = 0; i < this->used-1; i++) {
            short index = i;
            while(this->data[index+1] < this->data[index] && index>=0) {
                this->swap(index+1, index);
                index--;
            }
        }
    }

    // 3: Selection Sort
    void selectionSort() {
        for (short i = 0; i < this->used-1; i++) {
            short min = i;
            for (short y = i+1; y < this->used; y++) {
                if (this->data[y] < this->data[min]) {
                    min = y;
                }
            }
            this->swap(i, min);
        }
    }

    // 4: Merge Sort
    void merge(int arr[], int left[], int right[], int left_size, int right_size) {
        int i = 0, j = 0, k = 0;

        while (i < left_size && j < right_size) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            }
            else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < left_size) {
            arr[k] = left[i];
            i++;
            k++;
        }

        while (j < right_size) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }

    void mergeSort(int arr[], int left_index, int right_index) {
        if (left_index < right_index) {
            int middle_index = left_index + (right_index - left_index) / 2;

            int left_half[middle_index - left_index + 1];
            int right_half[right_index - middle_index];

            for (int i = 0; i < middle_index - left_index + 1; i++) {
                left_half[i] = arr[left_index + i];
            }

            for (int i = 0; i < right_index - middle_index; i++) {
                right_half[i] = arr[middle_index + 1 + i];
            }

            mergeSort(left_half, 0, middle_index - left_index);
            mergeSort(right_half, 0, right_index - middle_index - 1);
            merge(arr, left_half, right_half, middle_index - left_index + 1, right_index - middle_index);
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

int main(){ 
    srand(time(0)); // setting the seed 
    short length = 10; // this is the number of values (N); a switch
    
    Sort bubble(length); 
    
    for(int i=0; i<length; i++){ // length amount of random values
        bubble.insert(rand() % 100); 
    }
    
    typedef chrono::high_resolution_clock Time; // small increments of time
    typedef chrono::milliseconds ms; // miliseconds
    typedef chrono::duration<float> fsec; // duration converted to float
    
    auto start = Time::now(); // start timer
    bubble.bubbleSort(); // process (sorting)
    auto stop = Time::now(); // stop the timer
    fsec fs = stop - start; // converts the time
    ms d = chrono::duration_cast<ms>(fs);  //casts to miliseconds
    
    cout << "Elapsed Time: " << fs.count() << " seconds" << endl; 
    return 0; 

}