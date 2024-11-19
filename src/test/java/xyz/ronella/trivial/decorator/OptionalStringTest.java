package xyz.ronella.trivial.decorator;

import org.junit.jupiter.api.Test;
import xyz.ronella.trivial.handy.ObjectRequiredException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class OptionalStringTest {

    @Test
    public void nullBlankString() {
        String text=null;
        assertTrue(new OptionalString(Optional.ofNullable(text)).isBlank());
    }

    @Test
    public void nullEmptyString() {
        String text=null;
        assertTrue(new OptionalString(Optional.ofNullable(text)).isEmpty());
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
    public void validStringBlank() {
        String text="Test";
        assertFalse(new OptionalString(Optional.of(text)).isBlank());
    }

    @Test
    public void validTestStringBlank() {
        var text="Test";
        assertFalse(new OptionalString(Optional.of(text)).isBlank());
    }

    @Test
    public void validStringEmpty() {
        String text="Test";
        assertFalse(new OptionalString(Optional.of(text)).isEmpty());
    }

    @Test
    public void validTestStringEmpty() {
        var text="Test";
        assertFalse(new OptionalString(Optional.of(text)).isEmpty());
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
    public void presentAndNotBlankNullConsumer() {
        assertThrows(ObjectRequiredException.class, () -> new OptionalString(Optional.of("text"))
                .ifPresentNotBlank(null));
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
    public void presentAndEmptyNullConsumer() {
        assertThrows(ObjectRequiredException.class, () ->
                new OptionalString(Optional.of("text")).ifPresentNotEmpty(null));
    }

    @Test
    public void presentAndNotEmpty() {
        var text="NotEmpty";
        final var controlText = new Mutable<>("");
        final var expectedText = "NotEmpty";
        new OptionalString(Optional.of(text)).ifPresentNotEmpty(___text -> controlText.set("NotEmpty"));

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
    public void presentAndBlankNullElse() {
        final var controlText = new Mutable<>();
        assertThrows(ObjectRequiredException.class, () ->
                new OptionalString(Optional.of("text")).ifPresentNotBlankOrElse(null, () -> controlText.set("Blank")));
    }

    @Test
    public void presentAndBlankElseNull() {
        final var controlText = new Mutable<>();
        assertThrows(ObjectRequiredException.class, () ->
                new OptionalString(Optional.of("text"))
                        .ifPresentNotBlankOrElse(___text -> controlText.set("Blank"), null));
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
    public void presentAndEmptyElseNull() {
        final var controlText = new Mutable<>("");

        assertThrows(ObjectRequiredException.class, () ->
                new OptionalString(Optional.of("text")).ifPresentNotEmptyOrElse(
                        ___text -> controlText.set("NotEmpty"), null));

    }

    @Test
    public void presentAndEmptyNullElse() {
        final var controlText = new Mutable<>("");

        assertThrows(ObjectRequiredException.class, () ->
                new OptionalString(Optional.of("text")).ifPresentNotEmptyOrElse(null, () -> controlText.set("Empty")));

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

    @Test
    public void docSample() {
        var text="NotBlank";
        final var controlText = new Mutable<>("");
        new OptionalString(Optional.of(text)).ifPresentNotBlank(controlText::set);

        System.out.println(controlText.get());
    }

    @Test
    public void nullToDecorate() {
        assertThrows(ObjectRequiredException.class, () -> new OptionalString(null));
    }

}
