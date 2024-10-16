package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ucar.nc2.Attribute;
import ucar.nc2.Variable;
import ucar.nc2.filter.Enhancement;

public abstract class Vectorize implements Enhancement {

    private static Logger logger = LogManager.getLogger(Vectorize.class);

    protected Variable uVar;
    protected Variable vVar;
    private int nDims;
    protected int[] shape;

    public Vectorize(Variable var) {
        try {
            Attribute att = var.findAttribute(getAttributeName());
            String[] vars = att.getStringValue().split("/");
            this.uVar = var.getParentGroup().findVariableLocal(vars[0]);
            this.vVar = var.getParentGroup().findVariableLocal(vars[1]);

            this.shape = var.getShape();
            if (!validateDims()) { return; }

            this.nDims = this.shape.length;
            for (int d = 0; d < this.nDims; d++) {
                this.shape[d] = 1;
            }
        } catch (NullPointerException ex) {
            logger.error("Could not parse attribute {}", getAttributeName());
        }
    }

    protected int[] indexToCoords(int index) {
        int[] coords = new int[this.nDims];
        int innerDims = 1;
        for (int i = this.nDims-1; i >= 0; i--) {
            coords[i] = (index/innerDims) % this.shape[i];
            innerDims *= this.shape[i];
        }
        return coords;
    }

    private boolean validateDims() {
        return this.shape == this.uVar.getShape() && this.shape == this.vVar.getShape();
    }

    abstract protected String getAttributeName();
}