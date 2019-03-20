package com.vaadin.addons.multiformatdatefield.client;

import com.vaadin.addons.multiformatdatefield.MultiformatDateTimeField;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.datefield.PopupDateTimeFieldConnector;
import com.vaadin.shared.ui.Connect;

@Connect(MultiformatDateTimeField.class)
public class MultiformatDateTimeFieldConnector
        extends PopupDateTimeFieldConnector {

    @Override
    public MultiformatDateTimeFieldWidget getWidget() {
        return (MultiformatDateTimeFieldWidget) super.getWidget();
    }

    @Override
    public MultiformatDateTimeFieldState getState() {
        return (MultiformatDateTimeFieldState) super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);

        if (!getWidget().getAlternativeFormats()
                .equals(getState().alternativeFormats)) {
            getWidget().setAlternativeFormats(getState().alternativeFormats);
        }
    }
}
