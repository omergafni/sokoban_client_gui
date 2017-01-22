package view;
	
import controller.SokobanController;
import controller.server.CLI;
import controller.server.MyServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.MyModel;


public class Run extends Application {
	
	private FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
	
	@Override
	public void start(Stage primaryStage) throws Exception {

			BorderPane root = fxmlLoader.load();
			
			MyView sokoView = fxmlLoader.getController();
			MyModel sokoModel = new MyModel();
			SokobanController sokoController = new SokobanController(sokoModel,sokoView);

			sokoView.addObserver(sokoController);
			sokoModel.addObserver(sokoController);
			sokoController.getController().start();
			
			primaryStage.setScene(new Scene(root,400,400));
			primaryStage.show();
			
	}
	
	
	public static void main(String[] args) {
		

		if(args.length == 2){
			if(args[0].equals("-server")) {

				// Setting the server 
				MyModel sokoModel = new MyModel();
				SokobanController sokoController = new SokobanController(sokoModel,new MyServer(Integer.parseInt(args[1]),new CLI()));
				((CLI)(sokoController.getServer()).getCH()).addObserver(sokoController);
				sokoModel.addObserver(sokoController);

			} 
		} 
		
		else if (args.length == 0) launch(args);
			
		
		
		
		
	}
}
