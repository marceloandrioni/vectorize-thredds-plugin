package org.example;

import ucar.ma2.DataType;
import ucar.nc2.AttributeContainer;
import ucar.nc2.Variable;
import ucar.nc2.dataset.NetcdfDataset;
import ucar.nc2.dataset.VariableDS;
import ucar.nc2.filter.Enhancement;
import ucar.nc2.filter.EnhancementProvider;

import java.io.IOException;

public class VectorMagnitude extends Vectorize {

    public static final String ATTRIBUTE_NAME = "vectorize_mag";

    public VectorMagnitude(Variable var) {
        super(var);
    }

    @Override
    public double convert(double num) {
        try {
            // TODO: is this the best way?
            double u_val = uVar.readScalarDouble();
            double v_val = vVar.readScalarDouble();
            return Math.sqrt(v_val*v_val + u_val*u_val);
        } catch (IOException ioe) {
            // TODO: log
            return Double.NaN;
        }
    }

    @Override
    protected String getAttributeName() {
        return ATTRIBUTE_NAME;
    }


    public class Provider implements EnhancementProvider {

        public String getAttributeName() {
            return ATTRIBUTE_NAME;
        }

        @Override
        public boolean appliesTo(NetcdfDataset.Enhance enhance, AttributeContainer attributes, DataType dt) {
            return dt.isNumeric();
        }

        @Override
        public Enhancement create(VariableDS var) {
            return new VectorMagnitude(var);
        }
    }
}

