package com.temper.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class MyMain extends Application {

    public static void main(String[] args) {//optional
        launch(args);//use to open in command
    }

    @Override
    public void init() throws Exception {
        System.out.println("inti");
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {//start is abstract ,stage is outmost portion
        System.out.println("start");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("app_layout.fxml"));//use to connect both main and xml file
        VBox rootNode = loader.load();//it load root node of application that is pane

        MenuBar menuBar=createMenu();
        rootNode.getChildren().add(0,menuBar);
        Scene scene = new Scene(rootNode, 300, 275);//within stage

        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello JAVAFX");
        primaryStage.show();//use to show(visible)
    }
    private MenuBar createMenu()
    {
        //file menu
        Menu fileMenu =new Menu("File");
        MenuItem newMenuItem=new MenuItem("New");
        newMenuItem.setOnAction(event -> System.out.println("New menu item clicked"));
        SeparatorMenuItem separatorMenuItem=new SeparatorMenuItem();
        MenuItem quitMenuItem=new MenuItem("Quit");
        quitMenuItem.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });
        fileMenu.getItems().addAll(newMenuItem,separatorMenuItem,quitMenuItem);
        Menu helpMenu=new Menu("Help");
        MenuItem aboutapp=new MenuItem("About");
        aboutapp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                aboutapp();
            }
        });
        helpMenu.getItems().addAll(aboutapp);
        // menubar
        MenuBar menuBar=new MenuBar();
        menuBar.getMenus().addAll(fileMenu,helpMenu);
        return menuBar;
    }

    private void aboutapp() {
        Alert alertdialog=new Alert(Alert.AlertType.INFORMATION);
        alertdialog.setTitle("My first app");
        alertdialog.setHeaderText("Learning java");
        alertdialog.setContentText("I am just a beginner");
        ButtonType yesbtn=new ButtonType("Yes");
        ButtonType nobtn=new ButtonType("NO");
        alertdialog.getButtonTypes().setAll(yesbtn,nobtn);
        Optional<ButtonType>clickbtn= alertdialog.showAndWait();
        if(clickbtn.isPresent() && clickbtn.get()==yesbtn)
        {
            System.out.println("Yes is clicked");
        }
        if(clickbtn.isPresent() && clickbtn.get()==nobtn)
        {
            System.out.println("No is cliclked");
        }
    }

    @Override
    public void stop() throws Exception {
        System.out.println("stop");
        super.stop();
    }
}