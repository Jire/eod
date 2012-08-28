package eod.service;

import eod.Experimental;
import eod.Nullable;

@Experimental
public abstract class ThreadService extends AbstractService implements Runnable {

	private final Thread thread;

	public ThreadService(@Nullable String threadName) {
		this.thread = new Thread(this, threadName);
	}

	public ThreadService() {
		this(null);
	}

	protected final Thread getThread() {
		return thread;
	}

	@Override
	protected void onStart() {
		getThread().start();
	}

	@Override
	protected void onStop() {}

}