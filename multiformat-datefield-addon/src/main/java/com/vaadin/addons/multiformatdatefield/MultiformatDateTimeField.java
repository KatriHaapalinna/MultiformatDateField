package com.vaadin.addons.multiformatdatefield;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.vaadin.addons.multiformatdatefield.client.MultiformatDateTimeFieldState;
import com.vaadin.ui.DateTimeField;

public class MultiformatDateTimeField extends DateTimeField {

    private ArrayList<String> formats = new ArrayList<>();

    public MultiformatDateTimeField() {
        super();
    }

    /**
     * Constructs a new {@code MultiformatDateTimeField} with the given caption
     * and initial text contents.
     *
     * @param caption
     *            the caption <code>String</code> for the editor.
     * @param value
     *            the LocalDate value.
     */
    public MultiformatDateTimeField(String caption, LocalDateTime value) {
        super(caption, value);
    }

    /**
     * Constructs an empty {@code MultiformatDateTimeField} with caption.
     *
     * @param caption
     *            the caption of the datefield.
     */
    public MultiformatDateTimeField(String caption) {
        super(caption);
    }

    /**
     * Constructs a new {@code MultiformatDateTimeField} with a value change
     * listener.
     * <p>
     * The listener is called when the value of this
     * {@code MultiformatDateTimeField} is changed either by the user or
     * programmatically.
     *
     * @param valueChangeListener
     *            the value change listener, not {@code null}
     */
    public MultiformatDateTimeField(
            ValueChangeListener<LocalDateTime> valueChangeListener) {
        super();
        addValueChangeListener(valueChangeListener);
    }

    /**
     * Constructs a new {@code MultiformatDateTimeField} with the given caption
     * and a value change listener.
     * <p>
     * The listener is called when the value of this
     * {@code MultiformatDateTimeField} is changed either by the user or
     * programmatically.
     *
     * @param caption
     *            the caption for the field
     * @param valueChangeListener
     *            the value change listener, not {@code null}
     */
    public MultiformatDateTimeField(String caption,
            ValueChangeListener<LocalDateTime> valueChangeListener) {
        this(valueChangeListener);
        setCaption(caption);
    }

    /**
     * Constructs a new {@code MultiformatDateTimeField} with the given caption,
     * initial text contents and a value change listener.
     * <p>
     * The listener is called when the value of this
     * {@code MultiformatDateTimeField} is changed either by the user or
     * programmatically.
     *
     * @param caption
     *            the caption for the field
     * @param value
     *            the value for the field, not {@code null}
     * @param valueChangeListener
     *            the value change listener, not {@code null}
     */
    public MultiformatDateTimeField(String caption, LocalDateTime value,
            ValueChangeListener<LocalDateTime> valueChangeListener) {
        this(caption, value);
        addValueChangeListener(valueChangeListener);
    }

    /**
     * Set alternative input formats accepted by the component. The alternative
     * formats are applied in the order they are assigned in.
     *
     * @param formatStrings
     *            List of dateformat strings
     */
    public void setAlternativeFormats(List<String> formatStrings) {
        formats = new ArrayList<>(formatStrings);
        getState().alternativeFormats = formats
                .toArray(new String[formats.size()]);
    }

    public List<String> getAlternativeFormats() {
        return formats;
    }

    /**
     * Add alternative input format to the end of the alternative formats list.
     *
     * @param formatString
     *            String
     */
    public void addAlternativeFormat(String formatString) {
        String format = Objects.toString(formatString, "").trim();

        if (!format.isEmpty() && !formats.contains(format)) {
            formats.add(format);
            setAlternativeFormats(formats);
        }
    }

    @Override
    protected MultiformatDateTimeFieldState getState() {
        return (MultiformatDateTimeFieldState) super.getState();
    }
}