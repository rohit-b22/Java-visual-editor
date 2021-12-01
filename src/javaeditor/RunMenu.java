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

public class RunMenu extends JMenu implements ActionListener {

    JMenuItem Compile, Run;
    MainClass object;

    RunMenu(String name, MainClass obj) {
        object = obj;

        this.setText(name);

        JMenuItem Compile = new JMenuItem("Compile");
        JMenuItem Run = new JMenuItem("Run");
        this.add(Compile);
        this.add(Run);

        Compile.addActionListener(this);
        Run.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        String s = e.getActionCommand();

        if (s.equalsIgnoreCase("Compile")) {

            try {

                int end = object.absFile.lastIndexOf('\\');
                String r = object.frame.getTitle();
                String name = object.absFile.substring(0, end);
                File dir = new File(name);
//                System.out.println("Directory name is :" + name);
                Runtime rt = Runtime.getRuntime();
                Process p = rt.exec("javac  " + r, null, dir);
                p.waitFor();
                InputStream ip = p.getErrorStream();
                int a = ip.available();

                String str = "", readline = "";
                str = str + "Compiling ...." + "\n\n";
                object.tCompile.setText("");
                object.tOutput.setText("");
                if (a == 0) {
                    str = str + "Good !!. There is no error in your Program\n";
                    object.tCompile.setText(str);
                } else {
                    BufferedReader br = new BufferedReader(new InputStreamReader(ip));
                    while ((readline = br.readLine()) != null) {
                        str = str + "\n" + readline;
                    }
                    str = str + "\n\n";
                    object.tCompile.setText(str);
                }

            } catch (Exception ex) {
            }

        } else if (s.equals("Run")) {

            try {

                int end = object.absFile.lastIndexOf('\\');
//                String r = frame.getTitle();
                String name = object.absFile.substring(0, end);
                File dir = new File(name);

                final String r = object.frame.getTitle().substring(0, object.frame.getTitle().length() - 5);

                Runtime t = Runtime.getRuntime();
                Process p = t.exec("java  " + r, null, dir);
//                System.out.println("File name outside is: " + r);
                p.waitFor();
                int i = p.exitValue();
//                InputStream ip =p.getInputStream();
                InputStream ie = p.getErrorStream();
//                int a =ip.available();
                int b = ie.available();

                String str = "", readline = "";

//                if(b==0 && a!=0)
                if (b == 0) {
                    object.tOutput.setText("");
                    object.tInput.setText("");

                    object.iframe.setBounds(50, 50, 300, 200);
                    object.iframe.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
                    object.iframe.setVisible(true);
                    object.iframe.add(BorderLayout.NORTH, new JLabel("Give your inputs here..."));
                    JTextArea tGetOutput = new JTextArea();
                    object.iframe.add(BorderLayout.CENTER, tGetOutput);

                    JButton btn = new JButton("Submit");
//                    btn.setSize(60, 30);
                    object.iframe.add(BorderLayout.SOUTH, btn);

//                    wait();
                    btn.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {

                            try {
                                object.iframe.setVisible(false);
                                String s = "", rline = "";

                                object.tInput.setText(tGetOutput.getText());

                                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
//                                BufferedWriter inWriter = new BufferedWriter(new OutputStreamWriter(System.out));
                                writer.flush();
                                writer.write(object.tInput.getText());

                                object.tCompile.setText(object.tCompile.getText() + "Move to the output tab to see the output...");

                                InputStream ip = p.getInputStream();
                                BufferedReader reader = new BufferedReader(new InputStreamReader(ip));

//                                System.out.println("File name inside is " + r);
                                s = s + "Output of " + r + ".java  ...... \n\n";
                                while ((rline = reader.readLine()) != null) {
                                    s = s + rline;
                                }
                                object.tOutput.setText(s);
                            } catch (Exception em) {
                            }

                        }
                    });

                } else {
                    BufferedReader br = new BufferedReader(new InputStreamReader(ie));
                    while ((readline = br.readLine()) != null) {
                        str = str + readline;
                    }
                    str = str + "\n\n";
                    object.tCompile.setText(str);
                }
            } catch (Exception eq) {
            }

        }
    }
}
