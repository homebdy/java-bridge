package bridge.service;

import bridge.model.AnswerTable;
import bridge.model.Bridge;
import bridge.model.BridgeComparison;
import bridge.model.GameStatus;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private final GameStatus gameStatus;
    private final AnswerTable answerTable;
    private final BridgeComparison bridgeComparison;

    public BridgeGame(Bridge bridge, GameStatus gameStatus, AnswerTable answerTable) {
        this.gameStatus = gameStatus;
        this.answerTable = answerTable;
        this.bridgeComparison = new BridgeComparison(bridge);
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public boolean move(String moving) {
        int position = gameStatus.getPosition();
        boolean isCorrect = bridgeComparison.checkMoving(moving, position);
        answerTable.setAnswerTable(moving, isCorrect);
        gameStatus.movePosition();
        return isCorrect;
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        gameStatus.increaseRetryCount();
        gameStatus.clear();
        answerTable.clear();
    }
}
