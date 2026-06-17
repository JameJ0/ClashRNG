# My Personal Project

## Roguelike video game

For the term project, I would like to make a roguelike video game.

What is a *roguelike* game?

- RPG (role-playing game)
- Dungeon crawling: the levels will be different rooms that can be cleared
- Procedurally generated content (items, enemies, and rewards can be different at every level)
- Played on the 2D grid
- Playthrough ends when the player's health drops to 0
- Player loses all their items upon death, meaning the only "progress" is the player's knowledge and skill

The application will display a heads-up display (HUD) of the player's stats (like health and defence), as well as the current level that the player is in. There will also be background, the player model itself, enemy entities, and sometimes background obstacles on the playfield. This application is intended for people who enjoy a challenge and prefer PvE (player versus environment) games over online multiplayer games. Don't worry if the game sounds difficult; you will be able to select the difficulty level in the lobby screen.

This project is of interest to me because I've always played video games my whole life, but have never thought to create one myself. I've attempted to create a game in high school, but the code was a mess because of a lack of coding experience. I hope that through this project, I'll have another shot at making a well-organized network of code and classes that is (mostly) bug-free, easy to modify for the developer, and can be easily read by someone else.

## How to play?

WASD to move, and if you collide with an enemy, they take damage. Uou get coins when you kill an enemy, or you manually sell items in your inventory (click on the item yo u want to sell). use the sell all common button to sell all common items. click the save button to save, and exit the game by closing the window.

## User Stories

- As a user, I want to have the option to select different classes of characters at the start of the game
- As a user, I want to be able to see my health, mana level, and the list of items I currently own
- As a user, I want to be able to add a dropped item on the ground to my inventory
- As a user, I want to be able to remove an enemy from the play field when its health drops below 0
- As a user, I want to be able to save the game state (enemies, the player) to file
- As a user, I want to be able to load my save from file (if I so choose)  

(Phase 3 extras)
- As a user, I want to be able to click on an item and sell it for coins
- As a user, I want to be able to quick sell the low-rarity common items in one simple click

## Phase 4: Task 2

Fri Nov 28 05:29:03 PST 2025
Goblin spanwed at 1422, 54.

Fri Nov 28 05:29:03 PST 2025
Goblin spanwed at 242, 629.

Fri Nov 28 05:29:03 PST 2025
Goblin spanwed at 234, 289.

Fri Nov 28 05:29:03 PST 2025
Goblin spanwed at 21, 5.

Fri Nov 28 05:29:03 PST 2025
Goblin spanwed at 453, 469.

Fri Nov 28 05:29:07 PST 2025
Uncommon Item dropped at 453, 469.

Fri Nov 28 05:29:07 PST 2025
Goblin defeated.

Fri Nov 28 05:29:07 PST 2025
Goblin spanwed at 280, 626.

Fri Nov 28 05:29:07 PST 2025
Picked up an item: Uncommon Item

Fri Nov 28 05:29:09 PST 2025
Goblin defeated.

Fri Nov 28 05:29:09 PST 2025
Goblin spanwed at 1022, 499.

Fri Nov 28 05:29:09 PST 2025
Goblin defeated.

Fri Nov 28 05:29:09 PST 2025
Goblin spanwed at 92, 510.

Fri Nov 28 05:29:10 PST 2025
Common Item dropped at 92, 510.

Fri Nov 28 05:29:10 PST 2025
Goblin defeated.

Fri Nov 28 05:29:10 PST 2025
Goblin spanwed at 220, 880.

Fri Nov 28 05:29:10 PST 2025
Picked up an item: Common Item

Fri Nov 28 05:29:13 PST 2025
Common Item dropped at 234, 289.

Fri Nov 28 05:29:13 PST 2025
Goblin defeated.

Fri Nov 28 05:29:13 PST 2025
Goblin spanwed at 1425, 73.

Fri Nov 28 05:29:13 PST 2025
Picked up an item: Common Item

Fri Nov 28 05:29:16 PST 2025
Discarded 1 common items from player inventory.

## Phase 4 Task 3

As you can see from the UML, the GamePanel class and the GameFrame class both have each other, which shouldn't really happen. Usually, only GameFrame needs to have GamePanel. This is needed to trigger stuff like saving the game, because mouse clicks are handled in the GamePanel class. For now, I have no idea how I would refactor this so that this 'dual dependency' isn't needed. Maybe I can make an interface that GameFrame would implement, with methods like onSaveGame(), so that GamePanel would only need to hold a reference to the interface type. For example, the GamePanel class would have the field interface, then the constructor would have the interface as a parameter, and whenever it needs to save, it would call the interface.saveGame(). It doesn't seem that much better to implement it this way, but it would be nice to clear up the confusion of having each other as a field.

Another place where I can refactor is definitely the GameState class, because it has way too many responsibilities. Data storage, game logic, combat simulation, etc. This is way too many responsibilities for one class. If I had more time, perhaps I would've refactored some of those responsibilities to other classes, like a physics engine class or a combat manager class. So during update(), I would just pass the entities to those classes, and get the results. After the refactor, I can change and test different component classes without tinkering and creating new bugs with the core GameState.
