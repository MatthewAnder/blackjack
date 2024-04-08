# Jack n' Co

## A game of blackjack with a little twist.

### What will the application do? <br>
The application is a game of **blackjack**, but with a twist which is the user can take one card and hold it.
Later, the user can put it back again and can do some sort of **combo**. Instead of receiving the card from the dealer, 
the user can use his or her own cards. The combos can earn the user extra **money**. The leaderboard would showcase the amount of money the users made in an hour.
### Who will use it? <br>
Mostly people that is **bored** with work and university life will play this game for **entertainment**.

### Why is this project of interest to you? <br>
I am interested in making this game because of the logic behind the game. It is simple yet complex at the same time.
<br>

### User Stories
- As a user, I want to be able to press a button that says stand, hit, and play combo.
- As a user, I want to be able to hit and the card will show one by one.
- As a user, I want to get notified when I get a jackpot.
- As a user, I want to be able to gain money from winning and lose money.
- As a user, I want to keep track of my playing history.
- As a user, I want to save my money and can choose to save my history session.
- As a user, I want to load my session history any time.
- As a user, I want to buy accessories with my money  (if time permits).

# Instructions for Grader
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by playing the game
and using the "HIT" button.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by playing the game
and you will see the history. At the same time, you can load the history onto the current history. Thus, when you check
the history, it will be the combination of the current and the loaded history.
- You can locate my visual component by playing the game and checking the history.
- You can save the state of my application by pressing the "Save History" button.
- You can reload the state of my application by pressing the "Load History" button. This button is in the home page.

## Phase 4: Task 2
Note: the multiple "add a game to the history" indicates when you load the history into the game.
- Add a game to the history
- Add a game to the history
- Add a game to the history
- Add a game to the history
- Add a game to the history
- Add a game to the history
- Add a game to the history
- Add a game to the history
- Add a game to the history
- Add a game to the history
- Add a game to the history
- Add a game to the history
- $12 is taken from player's bank account.
- Resets User's hand
- Resets Dealer's hand
- Shuffling card!
- Card is King Diamonds
- King Diamonds is given to User
- Shuffling card!
- Card is 7 Hearts
- 7 Hearts is given to User
- Shuffling card!
- Card is Ace Spades
- Ace Spades is given to Dealer
- Shuffling card!
- Card is 4 Diamonds
- 4 Diamonds is given to Dealer
- Shuffling card!
- Card is 10 Spades
- 10 Spades is given to Dealer
- Shuffling card!
- Card is Ace Spades
- Ace Spades is given to Dealer
- Add a game to the history
- $100 is taken from player's bank account.
- Resets User's hand
- Resets Dealer's hand
- Shuffling card!
- Card is 2 Spades
- 2 Spades is given to User
- Shuffling card!
- Card is King Clubs
- King Clubs is given to User
- Shuffling card!
- Card is 4 Clubs
- 4 Clubs is given to Dealer
- Shuffling card!
- Card is 5 Spades
- 5 Spades is given to Dealer
- $200 is taken from player's bank account.
- Shuffling card!
- Card is 7 Spades
- 7 Spades is given to User
- Shuffling card!
- Card is King Clubs
- King Clubs is given to Dealer
- Add a game to the history

## Phase 4: Task 3
By looking at the UML diagram, I am able to see some arrows are pointing to one class and also to a class where it is
pointing to that class. I mean is that a lot of class is referencing class A and say class B has a field of class A.
Then, there is also a lot of classes that points to B too. I think we can refactor this, so that every class that is trying
to access class A points at class B. This would lessen the arrows and make a simpler diagram.
Another thing is that in the HomeGUI class, I observe a lot of repetitive code where it could have been
wrapped in a method, so we can call it multiple times with less lines of code needed.