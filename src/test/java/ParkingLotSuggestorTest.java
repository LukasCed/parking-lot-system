import com.company.algorithms.ParkingLotSuggestor;
import com.company.enums.CarTypeEnum;
import org.junit.*;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ParkingLotSuggestorTest {

    private HashMap<Integer, Boolean> floorHasFreeParkingSpaceMap;
    private ParkingLotSuggestor parkingLotSuggestor;

    @Before
    public void init() {
        floorHasFreeParkingSpaceMap = getFloorHasFreeParkingSpaceMap();
        parkingLotSuggestor = new ParkingLotSuggestor(floorHasFreeParkingSpaceMap, -4, 4);
    }

    @Test
    public void parkingLotSuggestor_eletricCar() throws Exception {
        Integer nearestFloor = parkingLotSuggestor.getNearestFloor(CarTypeEnum.ELECTRIC, 2);
        assertEquals(4, (int)nearestFloor);
    }

    @Test
    public void parkingLotSuggestor_van() throws Exception {
        Integer nearestFloor = parkingLotSuggestor.getNearestFloor(CarTypeEnum.VAN, -1);
        assertEquals(-3, (int)nearestFloor);
    }

    @Test
    public void parkingLotSuggestor_petrolDiesel() throws Exception {
        Integer nearestFloor = parkingLotSuggestor.getNearestFloor(CarTypeEnum.PETROLDIESEL, 0);
        assertEquals(-1, (int)nearestFloor);
    }

    @Test(expected = Exception.class)
    public void parkingLotSuggestor_invalidBoomBarrier_exceptionThrown() throws Exception {
        parkingLotSuggestor.getNearestFloor(CarTypeEnum.PETROLDIESEL, 6);
    }

    @Test
    public void parkingLotSuggestor_noSpacesLeft_noFloorReturned() throws Exception {
        parkingLotSuggestor.setFloorData(getFloorsWithNoFreeParkingSpaceMap(), -2, 2);
        Integer nearestFloor = parkingLotSuggestor.getNearestFloor(CarTypeEnum.ELECTRIC, 2);
        assertNull(nearestFloor);
    }

    // Test data
    private HashMap<Integer, Boolean> getFloorHasFreeParkingSpaceMap() {
        return new HashMap<Integer, Boolean>() {{
            put(-4, true);
            put(-3, true);
            put(-2, false);
            put(-1, true);
            put(0, false);
            put(1, true);
            put(2, true);
            put(3, false);
            put(4, true);
        }};
    }

    private HashMap<Integer, Boolean> getFloorsWithNoFreeParkingSpaceMap() {
        return new HashMap<Integer, Boolean>() {{
            put(-2, false);
            put(-1, false);
            put(0, false);
            put(1, false);
            put(2, false);
        }};
    }
}
