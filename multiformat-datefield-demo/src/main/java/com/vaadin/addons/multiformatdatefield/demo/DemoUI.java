package com.vaadin.addons.multiformatdatefield.demo;

import java.time.LocalDate;
import java.util.Arrays;

import javax.servlet.annotation.WebServlet;

import com.vaadin.addons.multiformatdatefield.MultiformatDateField;
import com.vaadin.addons.multiformatdatefield.MultiformatDateTimeField;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Binder;
import com.vaadin.data.validator.DateRangeValidator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("demo")
@Title("MultiformatDateField Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    private LocalDate date = LocalDate.now();

    @Override
    protected void init(VaadinRequest request) {

        // Initialize our new UI component
        final MultiformatDateField component = new MultiformatDateField(
                "Supports input formats dd.MM.yyy and ddMMyyyy");
        component.setDateFormat("dd.MM.yyyy");
        component
                .setAlternativeFormats(Arrays.asList("dd/MM/yyyy", "ddMMyyyy"));

        final MultiformatDateTimeField c1 = new MultiformatDateTimeField(
                "Supports input formats dd.MM.yyy HH:mm and ddMMyyyyHHmm");
        c1.setDateFormat("dd.MM.yyyy HH:mm");
        c1.setAlternativeFormats(
                Arrays.asList("dd/MM/yyyy HH:mm", "ddMMyyyyHHmm"));
        Binder<LocalDate> b = new Binder<>();
        b.forField(component)
                .withValidator(new DateRangeValidator("Has to before now",
                        LocalDate.MIN, LocalDate.now()))
                .bind((d) -> {
                    return date;
                }, (d, v) -> {
                    date = d;
                });

        final VerticalLayout layout = new VerticalLayout();

        layout.addComponents(component, c1);

        setContent(layout);
    }
}
