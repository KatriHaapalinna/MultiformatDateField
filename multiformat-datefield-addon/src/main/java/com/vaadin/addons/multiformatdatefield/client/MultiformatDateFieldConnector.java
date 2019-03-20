package com.vaadin.addons.multiformatdatefield.client;

import com.vaadin.addons.multiformatdatefield.MultiformatDateField;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.datefield.PopupDateFieldConnector;
import com.vaadin.shared.ui.Connect;

@Connect(MultiformatDateField.class)
public class MultiformatDateFieldConnector extends PopupDateFieldConnector {

    @Override
    public MultiformatDateFieldWidget getWidget() {
        return (MultiformatDateFieldWidget) super.getWidget();
    }

    @Override
    public MultiformatDateFieldState getState() {
        return (MultiformatDateFieldState) super.getState();
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
