import java.util.*;

/**
 * A cell in a 2D cellular automaton.
 * The cell has multiple possible states.
 * This is an implementation of the rules for Brian's Brain.
 * @see https://en.wikipedia.org/wiki/Brian%27s_Brain
 * 
 * @author David J. Barnes and Michael Kölling
 * @version  2016.02.29
 */
public class Cell
{
    // The possible states.
    public static final int HEALTHY = 0, ILL = 10, K1 = 3, K2 = 3, G = 1;
    // The number of possible states.
    public static final int NUM_STATES = 11;

    // The cell's state.
    private int state;
    // The cell's neighbors.
    private Cell[] neighbors;
    
    // Next cell reference and its getter method.
    private Cell next;

    /**
     * Set the initial state to be DEAD.
     */
    public Cell()
    {
        this(HEALTHY);
    }
    
    /**
     * The getNext method.
     * @return Next cell
     */
    public Cell getNext()
    {
        return next;
    }
    
    /**
     * Set the initial state.
     * @param initialState The initial state
     */
    public Cell(int initialState)
    {
        state = initialState;
        neighbors = new Cell[0];
    }
    
    /**
     * Determine this cell's next state, based on the
     * state of its neighbors.
     * This is an implementation of the rules for Brian's Brain.
     * @return The next state.
     */
    public int getNextState()
    {
        int s = state;
        int illCount = 0;
        int infectedCount = 0;
        for(Cell n : neighbors) {
            s += n.getState();
            if(n.getState() != HEALTHY) {
                if (n.getState() == ILL) {
                    illCount++;
                }
                else {
                    infectedCount++;
                }    
            }
        }
        
        if(state == HEALTHY) {
            return (int)(infectedCount/K1) + (int)(illCount/K2);
        }
        else if(state == ILL) {
            return HEALTHY;
        }
        else {
            return ((int)(s/(illCount + infectedCount + 1)) + G) % 10;
        }
    }
    
    /**
     * Set the next cell.
     * 
     */
    public void setNextCell() {
        int direction = (new Random()).nextInt(neighbors.length);
        next = neighbors[direction];
    }
    
    /**
     * Receive the list of neighboring cells and take
     * a copy.
     * @param neighborList Neighboring cells.
     */
    public void setNeighbors(ArrayList<Cell> neighborList)
    {
        neighbors = new Cell[neighborList.size()];
        neighborList.toArray(neighbors);
        setNextCell();
    }
    
    

    /**
     * Get the state of this cell.
     * @return The state.
     */
    public int getState()
    {
        return state;
    }
    
    /**
     * Set the state of this cell.
     * @param The state.
     */
    public void setState(int state)
    {
        this.state = state;
        setNextCell();
    }   
    
}
