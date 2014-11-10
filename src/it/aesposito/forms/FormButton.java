/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.forms;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Antonio
 */
public class FormButton {
    private JButton button;
    private String label;
    private ImageIcon icon;
    private ActionListener actions;
    private Dimension size;
    private FormModes[] modesEnabled;

    public FormButton(String label, ImageIcon icon, ActionListener actions, Dimension size, FormModes[] modesEnabled){
        this.label = label;
        this.icon = icon;
        this.actions = actions;
        this.size = size;
        this.modesEnabled = modesEnabled;
    }
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public ActionListener getActions() {
        return actions;
    }

    public void setActions(ActionListener actions) {
        this.actions = actions;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }
    
    public FormModes[] getModesEnabled() {
        return modesEnabled;
    }

    public void setModesEnabled(FormModes[] modesEnabled) {
        this.modesEnabled = modesEnabled;
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }
    
}
