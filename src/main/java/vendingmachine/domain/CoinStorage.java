package vendingmachine.domain;

import java.util.EnumMap;
import java.util.Map;

public class CoinStorage {
    private final Map<Coin, Integer> coinStorage;

    public CoinStorage(Map<Coin, Integer> coinStorage) {
        this.coinStorage = coinStorage;
    }

    public static CoinStorage fromMoney(int inputMoney) {
        validateMoney(inputMoney);
        return new CoinStorage(convertToCoins(inputMoney));
    }

    private static Map<Coin, Integer> convertToCoins(int inputMoney) {
        Map<Coin, Integer> coinStorage = initializeCointStorage();
        int remainingMoney = inputMoney;

        for (Coin coin : Coin.values()) {
            int coinCount = coin.calculateCount(remainingMoney);
            coinStorage.put(coin, coinStorage.getOrDefault(coin, 0) + coinCount);
            remainingMoney -= coin.calculateByCount(remainingMoney, coinCount);
        }

        return coinStorage;
    }

    private static Map<Coin, Integer> initializeCointStorage() {
        Map<Coin, Integer> coinStorage = new EnumMap<>(Coin.class);
        for (Coin coin : Coin.values()) {
            coinStorage.put(coin, 0);
        }
        return coinStorage;
    }

    private static void validateMoney(int inputMoney) {
        validateRange(inputMoney);
        validateUnit(inputMoney);

    }

    private static void validateUnit(int inputMoney) {
        if (!isValidUnit(inputMoney)) {
            throw new IllegalArgumentException("보유 금액은 10원 단위로 입력해야 합니다.");
        }
    }

    private static boolean isValidUnit(int inputMoney) {
        return inputMoney % 10 == 0;
    }

    private static void validateRange(int inputMoney) {
        if (!isInRange(inputMoney)) {
            throw new IllegalArgumentException("보유 금액은 10원부터 시작해야 합니다.");
        }
    }

    private static boolean isInRange(int inputMoney) {
        return inputMoney >= 10;
    }

    public Map<Coin, Integer> getCoinStorage() {
        return coinStorage;
    }
}
