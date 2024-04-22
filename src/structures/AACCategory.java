package structures;

/**
 * Class that creates AssociativeArrays which have keys of the image location and
 * values of the image icons. Methods to add, get both the location, directory and text
 * 
 * @author Lucas
 *
 */

public class AACCategory extends Object{
  //fields
  String name;
  AssociativeArray <String, String> something;

  //constructor
  public AACCategory (String name){
    this.name = name;
    this.something = new AssociativeArray<String,String>();
  }
  
  //methods
  public void addItem(String imageLoc, String text) throws NullKeyException{
    this.something.set(imageLoc, text);
  }  //addItem(String, String)

  public String getCurrentCategory() {
    return this.name;
  } //getCurrentCategory();

  public String[] getImage() {
    String[] val = new String[this.something.size];
    int j = 0;
    for (int i = 0; i < this.something.size; i++){
      if(this.something.pairs[i].key != null){
        val[j] = (String) this.something.pairs[i].key;
        j++;
      }
    }
    return val;
  } // getImage()

  public String getText(String imageLoc) throws KeyNotFoundException {
    return (String) this.something.get(imageLoc);
  } //getText(String)

  public Boolean hasImage(String imageLoc){
    return this.something.hasKey(imageLoc);
  } //hasImage(String)
}
