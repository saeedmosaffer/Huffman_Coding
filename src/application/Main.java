/*
 * Author: Saeed Mosaffer
 * ID No. 1202254
 * */
package application;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		Pane pane = new Pane();
		
		Label label=new Label("Compress & Decompress file");
		label.setFont(new Font(20));
		pane.getChildren().add(label);
		label.setTranslateX(160);
		label.setTranslateY(10);
		
		Image image10 = new Image("https://img.icons8.com/pastel-glyph/512/archive--v3.png");
		ImageView imageView10 = new ImageView();
		imageView10.setImage(image10);
		Button compress = new Button("Compress", imageView10);
		compress.setStyle("-fx-background-color:transparent;");
		imageView10.setFitHeight(40);
		imageView10.setFitWidth(40);
		pane.getChildren().add(compress);
		compress.setTranslateX(70);
		compress.setTranslateY(50);
		
		Image image101 = new Image("https://img.icons8.com/ios-filled/512/zip.png");
		ImageView imageView101 = new ImageView();
		imageView101.setImage(image101);
		Button decompress = new Button("Decompress", imageView101);
		decompress.setStyle("-fx-background-color:transparent;");
		imageView101.setFitHeight(40);
		imageView101.setFitWidth(40);
		pane.getChildren().add(decompress);
		decompress.setTranslateX(330);
		decompress.setTranslateY(50);
		
		DropShadow shadow5 = new DropShadow();

		compress.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eh) -> {
			compress.setEffect(shadow5);
		});

		compress.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent ej) -> {
			compress.setEffect(null);
		});
		
		decompress.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eh) -> {
			decompress.setEffect(shadow5);
		});

		decompress.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent ej) -> {
			decompress.setEffect(null);
		});

		TextArea ta = new TextArea();
		ta.setPrefSize(440, 300);
		pane.getChildren().add(ta);
		ta.setTranslateX(70);
		ta.setTranslateY(120);
		ta.setFont(new Font(15));

		compress.setOnAction(e -> {
			FileChooser fc = new FileChooser();
			File file = fc.showOpenDialog(primaryStage);
			try {
				Huffman.compress(file);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			// Calculate percentage of compression
		    double originalSize = file.length();
		    double compressedSize = new File(Huffman.outFileName).length();
		    double compressionPercentage = ((originalSize - compressedSize) / originalSize) * 100;


			Label l = new Label("Compression is done");
			l.setFont(new Font(15));
			pane.getChildren().add(l);
			l.setTranslateX(80);
			l.setTranslateY(440);
			
			Label l2 = new Label("Compression " + String.format("%.2f", compressionPercentage) + "%");
			l2.setFont(new Font(15));
			pane.getChildren().add(l2);
			l2.setTranslateX(80);
			l2.setTranslateY(470);

			ImageView i = new ImageView("https://img.icons8.com/glyph-neue/512/checkmark.png");
			i.setFitHeight(20);
			i.setFitWidth(20);
			pane.getChildren().add(i);
			i.setTranslateX(222);
			i.setTranslateY(440);
			
			Image image102 = new Image("https://img.icons8.com/external-goofy-flat-kerismaker/512/external-Statistic-marketing-goofy-flat-kerismaker.png");
			ImageView imageView102 = new ImageView();
			imageView102.setImage(image102);
			Button statistics = new Button("Show statistics", imageView102);
			statistics.setStyle("-fx-background-color:transparent;");
			imageView102.setFitHeight(40);
			imageView102.setFitWidth(40);
			pane.getChildren().add(statistics);
			statistics.setTranslateX(250);
			statistics.setTranslateY(430);
			
			statistics.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent eh) -> {
				statistics.setEffect(shadow5);
			});

			statistics.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent ej) -> {
				statistics.setEffect(null);
			});

			statistics.setOnAction(s -> {
				ta.appendText("File path: " + file.getPath() + "\nCompressed file path: " + Huffman.outFileName
						+ "\n\nASCII\tCharacter\t\tFrequency\tHuffCode\n");
				for (int k = 0; k < Huffman.huffCodeArray.length; k++) {
					if((int)Huffman.huffCodeArray[k].character==10 || (int)Huffman.huffCodeArray[k].character==9)
						continue;
					ta.appendText(String.valueOf((int) Huffman.huffCodeArray[k].character) + "\t\t  "
							+ Huffman.huffCodeArray[k].character + "\t\t\t"
							+ String.valueOf(Huffman.huffCodeArray[k].counter) + "\t\t\t"
							+ Huffman.huffCodeArray[k].huffCode+"\n");
				}
			});

		});
		
		decompress.setOnAction(e->{
			FileChooser fc = new FileChooser();
			File file = fc.showOpenDialog(primaryStage);
			Huffman.deCompress(file);
			Label l = new Label("Decompression is done");
			l.setFont(new Font(15));
			pane.getChildren().add(l);
			l.setTranslateX(80);
			l.setTranslateY(440);

			ImageView i = new ImageView("https://img.icons8.com/glyph-neue/512/checkmark.png");
			i.setFitHeight(20);
			i.setFitWidth(20);
			pane.getChildren().add(i);
			i.setTranslateX(235);
			i.setTranslateY(440);
		});

		Scene scene = new Scene(pane, 600, 500);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
