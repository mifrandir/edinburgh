from random import random, randint
import sys

def p_norm(x: [float], p: int) -> float:
    return sum(map(lambda v: abs(v)**p, x))**(1/p)

def test_with_p_equals_one():
    print("Testing with p=1...", end='')
    for _ in range(10):
        x = [random() for _ in range(10)] 
        assert_equals_float(p_norm(x, 1), sum(x))
    print("PASSED")

def test_single_nonzero_component():
    print("Testing with single nonzero component...", end='')
    for _ in range(10):
        x = [0, random()]
        assert_equals_float(p_norm(x, randint(1,10)), x[1])
    print("PASSED")

def test_pythagorean_triples():
    print("Testing with some pythagorean triples...", end='')
    f = lambda a, b, c: assert_equals_float(p_norm([a,b], 2), c)
    f(3,4,5)
    f(5,12,13)
    f(7,24,25)
    f(8,15,17)
    f(37,684,685)
    print("PASSED")

def assert_equals_float(result, expected):
    num_digits = 8
    try:
        assert round(result, num_digits) == round(expected, num_digits)
    except AssertionError:
        print(f"FAILED\nExpected: {expected:.8f}, Result: {result:.8f}")
        sys.exit(1)

def run_tests():
    test_with_p_equals_one()
    test_single_nonzero_component()
    test_pythagorean_triples()

if __name__ == "__main__":
    run_tests()