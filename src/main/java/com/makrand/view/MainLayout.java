package com.makrand.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

	public MainLayout() {
		H2 title = new H2("Maksite");
		RouterLink homeLink = new RouterLink("Default view", DefaultView.class);
		RouterLink uiLink = new RouterLink("UI scoped view", UIScopedView.class);
		RouterLink viewLink = new RouterLink("View scoped view", ViewScopedView.class);

		HorizontalLayout header = new HorizontalLayout(title, homeLink, uiLink, viewLink);
		header.setWidthFull();
		header.setPadding(true);
		header.setSpacing(true);
		addToNavbar(header);
	}
}
