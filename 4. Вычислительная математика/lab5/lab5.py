import math
import os
import random
import re
import sys


class Result:

    @staticmethod
    def first_function(x: float, y: float):
        return math.sin(x)

    @staticmethod
    def second_function(x: float, y: float):
        return (x * y) / 2

    @staticmethod
    def third_function(x: float, y: float):
        return y - (2 * x) / y

    @staticmethod
    def fourth_function(x: float, y: float):
        return x + y

    @staticmethod
    def default_function(x: float, y: float):
        return 0.0

    # How to use this function:
    # func = Result.get_function(4)
    # func(0.01)
    @staticmethod
    def get_function(n: int):
        if n == 1:
            return Result.first_function
        elif n == 2:
            return Result.second_function
        elif n == 3:
            return Result.third_function
        elif n == 4:
            return Result.fourth_function
        else:
            return Result.default_function

    @staticmethod
    def numeric_derivative(func, x0: float, dx: float = 10 ** (-5)) -> float:
        return (func(x0 + dx) - func(x0 - dx)) / (2 * dx)

    #
    # Complete the 'solveByEulerImproved' function below.
    #
    # The function is expected to return a DOUBLE.
    # The function accepts following parameters:
    #  1. INTEGER f
    #  2. DOUBLE epsilon
    #  3. DOUBLE a
    #  4. DOUBLE y_a
    #  5. DOUBLE b
    #
    @staticmethod
    def solveByEulerImproved(loc_f, loc_epsilon, loc_a, loc_y_a, loc_b):
        func = Result.get_function(loc_f)

        steps = 15
        x = [loc_a]
        y = [loc_y_a]

        for _ in range(steps):
            h = (loc_b - loc_a) / steps

            x_z = x[-1] + h / 2
            y_z = y[-1] + h / 2 * (func(x[-1], y[-1]))

            y.append(y[-1] + h * func(x_z, y_z))
            x.append(x[-1] + h)

        import matplotlib.pyplot as plt

        plt.plot(x, y, label="Created")

        match loc_f:
            case 1:
                plt.plot(x, [-math.cos(i) + 2 for i in x], label="Math")
            case 2:
                plt.plot(x, [math.e ** (i * i / 4) for i in x], label="Math")

        plt.legend()
        plt.show()

        return y[-1]


if __name__ == '__main__':
    f = int(input().strip())

    epsilon = float(input().strip())

    a = float(input().strip())

    y_a = float(input().strip())

    b = float(input().strip())

    result = Result.solveByEulerImproved(f, epsilon, a, y_a, b)

    print(str(result) + '\n')

    import matplotlib

