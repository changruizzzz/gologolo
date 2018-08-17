/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glgl.transactions;

import glgl.data.GoLoText;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import jtps.jTPS_Transaction;

/**
 *
 * @author changruizhou
 */
public class ChangeFont_Transaction implements jTPS_Transaction{
    
    Font oldFont;
    Font newFont;
    GoLoText goLoText;
    boolean ifChangeWeight;
    boolean ifChangePosture;
    
    public ChangeFont_Transaction(GoLoText initText, String family, double size, boolean changeWeight, boolean changePosture) {
        goLoText = initText;
        oldFont = ((Text)initText.getGoLoNode()).getFont();
        ifChangeWeight = changeWeight;
        ifChangePosture = changePosture;
        FontWeight tempWeight;
        FontPosture tempPosture;
        if(goLoText.isBold()) {
            if(changeWeight)
                tempWeight = FontWeight.NORMAL;
            else
                tempWeight = FontWeight.BOLD;
        } else {
            if(changeWeight)
               tempWeight = FontWeight.BOLD;
            else
                tempWeight = FontWeight.NORMAL;
        }
        if(goLoText.isItalic()) {
            if(changePosture)
                tempPosture = FontPosture.REGULAR;
            else
                tempPosture = FontPosture.ITALIC;
        } else {
            if(changePosture)
                tempPosture = FontPosture.ITALIC;
            else
                tempPosture = FontPosture.REGULAR;
        }
        newFont = Font.font(family, tempWeight, tempPosture, size);
    }
    
    @Override
    public void doTransaction() {
        ((Text)goLoText.getGoLoNode()).setFont(newFont);
        if(ifChangeWeight)
            goLoText.setIsBold(!goLoText.isBold());            
        if(ifChangePosture)
            goLoText.setIsItalic(!goLoText.isItalic());
    }

    @Override
    public void undoTransaction() {
        ((Text)goLoText.getGoLoNode()).setFont(oldFont);
        if(ifChangeWeight)
            goLoText.setIsBold(!goLoText.isBold());            
        if(ifChangePosture)
            goLoText.setIsItalic(!goLoText.isItalic());
    }
}
