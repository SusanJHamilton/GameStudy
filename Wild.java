/**
 * Auto Generated Java Class.
 */
public class Wild extends Area {
  private static String areaType = "Wild";
  
  public Wild() { 
    factory.probs.put("water", .3);
    factory.probs.put("dirt", .4);
  }
  public void spawnItems(){
    for (String x: factory.probs.keySet()){
      Double d=rnd.nextDouble();
      if (d<=factory.probs.get(x)){
        items.addItem(factory.getItem(x));
      }
    }
  }
  public static void main(String[] args) { 
    
  }
  
  /* ADD YOUR CODE HERE */
  
}
