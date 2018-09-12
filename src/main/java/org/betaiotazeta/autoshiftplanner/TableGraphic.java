package org.betaiotazeta.autoshiftplanner;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

/**
 *
 * @author betaiotazeta
 */

public class TableGraphic {

    public TableGraphic(AspApp aspApp, TablePanel tablePanel) {        
        this.aspApp = aspApp;
        this.tablePanel = tablePanel;
    }
    
    public void drawTable(Graphics2D g2) {

        Table table = aspApp.getTable();
        Business business = aspApp.getBusiness();
        nR = table.getNumberOfRows();
        nC = table.getnumberOfColumns();
                
        // dimensions of the tablePanel       
        xLeftEndJPanel = tablePanel.getWidth();
        yTopEndJPanel = tablePanel.getHeight();
                      
        // self-adjusting size       
        // rectangleLength: length of the rectangle used for drawing in pixels            
        rectangleLength = (int) Math.round( (xLeftEndJPanel - (1.5)*XLEFTSTART) / nC);

        // rectangleHeight: height of the rectangle used for drawing in pixels
        rectangleHeight = (int) Math.round( (yTopEndJPanel - (1.7)*YTOPSTART) / nR);
      
                
        // Draw the filled rectangles.        
        // Offset from left-top towards left during rectangles drawing in pixel.
        int xLeft = XLEFTSTART;
        // Offset from left-top towards down during rectangles drawing in pixel.
        int yTop = YTOPSTART;        
        // Each cell is a rectangle drawn of a given color.       
        Rectangle drawnRectangle;
               
        for (int i = 0; i < nR; i++) {
            for (int j = 0; j < nC; j++) {

                if (table.getCell(i, j).isForbidden()) {

                    g2.setColor(Color.BLACK);

                    drawnRectangle = new Rectangle(xLeft, yTop, rectangleLength, rectangleHeight);
                    g2.fill(drawnRectangle);

                } else {
                    
                    if (table.getCell(i, j).isWorked()) {

                        int idEmployee = (table.getCell(i, j).getIdEmployee());

                        if (idEmployee == 1) {
                            g2.setColor(Color.RED);
                        } else if (idEmployee == 2) {
                            g2.setColor(Color.BLUE);
                        } else if (idEmployee == 3) {
                            g2.setColor(Color.GREEN);
                        } else if (idEmployee == 4) {
                            g2.setColor(Color.YELLOW);
                        } else if (idEmployee == 5) {
                            g2.setColor(Color.MAGENTA);
                        } else if (idEmployee == 6) {
                            g2.setColor(Color.PINK);
                        } else if (idEmployee == 7) {
                            g2.setColor(Color.ORANGE);
                        } else if (idEmployee == 8) {
                            g2.setColor(Color.CYAN);
                        } else if (idEmployee == 9) {
                            g2.setColor(Color.LIGHT_GRAY);
                        }

                        drawnRectangle = new Rectangle(xLeft, yTop, rectangleLength, rectangleHeight);
                        g2.fill(drawnRectangle);

                    } else {

                        g2.setColor(Color.DARK_GRAY);

                        drawnRectangle = new Rectangle(xLeft, yTop, rectangleLength, rectangleHeight);
                        g2.fill(drawnRectangle);                        
                    }
                    
                    if (table.getCell(i, j).isMandatory()) {
                        
                        g2.setColor(Color.WHITE);
                        
                        // ellipse size offset
                        int osLenght = rectangleLength/5;
                        int osHeight = rectangleHeight/5;
                        
                        Ellipse2D.Double drawnEllipse = new Ellipse2D.Double(xLeft + osLenght, yTop + osHeight, rectangleLength - 2*osLenght , rectangleHeight - 2*osHeight);
                        g2.fill(drawnEllipse); 
                    }
                }
                xLeft = xLeft + rectangleLength;
            }
            xLeft = XLEFTSTART;
            yTop = yTop + rectangleHeight;
        }    

        
        // Draw a white border between cells.
        xLeft = XLEFTSTART;
        yTop = YTOPSTART;
        g2.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < nR; i++) {
            for (int j = 0; j < nC; j++) {
                drawnRectangle = new Rectangle(xLeft, yTop, rectangleLength, rectangleHeight);
                g2.draw(drawnRectangle);
                xLeft = xLeft + rectangleLength;
            }
            xLeft = XLEFTSTART;
            yTop = yTop + rectangleHeight; 
        }        

        
        // Draw a line between the days.
        xLeft = XLEFTSTART;
        yTop = YTOPSTART;
        int employees = business.getNumberOfEmployees();
        // Variable iL stands for interleaving is the distance between two lines
        // that demarcate the days in pixels.
        int iL = rectangleHeight * employees;
        // Variable lT stands for lengthTable (only rectangles) in pixels.
        int lT = (rectangleLength * nC);
        // Variable eL stands for edgeLine: overflow of the separator line between days in pixels.
        int eL = 20;
        
