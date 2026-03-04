package com.makrand.view;

import com.makrand.services.Greeter;
import com.makrand.services.ViewGreeter;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "view", layout = MainLayout.class)
@PageTitle("View Scoped View")
public class ViewScopedView extends VerticalLayout {

	public ViewScopedView(ViewGreeter viewGreeter, Greeter uiGreeter) {
		add(new Paragraph("This is a view scoped view."));
		add(new Paragraph(viewGreeter.sayHello()));
		add(new Paragraph(uiGreeter.sayHello()));
	}
}
