package libraryJFrame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JTextArea;

/**
* @author - Morgan Fidler
*/
//TODO: missing logical implementation

public class LibraryFrameThree extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelButtons_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibraryFrameThree frame = new LibraryFrameThree();
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
	public LibraryFrameThree() {
		createBaseFrameS3();
		
		JPanel panelInfo = createTextFrame();
		
		createTextInfoPane(panelInfo);
		
		JPanel panelButtons = createPanelButton();
		
		createCheckoutButton(panelButtons);
		
		createReturnButton(panelButtons);
		
		createBackS2Button(panelButtons);
	}

	private void createBackS2Button(JPanel panelButtons) {
		JButton btnBackS2 = new JButton("<-");
		btnBackS2.setPreferredSize(new Dimension(50, 35));
		panelButtons.add(btnBackS2);
	}

	private void createReturnButton(JPanel panelButtons) {
		JButton btnReturn = new JButton("Return");
		panelButtons.add(btnReturn);
		
	}

	private void createCheckoutButton(JPanel panelButtons) {
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.setPreferredSize(new Dimension(50, 35));
		btnCheckout.setHorizontalAlignment(SwingConstants.CENTER);
		btnCheckout.setFont(new Font("Arial", Font.PLAIN, 8));
		panelButtons.add(btnCheckout);
	}

	private JPanel createPanelButton() {
		panelButtons_1 = new JPanel();
		panelButtons_1.setLayout(new GridLayout(0, 3, 0, 0));
		contentPane.add(panelButtons_1, BorderLayout.SOUTH);
		return panelButtons_1;
	}

	private void createTextInfoPane(JPanel panelInfo) {
		JTextArea textInfoPane = new JTextArea();
		textInfoPane.setFont(new Font("Arial", Font.PLAIN, 16));
		textInfoPane.setWrapStyleWord(true);
		textInfoPane.setText("Title, Author, Year, Description. The quick brown fox jumps over the lazy dog - Sphinx of black quartz, judge my vow.");
		textInfoPane.setEditable(false);
		textInfoPane.setLineWrap(true);
		panelInfo.add(textInfoPane, BorderLayout.CENTER);
	}

	private JPanel createTextFrame() {
		JPanel panelInfo = new JPanel();
		contentPane.add(panelInfo);
		panelInfo.setLayout(new BorderLayout(0, 0));
		return panelInfo;
	}

	private void createBaseFrameS3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
	}

}
