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
import javax.swing.table.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.tree.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javaeditor.FileMenu;
//import javaeditor.*;

/**
 *
 * @author BHARAT BARNWAL
 */
public class FileMenu extends JMenu implements ActionListener{
    
    JMenuItem New, Open, Save, Close, Quit;
    MainClass object;
    
    FileMenu(String name, MainClass obj){
        
        object = obj;
        
        this.setText(name);
        
        New = new JMenuItem("New");
        Open = new JMenuItem("Open");
        Save = new JMenuItem("Save");
        Close = new JMenuItem("Close");
        Quit = new JMenuItem("Quit");
        
        
        this.add(New);
        this.add(Open);
        this.add(Save);
        this.add(Close);
        this.add(Quit);
        
        New.addActionListener(this);
        Open.addActionListener(this);
        Save.addActionListener(this);
        Close.addActionListener(this);
        Quit.addActionListener(this);
        

        New.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        Open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        Save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        Quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));
    }
    
    public void actionPerformed(ActionEvent e){
        
        String s = e.getActionCommand();
        
        if(s.equals("New")){
            
            object.tCompile.setText("");
            object.tOutput.setText("");
            
            object.frame.setTitle("Untitled");
            object.ta.setText(null);
            
        }else if(s.equals("Open")){
            
            object.tCompile.setText("");
            object.tOutput.setText("");
            
            JFileChooser filechooser = new JFileChooser();
            FileNameExtensionFilter javaFilter = new FileNameExtensionFilter("java files only(.java)", "java");
            filechooser.setAcceptAllFileFilterUsed(false);
            filechooser.addChoosableFileFilter(javaFilter);
            
            int action = filechooser.showOpenDialog(null);
            
            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }else{
                
                try{
                BufferedReader reader = new BufferedReader(new FileReader(filechooser.getSelectedFile()));
                object.ta.read(reader, null);
                
                
                object.absFile = filechooser.getSelectedFile().getAbsolutePath();
                object.frame.setTitle(object.absFile);
                int start = object.frame.getTitle().lastIndexOf('\\');
                String r = object.frame.getTitle().substring(start+1,object.frame.getTitle().length());
                object.frame.setTitle(r);
//                System.out.println(absFile);
                
//                int start = absFile.lastIndexOf('\\');
//                String r = frame.getTitle().substring(start+1,absFile.length());
//                frame.setTitle(r);
//                    System.out.println(absFile);
                
                
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }
            
            
        }else if(s.equals("Save")){
            
            object.tCompile.setText("");
            object.tOutput.setText("");
            
            JFileChooser filechooser = new JFileChooser();
            FileNameExtensionFilter javaFilter = new FileNameExtensionFilter("java files only(.java)", "java");
            filechooser.setAcceptAllFileFilterUsed(false);
            filechooser.addChoosableFileFilter(javaFilter);
            
            int action = filechooser.showSaveDialog(null);
            
            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }else{
                String filename = filechooser.getSelectedFile().getAbsolutePath();
                if(!filename.contains(".java")){
                    filename = filename + ".java";
                }
                
                try{
                BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
                object.ta.write(writer);
                }catch(IOException ex){
                    ex.printStackTrace();
                }
                
                object.absFile = filechooser.getSelectedFile().getAbsolutePath();
                object.absFile = object.absFile + ".java";
                
                
                object.absFile = filechooser.getSelectedFile().getAbsolutePath();
                object.frame.setTitle(object.absFile);
                int start = object.frame.getTitle().lastIndexOf('\\');
                String r = object.frame.getTitle().substring(start+1,object.frame.getTitle().length());
                object.frame.setTitle(r + ".java");
                object.absFile = object.absFile + ".java";
//                System.out.println(absFile);
                
//                int start = absFile.lastIndexOf('\\');
//                String r = frame.getTitle().substring(start+1,absFile.length());
//                frame.setTitle(r);
            }
                        
            
        }else if(s.equals("Close")){
            
        object.frame.setTitle("Untitled");
        object.ta.setText("");
        object.ta.requestFocus();
            
        }else if(s.equals("Quit")){
            
            System.exit(0);
            
        }
    }
}
