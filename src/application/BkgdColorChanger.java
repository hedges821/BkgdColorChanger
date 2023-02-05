package application;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;


public class BkgdColorChanger extends Application {

	@Override
	public void start(Stage primaryStage) {
		// originally build with width = 1000 and height 700
		int displayWidth = 1024;
		int displayHeight = 600;

		try {

			Group root = new Group();
			root.setId("root");
			Scene scene = new Scene(root, displayWidth, displayHeight);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			final Rectangle rect1 = new Rectangle();
			rect1.setFill(Color.WHITE);
			rect1.setWidth(displayWidth);
			rect1.setHeight(displayHeight);
			rect1.setStrokeWidth(2);
			rect1.setArcHeight(10);
			rect1.setArcWidth(10);
			


			//---------Label to right of sliders
			Label ta = new Label("Color Selector");
			ta.setStyle("-fx-text-fill: white;");
			final Rectangle rect2 = new Rectangle();
			rect2.setHeight(35);
			rect2.setWidth(210);
			rect2.setArcWidth(25);
			rect2.setArcHeight(25);
			final StackPane labelPane = new StackPane(rect2, ta);
			labelPane.setTranslateX(635);
			labelPane.setTranslateY(95);

			
			//---------labels below sliders
			int labelPaneTranslateY = 435;
			Label redLabel = new Label("Red");
			redLabel.setStyle("-fx-text-fill: white;");
			final Rectangle redLabelRect = new Rectangle();
			textLabelParams(redLabelRect);
			final StackPane redLabelPane = new StackPane(redLabelRect, redLabel);
			redLabelPane.setTranslateX(73);
			redLabelPane.setTranslateY(labelPaneTranslateY);

			Label greenLabel = new Label("Green");
			greenLabel.setStyle("-fx-text-fill: white;");
			final Rectangle greenLabelRect = new Rectangle();
			textLabelParams(greenLabelRect);
			final StackPane greenLabelPane = new StackPane(greenLabelRect, greenLabel);
			greenLabelPane.setTranslateX(175);
			greenLabelPane.setTranslateY(labelPaneTranslateY);

			Label blueLabel = new Label("Blue");
			blueLabel.setStyle("-fx-text-fill: white;");
			final Rectangle blueLabelRect = new Rectangle();
			textLabelParams(blueLabelRect);
			final StackPane blueLabelPane = new StackPane(blueLabelRect, blueLabel);
			blueLabelPane.setTranslateX(275);
			blueLabelPane.setTranslateY(labelPaneTranslateY);

			Label opacityLabel = new Label("Opacity");
			opacityLabel.setStyle("-fx-text-fill: white;");
			final Rectangle opacityLabelRect = new Rectangle();
			textLabelParams(opacityLabelRect);
			final StackPane opacityLabelPane = new StackPane(opacityLabelRect, opacityLabel);
			opacityLabelPane.setTranslateX(375);
			opacityLabelPane.setTranslateY(labelPaneTranslateY);


			//--------Sliders
			//--RED slider
			final Slider rSlider = new Slider();
			sliderParams(rSlider);

			final ProgressBar redPBar = new ProgressBar(0);
			progressBarHeightAndWidth(redPBar);

			//---SLIDER and PROGRESS BAR are stacked on top of each other
			final StackPane redSliderBox = new StackPane();
			redSliderBox.setPadding(new Insets(0, 0, 0, -88));
			redSliderBox.getChildren().addAll(redPBar, rSlider);
			

			//--GREEN slider
			final Slider gSlider = new Slider();
			sliderParams(gSlider);

			final ProgressBar greenPBar = new ProgressBar(0);
			progressBarHeightAndWidth(greenPBar);

			//---SLIDER and PROGRESS BAR are stacked on top of each other
			final StackPane greenSliderBox = new StackPane();
			greenSliderBox.setPadding(new Insets(0, 0, 0, -202));
			greenSliderBox.getChildren().addAll(greenPBar, gSlider);


			//--BLUE slider
			final Slider bSlider = new Slider();
			sliderParams(bSlider);

			final ProgressBar bluePBar = new ProgressBar(0);
			progressBarHeightAndWidth(bluePBar);

			//---SLIDER and PROGRESS BAR are stacked on top of each other
			final StackPane blueSliderBox = new StackPane();
			blueSliderBox.setPadding(new Insets(0, 0, 0, -200));
			blueSliderBox.getChildren().addAll(bluePBar, bSlider);


			//--Opacity slider
			final Slider opSlider = new Slider(0.0, 1.0, 0.0);
			opSlider.setOrientation(Orientation.VERTICAL);
			//opSlider.setShowTickLabels(true);
			//opSlider.setShowTickMarks(true);
			//opSlider.setMajorTickUnit(0.25f);
			opSlider.setPrefWidth(10);
			opSlider.setPrefHeight(300);

			final ProgressBar opacityPBar = new ProgressBar(0);
			progressBarHeightAndWidth(opacityPBar);

			//---SLIDER and PROGRESS BAR are stacked on top of each other
			final StackPane opacitySliderBox = new StackPane();
			opacitySliderBox.setPadding(new Insets(0, 0, 0, -201));
			opacitySliderBox.getChildren().addAll(opacityPBar, opSlider);


			//--GET SLIDER VALUES
			//-- red slider values
			rSlider.valueProperty().addListener(new ChangeListener<Number>() {
		        @Override
				public void changed(ObservableValue<? extends Number> ov, Number
		                old_val, Number new_val) {
		        	redPBar.setProgress(new_val.doubleValue()/255);
		        	redLabel.setText(String.valueOf((int)(rSlider.getValue())));
		        	ta.setText(String.format("%s: %d", "Red", (int)rSlider.getValue()));
		            rect1.setFill(Color.rgb((int) rSlider.getValue(),
		                    (int) gSlider.getValue(),
		                    (int) bSlider.getValue(),
		                    opSlider.getValue()));
		        }
		    });
			//-- green slider values
		    gSlider.valueProperty().addListener(new ChangeListener<Number>() {
		        @Override
				public void changed(ObservableValue<? extends Number> ov, Number
		                old_val, Number new_val) {
		        	greenPBar.setProgress(new_val.doubleValue()/255);
		        	greenLabel.setText(String.valueOf((int)(gSlider.getValue())));
		        	ta.setText(String.format("%s: %d", "Green", (int)gSlider.getValue()));
		            rect1.setFill(Color.rgb((int) rSlider.getValue(),
		                    (int) gSlider.getValue(),
		                    (int) bSlider.getValue(),
		                    opSlider.getValue()));
		        }
		    });
		    //-- blue slider values
		    bSlider.valueProperty().addListener(new ChangeListener<Number>() {
		          @Override
				public void changed(ObservableValue<? extends Number> ov, Number
		                  old_val, Number new_val) {
		        	  bluePBar.setProgress(new_val.doubleValue()/255);
		        	  blueLabel.setText(String.valueOf((int)(bSlider.getValue())));
		        	  ta.setText(String.format("%s: %d", "Blue", (int)bSlider.getValue()));
		              rect1.setFill(Color.rgb((int) rSlider.getValue(),
		                      (int) gSlider.getValue(),
		                      (int) bSlider.getValue(),
			                  opSlider.getValue()));
		          }
		    });

		  //-- opacity slider values
		    opSlider.valueProperty().addListener(new ChangeListener<Number>() {
		          @Override
				public void changed(ObservableValue<? extends Number> ov, Number
		                  old_val, Number new_val) {
		        	  opacityPBar.setProgress(new_val.doubleValue()/1);
		        	  opacityLabel.setText(String.format("%.4f", opSlider.getValue()));
		        	  ta.setText(String.format("%s: %.4f", "Opacity", opSlider.getValue()));
		              rect1.setFill(Color.rgb((int) rSlider.getValue(),
		                      (int) gSlider.getValue(),
		                      (int) bSlider.getValue(),
			                  opSlider.getValue()));
		          }
		    });

		    //--slider container
		    HBox sliderBox = new HBox(redSliderBox, greenSliderBox, blueSliderBox, opacitySliderBox);
		    sliderBox.setTranslateX(40);
		    sliderBox.setTranslateY(95); //position of slider container
		    sliderBox.setId("SliderStyle");

		    //--red toggle button
		    Button redButton = new Button("Red");
		    buttonParams(redButton, rSlider, opSlider, 79, 40);

		    //--green toggle button
		    Button greenButton = new Button("Green");
		    buttonParams(greenButton, gSlider, opSlider, 175, 40);

		    //--blue toggle button
		    Button blueButton = new Button("Blue");
		    buttonParams(blueButton, bSlider, opSlider, 275, 40);
		    
		    //--opacity toggle button
		    Button opacityButton = new Button("Opacity");
		    opacityButton.setId("labelText");
		    opacityButton.setTranslateX(371);
		    opacityButton.setTranslateY(40);
		    opacityButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) ->{
		    	opSlider.setValue((opSlider.getValue()==0) ? 1 : 0);
			});
		   


