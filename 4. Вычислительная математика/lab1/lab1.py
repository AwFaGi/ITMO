import copy
import time
from random import randint
from typing import Union
from functools import reduce


class MatrixInput:
    @staticmethod
    def read_from_file() -> Union[list[list[float]], None]:
        filename = input("Enter filename >>> ")

        try:
            with open(filename, "r") as file:
                n = int(file.readline())
                data = file.read().replace(",", ".")

            data = data.split("\n")
            data = [list(map(float, row.split())) for row in data]

            if len(data) == n and all([len(row) == n + 1 for row in data]):
                return data

            return None

        except:
            return None

    @staticmethod
    def generate_random() -> Union[list[list[float]], None]:
        try:
            n = int(input("Enter matrix size >>> "))
        except:
            return None

        output = [[randint(-100, 100) for _ in range(n + 1)] for _ in range(n)]
        return output

    @staticmethod
    def read_from_user() -> Union[list[list[float]], None]:
        try:
            n = int(input("Enter matrix size >>> "))
        except:
            return None

        output = []
        for _ in range(n):
            try:
                row = list(map(float, input().split()))
            except:
                return None
            if len(row) != n + 1:
                return None
            output.append(row)

        return output


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
MI = MatrixInput


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


def get_error(matrix: list[list[float]], answer: list[float]) -> list[float]:
    output = []
    for row in matrix:
        left = 0
        for j, k in zip(row, answer):
            left += j * k
        output.append(row[-1] - left)
    return output


def main():
    input_type = input("0: read from file\n1: read from console\n2: generate random\n>>> ")

    match input_type:
        case "0":
            data = MI.read_from_file()
            print("Read matrix:")
            print_matrix(data)
        case "1":
            data = MI.read_from_user()
        case "2":
            data = MI.generate_random()
            print("Generated matrix:")
            print_matrix(data)
        case _:
            print("Programma uronena")
            raise SystemExit

    start_time = time.time()

    res = solve_matrix(data)
    if res is None:
        print(f"Can't solve matrix")
        raise SystemExit

    end_time = time.time()

    answer, diag, det = res

    print("Diagonal:")
    print_matrix(diag)

    print(f"det = {det}\n")

    print("Answer:")
    print(*[f"x{i} = {round(value, 2)}" for i, value in enumerate(answer, start=1)], sep="\n")
    print()

    print("Error:")
    print(*[f"r{i} = {value}" for i, value in enumerate(get_error(data, answer), start=1)], sep="\n")  # round(value, 6)
    print()

    print(f"Working time: {end_time - start_time:.3f}")


if __name__ == "__main__":
    main()
