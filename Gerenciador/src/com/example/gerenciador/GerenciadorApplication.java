package com.example.gerenciador;

import com.vaadin.Application;
import com.vaadin.ui.*;

public class GerenciadorApplication extends Application {
	@Override
	public void init() {
		Window mainWindow = new Window("Gerenciador Application");
		Label label = new Label("Hello Vaadin user");
		mainWindow.addComponent(label);
		setMainWindow(mainWindow);
	}

}
