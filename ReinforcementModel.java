/**
 * Auto Generated Java Class.
 * Contains algorithm for figuring out value of each state
 * stateValues -- maximum value over all actions possible from this state
 * stateActionValues --  value of each action that might be taken in state
 * 
 */
import java.io.*; 
import java.util.*;

public class ReinforcementModel {
   
  /* <State, < Action, Value > >*/
  public HashMap<String,HashMap<String, Double>> stateActionValues = new HashMap<String,HashMap<String, Double>>();
  public HashMap<String,Double> stateValues = new HashMap<String,Double>();
  public String strategy="random"; //Default Value
  double lamda = 0.1;
  /* debugging helplers */
   void  dumpSizes()
   {
      int actions=0;
     for(  HashMap<String,Double> a:stateActionValues.values()){ actions=actions+a.size();}
     System.out.println(" StateActions map has "+stateActionValues.size()+" states and "+actions+" actions");
     System.out.println(" States map has "+stateValues.size()+" states");
}

  public void dumpActionValues(){
    System.out.println("Dump of StateActionValues data set");
    for(String state:stateActionValues.keySet())
    {
    System.out.println("  State: "+state);
    HashMap<String,Double> actions = stateActionValues.get(state);
      for(String action:actions.keySet())
      {
        System.out.println("    action: "+action+"   value  "+actions.get(action));
      }
    }
  }
  public void dumpStateValues(){
    System.out.println("   Dump of States");
      for(String state:stateValues.keySet())
      {
        System.out.println("    State: "+state+"   value  "+stateValues.get(state));
      }
    }
  public double averageStateValues()
  {  
    double tot=0;
    for(double x:stateValues.values())
    {
      tot+=x;
    }
    return tot/stateValues.size();
  }
  
// a single datapoint evaluation of state is the lifetime of player after that state  
// returnn the single data point but update the values map.
  public Double evaluateState(String state, Controller ctrl)
  {
    if( !stateActionValues.containsKey(state))
    {
      stateActionValues.put(state, new HashMap<String, Double>());
      stateValues.put(state, 0.0);
    }
    if(endCondition(ctrl))
    {
      return 0.0;  // state is worth zero if player is dead
    }
    String action = ctrl.pickAction(strategy);
    // he lives one day then performs action so add 1
    double actionVal = 1+evaluateAction(action, ctrl);
    Double existingActionVal = stateActionValues.get(state).get(action);
    if(existingActionVal == null)
    {
      existingActionVal = 0.0;
    }
//System.out.println("MoveNum "+ctrl.getMoveNum()+" Action val "+actionVal+" existingActionVal "+existingActionVal);
    stateActionValues.get(state).put(action,(existingActionVal*(1-lamda)+lamda*actionVal));
    double maxVal = getMax(stateActionValues.get(state));
    double existingMax = stateValues.get(state);
    //if (maxVal > existingMax)
    {
      stateValues.put(state, maxVal);
    }
    return actionVal;
  }
  public Double evaluateAction(String action, Controller ctrl)
  {
    ctrl.doAction(action);
    return evaluateState(ctrl.getState(),ctrl);
  }
  
  public Boolean endCondition(Controller ctrl)
  {
    if(ctrl.getScore() == 0)
    {
      return true;
    }
    return false;
  }
  public ReinforcementModel() { 
    /* YOUR CONSTRUCTOR CODE HERE*/
  }
  public Double getMax(HashMap<String, Double> doubleMap)
  {
    double maxVal = 0.0;
    for( double x: doubleMap.values())
    {
      if(maxVal < x)
      {
        maxVal = x;
      }
    }
    return maxVal;
  }
// run a game and evaluate the state.   
// returns the number of moves made in this game
  public double iterate(Boolean showMoves)
  {
    Controller ctrl = new Controller(showMoves);
    evaluateState(ctrl.getState(),ctrl);
    return ctrl.getMoveNum();
  }
  public void sweep(int nTests) 
  {
    strategy = "exploratory";
    PrintWriter out = null;
    try
    {
      out=new PrintWriter(new BufferedWriter(new FileWriter("ReinforcementResults.csv")));
    }catch(Exception e)
    {
      e.printStackTrace();
    } 
    out.println("NRuns,Lifetime");
    for(int nRuns=0; nRuns<4; nRuns++)
    {
      stateActionValues = new HashMap<String,HashMap<String, Double>>();
      stateValues = new HashMap<String,Double>();
    int totalGames=1;
    // iterating once first so that the total games ends up a nice power of 2
    iterate(false);
    for(double nGames = 1; nGames <= 14000; nGames = nGames*1.2)
    { double totalmoves=0.0;
      for(int i=0;i<nGames;i++)
      {
        totalmoves+=iterate(false);
      }
      totalGames += nGames;
      double avgmoves=totalmoves/nGames;  
      out.println(totalGames + "," +average(nTests));    
      System.out.println("Games Played = " + totalGames + " Average = " +average(nTests)+" Average state value "+averageStateValues()+" Random move average = "+avgmoves);
    }
    }
    out.close();
  }
  public double average(int nTests)
  {
    Controller ctrl = new Controller(false);
    ctrl.registerValues(stateActionValues);
    double avg = 0;
    for(int j = 0; j < nTests; j++)
    {
    
    avg = avg + ctrl.playGame("best");
    
    }
    avg = avg/nTests;
    return avg;
  }
 
  public static void main(String[] args) 
  {
    ReinforcementModel x = new ReinforcementModel();
//x.test();
    x.sweep(100);
  }
  public void test(){ 
      Scanner input = new Scanner(System.in);
    Integer nGames =  input.nextInt();
    Integer n =  input.nextInt();
    input.close();
    for(int i=0;i<nGames;i++)
    {
      if (i%10000==0){System.out.print(" "+i);}
      iterate(false);
    }
    System.out.println();
    dumpActionValues();
    dumpStateValues();
    dumpSizes();
    // now play a game
    Controller ctrl = new Controller(false);
    ctrl.registerValues(stateActionValues);
    double avg = 0;
    for(int j = 0; j < n; j++)
    {
    
    avg = avg + ctrl.playGame("best");
    
    }
    avg = avg/n;
    System.out.println("The average is: " + avg);
  /* ADD YOUR CODE HERE */
  } 
}
