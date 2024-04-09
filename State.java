package Finalproject;

/**
 *
 * @author mmoda
 */
public abstract class State {
    protected int points = 0; 
    protected State myState; 
    protected String name; 
    
 
    public abstract double redeempoints(double price, State myState);
    public abstract State updatestatus(int points, State myState);
    public abstract int getPoints();
    public abstract String getName();
}

