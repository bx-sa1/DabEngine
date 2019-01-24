package Observer;

import java.util.ArrayList;

public abstract class Subject {
	
	private ArrayList<Observer> observers = new ArrayList<>();
	
	public void addObserver(Observer o) {
		observers.add(o);
	}
	public void removeObserver(Observer o) {
		observers.remove(o);
	}
	public void notify(Object obj) {
		for(Observer o : observers) {
			o.onNotify(obj);
		}
	}

}
