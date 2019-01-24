package com.company;

import com.company.algorithms.ParkingLotSuggestor;
import com.company.enums.CarTypeEnum;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws Exception {
        HashMap floorHasFreeParkingSpaceMap = new HashMap<Integer, Boolean>() {{
            put(-2, false);
            put(-1, true);
            put(0, false);
            put(1, true);
            put(2, true);
            put(3, false);
            put(4, true);
            put(5, true);
            put(6, false);
            put(7, false);
            put(8, true);
        }};

        ParkingLotSuggestor parkingLotSuggestor = new ParkingLotSuggestor(floorHasFreeParkingSpaceMap, -2, 8);

        System.out.println(parkingLotSuggestor.getNearestFloor(CarTypeEnum.PETROLDIESEL, 2));
        System.out.println(parkingLotSuggestor.getNearestFloor(CarTypeEnum.VAN, 2));
        System.out.println(parkingLotSuggestor.getNearestFloor(CarTypeEnum.ELECTRIC, 2));
    }
}
