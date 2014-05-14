import java.util.*;

class Grid
{
	ArrayList<Point> cities = new ArrayList<Point>();
	int height;
	int width;

	public Grid(int height,int width)
	{
		this.height = height;
		this.width =  width;
	}


	public void populate(int count)
	{
		for (int i=0;i<count;i++)
		{
			double x = Math.random() * this.width;
			double y = Math.random() * this.height;
			Point p=new Point(x,y);
			p.PrintPoint();
			this.cities.add(p);

		}
	}
}