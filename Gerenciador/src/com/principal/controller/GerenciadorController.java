package com.principal.controller;

import java.util.Date;

import com.login.controller.LoginController;
import com.login.model.Login;
import com.principal.helper.Header;
import com.principal.helper.Session;
import com.principal.helper.SqlScript;
import com.principal.view.menu.Menu;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class GerenciadorController {

	private Window mainWindow;
	private VerticalLayout main;
	private LoginController loginController;
	private HorizontalSplitPanel menu;
	private Session session;
	
	public GerenciadorController(Window  mainWindow, VerticalLayout main){
		
	   this.mainWindow = mainWindow;	
	   this.main = main;	
	   gc();
	   login ();
	}
	
	public void login(){
	   main.removeAllComponents();
	   loginController = new LoginController();
	   loginController.setGerenciadorController(this);
	   mainWindow.addWindow(loginController.getView());
		
	}
	
	public void init(){
	    main.setSizeFull();
	    Header header = new Header(this);
	    main.addComponent(header);
	    Menu menu = new Menu(this);
	    setMenu(menu);
	    main.addComponent(menu);
	    main.setExpandRatio(menu,1 );
	    
	}

	public Window getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(Window mainWindow) {
		this.mainWindow = mainWindow;
	}

	public VerticalLayout getMain() {
		return main;
	}

	public void setMain(VerticalLayout main) {
		this.main = main;
	}
	
	public void gc(){
		System.gc();
	}
	
	public void logoOff(){
		Login l = getSession().getUsuario().getLogin();
		l.setDataEncerramento(new Date());
		l.setAtivo(false);
		l.alterar();
		
		try {
			 System.gc();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		login();
	}

	public HorizontalSplitPanel getMenu() {
		return menu;
	}

	public void setMenu(HorizontalSplitPanel menu) {
		this.menu = menu;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
	
	
}
