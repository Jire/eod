package eod;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * A <i>Guice</i> {@link Injector} supplier which
 * allows the use of {@link Module}s to create the
 * supplied injector.
 * 
 * @author Thomas G. P. Nappo
 */
@Experimental
public final class InjectorSupplier implements Supplier<Injector> {

	/**
	 * The encapsulated <i>Guice</i> modules to
	 * be used for creating the supplied injector.
	 */
	private final Module[] modules;

	/**
	 * Constructs an injector supplier.
	 * @param modules The <i>Guice</i> modules to
	 * be used for creating the supplied injector.
	 */
	public InjectorSupplier(Module... modules) {
		this.modules = modules;
	}

	@Override
	public Injector get() {
		return Guice.createInjector(modules);
	}

}