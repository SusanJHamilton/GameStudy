# Game Study Classes
1. Item  represents objects found in game
    Has name, price, description
2. Player - has inventory of items, supply of coins, location
       interacts with areas to pick up spawned items
       interacts with players to buy/sell items
       merge items within his inventory
       move to areas or markets
3. Area   
4. Inventory
    a collection of items. Players and areas can have inventories  
5.  item factory - creates items, mediates item interactions which create new items 
  
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

