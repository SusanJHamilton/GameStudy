/**
 * Auto Generated Java Class.
 */
import java.util.Random;
import java.util.List;

class Area {
  protected ItemFactory factory=new ItemFactory();
  protected Random rnd=new Random();
  public Inventory items = new Inventory();
  
  public Item getItem(String x){
    int n = items.removeItem(x);
    if( n > 0)
    {
      return items.getItem(x);
    }
    return null;
  }
  public boolean hasItem(String x)
  {
    return items.containsItem(x);
  }
  
  public List<Item> getItems(){
    return items.getItems();
  }
  // Market needs any and all items 
   public void fillItems(){
    for (String x: factory.probs.keySet()){
        items.addItem(factory.getItem(x));
    }
  }

// wild should only spawn items
  public void spawnItems(){
    for (String x: factory.probs.keySet()){
      Double d=rnd.nextDouble();
      if (d<=factory.probs.get(x)){
        items.addItem(factory.getItem(x));
      }
    }
  }
  public boolean containsItem(String itemName)
  {
    return items.containsItem(itemName);
  }
  /* returns amount of food sold */
  public int sellFood()
  {
    /* by default dont sell nothing */
    return 0;
  }
  public Area() 
  {
    factory.probs.put("water", .3);
    factory.probs.put("dirt", .4);
  }
     
  public String describe()
  {
    return "You see these objects here: \n"+items.describe();
  }
  public static void main(String[] args) { 
     Area A=new Area();
     A.spawnItems();
    for (Item x:A.getItems()){
      System.out.println("Area spawned item "+x.getName());
    }
  }
    
}
