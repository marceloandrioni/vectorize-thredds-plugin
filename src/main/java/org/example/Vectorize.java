package org.example;

import ucar.nc2.Variable;
import ucar.nc2.filter.Enhancement;

public abstract class Vectorize implements Enhancement {
    protected final Variable uVar;
    protected final Variable vVar;

    public Vectorize(Variable uVar, Variable vVar) {
        this.uVar = uVar;
        this.vVar = vVar;
    }
}