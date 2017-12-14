/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screenscreen;

/**
 *
 * @author SACHI
 */
public class Screenscreen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        splash Splash=new splash();
        Splash.setVisible(true);
        
        home Home=new home();
        int i;
        try{
            for(i=0;i<=100;i++){
                Thread.sleep(40);
                Splash.loading.setText(Integer.toString(i)+"%"); 
                Splash.loadingbar.setValue(i);
                if(i==100){
                   Splash.setVisible(false);
                   Home.setVisible(true);
                }
            }
        }
        catch(Exception e){
            
        }
    }
    
}
