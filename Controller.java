import java.util.*;

public class Controller
{
  // TODO. make a list of actions for given player and game
  // TODO. select an action at random from list and perform
  // TODO. play a game to completion.
  Boolean printDetails=true;
  
  // This maps (State,Action) to Value
  //  1 provides a score for every action that can be taken in a given state
  //  2  generated by a reinforcement model
  //  3 must be registered via registerValues() method before using it
  //  4 used by pickBestMove method
  
   HashMap<String,HashMap<String, Double>> stateActionValues;
  
  // Use this to find "reward" as score-previousScore
  double previousScore=0.0;
  // all possible actions in this game

    static String[] actions={
    "goTo Wild","goTo Market", "pickup dirt",  "pickup water",  "pickup mud", "pickup chamomile",
    "pickup chamomileTea",  "pickup mint",  "pickup mintTea",  "pickup herbalMedicine", "buy food",  "buy dirt",
    "buy water",  "buy mud", "buy chamomile","buy chamomileTea",  "buy mint",  "buy mintTea", "buy herbalMedicine",
     "sell dirt",  "sell water",  "sell mud", "sell chamomile","sell chamomileTea",  "sell mint",  
    "sell mintTea",  "sell herbalMedicine", "eat", "merge dirt water", "merge mint chamomile", "merge mint water",
    "merge chamomile water", "sleep 1" };
  
// THE game to control
   public Game game=new Game();
   double moveNum = 0.0;
   public Controller(Boolean printDetails){
     this.printDetails=printDetails;
   }
   public Controller(){}
   public static void main(String [] args)
  { 
    Controller controller = new Controller();
    controller.playGame("random");
  }
  
   // This is the default action - pick available action at random
   // Because this does not require knowledge of state values it can be a default method for the controller
   public String pickAction(String strategy)
   {
     if (strategy.equals("random"))
     {
       return pickRandomAction();
     }
     if (strategy.equals("best")){
       return pickBestAction();
     }
     //Half of the time, the "exploratory" strategy will play a move picking the best action, and the other half it will pick a random one
     if(strategy.equals("exploratory")) 
     {
      Random random = new Random();
       if(random.nextDouble() >= 0.25)
       {
         return pickBestAction();
       }
       else
       {
         return pickRandomAction();
       }
     }
     return "not supported";
   }
  public String pickRandomAction()
  {
    final NavigableMap<Double, String> actionList = new TreeMap<Double, String>();
    final Random random=new Random();
    double total = 0;
    double weight=1.0;
    String action;
    /* make a collection of POSSIBLE actions */
    for(String x:actions)
    {
     if (game.canDoMove(x))
     {
        total += weight;
        actionList.put(total, x);
     }
    }
    /* get a possible action randomly */
    double value = random.nextDouble() * total;
    action=actionList.higherEntry(value).getValue();
    if(printDetails){
      System.out.println(String.format("I choose to do %s",action));                     
    }
  /* do a random action */
    //System.out.println(game.doTurn(action));
    return action;
  }
public void registerValues(HashMap<String,HashMap<String, Double>> stateActionValues ){
  this.stateActionValues=stateActionValues;
}
// Pick the best action using known action values for this given state 
  public String pickBestAction(){
    String state=getState();
      HashMap<String, Double> actions = null;
      if(stateActionValues != null)
      {
        actions = stateActionValues.get(state);
      }
      double maxVal = 0.0;
      String maxAct="";
      if(actions!=null){
        for(String action: actions.keySet())
        {
          double val=actions.get(action);
          if (game.canDoMove(action)){
    
            if(maxVal < val)
            {
              maxVal = val;
              maxAct=action;
            }
           }
        }
      }
      // if we didn't find any action, we have to fall back on random action
      if (maxAct.equals("")){
        return pickRandomAction();
      }
      if(printDetails)
      {
        System.out.println(String.format("State is (%s) I do %s",state,maxAct));                     
      }
      return maxAct;
    }
  
  public void doMove(String strategy)
  {
    String action = pickAction(strategy);
    game.doTurn(action);
    double currentScore = game.player.getScore();
    moveNum++;
    if(printDetails)
    {
      System.out.println("The player's current score is " +currentScore);
    }
  }
  public void doAction(String action)
  {
    game.doTurn(action);
    moveNum++;
  }
   public double playGame(String strategy){
    moveNum = 0;
    game = new Game();
    while(game.player.getHealth() > 0)
    {
      doMove(strategy);
      if(moveNum >= 10000)
      {
          System.out.println("Ending game because the player lived 10,000 moves");
          break;
      }
    }
    if(printDetails){System.out.println("The player lived "+ moveNum+ " number of turns");}
    return moveNum;
   
   }
  public Double getMoveNum()
  {
    return moveNum;
  }  
  public String getState()
  {
    return game.getState();
  }
  public double getScore()
  {
    return game.getScore();
  }
}