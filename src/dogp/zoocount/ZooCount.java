/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dogp.zoocount;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * ZooCount is the main entry point for the application.
 *
 * @author jonas
 */
public class ZooCount extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainFrame frame = MainFrame.getInstance();
        frame.reveal();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainFrame frame = MainFrame.getInstance();
        frame.reveal();
    }
}
