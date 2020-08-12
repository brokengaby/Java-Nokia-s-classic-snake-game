package visual;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
public class WindowMain extends JFrame{
    
    PanelSnake panel_snake;
    
    public WindowMain(int speed_milis, int grid_size){
        //Gettin a panel
        this.panel_snake = new PanelSnake(speed_milis, grid_size);
        //Title
        this.setTitle("Java Nokia's Snake");
        //not layout
        this.setLayout(null);
        //Setting size
        int px_width = panel_snake.getWidth() + 6;
        int px_height =  panel_snake.getHeight() + 29;
        //Bounds and centering
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((screenSize.width - px_width) / 2, (screenSize.height - px_height) / 3, px_width, px_height);
        //Bounds
        this.panel_snake.setBounds(0, 0, panel_snake.getWidth(), panel_snake.getHeight());
        //Not change size
        this.setResizable(false);
        //Adding panel
        this.add(panel_snake);
        //Visible
        this.setVisible(true);
        //Close
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
}
