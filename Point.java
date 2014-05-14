import java.util.*;


class Point
{	
	double x;
	double y;

	public Point(double x,double y)
	{
		this.x=x;
		this.y=y;
	}

	public void PrintPoint()
	{
		System.out.println("Point("+x+","+y+") ");
	}

	public double distanceTo(Point p)
	{
		double dx=this.x-p.x;
		double dy=this.y-p.y;

		dx=Math.abs(dx*dx);
		dy=Math.abs(dy*dy);

		return Math.sqrt(dx+dy);

	}

}