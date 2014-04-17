package Menus;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;

import Components.CCheckBoxMenuItem;
import Components.CMenu;
import Components.CMenuItem;
import Components.CTabbedPane;
import Gui.JEditor;
import OptionDialogs.SearchDialog;
import OptionDialogs.SignatureDialog;
import OptionDialogs.StatisticsDialog;

public class ToolMenu extends CMenu{
	

	private static final long serialVersionUID = 1L;
	public static CCheckBoxMenuItem hulnumbers;
	private CMenuItem stats, search, searchInternet , gotoLine, toLower, toUpper,themes;
	private CMenu insert;
	private CMenuItem date, signature;
	
	public ToolMenu(String text, char Mnmonic) {
		super(text, Mnmonic);
		init();
		addToMenu();
		addActions();
	}
	
	public void init(){
		
		hulnumbers = new CCheckBoxMenuItem("Show line numbers", "hide or unhide the line numbers");
		hulnumbers.setSelected(true);
		hulnumbers.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));
		stats = new CMenuItem("Document statistics", "shows the statistics for the current document", 'D', KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0));
		search = new CMenuItem("Search", "search for text in the current documenr", 'S', KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
		searchInternet = new CMenuItem("Search Internet", "search for content on the internet", '1', null);
		insert = new CMenu("Insert", 'I');
		gotoLine = new CMenuItem("Goto line", "go to a specific line number", 'G', KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
		toLower = new CMenuItem("To lower", "set all the text to lower case characters", '1', null);
		toUpper = new CMenuItem("To upper", "set all the text to upper case letters", '1', null);
		themes = new CMenuItem("Themes", "change the look and feel of the JEditor", 'T', null);
		
		date = new CMenuItem("Date", "insert the current date", 'D', null);
		signature = new CMenuItem("Signature", "insert your signature", 'S', null);
	}
	
	public void addToMenu(){
		add(hulnumbers);
		addSeparator();
		add(stats);
		add(search);
		add(searchInternet);
		add(insert);
		add(gotoLine);
		addSeparator();
		add(toLower);
		add(toUpper);
		addSeparator();
		add(themes);
		
		insert.add(date);
		insert.add(signature);
	}
	
	public void addActions(){
		hulnumbers.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (hulnumbers.isSelected()) {
					CTabbedPane.getInstance().getPanel().getScrollPane().setRowHeaderView(CTabbedPane.getInstance().getPanel().getPanelHeader());
				}
				else {
					CTabbedPane.getInstance().getPanel().getScrollPane().setRowHeaderView(null);
				}

				JEditor.frame.validate();

			}
		});
		
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SearchDialog.getInstance().setVisible(true);
			}
		});
		
		searchInternet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String res = JOptionPane.showInputDialog(JEditor.frame, "Enter search text:", "Search internet", JOptionPane.QUESTION_MESSAGE);
				
				if(res == null){
					return;
				}
				
				try {
					Desktop.getDesktop().browse(new URI("http://www.google.com/search?q="+res.replace(" ", "%20")));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		gotoLine.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String res = JOptionPane.showInputDialog(JEditor.frame, "Enter line number:", "Goto line", JOptionPane.QUESTION_MESSAGE);
				
				if(res == null){
					return;
				}
				
				int linenum;
				
				try{

					linenum = Integer.parseInt(res);
					
				}catch(Exception e){

					JOptionPane.showMessageDialog(JEditor.frame, "Please enter a valid line number.", "Error", JOptionPane.WARNING_MESSAGE);
					gotoLine.doClick();
					return;
				}
				
				
				try{
					CTabbedPane.getInstance().getPanel().getTextArea().setCaretPosition(CTabbedPane.getInstance().getPanel().getTextArea().getDocument().getDefaultRootElement().getElement(linenum - 1).getStartOffset());
					
				} catch(Exception e){
					
					JOptionPane.showMessageDialog(JEditor.frame, "The specified line number cannot be reached", "Error", JOptionPane.ERROR_MESSAGE);
				
				}
			}
		});
		
		stats.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new StatisticsDialog().show();
			}
		});
		
		date.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					CTabbedPane.getInstance().getPanel().getTextArea().getDocument().insertString(CTabbedPane.getInstance().getPanel().getTextArea().getCaretPosition(), java.util.Calendar.getInstance().getTime()+"", null);

				} catch (BadLocationException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(JEditor.frame, "An error occured while inserting the date.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		signature.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SignatureDialog().setVisible(true);
			}
		});
		
		themes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
	}

}