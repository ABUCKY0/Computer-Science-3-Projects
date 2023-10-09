#include <iostream>
#include <string>
#include <ctime>

using namespace std;

struct Card {
    string cardSuit;
    short cardValue;
};

string cvtostr(short cardValue) {
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
            cout << "! Returning -1";
            return -1;
        }
    }
}

class Deck {
private:
    Card cards[52];
    short used;
    string cs;
    short cv;

public:
    string suits[4] = { "H", "D", "C", "S" };
    short size = 52;

    Deck() {
        this->cv = 1;
        this->cs = "H";
        this->used = 0;

        for (string suit : suits) {
            for (short value = 1; value < 14; value++) {
                this->cards[this->used].cardSuit = suit;
                this->cards[this->used].cardValue = value;
                this->used++;
            }
        }
    }

    Deck(short size) {
        this->size = size;
        this->used = 0;
    }

    ~Deck() {};

    Card getCard(short index) {
        return this->cards[index];
    }

    void swap(short index1, short index2) {
        Card tmp = this->cards[index1];
        this->cards[index1] = this->cards[index2];
        this->cards[index2] = tmp;
    }

    void shuffle() { // public face
        shuffle(this->used);
    }

    void shuffle(short numOfTimes) {
        short randomNum1, randomNum2;
        srand(time(0));

        for (short i = 0; i < numOfTimes; i++) {
            randomNum1 = (rand() % this->used);
            randomNum2 = (rand() % this->used);
            this->swap(randomNum1, randomNum2);
        }
    }

    bool insert(Card crd) {
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
        for (short i = 0; i < this->used; i++) {
            if (this->cards[i].cardValue == crd.cardValue && this->cards[i].cardSuit == crd.cardSuit) {
                return i;
            }
        }
        return -1;
    }

    Card* getCards() {
        return this->cards;
    }

    Card* del(Card crd) {
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
        while (this->used > 0) {
            this->del(this->cards[this->used - 1]);
        }
    }

    short getValue() {
        return this->cv;
    }

    string getSuit() {
        return this->cs;
    }

    short getUsed() {
        return this->used;
    }

    void displayCards() {
        string s = "";
        for (short i = 0; i < this->used; i++) {
            string c = cvtostr(this->cards[i].cardValue);
            s = s + c;
            s = s + " ";
        }
        cout << s;
    }
};

int main() {
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

    short playerBooks[numOfPlayers];

    //Display Cards as a temporary debugging sanity check
    for (short i = 0; i < numOfPlayers; i++) {
        cout << "Player " << i + 1 << ": ";
        playerDecks[i].displayCards();
        cout << endl;
    }


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
        if (numOfPlayers == 2) {
            playerToFish = ((playerTurn + 1) % numOfPlayers == 0) ? 1 : 2;
        }
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

            // Take 
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

            if (!cardFound) {
                cout << "Go Fish!" << endl;
                playerDecks[playerTurn].insert(*deck.del(deck.getCard(0)));
                validCardFished = true;
            }
        }

        // Remove Sets of Cards where 4 cards have the same value
        for (short i = 0; i < playerDecks[playerTurn].getUsed(); i++) {
            short numOfCards = 0;
            for (short j = 0; j < playerDecks[playerTurn].getUsed(); j++) {
                if (playerDecks[playerTurn].getCard(i). cardValue == playerDecks[playerTurn].getCard(j).cardValue) {
                    numOfCards++;
                }
            }
            if (numOfCards == 4) {
                for (short j = 0; j < playerDecks[playerTurn].getUsed(); j++) {
                    if (playerDecks[playerTurn].getCard(i).cardValue == playerDecks[playerTurn].getCard(j).cardValue) {
                        playerDecks[playerTurn].del(playerDecks[playerTurn].getCard(j));
                    }
                }
            }
        }

        // Display Cards as a temporary debugging sanity check
        for (short i = 0; i < numOfPlayers; i++) {
            cout << "Player " << i + 1 << ": ";
            playerDecks[i].displayCards();
            cout << endl;
        }

        // Check if any player has 0 cards, and the main deck isn't empty
        bool allPlayersHaveCards = true;
        for (short i = 0; i < numOfPlayers; i++) {
            if (playerDecks[i].getUsed() == 0 && deck.getUsed() == 0) {
                playerDecks[i].insert(*deck.del(deck.getCard(0)));
            }
            if (playerDecks[i].getUsed() == 0) {
                allPlayersHaveCards = false;
            }
        }
        if (!allPlayersHaveCards) {
            break;
        }

        playerTurn = (playerTurn + 1) % numOfPlayers;
    }

    return 0;
}