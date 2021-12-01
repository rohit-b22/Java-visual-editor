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

public class MainClass {
    
    final JInternalFrame iframe;
    JTextArea ta; 
    final JTextArea tCompile, tInput, tOutput;
    JPanel panel1, panel2, panel3;
    JTabbedPane tp;
    JFrame frame;
    UndoManager undo;
    ImageIcon icon;
    String selectedFont = "12";
    String absFile="";
    int index = 0;
    String str="";
        
    MainClass(){
        
        
        
        frame = new JFrame();
        frame.setTitle("Untitled");
        frame.setBounds(100, 100, 600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        iframe = new JInternalFrame();
        frame.add(iframe);
        
        JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        sp.setDividerSize(10);
        sp.setDividerLocation(250);
        sp.setOneTouchExpandable(true);
        frame.add(sp);
        
        ta = new JTextArea();
        ta.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        
        JScrollPane scrollpane = new JScrollPane(ta);
        scrollpane.setBorder(BorderFactory.createEmptyBorder());
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setTopComponent(scrollpane);
        
        tp = new JTabbedPane();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel2.setLayout(new BorderLayout());
        panel3.setLayout(new BorderLayout());
        
        tCompile = new JTextArea();
        tInput = new JTextArea();
        tOutput = new JTextArea();
        
        panel1.add(tCompile);
        panel2.add(tInput);
        panel3.add(tOutput);
        
        tp.add("  Compile tab  ", panel1);
        tp.add("   Input tab   ", panel2);
        tp.add("  Output tab   ", panel3);
        sp.setBottomComponent(tp);
        
        JMenuBar menubar = new JMenuBar();
        
        FileMenu File = new FileMenu("File", this);
        EditMenu Edit = new EditMenu("Edit", this);
        SearchMenu Search = new SearchMenu("Search", this);
        FormatMenu Format = new FormatMenu("Format", this);
        RunMenu Run = new RunMenu("Run", this);
        
        menubar.add(File);
        menubar.add(Edit);
        menubar.add(Search);
        menubar.add(Format);
        menubar.add(Run);
        frame.setJMenuBar(menubar);
        
        undo = new UndoManager();
        ta.getDocument().addUndoableEditListener(undo);
        
        frame.setVisible(true);
        frame.revalidate();
        
    }
    
    public static void main(String[] args) throws Exception{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new MainClass();
    }
}
