import numpy as np

#---------------------------------------
# Solution
#---------------------------------------


# Function to compute the sub-anti-diagonal of any square matrix
def sub_anti_diagonal(M):
    N = M.shape[0]
    # for each x[i] in the sub-diagonal the value is M[i,-i]
    return np.array([M[i, -i] for i in range(1, N)])


#---------------------------------------
# Tests
#---------------------------------------


# Testing against manually entered values and expected outputs
def test_manual():
    # "end=''" removes trailing linebreak
    print("Running manual tests...", end='')
    ms = []  # input matrices
    xs = []  # expected output vectors
    ms.append(
        np.array([[6.0, 3.0, 9.0, 10.0], [3.0, -1.0, 6.0, 9.0],
                  [-3.0, 6.0, 1.0, -1.0], [5.0, 3.0, 4.0, 3.0]]))
    xs.append(np.array([9.0, 1.0, 3.0]))
    ms.append(np.array([[0.0, 1.0], [2.0, 3.0]]))
    xs.append(np.array([3.0]))
    # cycling through all the test cases
    for i in range(len(ms)):
        y = sub_anti_diagonal(ms[i])
        # np.array_equal compares two arrays for equality
        if not np.array_equal(xs[i], y):
            print("FAILED")
            print(f"Expected: {xs[i]}")
            print(f"Actual: {y}")
            return
    print("PASSED")


# Testing with differently sized zero matrices
def test_zeros():
    print("Running test for zero matrices...", end="")
    MAX_SIZE = 1000  # number of sizes to test
    for i in range(2, MAX_SIZE):
        m = np.zeros((i, i))  # creating i x i matrix with 0s
        x = np.zeros(i - 1)  # sub-anti-diagonal should have length i-1
        y = sub_anti_diagonal(m)
        if not np.array_equal(x, y):
            print("FAILED")
            print(f"Expected: {x}")
            print(f"Actual: {y}")
            return
    print("PASSED")


# Making sure the shape is correct, regardless of content
def test_shape():
    print("Running test for output shape...", end="")
    MAX_SIZE = 1000  # number of sizes to test
    for i in range(2, MAX_SIZE):
        # creating i x i matrix with random values
        m = np.random.random((i, i))
        y = sub_anti_diagonal(m)
        if not y.shape[0] == i - 1:  # sub-anti-diagonal should have length i-1
            print("FAILED")
            print(f"Expected: {i-1}")
            print(f"Actual: {y.shape[0]}")
            return
    print("PASSED")


#---------------------------------------
# Entry Point
#---------------------------------------

# Running this file directly will lead to the tests being run
# Just use
#
# $ python CR1.py
#
# and you should see something like
#
# > Running manual tests...PASSED
# > Running test for zero matrices...PASSED
# > Running test for output shape...PASSED

if __name__ == "__main__":
    test_manual()
    test_zeros()
    test_shape()