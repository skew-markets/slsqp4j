package com.example.slsqp;

import com.example.slsqp.functions.Vector2ScalarFunc;
import com.example.slsqp.functions.Vector2VectorFunc;

public class Jacobian
{
    public static double epsilon = Math.sqrt(Math.ulp((double)1)); // 1.4901161193847656e-08

    public static double[][] approx_jacobian(double[] x, Vector2VectorFunc func, double... arg)
    {
        final int n = x.length;
        double[] f0;
        if (arg != null && arg.length > 0)
        {
            f0 = func.apply(x, arg);
        }
        else
        {
            f0 = func.apply(x);
        }

        double[][] jac = new double[n][f0.length];
        double[] dx = new double[n];

        for (int i = 0; i < n; i++)
        {
            dx[i] = Jacobian.epsilon;

            double[] add = new double[n];
            for (int j = 0; j < n; j++)
            {
                add[j] = x[j] + dx[j];
            }
            for (int j = 0; j < f0.length; j++)
            {
                if (arg != null && arg.length > 0)
                {
                    jac[i][j] = (func.apply(add, arg)[j] - f0[j]) / Jacobian.epsilon;
                }
                else
                {
                    jac[i][j] = (func.apply(add)[j] - f0[j]) / Jacobian.epsilon;
                }
            }
            dx[i] = 0;
        }
        return jac;
    }

    public static double[] approx_jacobian(double[] x, Vector2ScalarFunc func, double... arg)
    {
        final int n = x.length;
        double f0;
        if (arg != null && arg.length > 0)
        {
            f0 = func.apply(x, arg);
        }
        else
        {
            f0 = func.apply(x);
        }

        double[] jac = new double[n];
        double[] dx = new double[n];
        for (int i = 0; i < n; i++)
        {
            dx[i] = Jacobian.epsilon;

            double[] add = new double[n];
            for (int j = 0; j < n; j++)
            {
                add[j] = x[j] + dx[j];
            }

            if (arg != null && arg.length > 0)
            {
                jac[i] = (func.apply(add, arg) - f0) / Jacobian.epsilon;
            }
            else
            {
                jac[i] = (func.apply(add) - f0) / Jacobian.epsilon;
            }

            dx[i] = 0;
        }
        return jac;
    }

    public static double[][] transpose(double[][] arr)
    {
        double[][] temp = new double[arr[0].length][arr.length];
        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr[0].length; j++)
                temp[j][i] = arr[i][j];
        return temp;
    }

}
