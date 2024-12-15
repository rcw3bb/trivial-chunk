package xyz.ronella.trivial.handy;

import java.util.*;
import java.util.function.Function;

/**
 * A class that hunts for the first non-null value from various sources.
 *
 * @since 3.2.0
 * @author Ron Webb
 */
final public class ValueHunter {

    private final List<Function<String, String>> mercenaries;
    private final String target;

    private ValueHunter(final ValueHunterBuilder builder) {
        target = builder.target;
        mercenaries = builder.mercenaries;
    }

    /**
     * Hunts for the first non-null value.
     *
     * @return An Optional containing the first non-null value, or an empty Optional if none found.
     */
    public Optional<String> hunt() {
        Require.object(target, "No target was provided by the ValueHunterBuilder. " +
                "Use the hunt method that accepts a target.");

        return hunt(target);
    }

    /**
     * Hunts for the first non-null value.
     *
     * @param target The target value to be hunted.
     * @return An Optional containing the first non-null value, or an empty Optional if none found.
     *
     * @since 3.3.0
     */
    public Optional<String> hunt(final String target) {
        return mercenaries.stream()
                .map(___mercenary -> ___mercenary.apply(target))
                .filter(Objects::nonNull)
                .findFirst();
    }

    /**
     * Creates a new ValueHunterBuilder with the specified target.
     *
     * @param target The target value to be hunted.
     * @return A new instance of ValueHunterBuilder.
     * @throws ObjectRequiredException if the target is null.
     */
    public static ValueHunterBuilder getBuilder(final String target) {
        Require.object(target, "The target parameter must not be null.");
        return new ValueHunterBuilder(target);
    }

    /**
     * Creates a new ValueHunterBuilder with no target.
     *
     * @return A new instance of ValueHunterBuilder.
     * @since 3.3.0
     */
    public static ValueHunterBuilder getBuilder() {
        return new ValueHunterBuilder();
    }

    /**
     * A builder class for ValueHunter.
     */
    final public static class ValueHunterBuilder {
        private final String target;
        private final List<Function<String, String>> mercenaries;

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
         * Constructs a ValueHunterBuilder with no target.
         */
        private ValueHunterBuilder() {
            this(null);
        }

        /**
         * Adds a mercenary that can find the value.
         *
         * @param mercenary The mercenary to be added.
         */
        private void addMercenary(final Function<String, String> mercenary) {
            this.mercenaries.add(mercenary);
        }

        /**
         * Adds a mercenary that retrieves the value of the target from environment variables.
         *
         * @return The current instance of ValueHunterBuilder.
         */
        public ValueHunterBuilder byEnvVar() {
            addMercenary(System::getenv);
            return this;
        }

        /**
         * Adds a mercenary that retrieves the value of the target from the specified environment variable.
         *
         * @param envVar The environment variable from which to retrieve the value of the target.
         * @return The current instance of ValueHunterBuilder.
         */
        public ValueHunterBuilder asEnvVar(final String envVar) {
            Require.object(target, "The target must be known by the builder");

            Optional.ofNullable(envVar).ifPresent(___envVar -> addMercenary(___ -> System.getenv(___envVar)));
            return this;
        }

        /**
         * Adds a mercenary that retrieves the value of the target from system properties.
         *
         * @return The current instance of ValueHunterBuilder.
         */
        public ValueHunterBuilder bySysProp() {
            addMercenary(System::getProperty);
            return this;
        }

        /**
         * Adds a mercenary that retrieves the value of the target from the specified system property.
         *
         * @param sysProp The system property from which to retrieve the value of the target.
         * @return The current instance of ValueHunterBuilder.
         */
        public ValueHunterBuilder asSysProp(final String sysProp) {
            Require.object(target, "The target must be known by the builder");

            Optional.ofNullable(sysProp).ifPresent(___sysProp -> addMercenary(___ -> System.getProperty(___sysProp)));
            return this;
        }

        /**
         * Adds a mercenary that retrieves the value of the target from the given Properties object.
         *
         * @param properties The Properties object from which to retrieve the value of the target.
         * @return The current instance of ValueHunterBuilder.
         */
        public ValueHunterBuilder byProperties(final Properties properties) {
            addMercenary(properties::getProperty);
            return this;
        }

        /**
         * Adds a mercenary that retrieves the value of the target from the given ResourceBundle.
         *
         * @param resourceBundle The ResourceBundle object from which to retrieve the value of the target.
         * @return The current instance of ValueHunterBuilder.
         */
        @SuppressWarnings("PMD.EmptyCatchBlock")
        public ValueHunterBuilder byResourceBundle(final ResourceBundle resourceBundle) {
            addMercenary(___target -> {

                String value = null;

                try {
                    value = resourceBundle.getString(___target);
                }
                catch (MissingResourceException e) {
                    // Do nothing
                }

                return value;
            });

            return this;
        }

        /**
         * Adds a mercenary that retrieves the value of the target provided to the hunter.
         *
         * @param hunter The hunter that retrieves the value of the target.
         * @return The current instance of ValueHunterBuilder.
         * @throws ObjectRequiredException if the hunter is null.
         */
        public ValueHunterBuilder addHunter(final Function<String, String> hunter) {
            Require.object(hunter, "The hunter parameter must not be null.");

            addMercenary(hunter);
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