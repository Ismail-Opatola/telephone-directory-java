package telephonedirectorypackage;

/**
 * Generic functional interface
 * @author Ismail
 *
 * @param <T>
 * @param <U>
 */
@FunctionalInterface
public interface BiConsumer<T, U> {
	void accept(T t, U u);
}
