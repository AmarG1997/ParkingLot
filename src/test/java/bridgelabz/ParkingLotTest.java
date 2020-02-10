package bridgelabz;

import com.briddgelabz.ParkingLotException;
import com.briddgelabz.ParkingSystem;
import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {

    @Test
    public void whenDriverWantsToPark_shouldBeAbleToPark() throws ParkingLotException {
        ParkingSystem parkingSystem = new ParkingSystem();
        boolean park = parkingSystem.park("Amar", "MH-15-FE5310", "White");
        boolean park1 = parkingSystem.park("Mayur", "MH-15-BT1057", "White");
        boolean park2 = parkingSystem.park("Dipak", "MH-15-FE4141", "White");
        boolean park3= parkingSystem.park("sonam", "MH-15-FE41415", "black");
        parkingSystem.getDetails();
        Assert.assertEquals(true,park);
    }

    @Test
    public void whenDriverWantsToUnPark_shouldBeAbleToUnPark() throws ParkingLotException {
        ParkingSystem parkingSystem = new ParkingSystem();
        boolean park = parkingSystem.park("Amar", "MH-15-FE5310", "White");
        boolean park1 = parkingSystem.park("Mayur", "MH-15-BT1057", "White");
        boolean park2 = parkingSystem.park("Dipak", "MH-15-FE4141", "White");
        boolean park3= parkingSystem.park("sonam", "MH-15-FE41415", "black");
        boolean result = parkingSystem.unPark("mayur");
        parkingSystem.getDetails();
        Assert.assertEquals(true,result);
    }

    @Test
    public void whenDriverSayWrongName_shouldThrowException() throws ParkingLotException {
        ParkingSystem parkingSystem = new ParkingSystem();
        boolean park = parkingSystem.park("Amar", "MH-15-FE5310", "White");
        boolean park1 = parkingSystem.park("Mayur", "MH-15-BT1057", "White");
        boolean park2 = parkingSystem.park("Dipak", "MH-15-FE4141", "White");
        boolean park3= parkingSystem.park("sonam", "MH-15-FE41415", "black");
        try {
            parkingSystem.unPark("WrongName");
        } catch (ParkingLotException e) {
            Assert.assertEquals("NO CAR DATA FOUND",e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void whenParkingLotIsFull_shouldReturnFull()  {
        ParkingSystem parkingSystem = new ParkingSystem();
        boolean park=true;
        for (int i=1;i<=100;i++) {
            try {
                park = parkingSystem.park("ABC", "MH-15-FE5310", "White");
                park=parkingSystem.park("ABC","XYZ","RED");
            } catch (ParkingLotException e) {
                Assert.assertEquals("PARKING_LOT_IS_FULL",e.getMessage());
                e.printStackTrace();
            }
        }

    }
}
