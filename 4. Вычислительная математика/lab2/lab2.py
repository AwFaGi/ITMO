import copy
import math
from typing import Callable
from dataclasses import dataclass


@dataclass
class Equation:
    id: int
    text: str
    function: Callable


@dataclass
class SysEquation:
    id: int
    text: str
    function: Callable
    jacobi_row: list[Callable]


equations = [
    Equation(id=0, text="x^2 - 4 = 0", function=lambda x: x * x - 4),
    Equation(id=1, text="e^x - 17 = 0", function=lambda x: math.exp(x) - 17),
    Equation(id=2, text="x^3 - x + 11 = 0", function=lambda x: x * x * x - x + 11),
    Equation(id=3, text="sin(x) * x^2 - 3 = 0", function=lambda x: math.sin(x) * x * x - 3)
]

sys_equations = [
    SysEquation(id=0, text="x^2 - y = 0", function=lambda x, y: x * x - y,
                jacobi_row=[
                    lambda x, y: 2 * x, lambda x, y: -1
                ]),
    SysEquation(id=1, text="5*x - y^3 = 0", function=lambda x, y: 5 * x - y * y * y,
                jacobi_row=[
                    lambda x, y: 5, lambda x, y: -3 * y * y
                ]),
    SysEquation(id=2, text="1/x + y^2 = 0", function=lambda x, y: 1 / x + y * y,
                jacobi_row=[
                    lambda x, y: (-1) / (x * x), lambda x, y: 2 * y
                ]),
]


class MatrixOperation:
    @staticmethod
    def get_minor(matrix: list[list[float]], row_number: int, col_number: int) -> list[list[float]]:
        output = [[j for j in row] for i, row in enumerate(matrix) if i != row_number]
        for row in output:
            row.pop(col_number)

        return output

    @staticmethod
    def calculate_det(matrix: list[list[float]]) -> float | None:

        row_size = len(matrix)
        if row_size == 0:
            return None
        col_size = len(matrix[0])
        if col_size != row_size:
            return None

        if row_size == 1:
            return matrix[0][0]

        if row_size == 2:
            return matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1]

        det = 0
        for i, value in zip(range(row_size), matrix[0]):
            det += ((-1) ** i) * value * MO.calculate_det(MO.get_minor(matrix, 0, i))

        return det


MO = MatrixOperation


def get_jacobian(matrix: list[list[Callable]], point: list[float]) -> float:
    result = []

    for row in matrix:
        result.append([])
        for func in row:
            result[-1].append(func(*point))

    return MO.calculate_det(result)


def is_vector_diff_less_than_eps(vec1: list[float], vec2: list[float], eps: float = 10 ** (-5)) -> bool:
    data = []
    for i, j in zip(vec1, vec2):
        data.append(abs(i - j))

    return max(data) < eps


def numeric_derivative(func: Callable, x0: float, dx: float = 10 ** (-5)) -> float:
    return (func(x0 + dx) - func(x0 - dx)) / (2 * dx)


def numeric_derivative2(func: Callable, x0: float, dx: float = 10 ** (-5)) -> float:
    return (func(x0 + 2 * dx) - 2 * func(x0) + func(x0 - 2 * dx)) / (4 * dx * dx)


def bisection_method(eq: Equation, a_value: float, b_value: float, eps: float = 10 ** (-14)) -> float | None:
    func = eq.function

    while True:

        med = (a_value + b_value) / 2

        if -eps < func(med) < eps:
            return med

        if func(a_value) * func(med) < 0:
            b_value = med
            continue

        elif func(med) * func(b_value) < 0:
            a_value = med
            continue

        else:
            return


def tangent_method(eq: Equation, x0: float, eps: float = 10 ** (-10)) -> float | None:
    func = eq.function

    if func(x0) * numeric_derivative2(func, x0) < 0:
        return None

    prev_result = x0
    while True:
        result = prev_result - func(prev_result) / numeric_derivative(func, prev_result)

        if abs(result - prev_result) < eps:
            return result

        prev_result = result


def newton_method(equation_list: list[SysEquation], x0: list[float], eps: float = 10 ** (-5)) -> list[float] | None:
    jacobi_matrix = [sys_eq.jacobi_row for sys_eq in equation_list]

    prev_result = copy.deepcopy(x0)

    counter = 0

    while True:
        a_val, b_val = prev_result
        J = get_jacobian(jacobi_matrix, prev_result)
        new_a = a_val - (1 / J) * (
                equation_list[0].function(a_val, b_val) * equation_list[1].jacobi_row[1](a_val, b_val) -
                equation_list[1].function(a_val, b_val) * equation_list[0].jacobi_row[1](a_val, b_val)
        )

        new_b = b_val + (1 / J) * (
                equation_list[0].function(a_val, b_val) * equation_list[1].jacobi_row[0](a_val, b_val) -
                equation_list[1].function(a_val, b_val) * equation_list[0].jacobi_row[0](a_val, b_val)
        )

        result = [new_a, new_b]

        if is_vector_diff_less_than_eps(result, prev_result, eps):
            return result

        counter = counter + 1
        if counter > 300_000:
            return None

        prev_result = copy.deepcopy(result)


if __name__ == "__main__":

    task = input("Choose task:\n1: solve equation\n2: solve system\n>>> ")

    match task:
        case "1":
            print("Choose equation:")
            print(*[f"{eq.id}) {eq.text}" for eq in equations], sep="\n")

            equation = input(">>> ")
            try:
                equation = int(equation)
                equation = equations[equation]
            except (ValueError, IndexError):
                print("Programma uronena")
                raise SystemExit

            input_range = input("Enter a and b, like:\n1.25 3.21\n>>> ")

            try:
                a, b = list(map(float, input_range.split()))
            except Exception:
                print("Programma uronena")
                raise SystemExit

            print(f"Equation: {equation.text}")

            sol1 = bisection_method(equation, a, b)
            if sol1:
                print(f"Bisection method solution: {sol1:.4f}")
            else:
                print(f"Bisection method solution: {sol1}")

            sol2 = tangent_method(equation, b)
            if sol2:
                print(f"Tangent method solution: {sol2:.4f}")
            else:
                print(f"Tangent method solution: {sol2}")

        case "2":
            print("Choose equations:")
            print(*[f"{eq.id}) {eq.text}" for eq in sys_equations], sep="\n")

            sys_equation = input(">>> ")
            try:
                sys_equation = list(map(int, sys_equation.split()))
                sys_equation = [sys_equations[i] for i in sys_equation]
            except Exception:
                print("Programma uronena")
                raise SystemExit

            zero_round = input("Enter x_0 and y_0, like:\n1.25 3.21\n>>> ")

            try:
                zero_round = list(map(float, zero_round.split()))
            except Exception:
                print("Programma uronena")
                raise SystemExit

            print(f"System:")
            print(*[eq.text for eq in sys_equation], sep="\n")

            sol = newton_method(sys_equation, zero_round)

            if sol:
                print(f"Solution:")
                print(f"x = {sol[0]:.4f}")
                print(f"y = {sol[1]:.4f}")
            else:
                print("No solution found in 300k iterations")

        case _:
            print("Programma uronena")
            raise SystemExit
