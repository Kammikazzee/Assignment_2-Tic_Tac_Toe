package tictactoe.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.text.html.ListView;

public class ScoreModel {

    private ObservableList<String> winners;

    public ScoreModel() {
        winners = FXCollections.observableArrayList();
    }

    public ObservableList<String> getWinners() {
        return winners;
    }

    public void setNextWinner(String winner) {
        winners = FXCollections.observableArrayList("Dada");
        System.out.println(winners);
    }
}