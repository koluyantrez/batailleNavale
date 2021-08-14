package be.yantrez;

import java.io.InputStream;
import java.io.File;

import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.scene.layout.FlowPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Hello extends Application
{
	
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		
		Class<?> clazz = this.getClass();
		
		File file = new File("C:/resources/poufsouffle.jpg");
		String localUrl = file.toURI().toURL().toString();
		Image image = new Image(localUrl, 150.0, 90.0, true, true);
		ImageView imageView = new ImageView(image);
		
		
		FlowPane root = new FlowPane();
        root.setPadding(new Insets(20));
        
        root.getChildren().addAll(imageView);
        
        Scene scene = new Scene(root, 410, 300);
		
		primaryStage.setTitle("Bataille Navale");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
		
	/*
	public void start(Stage primaryStage)
	{
		primaryStage.setTitle("Bataille Navale");
		
		BorderPane root = new BorderPane();
		Text helloText = new Text("Hello World");
		root.setCenter(helloText);
		Scene scene = new Scene(root, 250, 100);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	*/
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
}
