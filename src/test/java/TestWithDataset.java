import org.junit.jupiter.api.Test;
import ucar.ma2.Array;
import ucar.nc2.Variable;
import ucar.nc2.dataset.NetcdfDataset;
import ucar.nc2.dataset.NetcdfDatasets;

import java.io.IOException;

public class TestWithDataset {

    @Test
    public void testDatasetWithNcml() throws IOException {
        NetcdfDataset ncDataset = NetcdfDatasets.openDataset("src/test/data/test_dataset.ncml");
        Variable mag = ncDataset.findVariable("cspd");
        Array magData = mag.read();
        Variable dir = ncDataset.findVariable("cdir");
        Array dirData = dir.read();
    }
}
