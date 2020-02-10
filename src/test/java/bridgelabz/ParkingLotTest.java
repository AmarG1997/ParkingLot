package bridgelabz;

import com.briddgelabz.ParkingSpot;
import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {

    @Test
    public void whenDriverWantsToPark_shouldBeAbleTorPark() {
        ParkingSpot parkingSpot = new ParkingSpot();
        boolean park = parkingSpot.park("Amar", "MH-15-FE5310", "White");
        boolean park1 = parkingSpot.park("Mayur", "MH-15-BT1057", "White");
        boolean park2 = parkingSpot.park("Dipak", "MH-15-FE4141", "White");
        boolean park3= parkingSpot.park("sonam", "MH-15-FE41415", "black");
        parkingSpot.getDetails();
        Assert.assertEquals(true,park);
    }
}
