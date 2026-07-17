package libraryJFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.SpringLayout;

/**
 * @author Morgan Fidler
 */
public class LibraryFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibraryFrame frame = new LibraryFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LibraryFrame() {
		baseFrameOne();
	}

	private void baseFrameOne() {
		// Creates the base frame that will contain a subframe for enter/password box, and a label at the top
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		// Creates a subframe that contains the password box and an enter button
		JPanel panelTextButton = new JPanel();
		getContentPane().add(panelTextButton, BorderLayout.CENTER);
		SpringLayout sl_panelTextButton = new SpringLayout();
		panelTextButton.setLayout(sl_panelTextButton);
		// Creates the enter button, the password box, and the label, in that order.
		JButton btnEnterID = btnEnter(panelTextButton, sl_panelTextButton);
		passwordFieldS1(panelTextButton, sl_panelTextButton, btnEnterID);
		lblPleaseID();
	}

	private void lblPleaseID() {
		JLabel lblPleaseID = new JLabel("Please enter your Library ID #");
		lblPleaseID.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseID.setFont(new Font("Arial", Font.BOLD, 24));
		getContentPane().add(lblPleaseID, BorderLayout.NORTH);
		lblPleaseID.setBorder(new EmptyBorder(12, 20, 12, 20));
	}

	private JButton btnEnter(JPanel panelTextButton, SpringLayout sl_panelTextButton) {
		JButton btnEnterID = new JButton("Enter ->");
		sl_panelTextButton.putConstraint(SpringLayout.WEST, btnEnterID, 150, SpringLayout.WEST, panelTextButton);
		sl_panelTextButton.putConstraint(SpringLayout.SOUTH, btnEnterID, -10, SpringLayout.SOUTH, panelTextButton);
		sl_panelTextButton.putConstraint(SpringLayout.EAST, btnEnterID, -149, SpringLayout.EAST, panelTextButton);
		btnEnterID.setFont(new Font("Arial", Font.BOLD, 12));
		panelTextButton.add(btnEnterID);
		return btnEnterID;
	}

	private void passwordFieldS1(JPanel panelTextButton, SpringLayout sl_panelTextButton, JButton btnEnterID) {
		passwordField = new JPasswordField();
		sl_panelTextButton.putConstraint(SpringLayout.NORTH, passwordField, 60, SpringLayout.NORTH, panelTextButton);
		sl_panelTextButton.putConstraint(SpringLayout.WEST, passwordField, 68, SpringLayout.WEST, panelTextButton);
		sl_panelTextButton.putConstraint(SpringLayout.SOUTH, passwordField, -91, SpringLayout.NORTH, btnEnterID);
		sl_panelTextButton.putConstraint(SpringLayout.EAST, passwordField, 368, SpringLayout.WEST, panelTextButton);
		passwordField.setFont(new Font("Arial", Font.BOLD, 14));
		passwordField.setEchoChar('*');
		panelTextButton.add(passwordField);
	}
}
