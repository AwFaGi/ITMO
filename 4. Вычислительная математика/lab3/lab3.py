#!/bin/python3

import math
import os
import random
import re
import sys

from typing import Callable


class Result:
    error_message = ""
    has_discontinuity = False
    eps = 10 ** (-7)

    @staticmethod
    def first_function(x: float):
        return 1 / x

    @staticmethod
    def second_function(x: float):
        if x == 0:
            return (math.sin(Result.eps) / Result.eps + math.sin(-Result.eps) / -Result.eps) / 2
        return math.sin(x) / x

    @staticmethod
    def third_function(x: float):
        return x * x + 2

    @staticmethod
    def fourth_function(x: float):
        return 2 * x + 2

    @staticmethod
    def five_function(x: float):
        return math.log(x)

    # How to use this function:
    # func = Result.get_function(4)
    # func(0.01)
    @staticmethod
    def get_function(n: int) -> Callable:
        if n == 1:
            return Result.first_function
        elif n == 2:
            return Result.second_function
        elif n == 3:
            return Result.third_function
        elif n == 4:
            return Result.fourth_function
        elif n == 5:
            return Result.five_function
        else:
            raise NotImplementedError(f"Function {n} is not defined.")

    #
    # Complete the 'calculate_integral' function below.
    #
    # The function is expected to return a DOUBLE.
    # The function accepts following parameters:
    #  1. DOUBLE a
    #  2. DOUBLE b
    #  3. INTEGER f
    #  4. DOUBLE epsilon
    #
    @staticmethod
    def calculate_integral(local_a: float, local_b: float, local_f: int, local_epsilon: float) -> float | None:
        match local_f:
            case 1:
                if local_a <= 0 and local_b >= 0:
                    Result.has_discontinuity = True
                    Result.error_message = "Integrated function has discontinuity or does not defined in current " \
                                           "interval"
                    return
            case 5:
                if local_a <= 0 or local_b <= 0:
                    Result.has_discontinuity = True
                    Result.error_message = "Integrated function has discontinuity or does not defined in current " \
                                           "interval"
                    return

        sign = 1 if local_a < local_b else -1
        local_a, local_b = min(local_a, local_b), max(local_a, local_b)

        func = Result.get_function(local_f)
        current_start = local_a
        summa = 0

        while current_start < local_b:
            med = current_start + local_epsilon / 2
            summa += (local_epsilon / 6) * (func(current_start) + 4 * func(med) + func(current_start + local_epsilon))
            current_start += local_epsilon

        return sign * summa


if __name__ == '__main__':

    a = float(input().strip())

    b = float(input().strip())

    f = int(input().strip())

    epsilon = float(input().strip())

    result = Result.calculate_integral(a, b, f, epsilon)
    if not Result.has_discontinuity:
        print(str(result) + '\n')
    else:
        print(Result.error_message + '\n')
