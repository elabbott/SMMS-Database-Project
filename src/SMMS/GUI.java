package SMMS;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

public class GUI {
	private JFrame frame;
	private JPanel mainPanel;
	private JButton search;
	private JTextField search_field;
	private BufferedImage imageMonkey;
	private JTabbedPane tabs;
	private JComboBox options;
	private Querries querries;
	protected int counter = 1;

	public GUI() {
		makeComponents();
		arrangeComponents();
		addListeners();
	}

	public void setVisible(boolean v) {
		frame.setVisible(v);
	}

	private void makeComponents() {
		frame = new JFrame("Space Monkey Movie Service");
		try {
			imageMonkey = ImageIO.read(new File("src/spacemonkey.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mainPanel = new JPanel();
		querries = new Querries();
		search = new JButton("GO!");
		search_field = new JTextField();
		tabs = new JTabbedPane();
		search_field.setText("");
		options = new JComboBox();
		options.addItem("See the Top Popular Movies");
		options.addItem("Search by Movie Title");
		options.addItem("Search by Movie Genre");
		options.addItem("Search by Director's Name");
		options.addItem("Search by Actor's Name");
		options.addItem("Search by Tag");
		options.addItem("See the Top Popular Directors");
		options.addItem("See the Top Popular Actors");
		options.addItem("Search by Country. First 100");
		options.addItem("Search by Filming Location. First 100");

		// TODO Auto-generated method stub

	}

	private JPanel layoutPanel() {
		mainPanel.setBackground(Color.BLACK);
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.BLACK);
		bottomPanel.setLayout(new GridLayout(6, 0));
		JLabel label1 = new JLabel("Select your filter criteria");
		label1.setForeground(Color.white);
		bottomPanel.add(label1);
		bottomPanel.add(options);
		JLabel label2 = new JLabel("Fill in your search query");
		label2.setForeground(Color.white);
		bottomPanel.add(label2);

		bottomPanel.add(search_field);
		JLabel label3 = new JLabel("Let the Space Monkey take care the rest!");
		label3.setForeground(Color.white);
		bottomPanel.add(label3);

		bottomPanel.add(search);
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.BLACK);
		ImageIcon icon = new ImageIcon(imageMonkey);
		JLabel label = new JLabel(icon);
		topPanel.add(label);

		c.gridx = 1;
		c.gridy = 0;
		mainPanel.add(topPanel, c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 0;
		c.weightx = 0;
		c.anchor = GridBagConstraints.CENTER;
		mainPanel.add(bottomPanel, c);

		return mainPanel;

	}

	private void arrangeComponents() {
		tabs.add("Start Page", layoutPanel());
		Container container = frame.getContentPane();
		container.add(tabs);
		container.validate();
		frame.setLocationByPlatform(false);
		frame.setSize(new Dimension(700, 750));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private class Search implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (options.getSelectedItem() == "See the Top Popular Movies") {
				try {
					ArrayList<Movie> results = new ArrayList<Movie>();
					results = querries.query_1();
					if (results.size() > 0) {
						JPanel jp = new JPanel();
						if (results.size() <= 5) {
							jp.setLayout(new GridLayout(results.size() + 2, 0));
						} else
							jp.setLayout(new GridLayout(results.size(), 0));
						for (int i = 0; i < results.size(); i++) {
							Movie movie = results.get(i);
							PanelPopulator panel = new PanelPopulator(
									movie.getTitle(), movie.getYear(),
									movie.getRTAudienceScore(),
									movie.getIMDBPictureURL(),
									movie.getRTPictureURL());
							JPanel j = panel.createPanelMovie1();
							jp.add(panel.createPanelMovie1());
						}
						JScrollPane jsp = new JScrollPane(jp);
						jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

						tabs.addTab("" + options.getSelectedItem() + ": "
								+ search_field.getText(), jsp);
						tabs.setTabComponentAt(counter, new ButtonTabComponent(
								tabs));
						tabs.setSelectedIndex(counter);
						counter++;
						tabs.setFocusable(true);
					} else
						JOptionPane
								.showMessageDialog(frame,
										"Sorry! No results have been found based on your search criteria");

				} catch (ClassNotFoundException | SQLException e) {
					JOptionPane.showMessageDialog(frame, "SQL Error!");
					e.printStackTrace();
				}
			} else if (options.getSelectedItem() == "Search by Movie Title") {
				try {
					if (search_field.getText().length() > 0) {
						ArrayList<Movie> results = new ArrayList<Movie>();
						results = querries.query_2(search_field.getText());
						if (results.size() > 0) {
							JPanel jp = new JPanel();
							if (results.size() <= 5) {
								jp.setLayout(new GridLayout(results.size() + 2,
										0));
							} else
								jp.setLayout(new GridLayout(results.size(), 0));

							for (int i = 0; i < results.size(); i++) {
								Movie movie = results.get(i);
								PanelPopulator panel = new PanelPopulator(
										movie.getTitle(), movie.getYear(),
										movie.getRTAudienceScore(),
										movie.getIMDBPictureURL(),
										movie.getRTPictureURL(),
										movie.getTags());
								jp.add(panel.createPanelMovie2());
							}
							JScrollPane jsp = new JScrollPane(jp);
							jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

							tabs.addTab("" + options.getSelectedItem() + ": "
									+ search_field.getText(), jsp);
							tabs.setTabComponentAt(counter,
									new ButtonTabComponent(tabs));
							tabs.setSelectedIndex(counter);
							counter++;
							tabs.setFocusable(true);
						} else
							JOptionPane
									.showMessageDialog(frame,
											"Sorry! No results have been found based on your search criteria");

					} else
						JOptionPane
								.showMessageDialog(frame,
										"Error. Please type something in ine search field!");

				} catch (ClassNotFoundException | SQLException e) {
					JOptionPane.showMessageDialog(frame, "SQL Error");
					e.printStackTrace();
				}

			} else if (options.getSelectedItem() == "Search by Movie Genre") {
				try {
					if (search_field.getText().length() > 0) {
						ArrayList<Movie> results = new ArrayList<Movie>();
						results = querries.query_3(search_field.getText());
						if (results.size() > 0) {
							JPanel jp = new JPanel();
							if (results.size() <= 5) {
								jp.setLayout(new GridLayout(results.size() + 2,
										0));
							} else
								jp.setLayout(new GridLayout(results.size(), 0));
							for (int i = 0; i < results.size(); i++) {
								Movie movie = results.get(i);
								PanelPopulator panel = new PanelPopulator(
										movie.getTitle(), movie.getYear(),
										movie.getRTAudienceScore(),
										movie.getIMDBPictureURL(),
										movie.getRTPictureURL());
								jp.add(panel.createPanelMovie1());
							}
							JScrollPane jsp = new JScrollPane(jp);
							jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

							tabs.addTab("" + options.getSelectedItem() + ": "
									+ search_field.getText(), jsp);
							tabs.setTabComponentAt(counter,
									new ButtonTabComponent(tabs));
							tabs.setSelectedIndex(counter);
							counter++;
							tabs.setFocusable(true);
						} else
							JOptionPane
									.showMessageDialog(frame,
											"Sorry! No results have been found based on your search criteria");

					} else
						JOptionPane
								.showMessageDialog(frame,
										"Error. Please type something in ine search field!");

				} catch (ClassNotFoundException | SQLException e) {
					JOptionPane.showMessageDialog(frame, "SQL Error");
					e.printStackTrace();
				}

			} else if (options.getSelectedItem() == "Search by Director's Name") {
				try {
					if (search_field.getText().length() > 0) {
						ArrayList<Movie> results = new ArrayList<Movie>();
						results = querries.query_4(search_field.getText());
						if (results.size() > 0) {
							JPanel jp = new JPanel();
							if (results.size() <= 5) {
								jp.setLayout(new GridLayout(results.size() +2,
										0));
							} else
								jp.setLayout(new GridLayout(results.size(), 0));
							for (int i = 0; i < results.size(); i++) {
								Movie movie = results.get(i);
								PanelPopulator panel = new PanelPopulator(
										movie.getTitle(), movie.getYear(),
										movie.getRTAudienceScore(),
										movie.getIMDBPictureURL(),
										movie.getRTPictureURL());
								jp.add(panel.createPanelMovie1());
							}
							JScrollPane jsp = new JScrollPane(jp);
							jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

							tabs.addTab("" + options.getSelectedItem() + ": "
									+ search_field.getText(), jsp);
							tabs.setTabComponentAt(counter,
									new ButtonTabComponent(tabs));
							tabs.setSelectedIndex(counter);
							counter++;
							tabs.setFocusable(true);
						} else
							JOptionPane
									.showMessageDialog(frame,
											"Sorry! No results have been found based on your search criteria");

					} else
						JOptionPane
								.showMessageDialog(frame,
										"Error. Please type something in ine search field!");

				} catch (ClassNotFoundException | SQLException e) {
					JOptionPane.showMessageDialog(frame, " SQL Error");
					e.printStackTrace();
				}

			} else if (options.getSelectedItem() == "Search by Actor's Name") {
				try {
					if (search_field.getText().length() > 0) {
						ArrayList<Movie> results = new ArrayList<Movie>();
						results = querries.query_5(search_field.getText());
						if (results.size() > 0) {
							JPanel jp = new JPanel();
							if (results.size() <= 5) {
								jp.setLayout(new GridLayout(results.size() + 2,
										0));
							} else
								jp.setLayout(new GridLayout(results.size(), 0));
							for (int i = 0; i < results.size(); i++) {
								Movie movie = results.get(i);
								PanelPopulator panel = new PanelPopulator(
										movie.getTitle(), movie.getYear(),
										movie.getRTAudienceScore(),
										movie.getIMDBPictureURL(),
										movie.getRTPictureURL());
								jp.add(panel.createPanelMovie1());
							}
							JScrollPane jsp = new JScrollPane(jp);
							jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

							tabs.addTab("" + options.getSelectedItem() + ": "
									+ search_field.getText(), jsp);
							tabs.setTabComponentAt(counter,
									new ButtonTabComponent(tabs));
							tabs.setSelectedIndex(counter);
							counter++;
							tabs.setFocusable(true);
						} else
							JOptionPane
									.showMessageDialog(frame,
											"Sorry! No results have been found based on your search criteria");

					} else
						JOptionPane
								.showMessageDialog(frame,
										"Error. Please type something in ine search field!");

				} catch (ClassNotFoundException | SQLException e) {
					JOptionPane.showMessageDialog(frame, " SQL Error");
					e.printStackTrace();
				}

			} else if (options.getSelectedItem() == "Search by Tag") {
				try {
					if (search_field.getText().length() > 0) {
						ArrayList<Movie> results = new ArrayList<Movie>();
						results = querries.query_6(search_field.getText());
						if (results.size() > 0) {
							JPanel jp = new JPanel();
							if (results.size() <= 5) {
								jp.setLayout(new GridLayout(results.size() + 2,
										0));
							} else
								jp.setLayout(new GridLayout(results.size(), 0));
							for (int i = 0; i < results.size(); i++) {
								Movie movie = results.get(i);
								PanelPopulator panel = new PanelPopulator(
										movie.getTitle(), movie.getYear(),
										movie.getRTAudienceScore(),
										movie.getIMDBPictureURL(),
										movie.getRTPictureURL());
								jp.add(panel.createPanelMovie1());
							}
							JScrollPane jsp = new JScrollPane(jp);
							jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

							tabs.addTab("" + options.getSelectedItem() + ": "
									+ search_field.getText(), jsp);
							tabs.setTabComponentAt(counter,
									new ButtonTabComponent(tabs));
							tabs.setSelectedIndex(counter);
							counter++;
							tabs.setFocusable(true);
						} else
							JOptionPane
									.showMessageDialog(frame,
											"Sorry! No results have been found based on your search criteria");

					} else
						JOptionPane
								.showMessageDialog(frame,
										"Error. Please type something in ine search field!");

				} catch (ClassNotFoundException | SQLException e) {
					JOptionPane.showMessageDialog(frame, " SQL Error");
					e.printStackTrace();
				}

			} else if (options.getSelectedItem() == "See the Top Popular Directors") {
				try {
					ArrayList<Movie> results = new ArrayList<Movie>();
					results = querries.query_7();
					if (results.size() > 0) {
						JPanel jp = new JPanel();
						if (results.size() <= 5) {
							jp.setLayout(new GridLayout(results.size() + 2, 0));
						} else
							jp.setLayout(new GridLayout(results.size(), 0));
						for (int i = 0; i < results.size(); i++) {
							Movie movie = results.get(i);
							PanelPopulator panel = new PanelPopulator(
									movie.directorName);
							jp.add(panel.createPanelDirector());
						}
						JScrollPane jsp = new JScrollPane(jp);
						jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

						tabs.addTab("" + options.getSelectedItem() + ": "
								+ search_field.getText(), jsp);
						tabs.setTabComponentAt(counter, new ButtonTabComponent(
								tabs));
						tabs.setSelectedIndex(counter);
						counter++;
						tabs.setFocusable(true);
					} else
						JOptionPane
								.showMessageDialog(frame,
										"Sorry! No results have been found based on your search criteria");

				}

				catch (ClassNotFoundException | SQLException e) {
					JOptionPane.showMessageDialog(frame, " SQL Error");
					e.printStackTrace();
				}

			} else if (options.getSelectedItem() == "See the Top Popular Actors") {
				try {
					ArrayList<Movie> results = new ArrayList<Movie>();
					results = querries.query_8();
					if (results.size() > 0) {
						JPanel jp = new JPanel();
						if (results.size() <= 5) {
							jp.setLayout(new GridLayout(results.size() + 2, 0));
						} else
							jp.setLayout(new GridLayout(results.size(), 0));
						for (int i = 0; i < results.size(); i++) {
							Movie movie = results.get(i);
							PanelPopulator panel = new PanelPopulator(
									movie.actorName);
							jp.add(panel.createPanelActor());
						}
						JScrollPane jsp = new JScrollPane(jp);
						jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

						tabs.addTab("" + options.getSelectedItem() + ": "
								+ search_field.getText(), jsp);
						tabs.setTabComponentAt(counter, new ButtonTabComponent(
								tabs));
						tabs.setSelectedIndex(counter);
						counter++;
						tabs.setFocusable(true);
					} else
						JOptionPane
								.showMessageDialog(frame,
										"Sorry! No results have been found based on your search criteria");

				}

				catch (ClassNotFoundException | SQLException e) {
					JOptionPane.showMessageDialog(frame, " SQL Error");
					e.printStackTrace();
				}

			} else if (options.getSelectedItem() == "Search by Country. First 100") {
				try {
					if (search_field.getText().length() > 0) {
						ArrayList<Movie> results = new ArrayList<Movie>();
						results = querries.query_9(search_field.getText());
						if (results.size() > 0) {
							JPanel jp = new JPanel();
							if (results.size() <= 5) {
								jp.setLayout(new GridLayout(results.size() + 2,
										0));
							} else
								jp.setLayout(new GridLayout(results.size(), 0));
							for (int i = 0; i < results.size(); i++) {
								Movie movie = results.get(i);
								PanelPopulator panel = new PanelPopulator(
										movie.getTitle(), movie.getYear(),
										movie.getRTAudienceScore(),
										movie.getIMDBPictureURL(),
										movie.getRTPictureURL());
								jp.add(panel.createPanelMovie1());
							}
							JScrollPane jsp = new JScrollPane(jp);
							jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

							tabs.addTab("" + options.getSelectedItem() + ": "
									+ search_field.getText(), jsp);
							tabs.setTabComponentAt(counter,
									new ButtonTabComponent(tabs));
							tabs.setSelectedIndex(counter);
							counter++;
							tabs.setFocusable(true);
						} else
							JOptionPane
									.showMessageDialog(frame,
											"Sorry! No results have been found based on your search criteria");

					} else
						JOptionPane
								.showMessageDialog(frame,
										"Error. Please type something in ine search field!");

				} catch (ClassNotFoundException | SQLException e) {
					JOptionPane.showMessageDialog(frame, " SQL Error");
					e.printStackTrace();
				}

			} else if (options.getSelectedItem() == "Search by Filming Location. First 100") {
				try {
					if (search_field.getText().length() > 0) {
						ArrayList<Movie> results = new ArrayList<Movie>();
						results = querries.query_10(search_field.getText());
						if (results.size() > 0) {
							JPanel jp = new JPanel();
							if (results.size() <= 5) {
								jp.setLayout(new GridLayout(results.size() + 2,
										0));
							} else
								jp.setLayout(new GridLayout(results.size(), 0));
							for (int i = 0; i < results.size(); i++) {
								Movie movie = results.get(i);
								PanelPopulator panel = new PanelPopulator(
										movie.getTitle(), movie.getYear(),
										movie.getRTAudienceScore(),
										movie.getIMDBPictureURL(),
										movie.getRTPictureURL());
								jp.add(panel.createPanelMovie1());
							}
							JScrollPane jsp = new JScrollPane(jp);
							jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

							tabs.addTab("" + options.getSelectedItem() + ": "
									+ search_field.getText(), jsp);
							tabs.setTabComponentAt(counter,
									new ButtonTabComponent(tabs));
							tabs.setSelectedIndex(counter);
							counter++;
							tabs.setFocusable(true);
						} else
							JOptionPane
									.showMessageDialog(frame,
											"Sorry! No results have been found based on your search criteria");

					} else
						JOptionPane
								.showMessageDialog(frame,
										"Error. Please type something in ine search field!");

				} catch (ClassNotFoundException | SQLException e) {
					JOptionPane.showMessageDialog(frame, " SQL Error");
					e.printStackTrace();
				}

			}

			// JPanel jp1 = new JPanel();
			// jp1.setLayout(new GridLayout(400,0));

			// for (int i = 0; i < 400 ; i++){
			// jp1.add(new JButton("HUr "+ i));
			// }
			// JScrollPane jsp = new JScrollPane(jp1);
			// jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

			// tabs.addTab(""+options.getSelectedItem()+": "+search_field.getText(),
			// jsp);
			// tabs.setFocusable(true);

		}

	}

	private void addListeners() {
		search.addActionListener(new Search());
	}

	private class ButtonTabComponent extends JPanel {
		private final JTabbedPane pane;

		public ButtonTabComponent(final JTabbedPane pane) {
			// unset default FlowLayout' gaps
			super(new FlowLayout(FlowLayout.LEFT, 0, 0));
			if (pane == null) {
				throw new NullPointerException("TabbedPane is null");
			}
			this.pane = pane;
			setOpaque(false);

			// make JLabel read titles from JTabbedPane
			JLabel label = new JLabel() {
				public String getText() {
					int i = pane.indexOfTabComponent(ButtonTabComponent.this);
					if (i != -1) {
						return pane.getTitleAt(i);
					}
					return null;
				}
			};

			add(label);
			// add more space between the label and the button
			label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
			// tab button
			JButton button = new TabButton();
			add(button);
			// add more space to the top of the component
			setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
		}

		private class TabButton extends JButton implements ActionListener {
			public TabButton() {
				int size = 17;
				setPreferredSize(new Dimension(size, size));
				setToolTipText("close this tab");
				// Make the button looks the same for all Laf's
				setUI(new BasicButtonUI());
				// Make it transparent
				setContentAreaFilled(false);
				// No need to be focusable
				setFocusable(false);
				setBorder(BorderFactory.createEtchedBorder());
				setBorderPainted(false);
				// Making nice rollover effect
				// we use the same listener for all buttons
				addMouseListener(buttonMouseListener);
				setRolloverEnabled(true);
				// Close the proper tab by clicking the button
				addActionListener(this);
			}

			public void actionPerformed(ActionEvent e) {
				int i = pane.indexOfTabComponent(ButtonTabComponent.this);
				if (i != -1) {
					counter--;
					pane.remove(i);
				}
			}

			// we don't want to update UI for this button
			public void updateUI() {
			}

			// paint the cross
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g.create();
				// shift the image for pressed buttons
				if (getModel().isPressed()) {
					g2.translate(1, 1);
				}
				g2.setStroke(new BasicStroke(2));
				g2.setColor(Color.BLACK);
				if (getModel().isRollover()) {
					g2.setColor(Color.RED);
				}
				int delta = 6;
				g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight()
						- delta - 1);
				g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight()
						- delta - 1);
				g2.dispose();
			}
		}

		private final MouseListener buttonMouseListener = new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				Component component = e.getComponent();
				if (component instanceof AbstractButton) {
					AbstractButton button = (AbstractButton) component;
					button.setBorderPainted(true);
				}
			}

			public void mouseExited(MouseEvent e) {
				Component component = e.getComponent();
				if (component instanceof AbstractButton) {
					AbstractButton button = (AbstractButton) component;
					button.setBorderPainted(false);
				}
			}
		};
	}

}