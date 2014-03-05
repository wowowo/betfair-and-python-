package betfair;

import java.awt.Desktop;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * Visual representation of the information from the other classes
 * @author User
 *
 */
public class Visuals extends javax.swing.JFrame {

	
	public Visuals() {
		initComponents();
	}

	/**
	 * Generic layout code from Oracle examples
	 */
	private void initComponents() {

		// initialize the visual objects
		i = events.length - 1;
		
		odds = new JLabel();
		home = new JLabel();
		away = new JLabel();

		next = new JButton();
		previous = new JButton();
		stats = new JButton();
		betfair = new JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Featured Games");

		home.setText(events[i].getHome());
		odds.setText(events[i].getOdds());
		away.setText(events[i].getAway());

		next.setText("Next");
		previous.setText("Previous");
		stats.setText("Stats");
		betfair.setText("Betfair");

		// add action listeners
		
		next.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				i = (i + 1) % events.length;
				
				home.setText(events[i].getHome());
				odds.setText(events[i].getOdds());
				away.setText(events[i].getAway());
				pack();
			}
		});

		previous.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				
				i--;
				
				if(i < 0)
					i= events.length - 1;
				
				home.setText(events[i].getHome());
				odds.setText(events[i].getOdds());
				away.setText(events[i].getAway());
				pack();

			}
		});

		stats.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
			    try {
			    	String a = Statistics.getStats(events[i]);
			        Desktop.getDesktop().browse(new URL(a).toURI());
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			}
		});

		betfair.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				  try {
				        Desktop.getDesktop().browse(new URL("https://www." + events[i].url).toURI());
				    } catch (Exception e) {
				        e.printStackTrace();
				    }
			}
		});

		// gereric layout options
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);

		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		previous,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		home)
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(away)
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(next)
																)
																		
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		stats)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		odds)
																.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(betfair)))
								.addContainerGap(27, Short.MAX_VALUE)));


		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														next,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(previous)
												.addComponent(
														home)
												.addComponent(away)
												.addComponent(next))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(stats)
												.addComponent(odds)
												.addComponent(betfair))
								.addContainerGap(21, Short.MAX_VALUE)));
		pack();
	}

	/**
	 * test 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		
		String url = "https://www.betfair.com/sport/football/";
		
		events = Betfair.getEvents(url);
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Visuals().setVisible(true);
			}
		});
	}

	private javax.swing.JLabel odds;
	private javax.swing.JLabel home;
	private javax.swing.JLabel away;

	private javax.swing.JButton next;
	private javax.swing.JButton previous;
	private javax.swing.JButton stats;
	private JButton betfair;
	private static Event[] events;
	private int i;

}
