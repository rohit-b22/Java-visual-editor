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

public class SearchMenu extends JMenu implements ActionListener {
    
    JMenuItem Find, FindNext;
    MainClass object;
    
    SearchMenu(String name, MainClass obj){
        
        object = obj;
        this.setText(name);
        
        Find = new JMenuItem("Find");
        FindNext = new JMenuItem("Find Next");
        this.add(Find);
        this.add(FindNext);
        
        Find.addActionListener(this);
        FindNext.addActionListener(this);
        
        Find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK));
        FindNext.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK));
    }
    
    
    public void actionPerformed(ActionEvent e){
    
        String s = e.getActionCommand();
        
        if(s.equals("Find")){
            
            object.index = 0;
            object.str = JOptionPane.showInputDialog("Find...");
            if(object.str != null){
                String txt = object.ta.getText();
                int az = txt.indexOf(object.str, object.index);
                if(az != -1){
                    object.ta.select(az, az+object.str.length());
                    object.index = az + object.str.length();
                }else{
                    object.ta.select(-1, -1);
                }
                
            }
            
        }else if(s.equals("Find Next")){
            
            if(object.str != null){
                String txt = object.ta.getText();
                int az = txt.indexOf(object.str, object.index);
                if(az != -1){
                    object.ta.select(az, az+object.str.length());
                    object.index = az + object.str.length();
                }
            }
        }
    }
}
