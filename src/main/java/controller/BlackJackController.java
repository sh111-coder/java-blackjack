package controller;

import java.util.List;
import java.util.Map;

import domain.AdditionalDrawStatus;
import domain.BlackJackGame;
import domain.participant.Player;
import domain.participant.Players;
import util.Constants;
import view.InputView;
import view.OutputView;
import view.ResultView;

public class BlackJackController {

    public void run() {
        BlackJackGame blackJackGame = generateBlackJackGame();
        printParticipantInitCardsStep(blackJackGame, blackJackGame.getPlayerNames());
        playerDrawCardStep(blackJackGame);
        dealerDrawCardStep(blackJackGame);
        printParticipantFinalCardsStep(blackJackGame, blackJackGame.getPlayerNames());
        printFinalFightResultStep(blackJackGame);
    }

    private BlackJackGame generateBlackJackGame() {
        List<String> playerNames = initPlayerNames();
        return new BlackJackGame(playerNames);
    }

    private List<String> initPlayerNames() {
        try {
            OutputView.printInputPlayerNameMessage();
            List<String> playerNames = InputView.inputPlayerNames();
            return playerNames;
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            return initPlayerNames();
        }
    }

    private void printParticipantInitCardsStep(BlackJackGame blackJackGame, List<String> playerNames) {
        ResultView.printInitMessage(playerNames);
        ResultView.printParticipantResult(Constants.DEALER_NAME, blackJackGame.findCardNamesByParticipantName(Constants.DEALER_NAME));
        for (String playerName : playerNames) {
            List<String> findCardNames = blackJackGame.findCardNamesByParticipantName(playerName);
            ResultView.printParticipantResult(playerName, findCardNames);
        }
    }

    public void playerDrawCardStep(BlackJackGame blackJackGame) {
        Players players = blackJackGame.getPlayers();
        for (Player player : players.getPlayers()) {
            drawCardOrPass(blackJackGame, player);
        }
    }

    private void drawCardOrPass(BlackJackGame blackJackGame, Player player) {
        AdditionalDrawStatus additionalDrawStatus = AdditionalDrawStatus.DRAW;
        while (AdditionalDrawStatus.isDrawable(additionalDrawStatus) && blackJackGame.canPlayerDrawCard(player)) {
            String playerName = player.getName();
            OutputView.printInputReceiveYesOrNotMessage(playerName);
            String receiveOrNot = InputView.inputReceiveOrNot();
            additionalDrawStatus = blackJackGame.distributePlayerCardOrPass(player, receiveOrNot);
            ResultView.printParticipantResult(playerName, blackJackGame.findCardNamesByParticipantName(playerName));
        }
    }

    private void dealerDrawCardStep(BlackJackGame blackJackGame) {
        while (blackJackGame.canDealerDrawCard()) {
            OutputView.printDealerReceivedMessage();
            blackJackGame.distributeDealerCard();
        }
    }

    private void printParticipantFinalCardsStep(BlackJackGame blackJackGame, List<String> playerNames) {
        ResultView.printParticipantFinalResult(Constants.DEALER_NAME, blackJackGame.findCardNamesByParticipantName(Constants.DEALER_NAME), blackJackGame.getDealerCardValueSum());
        for (String playerName : playerNames) {
            List<String> findCardNames = blackJackGame.findCardNamesByParticipantName(playerName);
            ResultView.printParticipantFinalResult(playerName, findCardNames, blackJackGame.findPlayerCardValueSumByPlayerName(playerName));
        }
    }

    private void printFinalFightResultStep(BlackJackGame blackJackGame) {
        Map<String, String> resultByPlayerName = blackJackGame.calculateResult();
        ResultView.printFinalFightResult(resultByPlayerName);
    }
}
