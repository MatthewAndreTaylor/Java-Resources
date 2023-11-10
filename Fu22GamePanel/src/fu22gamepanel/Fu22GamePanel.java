package fu22gamepanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

@SuppressWarnings("serial")
public class Fu22GamePanel extends JPanel {
    private static final int COLS = 50;
    private static final int ROWS = 25;
    private static final int GAP = 3;
    private JTextField entryField = new JTextField(COLS);
    private JTextArea textArea = new JTextArea(ROWS, COLS);
    private String playerName;

    public Fu22GamePanel(String name) {
        this.playerName = name;
        textArea.setFocusable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        EntryAction entryAction = new EntryAction("Submit", KeyEvent.VK_S);
        entryField.setAction(entryAction);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
        bottomPanel.add(entryField);
        bottomPanel.add(new JButton(entryAction));

        setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        setLayout(new BorderLayout(GAP, GAP));
        add(scrollPane);
        add(bottomPanel, BorderLayout.PAGE_END);
    }

    private class EntryAction extends AbstractAction {
        public EntryAction(String name, int mnemonic) {
            super(name);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String text = entryField.getText();
            entryField.setText("");
            textArea.append("> " + text + "\n");
            entryField.requestFocusInWindow();
        }
    }

    private static class WelcomePanel extends JPanel {
        private JTextField playerNameField = new JTextField(10);

        public WelcomePanel() {
            int wpGap = 20;
            setBorder(BorderFactory.createEmptyBorder(wpGap, wpGap, wpGap, wpGap));
            add(new JLabel("Enter Player Name:"));
            add(playerNameField);
        }

        public String getPlayerName() {
            return playerNameField.getText();
        }
        
        public int getResponse()
        {
            return JOptionPane.showConfirmDialog(null,this, "Welcome to the Game", JOptionPane.OK_CANCEL_OPTION);
        }
    }

    public static void createAndShowGui() {
        WelcomePanel welcomePanel = new WelcomePanel();
        // Checks that a name is inputted
        while(welcomePanel.getPlayerName().length() < 1)
        {     
            // get the users input
            if (welcomePanel.getResponse() != JOptionPane.OK_OPTION) 
            {
                return;
            }
        }

        System.out.println(welcomePanel.getPlayerName().length());
        String name = welcomePanel.getPlayerName();

        Fu22GamePanel mainPanel = new Fu22GamePanel(name);

        JFrame frame = new JFrame("Game -- Player: " + name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.setLocationRelativeTo(null);


        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGui();
        });
    }
}