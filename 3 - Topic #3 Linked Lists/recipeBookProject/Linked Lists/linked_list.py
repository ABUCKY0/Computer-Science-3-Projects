"""Module contains the Linked List class

"""
from abc import ABC, abstractmethod
from node import Node
class LinkedList(ABC):
    """The Linked List Abstract Class

    Args:
        ABC (class): Abstract
    """
    @abstractmethod
    def __init__(self, data: int):
        """Initalizes the Linked List

        Args:
            data (int): Data to start the list with
        """
        n = Node(data)
        self.head = n
        self.size = 1
    @abstractmethod
    def add_front(self, data):
        """Adds an item to the front of the LinkedList class

        Args:
            data (int): the integer to add to the front Linked list
        """
    @abstractmethod
    def add_after(self, data: int, after: int):
        """_summary_

        Args:
            data (int): The integer to add after the specified one
            after (int): The index to add the specified data after
        """
    @abstractmethod
    def add_before(self, data: int, before: int):
        """Adds an element after the specified index

        Args:
            data (int): The integer to add before the specified one
            before (int): The index to add the specified data before
        """
    @abstractmethod
    def add_back(self, data: int):
        """Adds an element to the end of the list

        Args:
            data (int): The integer to add to the end of the Linked List
        """
    @abstractmethod
    def delete_front(self):
        """Deletes the head element
        
        Returns:
            Node: Deleted Node
        """
    @abstractmethod
    def delete_back(self):
        """Deletes the last element
        
        Returns:
            Node: Deleted Node
        """
    def contains(self, data: int):
        """Check if the linked list contains the data

        Args:
            data (int): the int data to look for
        
        Returns:
            bool: True if found, else False
        """
    def get_size(self):
        """Returns self.size
        
        Returns:
            int: self.size
        """
    def clear(self):
        """Clears the Linked List
        """
        