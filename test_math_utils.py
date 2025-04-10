import pytest
from math_utils import MathUtils

class TestMathUtils:
    
    def test_add(self):
        assert MathUtils.add(2, 3) == 5
        assert MathUtils.add(-2, 1) == -1
        assert MathUtils.add(0, 0) == 0
    
    def test_subtract(self):
        assert MathUtils.subtract(3, 2) == 1
        assert MathUtils.subtract(0, 3) == -3
        assert MathUtils.subtract(5, 5) == 0
    
    def test_multiply(self):
        assert MathUtils.multiply(2, 3) == 6
        assert MathUtils.multiply(0, 5) == 0
        assert MathUtils.multiply(2, -2) == -4
    
    def test_divide(self):
        assert MathUtils.divide(4, 2) == 2.0
        assert MathUtils.divide(5, 0) == -1.0
        assert MathUtils.divide(1, 2) == 0.5