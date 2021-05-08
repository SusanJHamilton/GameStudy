/* All items in the game */
/* todo: add a double probability field and include it in initializer and constructor */
public class Item{
  public double price;
  public String description;
  public String name;
  
  public Item(String n,double p,String d){
    name=n;
    price=p;
    description=d;
  }
}
