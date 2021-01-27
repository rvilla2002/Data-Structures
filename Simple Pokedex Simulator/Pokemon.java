/** 
 * The Pokemon class contains all the information on the attributes of each pokemon and 
 * is used by the player class to assign Pokemon objects to a player.
 * @author: Rishi Villa
 * Collaborators : None
 * Teacher Name : Mrs. Ishman 
 * Period: 3
 * Due date : 05/07/2020
 * 
 */

import java.util.Map;
import java.util.TreeMap;

public class Pokemon implements Comparable<Pokemon>
{
  private String name;
  private int attack;
  private int defense;
  private int speed;
  private int hp;
  private static Map<String, Integer> pokemonPop;
  private String pref;
  
  /**
   * The constructor initializes all the instance variables
   * @param name of the Pokemon 
   * @param attack stats of the pokemon 
   * @param defense stats of the pokemon 
   * @param speed 
   */
  public Pokemon(String name, int attack, int defense, int speed, int hp, Map<String, Integer> pokemonPop)
  {
    this.name = name;
    this.attack = attack;
    this.defense = defense;
    this.speed = speed;
    this.hp = hp;
    this.pokemonPop = new TreeMap<>();
  }
  
  /**
   * retrieves the name of the Pokemon 
   * @return the name 
   */
  public String getName()
  {
    return name;
  }
  
  /**
   * retrieves the attack stats of the Pokemon
   * @return attack of pokemon
   */
  public int getAttack()
  {
    return attack;
  }
  
  /**
   * retrieves the defense of the pokemon
   * @return defense stats of Pokemon
   */
  public int getDefense()
  {
    return defense;
  }
  
  /**
   * retrieves the speed of the pokemon
   * @return speed stats of Pokemon
  */
  public int getSpeed()
  {
    return speed;
  }
 
  /**
   * retrieves the HP of the pokemon
   * @return HP of Pokemon
   */ 
  public int getHp()
  {
    return hp;
  }
  
  /**
   * retrieves the population of a certain pokemon
   * @return map of pokemon and population
   */
  public static int getPopulation(String name)
  {
    return pokemonPop.get(name);
  }
  
  /**
   * adds pokemon to a certain species when called
   * @param name of pokemon
   * @param number of pokemon to be added
   */
  public static void addPopulation(String pokemonName, int numPokemon)
  {
    if(pokemonPop.containsKey(pokemonName))
    {
       pokemonPop.put(pokemonName, pokemonPop.get(pokemonName) + numPokemon);
    }
    else
    {
      pokemonPop.put(pokemonName, numPokemon);
    }
  }
  
  /**
   * removes certain number of pokemon from population
   * @param name of pokemon 
   * @param number of pokemon to be added
   * @return whether or not pokemon was removed
   */
  public static boolean removePopulation(String pokemonName, int numPokemon)
  {
    if(pokemonPop.containsKey(pokemonName))
    {
      if(pokemonPop.get(pokemonName) - numPokemon >= 0)
      {
        pokemonPop.put(pokemonName, pokemonPop.get(pokemonName) - numPokemon);
      }
      else
      {
        pokemonPop.put(pokemonName, 0);
      }
      return true;
    }
    return false;
  }
  
  /**
   * method compares the stats of pokemon to match the preference.
   * @param the pokemon object being compared
   * @return compared value of each pokemon.
   */
  @Override
  public int compareTo(Pokemon pok)
  {
    switch(pref)
    {
      case("name"):
        return this.getName().compareTo(pok.getName());
      case("attack"):
        return this.getAttack() - pok.getAttack();
      case("defense"):
        return this.getDefense() - pok.getDefense();
      case("speed"):
        return this.getSpeed() - pok.getSpeed();
      default:
        return this.getHp() - pok.getHp();
    }
  }
  
  /**
   * gets preference string value 
   * @return pref value
   */
  public String getPref()
  {
    return pref;
  }
  
  /**
   * sets the pref string value to param
   * @param string value that is being assigned to pref
   */
  public void setPref(String key)
  {
    pref = key;
  }
  
  public boolean equalsTo(Pokemon pok)
  {
    return pok.getHp() == this.getHp() &&
    pok.getAttack() == this.getAttack() &&
    pok.getDefense() == this.getDefense() &&
    pok.getSpeed() == this.getSpeed();
  }
}