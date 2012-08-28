package eod.onset;

import eod.Experimental;
import eod.Nullable;
import eod.Optional;
import eod.event.EventManager;

@Experimental
public final class OnsetSequencer {

	private final Onset onset;
	private final Optional<EventManager> eventManager;

	public OnsetSequencer(Onset onset, @Nullable EventManager eventManager) {
		this.onset = onset;
		this.eventManager = Optional.fromNullable(eventManager);
	}

	public OnsetSequencer(Onset onset) {
		this(onset, null);
	}

	public void sequence() {
		boolean present = eventManager.isPresent();
		if (present) {
			eventManager.get().dispatchEvent(new OnsetBeginEvent(onset));
		}
		onset.begin();
		if (present) {
			eventManager.get().dispatchEvent(new OnsetEndEvent(onset));
		}
		onset.end();
	}

}