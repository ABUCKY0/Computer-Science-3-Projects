#include <iostream>
#include <string>
#include <ctime>
#include "../other utilities/colors.h"
using namespace std;

struct Card {
    // A Struct that Represents a single Playing Card
    string cardSuit;
    short cardValue;
};

string cvtostr(short cardValue) {
    // Converts a CardValue short to a string
    switch (cardValue) {
    case 1:
        return "Ace";
    case 11:
        return "Jack";
    case 12:
        return "Queen";
    case 13:
        return "King";
    default:
        return to_string(cardValue);
    }
}

short strtocv(string cardRank) {
    // Converts a string to a CardValue short
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
        short value = stoi(cardRank);
        if (value >= 2 && value <= 10) {
            return value;
        }
        else {
            return -1;
        }
    }
}

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
            string c = cvtostr(this->getCard(i).cardValue); //Gets card values
            s = s + c; //adds to main string
            s = s + " "; 
        }
        cout << s;
    }
    bool checkDeckForCardWithValue(short cardValue) {
        for (short i = 0; i < this->used; i++) {
            if (this->getCard(i).cardValue == cardValue) {
                return true;
            }
        }
        return false;
    }
};

int main() {
    // Welcome Message and Rules
    cout << "Welcome to Go Fish!" << endl;
    cout << "Rules:" << endl;
    cout << "1. Each player starts with 5 cards" << endl;
    cout << "2. Each player takes turns asking other players for cards" << endl;
    cout << "3. If the player has the card, they must give it to the player who asked" << endl;
    cout << "4. If the player doesn't have the card, the player who asked must go fish" << endl;
    cout << "5. If the player gets the card they asked for, they get another turn" << endl;
    cout << "6. If the player gets a card they didn't ask for, the turn goes to the next player" << endl;  
    cout << "7. If a player gets 4 cards of the same value, they must put them down" << endl;
    cout << Colors::IYELLOW << "8. The game ends when all cards are gone from the main deck" << Colors::RESET << endl;



    //2-6 Players
    cout << "Number of Players (2-6): ";
    short numOfPlayers;
    cin >> numOfPlayers;
    cout << endl;
    cin.ignore();

    // Make Sure Number of Players is Valid
    while (numOfPlayers < 2 || numOfPlayers > 6) {
        cout << "Invalid Number of Players" << endl;
        cout << "Number of Players (2-6): ";
        cin >> numOfPlayers;
        cout << endl;
        cin.ignore();
    }

    //Create Deck
    Deck deck;
    deck.shuffle();

    //Make Decks for each player
    Deck playerDecks[numOfPlayers];
    for (short i = 0; i < numOfPlayers; i++) {
        playerDecks[i] = Deck(20);
    }
    //DeaL Cards to each player
    for (short i = 0; i < numOfPlayers; i++) {
        for (short j = 0; j < 5; j++) {
            playerDecks[i].insert(*deck.del(deck.getCard(0)));
        }
    }
    // The number of books (sets) each player has
    short playerBooks[numOfPlayers];


    // Game Loop
    short playerTurn = 0;
    while (true) {
        // Check if Player has 0 cards, and the main deck isn't empty
        if (playerDecks[playerTurn].getUsed() == 0 && deck.getUsed() == 0) {
            playerDecks[playerTurn].insert(*deck.del(deck.getCard(0)));
        }

        // Display Player Turn
        cout << endl;
        cout << "Player " << playerTurn + 1 << "'s Turn" << endl;
        cout << "Your Cards: ";
        playerDecks[playerTurn].displayCards();
        cout << endl;

        // Get player to fish and card to fish for
        bool validCardFished = false;
        short playerToFish = ((playerTurn + 1) % numOfPlayers) + 1;
        //sets default player to fish to the next player if there are two players
        if (numOfPlayers == 2) {
            playerToFish = ((playerTurn + 1) % numOfPlayers == 0) ? 1 : 2;
        }

        // Make Sure The Card You're Fishing (Asking) For is Valid
        while (!validCardFished) {
            if (numOfPlayers > 2) {
                cout << "What player would you like to fish? (1-" << numOfPlayers << "): ";
                cin >> playerToFish;
                cout << endl;
                cin.ignore();
            }

            // Make Sure Player to Fish is Valid
            while (playerToFish < 1 || playerToFish > numOfPlayers) {
                cout << "Invalid Player" << endl;
                cout << "What player would you like to fish? (1-" << numOfPlayers << "): ";
                cin >> playerToFish;
                cout << endl;
                cin.ignore();
            }

            cout << "What card would you like to fish for? (A (Ace), 2-10, J (Jack), K (King), Q (Queen)): ";
            string cardToFish;
            getline(cin, cardToFish);

            // make sure you have the card you're fishing for
            while (!playerDecks[playerTurn].checkDeckForCardWithValue(strtocv(cardToFish))) {
                cout << "You don't have that card" << endl;
                cout << "What card would you like to fish for? (A (Ace), 2-10, J (Jack), K (King), Q (Queen)): ";
                getline(cin, cardToFish);
            }


            // Finding and Taking the Card
            bool cardFound = false;
            for (short i = playerDecks[playerToFish - 1].getUsed() - 1; i >= 0; i--) {
                if (playerDecks[playerToFish - 1].getCard(i).cardValue == strtocv(cardToFish)) {
                    Card* cardFished = playerDecks[playerToFish - 1].del(playerDecks[playerToFish - 1].getCard(i));
                    if (cardFished != nullptr) {
                        bool t = playerDecks[playerTurn].insert(*cardFished);
                        validCardFished = true;
                        cardFound = true;
                    }
                    break;
                }
            }
            // If the card wasn't found, go fish
            if (!cardFound) {
                cout << "Go Fish!" << endl;
                playerDecks[playerTurn].insert(*deck.del(deck.getCard(0)));
                validCardFished = true;
                playerTurn = (playerTurn + 1) % numOfPlayers;
            }
        }

        // Remove Sets of Cards where 4 cards have the same value
        for (short i = 0; i < playerDecks[playerTurn].getUsed(); i++) {
            short numOfCards = 0;
            for (short j = 0; j < playerDecks[playerTurn].getUsed(); j++) {
                if (playerDecks[playerTurn].getCard(i). cardValue == playerDecks[playerTurn].getCard(j).cardValue) {
                    numOfCards++; // Increment number of cards with the same value
                }
            }
            // Set Found
            if (numOfCards == 4) {
                for (short j = 0; j < playerDecks[playerTurn].getUsed(); j++) {
                    if (playerDecks[playerTurn].getCard(i).cardValue == playerDecks[playerTurn].getCard(j).cardValue) {
                        playerDecks[playerTurn].del(playerDecks[playerTurn].getCard(j));
                    }
                }
            }
        }

        // Check if any player has 0 cards, and the main deck isn't empty
        bool gameIsOver = false;
        for (short i = 0; i < numOfPlayers; i++) {
            if (playerDecks[i].getUsed() == 0 && deck.getUsed() != 0) {
                playerDecks[i].insert(*deck.del(deck.getCard(0)));
            }
            if (playerDecks[i].getUsed() == 0) {
                gameIsOver = true;
                break;
            }
            else if (deck.getUsed() <=0) {
                gameIsOver = true;
                break;
            }
        }
        if (gameIsOver) {
            break;
        }
        cout << "---------------------------------";
    }
    // Determine the winner
    short maxBooks = 0;
    short winner = -1;
    for (short i = 0; i < numOfPlayers; i++) {
        short books = 0;
        for (short j = 0; j < playerDecks[i].getUsed(); j++) {
            short numOfCards = 0;
            for (short k = 0; k < playerDecks[i].getUsed(); k++) {
                if (playerDecks[i].getCard(j).cardValue == playerDecks[i].getCard(k).cardValue) {
                    numOfCards++;
                }
            }
            if (numOfCards == 4) {
                books++;
            }
        }
        if (books > maxBooks) {
            maxBooks = books;
            winner = i;
        }
    }

    // Print the winner
    cout << endl << endl;
    cout << Colors::BIRED << "GAME OVER" << Colors::RESET << endl;
    cout << "Player " << winner + 1 << " wins!" << endl;
    return 0;
}