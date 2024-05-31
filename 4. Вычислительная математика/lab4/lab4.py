#!/bin/python3
import copy
from functools import reduce
from typing import Union

import matplotlib
matplotlib.use("module://backend_interagg")
import matplotlib.pyplot as plt


class MatrixOperation:
    @staticmethod
    def get_multiplied_row(matrix: list[list[float]], row_number: int, value: float) -> list[float]:
        return [i * value for i in matrix[row_number]]

    @staticmethod
    def add_multiplied_row(matrix: list[list[float]], row_to_add: list[float], row_number: int) -> None:
        for add_value, (num, old_value) in zip(row_to_add, enumerate(matrix[row_number])):
            matrix[row_number][num] = old_value + add_value

    @staticmethod
    def change_rows(matrix: list[list[float]], first: int, second: int) -> None:
        matrix[first], matrix[second] = matrix[second], matrix[first]

    @staticmethod
    def find_max_value_in_column(matrix: list[list[float]], column_num: int, start_row: int) -> tuple[int, float]:
        row_number = start_row
        max_value = 0

        for i in range(start_row, len(matrix)):
            row = matrix[i]
            if abs(row[column_num]) > abs(max_value):
                row_number = i
                max_value = row[column_num]

        return row_number, max_value

    @staticmethod
    def get_minor(matrix: list[list[float]], row_number: int, col_number: int) -> list[list[float]]:
        output = [[j for j in row] for i, row in enumerate(matrix) if i != row_number]
        for row in output:
            row.pop(col_number)

        return output

    @staticmethod
    def calculate_det(matrix: list[list[float]]) -> Union[float, None]:

        row_size = len(matrix)
        if row_size == 0:
            return None
        col_size = len(matrix[0])
        if col_size != row_size + 1:
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


def print_matrix(matrix: list[list[float]]) -> None:
    if matrix is None:
        print("No matrix")
        return
    copied = [[round(element, 2) for element in row] for row in matrix]
    print(*copied, sep="\n", end="\n\n")


def solve_matrix(matrix: list[list[float]]) -> Union[tuple, None]:
    n = len(matrix)
    wm = working_matrix = copy.deepcopy(matrix)

    for i in range(n):
        main_row, main_elem = MO.find_max_value_in_column(wm, i, i)
        if main_elem == 0:
            return None

        for j in range(n):
            if j == main_row:
                continue
            coeff = - wm[j][i] / main_elem
            MO.add_multiplied_row(wm, MO.get_multiplied_row(wm, main_row, coeff), j)

        if main_row != i:
            MO.change_rows(wm, main_row, i)

    det = reduce(lambda x, y: x * y, [row[i] for i, row in enumerate(matrix)])

    return [row[-1] / row[i] for i, row in enumerate(working_matrix)], working_matrix, det


def interpolate_by_spline(x_values: list[float], y_values: list[float], x_point: float) -> float | None:
    h_values = [x_values[i] - x_values[i - 1] for i in range(1, len(x_values))]  # h_i == y_{i + 1}
    dy_values = [y_values[i] - y_values[i - 1] for i in range(1, len(y_values))]

    c_matrix = []

    first_row = [2 * (h_values[0] + h_values[1]), h_values[1]]
    first_row += [0] * (len(h_values) - 2)
    first_row.append(3 * ((dy_values[1]) / h_values[1] - (dy_values[0]) / h_values[0]))

    c_matrix.append(first_row)

    for i in range(1, len(h_values) - 1):
        row = [float(0)] * (i - 1)
        row.append(h_values[i])
        row.append(2 * (h_values[i] + h_values[i + 1]))
        row.append(h_values[i + 1])
        row += [0] * (len(h_values) - 3 - (i - 1))
        row.append(3 * ((dy_values[i + 1]) / h_values[i + 1] - (dy_values[i]) / h_values[i]))

        c_matrix.append(row)

    conv_n = len(h_values) - 1

    last_row = [float(0)] * (conv_n - 1)
    last_row.append(h_values[conv_n - 1])
    last_row.append(2 * (h_values[conv_n - 1] + h_values[conv_n]))
    last_row.append(3 * ((dy_values[conv_n]) / h_values[conv_n] - (dy_values[conv_n - 1]) / h_values[conv_n - 1]))

    c_matrix.append(last_row)
    c_list = solve_matrix(c_matrix)[0]

    d_list = [c_list[0] / (3 * h_values[0])]
    for i in range(conv_n):
        d_list.append((c_list[i + 1] - c_list[i]) / (3 * h_values[i + 1]))

    b_list = [(dy_values[0] / h_values[0]) + h_values[0] * (2 * c_list[0] / 3)]
    for i in range(conv_n):
        b_list.append((dy_values[i + 1] / h_values[i + 1]) + h_values[i + 1] * ((2 * c_list[i + 1] + c_list[i]) / 3))

    a_list = y_values[1:]

    for i in range(1, len(x_values)):

        if x_point == x_values[i]:
            return y_values[i]

        if x_point < x_values[i]:
            need_number = i - 1
            right = x_values[i]
            return a_list[need_number] + \
                   b_list[need_number] * (x_point - right) + \
                   c_list[need_number] * (x_point - right) * (x_point - right) + \
                   d_list[need_number] * (x_point - right) * (x_point - right) * (x_point - right)


if __name__ == '__main__':
    axis_count = int(input().strip())

    x_axis = list(map(float, input().rstrip().split()))

    y_axis = list(map(float, input().rstrip().split()))

    x = float(input().strip())

    func = input().strip()

    result = interpolate_by_spline(x_axis, y_axis, x)

    print(str(result) + '\n')

    for i in range(axis_count):
        plt.scatter(x_axis[i], y_axis[i])

    dist = x_axis[-1] - x_axis[0]
    step = dist / 100

    x_points = [x_axis[0] + step * i for i in range(100)] + [x_axis[-1]]
    y_points = [interpolate_by_spline(x_axis, y_axis, i) for i in x_points]

    plt.xlim(x_points[0] - 2, x_points[-1] + 2)
    plt.ylim(min(y_points) - 1, max(y_points) + 1)
    plt.plot(x_points, y_points, label="Created")

    match func:
        case "# 2x+2":
            func_points = [2*i + 2 for i in x_points]
        case "# x^2":
            func_points = [i * i for i in x_points]
        case "# 2^(-x)":
            func_points = [2**(-i) for i in x_points]
        case _:
            func_points = []

    plt.plot(x_points, func_points, label="Math")
    plt.legend()
    plt.show()
