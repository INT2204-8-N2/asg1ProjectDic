import javax.swing.*;
import javax.swing.plaf.DesktopIconUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class main{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                kFrame kframe = new kFrame("kProjectDic application");
                kframe.setSize(800, 600);
                kframe.setLocationRelativeTo(null);
                kframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                kframe.setVisible(true);
                kframe.setResizable(false);
            }
        });
    }
}
