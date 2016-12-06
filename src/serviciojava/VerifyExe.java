/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviciojava;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.Timer;

/**
 *
 * @author DAVID PUC POOT
 */
public class VerifyExe {
    public static String pidInfo ="";
    public static void main(String args[]) throws IOException{
        String line;
            Process p =Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
        try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            while ((line = input.readLine()) != null) {
                pidInfo+=line;
            }
        }
        
            Timer t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 System.out.println("aqi");
            if(pidInfo.contains("Start8_64.exe"))
            {
                System.out.println("Esta abierto Start8");
            }else{
                System.out.println("No hacer nada");
            }
            }
        });
            t.start();
            
           
    }
}
