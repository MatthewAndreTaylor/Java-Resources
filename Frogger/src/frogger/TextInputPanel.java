/*****************************************************************
 * Class: TextInputPanel
 * Programmer: Matthew Taylor
 * Date: April 17, 2021
 * Program: TextInputPanel.java
 * Description: Keeps track of a text panel for a users typed response
 *****************************************************************/
package frogger;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

// Inherits from TextPanel
public class TextInputPanel extends TextPanel
{
    // Constant
    private static final int MAX_TEXT_LENGTH = 12;
    
    // Fields
    private JTextField textField;
    private String defaultText;
    
    /**
     * Constructor
     * @param labelText
     * @param defaultTextSelection 
     */
    public TextInputPanel(String labelText, String defaultTextSelection) 
    {
        // Use Text panel for menu with label
        super(labelText);
        
        // Set text field max length
        textField = new JTextField(MAX_TEXT_LENGTH);
        
        // Use default text
        defaultText = defaultTextSelection;
        textField.setText(defaultText);
        
        // Swing trickery to force the text to be all selected on start
        textField.addAncestorListener(new AncestorListener() 
        {
            //Change some methods of AncestorListener component
            public void ancestorRemoved(AncestorEvent event) {}
            public void ancestorMoved(AncestorEvent event) {}
            public void ancestorAdded(AncestorEvent event) 
            {
                // When the text feild is really added
                if (event.getSource() == textField) 
                {
                    // The focus and selection must be made at the same time
                    textField.selectAll();
                    textField.requestFocusInWindow();
                }
            }
        });
        
        // Add the text field so it can be used
        add(textField);
    }
    
    /**
     * Shows the input panel
     * @param title The title for the menu 
     */
    public void showPanel(String title)
    {
        int optionResponse;
        do
        {
            // Before showing set text back to default to remind user they need to fill it in
            textField.setText(defaultText);
            optionResponse = showOptions(title, JOptionPane.OK_CANCEL_OPTION);
        }
        while(optionResponse == JOptionPane.OK_OPTION && !isValidText());
        
        // The user quits (close / cancel option)
        if (optionResponse != JOptionPane.OK_OPTION)
        {
            textField.setText("");
        }
    }
    
    /**
     * Method Name: isValid
     * Checks if a name is valid
     * @return if the names length is valid
     */
    public boolean isValidText()
    {
        return textField.getText().length() != 0;
    }  
    
    /**
     * Method Name: getText
     * Gets the string inside the text box
     * @return a string the user has typed
     */
    public String getText()
    {
        // Return the user's name
        return textField.getText();
    }
    
    // Accessors and Mutators
    public JTextField getTextField()
    {
        return textField;
    }
    
    public void setTextField(JTextField jField)
    {
        textField = jField;
    }
    
    public String getDefaultText()
    {
        return defaultText;
    }
    
    public void setDefaultText(String text)
    {
        defaultText = text;
    }
}
