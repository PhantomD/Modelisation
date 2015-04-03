package modelisation;
 
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
 
import javax.imageio.ImageIO;
import javax.swing.*;
import static modelisation.SeamCarving.flotDepart;
import static modelisation.SeamCarving.gResiduel;
import static modelisation.SeamCarving.interest;
import static modelisation.SeamCarving.parcoursLargeur;
import static modelisation.SeamCarving.tograph;
 
public class Interface extends JPanel implements ActionListener {
 
        static private final String newline = "\n";
        private JButton openButton, seamButton;
        private JTextArea txtArea;
        private JFileChooser fileCh;
        private BufferedImage pgm;
        private boolean ok = false;
        private File file;
 
        public Interface() {
                super(new BorderLayout());
 
                txtArea = new JTextArea(5, 20);
                txtArea.setMargin(new Insets(5, 5, 5, 5));
                txtArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(txtArea);
 
                fileCh = new JFileChooser();
 
                openButton = new JButton("Ouvrir un fichier");
                openButton.addActionListener(this);
 
                seamButton = new JButton("Lancer Seam Carving");
                seamButton.addActionListener(this);
 
                JPanel buttonPanel = new JPanel();
                buttonPanel.add(openButton);
                buttonPanel.add(seamButton);
 
                add(buttonPanel, BorderLayout.PAGE_START);
                add(scrollPane, BorderLayout.CENTER);
        }
 
        public void actionPerformed(ActionEvent e) {
 
                if (e.getSource() == openButton) {
                        int returnVal = fileCh.showOpenDialog(Interface.this);
 
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                                file = fileCh.getSelectedFile();
 
                                String ext = file.getName().substring(
                                                file.getName().lastIndexOf("."));
                                String correct = ".pgm";
 
                                try {
                                        setPgm(ImageIO.read(file));
                                        if (ext.equals(correct) == false) {
 
                                                txtArea.append("Erreur : fichier .pgm requis" + newline);
 
                                        } else {
                                                ok = true;
                                                txtArea.append("Selection du fichier : "
                                                                + file.getName() + "." + newline);
                                        }
                                } catch (Exception e1) {
                                        // TODO Auto-generated catch block
                                        JOptionPane.showMessageDialog(this,
                                                        "Erreur :" + e1.toString(), "Erreur",
                                                        JOptionPane.ERROR_MESSAGE);
                                }
                        } else {
                                txtArea.append("Fenêtre fermée par l'utilisateur." + newline);
                        }
 
                        if (ok) {
                                txtArea.setCaretPosition(txtArea.getDocument().getLength());
                        }
 
                } else if (e.getSource() == seamButton) {
 
                        SeamCarving sc = new SeamCarving();
                        int[][] tab = sc.readpgm(file.getPath());
                        System.out.println("FLOTDEPART = "+flotDepart(tograph(interest(tab)))+"\n");
                        sc.parcoursLargeur(gResiduel(tograph(interest(tab))));
                }
        }
 
        private static void createAndShowGUI() {
 
                JFrame frame = new JFrame("SeamCarving");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
                frame.add(new Interface());
                frame.pack();
                frame.setVisible(true);
        }
 
        public BufferedImage getPgm() {
                return pgm;
        }
 
        public void setPgm(BufferedImage pgm) {
                this.pgm = pgm;
        }
       
        public static void main(String[] args) {
 
                SwingUtilities.invokeLater(new Runnable() {
 
                        public void run() {
 
                                UIManager.put("swing.boldMetal", Boolean.FALSE);
                                createAndShowGUI();
                        }
                });
        }
}
