package libraryJFrame;

import java.awt.EventQueue;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * @author - Morgan Fidler
 */
// TODO: missing logical implementation

public class LibraryFrameFour extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelSearch;
	private JPasswordField txtID;
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
					LibraryFrameFour frame = new LibraryFrameFour();
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
		btnValidate.setFont(new Font("Arial", Font.PLAIN, 8));
		btnValidate.setHorizontalAlignment(SwingConstants.CENTER);
		btnValidate.setPreferredSize(new Dimension(50, 35));

		btnBackS3.setPreferredSize(new Dimension(50, 35));

		JPanel panelButtons = new JPanel(new GridLayout(1, 2));
		panelButtons.setPreferredSize(new Dimension(100, 35));

		panelButtons.add(btnValidate);
		panelButtons.add(btnBackS3);

		panelSearch.add(panelButtons, BorderLayout.EAST);
	}

	// Creates the button to return to S3 in the searchPanel
	private void btnBackS3() {
		btnBackS3 = new JButton("<-");
	}

	// creates the search bar field in the searchPanel
	private void txtID() {
		txtID = new JPasswordField();
		txtID.setEchoChar('*');
		txtID.setHorizontalAlignment(SwingConstants.LEFT);
		txtID.setFont(new Font("Arial", Font.PLAIN, 14));
		txtID.setBorder(new LineBorder(Color.BLACK, 1));
		txtID.setBackground(Color.WHITE);

		panelSearch.add(txtID, BorderLayout.CENTER);
	}

	// Creates the panel to hold the search panel items
	private void panelSearch() {
		panelSearch = new JPanel();
		panelSearch.setLayout(new BorderLayout(0, 0));
		panelSearch.setBackground(contentPane.getBackground());

		contentPane.add(panelSearch, BorderLayout.SOUTH);
	}

	private void txtMessage() {
		JTextArea txtMessage = new JTextArea();
		txtMessage.setFont(new Font("Arial", Font.PLAIN, 36));
		txtMessage.setEditable(false);
		txtMessage.setLineWrap(true);
		txtMessage.setWrapStyleWord(true);
		txtMessage.setBackground(contentPane.getBackground());
		txtMessage.setBorder(null);

		txtMessage.setText(
			"Please wait for a Library Employee to assist you in authorization.");

		contentPane.add(txtMessage, BorderLayout.CENTER);
	}

	// Creates the base layout for everything
	private void baseFrameS4() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		setContentPane(contentPane);
	}

}