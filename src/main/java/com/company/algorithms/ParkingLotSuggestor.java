package com.company.algorithms;

import com.company.enums.CarTypeEnum;

import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.IntStream;

public class ParkingLotSuggestor {

    private HashMap<Integer, Boolean> floorHasFreeParkingSpaceMap;
    private int floorFrom;
    private int floorTo;

    public ParkingLotSuggestor(HashMap<Integer, Boolean> floorHasFreeParkingSpaceMap, int floorFrom, int floorTo) {
        this.floorFrom = floorFrom;
        this.floorTo = floorTo;
        this.floorHasFreeParkingSpaceMap = floorHasFreeParkingSpaceMap;
    }

    public Integer getNearestFloor(CarTypeEnum carTypeEnum, int boomBarrier) throws Exception {
        validate(boomBarrier);

        return IntStream.range(floorFrom, floorTo + 1)
                .limit(carTypeEnum == CarTypeEnum.VAN ? 2 : floorTo - floorFrom + 1)
                .skip(carTypeEnum == CarTypeEnum.ELECTRIC ? floorTo - floorFrom - 1 : 0)
                .filter(floor -> floorHasFreeParkingSpaceMap.getOrDefault(floor, false))
                .boxed()
                .min(Comparator.comparingInt(i -> Math.abs(i - boomBarrier)))
                .orElse(null);
    }

    private void validate(int boomBarrier) throws Exception {
        if (boomBarrier < floorFrom || boomBarrier > floorTo) {
            throw new Exception("Incorrect boom barrier");
        }
    }

    public void setFloorData(HashMap<Integer, Boolean> floorHasFreeParkingSpaceMap, int floorFrom, int floorTo) {
        this.floorHasFreeParkingSpaceMap = floorHasFreeParkingSpaceMap;
        this.floorTo = floorTo;
        this.floorFrom = floorFrom;
    }
}