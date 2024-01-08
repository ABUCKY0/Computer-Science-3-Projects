#include <iostream>
#include <string>
#include <ctime>

using namespace std;

struct Card {
    string cardSuit;
    short cardValue;
};

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
            string c = to_string(this->cards[i].cardValue) + this->cards[i].cardSuit;
            s = s + c;
            s = s + " ";
        }
        cout << s;
    }
};

int main() {
    Deck deck;
    Deck player1(26);
    Deck player2(26);

    deck.shuffle(52);
    cout<<"Main Deck: ";
    deck.displayCards();

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
    
    cout<<"\n\nPlayer 2 Deck: ";
    player2.displayCards();
    
    deck.eraseDeck();
    cout<<"\n\nNew Main Deck: ";
    deck.displayCards();
    
    return 0;
}