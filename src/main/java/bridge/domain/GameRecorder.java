package bridge.domain;

import bridge.constant.GameStatus;

public class GameRecorder {

    private final int bridgeSize;

    private int position = 0;
    private int retryCount = 0;
    private GameStatus gameStatus;


    public GameRecorder(int bridgeSize) {
        this.bridgeSize = bridgeSize;
        this.gameStatus = GameStatus.PROGRESS;
    }

    public void movePosition() {
        position++;
    }

    public void checkProgress(boolean canMoving) {
        gameStatus = GameStatus.checkStatus(canMoving, bridgeSize, position);
    }

    public void checkGameStatus(String restartCommand) {
        gameStatus = GameStatus.checkStatus(restartCommand);
        if (gameStatus == GameStatus.RESTART) {
            initGame();
        }
    }

    private void initGame() {
        position = 0;
        retryCount++;
        gameStatus = GameStatus.init();
    }

    public int getPosition() {
        return position;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public boolean isProgress() {
        return gameStatus.isProgress();
    }

    public boolean isNotEnd() {
        return gameStatus.isNotEnd();
    }

    public boolean isFailed() {
        return gameStatus.isFailed();
    }
}
