package structures;

import static java.lang.reflect.Array.newInstance;

/**
 * A basic implementation of Associative Arrays with keys of type K
 * and values of type V. Associative Arrays store key/value pairs
 * and permit you to look up values by key.
 *
 * @author Lucas Willett
 * @author Samuel A. Rebelsky
 */
public class AssociativeArray<K, V> {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default capacity of the initial array.
   */
  static final int DEFAULT_CAPACITY = 16;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The size of the associative array (the number of key/value pairs).
   */
  int size;

    /**
   * The capacity of the associative array (the number of key/value pairs).
   */
  int capacity;

  /**
   * The array of key/value pairs.
   */
  KVPair<K, V> pairs[];

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new, empty associative array.
   */
  @SuppressWarnings({ "unchecked" })
  public AssociativeArray() {
    // Creating new arrays is sometimes a PITN.
    this.pairs = (KVPair<K, V>[]) newInstance((new KVPair<K, V>()).getClass(),
        DEFAULT_CAPACITY);
    this.size = 0;
    this.capacity = DEFAULT_CAPACITY;
  } // AssociativeArray()

  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /**
   * Create a copy of this AssociativeArray.return new String[] { "img/food/icons8-french-fries-96.png", "img/food/icons8-watermelon-96.png" }; // STUB
   */
  public AssociativeArray<K, V> clone() {
    AssociativeArray<K, V> clone = new AssociativeArray<K, V>();
    clone.pairs = this.pairs;
    clone.size = this.size;
    clone.capacity = this.capacity;
    return clone;
  } // clone()

  /**
   * Convert the array to a string.
   */
  public String toString() {
    String str = "{ ";
    if(this.size == 0){
      return "{}";
    } else {
      int i = 0;
      do {
        if((this.pairs[i] != null) && (this.pairs[i].key != null)){
          str = str+ this.pairs[i].key + ": " + this.pairs[i].value + ", " ;
        }
        i++;
      } while(i < this.capacity);
    }
    str = str + "}";
    return str; 
  } // toString()

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * Set the value associated with key to value. Future calls to
   * get(key) will return value.
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public void set(K key, V value) throws NullKeyException {
    if(this.hasKey(key)){
      for(int i = 0; i < this.size; i++){
        if (this.pairs[i].key.equals(key)){
          this.pairs[i].value = value;
          return;
        } //if
      } //for
    } else {
      if(this.isFull()){
        this.expand();
      }
      this.pairs[this.size] = new KVPair(key, value);
      this.size++;
    } //else
  } // set(K,V)

  /**
   * Get the value associated with key.
   *
   * @throws KeyNotFoundException
   *                              when the key is null or does not 
   *                              appear in the associative array.
   */
  public V get(K key) throws KeyNotFoundException {
   // if(!this.hasKey(key)){
    //  throw new KeyNotFoundException();
    //} else {
      for(int i = 0; i < this.size; i++){
        if (this.pairs[i].key.equals(key)){
          return this.pairs[i].value;
        } //if
      } //for
    return null;
    //} //else
  } // get(K)

  /**
   * Determine if key appears in the associative array. Should
   * return false for the null key.
   */
  public boolean hasKey(K key) {
    for(int i = 0; i < this.size; i++){
      if ((this.pairs[i] != null) && (this.pairs[i].key != null) && (this.pairs[i].key.equals(key))){
        return true;
      }
    }
    return false;
  } // hasKey(K)img/food/icons8-pizza-96.png", "img/food/icons8-watermelon-96.png" 

    /**
   * Determine if an array is maxed capacity
   */
  public boolean isFull() {
    for(int i = 0; i < this.size; i++){
      if (this.pairs[i].key == null){
        return false;
      }
    }
    return true;
  } // hasKey(K)

  /**
   * Remove the key/value pair associated with a key. Future calls
   * to get(key) will throw an exception. If the key does not appear
   * in the associative array, does nothing.
   */
  public void remove(K key) {
    for(int i = 0; i < this.capacity; i++){
      if ((this.pairs[i] != null) && (this.pairs[i].key != null) && (this.pairs[i].key.equals(key))){
        this.pairs[i].key = null;
        this.pairs[i].value = null;
        this.size--;
        return;
      } //if
    } //for
    return;
  } // remove(K)

  /**
   * Determine how many key/value pairs are in the associative array.
   */
  public int size() {
    return this.size;
  } // size()

  // +-----------------+---------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * Expand the underlying array.
   */
  void expand() {
    this.pairs = java.util.Arrays.copyOf(this.pairs, this.pairs.length * 2);
  } // expand()

  /**
   * Find the index of the first entry in `pairs` that contains key.
   * If no such entry is found, throws an exception.
   */
  int find(K key) throws KeyNotFoundException {
    if(!this.hasKey(key)){
      throw new KeyNotFoundException();
    } else {
      int index = 0;
      for(int i = 0; i < this.size; i++){
        if (this.pairs[i].key.equals(key)){
          break;
        }
      }      
      return index;
    }
  } // find(K)

} // class AssociativeArray
