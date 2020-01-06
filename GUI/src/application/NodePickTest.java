package application;

import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;

import javafx.scene.layout.Pane;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class NodePickTest extends Application {
	
	Rectangle2D screen;
	Pane pane;
	Scene scene;
	
	@Override
	public void start(Stage stage) {
		screen = Screen.getPrimary().getVisualBounds();
		pane = new Pane();
		scene = new Scene(pane, screen.getWidth(), screen.getHeight());
		
		Circle circle = new Circle(screen.getWidth()*0.5, screen.getHeight()*0.5, 100);
		circle.setFill(Color.RED);
		
		Circle zUpCircle = new Circle(screen.getWidth()*0.5, screen.getHeight()*0.5, 50);
		zUpCircle.setFill(Color.BLUE);
		
		Label label = new Label("Object toString will appear here.");
		
		circle.setOnMousePressed(e -> {
			label.setText((pick(circle, e.getSceneX(), e.getSceneY())).toString());
		});
		
		pane.getChildren().addAll(label, zUpCircle, circle);
		stage.setScene(scene);
		stage.setTitle("Node Picking Test");
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	//sceneX is mouse event getX(), sceneY is mouse event getY()
	public static Node pick(Node node, double sceneX, double sceneY) {
		Point2D point = node.sceneToLocal(sceneX, sceneY, true);
		
		//If 2D point is not in Node, we did not pick the node, therefore return null (undesired)
		if(!node.contains(point)) {
			return null;
		}
		
		if(node instanceof Parent) {
			Node bestMatchingChild = null;
			List<Node> children = ((Parent)node).getChildrenUnmodifiable();
			
			for (int i = children.size() - 1; i >= 0; i--) {
				Node child = children.get(i);
				point = child.sceneToLocal(sceneX, sceneY, true);
				
				if(child.isVisible() && !child.isMouseTransparent() && child.contains(point)) {
					bestMatchingChild = child;
					break;
				}
			}
			
			if(bestMatchingChild != null) {
				return pick(bestMatchingChild, sceneX, sceneY);
			}
		}
		
		return node;
	}
}
