

public class Game {

    final int FINAL_WIDTH = 600;
    final int FINAL_HEIGHT = 400;

    public enum STATE{
        Menu,
        HighScore,
    }

    public static STATE gameState = STATE.Menu;

    public Game(){
        new Hangman("CS245 Project");
    }

    public static void main(String[] args) {
        new Game();
    }
}