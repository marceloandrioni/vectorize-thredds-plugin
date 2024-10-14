package org.example;

import ucar.ma2.DataType;
import ucar.nc2.AttributeContainer;
import ucar.nc2.Variable;
import ucar.nc2.dataset.NetcdfDataset;
import ucar.nc2.dataset.VariableDS;
import ucar.nc2.filter.Enhancement;
import ucar.nc2.filter.EnhancementProvider;

public class VectorMagnitude extends Vectorize {

    public static final String ATTRIBUTE_NAME = "vectorize_mag";

    public VectorMagnitude(Variable uVar, Variable vVar) {
        super(uVar, vVar);
    }

    @Override
    public double convert(double num) {
        return 0;
    }


    public class Provider implements EnhancementProvider {

        @Override
        public boolean appliesTo(NetcdfDataset.Enhance enhance, AttributeContainer attributes, DataType dt) {
            return false;
        }

        @Override
        public Enhancement create(VariableDS var) {
            return null;
        }
    }
}

