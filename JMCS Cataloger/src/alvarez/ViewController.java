package alvarez;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

public class ViewController {
	
	//APPLICATION ELEMENTS: UI AND OBJECTS
	private static JFrame jframeWindow;
	private static JPanel panel;
	private static JButton inputBtn;
	private static JTextField inputTxt;
	private static JButton runCatalogBtn;
	private static JTextArea display;
	private static File fileToRead;

	public static void main(String[] args) {
		constructAppWindow();
		addListenerEvents();
	}
	
	private static void constructAppWindow() {
		jframeWindow = new JFrame();
		jframeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		inputBtn = new JButton("Code File");
		inputBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		inputTxt = new JTextField(25);
		inputTxt.setEditable(false);
		inputTxt.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		runCatalogBtn = new JButton("Build Catalog");
		runCatalogBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		
		display = new JTextArea("", 10, 400);
		display.setEditable(false);
		display.setLineWrap(true);
		display.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(display);
		scrollPane.setPreferredSize(new Dimension(390,400));
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(400, 520));
		panel.setBackground(Color.GRAY);
		panel.add(inputBtn);
		panel.add(inputTxt);
		panel.add(runCatalogBtn);
		panel.add(scrollPane);
		
		jframeWindow.add(panel);
		jframeWindow.pack();
		jframeWindow.setVisible(true);
	}
	
	private static void addListenerEvents() {
		inputBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestInputFile();
			}
		});
		
		
		runCatalogBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JMCS jmcs = new JMCS(fileToRead);
				jmcs.buildCatalog();
				display.setText(jmcs.toString());
			}
		});
		
	}
	
	public static void requestInputFile() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			fileToRead = jfc.getSelectedFile();
			inputTxt.setText(fileToRead.toString());
		}
	}

}
