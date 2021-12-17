/* All items in the game */
/* todo: add a double probability field and include it in initializer and constructor */
public class Item implements Comparable<Item>{
  public double price;
  public String description;
  public String name;
  
  public String getName()
  {
    return name;
  }

  @Override
  public int compareTo(Item t)
  {
    return name.compareTo(t.name);
  }
  
  public double getPrice()
  {
    return price;
  }
  public String describe()
  {
    return name+": "+description;
  }  
  public Item(String n,double p,String d)
  {
    name=n;
    price=p;
    description=d;
  }
}

