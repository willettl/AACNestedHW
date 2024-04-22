package structures;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Class that creates and associtive array of category names and AACCategories for the home page
 * of the GUI. Has methods to add, get all the catgories, get all the images, etc
 * 
 * @author Lucas
 *
 */

public class AACMappings extends Object{
  //fields
  public AssociativeArray <String, AACCategory> arr;
  AACCategory home;
  AACCategory current;

  //constructor
  public AACMappings(String filename) throws NullKeyException, KeyNotFoundException, FileNotFoundException{
    this.home = new AACCategory("");
    this.current = this.home;
    this.arr = new AssociativeArray<String, AACCategory>();
    this.arr.set("", this.current);
    //
    Scanner eyes = new Scanner(new File(filename));
    while(eyes.hasNext()){
      String tmp = eyes.nextLine();
      String[] tmps = tmp.split(" ", 2);
      if(tmp.charAt(0) != '>'){
        this.current = new AACCategory(tmps[1]);
        this.arr.set(tmps[0], this.current);        
        this.home.addItem(tmps[0], tmps[1]);
      } else { //if else
        tmps[0] = tmps[0].substring(1);
        this.current.addItem(tmps[0], tmps[1]);
      } //else 
    } //while
    eyes.close();
    reset();
    this.current = this.home;
  }

  //methods
  public void add(String imageLoc, String text) throws NullKeyException, KeyNotFoundException{
    if(this.current == this.arr.get("")){
      this.arr.set(text, new AACCategory(text));
      this.current.addItem(imageLoc, text);
    } else {
      this.current.addItem(imageLoc, text);
    }
  } //add(String, String)

  public String getCurrentCategory() {
    return this.current.getCurrentCategory();
  } //getCurrentCategory();

  public String[] getImageLocs() throws KeyNotFoundException {
    //System.out.println(this.current.getImage());
    return this.current.getImage();
    //return new String[] { "img/food/icons8-french-fries-96.png", "img/food/icons8-watermelon-96.png" }; // STUB
  } // getImageLocs()

  public String getText(String imageLoc) throws KeyNotFoundException {
    System.out.println(imageLoc);
    if(isCategory(imageLoc)){
      //System.out.println(this.arr + "1");
      //System.out.println(imageLoc + "2");
      String txt = this.current.getText(imageLoc);
      //System.out.println(txt + "3");
      this.current = this.arr.get(imageLoc);
      //System.out.println(this.current + "4");
      return txt;
    } else {
      String txt = this.current.getText(imageLoc);
      //System.out.println(txt);
      return txt;
    }
  } //getText(String)

  public Boolean isCategory(String imageLoc) throws KeyNotFoundException{
    //System.out.println(this.arr.get("").hasImage(imageLoc));
    return this.arr.get("").hasImage(imageLoc);
  } //isCategory(String)

  public void reset() throws KeyNotFoundException{
    this.current = this.arr.get("");
  } //reset()

  public void writeToFile(String filename) throws IOException, KeyNotFoundException{
    AACCategory holder = current;
    this.current = this.arr.get("");
    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
    String[] cats = this.current.getImage();
    //String[] catNames = new String[cats.length];
    //for(int k = 0; k < cats.length; k++){
    //  catNames[k] = current.getText(cats[k]);
    //  System.out.println(current.getText(cats[k]) + "  " + k);
    //}
    for(int i = 0; i < cats.length; i++){
      writer.write(this.current.getImage()[i] + " " + this.current.getText(this.current.getImage()[i]) + "\n");
      this.current = this.arr.get(cats[i]);
      String[] allStr = this.current.getImage();
      for(int j = 0; j < allStr.length; j++){
        writer.write(">" + this.current.getImage()[j] + " " + this.current.getText(allStr[j]) + "\n");
      }
      this.current = this.arr.get("");
    }
    writer.close();
    this.current = holder;
    return; //STUB
  } //writeToFile(String)
}
