package librarykiosk;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Rectangle;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTextArea;

public class KioskApp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	/**
	 *  Fields declared at global scope for updating and accessing
	 */
	private JPanel panelValidateUser = new JPanel();
	private final JLabel lblVIPleaseId = new JLabel("Please enter your Library ID #");
	private final JPanel panelVIPassHandlers = new JPanel();
	private final JPasswordField txtVIPassBox = new JPasswordField();
	private final JButton btnVIEnterId = new JButton("Enter ->");
	
	private JPanel panelSearchCatalog = new JPanel();
	private final JScrollPane scrollPane = new JScrollPane();
	private int selectedBookId = 0;
	private final JPanel panelSCActions = new JPanel();
	private final JTextField txtSearch = new JTextField();
	private final JButton btnSCBackVI = new JButton("<-");
	private final JButton btnSCQuery = new JButton("Q");
	private DefaultListModel<String> listModel;
	private JList<String> resultsList;
	
	private JPanel panelBookInfo = new JPanel();
	private final JPanel panelBIDisplayInfo = new JPanel();
	private String selectedBookInfo;
	private final JPanel panelBIActions = new JPanel();
	private final JButton btnCheckout = new JButton("Checkout");
	private final JButton btnReturn = new JButton("Return");
	private final JButton btnBIBackSC = new JButton("<-");
	private final JTextArea txtScrollPane = new JTextArea();
	
	public Library workingLibrary = new Library();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KioskApp frame = new KioskApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		// Main method code goes here
		
	}

	/**
	 * Create the frame.
	 */
	public KioskApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		// Populates a JPanel of the Validate User (first) frame
		panelValidateUser = createPanelValidateUser();
		panelValidateUser.setVisible(true);
		
		// Populates a JPanel of the Search Catalog (second) frame
		panelSearchCatalog = createPanelSearchCatalog();
		panelSearchCatalog.setVisible(false);
		
		// Populates a JPanel of the Book Info (third) frame
		panelBookInfo = createPanelBookInfo();
		panelBookInfo.setVisible(false);
		
	}

	private JPanel createPanelBookInfo() {
		// Creates, constraints, and adds the base panel for panelBookInfo
		panelBookInfo = new JPanel();
		panelBookInfo.setLayout(new BorderLayout(0, 0));
		panelBIDisplayInfo.setBackground(new Color(255, 255, 255));
		contentPane.add(panelBookInfo, "name_432252660636700");
		
		// Constraints and adds the results list to the center of the base panel's BorderLayout
		panelBookInfo.add(panelBIDisplayInfo, BorderLayout.CENTER); // Creates a subpanel even though it only contains one object - for flexibility during development
		panelBIDisplayInfo.setLayout(new BorderLayout(0, 0));
		txtScrollPane.setWrapStyleWord(true);
		txtScrollPane.setText("Error at some point when handling book index numbers, please go back and try again.");
		txtScrollPane.setLineWrap(true);
		txtScrollPane.setFont(new Font("Arial", Font.PLAIN, 16));
		txtScrollPane.setEditable(false);
		panelBIDisplayInfo.add(txtScrollPane, BorderLayout.NORTH); // Adds the display list to the subpanel
		
		// Constraints and adds the Checkout button to the subpanel
		panelBIActions.add(btnCheckout); // TODO: Implement functionality
		
		// Constraints and adds the Return-book button to the subpanel
		panelBIActions.add(btnReturn); // TODO: Implement functionality
		
		// Constraints and adds the return to last screen button to the subpanel
		btnBIBackSC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelSearchCatalog.setVisible(true);
				panelBookInfo.setVisible(false);
			}
		});
		btnBIBackSC.setPreferredSize(new Dimension(50, 35));
		panelBIActions.add(btnBIBackSC);
		
		
		// Constraints and adds the subpanel for the buttons
		panelBookInfo.add(panelBIActions, BorderLayout.SOUTH);
		panelBIActions.setLayout(new GridLayout(0, 3, 0, 0));
		btnCheckout.setPreferredSize(new Dimension(50, 35));
		btnCheckout.setHorizontalAlignment(SwingConstants.CENTER);
		btnCheckout.setFont(new Font("Arial", Font.PLAIN, 8));
		
		return panelBookInfo;
	}
	
	private JPanel createPanelValidateUser() {
		// Creates, constraints, and adds the base panel for ValidateId
		panelValidateUser = new JPanel();
		contentPane.add(panelValidateUser, "name_432252651177600");
		panelValidateUser.setLayout(new BorderLayout(0, 0));
		lblVIPleaseId.setOpaque(true);
		
		// Constraints and adds a label at the top of the base panel's BorderLayout 
		lblVIPleaseId.setHorizontalAlignment(SwingConstants.CENTER);
		lblVIPleaseId.setFont(new Font("Arial", Font.BOLD, 20));
		lblVIPleaseId.setBorder(new EmptyBorder(12, 20, 12, 20));
		panelValidateUser.add(lblVIPleaseId, BorderLayout.NORTH);
		
		// Constraints and adds the subpanel to hold the password box and the enter button
		panelValidateUser.add(panelVIPassHandlers, BorderLayout.CENTER);
		SpringLayout springLayVIPassHandlers = new SpringLayout();
		panelVIPassHandlers.setLayout(springLayVIPassHandlers);
		
		// Constraints and adds the Password Field
		springLayVIPassHandlers.putConstraint(SpringLayout.NORTH, txtVIPassBox, 60, SpringLayout.NORTH, panelVIPassHandlers);
		springLayVIPassHandlers.putConstraint(SpringLayout.WEST, txtVIPassBox, 60, SpringLayout.WEST, panelVIPassHandlers);
		springLayVIPassHandlers.putConstraint(SpringLayout.EAST, txtVIPassBox, -60, SpringLayout.EAST, panelVIPassHandlers);
		txtVIPassBox.setFont(new Font("Arial", Font.BOLD, 14));
		txtVIPassBox.setEchoChar('*');
		panelVIPassHandlers.add(txtVIPassBox);
		
		// Constraints and adds the Enter button
		springLayVIPassHandlers.putConstraint(SpringLayout.WEST, btnVIEnterId, 150, SpringLayout.WEST, panelVIPassHandlers);
		springLayVIPassHandlers.putConstraint(SpringLayout.SOUTH, btnVIEnterId, -10, SpringLayout.SOUTH, panelVIPassHandlers);
		springLayVIPassHandlers.putConstraint(SpringLayout.EAST, btnVIEnterId, -150, SpringLayout.EAST, panelVIPassHandlers);
		btnVIEnterId.setFont(new Font("Arial", Font.BOLD, 12));
		btnVIEnterId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(compareID(txtVIPassBox.getPassword())) {
					panelSearchCatalog.setVisible(true);
					panelValidateUser.setVisible(false);
				}
				else {
					lblVIPleaseId.setBackground(new Color(255, 0, 0));
				}
			}
		});
		panelVIPassHandlers.add(btnVIEnterId);
		
		// Returns the complete frame
		return panelValidateUser;
	}

	private JPanel createPanelSearchCatalog() {
		// Creates, constraints, and adds the base panel for SearchCatalog
		contentPane.add(panelSearchCatalog, "name_432252656295800");
		panelSearchCatalog.setLayout(new BorderLayout(0, 0));
		
		// Creates, constraints, and adds the Scroll Pane to the base panel's BorderLayout
		panelSearchCatalog.add(scrollPane, BorderLayout.CENTER);
		listModel = new DefaultListModel<>();
		resultsList = new JList<>(listModel);
		scrollPane.setViewportView(resultsList);
		// Populates the Scroll Pane with results
		for (Book i : workingLibrary.getBooks()) {
			listModel.addElement(i.getData());
		}
		
		resultsList.addListSelectionListener(event -> {
			if (resultsList.getSelectedIndex() != -1) {
		        selectedBookId = resultsList.getSelectedIndex();
		        txtScrollPane.setText(refreshBookInfoBI());
		        panelBookInfo.setVisible(true);
		        panelSearchCatalog.setVisible(false);
		    }
		});
		
		// Creates, constraints, and adds a subpanel to contain the search text field, enter button, and back button.
		panelSearchCatalog.add(panelSCActions, BorderLayout.NORTH);
		panelSCActions.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// Creates, constraints, and adds the text field for searching to the subpanel panelSearchCatalog
		txtSearch.setHorizontalAlignment(SwingConstants.LEFT);
		txtSearch.setBounds(new Rectangle(2, 2, 2, 2));
		txtSearch.setFont(new Font("Arial", Font.PLAIN, 14));
		txtSearch.setColumns(26);
		txtSearch.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		txtSearch.setBorder(new EmptyBorder(10, 20, 10, 160));
		panelSCActions.add(txtSearch);
		
		// Creates, constraints, and adds the initiate query button
		btnSCQuery.setHorizontalAlignment(SwingConstants.RIGHT);
		btnSCQuery.setFont(new Font("Arial", Font.PLAIN, 14));
		panelSCActions.add(btnSCQuery);
		btnSCBackVI.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelValidateUser.setVisible(true);
				panelSearchCatalog.setVisible(false);
				txtVIPassBox.setText("");
				lblVIPleaseId.setBackground(new Color(240, 240, 240));
			}
		});
		
		// Creates, constraints, and adds the return to VI frame button
		btnSCBackVI.setHorizontalAlignment(SwingConstants.RIGHT);
		btnSCBackVI.setFont(new Font("Arial", Font.PLAIN, 14));
		panelSCActions.add(btnSCBackVI);
		
		return panelSearchCatalog;
	}

	public boolean compareID(char[] validationArray) {
		
		String validationString = new String(validationArray);
		Integer validationInteger = Integer.parseInt(validationString);
		
		User tempUser = new User(validationInteger, "name");
		
		if (tempUser.validateId()) {
			return true;
		};
		return false;
		// TODO: Implement error handling
	}

	
	public String refreshBookInfoBI() {
		ArrayList<Book> tempBookArray = new ArrayList<>(workingLibrary.getBooks());
		if (selectedBookId >= 0 && selectedBookId < tempBookArray.size() && tempBookArray.get(selectedBookId) != null) {
			Book tempBook = tempBookArray.get(selectedBookId);
			return String.format("%s - written by %s, published %s. \n\nThe quick brown fox jumps over the lazy dog - Sphinx of black quartz, judge my vow.", tempBook.getTitle(), tempBook.getAuthor(), tempBook.getPublishDate());
		}
		else return "Index Out of Bounds or other error has occurred, we apologize for the inconvenience.";
	}
}

/** Archives
 * btnEnterID.addMouseListener(new MouseAdapter() {
			// The compareID() method uses the current data in the passwordField to validate
			@Override
			public void mouseClicked(MouseEvent e) {
				if(compareID()) {
					LibraryFrameOne kioskFrameOne = new LibraryFrameOne();
					LibraryFrameTwo kioskFrameTwo = new LibraryFrameTwo();
					kioskFrameTwo.setVisible(true);
					kioskFrameOne.setVisible(false)
					
				}
			}
		});
		**/
	
