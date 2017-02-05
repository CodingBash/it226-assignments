package edu.ilstu.swingproject.view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.ilstu.swingproject.model.Rectangle;

public class Frame {

	static List<Rectangle> rectangleList;
	static List<Rectangle> intersectionList;
	static List<Color> intersectionListColor;
	static List<Rectangle> unionList;
	static List<Rectangle> commonList;
	static boolean drawIntersections;
	static boolean drawUnions;
	static boolean drawCommon;

	public static void main(String[] args) {
		rectangleList = new ArrayList<Rectangle>();
		intersectionList = new ArrayList<Rectangle>();
		intersectionListColor = new ArrayList<Color>();
		unionList = new ArrayList<Rectangle>();
		commonList = new ArrayList<Rectangle>();

		drawIntersections = false;
		drawUnions = false;
		drawCommon = false;

		JFrame f = new JFrame("Rectangles");

		final JPanel drawingPanel = new DrawingPanel();
		final JPanel optionPanel = new JPanel();
		final JCheckBox checkBox = new JCheckBox("Draw Intersections");
		checkBox.setRolloverEnabled(false);
		checkBox.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				ButtonModel buttonModel = abstractButton.getModel();
				drawIntersections = buttonModel.isSelected();
				if (drawIntersections) {
					intersectionList = drawIntersections();
					intersectionListColor = drawIntersectionsColor(intersectionList.size());
					drawingPanel.repaint();
				} else {
					intersectionList.clear();
					drawingPanel.repaint();
				}
			}
		});
		final JCheckBox checkBox2 = new JCheckBox("Draw Union");
		checkBox2.setRolloverEnabled(false);
		checkBox2.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				ButtonModel buttonModel = abstractButton.getModel();
				drawUnions = buttonModel.isSelected();
				if (drawUnions) {
					unionList = drawUnions();
					drawingPanel.repaint();
				} else {
					unionList.clear();
					drawingPanel.repaint();

				}
			}
		});
		final JCheckBox checkBox3 = new JCheckBox("Draw Common Area");
		checkBox3.setRolloverEnabled(false);
		checkBox3.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				ButtonModel buttonModel = abstractButton.getModel();
				drawCommon = buttonModel.isSelected();
				if (drawCommon) {
					commonList = drawCommon();
					drawingPanel.repaint();
				} else {
					commonList.clear();
					drawingPanel.repaint();

				}
				System.out.println(commonList.size());
			}

		});
		JButton button = new JButton("Save Image");
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Container c = drawingPanel;
				BufferedImage image = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_RGB);
				c.paint(image.getGraphics());
				JFileChooser jFileChooser = new JFileChooser();
				int returnState = jFileChooser.showSaveDialog(optionPanel);
				if (returnState == JFileChooser.APPROVE_OPTION) {
					try {
						ImageIO.write(image, "PNG",
								new File(jFileChooser.getSelectedFile().getAbsolutePath() + ".png"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

			}

		});
		JButton button2 = new JButton("Clear Drawing");
		button2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				rectangleList.clear();
				intersectionList.clear();
				unionList.clear();
				commonList.clear();
				checkBox.setSelected(false);
				checkBox2.setSelected(false);
				checkBox3.setSelected(false);
				drawIntersections = false;
				drawUnions = false;
				drawCommon = false;

				drawingPanel.repaint();
			}

		});
		optionPanel.add(checkBox);
		optionPanel.add(checkBox2);
		optionPanel.add(checkBox3);
		optionPanel.add(button);
		optionPanel.add(button2);

		drawingPanel.setBackground(Color.WHITE);
		f.add(drawingPanel, BorderLayout.NORTH);
		f.add(optionPanel, BorderLayout.SOUTH);
		f.getContentPane().setSize(new Dimension(1080, 720));
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	protected static List<Color> drawIntersectionsColor(int size) {
		List<Color> tempColor = new ArrayList<Color>(size);
		final Random r = new Random();
		for (int i = 0; i < size; i++) {
			tempColor.add(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256), r.nextInt(256)));
		}
		return tempColor;
	}

	protected static List<Rectangle> drawUnions() {
		List<Rectangle> tempList = new ArrayList<Rectangle>();
		Rectangle unionRect = rectangleList.get(0);

		for (int i = 1; i < rectangleList.size(); i++) {
			unionRect = unionRect.union(rectangleList.get(i));
		}
		tempList.add(unionRect);
		return tempList;
	}

	protected static List<Rectangle> drawIntersections() {
		List<Rectangle> tempList = new ArrayList<Rectangle>();
		for (int i = 0; i < rectangleList.size(); i++) {
			for (int k = 0; k < rectangleList.size(); k++) {
				if (i != k) {
					tempList.add(rectangleList.get(i).intersect(rectangleList.get(k)));
				}
			}
		}
		return tempList;
	}

	protected static List<Rectangle> drawCommon() {
		List<Rectangle> tempList = new ArrayList<Rectangle>();
		Rectangle commonRect = rectangleList.get(0);

		for (int i = 1; i < rectangleList.size(); i++) {
			commonRect = commonRect.intersect(rectangleList.get(i));
		}
		tempList.add(commonRect);
		return tempList;
	}
}

@SuppressWarnings("serial")
class DrawingPanel extends JPanel {
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		float dash1[] = { 10.0f };
		BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
		BasicStroke solid = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f);
		g2.clearRect(0, 0, this.getWidth(), this.getHeight());
		g2.setColor(Color.WHITE);
		g2.setStroke(solid);
		g2.fillRect(0, 0, getWidth(), getHeight());
		for (Rectangle rect : Frame.rectangleList) {
			g2.setColor(Color.GREEN);
			g2.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
		}
		for (int i = 0; i < Frame.intersectionList.size(); i++) {
			g2.setColor(Frame.intersectionListColor.get(i));
			Rectangle rect = Frame.intersectionList.get(i);
			g2.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
		}

		for (Rectangle rect : Frame.commonList) {
			g2.setColor(Color.BLUE);
			g2.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
		}
		for (Rectangle rect : Frame.rectangleList) {
			g2.setStroke(solid);
			g2.setColor(Color.BLACK);
			g2.drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
		}
		for (Rectangle rect : Frame.unionList) {
			g2.setStroke(dashed);
			g2.setColor(Color.BLUE);
			g2.drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
		}
	}

	static int originalX = 0;
	static int originalY = 0;

	public DrawingPanel() {
		this.setPreferredSize(new Dimension(1080, 720));
		this.setBackground(new java.awt.Color(255, 255, 255));
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				originalX = evt.getX();
				originalY = evt.getY();
				Rectangle rectangle = new Rectangle(originalX, originalY, 0, 0);
				Frame.rectangleList.add(rectangle);
			}

			public void mouseReleased(MouseEvent evt) {
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent evt) {
				Rectangle rectangle = Frame.rectangleList.get(Frame.rectangleList.size() - 1);
				int width = evt.getX() - originalX;
				int height = evt.getY() - originalY;
				System.out.println(originalX + ":" + originalY);
				if (width < 0) {
					rectangle.setX(originalX - Math.abs(width));

				}
				if (height < 0) {
					rectangle.setY(originalY - Math.abs(height));

				}
				rectangle.setWidth(Math.abs(width));
				rectangle.setHeight(Math.abs(height));
				System.out.println(rectangle);
				Frame.rectangleList.remove(Frame.rectangleList.size() - 1);
				Frame.rectangleList.add(rectangle);
				repaint();
			}
		});
	}
}