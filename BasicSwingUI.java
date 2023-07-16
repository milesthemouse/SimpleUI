import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class BasicSwingUI {
    private static boolean isDarkMode = false; // Indicates the current mode (light or dark)

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Basic Swing UI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Create a button labeled "Open Notepad"
        JButton openNotepadButton = new JButton("Open Notepad");

        // Create a button labeled "Open Chrome"
        JButton openChromeButton = new JButton("Open Chrome");

        // Create a button labeled "Exit"
        JButton exitButton = new JButton("Exit");

        // Create a button labeled "Reload"
        JButton reloadButton = new JButton("Reload");

        // Create a button labeled "CMD"
        JButton cmdButton = new JButton("CMD");

        // Create a button labeled "Reset Computer"
        JButton resetButton = new JButton("Reset Computer");

        // Create a button labeled "Get IP Address"
        JButton ipAddressButton = new JButton("Get IP Address");

        // Create a button labeled "Dark Mode"
        JButton darkModeButton = new JButton("\u263D"); // Unicode for ☽

        // Create a button labeled "Email"
        JButton emailButton = new JButton("Email");

        // Create a panel for the side buttons
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.add(openNotepadButton);
        sidePanel.add(openChromeButton);
        sidePanel.add(exitButton);
        sidePanel.add(reloadButton);
        sidePanel.add(cmdButton);
        sidePanel.add(resetButton);
        sidePanel.add(ipAddressButton);
        sidePanel.add(emailButton);

        // Set the layout manager of the frame's content pane to GridLayout
        frame.getContentPane().setLayout(new GridLayout(1, 2));

        // Add the side panel to the left of the frame
        frame.getContentPane().add(sidePanel);

        // Apply light mode styling initially
        applyLightModeStyle(frame, sidePanel);

        // Add an action listener to the exit button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Add an action listener to the reload button
        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                main(null);
            }
        });

        // Add an action listener to the open notepad button
        openNotepadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().open(new java.io.File("C:\\Windows\\System32\\notepad.exe"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Add an action listener to the open chrome button
        openChromeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().open(new java.io.File("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Add an action listener to the cmd button
        cmdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Runtime.getRuntime().exec("cmd.exe /c start cmd.exe");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Add an action listener to the reset computer button
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(frame, "Are you sure you want to reset the computer?", "Reset Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    try {
                        Runtime.getRuntime().exec("shutdown -r -t 0");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // Add an action listener to the get IP address button
        ipAddressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String ipAddress = InetAddress.getLocalHost().getHostAddress();
                    JOptionPane.showMessageDialog(frame, "Your IP address is: " + ipAddress);
                } catch (UnknownHostException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Add an action listener to the dark mode button
        darkModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isDarkMode = !isDarkMode; // Toggle the mode

                if (isDarkMode) {
                    applyDarkModeStyle(frame, sidePanel);
                } else {
                    applyLightModeStyle(frame, sidePanel);
                }
            }
        });

        // Add an action listener to the email button
        emailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Email: sparmal184@gmail.com");
            }
        });

        // Set the frame visible
        frame.setVisible(true);
    }

    // Apply the light mode styling to the GUI
    private static void applyLightModeStyle(JFrame frame, JPanel sidePanel) {
        frame.getContentPane().setBackground(Color.WHITE);
        frame.getContentPane().setForeground(Color.BLACK);
        sidePanel.setBackground(Color.WHITE);
        sidePanel.setForeground(Color.BLACK);
        updateComponentTreeColor(sidePanel, Color.WHITE, Color.BLACK);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    // Apply the dark mode styling to the GUI
    private static void applyDarkModeStyle(JFrame frame, JPanel sidePanel) {
        frame.getContentPane().setBackground(Color.BLACK);
        frame.getContentPane().setForeground(Color.WHITE);
        sidePanel.setBackground(Color.BLACK);
        sidePanel.setForeground(Color.WHITE);
        updateComponentTreeColor(sidePanel, Color.BLACK, Color.WHITE);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    // Recursively update the background and foreground colors of all components in the panel
    private static void updateComponentTreeColor(Component component, Color background, Color foreground) {
        component.setBackground(background);
        component.setForeground(foreground);

        if (component instanceof Container) {
            Component[] components = ((Container) component).getComponents();
            for (Component child : components) {
                updateComponentTreeColor(child, background, foreground);
            }
        }
    }
}
