package park;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ParkExample {
    private static Park[] parkArrays = new Park[5];
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean run = true;


        while (run) {
            System.out.println("---------------------------------------------------");
            System.out.println("1.입고 선택 | 2.출고 선택 | 3.현재 입고현황 | 4. 프로그램 종료");
            System.out.println("---------------------------------------------------");
            System.out.print("> ");

            int selectNo = scanner.nextInt();

            if (selectNo == 1) {
                enterCar();
            } else if (selectNo == 2) {
                outCar();
            } else if (selectNo == 3) {
                parkingCarList();
            } else {
                run = false;
            }
        }
        System.out.println("프로그램 종료");
    }

    public static void enterCar() {
        if (parkArrays[parkArrays.length - 1] == null) {
            System.out.println("-----------");
            System.out.println("차량 번호 입력");
            System.out.println("-----------");
            System.out.print("> ");
            Pattern pattern = Pattern.compile("\\d{2,3}[가-힣]{1}\\d{4}");
            String carNumber = scanner.next();
            boolean result = Pattern.matches(String.valueOf(pattern), carNumber);
            if (result) {
                LocalTime enterTime = LocalTime.now();
                Park park = new Park(carNumber, enterTime);
                for (int i = 0; i < parkArrays.length; i++) {
                    if (parkArrays[i] == null) {
                        parkArrays[i] = park;
                        System.out.println("차량이 입고되었습니다.");
                        System.out.print("입고 시간 > ");
                        System.out.println(enterTime.withNano(0));
                        break;
                    }
                }
            } else {
                System.out.println("올바른 자동차번호가 아닙니다.");
            }
        } else {
            System.out.println("현재 주차장은 만차입니다.");
        }
    }

    public static void outCar() {
        if (parkArrays[0] != null) {
            System.out.println("-----------");
            System.out.println("차량 번호 입력");
            System.out.println("-----------");
            System.out.print("> ");
            String carNumber = scanner.next();
            LocalTime outTime = LocalTime.now();
            for (int i = 0; i < parkArrays.length; i++) {
                if ((parkArrays[i] != null) && (carNumber.equals(parkArrays[i].getCarNumber()))) {
                    System.out.print("출고 시간 > ");
                    System.out.println(outTime.withNano(0));
                    long differ = ChronoUnit.SECONDS.between(parkArrays[i].getLocalTime(), outTime);
                    System.out.println("요금은 " + differ * 1000 + "원입니다.");
                    System.out.println("차량이 출고 되었습니다.");
                    parkArrays[i] = null;
                    break;
                } else {
                    System.out.println("입고된 차량이 없습니다.");
                }
            }
        } else {
            System.out.println("출고할 차량이 없습니다.");
        }
    }

    public static void parkingCarList() {
        if (parkArrays[0] != null) {
            System.out.println("------------");
            System.out.println("현재 입고된 차량");
            System.out.println("------------");
            for (int i = 0; i < parkArrays.length; i++) {
                if (parkArrays[i] != null) {
                    System.out.println("차량 번호 : " + parkArrays[i].getCarNumber());
                    System.out.println("입고 시간 : " + parkArrays[i].getLocalTime().withNano(0));
                }
            }
        } else {
            System.out.println("입고된 차량이 없습니다.");
        }
    }

}
