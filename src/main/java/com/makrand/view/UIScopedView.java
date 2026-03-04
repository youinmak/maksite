package com.makrand.view;

import com.makrand.services.Greeter;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

@UIScope
@Route(value = "ui", layout = MainLayout.class)
@PageTitle("UI Scoped View")
public class UIScopedView extends VerticalLayout {

	public UIScopedView(Greeter greeter) {
		add(new Paragraph("This is a UI scoped view. Greeter says: " + greeter.sayHello()));
	}
}
