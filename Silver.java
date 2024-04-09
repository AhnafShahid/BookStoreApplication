package project;

/**
 *
 * @author mmoda
 */
public class Silver extends State{
    
    public Silver(){
        name = "Silver"; 
    }
    
    public Silver(int points){
        name = "Silver"; 
        this.points = points; 
    }
    

    public double redeempoints(double price, State myState){
        int temp = this.points; 
        int points2 = 0;
        System.out.println("price before "+ price); 
        while (temp>= 100 && price > 0){
            price--;//0
            temp = temp - 100; //10
            points2 = points2 + 100; //100
            
        }
        System.out.println("this.points: " + this.points + "points2 " +points2); 
        updatestatus(-1*points2, myState);  //
        System.out.println("price " + price); 
        return price; 
    }
    
    public State updatestatus(int points, State myState){
        this.points = this.points + points; 
        if (this.points >= 1000){
            myState = new Gold(this.points); 
        }
        return myState; 
    }
    
    public String getName(){
        return this.name; 
    }
    public int getPoints(){
        return this.points; 
    }
    
}
