#include <iostream>
#include <string>
#include <ctime>

using namespace std;
//actual
struct Card {
    string cardSuit;
    short cardValue;
    
    string getValueAsString(){
        if(this->cardValue == 1)
            return "A";
        else if(this->cardValue > 1 && this->cardValue < 11)
            return to_string(this->cardValue);
        else if(this->cardValue == 11)
            return "J";
        else if (this->cardValue==12)
            return "Q";
        else
            return "K";
    }
};

class Deck {
    /*
    A Deck class with simple card management utilites
    
    Implements swapping, removing, adding, shuffling, and deleting, 
    along with displaying of cards to the console. 
 */
private:
    Card cards[52]; //an array of cards representing the deck
    short used; //a short integer that holds the used space in the card deck

public:
    short size = 52; 
    string suits[4] = { "H", "D", "C", "S" }; //the card suits
    Deck() {
        /*
          Creates a new, filled deck
        */
        this->used = 0; //sets used to 0
        
        /*Fills in the deck with cards of different types*/
        for (string suit : suits) {
            for (short value = 1; value < 14; value++) {
                this->cards[this->used].cardSuit = suit;
                this->cards[this->used].cardValue = value;
                this->used++;
            }
        }
    }

    Deck(short size) {
        /*
         Makes a new empty deck, useful for player decks
         
         Args:
            size (short): deck size
        */
        this->size = size;
        this->used = 0;
    }

    ~Deck() {};

    void swap(short index1, short index2) {
        /*Swaps the card at index1 with the card at index2
        
        Args:
            index1 (short): first card to swap
            index2 (short): second card to swap*/
        Card tmp = this->cards[index1];
        this->cards[index1] = this->cards[index2];
        this->cards[index2] = tmp;
    }

    void shuffle() {
        /*
        shuffles the deck by the amount of used space in the deck
        */
        shuffle(this->used);
    }

    void shuffle(short numOfTimes) {
        /*shuffles the deck a given number of times
        
        Args:
            numOfTimes (short): number of times to replace a random card with another one
        */
        short randomNum1, randomNum2;
        srand(time(0));

        for (short i = 0; i < numOfTimes; i++) {
            randomNum1 = (rand() % this->used);
            randomNum2 = (rand() % this->used);
            this->swap(randomNum1, randomNum2);
        }
    }

    bool insert(Card crd) {
        /*inserts a card into the deck if it's not already present
        
        Args:
            crd (Struct (card)): A Card Dataclass representing a card
        
        Returns:
            Boolean True if completed, false if not.
        */
        if (this->used < this->size) { // Check if there is space in the deck
            if (indexOf(crd) == -1) { // Check if the card already exists in the deck
                this->cards[this->used] = crd;
                this->used++;
                return true;
            }
        }
        return false;
    }

    short indexOf(Card crd) {
        /*returns the index of a given Card
        
        Args:
            crd (Struct of Card): A Card Dataclass representing a card
        Returns:
            positive short representing index if found, otherwise returns -1
        */
        for (short i = 0; i < this->used; i++) {
            if (this->cards[i].cardValue == crd.cardValue && this->cards[i].cardSuit == crd.cardSuit) {
                return i;
            }
        }
        return -1;
    }

    Card* getCards() {
        /*returns the card array
        
        Returns:
            Array of Cards
        */
        return this->cards;
    }
    
    Card getCard(short index) {
        /*Returns the card at a given index
        
        Args:
            index (short): the index of a card to get
        Returns:
            the Requested card
        */
        return this->cards[index];
    }

    Card* del(Card crd) {
        /*Deletes and returns a given card
        
        Args:
            crd (Card Struct): the card to delete
        Returns:
            A card Pointer*/
        short index = indexOf(crd);
        if (index != -1) {
            Card* c = &this->cards[index];
            swap(index, this->used - 1); // Corrected index
            this->used--;
            return c;
        }
        else {
            return nullptr;
        }
    }

