package project;

/**
 *
 * @author mmoda
 */
public class Gold extends State{
    
    
    public Gold(int points){
        name = "Gold"; 
        this.points = points; 
    }
    public double redeempoints(double price, State myState){
        int temp = this.points; 
        int points2 = 0;
        
        while (temp>= 100 && price > 0){
            price--;
            temp = temp - 100; 
            points2 = points2 + 100; 
            
        }
       // System.out.println("this.points: " + this.points + "points2 " +points2); 
        updatestatus(-1*points2, myState); 
        return price; 
    }
    
    
    public State updatestatus(int points, State myState){
        this.points = this.points + points; 
        if (this.points < 1000){
            myState = new Silver(this.points); 
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
