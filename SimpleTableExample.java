package beltexam;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class SimpleTableExample
		extends 	JFrame
 {
	// Instance attributes used in this example
	private	JPanel		topPanel;
	private	JTable		table, table2;
	private	JScrollPane scrollPane;

	// Constructor of main frame
	public SimpleTableExample()
	{
		// Set the frame characteristics
		setTitle( "������� � 11 ����" );
		setSize( 400, 200 );
		setBackground( Color.gray );

		// Create a panel to hold all other components
		topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );

		// Create columns names
		String columnNames[] = { ""
				+ "���", "����", "�����������" };

		// Create some data
		String dataValues[][] =
		{
			{ "������� ������", "11�", "��������" },
			{ "���� �������", "11�", "�����" },
			{ "����� �����", "11�", "�����" },
			{ "����� ��������", "11�", "��������" }
		};

		// Create a new table instance
		table = new JTable( dataValues, columnNames );
		table2 = new JTable( dataValues, columnNames );

		// Add the table to a scrolling pane
		scrollPane = new JScrollPane( table );
		//scrollPane = new JScrollPane ( table2);
		topPanel.add( scrollPane, BorderLayout.CENTER );
	}

	// Main entry point for this example
	public static void main( String args[] )
	{
		// Create an instance of the test application
		SimpleTableExample mainFrame	= new SimpleTableExample();
		//SimpleTableExample mainFrame2	= new SimpleTableExample();
		mainFrame.setVisible( true );
		//mainFrame2.setVisible( true );
	}
}