    void eraseDeck() {
        /*Erases the Deck by removing each card*/
        while (this->used > 0) {
            this->del(this->cards[this->used - 1]);
        }
    }

    short getUsed() {
        /*returns the used space of the deck
        
        Returns:
            the this->used short value
        */
        return this->used;
    }

    void displayCards() {
        /*Displays all cards and their values to console (cout)
        */
        string s = "";
        for (short i = 0; i < this->used; i++) {
            string c = this->cards[i].getValueAsString() + this->cards[i].cardSuit; //Gets card values
            s = s + c; //adds to main string
            s = s + " "; 
        }
        cout << s;
    }
    
    /*
        Extended Functionality for Histograms
    */
    void createHistogramForSuit() { //Card Suit (Hearts, Diamonds, Clubs, Spades)
        /*
        Creates a Histogram Bar graph for the suits in a deck
        */
        string h = "", d = "", c = "", s = ""; // h = hearts; d = diamonds, c = clubs, s = spades
        
        for (short i = 0; i < this->getUsed(); i++){
            if (this->getCard(i).cardSuit == "H")
                h = h + "#";
            else if (this->getCard(i).cardSuit == "D")
                d = d + "#";
            else if (this->getCard(i).cardSuit == "C")
                c = c + "#";
            else
                s = s + "#";
        }
        cout << "\tDeck Histogram for Suit\n"<<endl;
        cout << "Hearts   |" << h << endl;
        cout << "Diamonds |" << d << endl;
        cout << "Clubs    |" << c << endl;
        cout << "Spades   |" << s << endl;
    }
    
    string convertValueToString(short value){
        /*Converts a Given int value to the value of a card
        
        1 is the Ace
        2 through 10 are normal cards
        11 is the Jack
        12 is the Queen
        and 13 is the King
        
        Args: 
            value (int): the integer value that represents a card's value
        
        Returns:
            (str) a string that represents the card
        */
        if(value == 1)
            return "A";
        else if(value > 1 && value < 11)
            return to_string(value);
        else if(value == 11)
            return "J";
        else if (value==12)
            return "Q";
        else
            return "K";
    }
    void createHistogramForValue() {
    /*
    Calculates a Histogram for the Card Value (Queen, King, Jack, Joker, etc)
    */
    short values[13] = {0};
    for (short i = 0; i < this->getUsed(); i++) {
        values[this->getCard(i).cardValue - 1] = values[this->getCard(i).cardValue - 1] + 1;
    }
    cout << endl;
    cout << "\tDeck Histogram for Value\n";
    for (short i = 0; i < 13; i++) {
        cout << this->convertValueToString(i+1);
        if (i == 9)
            cout << ":|"; 
        else
            cout<< ": |";
            
        
        for (short x = 0; x < values[i]; x++){
            cout << "#";
        }
        cout << endl;
    }
}
};

int main() {
    
    Deck deck;// main deck
    Deck player1(26); // player1 deck
    Deck player2(26); // player2 deck

    deck.shuffle(52); //shuffles main deck
    cout<<"Main Deck: ";
    deck.displayCards(); //displays all the cards in the deck
    
    cout << endl;
    cout << endl;
    deck.createHistogramForSuit(); //main deck histograms
    deck.createHistogramForValue(); 

    for (short i = 0; i < deck.getUsed(); i++) {
        Card crd = deck.getCard(i);
        if(i < 26) { // the first 26 cards go to player 1
            player1.insert(crd);
            
        }
        else{ // the rest of the cards go to player 2
            player2.insert(crd);
        }
    }
    cout<<"\n\nPlayer 1 Deck: ";
    player1.displayCards();
    cout << "\n\n";
    player1.createHistogramForSuit();
    player1.createHistogramForValue();
    
    
    cout<<"\n\nPlayer 2 Deck: ";
    player2.displayCards();
    cout << "\n\n";
    player2.createHistogramForSuit();
    player2.createHistogramForValue();
    
    
    deck.eraseDeck();
    cout<<"\n\nNew Main Deck: ";
    deck.displayCards();

    return 0;
}