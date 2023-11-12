"""Singly Linked List Class
"""
from .node import Node
from .linked_list import LinkedList

class SinglyLinkedList(LinkedList):
    """Singly Linked List Class

    Args:
        LinkedList (_type_): _description_
    """
    def __init__(self,node: Node = None):
        """Creates a Singly Linked List

        Args:
            data (int, optional): _description_. Defaults to None.
            node (Node, optional): _description_. Defaults to None.
        """
        if node is None:
            self.size = 0
            self.head = None
        else:
            self.head = node
            self.size = 1
    def add_front(self, data: Node):
        """Adds an element to the front of the Linked List

        Args:
            data (int): The integer to add to the front of the Linked List
        """
        n = Node(data)
        n.next = self.head
        self.head = n
        self.size += 1
    def add_after(self, data: Node, after: int):
        """Adds an element after the specified element

        Args:
            data (int): The integer to add after the specified one
            after (int): The element to add the specified data after
        """
        if self.contains(after) is False:
            self.add_back(data)
        else:
            n = Node(data)
            tmp = self.head
            while tmp.data != after:
                tmp = tmp.next
            n.next = tmp.next
            tmp.next = n
            self.size += 1
    def add_before(self, data: Node, before: int):
        """Adds an element before the specified element

        Args:
            data (int): The integer to add before the specified one
            before (int): The element to add the specified data before
        """
        if self.contains(before) is False:
            self.add_front(data)
        else:
            n = Node(data)
            tmp = self.head
            while tmp.next.data != before:
                tmp = tmp.next
            n.next = tmp.next
            tmp.next = n
            self.size += 1
    def add_back(self, data: Node):
        """Adds an element to the end of the list

        Args:
            data (int): The integer to add to the end of the Linked List
        """
        n = Node(data)
        tmp = self.head
        while tmp.next is not None:
            tmp = tmp.next
        tmp.next = n
        self.size += 1
    def delete_front(self):
        """Deletes the head element
        
        Returns:
            Node: Deleted Node
        """
        tmp = self.head
        self.head = self.head.next
        self.size -= 1
        return tmp
    def clear(self):
        """Clears the Linked List
        """
        self.head = None
        self.size = 0
    def delete_back(self):
        """Deletes the last element
        
        Returns:
            Node: Deleted Node
        """
        tmp = self.head
        while tmp.next.next is not None:
            tmp = tmp.next
        tmp.next = None
        self.size -= 1
        return tmp
    def contains(self, data: int):
        """Check if the linked list contains the data

        Args:
            data (int): the int data to look for
        
        Returns:
            bool: True if found, else False
        """
        tmp = self.head
        while tmp is not None:
            if tmp.data == data:
                return True
            tmp = tmp.next
        return False
    def convert_to_list(self):
        """Converts the Linked List to a list

        Returns:
            list: The list representation of the Linked List
        """
        tmp = self.head
        lst = []
        while tmp is not None:
            lst.append(tmp.data)
            tmp = tmp.next
        return lst
    def convert_from_list(self, lst: list):
        """Converts the list to a Linked List

        Args:
            lst (list): The list to convert from
        """
        self.head = Node(lst[0])
        tmp = self.head
        for i in range(1, len(lst)):
            tmp.next = Node(lst[i])
            tmp = tmp.next
        self.size = len(lst)
    def get_size(self):
        """Returns self.size
        
        Returns:
            int: self.size
        """
        return self.size
    def delete_at(self, index: int):
        """Deletes the item at the specified index

        Args:
            index (int): the index of the item to remove
            
        Returns:
            recipe: the deleted recipe
        """
        if self.head is None:
            return None
        if index == 0:
            deleted = self.head
            self.head = self.head.next
            self.size -= 1
            return deleted
        if index >= self.size:
            return None
        current = self.head
        count = 0
        while count < index - 1 and current is not None:
            current = current.next
            count += 1
        if current is None:
            return None
        deleted = current.next
        current.next = current.next.next
        self.size -= 1
        return deleted
    def __repr__(self):
        """returns the string representation of the class

        Returns:
            str: the string representation of the class
        """
        i = 0
        tmp = self.head
        s = ""
        while tmp is not None:
            s += "Recipe at index " + str(i) + ": "
            s += str(tmp.data) + "\n"
            tmp = tmp.next
            i+=1
        return s
    