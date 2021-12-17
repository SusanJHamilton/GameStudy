#
Game -- run main in this class to do manual game tests
Controller -- run main in this class to test computer-run games
ReinforcementModel -- run main in this class to collect machine learning data on test games
# Semantics
  state -- the players status: inventory, health,status, represented by a string
           the game state is just the state of the player in the game
           the controller state is just the state of the game it controls
           used by the ReinforcementModel evaluate the "value" of a game
          
  value -- the better the outcome of a game the more value it has. We optimise for value
            this version, the number of moves the player lived is used.
            inventory and money might be added to the value but first we want the algorithm to discover that instead.
  # Algorithm
  Every state has a value.
  For any state, every action has a value. Computed by the value of the state which arises after the action
  The value of a state is calculated as the maximum of the values of the actions available for that state.  
  
  The one place that does not work is when there are no actions available for a state. For example when the game is over.
  We used default value in that one case, assign the number of moves the player lived as a default value.
 
  # Game Study Classes
1. Item  represents objects found in game
    Has name, price, description
2. Player - has inventory of items, supply of coins, location
       interacts with areas to pick up spawned items
       interacts with players to buy/sell items
       merge items within his inventory
       move to areas or markets
3. Area   
     contains an inventory 
     can spawn items. 
     can giveItem
4. Inventory
    a collection of items. Players and areas can have inventories  
  methods: add and remove item
5.  item factory - creates items, mediates item interactions which create new items 
6.  Controller -- runs a game making random moves all the way to end
7.  ReinforcementModel. This will run games and keep the results
  
Verb
  char moves from area
  chars interact with each other
  items spawn
  add stuff to inventory
  char health changes and/or char dies
  
  Machine learning:
  The actor in all models is the player. interacts with other players and with areas
  performance metric: as measured at end of cycle our choices are: wealth, survival, wealth, 
  Possible approaches:
  * Classification: 
      decisions classified as "good" or "bad" very simple but neglects state
  * logistic regression
     a small extension of classifier determine a goodness number betwee zero and one, create some probability for actor
  *   Reinforcement learning: discover Q(s,a) s=state, a=action to take when in that state, Q=reward 
     update Q with Q-> (1-lambda)Q+lambda(r+delta Q')
     lambda is learning rate. Q' is maximum Q of new state, delta is so-called discount rate         
 

  Adjective
char  health
      wealth
items
    name
    description
    price
    * optional shelf life
    * prob of spawning factored into item factory, need to work into areas

# Controller
                                            Action:
Game has entry method doMove having string input. It will parse the string into methods to call and args to call
them with. This makes a convenient controller method since all actions can be represented by string.

                                            
                                            