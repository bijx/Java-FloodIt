
/**
 * The class <b>DotInfo</b> is a simple helper class to store the initial color and state
 * (captured or not) at the dot position (x,y)
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class DotInfo {


	private int x, y, color;
	private boolean captured = false;



    /**
     * Constructor 
     * 
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     * @param color
     *            the initial color
     */
    public DotInfo(int x, int y, int color){
    	this.x = x;
    	this.y = y;
    	this.color = color;

    }

    /**
     * Getter method for the attribute x.
     * 
     * @return the value of the attribute x
     */
    public int getX(){
    	return x;
    }
    
    /**
     * Getter method for the attribute y.
     * 
     * @return the value of the attribute y
     */
    public int getY(){
    	return y;
    }
    
 
    /**
     * Setter for captured
     * @param captured
     *            the new value for captured
     */
    public void setCaptured(boolean captured) {
    	this.captured = captured;
    }

    /**
     * Get for captured
     *
     * @return captured
     */
    public boolean isCaptured(){
    	return captured;
    }

    /**
     * Get for color
     *
     * @return color
     */
    public int getColor() {
    	return color;
    }
    
    public void setX(int x){
    	this.x = x;
    }
    public void setY(int y){
    	this.y = y;
    }
    public void setCol(int col){
    	color = col;
    }

 }