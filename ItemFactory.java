import java.util.HashMap;
/* Creates all items in the game. */
public class ItemFactory{
  public HashMap<String,Item> items;
  public HashMap<String,Double> probs;
public HashMap<String,HashMap<String,String>> mergemap;// TODO: replace with an implementation of DoubleStringMap  

  
  public ItemFactory(){
    makeItems();
  }
  public Item getItem(String name){
    Item x= items.get(name);
    if (x==null) {return new Item("unknown",0,"not an item in this game");}
    return x;
  }
  private void makeItems(){
    items = new HashMap<String,Item>();
    items.put("water",new Item("water",0.50,"wet and drinkable"));
    items.put("dirt", new Item("dirt",0.01,"found on the ground everywhere"));
    items.put("mud",new Item("mud",  0.52,"cheap to make and sell"));
    
    probs = new HashMap<String,Double>();
    probs.put("water", .3);
    probs.put("dirt", .4);
    probs.put("mud", .15);
    
    mergemap=new HashMap<String,HashMap<String,String>>();
    putmerge("water","dirt","mud");
  }
  
  public void putmerge(String x,String y, String z){
  if (mergemap.get(x)==null) mergemap.put(x,new HashMap<String,String>());
  mergemap.get(x).put(y,z);
  if (mergemap.get(y)==null) mergemap.put(y,new HashMap<String,String>());
  mergemap.get(y).put(x,z);
  }

  public String message(Item x){
    String scarcity;
    if (x==null){
      return "Item is undefined ";
    }
    String msg =(x.name+" is "+x.description+" and can be had for a mere "+x.price);
     if(probs.get(x.name)> .2){
         scarcity = "common";
     }
     else{
        scarcity = "rare";
     }
     return msg + " and is " + scarcity;
  }
  public Item merge(Item x,Item y){
    return merge(x.name,y.name);
  }
  public Item merge(String x,String y){
    if (mergemap.get(x)==null) return null;
    if (mergemap.get(x).get(y)==null) return null;
    return getItem((mergemap.get(x)).get(y));
  }
  public static void main(String[] args){
    ItemFactory f=new ItemFactory();
    Item x=f.getItem("dirt");
    Item y=f.getItem("water");
    System.out.println(f.message(x));
    System.out.println(f.message(y));
    Item merged=f.merge(x,y);
    System.out.println(f.message(merged));
  }
}
