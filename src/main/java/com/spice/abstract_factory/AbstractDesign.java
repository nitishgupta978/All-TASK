package com.spice.abstract_factory;

/**
 * @author nitish.gupta_spicemo!
 *
 */

//Java Program to demonstrate the
//working of Abstract Factory Pattern

enum CarType
{
	BMW, TATA, MERCEDES
}

abstract class Car
{
	Car(CarType model, Location location)
	{
		this.model = model;
		this.location = location;
	}

	abstract void construct();

	CarType model = null;
	Location location = null;

	CarType getModel()
	{
		return model;
	}

	void setModel(CarType model)
	{
		this.model = model;
	}

	Location getLocation()
	{
		return location;
	}

	void setLocation(Location location)
	{
		this.location = location;
	}

	@Override
	public String toString()
	{
		return "CarModel - "+model + " located in "+location;
	}
}

class MERCEDESCar extends Car
{
	MERCEDESCar(Location location)
	{
		super(CarType.MERCEDES, location);
		construct();
	}
	@Override
	protected void construct()
	{
		System.out.println("Connecting to MERCEDES car");
	}
}

class BMWCar extends Car
{
	BMWCar(Location location)
	{
		super(CarType.BMW, location);
		construct();
	}
	@Override
	protected void construct()
	{
		System.out.println("Connecting to BMW Car ");
	}
}

class TATACar extends Car
{
	TATACar(Location location)
	{
		super(CarType.TATA,location );
		construct();
	}
	
	@Override
	void construct()
	{
		System.out.println("Connecting to TATA car");
	}
}

enum Location
{
DEFAULT, USA, INDIA
}

class INDIACarFactory
{
	static Car buildCar(CarType model)
	{
		Car car = null;
		switch (model)
		{
			case BMW:
				car = new BMWCar(Location.INDIA);
				break;
			
			case TATA:
				car = new TATACar(Location.INDIA);
				break;
				
			case MERCEDES:
				car = new MERCEDESCar(Location.INDIA);
				break;
				
				default:
				break;
			
		}
		return car;
	}
}

class DefaultCarFactory
{
	public static Car buildCar(CarType model)
	{
		Car car = null;
		switch (model)
		{
			case BMW:
				car = new BMWCar(Location.DEFAULT);
				break;
			
			case TATA:
				car = new TATACar(Location.DEFAULT);
				break;
				
			case MERCEDES:
				car = new MERCEDESCar(Location.DEFAULT);
				break;
				
				default:
				break;
			
		}
		return car;
	}
}


class USACarFactory
{
	public static Car buildCar(CarType model)
	{
		Car car = null;
		switch (model)
		{
			case BMW:
				car = new BMWCar(Location.USA);
				break;
			
			case TATA:
				car = new TATACar(Location.USA);
				break;
				
			case MERCEDES:
				car = new MERCEDESCar(Location.USA);
				break;
				
				default:
				break;
			
		}
		return car;
	}
}



class CarFactory
{
	private CarFactory()
	{
		
	}
	public static Car buildCar(CarType type)
	{
		Car car = null;
		// We can add any GPS Function here which
		// read location property somewhere from configuration
		// and use location specific car factory
		// Currently I'm just using INDIA as Location
		Location location = Location.INDIA;
		
		switch(location)
		{
			case USA:
				car = USACarFactory.buildCar(type);
				break;
				
			case INDIA:
				car = INDIACarFactory.buildCar(type);
				break;
					
			default:
				car = DefaultCarFactory.buildCar(type);

		}
		
		return car;

	}
}

class AbstractDesign
{
	public static void main(String[] args)
	{
		System.out.println(CarFactory.buildCar(CarType.BMW));
		System.out.println(CarFactory.buildCar(CarType.TATA));
		System.out.println(CarFactory.buildCar(CarType.MERCEDES));
	}
}