		    //--exit button
		    Button exitButton = new Button("Exit");
		    exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) ->{
				System.exit(0);
			});
		    exitButton.setId("buttonTextSize25");
		    exitButton.setTranslateX(742);
		    exitButton.setTranslateY(450);

		    //--specific colors
		    Button onlyRed = new Button("Red");
		    onlyRed.setId("BackgroundRadius25");
		    onlyRed.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) ->{
		    	onlyColor(rSlider, 255, gSlider, 0, bSlider, 0, opSlider, 1, ta, "Red");
			});

		    Button onlyOrange = new Button("Orange");
		    onlyOrange.setId("BackgroundRadius25");
		    onlyOrange.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) ->{
		    	onlyColor(rSlider, 255, gSlider, 255/2, bSlider, 0, opSlider, 1, ta, "Orange");
			});
		    Button onlyYellow = new Button("Yellow");
		    onlyYellow.setId("BackgroundRadius25");
		    onlyYellow.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) ->{
		    	onlyColor(rSlider, 255, gSlider, 255, bSlider, 0, opSlider, 1, ta, "Yellow");
			});
		    Button onlyGreen = new Button("Green");
		    onlyGreen.setId("BackgroundRadius25");
		    onlyGreen.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) ->{
		    	onlyColor(rSlider, 0, gSlider, 255, bSlider, 0, opSlider, 1, ta, "Green");
			});
		    Button onlyBlue = new Button("Blue");
		    onlyBlue.setId("BackgroundRadius25");
		    onlyBlue.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) ->{
		    	onlyColor(rSlider, 0, gSlider, 0, bSlider, 255, opSlider, 1, ta, "Blue");
			});
		    Button onlyPurple = new Button("Purple");
		    onlyPurple.setId("BackgroundRadius25");
		    onlyPurple.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) ->{
		    	onlyColor(rSlider, 255, gSlider, 0, bSlider, 255, opSlider, 1, ta, "Purple");
			});
		    Button onlyWhite = new Button("White");
		    onlyWhite.setId("BackgroundRadius25");
		    onlyWhite.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) ->{
		    	onlyColor(rSlider, 255, gSlider, 255, bSlider, 255, opSlider, 0, ta, "White");
			});
		    Button onlyGrey = new Button("Gray");
		    onlyGrey.setId("BackgroundRadius25");
		    onlyGrey.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) ->{
		    	onlyColor(rSlider, 255/2, gSlider, 255/2, bSlider, 255/2, opSlider, 0.5, ta, "Gray");
			});
		    Button onlyBlack = new Button("Black");
		    onlyBlack.setId("BackgroundRadius25");
		    onlyBlack.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) ->{
		    	onlyColor(rSlider, 0, gSlider, 0, bSlider, 0, opSlider, 1, ta, "Black");
			});
		    
		    GridPane colorContainer = new GridPane();
		    colorContainer.add(onlyRed, 0, 0);
		    colorContainer.add(onlyOrange, 1, 0);
		    colorContainer.add(onlyYellow, 2, 0);
		    colorContainer.add(onlyGreen, 0, 1);
		    colorContainer.add(onlyBlue, 1, 1);
		    colorContainer.add(onlyPurple, 2, 1);
		    colorContainer.add(onlyWhite, 0, 2);
		    colorContainer.add(onlyGrey, 1, 2);
		    colorContainer.add(onlyBlack, 2, 2);
		    colorContainer.setHgap(8);
		    colorContainer.setVgap(8);
		    colorContainer.setTranslateX(635);
		    colorContainer.setTranslateY(140);
		    colorContainer.setId("colorContainer");

		    //-----------KEYBOARD INPUT-----------------------
		    //-----------------------------------------------
		    root.setOnKeyPressed(new EventHandler<KeyEvent>()
		    {
		    	@Override
		    	public void handle(KeyEvent ke)
		    	{
		    		switch(ke.getCode()) {
		    		//Quit
		    		case L:
		    			System.exit(0);
		    			break;
		    		//red toggle
		    		case Z:
		    			rSlider.setValue((rSlider.getValue()==0) ? 255 : 0);
				    	opSlider.setValue((1));
		    			break;
		    		//green toggle
		    		case X:
		    			gSlider.setValue((gSlider.getValue()==0) ? 255 : 0);
				    	opSlider.setValue((1));
		    			break;
		    		//blue toggle
		    		case C:
		    			bSlider.setValue((bSlider.getValue()==0) ? 255 : 0);
				    	opSlider.setValue((1));
		    			break;
		    		//opacity toggle
		    		case V:
		    			opSlider.setValue((opSlider.getValue()==0) ? 1 : 0);
		    			break;
		    		//red only button
		    		case DIGIT1:
		    		case NUMPAD7:
		    			onlyColor(rSlider, 255, gSlider, 0, bSlider, 0, opSlider, 1, ta, "Red");
		    			break;
		    		//orange only button
		    		case DIGIT2:
		    		case NUMPAD8:
		    			onlyColor(rSlider, 255, gSlider, 255/2, bSlider, 0, opSlider, 1, ta, "Orange");
				    	break;
				    //yellow only button
		    		case DIGIT3:
		    		case NUMPAD9:
		    			onlyColor(rSlider, 255, gSlider, 255, bSlider, 0, opSlider, 1, ta, "Yellow");
		    			break;
		    		//green only button
		    		case DIGIT4:
		    		case NUMPAD4:
		    			onlyColor(rSlider, 0, gSlider, 255, bSlider, 0, opSlider, 1, ta, "Green");
		    			break;
		    		//blue only button
		    		case DIGIT5:
		    		case NUMPAD5:
		    			onlyColor(rSlider, 0, gSlider, 0, bSlider, 255, opSlider, 1, ta, "Blue");
		    			break;
		    		//purple only button
		    		case DIGIT6:
		    		case NUMPAD6:
		    			onlyColor(rSlider, 255, gSlider, 0, bSlider, 255, opSlider, 1, ta, "Purple");
		    			break;
		    		//white only button
		    		case DIGIT7:
		    		case NUMPAD1:
		    			onlyColor(rSlider, 255, gSlider, 255, bSlider, 255, opSlider, 0, ta, "White");
		    			break;
		    		//gray only button
		    		case DIGIT8:
		    		case NUMPAD2:
		    			onlyColor(rSlider, 255/2, gSlider, 255/2, bSlider, 255/2, opSlider, 0.5, ta, "Gray");
		    			break;
		    		//black only button
		    		case DIGIT9:
		    		case NUMPAD3:
		    			onlyColor(rSlider, 0, gSlider, 0, bSlider, 0, opSlider, 1, ta, "Black");
		    			break;
				    default:
				    	break;
		    		}
		    	}
		    });
		    //-----------------------------------------------
		    //--------END KEYBOARD INPUT---------------------


			// add to Group pane
		    root.getChildren().addAll(rect1, sliderBox, redButton, greenButton,
		    		blueButton, opacityButton, exitButton, colorContainer, labelPane,
		    		redLabelPane, greenLabelPane, blueLabelPane, opacityLabelPane);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	//---------------------------------
	public static void main(String[] args) {
		launch(args);
	}
	
	//-----------Methods-------------
	public static void textLabelParams(Rectangle box) {
		box.setHeight(40);
		box.setWidth(90);
		box.setArcWidth(25);
		box.setArcHeight(25);
	}
	
	public static void sliderParams(Slider slider) {
		slider.setMin(0);
		slider.setMax(255);
		slider.setOrientation(Orientation.VERTICAL);
		slider.setPrefWidth(30);
		slider.setPrefHeight(300);
	}
	
	public static void progressBarHeightAndWidth(ProgressBar progressBar) {
		progressBar.getTransforms().setAll(new Rotate(-90, 150, 27)); //progress bar rotated vertically
		progressBar.setPrefHeight(60);
		progressBar.setPrefWidth(300);
	}
	
	public static void buttonParams(Button button, Slider slider, 
			Slider opSlider, int translateX, int translateY) {
		button.setId("labelText");
		button.setTranslateX(translateX);
		button.setTranslateY(translateY);
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) ->{
			slider.setValue((slider.getValue()==0) ? 255 : 0);
	    	opSlider.setValue((1));
		});
	}
	
	public static void onlyColor(Slider rSlider, int red, Slider gSlider, int green, 
			Slider bSlider, int blue, Slider opSlider, double opacity, Label ta, String color) {
		rSlider.setValue(red);
    	gSlider.setValue(green);
    	bSlider.setValue(blue);
    	opSlider.setValue(opacity);
    	ta.setText(color + " selected");
	}
}
