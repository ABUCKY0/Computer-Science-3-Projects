class Sort:
    def __init__(self, n):
        self.data = [0] * n
        self.size = n
        self.used = 0

    def insert(self, number):
        if self.used < self.size:
            self.data[self.used] = number
            self.used += 1
            return True
        return False

    def index_of(self, number):
        for i in range(self.used):
            if self.data[i] == number:
                return i
        return -1

    def del_(self, number):
        index = self.index_of(number)
        if index != -1:
            for i in range(index, self.used - 1):
                self.data[i] = self.data[i + 1]
            self.used -= 1
            return number
        else:
            return -1

    def swap(self, index1, index2):
        self.data[index1], self.data[index2] = self.data[index2], self.data[index1]

    def bubble_sort(self):
        for i in range(self.used):
            for y in range(self.used - i - 1):
                if self.data[y] > self.data[y + 1]:
                    self.swap(y, y + 1)

    def insertion_sort(self):
        for i in range(self.used - 1):
            index = i
            while self.data[index + 1] < self.data[index] and index >= 0:
                self.swap(index + 1, index)
                index -= 1

    def selection_sort(self):
        for i in range(self.used):
            for y in range(self.used - i - 1):
                if self.data[y] > self.data[y + 1]:
                    self.swap(y, y + 1)

    def display(self):
        if self.used == 0:
            print("Array is empty.")
            return

        print(self.data[0], end="")
        for i in range(1, self.used):
            print(", " + str(self.data[i]), end="")
        print()


bubble = Sort(5)

bubble.insert(5)
bubble.insert(3)
bubble.insert(2)
bubble.insert(1)
bubble.insert(7)

print("Unsorted Data: ")
bubble.display()
print()
bubble.insertion_sort()
print()
print("Sorted Data: ")
bubble.display()
