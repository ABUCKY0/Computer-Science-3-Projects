#include <iostream>
#include <string>
#include <ctime>
#include "../../Other Misc Used Files/Colors.h"
using namespace std;

struct Card {
    /**
     * @struct Card
     * @brief Represents a single playing card.
     */
    string cardSuit; ///< String representing the suit of the card.
    short cardValue; ///< Short representing the numeric value of the card.
};

string cvtostr(short cardValue) {
    /**
     * @brief Converts a CardValue short to a string.
     * @param cardValue Short representing the numeric value of the card.
     * @return String representing the card value.
     * @example "Ace", "Jack", "Queen", "King", or a numeric value.
     */
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
    /**
     * @brief Converts a string to a CardValue short.
     * @param cardRank String representing the rank of the card.
     * @return Short representing the numeric value of the card.
     * @example For "A" or "ace", returns 1. For "J" or "jack", returns 11, and so on.
     */
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
    /**
     * @class Deck
     * @brief A class with simple card management utilities.
     * Implements swapping, removing, adding, shuffling, deleting, and displaying of cards to the console.
     */
private:
    Card cards[52]; ///< Array of cards representing the deck.
    short used;     ///< Short integer that holds the used space in the card deck.

public:
    short size = 52; ///< Size of the deck.

    Deck() {
        /**
         * @brief Creates a new, filled deck.
         * Fills in the deck with cards of different types.
         * @note Time Complexity: O(N), where N is the number of cards in the deck.
         * @example Deck deck; // Creates a new deck.
         */
        string suits[4] = { "H", "D", "C", "S" }; // The card suits
        this->used = 0; // Sets used to 0

        // Fills in the deck with cards of different types
        for (string suit : suits) {
            for (short value = 1; value < 14; value++) {
                this->cards[this->used].cardSuit = suit;
                this->cards[this->used].cardValue = value;
                this->used++;
            }
        }
    }

    Deck(short size) {
        /**
         * @brief Makes a new empty deck, useful for player decks.
         * @param size Short representing the deck size.
         * @note Time Complexity: O(1)
         * @example Deck deck(20); // Creates a new deck with a size of 20.
         */
        this->size = size;
        this->used = 0;
    }

    ~Deck() {};

    void swap(short index1, short index2) {
        /**
         * @brief Swaps the card at index1 with the card at index2.
         * @param index1 Short representing the index of the first card to swap.
         * @param index2 Short representing the index of the second card to swap.
         * @note Time Complexity: O(1)
         * @example deck.swap(0, 1); // Swaps the first and second cards.
         */
        Card tmp = this->cards[index1];
        this->cards[index1] = this->cards[index2];
        this->cards[index2] = tmp;
    }

    void shuffle() {
        /**
         * @brief Shuffles the deck by the amount of used space in the deck.
         * @note Time Complexity: O(N * M), where N is the number of used cards and M is the number of shuffles.
         * @example deck.shuffle(); // Shuffles the deck.
         */
        shuffle(this->used);
    }

    void shuffle(short numOfTimes) {
        /**
         * @brief Shuffles the deck a given number of times.
         * @param numOfTimes Short representing the number of times to replace a random card with another one.
         * @note Time Complexity: O(N * M), where N is the number of used cards and M is the number of shuffles.
         * @example deck.shuffle(5); // Shuffles the deck 5 times.
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
        /**
         * @brief Inserts a card into the deck if it's not already present.
         * @param crd Struct representing a card.
         * @return Boolean True if completed, false if not.
         * @note Time Complexity: O(N) on average due to indexOf() function.
         * @example deck.insert(Card("H", 1)); // Inserts an Ace of Hearts into the deck.
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
        /**
         * @brief Returns the index of a given card.
         * @param crd Struct representing a card.
         * @return Positive short representing the index if found, otherwise returns -1.
         * @note Time Complexity: O(N), where N is the number of used cards in the deck.
         * @example deck.indexOf(Card("H", 1)); // Returns the index of the Ace of Hearts.
         */
        for (short i = 0; i < this->used; i++) {
            if (this->cards[i].cardValue == crd.cardValue && this->cards[i].cardSuit == crd.cardSuit) {
                return i;
            }
        }
        return -1;
    }

    Card* getCards() {
        /**
         * @brief Returns the card array.
         * @return Array of Cards.
         * @note Time Complexity: O(1)
         * @example deck.getCards(); // Returns the card array.
         */
        return this->cards;
    }

    Card getCard(short index) {
        /**
         * @brief Returns the card at a given index.
         * @param index Short representing the index of the card.
         * @return The requested card.
         * @note Time Complexity: O(1)
         * @example deck.getCard(0); // Returns the first card in the deck.
         */
        return this->cards[index];
    }

    Card* del(Card crd) {
        /**
         * @brief Deletes and returns a given card.
         * @param crd Struct representing the card to delete.
         * @return A pointer to the deleted card, or nullptr if not found.
         * @note Time Complexity: O(N), where N is the number of used cards in the deck. (Due to indexOf() function)
         * @example deck.del(Card("H", 1)); // Deletes the Ace of Hearts from the deck.
         */
        short index = indexOf(crd);
        if (index != -1) {
            Card* c = &this->cards[index];
            this->swap(index, this->used - 1); // Corrected index
            this->used--;
            return c;
        }
        else {
            return nullptr;
        }
    }

    void eraseDeck() {
        /**
         * @brief Erases the deck by removing each card.
         * @note Time Complexity: O(N), where N is the number of used cards in the deck.
         * @example deck.eraseDeck(); // Erases the deck. 
         */
        while (this->used > 0) {
            this->del(this->cards[this->used - 1]);
        }
    }

    short getUsed() {
        /**
         * @brief Returns the used space of the deck.
         * @return Short representing the used space.
         * @note Time Complexity: O(1)
         * @example deck.getUsed(); // Returns the used space of the deck. (0-52)
         */
        return this->used;
    }

    void displayCards() {
        /**
         * @brief Displays all cards and their values to console (cout).
         * @note Time Complexity: O(N), where N is the number of used cards in the deck.
         * @example deck.displayCards(); // Displays all cards and their values to console.
         */
        string s = "";
        for (short i = 0; i < this->used; i++) {
            string c = cvtostr(this->getCard(i).cardValue); // Gets card values
            s = s + c; // Adds to main string
            s = s + " ";
        }
        cout << s;
    }

    bool checkDeckForCardWithValue(short cardValue) {
        /**
         * @brief Checks if the deck contains a card with a specific value.
         * @param cardValue Short representing the value to check for.
         * @return Boolean True if the deck contains a card with the specified value, false otherwise.
         * @note Time Complexity: O(N), where N is the number of used cards in the deck.
         * @example deck.checkDeckForCardWithValue(1); // Checks if the deck contains an Ace. 
         */
        for (short i = 0; i < this->used; i++) {
            if (this->getCard(i).cardValue == cardValue) {
                return true;
            }
        }
        return false;
    }
};

int main() {
    /**
     * @brief The main function.
     * @return Integer 0 upon exit success.
     * @note Time Complexity: O(N), where N is the number of cards in the deck.
     * @example main(); // Runs the program.
     * @example ./gofish.out, ./gofish.exe, ./gofish.so, etc; // Runs the program.
    */

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
    cout << Colors::HI_YELLOW << "8. The game ends when all cards are gone from the main deck" << Colors::RESET << endl;



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
    cout << Colors::BOLD_HI_RED << "GAME OVER" << Colors::RESET << endl;
    cout << "Player " << winner + 1 << " wins!" << endl;
    return 0;
}