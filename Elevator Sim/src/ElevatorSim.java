import java.util.*;
import simpack.*;

public class ElevatorSim {
	ArrayList<Elevator> elist;
	ArrayList<Floor> flist;
	ElevatorController controller;
	long inittime;
	public ElevatorSim(int floors, int elevators, int people)
	{
		System.out.println("Constructing simulation with " + floors + " floors, " 
				+ elevators + " elevators, and " + people + " people");
		elist = new ArrayList<Elevator>();
		flist = new ArrayList<Floor>();
		for(int i = 0; i < floors; i++)
		{
			flist.add(new Floor(elist, i+1));
		}
		for(int i = 0; i < elevators; i++)
		{
			elist.add(new Elevator(flist, 5, i+1));
		}
		for(int i = 0; i < people; i++)
		{
			int current = (int)(Math.random()*floors);
			int destination = (int)(Math.random()*floors);
			Person p = new Person(flist.get(current), flist.get(destination));
			flist.get(current).addPerson(p);
		}
		inittime = System.currentTimeMillis();
		Sim.init(inittime, Const.HEAP);
		
		controller = new ElevatorController(elist, flist);
	}
	public static void main(String args[])
	{
		System.out.println("doing test simulation with 10 floors, 3 elevators, and 30 people");
		ElevatorSim esim = new ElevatorSim(10, 3, 30);
		System.out.println("initial state");
		for(Elevator elevator:esim.elist)
		{
			System.out.println(elevator);
		}
		for(Floor floor:esim.flist)
		{
			System.out.println(floor);
		}
		esim.controller.control();
	}
	
}