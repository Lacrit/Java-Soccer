

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;


import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.border.MatteBorder;

public class Main {

	protected static String fileName = "Untitled";

	public static void main(String[] args) {
	
		JFrame jf = new JFrame();
		jf.setTitle("Simple editor - " + fileName);
		JTextArea jt = new JTextArea(20, 60);
		JFileChooser fc = new JFileChooser();
		JScrollPane sp = new JScrollPane(jt, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		FileFilter txtFilter = new FileNameExtensionFilter("Plain text", "txt");
		fc.setFileFilter(txtFilter);
		jt.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		jt.setBackground(new Color(248, 248, 255));
		jt.setForeground(new Color(128, 128, 128));
		// ==========================================================================
		
		
		JMenuBar mb = new JMenuBar();

		// Main button "File"........
		// ==========================================================================
		JMenu menu1 = new JMenu("File");
		menu1.setFont(new Font("Segoe UI", Font.PLAIN, 35));
		mb.add(menu1);
		// ==========================================================================
		
		JMenuItem mi1 = new JMenuItem("Open");
		mi1.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mi1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mi1.setBorder(BorderFactory.createRaisedBevelBorder());
		//////////////////////////////////////////////////////////////////////////////
		mi1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {				
					fileName = fc.getSelectedFile().getAbsolutePath();
					jf.setTitle("Simple editor - " + fileName);
					FileReader fr = null;
					try {
						fr = new FileReader(fileName);
						jt.read(fr, null);
						fr.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		menu1.add(mi1);
		//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\		
		
		JMenuItem mi12 = new JMenuItem("Save");
		mi12.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mi12.setMnemonic(KeyEvent.VK_S);
		mi12.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mi12.setBorder(BorderFactory.createRaisedBevelBorder());
		///////////////////////////////////////////////////////////////////////////////
		mi12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fileName != "Untitled") {
					try (FileWriter fw = new FileWriter(fc.getSelectedFile().getAbsolutePath() + ".txt")) {
						fw.write(jt.getText());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} else {
					if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
						FileWriter fw = null;
						try {
							fw = new FileWriter(fc.getSelectedFile().getAbsolutePath());
							jt.write(fw);
							fileName = fc.getSelectedFile().getAbsolutePath();
							jf.setTitle("Simple editor - " + fileName);
							fw.close();
						} catch (Exception ex1) {
							ex1.printStackTrace();
						}
					}
								
				}
			}
		});
		menu1.add(mi12);
		//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		JMenuItem mi13 = new JMenuItem("Save as...");
		mi13.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mi13.setMnemonic(KeyEvent.VK_S);
		mi13.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK | KeyEvent.SHIFT_MASK));
		mi13.setBorder(BorderFactory.createRaisedBevelBorder());
		/////////////////////////////////////////////////////////////////////////////////
		mi13.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					if (fileName != "Untitled") {
						try (FileWriter fw = new FileWriter(fc.getSelectedFile())) {
							fw.write(jt.getText());
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					} else {
					FileWriter fw = null;
					try {
						fw = new FileWriter(fc.getSelectedFile().getAbsolutePath() + ".txt");
						jt.write(fw);
						fileName = fc.getSelectedFile().getAbsolutePath();
						jf.setTitle("Simple editor - " + fileName);						
						fw.close();
					} catch (Exception eX) {
						eX.printStackTrace();
					}
				}
				}
			}
		});
		//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		menu1.add(mi13);
		
		JMenuItem mi14 = new JMenuItem("Exit");
		mi14.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mi14.setMnemonic(KeyEvent.VK_X);
		mi14.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_MASK | KeyEvent.SHIFT_MASK));
		mi14.setBorder(BorderFactory.createRaisedBevelBorder());
		///////////////////////////////////////////////////////////////////////////////////////////
		mi14.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int res = JOptionPane.showConfirmDialog(mi14, "Would you like to save " + fileName + " before close?", "Save dialog",
						JOptionPane.YES_NO_OPTION);
				if (res == JOptionPane.YES_OPTION) {
					saveFile();
					System.exit(0);
				} else if (res == JOptionPane.NO_OPTION) {
				    System.exit(0);
				} else if (res == JOptionPane.CANCEL_OPTION) {		
					return;
				}
			}

			private void saveFile() {
				if (fileName != "Untitled") {
					try (FileWriter fw = new FileWriter(fc.getSelectedFile())) {
						fw.write(jt.getText());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				} else {
					if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
						FileWriter fw = null;
						try {
							fw = new FileWriter(fc.getSelectedFile().getAbsolutePath());
							jt.write(fw);
							fileName = fc.getSelectedFile().getAbsolutePath();
							fw.close();
						} catch (Exception ex1) {
							ex1.printStackTrace();
						}
					}
								
				}
			}
		});
		//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		//==========================================================================================
				JSeparator sep = new JSeparator();
				sep.setBorder(new MatteBorder(0, 10, 0, 10, (Color) SystemColor.menu));
				sep.setBackground(Color.RED);
				menu1.add(sep);
		menu1.add(mi14);
		//==========================================================================================

		// Main button "Edit"
		// =====================================================================
		JMenu menu2 = new JMenu("Edit");
		menu2.setFont(new Font("Segoe UI", Font.PLAIN, 35));
		mb.add(menu2);

		// =====================================================================
		
		JMenu mnNewMenu = new JMenu("Adress");
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mnNewMenu.setBorder(BorderFactory.createRaisedBevelBorder());
		menu2.add(mnNewMenu);
	
		JMenuItem mntmNewMenuItem = new JMenuItem("Home ");
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mntmNewMenuItem.setMnemonic(KeyEvent.VK_H);
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_MASK | KeyEvent.SHIFT_MASK));
		mntmNewMenuItem.setBorder(BorderFactory.createRaisedBevelBorder());
		//////////////////////////////////////////////////////////////////////////////
		mntmNewMenuItem.addActionListener(new ActionListener() {
		
					@Override
					public void actionPerformed(ActionEvent e){
                        jt.insert(" I won`t tell you ", jt.getCaretPosition());
       
                }
		});
		//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("University "); 
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mntmNewMenuItem_1.setMnemonic(KeyEvent.VK_U);
		mntmNewMenuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_MASK | KeyEvent.SHIFT_MASK));
		mntmNewMenuItem_1.setBorder(BorderFactory.createRaisedBevelBorder());
		//////////////////////////////////////////////////////////////////////////////
		mntmNewMenuItem_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e){
                jt.insert(" Why do you care? ", jt.getCaretPosition());

			}
        });
		//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Company ");
		mntmNewMenuItem_2.setMnemonic(KeyEvent.VK_C);
		mntmNewMenuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK | KeyEvent.SHIFT_MASK));
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mntmNewMenuItem_2.setBorder(BorderFactory.createRaisedBevelBorder());
		///////////////////////////////////////////////////////////////////////////////
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e){
                jt.insert(" Don`t try to read my mind! ", jt.getCaretPosition());

			}
		});
		//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		mnNewMenu.add(mntmNewMenuItem_2);
	
		// Main button "Options"
		// ====================================================================
		JMenu menu3 = new JMenu("Options");
		menu3.setFont(new Font("Segoe UI", Font.PLAIN, 35));
		mb.add(menu3);

		// ====================================================================
		JMenu mnNewMenu_1 = new JMenu("Foreground");
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mnNewMenu_1.setBorder(BorderFactory.createRaisedBevelBorder());
		menu3.add(mnNewMenu_1);
		
		///////////////////////////////////////////////////////////////////////////////
		ActionListener alForegroundColor = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JMenuItem item = (JMenuItem)e.getSource();
                String s = item.getText();
                if ("Blue".equals(s)) {
                	jt.setForeground(Color.BLUE);
                } else if  ("Yellow".equals(s)) {
                	jt.setForeground(Color.YELLOW);
                } else if  ("Orange".equals(s)) {
                	jt.setForeground(Color.ORANGE);
                } else if  ("Red".equals(s)) {
                	jt.setForeground(Color.RED);
                } else if  ("White".equals(s)) {
                	jt.setForeground(Color.WHITE);
                } else if  ("Black".equals(s)) {
                	jt.setForeground(Color.BLACK);
                } else if  ("Green".equals(s)) {
                	jt.setForeground(Color.GREEN);
			}			
           }
		};
		//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Blue");
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mntmNewMenuItem_3.setBorder(BorderFactory.createRaisedBevelBorder());
		mntmNewMenuItem_3.setIcon(new MyIcon(Color.BLUE));		
		mntmNewMenuItem_3.addActionListener(alForegroundColor);
		mnNewMenu_1.add(mntmNewMenuItem_3);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Yellow");
		mntmNewMenuItem_4.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mntmNewMenuItem_4.setBorder(BorderFactory.createRaisedBevelBorder());
		mntmNewMenuItem_4.setIcon(new MyIcon(Color.YELLOW));	
		mntmNewMenuItem_4.addActionListener(alForegroundColor);
		mnNewMenu_1.add(mntmNewMenuItem_4);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Orange");
		mntmNewMenuItem_5.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mntmNewMenuItem_5.setBorder(BorderFactory.createRaisedBevelBorder());
		mntmNewMenuItem_5.setIcon(new MyIcon(Color.ORANGE));
		mntmNewMenuItem_5.addActionListener(alForegroundColor);
		mnNewMenu_1.add(mntmNewMenuItem_5);

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Red");
		mntmNewMenuItem_6.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mntmNewMenuItem_6.setBorder(BorderFactory.createRaisedBevelBorder());
		mntmNewMenuItem_6.setIcon(new MyIcon(Color.RED));
		mntmNewMenuItem_6.addActionListener(alForegroundColor);
		mnNewMenu_1.add(mntmNewMenuItem_6);

		JMenuItem mntmNewMenuItem_7 = new JMenuItem("White");
		mntmNewMenuItem_7.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mntmNewMenuItem_7.setBorder(BorderFactory.createRaisedBevelBorder());
		mntmNewMenuItem_7.setIcon(new MyIcon(Color.WHITE));
		mntmNewMenuItem_7.addActionListener(alForegroundColor);
		mnNewMenu_1.add(mntmNewMenuItem_7);

		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Black");
		mntmNewMenuItem_8.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mntmNewMenuItem_8.setBorder(BorderFactory.createRaisedBevelBorder());
		mntmNewMenuItem_8.setIcon(new MyIcon(Color.BLACK));
		mntmNewMenuItem_8.addActionListener(alForegroundColor);
		mnNewMenu_1.add(mntmNewMenuItem_8);

		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Green");
		mntmNewMenuItem_9.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mntmNewMenuItem_9.setBorder(BorderFactory.createRaisedBevelBorder());
		mntmNewMenuItem_9.setIcon(new MyIcon(Color.GREEN));
		mntmNewMenuItem_9.addActionListener(alForegroundColor);
		mnNewMenu_1.add(mntmNewMenuItem_9);

		JMenu mnNewMenu_2 = new JMenu("Background");
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mnNewMenu_2.setBorder(BorderFactory.createRaisedBevelBorder());
		menu3.add(mnNewMenu_2);
		
		//////////////////////////////////////////////////////////////////////////
		ActionListener alBackgroundColor = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JMenuItem item = (JMenuItem)e.getSource();
                String s = item.getText();
                if ("Blue".equals(s)) {
                	jt.setBackground(Color.BLUE);
                } else if  ("Yellow".equals(s)) {
                	jt.setBackground(Color.YELLOW);
                } else if  ("Orange".equals(s)) {
                	jt.setBackground(Color.ORANGE);
                } else if  ("Red".equals(s)) {
                	jt.setBackground(Color.RED);
                } else if  ("White".equals(s)) {
                	jt.setBackground(Color.WHITE);
                } else if  ("Black".equals(s)) {
                	jt.setBackground(Color.BLACK);
                } else if  ("Green".equals(s)) {
                	jt.setBackground(Color.GREEN);
			}			
           }
		};
		//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Blue");
		mntmNewMenuItem_10.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mntmNewMenuItem_10.setBorder(BorderFactory.createRaisedBevelBorder());
		mntmNewMenuItem_10.setIcon(new MyIcon(Color.BLUE));
		mntmNewMenuItem_10.addActionListener(alBackgroundColor);
		mnNewMenu_2.add(mntmNewMenuItem_10);

		JMenuItem mntmNewMenuItem_11 = new JMenuItem("Yellow");
		mntmNewMenuItem_11.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mntmNewMenuItem_11.setBorder(BorderFactory.createRaisedBevelBorder());
		mntmNewMenuItem_11.setIcon(new MyIcon(Color.YELLOW));
		mntmNewMenuItem_11.addActionListener(alBackgroundColor);
		mnNewMenu_2.add(mntmNewMenuItem_11);

		JMenuItem mntmNewMenuItem_12 = new JMenuItem("Orange");
		mntmNewMenuItem_12.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mntmNewMenuItem_12.setBorder(BorderFactory.createRaisedBevelBorder());
		mntmNewMenuItem_12.setIcon(new MyIcon(Color.ORANGE));
		mntmNewMenuItem_12.addActionListener(alBackgroundColor);
		mnNewMenu_2.add(mntmNewMenuItem_12);

		JMenuItem mntmNewMenuItem_13 = new JMenuItem("Red");
		mntmNewMenuItem_13.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mntmNewMenuItem_13.setBorder(BorderFactory.createRaisedBevelBorder());
		mntmNewMenuItem_13.setIcon(new MyIcon(Color.RED));
		mntmNewMenuItem_13.addActionListener(alBackgroundColor);
		mnNewMenu_2.add(mntmNewMenuItem_13);

		JMenuItem mntmNewMenuItem_14 = new JMenuItem("White");
		mntmNewMenuItem_14.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mntmNewMenuItem_14.setBorder(BorderFactory.createRaisedBevelBorder());
		mntmNewMenuItem_14.setIcon(new MyIcon(Color.WHITE));
		mntmNewMenuItem_14.addActionListener(alBackgroundColor);
		mnNewMenu_2.add(mntmNewMenuItem_14);

		JMenuItem mntmNewMenuItem_15 = new JMenuItem("Black");
		mntmNewMenuItem_15.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mntmNewMenuItem_15.setBorder(BorderFactory.createRaisedBevelBorder());
		mntmNewMenuItem_15.setIcon(new MyIcon(Color.BLACK));
		mntmNewMenuItem_15.addActionListener(alBackgroundColor);
		mnNewMenu_2.add(mntmNewMenuItem_15);

		JMenuItem mntmNewMenuItem_16 = new JMenuItem("Green");
		mntmNewMenuItem_16.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mntmNewMenuItem_16.setBorder(BorderFactory.createRaisedBevelBorder());
		mntmNewMenuItem_16.setIcon(new MyIcon(Color.GREEN));
		mntmNewMenuItem_16.addActionListener(alBackgroundColor);
		mnNewMenu_2.add(mntmNewMenuItem_16);

		JMenu mnNewMenu_3 = new JMenu("Font size");
		mnNewMenu_3.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mnNewMenu_3.setBorder(BorderFactory.createRaisedBevelBorder());
		menu3.add(mnNewMenu_3);
		
		//////////////////////////////////////////////////////////////////////////
		ActionListener alFontSize = new ActionListener() {
		  @Override
          public void actionPerformed(ActionEvent e) {
              JMenuItem item = (JMenuItem)e.getSource();
              String s = item.getText();
              if        ("24 pts".equals(s)) {
            	  jt.setFont(new Font("Segoe UI", Font.PLAIN, 24));
              } else if ("26 pts".equals(s)) {
            	  jt.setFont(new Font("Segoe UI", Font.PLAIN, 26));
              } else if ("28 pts".equals(s)) {
            	  jt.setFont(new Font("Segoe UI", Font.PLAIN, 28));
              } else if ("30 pts".equals(s)) {
            	  jt.setFont(new Font("Segoe UI", Font.PLAIN, 30));
              } else if ("32 pts".equals(s)) {
            	  jt.setFont(new Font("Segoe UI", Font.PLAIN, 32));
              } else if ("34 pts".equals(s)) {
            	  jt.setFont(new Font("Segoe UI", Font.PLAIN, 34));
              } else if ("36 pts".equals(s)) {
            	  jt.setFont(new Font("Segoe UI", Font.PLAIN, 36));
              } else if ("38 pts".equals(s)) {
            	  jt.setFont(new Font("Segoe UI", Font.PLAIN, 38));
              } else if ("40 pts".equals(s)) {
            	  jt.setFont(new Font("Segoe UI", Font.PLAIN, 40));
              }
          }		  
		};	
		//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		
		JMenuItem mntmNewMenuItem_17 = new JMenuItem("24 pts");
		mntmNewMenuItem_17.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mntmNewMenuItem_17.setBorder(BorderFactory.createRaisedBevelBorder());
		mntmNewMenuItem_17.addActionListener(alFontSize);
		mnNewMenu_3.add(mntmNewMenuItem_17);

		JMenuItem mntmNewMenuItem_18 = new JMenuItem("26 pts");
		mntmNewMenuItem_18.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mntmNewMenuItem_18.setBorder(BorderFactory.createRaisedBevelBorder());
		mntmNewMenuItem_18.addActionListener(alFontSize);
		mnNewMenu_3.add(mntmNewMenuItem_18);

		JMenuItem mntmNewMenuItem_19 = new JMenuItem("28 pts");
		mntmNewMenuItem_19.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mntmNewMenuItem_19.setBorder(BorderFactory.createRaisedBevelBorder());
		mntmNewMenuItem_19.addActionListener(alFontSize);
		mnNewMenu_3.add(mntmNewMenuItem_19);

		JMenuItem mntmNewMenuItem_20 = new JMenuItem("30 pts");
		mntmNewMenuItem_20.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mntmNewMenuItem_20.setBorder(BorderFactory.createRaisedBevelBorder());
		mntmNewMenuItem_20.addActionListener(alFontSize);
		mnNewMenu_3.add(mntmNewMenuItem_20);

		JMenuItem mntmNewMenuItem_21 = new JMenuItem("32 pts");
		mntmNewMenuItem_21.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mntmNewMenuItem_21.setBorder(BorderFactory.createRaisedBevelBorder());
		mntmNewMenuItem_21.addActionListener(alFontSize);
		mnNewMenu_3.add(mntmNewMenuItem_21);

		JMenuItem mntmNewMenuItem_22 = new JMenuItem("34 pts");
		mntmNewMenuItem_22.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mntmNewMenuItem_22.setBorder(BorderFactory.createRaisedBevelBorder());
		mntmNewMenuItem_22.addActionListener(alFontSize);
		mnNewMenu_3.add(mntmNewMenuItem_22);

		JMenuItem mntmNewMenuItem_23 = new JMenuItem("36 pts");
		mntmNewMenuItem_23.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mntmNewMenuItem_23.setBorder(BorderFactory.createRaisedBevelBorder());
		mntmNewMenuItem_23.addActionListener(alFontSize);
		mnNewMenu_3.add(mntmNewMenuItem_23);

		JMenuItem mntmNewMenuItem_24 = new JMenuItem("38 pts");
		mntmNewMenuItem_24.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mntmNewMenuItem_24.setBorder(BorderFactory.createRaisedBevelBorder());
		mntmNewMenuItem_24.addActionListener(alFontSize);
		mnNewMenu_3.add(mntmNewMenuItem_24);

		JMenuItem mntmNewMenuItem_25 = new JMenuItem("40 pts");
		mntmNewMenuItem_25.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		mntmNewMenuItem_25.setBorder(BorderFactory.createRaisedBevelBorder());
		mntmNewMenuItem_25.addActionListener(alFontSize);
		mnNewMenu_3.add(mntmNewMenuItem_25);

		// =============================================================================
		jf.setJMenuBar(mb);
		jf.getContentPane().add(sp);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		jf.pack();
		jf.setVisible(true);
	}

}
