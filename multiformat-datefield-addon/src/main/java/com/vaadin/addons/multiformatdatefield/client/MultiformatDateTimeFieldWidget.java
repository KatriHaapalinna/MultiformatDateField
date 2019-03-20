package com.vaadin.addons.multiformatdatefield.client;

import java.util.Date;

import com.google.gwt.i18n.client.TimeZone;
import com.vaadin.client.LocaleNotLoadedException;
import com.vaadin.client.LocaleService;
import com.vaadin.client.ui.VPopupTimeCalendar;

public class MultiformatDateTimeFieldWidget extends VPopupTimeCalendar {

    private static final String PARSE_ERROR_CLASSNAME = "-parseerror";

    private static final String TEXTFIELD_ID = "field";

    /** For internal use only. May be removed or replaced in the future. */
    private String formatStr;

    /** For internal use only. May be removed or replaced in the future. */
    private TimeZone timeZone;

    private String[] formatStrings = {};

    /**
     * Gets the date format string for the current locale.
     *
     * @return the format string
     */
    @Override
    public String getFormatString() {
        if (formatStr == null) {
            setFormatString(createFormatString());
        }
        return formatStr;
    }

    public void setAlternativeFormats(String[] formatStrings) {
        this.formatStrings = formatStrings;
    }

    public String[] getAlternativeFormats() {
        return formatStrings;
    }

    /**
     * Sets the time zone for the field.
     *
     * @param timeZone
     *            the new time zone to use
     * @since 8.2
     */
    @Override
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * Create a format string suitable for the widget in its current state.
     *
     * @return a date format string to use when formatting and parsing the text
     *         in the input field
     * @since 8.1
     */
    @Override
    protected String createFormatString() {
        if (isYear(getCurrentResolution())) {
            return "yyyy"; // force full year
        }
        try {
            String frmString = LocaleService.getDateFormat(currentLocale);
            return cleanFormat(frmString);
        } catch (LocaleNotLoadedException e) {
            // TODO should die instead? Can the component survive
            // without format string?
            return null;
        }
    }

    /**
     * Sets the date format string to use for the text field.
     *
     * @param formatString
     *            the format string to use, or {@code null} to force re-creating
     *            the format string from the locale the next time it is needed
     * @since 8.1
     */
    @Override
    public void setFormatString(String formatString) {
        formatStr = formatString;
    }

    @Override
    public void updateBufferedValues() {
        updateDate();
        bufferedDateString = text.getText();
        updateBufferedResolutions();
    }

    private void updateDate() {
        if (!text.getText().isEmpty()) {
            try {
                String enteredDate = text.getText();
                Date newDate = tryParseToDate(enteredDate, lenient);
                setDate(newDate);

                if (lenient) {
                    text.setValue(getDateTimeService().formatDate(newDate,
                            getFormatString(), timeZone), false);
                }

                removeStyleName(getStylePrimaryName() + PARSE_ERROR_CLASSNAME);
            } catch (final Exception e) {
                System.out.println(e.getMessage());
                addStyleName(getStylePrimaryName() + PARSE_ERROR_CLASSNAME);
                setDate(null);
            }
        } else {
            setDate(null);
            // remove possibly added invalid value indication
            removeStyleName(getStylePrimaryName() + PARSE_ERROR_CLASSNAME);
        }
    }

    private Date tryParseToDate(String dateString, boolean lenient) {
        try {
            return getDateTimeService().parseDate(dateString, getFormatString(),
                    lenient);
        } catch (IllegalArgumentException e) {
            // NO-OP: try alternative formats if present
        }
        for (String fmt : formatStrings) {
            try {
                return getDateTimeService().parseDate(dateString, fmt, lenient);
            } catch (IllegalArgumentException e) {
                continue;
            }
        }
        return null;
    }
}
