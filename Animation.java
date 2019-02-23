package Graham;
 
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Animation extends JPanel implements ActionListener {

    private static final int WIDE = 1100;
    private static final int HIGH = 600;
    
    public static  List<Punct> PuncteAdaugate=new ArrayList<>();
    public static  List<Punct> convexHull=new ArrayList<>();
    static JButton Hull, Sterge, Adauga,RandomP;
    public static JFrame f;
    static JTextField  Xstring, Ystring;
  	static JLabel nrPct,nrPctConvexe;
  	static JCheckBox box,partybox,coord;
 	
  	static boolean boxx=false;
  	static boolean party=false;
  	static boolean showCoord=false;
  	
  	static List<Punct> listaPuncte=new ArrayList<>();
 	
  	
    void create() {
    
    	
    	f = new JFrame("Convex Hulls (Graham Scan) v1.2 - Florea Marius-Ioan - SDA 2018");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(this);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setResizable(false);
        
       
        box=new JCheckBox("Auto-Hull",false);
        partybox=new JCheckBox("ColorMode",false);
        coord=new JCheckBox("Coordonate",false);
        
        Xstring=new JTextField(3);
     	add(Xstring);
     	Ystring=new JTextField(3);
     	add(Ystring);
     	
     	
        
    	
    	 Adauga = new JButton( new AbstractAction("Adauga") {
             @Override
             public void actionPerformed( ActionEvent e ) {
     
            	 if(Xstring.getText().isEmpty()||Ystring.getText().isEmpty())
            		 JOptionPane.showMessageDialog(f, "Valorile pentru X si Y nu pot fi nule!");
            	 if(Integer.valueOf(Xstring.getText())>800||Integer.valueOf(Ystring.getText())>550)
            		 JOptionPane.showMessageDialog(f, "X E [1,800],  Y E [1,550]");
             	
            	 
            	Integer X=Integer.valueOf(Xstring.getText());
     			Integer Y=Integer.valueOf(Ystring.getText());
     		
            	 
     			
     			
     		JLabel punctJL = new JLabel();
              punctJL.setIcon(new ImageIcon("punct2.png"));
                
              Dimension d = punctJL.getPreferredSize();
              punctJL.setBounds(X-10, Y-10, d.width, d.height);
                 
              
          	JLabel coord = new JLabel();
        	coord.setForeground(Color.WHITE);
        	 coord.setBounds(X, Y, 60, 45);
                
        	if(showCoord==true)
        	{coord.setText((X-10)+","+(Y-10));}
            else coord.setText("");
            add(coord);
              
              
              
              add(punctJL);
             
              Punct p=new Punct(X,Y);
              PuncteAdaugate.add(p);
            
              if(boxx==true) {
              if(PuncteAdaugate.size()>=3)
              convexHull = GrahamScan.ConvexHull(PuncteAdaugate);
              }
              
              Xstring.setText("");
                Ystring.setText("");
                
                updatePuncte();
                repaint();
             
             
             }

 			
         });
    
    	 
    	 
        
        Hull = new JButton( new AbstractAction("Hull") {
            @Override
            public void actionPerformed( ActionEvent e ) {
              if(PuncteAdaugate.size()<3)
            	  {JOptionPane.showMessageDialog(f, "O invelitoare trebuie sa contina minim 3 puncte! Mai adauga "+(3-PuncteAdaugate.size())+" puncte.");  
            	  }
            
              else  
              if(GrahamScan.Coliniaritate(PuncteAdaugate)!=0)
              	  {JOptionPane.showMessageDialog(f, "O invelitoare nu poate fi formata doar din puncte coliniare");
                	
              	  }
              
              else
              convexHull = GrahamScan.ConvexHull(PuncteAdaugate);
              
            
              updatePuncte();
              
              repaint();
             
            
            
            }

			
        });

        
        
        
        
        Sterge = new JButton( new AbstractAction("Sterge") { 
            @Override
            public void actionPerformed( ActionEvent e ) {
            
            	
            	
            	PuncteAdaugate.clear();
            	convexHull.clear();
            	
            	removeAll();    	
            	f.dispose();
                create();
                boxx=false;
                party=false;
            	
            	repaint();
            	
            }

		
            
            
        });
     
        
        
        
        RandomP = new JButton( new AbstractAction("Random") { 
            @Override
            public void actionPerformed( ActionEvent e ) {
            
            	Random rand = new Random();
            
            	for(int i=0;i<50;i++)
            	{
            		int X=rand.nextInt(850)+1;
            		int Y=rand.nextInt(550)+1;
            		  JLabel punctJL = new JLabel();
                      punctJL.setIcon(new ImageIcon("punct2.png"));
                        
                      Dimension d = punctJL.getPreferredSize();
                      punctJL.setBounds(X-10, Y-10, d.width, d.height);
                         
                      add(punctJL);
                     
                      
                    	JLabel coord = new JLabel();
                  	coord.setForeground(Color.WHITE);
                  	 coord.setBounds(X, Y, 60, 45);
                          
                  	if(showCoord==true)
                  	{coord.setText((X-10)+","+(Y-10));}
                      else coord.setText("");
                      add(coord);
                        
                        
                      
                      
                      
                      Punct p=new Punct(X,Y);
                      PuncteAdaugate.add(p);
            		
                      if(boxx==true) {
                          if(PuncteAdaugate.size()>=3)
                          convexHull = GrahamScan.ConvexHull(PuncteAdaugate);
                          }
                      
                      updatePuncte();
                      repaint();
            		
            		
            		
            	}
            	
            }

		
            
            
        });
        
       
        
        nrPct=new JLabel();
        nrPct.setForeground(Color.WHITE);
        nrPct.setFont(new Font("Courier New", Font.BOLD, 16));
        nrPct.setText("Puncte: 0      ");
        
      
        nrPctConvexe=new JLabel();
        nrPctConvexe.setForeground(Color.WHITE);
        nrPctConvexe.setFont(new Font("Courier New", Font.BOLD, 16));
        nrPctConvexe.setText("Puncte Convexe: 0      ");
      
        

        
        add(Adauga);
        add(Hull);
        add(RandomP);
        add(Sterge);
        add(nrPct); 
        add(nrPctConvexe);
        add(box);
        add(coord);
        add(partybox);
        
        
        box.addItemListener(new ItemListener() {    
            public void itemStateChanged(ItemEvent e) {                 
                 
               if(e.getStateChange()==1)
            	   boxx=true;
               else boxx=false;
            
            }    
         });    
        
        partybox.addItemListener(new ItemListener() {    
            public void itemStateChanged(ItemEvent e) {                 
                 
               if(e.getStateChange()==1)
            	   party=true;
              
               else party=false;
            
            }    
         });    
        
     
        
        coord.addItemListener(new ItemListener() {    
            public void itemStateChanged(ItemEvent e) {                 
                 
               if(e.getStateChange()==1)
            	   showCoord=true;
               else showCoord=false;
            
            }    
         });    
        
        validate();
        
        
    }                                                                                //-------------------------------------
    
    
    void updatePuncte() {

        remove(nrPct);
        nrPct.setText("Puncte: "+PuncteAdaugate.size()+"   ");
        add(nrPct); 
        
        remove(nrPctConvexe);
        if(convexHull.size()<3)
            nrPctConvexe.setText("Puncte Convexe: 0");
        else
        nrPctConvexe.setText("Puncte Convexe: "+(convexHull.size()-1)+"  ");
        
        add(nrPctConvexe); 
        
    }
 
    public Animation() {															
    	
    	Color c=new Color(0, 61, 63);
        this.setBackground(c);
       this.setPreferredSize(new Dimension(WIDE, HIGH));
        this.addMouseListener(new MouseHandler());
      
       
        
    }

    
    
    

    @Override
    public void paintComponent(Graphics g) {
   	
        super.paintComponent(g);
        int xloc,yloc;
        
        Color c;
        if(party==true) {
        
        Random rand = new Random();
        int a=rand.nextInt(255)+1;
        int b=rand.nextInt(255)+1;
        int d=rand.nextInt(255)+1;
        
        c=new Color(a,b,d);
        }
        else
          c=new Color(82, 178, 131);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(8));
        g2.setColor(c);
        
        
        
        
        if(convexHull.size()>=3)
        { xloc=convexHull.get(0).x;
          yloc=convexHull.get(0).y;
          
        	for(int i=1;i<convexHull.size();i++)
        	{
        		  g2.drawLine(xloc, yloc,convexHull.get(i).x, convexHull.get(i).y);
        	xloc=convexHull.get(i).x;
        	yloc=convexHull.get(i).y; 
        	
        	}   
       
    }
        
        
    }

    
    
    
   
    	

    public void actionPerformed(ActionEvent e) {
        this.repaint();
        
   
    }

    
    
    
    private class MouseHandler extends MouseAdapter {

    
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
        
            
           
            
        	JLabel punctJL = new JLabel();
        	punctJL.setForeground(Color.WHITE);
            if(showCoord==true)
        	{punctJL.setText((e.getX()-10)+","+(e.getY()-10));}
            else punctJL.setText("");
            
        	punctJL.setIcon(new ImageIcon("punct2.png"));
            
           
            Dimension d = punctJL.getPreferredSize();
            punctJL.setBounds(e.getX()-10, e.getY()-10, d.width, d.height);
            
            add(punctJL);
            
        
        Punct p=new Punct(e.getX(),e.getY());
            
            PuncteAdaugate.add(p);
           
            if(boxx==true) {
                if(PuncteAdaugate.size()>=3)
                convexHull = GrahamScan.ConvexHull(PuncteAdaugate);
                }
            
            updatePuncte();
            repaint();
           
          
        }
    }

        
    }