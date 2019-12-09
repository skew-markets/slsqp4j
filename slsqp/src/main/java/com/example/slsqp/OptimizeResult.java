package com.example.slsqp;

public class OptimizeResult
{
    public final double[] x;
    public final double fx;
    public final double[] jac;
    public final int status;
    public final int numIters;
    public final int exit_mode;
    public final boolean success;
    public double[][] a;

    public OptimizeResult(
        double[] x,
        double fx,
        double[] jac,
        int status,
        int numIters,
        int exit_mode,
        boolean success,
        double[][] a
    )
    {
        this.x = x;
        this.fx = fx;
        this.jac = jac;
        this.numIters = numIters;
        this.status = status;
        this.exit_mode = exit_mode;
        this.success = success;
        this.a = a;
    }
}
