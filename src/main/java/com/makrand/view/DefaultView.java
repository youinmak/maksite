package com.makrand.view;

import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Default View")
public class DefaultView extends VerticalLayout {

	public DefaultView() {
		add(new Paragraph("This is the default view."));
	}
}
