package Forms;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class TextPlaceHolder extends JLabel implements FocusListener, DocumentListener {

    public enum Show {
        ALWAYS, FOCUS_GAINED, FOCUS_LOST;
    }

    private JTextComponent component;
    private Document document;

    private Show show;
    private boolean showPromptOnce;
    private int focusLost;

    public TextPlaceHolder(String text, JTextComponent component) {
        this(text, component, Show.ALWAYS);
    }

    public TextPlaceHolder(String text, JTextComponent component, Show show) {
        this.component = component;
        setShow(show);
        document = component.getDocument();

        setText(text);
        setFont(component.getFont());

        // setForeground(component.getForeground());
        setForeground(Color.gray);
        // setBorder(new EmptyBorder(component.getInsets()));
        setHorizontalAlignment(JLabel.LEADING);

        component.addFocusListener(this);
        document.addDocumentListener(this);

        component.setLayout(new BorderLayout());
        component.add(this);
        checkForPrompt();
    }

    /**
     * Convenience method to change the alpha value of the current foreground Color
     * to the specifice value.
     *
     * @param alpha value in the range of 0 - 1.0.
     */
    public void changeAlpha(float alpha) {
        changeAlpha((int) (alpha * 255));
    }

    /**
     * Convenience method to change the alpha value of the current foreground Color
     * to the specifice value.
     *
     * @param alpha value in the range of 0 - 255.
     */
    public void changeAlpha(int alpha) {
        alpha = alpha > 255 ? 255 : alpha < 0 ? 0 : alpha;

        Color foreground = getForeground();
        int red = foreground.getRed();
        int green = foreground.getGreen();
        int blue = foreground.getBlue();

        Color withAlpha = new Color(red, green, blue, alpha);
        super.setForeground(withAlpha);
    }

    /**
     *
     * @param style value representing the the new style of the Font.
     */
    public void changeStyle(int style) {
        setFont(getFont().deriveFont(style));
    }

    /**
     * Get the Show property
     *
     * @return the Show property.
     */
    public Show getShow() {
        return show;
    }

    /**
     *
     * @param show a valid Show enum
     */
    public void setShow(Show show) {
        this.show = show;
    }

    /**
     * Get the showPromptOnce property
     *
     * @return the showPromptOnce property.
     */
    public boolean getShowPromptOnce() {
        return showPromptOnce;
    }

    /**
     * 
     * @param showPromptOnce when true the prompt will only be shown once, otherwise
     *                       it will be shown repeatedly.
     */
    public void setShowPromptOnce(boolean showPromptOnce) {
        this.showPromptOnce = showPromptOnce;
    }

    /**
     * Check whether the prompt should be visible or not. The visibility will change
     * on updates to the Document and on focus changes.
     */
    private void checkForPrompt() {
        // Text has been entered, remove the prompt

        if (document.getLength() > 0) {
            setVisible(false);
            return;
        }

        // Prompt has already been shown once, remove it

        if (showPromptOnce && focusLost > 0) {
            setVisible(false);
            return;
        }

        // Check the Show property and component focus to determine if the
        // prompt should be displayed.

        if (component.hasFocus()) {
            if (show == Show.ALWAYS || show == Show.FOCUS_GAINED)
                setVisible(true);
            else
                setVisible(false);
        } else {
            if (show == Show.ALWAYS || show == Show.FOCUS_LOST)
                setVisible(true);
            else
                setVisible(false);
        }
    }

    // Implement FocusListener

    public void focusGained(FocusEvent e) {
        checkForPrompt();
    }

    public void focusLost(FocusEvent e) {
        focusLost++;
        checkForPrompt();
    }

    // Implement DocumentListener

    public void insertUpdate(DocumentEvent e) {
        checkForPrompt();
    }

    public void removeUpdate(DocumentEvent e) {
        checkForPrompt();
    }

    public void changedUpdate(DocumentEvent e) {
    }
}