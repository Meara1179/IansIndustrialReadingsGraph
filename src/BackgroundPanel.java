import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class BackgroundPanel extends JPanel
{
    private Image background;


    public BackgroundPanel(SpringLayout layout)
    {
        try
        {
            File f = new File("Ian_Logo.png");
            background = ImageIO.read(f);
        }
        catch (Exception e)
        {

        }

        this.background = background;
        setLayout(layout);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(background, 0, 0, null);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(background.getWidth(this), background.getHeight(this));
    }

}
