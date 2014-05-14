import java.util.*;

class Driver
{
	static int HEIGHT = 20;
	static int WIDTH  = 20;
	static int NUM_CITIES = 20;

	static int InitPopulation = 20;
	static double MatePcnt       = 0.5;
	static double MutationRate   = 0.01;
	static int NumIterations  = 5000;

	

	public static ArrayList<Route> bubbleSort(ArrayList<Route> data)  
	{  
	   for (int k = 0; k < data.size() - 1; k++)  
	   {  
	      boolean isSorted = true;  
	  
	      for (int i = 1; i < data.size() - k; i++)  
	      {  
	         if (data.get(i).fitness < data.get(i-1).fitness)  
	         {  
	            Collections.swap(data,i,i-1); 
	            isSorted = false;
	         }  
	      }  
	  
	      if (isSorted)  
	         break;  
	   }

	   return data;  
	}  


	public static void main(String[] args)
	{

		//Point p=new Point(1,2);
		
		//p.PrintPoint();		

		Grid g =new Grid(HEIGHT,WIDTH);

		g.populate(NUM_CITIES);

		ArrayList<Route> population =new ArrayList<Route>();

		if (InitPopulation%2 != 0)
			InitPopulation += 1;


		System.out.println (" ");
		System.out.println ("Parameters : "+"height:"+ HEIGHT+", width: "+ WIDTH+", Number of Cities:"+ NUM_CITIES+", Initial Population:"+ InitPopulation+", Mate Percent:"+ MatePcnt+", Mutation Rate:"+ MutationRate+", Number of Iterations:"+ NumIterations);
		System.out.println (" ");
		System.out.println ("Generating Initial Population: Stand By");
		
		for(int i=0 ;i<InitPopulation;i++)
		{
			Route r =new Route();
			r.generateRoute(g.cities);
			population.add(r);
		}

		int iteration = 0;

		System.out.println ("Starting life.");

		Route globalMaxFit=population.get(0);

		for (int i=0;i<NumIterations;i++)
		{

			ArrayList<Route> newPopulation =new ArrayList<Route>();

			

			
			Route localmaxFit=population.get(0);
			
			for(int j=0;j<population.size();j++)
			{
				if(localmaxFit.fitness()<population.get(j).fitness())
				{
					localmaxFit=	population.get(j);
				}
			}


			//updating global max fit

			if(globalMaxFit.fitness()<localmaxFit.fitness())
			{
				globalMaxFit=localmaxFit;
			}



			// Split population into two halves

			ArrayList<Route> A =new ArrayList<Route>(population.subList(0, (int)population.size()/2)); //population[:int(len(population)/2)]
			ArrayList<Route> B =new ArrayList<Route>(population.subList(((int)population.size()/2+1), (int)population.size())); //population[int(len(population)/2):]

			// Sort them by fitness
			

			

			A = bubbleSort( A );

			B = bubbleSort( B );

			// Mate the top MatePcnt% 
			for(int idx=0; idx<(int)A.size()*MatePcnt;idx++)
			{
				newPopulation.add(A.get(idx).sexyTime(B.get(idx)));	

			}



			// Mutate a few
			for(int k=0;k<population.size();k++) 
			{
				Route indv=new Route();

				indv.path=new ArrayList<Point>(population.get(k).path);
				indv.fitness=population.get(k).fitness;

				Route cpy =new Route();
				cpy.path =new ArrayList<Point>( indv.path );

				if(Math.random() < MutationRate)
				{
					cpy.mutate();
					newPopulation.add(cpy);
				}
			}

			// Randomly add rest of population to keep the size of the universe the same
			Collections.shuffle(population) ;

			newPopulation.addAll(population.subList(0,population.size()-newPopulation.size()));
			
			population =new ArrayList<Route>(newPopulation);

			// Some logging
			
			System.out.println ("");
			System.out.println ("Iteration: " + iteration );
			System.out.println ("The most fit individual in this population is: " + (1.0/localmaxFit.distance()));
			System.out.println ("Overall the most fit individual : "+(1.0/globalMaxFit.distance()));
			System.out.println ("");
			
			//sys.stdout.flush() // Used to watch progress 
			
			iteration += 1;
		}

	}
}