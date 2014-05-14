import java.util.*;

class Route
{


	ArrayList<Point> path =new ArrayList<Point>();
	double fitness;


	public Route()
	{
		fitness=0;
	}

	public void generateRoute(ArrayList<Point> cities)
	{
		 this.path = new ArrayList<Point>(cities);
		 Collections.shuffle(this.path);
	}

	public double fitness()
	{
		this.fitness = 1.0/this.distance();
		return this.fitness;
	}

	public int distance()
	{
		double total = 0;
		int next=0;
		for(int i=0;i<path.size();i++)
		{
			 if (i+1 != this.path.size())
			 {
			 	next = i+1;
			 } 
			 else
			 {
			 	next=0;	
			 }
			  
			total += this.path.get(i).distanceTo(this.path.get(next));

			
		}
		total=(int)total;


		return (int)total;
	}


	// Mates the route with another
	// Grabs a random section from the first parent
    // then fills in the missing points in order from the second parent

	public Route sexyTime(Route mate)
	{
		Route child =new  Route();
		int midme = (int)(this.path.size()/2);

		int idx1 = (int)Math.random() * midme;
		
		int idx2 = idx1+1 + (int)Math.random() * this.path.size();
		
		
		child.path =new ArrayList<Point>(this.path.subList(idx1, idx2));
		
		for(int i=0;i<mate.path.size();i++)
		{
			
			if(!child.path.contains(mate.path.get(i)))
			{
				child.path.add(mate.path.get(i));
			}


		}
		return child;
	}



	// Mutates the route by simple swap of points
	
	public void  mutate()
	{
		int idx1 = (int)(Math.random() * this.path.size() ); 
		int idx2 = idx1;

		while (idx1 == idx2)
		{
			

			

			idx2  =(int)( Math.random() * this.path.size() );
		}

		Collections.swap(path,idx1,idx2);

	}


	public void printPoints()
	{
		for(int i=0;i<this.path.size();i++)
		{
			this.path.get(i).PrintPoint();

		}
	}

	public String fancyRepr()
	{
		String sx = "X | ";
		String sy = "Y | ";

		for(int i=0;i<this.path.size();i++)
		{
			sx += this.path.get(i).x + " ";
			sy += this.path.get(i).x + " "	;	

		}

		return sy + "\n" + sx + "\n";
	}


	public void addPoint(int x,int y)
	{
		this.path.add(new Point(x,y));
	} 
		


}