package org.example;

import ucar.nc2.Attribute;
import ucar.nc2.Variable;
import ucar.nc2.filter.Enhancement;

public abstract class Vectorize implements Enhancement {
    protected final Variable uVar;
    protected final Variable vVar;

    // TODO: error handling
    public Vectorize(Variable var) {
        Attribute att = var.findAttribute(getAttributeName());
        String[] vars = att.getStringValue().split("/");
        this.uVar = var.getParentGroup().findVariableLocal(vars[0]);
        this.vVar = var.getParentGroup().findVariableLocal(vars[1]);
    }

    abstract protected String getAttributeName();
}