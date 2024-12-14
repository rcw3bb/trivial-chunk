package xyz.ronella.trivial.handy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
    void huntReturnsValueFromEnvironmentVariable() {
        ValueHunter hunter = ValueHunter.getBuilder("PATH")
                .byEnvVar()
                .build();
        assertTrue(hunter.hunt().isPresent());
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
    void huntReturnsValueFromPropertiesObject() {
        Properties properties = new Properties();
        properties.setProperty("testProp", "propValue");
        ValueHunter hunter = ValueHunter.getBuilder("testProp")
                .byProperties(properties)
                .build();
        assertEquals(Optional.of("propValue"), hunter.hunt());
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
    void huntReturnsValueFromPropertyResourceBundle() throws IOException {
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
}