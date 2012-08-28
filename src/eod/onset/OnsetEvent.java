package eod.onset;

import eod.Experimental;
import eod.event.Event;

@Experimental
public abstract class OnsetEvent implements Event {

	private final Onset onset;

	public OnsetEvent(Onset onset) {
		this.onset = onset;
	}

	public Onset getOnset() {
		return onset;
	}

}