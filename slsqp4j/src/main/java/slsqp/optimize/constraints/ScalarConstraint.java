package slsqp.optimize.constraints;

import slsqp.optimize.Jacobian;
import slsqp.optimize.functions.Vector2ScalarFunc;
import slsqp.optimize.functions.Vector2VectorFunc;

public final class ScalarConstraint
{
    private ConstraintType constraintType;
    private Vector2ScalarFunc constraintFunc;
    private Vector2VectorFunc jacobian;
    private double[] args;

    public static class ScalarConstraintBuilder
    {
        private ConstraintType constraintType;
        private Vector2ScalarFunc constraintFunc;
        private Vector2VectorFunc jacobian;
        private double[] args;

        public ScalarConstraintBuilder withConstraintFunction(Vector2ScalarFunc constraintFunc, double... args)
        {
            this.constraintFunc = constraintFunc;
            this.args = args;
            return this;
        }

        public ScalarConstraintBuilder withConstraintType(ConstraintType constraintType)
        {
            this.constraintType = constraintType;
            return this;
        }

        public ScalarConstraintBuilder withJacobian(Vector2VectorFunc jacobian)
        {
            this.jacobian = jacobian;
            return this;
        }

        public ScalarConstraint build()
        {
            if (this.constraintType == null)
            {
                throw new IllegalStateException("must specify a constraint type");
            }
            if (this.constraintFunc == null)
            {
                throw new IllegalStateException("must specify a constraint function");
            }
            final ScalarConstraint scalarConstraint = new ScalarConstraint();
            scalarConstraint.constraintType = this.constraintType;
            scalarConstraint.constraintFunc = this.constraintFunc;
            scalarConstraint.jacobian = this.jacobian;
            scalarConstraint.args = this.args;
            return scalarConstraint;
        }
    }

    private ScalarConstraint()
    {
    }

    public ConstraintType getConstraintType()
    {
        return constraintType;
    }

    public double[] getJacobian(double[] x)
    {
        if (jacobian == null)
        {
            return Jacobian.approxJacobian(x, constraintFunc, args);
        }
        else
        {
            return jacobian.apply(x, args);
        }
    }

    public double apply(double[] x)
    {
        return constraintFunc.apply(x, args);
    }
}
