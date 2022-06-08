package park;

import java.time.LocalTime;

public class Park {
    private String carNumber;
    LocalTime localTime;

    public Park(String carNumber, LocalTime localTime) {
        this.carNumber = carNumber;
        this.localTime = localTime;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }
}
