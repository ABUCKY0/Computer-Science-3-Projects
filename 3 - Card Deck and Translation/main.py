import random
from dataclasses import dataclass
import sys


@dataclass
class Card:
    card_suit: str # variable: type specifices the type, any can be used to specify any type
    card_value: int

class Deck:
    """A Deck class with simple card management utilites
    
    Implements swapping, removing, adding, shuffling, and deleting, 
    along with displaying of cards to the console. 
    
    Note:
        This class often relies on the programmer to properly pass in
        variables of the right type, and often doesn't check before execution
        if the data is valid
    
    Attributes:
        used (int): used space in the deck
        size (int): size of the card deck
        suits (`list` of str) 
        cards (`list` of Card class)
    
    Args:
        size (int, optional): max size of the deck
        is_player_deck (bool, optional): if the deck is a player deck
    
    """
    def __init__(self, size=52, is_player_deck=False):
        self.used = 0
        self.size = size
        self.cards = []
        suits = {"H", "D", "C", "S"}
        
        if (not is_player_deck):
            for suit in suits:
                for value in range(1, 14):
                    self.cards.append(Card(card_suit=suit, card_value=value))
                    self.used = self.used + 1
        else:
            self.size = size
            self.used = 0
        
        
    def swap(self, index1, index2):
        """Swaps the card at index1 with the card at index2
        
        Args:
            index1 (int): first card to swap
            index2 (int): second card to swap
        
        """
        tmp = self.cards[index1]
        self.cards[index1] = self.cards[index2]
        self.cards[index2] = tmp
    
    def shuffle(self, num_of_times):
        """shuffles the deck a given number of times
        
        Args:
            num_of_times (int): number of times to replace a random card with another one
            
        """
        for i in range(num_of_times):
            random_num_1 = random.randint(0, self.used - 1)
            random_num_2 = random.randint(0, self.used - 1)
            self.swap(random_num_1, random_num_2)
    
    def insert(self, crd):
        """inserts a card into the deck if it's not already present
        
        Args:
            crd (`Class` of Card): A Card Dataclass representing a card
        
        Returns:
            Boolean True if completed, false if not.
        """
        
        if (self.used < self.size):
            if (self.index_of(crd) == -1):
                self.cards.append(crd)
                self.used += 1
                return True
        return False
    
    def index_of(self, crd):
        """returns the index of a given Card
        
        Args:
            crd (`Class` of Card): A Card Dataclass representing a card
        Returns:
            positive (int) representing index if found, otherwise returns -1
        """
        for i in range(self.used): 
            if ((self.cards[i].card_value == crd.card_value) and (self.cards[i].card_suit  == crd.card_suit)):
                return i
        return -1
    
    def get_cards(self):
        """returns the card list
        
        Returns:
            list of `Cards`
        """
        return self.cards
    
    def get_card(self, index):
        """Returns a Card at a given index
        
        Args:
            (int): index
        Returns:
            Class of Card
        """
        try:
            return self.cards[index]
        except IndexError as e:
            print("ERROR: Invalid Index Provided to get_card, and was caught.")
            return
        
    def delete(self, crd):
        """Removes a card from the index in the python list
        Args:
            (Class of Card): Card Dataclass representing a specific card
        Returns:
            the removed Card
        """
        index = self.index_of(crd)
        if (index != -1):
            c = self.cards[index]
            self.swap(index, self.used - 1)
            self.used -= 1
            return c
    
    def erase_deck(self):
        """removes all cards from the deck
        """
        for i in range(self.used):
            self.delete(self.cards[self.used-1])
    
    def get_used(self):
        """returns the used variable
        Returns:
            the amount of cards in use (int)
        """
        return self.used
        
    def display_cards(self):
        """Displays the value of all cards in the list
        """
        s = ""
        for i in range(self.used):
            c = str(self.cards[i].card_value) + self.cards[i].card_suit
            s = s + c
            s = s + " "
        print(s)

if __name__ == "__main__":
    deck = Deck()
    player_1 = Deck(26, is_player_deck=True)
    player_2 = Deck(26, is_player_deck=True)
    deck.shuffle(52)
    print("Main Deck: ", end="")
    deck.display_cards()
    
    for i in range(deck.get_used()):
        crd = deck.get_card(i)
        if (i < 26):
            player_1.insert(crd)
        else:
            player_2.insert(crd)
    
    print("\n\nPlayer 1 Deck: ", end = "")
    player_1.display_cards()
    
    print("\n\nPlayer 2 Deck: ", end = "")
    player_2.display_cards()
    
    deck.erase_deck()
    print("\n\nNew Main Deck: ", end = "")
    deck.display_cards()