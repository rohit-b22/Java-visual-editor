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

public class EditMenu extends JMenu implements ActionListener{
    
    JMenuItem Undo, Redo, Cut, Copy, Paste, SelectAll;
    MainClass object;
    
    EditMenu(String name, MainClass obj){
        
        object = obj;
        
        this.setText(name);
    
        Undo = new JMenuItem("Undo");
        Redo = new JMenuItem("Redo");
        Cut = new JMenuItem("Cut");
        Copy = new JMenuItem("Copy");
        Paste = new JMenuItem("Paste");
        SelectAll = new JMenuItem("Select all");
        
        this.add(Undo);
        this.add(Redo);
        this.add(Cut);
        this.add(Copy);
        this.add(Paste);
        this.add(SelectAll);
        
        
        Cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        Copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        Paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        SelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        Undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        Redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK));
        
        Undo.addActionListener(this);
        Redo.addActionListener(this);   
        Cut.addActionListener(this);
        Copy.addActionListener(this);
        Paste.addActionListener(this);
        SelectAll.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e){
    
        String s = e.getActionCommand();
        
        if(s.equals("Undo")){
            
            object.undo.undo();
            
        }else if(s.equals("Redo")){
            
            object.undo.redo();
            
        }else if(s.equals("Cut")){
            
            object.ta.cut();
            
        }else if(s.equals("Copy")){
            
            object.ta.copy();
            
        }else if(s.equals("Paste")){
            
            object.ta.paste();
            
        }else if(s.equals("Select All")){
            
            object.ta.selectAll();
            
        }
    }
}
