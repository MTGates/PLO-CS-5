package libraryJFrame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.DefaultListModel;

/**
 * @author - Morgan Fidler
 */
// TODO: missing logical implementation

public class LibraryFrameTwo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelSearch;
	private JTextField textFieldSearch;
	private JButton btnBackS1;
	private JButton btnSearch;
	private DefaultListModel<String> listModel;
	private JScrollPane scrollPane;
	private JList<String> resultsList;

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
	public LibraryFrameTwo() {
		baseFrameS2();
		searchResultsList();
		// Search panel with text field, return, and query buttons
		panelSearch();
		searchBar();
		btnBackS1();
		btnSearch();

	}

	// Creates the search button in the searchPanel
	private void btnSearch() {
		btnSearch = new JButton("Q");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		panelSearch.add(btnSearch, BorderLayout.CENTER);
	}

	// Creates the end session button to return to S1 in the searchPanel
	private void btnBackS1() {
		btnBackS1 = new JButton("<-");
		panelSearch.add(btnBackS1, BorderLayout.EAST);
	}

	// creates the search bar field in the searchPanel
	private void searchBar() {
		textFieldSearch = new JTextField();
		textFieldSearch.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldSearch.setBounds(new Rectangle(2, 2, 2, 2));
		textFieldSearch.setFont(new Font("Arial", Font.PLAIN, 14));
		textFieldSearch.setColumns(10);
		textFieldSearch.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panelSearch.add(textFieldSearch, BorderLayout.WEST);
		textFieldSearch.setBorder(new EmptyBorder(10, 20, 10, 210));
	}

	// Creates the panel to hold the search panel items
	private void panelSearch() {
		panelSearch = new JPanel();
		contentPane.add(panelSearch, BorderLayout.NORTH);
		panelSearch.setLayout(new BorderLayout(0, 0));
		
	}

	// Creates the list to contain search result entries, which are all clickable and placed inside a jlist nested inside a scrollpane
	private void searchResultsList() {
	    listModel = new DefaultListModel<>();
	    resultsList = new JList<>(listModel);
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setViewportView(resultsList);
	    contentPane.add(scrollPane, BorderLayout.CENTER);
	    listModel.addElement("The Hobbit");
	    listModel.addElement("1984");
	    listModel.addElement("Dune");
	}

	// Creates the base layout for everything 
	private void baseFrameS2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
	}

}
