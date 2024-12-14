package xyz.ronella.trivial.handy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serial;
import java.util.Optional;
import java.util.Properties;
import java.util.PropertyResourceBundle;

public class ValueHunterTest {

    @Test
    void huntReturnsFirstNonNullValue() {
        ValueHunter hunter = ValueHunter.getBuilder("test")
                .byEnvVar()
                .bySysProp()
                .byProperties(new Properties() {
                    @Serial
                    private static final long serialVersionUID = 6417976303492901555L;

                    {
                        setProperty("test", "value");
                    }
                }).build();
        assertEquals(Optional.of("value"), hunter.hunt());
    }

    @Test
    void huntReturnsEmptyOptionalWhenNoValuesFound() {
        ValueHunter hunter = ValueHunter.getBuilder("nonexistent")
                .byEnvVar()
                .bySysProp()
                .byProperties(new Properties())
                .build();
        assertTrue(hunter.hunt().isEmpty());
    }

    @Test
    @EnabledOnOs({OS.WINDOWS})
    void huntReturnsValueFromEnvironmentVariable() {
        ValueHunter hunter = ValueHunter.getBuilder("PATH")
                .byEnvVar()
                .build();
        assertTrue(hunter.hunt().isPresent());
    }

    @Test
    @EnabledOnOs({OS.WINDOWS})
    void huntReturnsValueFromDifferentEnvironmentVariable() {
        ValueHunter hunter = ValueHunter.getBuilder("PATH")
                .asEnvVar("windir")
                .build();
        assertEquals(Optional.of("C:\\WINDOWS"), hunter.hunt());
    }

    @Test
    void huntReturnsValueFromSystemProperty() {
        System.setProperty("testSysProp", "sysPropValue");
        ValueHunter hunter = ValueHunter.getBuilder("testSysProp")
                .bySysProp()
                .build();
        assertEquals(Optional.of("sysPropValue"), hunter.hunt());
    }

    @Test
    void huntReturnsValueFromDifferentSystemProperty() {
        System.setProperty("dummyProp", "sysPropValue");
        ValueHunter hunter = ValueHunter.getBuilder("testSysProp")
                .asSysProp("dummyProp")
                .build();
        assertEquals(Optional.of("sysPropValue"), hunter.hunt());
    }

    @Test
    void huntReturnsValueFromPropertiesObject() {
        Properties properties = new Properties();
        properties.setProperty("testProp", "propValue");
        ValueHunter hunter = ValueHunter.getBuilder("testProp")
                .byProperties(properties)
                .build();
        assertEquals(Optional.of("propValue"), hunter.hunt());
    }

    @Test
    void huntReturnsValueFromPropertiesObjectInvalidProperty() {
        Properties properties = new Properties();
        properties.setProperty("testProp", "propValue");
        ValueHunter hunter = ValueHunter.getBuilder("testProp2")
                .byProperties(properties)
                .build();
        assertTrue(hunter.hunt().isEmpty());
    }

    @Test
    void huntReturnsFirstNonNullValueFromMultipleSources() {
        System.setProperty("testMulti", "sysPropValue");
        Properties properties = new Properties();
        properties.setProperty("testMulti", "propValue");
        ValueHunter hunter = ValueHunter.getBuilder("testMulti")
                .byEnvVar()
                .bySysProp()
                .byProperties(properties)
                .build();
        assertEquals(Optional.of("sysPropValue"), hunter.hunt());
    }

    @Test
    void huntReturnsValueFromPropertyResourceBundleByProperty() throws IOException {
        PropertyResourceBundle bundle;
        try (FileInputStream input = new FileInputStream("src/test/resources/valuehunter/test.properties")) {
            bundle = new PropertyResourceBundle(input);
        }
        ValueHunter hunter = ValueHunter.getBuilder("field")
                .byProperties(new Properties() {{
                    bundle.keySet().forEach(key -> setProperty(key, bundle.getString(key)));
                }})
                .build();
        assertEquals(Optional.of("dummy"), hunter.hunt());
    }

    @Test
    void huntReturnsValueFromPropertyResourceBundle() throws IOException {
        PropertyResourceBundle bundle;
        try (FileInputStream input = new FileInputStream("src/test/resources/valuehunter/test.properties")) {
            bundle = new PropertyResourceBundle(input);
        }
        ValueHunter hunter = ValueHunter.getBuilder("field")
                .byResourceBundle(bundle)
                .build();
        assertEquals(Optional.of("dummy"), hunter.hunt());
    }

    @Test
    void huntReturnsValueFromMultiplePropertyResourceBundle() throws IOException {
        PropertyResourceBundle bundle1;
        PropertyResourceBundle bundle2;
        try (   FileInputStream input = new FileInputStream("src/test/resources/valuehunter/test.properties");
                FileInputStream input2 = new FileInputStream("src/test/resources/valuehunter/test2.properties")) {
            bundle1 = new PropertyResourceBundle(input);
            bundle2 = new PropertyResourceBundle(input2);
        }
        ValueHunter hunter = ValueHunter.getBuilder("field")
                .byResourceBundle(bundle1)
                .byResourceBundle(bundle2)
                .build();
        assertEquals(Optional.of("dummy"), hunter.hunt());
    }

    @Test
    void huntReturnsValueFromMultiplePropertyResourceBundleButOnlyOneHasIt() throws IOException {
        PropertyResourceBundle bundle1;
        PropertyResourceBundle bundle2;
        try (   FileInputStream input = new FileInputStream("src/test/resources/valuehunter/test.properties");
                FileInputStream input2 = new FileInputStream("src/test/resources/valuehunter/test2.properties")) {
            bundle1 = new PropertyResourceBundle(input);
            bundle2 = new PropertyResourceBundle(input2);
        }
        ValueHunter hunter = ValueHunter.getBuilder("field2")
                .byResourceBundle(bundle1)
                .byResourceBundle(bundle2)
                .build();
        assertEquals(Optional.of("dummy3"), hunter.hunt());
    }

    @Test
    void addHunterReturnsValueFromCustomHunter() {
        ValueHunter hunter = ValueHunter.getBuilder("custom")
                .addHunter(key -> "customValue")
                .build();
        assertEquals(Optional.of("customValue"), hunter.hunt());
    }

    @Test
    void addHunterReturnsEmptyOptionalWhenHunterReturnsNull() {
        ValueHunter hunter = ValueHunter.getBuilder("custom")
                .addHunter(key -> null)
                .build();
        assertTrue(hunter.hunt().isEmpty());
    }

    @Test
    void addHunterThrowsExceptionWhenHunterIsNull() {
        assertThrows(ObjectRequiredException.class, () -> {
            ValueHunter.getBuilder("custom")
                    .addHunter(null)
                    .build();
        });
    }

    @Test
    void createBuilderThrowsExceptionWhenTargetIsNull() {
        assertThrows(ObjectRequiredException.class, () -> {
            ValueHunter.getBuilder(null);
        });
    }

}