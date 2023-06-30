/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.samuelandrey.jenovabot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author samuel
 */
public class ExportToExcel {
    public ExportToExcel(JTable table, File file, String encoding) {
        try {
            TableModel tableModel = table.getModel();
            BufferedWriter bOut = new BufferedWriter(new FileWriter(file, false));

            // Menulis nama kolom
            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                bOut.write(tableModel.getColumnName(i));
                if (i < tableModel.getColumnCount() - 1) {
                    bOut.write(",");
                }
            }
            bOut.newLine();

            // Menulis data baris
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    bOut.write(tableModel.getValueAt(i, j).toString());
                    if (j < tableModel.getColumnCount() - 1) {
                        bOut.write(",");
                    }
                }
                bOut.newLine();
            }

            bOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
}
