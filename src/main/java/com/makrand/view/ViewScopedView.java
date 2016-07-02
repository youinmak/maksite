package com.makrand.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.makrand.services.Greeter;
import com.makrand.services.ViewGreeter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@SpringView(name=ViewScopedView.VIEW_NAME)
public class ViewScopedView extends VerticalLayout implements View {

	public static final String VIEW_NAME = "view";
	
	@Autowired
	private ViewGreeter viewGreeter;
	
	@Autowired
	private Greeter uiGreeter;
	
	@PostConstruct
	void init(){
		setMargin(true);
		setSpacing(true);
		addComponent(new Label("This is a view scoped view."));
		addComponent(new Label(viewGreeter.sayHello()));
		addComponent(new Label(uiGreeter.sayHello()));
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// the view is constructed in the init() method()

	}

}
