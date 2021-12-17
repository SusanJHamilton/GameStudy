/**
 * Any bag of items.
 */
import java.util.*;

public class Inventory {
   /* map of item name and count of items in the inventory */
   public HashMap<String,Integer> itemCounts = new HashMap<String,Integer>();
   public HashMap<String,Item> items = new HashMap<String,Item>();
   /* todo: make the list only have items if count>0 */
   public List<Item> getItems(){
    ArrayList<Item> itemList=new ArrayList<Item>(items.values());
    Collections.sort(itemList);
    return itemList;
  }
   /* add an item: increment the count of that item. Return number of items added.  */
   public int addItem(Item x){
     if(x==null)
     {
       return 0;
     }
     String itemName=x.getName();
     items.put(itemName,x);
     Integer count=itemCounts.get(itemName);
     if (count==null){
       count=0;
     }
     itemCounts.put(itemName,count+1);
     return 1;
   }

   public Item getItem(String itemName){
     return items.get(itemName);
   }
   // returns number items removed
   public int removeItem(String itemName){
     Integer count=itemCounts.get(itemName);
     if (count==null){
       count=0;
     }
     if (count>0){
      itemCounts.put(itemName,count-1);
     }
     if (count>0) return 1;else return 0;
   }
   
   public boolean containsItem(String itemName)
   {
     Integer count = itemCounts.get(itemName);
     return ((count != null) && (count > 0));
   }
   public String describe()
   {  
     String s="";
     for (Item x:getItems())
     {
       int numx=itemCounts.get(x.getName());
       if(numx > 0) 
       {
         s=s+"  "+numx+" of "+x.describe()+"\n";
       }
     }
     return s;
   }
  public static void main(String[] args) { 
    
  }
  
  /* ADD YOUR CODE HERE */
  
}
