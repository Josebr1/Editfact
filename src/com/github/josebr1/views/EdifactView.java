package com.github.josebr1.views;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import com.github.josebr1.controllers.EdifactController;

public class EdifactView {

	private JButton btnConvert;
	private JButton btnSearch;
	private TitledBorder titleBorderLogs;
	private Icon iconBtnConvert;
	private Icon iconBtnSearch;
	private FileDialog fileDialog;
	private String pathFileEdi;
	private JTextArea txtLogs;
	
	public EdifactView() {
		pathFileEdi = "";
	}
	
	public void execute() {
		
		JFrame view = new JFrame("Convert Edifact to xml");
		view.setSize(250, 150);
		
		JPanel container = new JPanel();
		container.setLayout(null);
		
		iconBtnConvert = new ImageIcon(getClass().getResource("/com/github/josebr1/resources/icon_run.png"));
		btnConvert = new JButton("Convert");
		btnConvert.setBounds(0, 0, 125, 30);
		btnConvert.setIcon(iconBtnConvert);
		btnConvert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pathFileEdi.equals("")) {
					JOptionPane.showMessageDialog(null, "Not file");
					return;
				}
				EdifactController controller = new EdifactController();
				String result = controller.toXMLConvert(pathFileEdi);
				setLog(result);
			}
		});
		
		fileDialog = new FileDialog((Frame)null, "Select file edi");
		fileDialog.setMode(FileDialog.LOAD);
		
		iconBtnSearch = new ImageIcon(getClass().getResource("/com/github/josebr1/resources/icon_search.png"));
		btnSearch = new JButton("Search");
		btnSearch.setBounds(125, 0, 125, 30);
		btnSearch.setIcon(iconBtnSearch);
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fileDialog.setVisible(true);
				pathFileEdi = fileDialog.getDirectory() + fileDialog.getFile();
				setLog(pathFileEdi);
				System.out.println(pathFileEdi);
			}
		});

		titleBorderLogs = BorderFactory.createTitledBorder("Logs");

		txtLogs = new JTextArea();
		txtLogs.setBounds(2, 32, 240, 85);
		txtLogs.setLineWrap(true);
		txtLogs.setEditable(false);
		txtLogs.setBorder(titleBorderLogs);

		container.add(btnConvert);
		container.add(btnSearch);
		container.add(txtLogs);
		
		view.add(container);
		view.setVisible(true);
		view.setResizable(false);
		view.setLocationRelativeTo(null);
		view.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	private void setLog(String log) {
		txtLogs.setText("");
		txtLogs.setText(log);
	}
}
