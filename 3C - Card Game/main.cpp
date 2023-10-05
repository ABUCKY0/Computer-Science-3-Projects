#include <iostream>
#include <string>
#include <ctime>
#include <cstring>
using namespace std;

struct Card {
    string cardSuit;
    short cardValue;
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

    Deck() {
        /*
          Creates a new, filled deck
        */
        string suits[4] = { "H", "D", "C", "S" }; //the card suits
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
            string c = to_string(this->cards[i].cardValue) + this->cards[i].cardSuit; //Gets card values
            s = s + c; //adds to main string
            s = s + " "; 
        }
        cout << s;
    }
    
};

bool isGameOver(Deck decks[], Deck mainDeck) {
    
    return false;
}

short getCardValue(string cardRank) {
    if (cardRank == "a" || cardRank == "ace" || cardRank == "A" || cardRank == "Ace") {
        return 1;
    }
    else if (cardRank == "J" || cardRank == "j" || cardRank == "Jack" || cardRank == "jack") {
        return 11;
    }
    else if (cardRank == "Q" || cardRank == "q" || cardRank == "Queen" || cardRank == "queen") {
        return 12;
    }
    else if (cardRank == "K" || cardRank == "k" || cardRank == "King" || cardRank == "king") {
        return 13;
    }
    else {
        try {
            short value = stoi(cardRank);
            if (value >= 2 && value <= 10) {
                return value;
            }
            else {
                return -1;
            }
        }
        catch (exception e) {
            return -1;
        }
    }
}

int main() {
   /*
   Go Fish is played with 2-6 players, and the goal is to collect the most sets of 4 cards.

   Each player starts with 5 cards. The remaining cards are placed in a pile in the center of the table.
    The first player asks another player for a specific card. For example, "Player 1" may ask "Player 2", "Do you have any 7s?"
   */
    Deck deck; // Makes a new Main Deck
    short numOfPlayers; // Number of players
    cout << "Number of Players (2-6): ";
    cin >> numOfPlayers; 
    Deck playerDecks[numOfPlayers];

    //create decks for each player
    for (short i = 0; i < numOfPlayers; i++) {
        playerDecks[i] = Deck(5);
    }
    deck.shuffle();
    //deal 5 cards to each player
    for (short i = 0; i < numOfPlayers; i++) {
        for (short j = 0; j < 5; j++) {
            playerDecks[i].insert(*deck.del(deck.getCard(0)));
        }
    }

    short turn = 0;
    while (!isGameOver(playerDecks, deck)) {
        Deck& currentPlayer = playerDecks[turn];
        Deck& nextPlayer = playerDecks[(turn + 1) % numOfPlayers];

        // Display current player's hand
        cout << "Player " << turn + 1 << "'s turn" << endl;
        cout << "Your cards: ";
        currentPlayer.displayCards();
        cout << endl;

        short playerIndex = turn+1;
        string cardRank = "";
        if (numOfPlayers > 2) {
            bool validPlayerIndex = false;
            while (!validPlayerIndex) {
                cout << "Player " << turn + 1 << ", which player do you want to ask? Choose a player (1-" << numOfPlayers << "): ";
                cin >> playerIndex;
                playerIndex--; // Corrected index
                cin.ignore(); // Clear input buffer
                if (playerIndex >= 0 && playerIndex < numOfPlayers && playerIndex != turn) {
                    validPlayerIndex = true;
                }
            }
        }
        else {
            playerIndex = (turn + 1) % numOfPlayers;
        }
        cout << "Player " << turn + 1 << ", which card do you want to ask for? Choose a card rank (Ace (A), 2-10, Jack (J), Queen (Q), King (K)): ";
        cin >> cardRank;

        short integerCardRank = getCardValue(cardRank);
        cout << "integerCardRank: " << integerCardRank << endl;
        if (integerCardRank == -1) {
            cout << "Invalid card rank!" << endl;
            continue;
        }
        
        bool found = false;
        for (short i = 0; i < currentPlayer.getUsed(); i++) {
            if (currentPlayer.getCard(i).cardValue == integerCardRank) {
                found = true;
                cout << "You already have that card!" << endl;
                break;
            }
        }

        if (!found) {
            found = false;
            for (short i = 0; i < nextPlayer.getUsed(); i++) {
                if (nextPlayer.getCard(i).cardValue == integerCardRank) {
                    found = true;
                    currentPlayer.insert(*nextPlayer.del(nextPlayer.getCard(i)));
                    break;
                }
            }
            if (!found) {
                cout << "Go Fish!" << endl;
                currentPlayer.insert(*deck.del(deck.getCard(0)));
            }
        }
        // Check for sets
        for (short i = 1; i <= 13; i++) {
            short count = 0;
            for (short j = 0; j < currentPlayer.getUsed(); j++) {
                if (currentPlayer.getCard(j).cardValue == i) {
                    count++;
                }
            }
            if (count == 4) {
                cout << "Player " << turn + 1 << " has a set of " << i << "s!" << endl;
                for (short j = 0; j < currentPlayer.getUsed(); j++) {
                    if (currentPlayer.getCard(j).cardValue == i) {
                        deck.insert(*currentPlayer.del(currentPlayer.getCard(j)));
                    }
                }
            }
        }

        turn = (turn + 1) % numOfPlayers;
    }

    return 0;
}