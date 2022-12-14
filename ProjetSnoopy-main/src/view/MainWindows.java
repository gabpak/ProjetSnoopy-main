package view;

import controler.Music;
import model.Parameters;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainWindows extends JFrame{
    public JPanel f_panel;
    Music music;
    /**
     * create the main window
     * @see Parameters 
     */
    public MainWindows(){
        super("Snoopy Revenge - ECE Paris");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(Parameters.WINDOW_WIDTH, Parameters.WINDOW_HEIGHT);
        this.setResizable(false);
        this.setVisible(true);

        music = new Music("mainTheme.wav"); // Load the music
        music.play(); // Play the music
    }
        
    public void putContent(JPanel panel){
        f_panel = panel;
        this.setContentPane(f_panel);
        this.revalidate(); // Refresh the window
        this.requestFocus();
        //this.repaint();
    }
    public void putContent(JScrollPane scrollPane){
        this.setContentPane(scrollPane);
        this.revalidate(); // Refresh the window
        this.requestFocus();
        //this.repaint();
    }
}
