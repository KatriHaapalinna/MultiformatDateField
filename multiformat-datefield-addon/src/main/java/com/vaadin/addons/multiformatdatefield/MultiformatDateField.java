package com.vaadin.addons.multiformatdatefield;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.vaadin.addons.multiformatdatefield.client.MultiformatDateFieldState;
import com.vaadin.ui.DateField;

public class MultiformatDateField extends DateField {

    private ArrayList<String> formats = new ArrayList<>();

    public MultiformatDateField() {
        super();
    }

    /**
     * Constructs a new {@code MultiformatDateField} with the given caption and
     * initial text contents.
     *
     * @param caption
     *            the caption <code>String</code> for the editor.
     * @param value
     *            the LocalDate value.
     */
    public MultiformatDateField(String caption, LocalDate value) {
        super(caption, value);
    }

    /**
     * Constructs an empty {@code MultiformatDateField} with caption.
     *
     * @param caption
     *            the caption of the datefield.
     */
    public MultiformatDateField(String caption) {
        super(caption);
    }

    /**
     * Constructs a new {@code MultiformatDateField} with a value change
     * listener.
     * <p>
     * The listener is called when the value of this
     * {@code MultiformatDateField} is changed either by the user or
     * programmatically.
     *
     * @param valueChangeListener
     *            the value change listener, not {@code null}
     */
    public MultiformatDateField(
            ValueChangeListener<LocalDate> valueChangeListener) {
        super();
        addValueChangeListener(valueChangeListener);
    }

    /**
     * Constructs a new {@code MultiformatDateField} with the given caption and
     * a value change listener.
     * <p>
     * The listener is called when the value of this
     * {@code MultiformatDateField} is changed either by the user or
     * programmatically.
     *
     * @param caption
     *            the caption for the field
     * @param valueChangeListener
     *            the value change listener, not {@code null}
     */
    public MultiformatDateField(String caption,
            ValueChangeListener<LocalDate> valueChangeListener) {
        this(valueChangeListener);
        setCaption(caption);
    }

    /**
     * Constructs a new {@code MultiformatDateField} with the given caption,
     * initial text contents and a value change listener.
     * <p>
     * The listener is called when the value of this
     * {@code MultiformatDateField} is changed either by the user or
     * programmatically.
     *
     * @param caption
     *            the caption for the field
     * @param value
     *            the value for the field, not {@code null}
     * @param valueChangeListener
     *            the value change listener, not {@code null}
     */
    public MultiformatDateField(String caption, LocalDate value,
            ValueChangeListener<LocalDate> valueChangeListener) {
        this(caption, value);
        addValueChangeListener(valueChangeListener);
    }

    /**
     * Set alternative input formats accepted by the component. The alternative
     * formats are applied in the order they are assigned in.
     *
     * Set an empty list to remove all alternative formats.
     *
     * @param formatStrings
     *            List of dateformat strings, not {@code null}
     */
    public void setAlternativeFormats(List<String> formatStrings) {
        formats = new ArrayList<>(formatStrings);
        getState().alternativeFormats = formats
                .toArray(new String[formats.size()]);
    }

    public void addAlternativeFormat(String formatString) {
        String format = Objects.toString(formatString, "").trim();

        if (!format.isEmpty() && !formats.contains(format)) {
            formats.add(format);
            setAlternativeFormats(formats);
        }
    }

    @Override
    protected MultiformatDateFieldState getState() {
        return (MultiformatDateFieldState) super.getState();
    }
}
