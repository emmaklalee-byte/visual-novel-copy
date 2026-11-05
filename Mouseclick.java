

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.*;
import java.io.*;

public class Mouseclick extends JFrame{
    private JButton button;
    private JTextArea textArea;

   

    public Mouseclick (){
        super("Visual Novel Tool");
        setLayout(new BorderLayout());

        //create next button
        button = new JButton("next");
        button.setPreferredSize(new Dimension(100,30));
        
        //define background image
        ImageIcon bkg = new ImageIcon("pngresource/background/bkg1.png");
        JLabel anewlabel = new JLabel(bkg);
        JLabel chlabel = new JLabel();
       
        //next button functions are all here
        button.addMouseListener(new MouseAdapter(){
            //@Override
            int i = 0;
            int bkgnow = 1;
            int chcount2;

            //read the text file
            public static String[] readFile() {
                int l = 0;
                String b[] = new String[100000];
                String pathname = "txtresource/test1.txt"; 
        
                try (FileReader reader = new FileReader(pathname);
                     BufferedReader br = new BufferedReader(reader) 
                ) {
                    String line;
                    
                    
                    while ((line = br.readLine()) != null) {
                        
                        b[l] = line;
                        l++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //return matrix b, b[i] is the ith line of the text file
                return b;
            }

            //get background image (linear system)
            public static String[] readbkgfile(){
                File bkgfile = new File("pngresource/background");
                String bkgarr[] = new String[10000];
                File[] files = bkgfile.listFiles();
                int bkgcount = 0;
                for (File file2 : files){
                    bkgarr[bkgcount] = "pngresource/background/"+file2.getName();
                    //System.out.println(file2.getName());
                    bkgcount++;
                }
                return bkgarr;
            }

            //get character image
            public static String[] readchfile(){
                File chfile = new File("pngresource/character");
                String charr[] = new String[100];
                File[] files = chfile.listFiles();
                int chcount = 0;
                for (File file2 : files){
                    charr[chcount] = "pngresource/character/"+file2.getName();
                    //System.out.println(file2.getName());
                    chcount++;
                }
                charr[chcount] = "chend";
                return charr;
            }

            String text1[] = readFile();
            String chlist[] = readchfile();
            String bkglist[] = readbkgfile();
            Boolean isexist[] = new Boolean[100000];
            String pngtogether[] = new String[10000];

            //combine images
            public static String makepngtogether(String[] bkglist,String[] chlist, int bkgnow,int chcount2,Boolean[] isexist,String[] pngtogether){
                try{
                BufferedImage big = ImageIO.read(new File(bkglist[bkgnow]));
                BufferedImage small = ImageIO.read(new File(chlist[chcount2]));
                Graphics2D g = big.createGraphics();
                int x = 100;
                int y = -10 ;
                g.drawImage(small, x, y, small.getWidth(), small.getHeight(), null);
                g.dispose();
                ImageIO.write(big, "png", new File("combine/"+bkgnow + chcount2 +".png"));
                }catch (Exception e){
                    e.printStackTrace();
                }
                int k = Integer.parseInt(""+bkgnow+chcount2);
                isexist[k] = false;
                pngtogether[k] = "combine/"+bkgnow+chcount2+".png";
                return "combine/"+bkgnow+chcount2+".png";
            }
            
            //jump to different areas of the text file(for choice function)
            public static int jumpTo(String searchString, String[] dataBase) {
                int jump = 0;
                for (int search = 0; search < dataBase.length; search++) {
                    if (dataBase[search] != null && dataBase[search].contains(searchString) && dataBase[search].contains("@@")) {
                        jump = search;
                    }
                }
                return jump;
            }

            //set background image (changed from initial linear progression to a fetchable asset)
            public static int callBkg(String command) {
                int bgcount = 1;
                String intValue = command.replaceAll("[^0-9]", "");
                bgcount = Integer.parseInt(intValue);
                return bgcount;
            }

            //get background image (retrieves current background)
            public static int readBkg(int index, String[] dataBase) {
                int bgcount = 1;
                int search = index;
                boolean foundBkg = false;
                while (!foundBkg) {
                    if (!dataBase[search].contains("_") && search >= 1) {
                        search--;
                    }
                    else {
                        foundBkg = true;
                    }
                }
                bgcount = callBkg(dataBase[search]);
                return bgcount;
            }

            //what happens when next button is clicked
             public void mouseClicked (MouseEvent e){
                //textArea.setText("Hello");
                boolean isChoice = false;
                if (i < text1.length){
                    //changes background
                    if (text1[i].contains("_")){
                        //System.out.println(bkglist[bkgnow]);
                        bkgnow = callBkg(text1[i]);
                        anewlabel.setIcon(new ImageIcon(bkglist[bkgnow]));
                        i++;
                        //textArea.setText(text1[i]);
                    }
                    //calls choice button window
                    if (text1[i].contains("CHOICE")) {
                        isChoice = true;
                        //turns off next button until choice is made
                        button.setEnabled(false);
                        //creates desired number of choice buttons, window, panel etc. blah blah
                        int buttonCount = Integer.parseInt(text1[i+1]);
                        String[] choiceName = new String[buttonCount];
                        JPanel windowContent= new JPanel();
                        JButton[] choiceButtons = new JButton[buttonCount];
                        BorderLayout bl = new BorderLayout();
                        windowContent.setLayout(bl);
                        JTextField displayField = new JTextField(30);
                        textArea.setText(text1[i]);
                        //labels buttons
                        for (int j = 0; j < buttonCount; j++) {
                            int buttonLabel = i + j + 2;
                            choiceButtons[j] = new JButton(text1[buttonLabel]);
                            choiceName[j] = text1[buttonLabel];
                        }
                        //moves pointer out of the button setup
                        i = i + buttonCount + 1;
                        GridLayout gl =new GridLayout(1,buttonCount);
                        windowContent.setLayout(gl);
                        //add buttons
                        for (int j = 0; j < buttonCount; j++) {
                            windowContent.add(choiceButtons[j]);
                        }
                        JFrame frame = new JFrame("Choice Panel");
                        frame.setContentPane(windowContent);
                        //set the size of the window to be big enough to accomodate all controls.
                        frame.pack();
                        //display the window
                        frame.setVisible(true);
                        //centers the window
                        frame.setLocationRelativeTo(null); 
                        //the actual choice function--jumps player to desired portion of text
                        for (int j = 0; j < buttonCount; j++) {
                            final Integer innerJ = j;
                            choiceButtons[j].addMouseListener(new MouseAdapter() {
                                //what happens when you click on these cool new buttons??
                                public void mouseClicked(MouseEvent e) {
                                //you jump to the corresponding checkpoint, of course!
                                  i = jumpTo(choiceName[innerJ], text1) + 1;
                                  bkgnow = readBkg(i, text1);
                                  //you can click next again
                                  button.setEnabled(true);
                                  //you CAN'T click another choice though
                                  frame.setVisible(false);
                                 }   
                            });
                        }  
  
                    }
                    //we don't want to print the checkpoints and funky choice mechanisms
                    if (!text1[i].contains("@@") && !isChoice) {
                        chcount2 = 0;
                        //BufferedImage buffimg = null;
                        while (chlist[chcount2].equals("chend")==false){
                            char temp = text1[i].charAt(0);
                            char chcountchar = (char)(chcount2+'0'); 
                            //System.out.println(temp);
                            //System.out.println(chcountchar);
                            //System.out.println(chlist[0]);
                            if (temp == chcountchar){
                                //chlabel.setIcon(new ImageIcon(chlist[chcount2]));
                                System.out.println(chlist[chcount2]);
                                //add(chlabel,FlowLayout.LEADING);
                                int k1 = Integer.parseInt(""+bkgnow+chcount2);
                                //if (isexist[k1] == false){
                                    //anewlabel.setIcon(new ImageIcon(pngtogether[k1]));
                                //}
                                //else {
                                String newimg = makepngtogether(bkglist,chlist,bkgnow,chcount2,isexist,pngtogether);
                                anewlabel.setIcon(new ImageIcon(newimg));
                                //}

                            }
                            chcount2++;
                        }
                            textArea.setText(text1[i].substring(1,text1[i].length()));
                    }
                    
                    //System.out.println(text1[i]);
                    i++;
                }
                else {
                    textArea.setText("end");
                    button.setEnabled(false);
                }


            }
        });

        textArea = new JTextArea();
        Font font = new Font("Arial",Font.PLAIN,28);
        textArea.setFont(font);
        textArea.setEditable(false);

        
        add(chlabel,BorderLayout.CENTER);
        add(button,BorderLayout.SOUTH);
       
        add(anewlabel,BorderLayout.NORTH);
        add(new JScrollPane(textArea),BorderLayout.CENTER);

        setSize(1300,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); 

    }

    public static void main(String[] args) {
        new Mouseclick();
    }
}

