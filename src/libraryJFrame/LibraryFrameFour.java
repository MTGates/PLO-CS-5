package libraryJFrame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JList;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;

/**
 * @author - Morgan Fidler
 */
// TODO: missing logical implementation

public class LibraryFrameFour extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelSearch;
	private JTextField txtID;
	private JButton btnBackS3;
	private JButton btnValidate;
	private JFormattedTextField txtMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibraryFrameTwo frame = new LibraryFrameTwo();
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
	public LibraryFrameFour() {
		baseFrameS4();
		txtMessage();
		// Search panel with text field, return, and query buttons
		panelSearch();
		txtID();
		btnBackS3();
		btnValidate();

	}

	// Creates the search button in the searchPanel
	private void btnValidate() {
		btnValidate = new JButton("L_>");
		btnValidate.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnValidate.setHorizontalAlignment(SwingConstants.RIGHT);
		panelSearch.add(btnValidate, BorderLayout.CENTER);
	}

	// Creates the button to return to S3 in the searchPanel
	private void btnBackS3() {
		btnBackS3 = new JButton("<-");
		panelSearch.add(btnBackS3, BorderLayout.EAST);
	}

	// creates the search bar field in the searchPanel
	private void txtID() {
		txtID = new JTextField();
		txtID.setHorizontalAlignment(SwingConstants.LEFT);
		txtID.setBounds(new Rectangle(2, 2, 2, 2));
		txtID.setFont(new Font("Arial", Font.PLAIN, 14));
		txtID.setColumns(10);
		txtID.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panelSearch.add(txtID, BorderLayout.WEST);
		txtID.setBorder(new EmptyBorder(10, 20, 10, 210));
	}

	// Creates the panel to hold the search panel items
	private void panelSearch() {
		panelSearch = new JPanel();
		contentPane.add(panelSearch, BorderLayout.SOUTH);
		panelSearch.setLayout(new BorderLayout(0, 0));
	}

	private void txtMessage() {
		JTextArea txtMessage = new JTextArea();
		txtMessage.setFont(new Font("Arial", Font.PLAIN, 46));
		txtMessage.setEditable(false);
		txtMessage.setLineWrap(true);
		txtMessage.setWrapStyleWord(true); 
		txtMessage.setText("Please wait for a Library Employee to assist you in authorization.");
		contentPane.add(txtMessage, BorderLayout.NORTH);
	}

	// Creates the base layout for everything 
	private void baseFrameS4() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
	}

}
