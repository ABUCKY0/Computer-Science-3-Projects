"""Saves a List to a binary file"""
import pickle

class Savedata():
    """Saves data to and from a file
    """
    def save_file(self, data: list, fileName = "savedata.bin"):
        """Save the data to the file

        Args:
            fileName (str): the name of the file to save to
            data (obj): the data to save to the file
        """
        with open(fileName, 'wb') as f:
            pickle.dumps(data, f)
            
    def load_file(self, fileName = "savedata.bin"):
        """Load the data from the file

        Args:
            fileName (str): the name of the file to load from

        Returns:
            obj: the data loaded from the file
        """
        with open(fileName, 'rb') as f:
            return pickle.loads(f)