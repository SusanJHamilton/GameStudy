/**
 * Auto Generated Java Class.
 */
import java.util.Scanner;
public class Game {
  public Area wild = new Area();
  public Area market = new Area();
  public Player player = new Player();
  public int clock=0;
 
  public Game() { 
    wild.spawnItems();
    wild.spawnItems();
    wild.spawnItems();
    market.fillItems();
  }

  public void playTestGame()
  {
    player.goTo("Wild");
    System.out.println(player.reportStatus());
    System.out.println(player.getOlder());
    System.out.println(player.buy("dirt",wild));
    System.out.println(player.sell("dirt"));
    System.out.println(player.buy("food",wild)); 
    System.out.println(player.pickUpItem("dirt",wild));
   player.goTo("Market");
   System.out.println(player.buy("food",market));
  
  }
  /* returns true if action can be performed */
  public boolean canDoMove(String action)
  { 
    String[] sentence = action.split(" ");
    String verb = sentence[0].toLowerCase();
    String noun="";
    if (sentence.length>1)
    {
      noun = sentence[1];
    }
    String secondNoun = "";
    if(sentence.length>2)
    {
      secondNoun = sentence[2];
    }
   switch(verb){
      case "report":
      case "status":
      case "describe":
      case "score":
      case "sleep":
        // disable sleep for now
       return false;
     case "buy":
        return player.canBuy(noun, market);
      case "sell":
        return player.canSell(noun);
      case "goto":
        return player.canGoTo(noun);
      case "pickup":
        return player.canPickUpItem(noun, wild);
      case "eat":
        return player.canEat();
      case "merge":
        return player.canMerge(noun, secondNoun);
      default:
        return false;
     }
  }
  public String doMove(String action)
  {
    String[] sentence = action.split(" ");
    String verb = sentence[0].toLowerCase();
    String noun="";
    if (sentence.length>1)
    {
      noun = sentence[1];
    }
    String secondNoun = "";
    if(sentence.length>2)
    {
      secondNoun = sentence[2];
    }
   
    switch(verb){
      case "report":
      case "status":
      case "describe":
        switch(noun)
        {
          case "place":
            if (player.location.equals("Wild")) 
            {
               return "You are in the wild. \n"+wild.describe();
            }
            if (player.location.equals("Market")) 
            {
              return "You are in the market \n"+market.describe();
            }
        case "":
        case "me" :
        case "player":
          return player.describe();
        }
      case "buy":
        return player.buy(noun, market);
      case "sell":
        return player.sell(noun);
      case "goto":
        return player.goTo(noun);
      case "pickup":
        return player.pickUpItem(noun, wild);
      case "eat":
        return player.eat();
      case "merge":
        return player.merge(noun, secondNoun);
      case "score":
        return "Your score is: " + player.getScore();
      case "sleep":
        return sleep(noun);
      default:
        return "I don't know how to do " +verb +"!!!!";
    }
  }
  
  public String sleep(String days)
  {
    int numDays = Integer.parseInt(days);
    clock = 0;
    player.getOlder(numDays);
    return "You slept for " +numDays + " days straight!!";
  }
  
  public void takeTurn(){
    Scanner input = new Scanner(System.in);
    String action =  input.nextLine();
    System.out.println(doTurn(action));
    input.close();
  }
  public String doTurn(String action) 
  {
    if(player.getHealth() <= 0)
    {
      return "You're dead lol";
    }
    
    String result=doMove(action);
    clock=clock+1;
    // get older each turn makes the health more accurate
    result=result+"\n"+player.getOlderByOneTurn();
    
    // spawn items only once per day
    if (clock>player.TURNSPERDAY)
    {
 //     result=result+"\n"+player.getOlder();
      clock=0;
      wild.spawnItems();
    }
    return result;
  }
  public String getState()
  {
    return player.getState();
  }
  public double getScore()
  {
    return player.getScore();
  }
  public static void main(String[] args) { 
    Game game = new Game();  
    do{
    game.takeTurn();
    }
    while(true);
  }

  
  /* ADD YOUR CODE HERE */
  
}
