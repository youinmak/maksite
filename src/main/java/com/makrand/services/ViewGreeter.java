package com.makrand.services;

import com.makrand.view.ViewScopedView;
import com.vaadin.flow.spring.annotation.RouteScope;
import com.vaadin.flow.spring.annotation.RouteScopeOwner;
import com.vaadin.flow.spring.annotation.SpringComponent;

@SpringComponent
@RouteScope
@RouteScopeOwner(ViewScopedView.class)
public class ViewGreeter {

	public String sayHello(){
		return "Hello from a view scoped bean " + toString();
	}
}
