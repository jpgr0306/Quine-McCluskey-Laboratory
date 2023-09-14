//Hello World

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import java.awt.Desktop;
import java.net.URI;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Dimension;

public class GUI {

    private JFrame frmQuinemccluskeyLaboratory;
    private JTextField textAreaDontCares;
    private JTextField textAreaGeneric;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                     try {
                         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } 
                    catch (Exception e) {
                       e.printStackTrace();
                    }
                    GUI window = new GUI();
                    window.frmQuinemccluskeyLaboratory.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GUI() {
        frmQuinemccluskeyLaboratory = new JFrame();
        frmQuinemccluskeyLaboratory.setMinimumSize(new Dimension(650, 400));
        frmQuinemccluskeyLaboratory.setTitle("Quine-McCluskey Laboratory");
        frmQuinemccluskeyLaboratory.setIconImage(new ImageIcon(getClass().getResource("Favicon.png")).getImage());
        frmQuinemccluskeyLaboratory.setBounds(100, 100, 590, 460);
        frmQuinemccluskeyLaboratory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JMenuBar menuBar = new JMenuBar();
        
        JLabel lblNewLabelTitle = new JLabel("Quine-McCluskey Laboratory");
        lblNewLabelTitle.setFont(new Font("Bahnschrift", Font.BOLD, 16));
        lblNewLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        
        textAreaDontCares = new JTextField();
        textAreaDontCares.setColumns(10);
        
        textAreaGeneric = new JTextField();
        textAreaGeneric.setColumns(10);

        final JLabel lblNewLabel1 = new JLabel("Expression");
        lblNewLabel1.setHorizontalAlignment(SwingConstants.LEFT);
        
        JLabel lblNewLabel2 = new JLabel("Don't Cares");
        lblNewLabel2.setHorizontalAlignment(SwingConstants.LEFT);
        
        JMenu mnNewMenu = new JMenu("Evaluate");
        mnNewMenu.setIcon(new ImageIcon(getClass().getResource("Evaluate.png")));
        menuBar.add(mnNewMenu);
        
        JMenuItem mntmNewMenuItem = new JMenuItem("Expression");
        mntmNewMenuItem.setIcon(new ImageIcon(getClass().getResource("Pencil.png")));
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblNewLabel1.setText("Expression");
            }
        });
        mnNewMenu.add(mntmNewMenuItem);
        
        JMenuItem mntmNewMenuItem_1 = new JMenuItem("Minterms");
        mntmNewMenuItem_1.setIcon(new ImageIcon(getClass().getResource("Minterm.png")));
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblNewLabel1.setText("Minterms");
            }
        });
        mnNewMenu.add(mntmNewMenuItem_1);
        
        JMenuItem mntmNewMenuItem_2 = new JMenuItem("Truth Table");
        mntmNewMenuItem_2.setIcon(new ImageIcon(getClass().getResource("Table.png")));
        mntmNewMenuItem_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblNewLabel1.setText("Truth Table");
            }
        });

        mnNewMenu.add(mntmNewMenuItem_2);
        
        JMenu mnNewMenu_1 = new JMenu("Help");
        mnNewMenu_1.setIcon(new ImageIcon(getClass().getResource("Help.png")));
        menuBar.add(mnNewMenu_1);
        
        JMenuItem mntmNewMenuItem_3 = new JMenuItem("Documentation");
        mntmNewMenuItem_3.setIcon(new ImageIcon(getClass().getResource("Documentation.png")));
        mntmNewMenuItem_3.addActionListener(new ActionListener(){
        		public void actionPerformed(ActionEvent e){
        			openURL("https://github.com/jpgr0306/Quine-McCluskey-Laboratory");
        		}
        	}
        );
        mnNewMenu_1.add(mntmNewMenuItem_3);
        
        JMenuItem mntmNewMenuItem_4 = new JMenuItem("License and Credits");
        mntmNewMenuItem_4.setIcon(new ImageIcon(getClass().getResource("License.png")));
        mnNewMenu_1.add(mntmNewMenuItem_4);
        mntmNewMenuItem_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frmLicense = new JFrame();
                frmLicense.setResizable(false);
                frmLicense.setTitle("License and Credits");
                frmLicense.setIconImage(new ImageIcon(getClass().getResource("License.png")).getImage());
                frmLicense.setBounds(0,0,479,426);
                frmLicense.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frmLicense.getContentPane().setLayout(null);
                JLabel johnImage = new JLabel(), alekImage = new JLabel(), johnName = new JLabel("João Pedro Gava Ribeiro"),
                        alekName = new JLabel("Alexandre Zucki Baciuk"), cv = new JLabel(), cc = new JLabel(),
                        license = new JLabel("Attribution-ShareAlike 4.0");
                JButton btnJohn = new JButton(), btnAlek = new JButton(), btnLicense = new JButton();
                btnJohn.setText("CV");
                btnJohn.setFont(btnJohn.getFont().deriveFont(10.0f));
                btnJohn.setBounds(76,5,50,25);
                btnJohn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        openURL("https://lattes.cnpq.br/9777800466088295");
                    }
                });
                btnAlek.setText("CV");
                btnAlek.setFont(btnAlek.getFont().deriveFont(10.0f));
                btnAlek.setBounds(306,5,50,25);
                btnAlek.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        openURL("https://lattes.cnpq.br/3603022201952775");
                    }
                });
                btnLicense.setText("Read License");
                btnLicense.setFont(btnLicense.getFont().deriveFont(10.0f));
                btnLicense.setBounds(271,336,120,25);
                btnLicense.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        openURL("https://creativecommons.org/licenses/by-sa/4.0/");
                    }
                });
                johnImage.setIcon(new ImageIcon(getClass().getResource("JoaoPedro.png")));
                alekImage.setIcon(new ImageIcon(getClass().getResource("Alexandre.png")));
                cv.setIcon(new ImageIcon(getClass().getResource("CV.png")));
                cc.setIcon(new ImageIcon(getClass().getResource("CC.png")));
                johnName.setHorizontalAlignment(SwingConstants.CENTER);
                johnName.setBounds(10,30,183,20);
                alekName.setHorizontalAlignment(SwingConstants.CENTER);
                alekName.setBounds(208,30,246,20);
                johnImage.setBounds(10,50,183,246);
                alekImage.setBounds(208,50,246,246);
                cv.setBounds(140,5,152,25);
                cc.setBounds(10,306,201,70);
                license.setBounds(208,310,246,20);
                license.setHorizontalAlignment(SwingConstants.CENTER);
                frmLicense.getContentPane().add(johnImage);
                frmLicense.getContentPane().add(alekImage);
                frmLicense.getContentPane().add(johnName);
                frmLicense.getContentPane().add(alekName);
                frmLicense.getContentPane().add(cv);
                frmLicense.getContentPane().add(cc);
                frmLicense.getContentPane().add(license);
                frmLicense.getContentPane().add(btnJohn);
                frmLicense.getContentPane().add(btnAlek);
                frmLicense.getContentPane().add(btnLicense);
                frmLicense.setVisible(true);
            }
        });
        
        Button button1 = new Button("Generate");
        
        Button button2 = new Button("Copy");
        Font textPaneFont = new Font(Font.MONOSPACED, Font.PLAIN, 14);
        final JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(textPaneFont);
    
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final Expression exp;
                Minterm min;
                if(lblNewLabel1.getText().equals("Expression"))
                {
                    try {
                        exp = new Expression(textAreaGeneric.getText(),textAreaDontCares.getText());
                        textArea.setText(exp.getSolution());
                    } catch (Exception ex){
                    	createErrorMessage(ex.getMessage());
                    }
                }
                else if(lblNewLabel1.getText().equals("Minterms"))
                {
                    //AVALIAR
                    try {
                        min = new Minterm(textAreaGeneric.getText(),textAreaDontCares.getText());
                        textArea.setText(min.getSolution("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"));
                    } catch (Exception ex){
                    	createErrorMessage(ex.getMessage());
                    }
                }
                else
                {
                    try {
                        exp = new Expression(textAreaGeneric.getText(),textAreaDontCares.getText());
                        textArea.setText(exp.getTableString());
                    } catch (Exception ex){
                    	createErrorMessage(ex.getMessage());
                    }
                }
            }
        });
        
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringSelection selection = new StringSelection(textArea.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, selection);
            }
        });
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(textArea);
        GroupLayout groupLayout = new GroupLayout(frmQuinemccluskeyLaboratory.getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addComponent(menuBar, GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
        			.addGap(2))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addComponent(lblNewLabelTitle, GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
        			.addGap(2))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(32)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(68)
        					.addComponent(textAreaGeneric, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
        				.addComponent(lblNewLabel1, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE))
        			.addGap(8)
        			.addComponent(button1, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
        			.addGap(48))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(32)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblNewLabel2, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(68)
        					.addComponent(textAreaDontCares, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)))
        			.addGap(8)
        			.addComponent(button2, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
        			.addGap(48))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(32)
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
        			.addGap(46))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
        			.addGap(12)
        			.addComponent(lblNewLabelTitle, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
        			.addGap(10)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(1)
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(textAreaGeneric, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lblNewLabel1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
        					.addGap(3))
        				.addComponent(button1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(4)
        					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblNewLabel2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
        						.addComponent(textAreaDontCares, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
        				.addComponent(button2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
        			.addGap(32))
        );
        frmQuinemccluskeyLaboratory.getContentPane().setLayout(groupLayout);
    }
    
    private void openURL(String url){
        Desktop desktop = java.awt.Desktop.getDesktop();
        try {
            URI toOpen = new URI(url);
            desktop.browse(toOpen);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void createErrorMessage(String error){
    	JPanel panel = new JPanel(new GridLayout(0,1));
    	String[] lines = error.split("\n");
    	for (String line : lines) {
    	    JLabel label = new JLabel(line);
    	    label.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
    	    panel.add(label);
    	}
    	JOptionPane.showMessageDialog(null,panel,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