        g2.setColor(Color.WHITE);
        Line2D.Double drawnLine;
        
        // Cycle for executed 8 times: 8 lines for 7 days.
        for (int i = 0; i <= 7; i++) {
            drawnLine = new Line2D.Double(xLeft - eL, yTop - 1, xLeft + lT + eL, yTop - 1);
            g2.draw(drawnLine);
            yTop = yTop + iL;
        }
        
        
        // Write the names of the days.
        xLeft = XLEFTSTART;
        yTop = YTOPSTART;
        g2.setColor(Color.BLACK);
        // Variable eD stands for edgeDay: offset string days in pixels.
        int eD = 75;
        // Variable hF stands for heightFont: average height of the font in pixels.
        // Divided by 2 to get half and lower the string of days.
        int hF = ( g2.getFontMetrics().getAscent() ) / 2;
        
        g2.drawString("Monday", xLeft - eD, yTop + (iL / 2) + hF);
        g2.drawString("Tuesday", xLeft - eD, yTop + (iL / 2) + iL + hF);
        g2.drawString("Wednesday", xLeft - eD, yTop + (iL / 2) + 2*iL + hF);
        g2.drawString("Thursday", xLeft - eD, yTop + (iL / 2) + 3*iL + hF);
        g2.drawString("Friday", xLeft - eD, yTop + (iL / 2) + 4*iL + hF);        
        g2.drawString("Saturday", xLeft - eD, yTop + (iL / 2) + 5*iL + hF);
        g2.drawString("Sunday", xLeft - eD, yTop + (iL / 2) + 6*iL + hF);
                
        
        // Writes the hours.
        xLeft = XLEFTSTART;
        yTop = YTOPSTART;
        // Variable eH stands for edge hours: how much is above the table.           
        int eH = 5;       
        // Variable nC stands for the number of columns in the table.
        // int nC = table.getnumberOfColumns();        
        // Variable sT stands for business start time.
        double sT = business.getStartTime();        
        // Variable eT stands for business end time.
        // double eT = business.getEndTime();
        
        double sTf = Math.floor(sT);
        // double eTf = Math.floor(eT);
        
        if ( sT == sTf) {
            // working time starts at full hour
            for (int i = 0; i <= (nC / 2); i++) {                
                g2.drawString( (int) (sT + i) + "", xLeft + (i * 2 * rectangleLength), yTop - eH);
            }
        }
        else {
            // working time starts at half an hour
            for (int i = 0; i <= (nC / 2); i++) {
                g2.drawString( (int) (sT + 0.5 + i) + "", xLeft + rectangleLength + (i * 2 * rectangleLength), yTop - eH);
            }
        }
    }    
        
    public int getRectangleLength() {
        return rectangleLength;
    }

    public int getRectangleHeight() {
        return rectangleHeight;
    }
    
    private AspApp aspApp;   
    private TablePanel tablePanel;
     

    // rectangle drawing start position offset
    // XLEFTSTART: offset from left-top towards left before rectangle drawing in pixel.
    public static final int XLEFTSTART = 100;

    // YTOPSTART: offset from left-top towards down before rectangle drawing in pixel.        
    public static final int YTOPSTART = 50;

    
    // report the size of the TablePanel
    // xLeftEndJPanel: pixel from left-top towards left of TablePanel (length).
    private int xLeftEndJPanel;
    
    // yTopEndJPanel: pixel from right-top towards down of TablePanel (height).
    private int yTopEndJPanel;

    
    // dimensions of the rectangle used to draw in pixels
    // rectangleLength: length of the rectangle used to draw in pixels
    private int rectangleLength = 1;

    // rectangleHeight: height of the rectangle used to draw in pixel
    private int rectangleHeight = 1;

   
    // nR: number (quantity) of rows that has the object of type Table
    // whose reference is called "table"
    private int nR = 1;
    
    // nR: number (quantity) of columns that has the object of type Table
    // whose reference is called "table"
    private int nC = 1;
}
