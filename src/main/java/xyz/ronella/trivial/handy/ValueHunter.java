package xyz.ronella.trivial.handy;

import java.util.*;
import java.util.function.Supplier;

/**
 * A class that hunts for the first non-null value from a list of mercenaries.
 *
 * @since 3.2.0
 * @author Ron Webb
 */
final public class ValueHunter {

    private final List<Supplier<String>> mercenaries;

    private ValueHunter(final ValueHunterBuilder builder) {
        mercenaries = builder.mercenaries;
    }

    /**
     * Hunts for the first non-null value provided by the mercenaries.
     *
     * @return An Optional containing the first non-null value, or an empty Optional if none found.
     */
    public Optional<String> hunt() {
        return mercenaries.stream()
                .map(Supplier::get)
                .filter(Objects::nonNull)
                .findFirst();
    }

    /**
     * Creates a new ValueHunterBuilder with the specified target.
     *
     * @param target The target value to be hunted.
     * @return A new instance of ValueHunterBuilder.
     */
    public static ValueHunterBuilder getBuilder(final String target) {
        return new ValueHunterBuilder(target);
    }

    /**
     * A builder class for ValueHunter.
     */
    final public static class ValueHunterBuilder {
        private final String target;
        private final List<Supplier<String>> mercenaries;

        /**
         * Constructs a ValueHunterBuilder with the specified target.
         *
         * @param target The target value to be hunted.
         */
        private ValueHunterBuilder(final String target) {
            this.target = target;
            this.mercenaries = new ArrayList<>();
        }

        /**
         * Adds a mercenary to the list of mercenaries.
         *
         * @param mercenary The mercenary to be added.
         */
        private void addMercenary(final Supplier<String> mercenary) {
            this.mercenaries.add(mercenary);
        }

        /**
         * Adds a mercenary that retrieves the value of the target from environment variables.
         *
         * @return The current instance of ValueHunterBuilder.
         */
        public ValueHunterBuilder byEnvVar() {
            Optional.ofNullable(target).ifPresent(___target -> addMercenary(() -> System.getenv(___target)));
            return this;
        }

        /**
         * Adds a mercenary that retrieves the value of the target from system properties.
         *
         * @return The current instance of ValueHunterBuilder.
         */
        public ValueHunterBuilder bySysProp() {
            Optional.ofNullable(target).ifPresent(___target -> addMercenary(() -> System.getProperty(___target)));
            return this;
        }

        /**
         * Adds a mercenary that retrieves the value of the target from the given Properties object.
         *
         * @param properties The Properties object from which to retrieve the value of the target.
         * @return The current instance of ValueHunterBuilder.
         */
        public ValueHunterBuilder byProperties(final Properties properties) {
            Optional.ofNullable(target).ifPresent(___target -> addMercenary(() -> properties.getProperty(___target)));
            return this;
        }

        /**
         * Adds a mercenary that retrieves the value of the target from the given ResourceBundle.
         * @param properties The ResourceBundle object from which to retrieve the value of the target.
         * @return The current instance of ValueHunterBuilder.
         */
        @SuppressWarnings("PMD.EmptyCatchBlock")
        public ValueHunterBuilder byResourceBundle(final ResourceBundle properties) {
            Optional.ofNullable(target).ifPresent(___target -> addMercenary(() -> {
                String value = null;

                try {
                    value = properties.getString(___target);
                }
                catch (MissingResourceException e) {
                    // Do nothing
                }

                return value;
            }));
            return this;
        }

        /**
         * Builds and returns a new instance of ValueHunter.
         *
         * @return A new instance of ValueHunter.
         */
        public ValueHunter build() {
            return new ValueHunter(this);
        }
    }

}