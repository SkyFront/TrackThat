package Helpers;

/**
 * Created by kelvinchan on 15-03-21.
 */
public class Counter {

    String name;
    int count;

    //constructor
    public Counter(){

    }

    public Counter(String name, int count){
        this.name = name;
        this.count = count;
    }

    //get and set
    public String getName(){
        return name;
    }

    public int getCount(){
        return count;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCount(int count){
        this.count = count;
    }


}
