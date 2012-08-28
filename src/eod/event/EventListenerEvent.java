package eod.event;

public abstract class EventListenerEvent implements Event {

	private final EventListener listener;

	protected EventListenerEvent(EventListener listener) {
		this.listener = listener;
	}

	public EventListener getListener() {
		return listener;
	}

}