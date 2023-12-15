package vendingmachine.domain;

import java.util.EnumMap;
import java.util.Map;

public class CoinStorage {
    private final Map<Coin, Integer> coinStorage;

    public CoinStorage(Map<Coin, Integer> coinStorage) {
        this.coinStorage = coinStorage;
    }

    private static CoinStorage from(Map<Coin, Integer> coinStorage) {
        return new CoinStorage(coinStorage);
    }

    public static CoinStorage fromMoney(int inputMoney) {
        validateMoney(inputMoney);
        return new CoinStorage(convertToCoins(inputMoney));
    }

    private static Map<Coin, Integer> convertToCoins(int inputMoney) {
        Map<Coin, Integer> coinStorage = initializeCointStorage();

        for (Coin coin : Coin.values()) {
            int coinCount = coin.calculateCount(inputMoney);
            coinStorage.put(coin, coinStorage.getOrDefault(coin, 0) + coinCount);
            inputMoney -= coin.calculateByCount(coinCount);
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

    public CoinStorage getAvailableChanges(int inputMoney) {
        Map<Coin, Integer> availableCoinStorage = new EnumMap<>(Coin.class);
        for (Coin coin : Coin.values()) {
            int requiredCoinCount = coin.calculateCount(inputMoney);
            int coinStock = this.coinStorage.get(coin);
            if (coinStock >= requiredCoinCount) {
                availableCoinStorage.put(coin, requiredCoinCount);
                inputMoney -= coin.calculateByCount(requiredCoinCount);

            }
            if (coinStock < requiredCoinCount) {
                availableCoinStorage.put(coin, coinStock);
                inputMoney -= coin.calculateByCount(coinStock);
            }
        }
        return CoinStorage.from(availableCoinStorage);
    }
}
