class Node:
    """The indiviual Node element for the Linked lists
    """
    def __init__(self, data: int):
        """_summary_

        Args:
            data (int): the data held in the node
        """
        self.data = data
        self.next = None
        self.prev = None
    def __repr__(self):
        """returns the string representation of the class

        Returns:
            str: the string representation of the class
        """
        return self.data + " "
    