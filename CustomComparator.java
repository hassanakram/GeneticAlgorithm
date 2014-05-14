import java.util.*;

public class CustomComparator implements Comparator<Route> 
{
    @Override
    public int compare(Route o1, Route o2) 
    {
    	if(o1.fitness>o2.fitness)
    	{
    		return 1;
    	}
    	else if(o1.fitness<o2.fitness)
    	{
    		return -1;
    	}
    	
        return 0;
    }
}