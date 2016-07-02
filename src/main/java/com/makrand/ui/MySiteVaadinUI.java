package com.makrand.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.makrand.services.Greeter;
import com.makrand.view.UIScopedView;
import com.makrand.view.ViewScopedView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Theme("valo")
@SuppressWarnings({ "serial" , "unused"})
@SpringUI//(path="/mysite")
public class MySiteVaadinUI extends UI {
	
	
	@Autowired
	private Greeter greeter;
	
	@Autowired
	private SpringViewProvider viewProvider;

	@Override
	protected void init(VaadinRequest request) {
		//setContent(new Label(greeter.sayHello()));
		
		final VerticalLayout root = new VerticalLayout();
		root.setSizeFull();
		root.setMargin(true);
		root.setSpacing(true);
		setContent(root);
		
		final CssLayout cssLayout = new CssLayout();
		cssLayout.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		cssLayout.addComponent(createNavigationButton("UI Scoped view", UIScopedView.VIEW_NAME));
		cssLayout.addComponent(createNavigationButton("View scoped view", ViewScopedView.VIEW_NAME));
		root.addComponent(cssLayout);
		
		final Panel viewContainer = new Panel();
		viewContainer.setSizeFull();
		root.addComponent(viewContainer);
		root.setExpandRatio(viewContainer, 1.0f);
		
		Navigator 	navigator = new Navigator(this, viewContainer);
		navigator.addProvider(viewProvider);

	}
	
	private Button createNavigationButton(String caption, final String viewName){
		
		final Button button = new Button(caption);
		button.addStyleName(ValoTheme.BUTTON_SMALL);
		button.addClickListener(event -> getUI().getNavigator().navigateTo(viewName));
		
		return button;
	}

}
