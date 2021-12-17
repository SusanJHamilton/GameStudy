/**
 * Auto Generated Java Class.
 */
import java.util.Collections;

public class Player {
    public Inventory items = new Inventory();
    public ItemFactory merger = new ItemFactory();

    // Initial player values
    public String location = "Wild";
    private double coins = 1;
    private double health = 1;
    private int food = 2;
    private int age = 0;
    
// game and player constants
    double FOODVALUE=1.0;      //How much health to add after eating
    double HEALTHY=2.0;        // Minimum health value for "Healthy" state
    int    TURNSPERDAY=5;    //Number of clocks per day
    double HEALTHPERDAY=1.0;  //How much health to lose each day

    public Player() 
    { 
    }
  
    public String buy(String itemName,Area currentLoc)
  { 
    if( location.equals("Market"))
    {
      if (itemName.equals("food"))
      {
        if(coins >= 1)
        {
         food+=1;
         coins-=1;
         return "You got some tasteeee food";
        }
        return "You are poor >:(. starve.";
      }
       Item x=currentLoc.getItem(itemName);
       if(x==null)
       {
          return itemName+" does not exist here";
       }
       if (x.getPrice()>coins)
       {
         return "You cannot afford "+itemName;
       }
       coins-=x.getPrice();
       items.addItem(x);
          return "You bought a shiny new "+itemName;
    }
    return "You cannot buy here";
  }
 public boolean canBuy(String itemName,Area currentLoc)
 {
     Item x=currentLoc.getItem(itemName);
     return ( location.equals("Market")&&(((itemName.equals("food")) && (coins>=1))|| ((x!=null)&&(coins>=x.getPrice()))));
  }
  public double getHealth()
  {
    return health;
  }
  public boolean canSell(String itemName){
    Item item=items.getItem(itemName);
    return ((item!=null)&&(items.containsItem(itemName))&&(location.equals("Market")));
  }
  
  public String sell(String itemName)
  {
     Item item=items.getItem(itemName);
     if (canSell(itemName))
     {
      items.removeItem(itemName);
      coins+=item.getPrice();
      return ("Kaching");
    }
    return "You can't sell "+itemName+" here";
    }
   public String describe()
   {
     String s="You are a player\n";
     s+=reportStatus();
     s+="You have \n";
     s+=items.describe();
     return s;
   }
   public String reportStatus()
   {
     String s="Your health is "+health+"\n";
     s=s+"You have "+coins+" coins\n";
     s=s+"You have "+food+" foods\n";
     s=s+"You are "+age+" days old\n";
     return s;
   }   
   public boolean canGoTo(String area)
   {
     return (!location.equals(area)&&(area.equals("Wild")||area.equals("Market")));
   }
     
  public String goTo(String area){
    location = area;
    if(location.equals("Wild")){
      return "You see a wilderness area. I wonder is there is stuff here";
    }
    if(location.equals("Market")){
      return "A crowded area. Looks like some kind of commerce going on here";
    }
    return "this area does not exist :)";
  
  }
  
  // only if it is in wild
  public boolean canPickUpItem(String item,Area place)
  {
    return ("Wild".equals(location)&&place.hasItem(item));
  }
  public String pickUpItem(String item,Area place)
  {
    if("Wild".equals(location))
    {
      int numberAdded=items.addItem(place.getItem(item));
      if(numberAdded>0)
      {
        return "Picked up "+item;
      } else {
        return "There is no "+item+" here";
     }
    }
    return "That stuff isn't free here, Bro";
  }
  public boolean canMerge(String x,String y)
  {
    return ((items.containsItem(x) && items.containsItem(y))&& merger.canMerge(x,y));
  }
    
  public String merge(String x, String y)
  {
    if(items.containsItem(x) && items.containsItem(y))
    {
      Item mergedItem = merger.merge(x,y);
      if(mergedItem == null)
      {
        return "These items cannot be combined.";
      }
      items.addItem(mergedItem);
      items.removeItem(x);
      items.removeItem(y);
      //return "You combined " +x + " and " +y + "to get" + mergedItem.getName();
      return String.format("You combined %s and %s to get %s",x,y,mergedItem.getName());
    }
    return "You do not have both these items in your inventory.";
  }
    // unique idenetifier for any state
   // State represents what the player has, his health, his wealth
   // concat each item in inventory sorted, binary representing health and one representing wealth, and location
   public String getState()
   {
     String s="";
/*     for(Item x:items.getItems())
     {
       int numx = items.itemCounts.get(x.getName());
       if(numx > 0) 
       {
         s = s + x.getName()+ " , ";
       }
     }
  */
     if(health > HEALTHY)
     {
       s += "full , ";
     }
     else
     {
       s += "hungry level "+health+"  , ";
     }
     if( coins >= 1)
     {
       s += "wealthy , ";
     }
     else
     {
       s += "poor , ";
     }
     if(food > 0)
     {
       s+= "has food , ";
     }
     else
     {
       s += "pantry empty , ";
     }
     s += location + " , ";
     return s;
     
   }
  public double getScore()
  {
    if(health <= 0)
    {
      return 0.0;
    }
    double yourScore = coins+food+health;
    for (Item x:items.getItems())
    {
       int numx = items.itemCounts.get(x.getName());
       if(numx > 0) 
       {
         yourScore = yourScore + (numx * x.getPrice());
       }
     }
     return yourScore;
   }
  
  
  public String getOlder()
  {
     health -= HEALTHPERDAY;
     age += 1;
     return "You are " + age + " days old";
  }
   public String getOlder(int days)
  {
     health -= HEALTHPERDAY*days;
     age += days;
     return "You are " + age + " days old";
  }
   public String getOlderByOneTurn()
  {
     health -= HEALTHPERDAY/TURNSPERDAY;
     return "Health: "+health;
  }
   public boolean canEat()
   {
     return (food>0);
   }
  public String eat(){
    if( food > 0 ){
       health += FOODVALUE;
       food -= 1;
       return "yum!";
    } else{
         return "you have no food :(";
    }
  }
  
  public static void main(String[] args) { 
    
  }
  
  
}
