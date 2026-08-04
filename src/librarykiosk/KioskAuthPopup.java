package librarykiosk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author - Morgan Fidler
 * This is a popup frame for use with KioskApp.java to validate employee credentials before altering the status of a book.
 * This is the only surviving frame from a very poor attempt at GUI design. Therefore, the structure may differ slightly from the rest of the frames in KioskApp. You've been informed.
 */
public class KioskAuthPopup extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPanel panelKAPassHandlers;

	private JPasswordField txtKAPassField;
	private JPanel panelKAActions = new JPanel(new GridLayout(1, 2));
	private JButton btnKAClose;
	private JButton btnKAValidate;

	private JTextArea txtLblKAPleaseWait;
	private JLabel lblAuthorization;
	
	private KioskApp kioskApp;

	/**
	 * Create the frame.
	 */
	public KioskAuthPopup(KioskApp kioskApp) {
		this.kioskApp = kioskApp;
		
		CreateBaseFrameKA();

		createTxtLblKAPleaseWait();
		createLblAuthorization();

		createPanelKAPassHandlers();
		createTxtKAPassField();
		
		createPanelKAActions();
		createBtnKAValidate();
		createBtnKAClose();
	}

	/**
	 * Creates the base layout for everything
	 */
	private void CreateBaseFrameKA() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		setContentPane(contentPane);
	}

	/**
	 * Creates the main text message asking for authorization.
	 */
	private void createTxtLblKAPleaseWait() {
		txtLblKAPleaseWait = new JTextArea();

		txtLblKAPleaseWait.setFont(new Font("Arial", Font.PLAIN, 36));
		txtLblKAPleaseWait.setEditable(false);
		txtLblKAPleaseWait.setLineWrap(true);
		txtLblKAPleaseWait.setWrapStyleWord(true);
		txtLblKAPleaseWait.setBackground(contentPane.getBackground());
		txtLblKAPleaseWait.setBorder(null);
		txtLblKAPleaseWait.setPreferredSize(new Dimension(450, 120));

		txtLblKAPleaseWait.setText(
				"Please wait for a Library Employee to assist you in authorization."
		);

		contentPane.add(txtLblKAPleaseWait, BorderLayout.NORTH);
	}

	/**
	 * Creates the label indicating the required authorization input.
	 */
	private void createLblAuthorization() {
		lblAuthorization = new JLabel("Enter Authorization Code:");
		lblAuthorization.setFont(new Font("Arial", Font.BOLD, 24));

		JPanel panelKALabel = new JPanel(new BorderLayout());
		panelKALabel.setBackground(contentPane.getBackground());

		panelKALabel.add(lblAuthorization, BorderLayout.WEST);

		contentPane.add(panelKALabel, BorderLayout.CENTER);
	}

	/**
	 * Creates the bottom panel containing the password field and buttons.
	 */
	private void createPanelKAPassHandlers() {
		panelKAPassHandlers = new JPanel();

		panelKAPassHandlers.setLayout(new BorderLayout(0, 0));
		panelKAPassHandlers.setBackground(contentPane.getBackground());

		contentPane.add(panelKAPassHandlers, BorderLayout.SOUTH);
	}

	/**
	 * Creates the authorization code input field.
	 */
	private void createTxtKAPassField() {
		txtKAPassField = new JPasswordField();

		txtKAPassField.setHorizontalAlignment(SwingConstants.LEFT);
		txtKAPassField.setFont(new Font("Arial", Font.PLAIN, 14));
		txtKAPassField.setBorder(new LineBorder(Color.BLACK, 1));
		txtKAPassField.setBackground(Color.WHITE);
		txtKAPassField.setEchoChar('*');

		panelKAPassHandlers.add(txtKAPassField, BorderLayout.CENTER);
	}

	/**
	 * Creates the panel for Auth/Back buttons
	 */
	public void createPanelKAActions() {
		panelKAActions.setPreferredSize(new Dimension(100, 35));
		panelKAPassHandlers.add(panelKAActions, BorderLayout.EAST);
	}

	/**
	 * Creates the authorization and back buttons.
	 */
	private void createBtnKAValidate() {
		btnKAValidate = new JButton("->");
		btnKAValidate.setFont(new Font("Arial", Font.BOLD, 14));
		btnKAValidate.setHorizontalAlignment(SwingConstants.CENTER);
		btnKAValidate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (txtKAPassField.getPassword().length == 0) {
					txtKAPassField.setBackground(new Color(255, 0, 0));
				}
				else if (compareEmployeeID(txtKAPassField.getPassword())) { 
					initiateCheckOrReturn();
					dispose();
				}
				else {
					txtKAPassField.setBackground(new Color(255, 0, 0));
				}
			}
		});
		panelKAActions.add(btnKAValidate);
	}
	
	/**
	 * Creates the button returning to the previous screen.
	 */
	private void createBtnKAClose() {
		btnKAClose = new JButton("X");
		btnKAClose.setFont(new Font("Arial", Font.BOLD, 14));
		btnKAClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtKAPassField.setText("");
				dispose();
			}
		});
		panelKAActions.add(btnKAClose);
	}
	
	/**
	 * @param validationArray - Takes in a char[] based on the input provided in the txtVIPassBox and collected by an event handler to compare with known hard-coded IDs
	 * @return boolean - Returns true if a match is found in hardcoded IDs in either User or Employee
	 */
	public boolean compareEmployeeID(char[] validationArray) {
		String validationString = new String(validationArray);
		try {
		    Integer validationInteger = Integer.parseInt(validationString);
		    Employee tempEmployee = new Employee(validationInteger, "name");

		    if (tempEmployee.authorize()) {
		        return true;
		    }

		} catch (NumberFormatException e) {
			txtKAPassField.setBackground(new Color(255, 0, 0));
		}

		return false;
	}
	
	/**
	 * Determines whether we're using the checkout or the return process
	 */
	public void initiateCheckOrReturn() {
		if (kioskApp.checkoutOrReturn01 == 0) {
			kioskApp.workingLibrary.checkoutBook(kioskApp.searchingLibrary.get(kioskApp.selectedBookId));
		}
		else {
			kioskApp.workingLibrary.returnBook(kioskApp.searchingLibrary.get(kioskApp.selectedBookId));
		}
		kioskApp.refreshSearchResults();
		kioskApp.panelSearchCatalog.setVisible(true);
		kioskApp.panelBookInfo.setVisible(false);
		txtKAPassField.setText("");
	}
}