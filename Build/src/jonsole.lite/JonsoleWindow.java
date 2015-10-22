package jonsole.lite;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import javax.jws.soap.SOAPBinding;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.format.TextStyle;

public final class JonsoleWindow {
    public static TextArea consoleArea;
    public JonsoleWindow(){}
    public void showWindow(){
        Stage stage = new Stage();
        stage.setTitle("Jonsole Console");
        BorderPane borderPane = new BorderPane();
        TextArea textArea = new TextArea();
        VBox topContainer = new VBox();
        Menu jonsole = new Menu("Jonsole");
        MenuItem quit = new MenuItem("Quit");
        jonsole.getItems().addAll(quit);
        Menu about = new Menu("Help");
        MenuItem jonsoleAbout = new MenuItem("About");
        MenuItem jonsoleWebsite = new MenuItem("GitHub");
        about.getItems().addAll(
                jonsoleAbout,
                jonsoleWebsite
        );
        MenuBar menu = new MenuBar();
        menu.getMenus().addAll(
                jonsole,
                about
        );
        topContainer.getChildren().add(menu);
        borderPane.setTop(topContainer);
        Stage aboutStage = new Stage();
        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.hide();
                if(aboutStage.isShowing()){
                    aboutStage.hide();
                }
            }
        });
        aboutStage.setResizable(false);
        BorderPane aboutPane = new BorderPane();
        Image logo = new Image("/jonsole/lite/resources/logo.png");
        ImageView imageView = new ImageView(logo);
        aboutPane.setCenter(imageView);
        Text jonsoleText = new Text("JonsoleLite 2");
        jonsoleText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(jonsoleText);
        aboutPane.setBottom(hBox);
        Scene aboutScene = new Scene(aboutPane, 319, 299);
        aboutStage.setScene(aboutScene);
        jonsoleAbout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                aboutStage.show();
            }
        });
        jonsoleWebsite.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Desktop.isDesktopSupported())
                {
                    try {
                        Desktop.getDesktop().browse(new URI("https://github.com/GlassSources/Jonsole-Releases"));
                    } catch (IOException e) {
                        StringWriter sw = new StringWriter();
                        PrintWriter pw = new PrintWriter(sw);
                        e.printStackTrace(pw);
                        String stackTrace = sw.toString();
                        Logger logger = new Logger("JonsoleLite");
                        logger.log(Logger.Log.FAILURE, stackTrace);
                    } catch (URISyntaxException e) {
                        StringWriter sw = new StringWriter();
                        PrintWriter pw = new PrintWriter(sw);
                        e.printStackTrace(pw);
                        String stackTrace = sw.toString();
                        Logger logger = new Logger("JonsoleLite");
                        logger.log(Logger.Log.FAILURE, stackTrace);
                    }
                }
            }
        });
        consoleArea = textArea;
        textArea.setEditable(true);
        textArea.setPrefSize(1000.0, 1000.0);
        borderPane.setCenter(textArea);
        Scene scene = new Scene(borderPane, 628,299);
        stage.setResizable(true);
        stage.setScene(scene);
        stage.show();
    }
    public Logger getLogger(String loggerName){
        Logger logger = new Logger(loggerName);
        return logger;
    }
}
