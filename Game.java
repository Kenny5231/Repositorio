/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacion.pkg1__;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/**
 *
 * @author Kenny
 */
class Game extends Container
{
	protected PlayingField myField;

	private JPanel statusBar, messagePanel;
	private JTextField message;
	private JButton send;
	protected Point thePoint;
	protected int result;
	protected Connection myConnection;
	protected boolean demoRunning = true;

	Game(String borderTitle)
	{
		myField = new PlayingField(borderTitle);

		setLayout(new BorderLayout());
		add(myField, BorderLayout.CENTER);

		(messagePanel = new JPanel()).setLayout(new BorderLayout());
		messagePanel.setBorder(new TitledBorder("Message Dispatcher"));
		message = new JTextField();
		message.addKeyListener(new KeyAdapter()
	  {
		public void keyTyped (KeyEvent ke)
		{
			if (ke.getKeyChar()==ke.VK_ENTER)
			{
				if (myConnection!=null && myConnection.established())
				{
				   myConnection.sendObject(new ObjectPacket(message.getText()));
				}
				myField.addMessage("Dispatched: " + message.getText());
				message.setText("");
				if (BattleShip.soundOn()) Sound.sonar.play();
			}
		}
	  });

	  ButtonHandler handle = new ButtonHandler();
		send = new JButton("Send");
		send.addActionListener(handle);

		messagePanel.add(message, BorderLayout.CENTER);
		messagePanel.add(send, BorderLayout.EAST);
		add(messagePanel, BorderLayout.SOUTH);
	}

	private class ButtonHandler implements ActionListener
   {
	  public void actionPerformed( ActionEvent e )
	  {
			if (e.getSource() == send)
			{
				if (myConnection!=null && myConnection.established())
				{
					myConnection.sendObject(new ObjectPacket(message.getText()));
				}
				myField.addMessage("Dispatched: " + message.getText());
				message.setText("");
				if (BattleShip.soundOn()) Sound.sonar.play();
			}
		}
	}
}

