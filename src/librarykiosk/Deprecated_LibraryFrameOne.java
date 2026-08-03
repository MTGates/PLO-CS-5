package librarykiosk;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.SpringLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Morgan Fidler
 */
//TODO: missing logical implementation

public class Deprecated_LibraryFrameOne extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPasswordField passwordField;
	
	// Declares all fields for the GUI for continual reference after initialization
	JPanel panelTextButton = new JPanel();
	JLabel lblPleaseID = new JLabel("Please enter your Library ID # and Name");
	JButton btnEnterID = new JButton("Enter ->");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Deprecated_LibraryFrameOne frame = new Deprecated_LibraryFrameOne();
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
	public Deprecated_LibraryFrameOne() {
		baseFrameOne();
		
		lblPleaseID();
	}

	private void baseFrameOne() {
		// Creates the base frame that will contain a subframe for enter/password box, and a label at the top
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		// Creates a subframe that contains the password box and an enter button
		getContentPane().add(panelTextButton, BorderLayout.CENTER);
		SpringLayout sl_panelTextButton = new SpringLayout();
		panelTextButton.setLayout(sl_panelTextButton);
		// Creates the enter button, the password box, and the label, in that order.
		JButton btnEnterID = btnEnter(panelTextButton, sl_panelTextButton);
		passwordFieldS1(panelTextButton, sl_panelTextButton, btnEnterID);
	}

	private void lblPleaseID() {
		lblPleaseID.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseID.setFont(new Font("Arial", Font.BOLD, 20));
		getContentPane().add(lblPleaseID, BorderLayout.NORTH);
		lblPleaseID.setBorder(new EmptyBorder(12, 20, 12, 20));
	}

	private JButton btnEnter(JPanel panelTextButton, SpringLayout sl_panelTextButton) {
		sl_panelTextButton.putConstraint(SpringLayout.WEST, btnEnterID, 150, SpringLayout.WEST, panelTextButton);
		sl_panelTextButton.putConstraint(SpringLayout.SOUTH, btnEnterID, -10, SpringLayout.SOUTH, panelTextButton);
		sl_panelTextButton.putConstraint(SpringLayout.EAST, btnEnterID, -149, SpringLayout.EAST, panelTextButton);
		btnEnterID.addMouseListener(new MouseAdapter() {
			// The compareID() method uses the current data in the passwordField to validate
			@Override
			public void mouseClicked(MouseEvent e) {
				if(compareID()) {
					Deprecated_LibraryFrameOne kioskFrameOne = new Deprecated_LibraryFrameOne();
					Deprecated_LibraryFrameTwo kioskFrameTwo = new Deprecated_LibraryFrameTwo();
					kioskFrameTwo.setVisible(true);
					kioskFrameOne.setVisible(false);
				}
			}
		});
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
	
	/**
	 * @return - true if ID matches with an existing user, false if not, error if not an int
	 * @param - 
	 * Validates the ID by creating a temporary user object with the given ID # in passwordField and returns true if the
	 * User class method validateID() returns true
	 */
	public boolean compareID() {
		
		char[] validationArray = passwordField.getPassword();
		String validationString = new String(validationArray);
		Integer validationInteger = Integer.parseInt(validationString);
		
		User tempUser = new User(validationInteger, "name");
		
		if (tempUser.validateId()) {
			return true;
		};
		
		return false;

		// TODO: Implement error handling
	}
}
