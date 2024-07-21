package dev.AP.assignment;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;

public class Setting extends Application {
    private static MediaPlayer mediaPlayer;

    private static final HashMap<String, String> MUSIC_MAP = new HashMap<>();

    static {
        MUSIC_MAP.put("Taylor & Lopker - Cruel Catholic Men", "https://files.freemusicarchive.org/storage-freemusicarchive-org/tracks/fTMwwE0mJgVyJWWKagyncxcY9jrvpc39FlAxbo5g.mp3?download=1&name=Taylor%20%26%20Lopker%20-%20Cruel%20Catholic%20Men%20%7C%20Priest%20Child%20Abuse%20Protected%20By%20Church.mp3");
        MUSIC_MAP.put("Kevin MacLeod - Enchanted Journey", "https://files.freemusicarchive.org/storage-freemusicarchive-org/music/no_curator/Kevin_MacLeod/Contemporary_Sampler/Kevin_MacLeod_-_Enchanted_Journey.mp3?download=1&name=Kevin%20MacLeod%20-%20Enchanted%20Journey.mp3");
        MUSIC_MAP.put("Mr Smith - Bridge", "https://files.freemusicarchive.org/storage-freemusicarchive-org/tracks/pYjcV6JCDIRmCEf9J8c1vgBYn4VwwWOYVfOZxUNX.mp3?download=1&name=Mr%20Smith%20-%20Bridge.mp3");
    }

    @Override
    public void start(Stage primaryStage) {
        openSettings();
    }

    public static void openSettings() {
        Stage settingsStage = new Stage();

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        ComboBox<String> musicComboBox = new ComboBox<>();
        musicComboBox.setStyle("-fx-padding: 8 15 15 15;" +
                "-fx-background-insets: 0,0 0 5 0,0 0 6 0,0 0 7 0;" +
                "-fx-background-radius: 8;" +
                "-fx-background-color:linear-gradient(from 0% 93% to 0% 100%,#a34313 0%,#903b12 100%), #9d4024, " +
                "#d86e3a, radial-gradient(center 50% 50%, radius 100%,#d96e3a,#c54e2c);" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75),4,0,0,1);" +
                "-fx-font-weight: bold;-fx-font-size: 1.1em;-fx-text-fill: white;");
        musicComboBox.getItems().addAll(MUSIC_MAP.keySet());
        musicComboBox.getSelectionModel().selectFirst();

        musicComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            String musicUrl = MUSIC_MAP.get(newVal);
            if (musicUrl != null) {
                changeMusic(musicUrl);
            }
        });

        Text brightnessLabel = new Text("Brightness: ");
        brightnessLabel.setFont(Font.font("adobe arabic", FontWeight.BOLD, FontPosture.REGULAR, 20));
        brightnessLabel.setFill(Color.WHITE);
        Slider brightnessSlider = createSlider();
        brightnessSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("Brightness: " + newVal.intValue());
        });

        Text musicVolumeLabel = new Text("Music Volume: ");
        musicVolumeLabel.setFont(Font.font("adobe arabic", FontWeight.BOLD, FontPosture.REGULAR, 20));
        musicVolumeLabel.setFill(Color.WHITE);
        Slider musicVolumeSlider = createSlider();
        musicVolumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (mediaPlayer != null) {
                mediaPlayer.setVolume(newVal.doubleValue() / 100.0);
            }
            System.out.println("Music Volume: " + newVal.intValue());
        });

        GridPane settingsGrid = new GridPane();
        settingsGrid.add(brightnessLabel, 0, 0);
        settingsGrid.add(brightnessSlider, 1, 0);
        settingsGrid.add(musicVolumeLabel, 0, 1);
        settingsGrid.add(musicVolumeSlider, 1, 1);

        Text musicSelectionLabel = new Text("Background Music: ");
        musicSelectionLabel.setFont(Font.font("adobe arabic", FontWeight.BOLD, FontPosture.REGULAR, 20));
        musicSelectionLabel.setFill(Color.WHITE);
        settingsGrid.add(musicSelectionLabel, 0, 2);
        settingsGrid.add(musicComboBox, 1, 2);

        Text settingsLabel = new Text("Settings");
        settingsLabel.setFont(Font.font("adobe arabic", FontWeight.BOLD, FontPosture.REGULAR, 40));
        settingsLabel.setFill(Color.WHITE);
        settingsLabel.setUnderline(true);

        root.getChildren().addAll(settingsLabel, settingsGrid);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.rgb(39, 38, 38), CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(root);
        settingsStage.setTitle("Settings");
        settingsStage.setScene(scene);
        settingsStage.show();
    }

    private static void changeMusic(String musicUrl) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        Media media = new Media(musicUrl);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.5); // Set initial volume to 50%
        mediaPlayer.play();
    }

    private static Slider createSlider() {
        Slider slider = new Slider(0, 100, 50);
        slider.setStyle("-fx-padding: 8 15 15 15;" +
                "-fx-background-insets: 0,0 0 5 0,0 0 6 0,0 0 7 0;" +
                "-fx-background-radius: 8;" +
                "-fx-background-color:linear-gradient(from 0% 93% to 0% 100%,#a34313 0%,#903b12 100%), #9d4024, " +
                "#d86e3a, radial-gradient(center 50% 50%, radius 100%,#d96e3a,#c54e2c);" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.75),4,0,0,1);" +
                "-fx-font-weight: bold;-fx-font-size: 1.1em;-fx-text-fill: white;");
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(10);
        return slider;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

