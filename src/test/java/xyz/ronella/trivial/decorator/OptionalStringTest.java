package xyz.ronella.trivial.decorator;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class OptionalStringTest {

    @Test
    public void nullString() {
        String text=null;
        assertTrue(new OptionalString(Optional.ofNullable(text)).isBlank());
    }

    @Test
    public void emptyString() {
        String text="";
        assertTrue(new OptionalString(Optional.of(text)).isEmpty());
    }

    @Test
    public void blankString() {
        String text="   ";
        assertTrue(new OptionalString(Optional.of(text)).isBlank());
    }

    @Test
    public void validString() {
        String text="Test";
        assertFalse(new OptionalString(Optional.of(text)).isBlank());
    }

    @Test
    public void validTestString() {
        var text="Test";
        assertFalse(new OptionalString(Optional.of(text)).isBlank());
    }

    @Test
    public void presentAndBlank() {
        var text="   ";
        final var controlText = new Mutable<>("Blank");
        final var expectedText = "Blank";
        new OptionalString(Optional.of(text)).ifPresentNotBlank(___text -> controlText.set("NotBlank"));

        assertEquals(expectedText, controlText.get());
    }

    @Test
    public void presentAndNotBlank() {
        var text="NotBlank";
        final var controlText = new Mutable<>("");
        final var expectedText = "NotBlank";
        new OptionalString(Optional.of(text)).ifPresentNotBlank(___text -> controlText.set("NotBlank"));

        assertEquals(expectedText, controlText.get());
    }

    @Test
    public void presentAndEmpty() {
        var text="";
        final var controlText = new Mutable<>("Empty");
        final var expectedText = "Empty";
        new OptionalString(Optional.of(text)).ifPresentNotEmpty(___text -> controlText.set("NotEmpty"));

        assertEquals(expectedText, controlText.get());
    }

    @Test
    public void presentAndNotEmpty() {
        var text="NotEmpty";
        final var controlText = new Mutable<>("");
        final var expectedText = "NotEmpty";
        new OptionalString(Optional.of(text)).ifPresentNotBlank(___text -> controlText.set("NotEmpty"));

        assertEquals(expectedText, controlText.get());
    }

    @Test
    public void presentAndBlankElse() {
        var text="   ";
        final var controlText = new Mutable<>("");
        final var expectedText = "Blank";
        new OptionalString(Optional.of(text)).ifPresentNotBlankOrElse(
                ___text -> controlText.set("NotBlank"),
                () -> controlText.set("Blank"));

        assertEquals(expectedText, controlText.get());
    }

    @Test
    public void presentAndNotBlankElse() {
        var text="NotBlank";
        final var controlText = new Mutable<>("");
        final var expectedText = "NotBlank";
        new OptionalString(Optional.of(text)).ifPresentNotBlankOrElse(
                ___text -> controlText.set("NotBlank"),
                () -> controlText.set("Blank"));

        assertEquals(expectedText, controlText.get());
    }

    @Test
    public void nullAndNotBlankElse() {
        String text=null;
        final var controlText = new Mutable<>("");
        final var expectedText = "Blank";
        new OptionalString(Optional.ofNullable(text)).ifPresentNotBlankOrElse(
                ___text -> controlText.set("NotBlank"),
                () -> controlText.set("Blank"));

        assertEquals(expectedText, controlText.get());
    }

    @Test
    public void presentAndEmptyElse() {
        var text="";
        final var controlText = new Mutable<>("");
        final var expectedText = "Empty";
        new OptionalString(Optional.of(text)).ifPresentNotEmptyOrElse(
                ___text -> controlText.set("NotEmpty"),
                () -> controlText.set("Empty"));

        assertEquals(expectedText, controlText.get());
    }

    @Test
    public void presentAndNotEmptyElse() {
        var text="NotEmpty";
        final var controlText = new Mutable<>("");
        final var expectedText = "NotEmpty";
        new OptionalString(Optional.of(text)).ifPresentNotEmptyOrElse(
                ___text -> controlText.set("NotEmpty"),
                () -> controlText.set("Empty"));

        assertEquals(expectedText, controlText.get());
    }

    @Test
    public void nullAndNotEmptyElse() {
        String text=null;
        final var controlText = new Mutable<>("");
        final var expectedText = "Empty";
        new OptionalString(Optional.ofNullable(text)).ifPresentNotEmptyOrElse(
                ___text -> controlText.set("NotEmpty"),
                () -> controlText.set("Empty"));

        assertEquals(expectedText, controlText.get());
    }

}
