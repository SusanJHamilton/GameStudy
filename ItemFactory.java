import java.util.HashMap;

public class ItemFactory{
  public HashMap<String,Item> items;

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
    items.put("dirt", new Item("dirt",0.01,"find it on the ground everywhere"));
    items.put("mud",new Item("mud",  0.52,"cheap to make and sell"));
  }
  public Item merge(String x,String y){
	return merge(getItem(x),getItem(y));
  }
  public Item merge(Item x,Item y){
     if( x.name.equals("dirt") && y.name.equals("water")) return getItem("mud");
     if( y.name.equals("dirt") && x.name.equals("water")) return getItem("mud");
      return null;
  }
 public static void main(String[] args){
   ItemFactory f=new ItemFactory();
   Item x=f.getItem(args[0]);
   System.out.println(x.name+" is "+x.description+" and can be had for a mere "+x.price);
   Item merged=f.merge(args[0],args[1]);
   System.out.println(merged.name+" is "+merged.description+" and can be had for a mere "+merged.price);
 
 }
}
