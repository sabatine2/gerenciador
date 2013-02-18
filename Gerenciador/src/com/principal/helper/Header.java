package com.principal.helper;


import com.midiasocial.controller.MidiaSocialMenuController;
import com.midiasocial.view.menu.MidiaSocialMenu;
import com.principal.controller.GerenciadorController;
import com.principal.gerenciador.GerenciadorApplication;
import com.principal.view.menu.Menu;
import com.usuario.model.Usuario;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.themes.BaseTheme;

/**
 * Class Header: Classe tem componentes correspondentes a parte superior da tela.
 * @author Ricardo
 */

@SuppressWarnings({"serial", "unused"})
public class Header extends CssLayout {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MenuBar menubar = new MenuBar();
	private CssLayout left;
	Resource res = new ThemeResource("../reindeer/Icons/home.png");
	/**
	 * Construtor cria os componentes 
	 * @author Ricardo
	 */
	  private GerenciadorController gerenciadorController;
		
	public Header(final GerenciadorController gerenciadorController) {
	       this.gerenciadorController = gerenciadorController;

		//Define propor�‹o da tela e estilo
		setWidth("100%");
		addStyleName("toolbar-invert");

		//Parte que fica a esquerda
		//Nome da aplica�‹o e menus superiores
		
		CssLayout left = new CssLayout();
		left.setSizeUndefined();
		left.addStyleName("left");
		addComponent(left);
		
		//Define o nome da aplica�‹o
		Label title = new Label("Midia Social SAC 2.0");
		title.addStyleName("h1");
		left.addComponent(title);
	
		//
		Button home = new Button("Home", new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Menu menu = new Menu(gerenciadorController);
				alterarMenu(menu);
			}
		});
		home.setStyleName("borderless");
		//home.setIcon(res);
		title.addStyleName("left");
		left.addComponent(home);
		
		//
		Button redesSociais = new Button("Redes Sociais", new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				
				MidiaSocialMenuController midiaSocialMenuController = new MidiaSocialMenuController(gerenciadorController);
				alterarMenu(midiaSocialMenuController.getMidiaSocialMenu());
			}
		});
		redesSociais.setStyleName("borderless");
		title.addStyleName("left");
		left.addComponent(redesSociais);
		
		//Parte que fica a direita
		//Login
		//
		CssLayout right = new CssLayout();
		right.setSizeUndefined();
		right.addStyleName("right");
		addComponent(right);
		
		Label user = new Label("User: "+ gerenciadorController.getSession().getUsuario().getNome());
		title.addStyleName("right");
		right.addComponent(user);
		
		//Para confirmar o login
		Button login = new Button("Logoff",new Button.ClickListener() {
            // inline click-listener
            public void buttonClick(ClickEvent event) {
            	 logoOff();
            }
        });
		login.addStyleName("border-normal");
		right.addComponent(login);
   }
	
	public void logoOff(){
		
		 gerenciadorController.logoOff();
	}
	
	  private Command menuCommand = new Command() {
		
		public void menuSelected(MenuItem selectedItem) {
			
		}
	};
	
	public void alterarMenu(HorizontalSplitPanel menu){
		
         gerenciadorController.getMain().removeComponent(gerenciadorController.getMenu());
         gerenciadorController.setMenu(null);
         gerenciadorController.setMenu(menu);
         gerenciadorController.getMain().addComponent(gerenciadorController.getMenu());
         gerenciadorController.getMain().setExpandRatio(menu,1 );
         gerenciadorController.gc();
        
	}
}