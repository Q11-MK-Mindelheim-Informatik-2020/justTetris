package Effects;


import Var.Var;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


public class Sounds {

    public static MediaPlayer playSound(String filename, double volume, boolean repeat) {
        return playSound(filename, volume, repeat, 0, MediaPlayer.INDEFINITE, 0);
    }

    public static MediaPlayer playSound(String filename, double volume, boolean repeat, double start, double end, double secondstart) {
        MediaPlayer mediaPlayer = new MediaPlayer(Var.sounds.get(filename));

        mediaPlayer.setOnReady(() -> {
            mediaPlayer.setStartTime(Duration.seconds(start));
            if (end == MediaPlayer.INDEFINITE) {
                mediaPlayer.setStopTime(Var.sounds.get(filename).getDuration());
            }
            else {
                mediaPlayer.setStopTime(Duration.seconds(end));
            }
            if (repeat) {
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            }
            mediaPlayer.setVolume(Var.volume*volume/100);
            /*mediaPlayer.setOnRepeat(() -> {
                System.out.println(filename + " wird wiederholt");
                System.out.println(mediaPlayer.getStartTime());
            });*/
            mediaPlayer.play();
            mediaPlayer.setStartTime(Duration.seconds(secondstart));
        });
        if (!repeat) mediaPlayer.setOnEndOfMedia(mediaPlayer::dispose);
        return mediaPlayer;
    }
}
