import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.net.URL;
import java.net.MalformedURLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;



public class Address extends JFrame {

	private JPanel contentPane;
	private JTextField txtURL;
	private URL URL;
	private JLabel lblResult;
	private JPanel panel_1;
	private JTextField txtShort;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Address frame = new Address();
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
	public Address() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		txtURL = new JTextField();
		txtURL.setText("http://");
		txtURL.setToolTipText("Insert the addres to short");
		panel.add(txtURL);
		txtURL.setColumns(25);
		
		JButton btnAccorcia = new JButton("Short");
		btnAccorcia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblResult.setText("Loading...");
				try
				{
					URL = new URL("http://tiny-url.info/api/v1/random?url=" + txtURL.getText());
					int count;
					BufferedInputStream inStream = new BufferedInputStream(URL.openStream());
					InputStreamReader reader = new InputStreamReader(inStream);
					lblResult.setText("Short URL: ");
					while( (count = reader.read()) != -1)
					{
						txtShort.setText(txtShort.getText() + (char)count);
					}
					txtShort.setVisible(true);
				}catch(Exception e)
				{
					System.out.println("ERRORE");
				}
			}
		});
		panel.add(btnAccorcia);
		
		panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		lblResult = new JLabel("");
		panel_1.add(lblResult);
		
		txtShort = new JTextField();
		panel_1.add(txtShort);
		txtShort.setColumns(10);
		txtShort.setVisible(false);
	}

}
