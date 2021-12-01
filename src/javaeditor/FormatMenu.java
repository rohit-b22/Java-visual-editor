/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaeditor;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import javax.swing.undo.UndoManager;
import java.util.*;
import javax.swing.*;
import java.io.File;
import javax.swing.table.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.tree.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FormatMenu extends JMenu implements ActionListener {
    
    JMenu Colors;
    JMenuItem FontSize, foreColor, backColor;
    MainClass object;
    
    FormatMenu(String name, MainClass obj){
        
        object = obj;
        
        this.setText(name);
    
        FontSize = new JMenuItem("Font size");
        Colors = new JMenu("Colors");
        foreColor = new JMenuItem("Foreground color");
        backColor = new JMenuItem("Background color");
        Colors.add(foreColor);
        Colors.add(backColor);
                        
        this.add(FontSize);
        this.add(Colors);
        
        FontSize.addActionListener(this);
        foreColor.addActionListener(this);
        backColor.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e){
        
        String s = e.getActionCommand();
        
        if(s.equalsIgnoreCase("Font size")){
            
            try{
                String[] st = {"7", "8", "9", "10", "11", "12", "13", "14", "16", "18", "20", "24", "28", "32", "36", "48"};
                ImageIcon ic = new ImageIcon("");
                object.selectedFont= (String)JOptionPane.showInputDialog(object.frame,"Choose font size", "Font Size", JOptionPane.QUESTION_MESSAGE, ic, st,object.selectedFont);

                object.ta.setFont(new Font(object.ta.getFont().getFamily(),Font.PLAIN,Integer.parseInt(object.selectedFont)));
                object.tCompile.setFont(new Font(object.ta.getFont().getFamily(),Font.PLAIN,Integer.parseInt(object.selectedFont)));
                object.tInput.setFont(new Font(object.ta.getFont().getFamily(),Font.PLAIN,Integer.parseInt(object.selectedFont)));
                object.tOutput.setFont(new Font(object.ta.getFont().getFamily(),Font.PLAIN,Integer.parseInt(object.selectedFont)));
                                
            }catch(NumberFormatException ne){}
                      
            
        }else if(s.equalsIgnoreCase("foreground Color")){
            
            Color color = JColorChooser.showDialog(object.frame, "Choose text color", Color.black);
            object.ta.setForeground(color);
            object.tCompile.setForeground(color);
            object.tInput.setForeground(color);
            object.tOutput.setForeground(color);
            
            
        }else if(s.equalsIgnoreCase("background Color")){
                        
            Color color = JColorChooser.showDialog(object.frame, "Choose text color", Color.white);
            object.ta.setBackground(color);
            object.tCompile.setBackground(color);
            object.tInput.setBackground(color);
            object.tOutput.setBackground(color);
                        
            
        }
    }
}
