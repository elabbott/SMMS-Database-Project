package SMMS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PanelPopulator {

	String title;
	int year;
	int score;
	URL pic1;
	URL pic2;
	JButton button = new JButton("Tags");
	ArrayList<String> tags;

	public PanelPopulator(String title, int year, int score, String pic1,
			String pic2) {
		this.title = title;
		this.year = year;
		this.score = score;

		try {
			this.pic1 = new URL(pic1);
			this.pic2 = new URL(pic2);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			try {
				this.pic1 = new URL(
						"http://browshot.com/static/images/not-found.png");
			} catch (MalformedURLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				this.pic2 = new URL(
						"http://browshot.com/static/images/not-found.png");
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public PanelPopulator(String title, int year, int score, String pic1,
			String pic2, ArrayList<String> tags) {
		this.title = title;
		this.year = year;
		this.score = score;

		try {
			this.pic1 = new URL(pic1);
			this.pic2 = new URL(pic2);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			try {
				this.pic1 = new URL(
						"http://browshot.com/static/images/not-found.png");
			} catch (MalformedURLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				this.pic2 = new URL(
						"http://browshot.com/static/images/not-found.png");
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		this.tags = tags;
	}

	public PanelPopulator(String name) {
		this.title = name;
	}

	public JPanel createPanelMovie1() {
		JPanel pane = new JPanel();
		BufferedImage image_from_imdb = null;
		BufferedImage image_from_rt = null;
		try {
			image_from_imdb = ImageIO.read(pic1);
		} catch (IOException e) {
			try {
				image_from_imdb = ImageIO.read(new File("src/error.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		try {
			image_from_rt = ImageIO.read(pic2);
		} catch (IOException e) {
			try {
				image_from_rt = ImageIO.read(new File("src/error.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		Image image_imdb = image_from_imdb.getScaledInstance(200, 200, 0);
		Image image_rt = image_from_rt.getScaledInstance(200, 200, 0);

		pane.setLayout(new GridLayout(0, 2));

		JLabel l = new JLabel(new ImageIcon(image_imdb));
		JLabel l2 = new JLabel(new ImageIcon(image_rt));

		JPanel p1 = new JPanel(new GridLayout(0, 2));

		p1.add(l);
		p1.add(l2);
		p1.setBorder(BorderFactory.createLineBorder(Color.black));

		pane.add(p1);

		JProgressBar bar = new JProgressBar(0, 100);
		bar.setValue(score);
		bar.setStringPainted(true);

		JTextArea l3 = new JTextArea("Title: " + title + "\nYear Published: "
				+ year);
		l3.setOpaque(false);
		l3.setFont(new Font("Serif", Font.PLAIN, 24));
		l3.setEditable(false);
		l3.setWrapStyleWord(true);

		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(bar, BorderLayout.SOUTH);

		JPanel p2 = new JPanel(new GridLayout(4, 0));
		p2.add(l3);
		p2.add(p);
		p2.setBorder(BorderFactory.createLineBorder(Color.black));

		pane.add(p2);

		return pane;
	}

	public JPanel createPanelMovie2() {
		final JPanel pane = new JPanel();
		BufferedImage image_from_imdb = null;
		BufferedImage image_from_rt = null;
		try {
			image_from_imdb = ImageIO.read(pic1);
		} catch (IOException e) {
			try {
				image_from_rt = ImageIO.read(new File("src/error.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		try {
			image_from_rt = ImageIO.read(pic2);
		} catch (IOException e) {
			try {
				image_from_rt = ImageIO.read(new File("src/error.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		Image image_imdb = image_from_imdb.getScaledInstance(200, 200, 0);
		Image image_rt = image_from_rt.getScaledInstance(200, 200, 0);

		pane.setLayout(new GridLayout(0, 2));

		JLabel l = new JLabel(new ImageIcon(image_imdb));
		JLabel l2 = new JLabel(new ImageIcon(image_rt));

		JPanel p1 = new JPanel(new GridLayout(0, 2));

		p1.add(l);
		p1.add(l2);
		p1.setBorder(BorderFactory.createLineBorder(Color.black));

		pane.add(p1);

		JProgressBar bar = new JProgressBar(0, 100);
		bar.setValue(score);
		bar.setStringPainted(true);

		JTextArea l3 = new JTextArea("Title: " + title + "\nYear Published: "
				+ year);
		l3.setOpaque(false);
		l3.setFont(new Font("Serif", Font.PLAIN, 24));
		l3.setEditable(false);
		l3.setWrapStyleWord(true);

		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(bar, BorderLayout.SOUTH);

		JPanel p2 = new JPanel(new GridLayout(4, 0));
		p2.add(l3);
		p2.add(button);
		p2.add(p);
		JLabel jl = new JLabel();
		String s = "";
		for (int x = 0; x<tags.size(); x++){
			s+=tags.get(x);
		}
		jl.setText(s);
		
		p2.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		pane.add(p2);
		
		//Button actionListener
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				//JOptionPane.showMessageDialog(pane, tags.toString());
				String message_for_box = PanelPopulator.this.toString(tags);
				JOptionPane.showMessageDialog(pane, "<html><body><p style='width: 200px;'>"+message_for_box+"</p></body></html>");
			}
		});
		
		
		
		return pane;
	}

	public JPanel createPanelDirector() {
		JPanel pane = new JPanel();
		JTextArea l3 = new JTextArea(title);
		l3.setFont(new Font("Serif", Font.PLAIN, 24));
		l3.setEditable(false);
		l3.setWrapStyleWord(true);
		l3.setOpaque(false);
		pane.add(l3);
		return pane;
	}

	public JPanel createPanelActor() {
		JPanel pane = new JPanel();
		JTextArea l3 = new JTextArea(title);
		l3.setFont(new Font("Serif", Font.PLAIN, 24));
		l3.setEditable(false);
		l3.setWrapStyleWord(true);
		l3.setOpaque(false);
		pane.add(l3);
		return pane;
	}
	private String toString(ArrayList<String> str){
		String result ="";
		for(int i =0; i < str.size(); i++){
			result += str.get(i)+ ",\t";
		}
		return result;
	}
}