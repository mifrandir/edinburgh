# Week 4 worksheet: Gaussian elimination

Gaussian elimination is a direct method to solve linear systems of the form $Ax = b$, with $A \in \mathbb{R}^{n\times n}$ and $b \in \mathbb{R}^n$, to find the unknown $x \in \mathbb{R}^n$. This week, we put what we have seen so far into practice, and program different steps of the Gaussian elimination algorithm: forward substitution, backward substitution, and elementary row operations.

The best way to learn programming is to write code. Don't hesitate to edit the code in the example cells, or add your own code, to test your understanding. You will find practice exercises throughout the notebook, denoted by 🚩 ***Exercise $x$:***.

#### Displaying solutions

Solutions will be released one week after the worksheets are released, as a new `.txt` file in the same GitHub repository. After pulling the file to your workspace (either your computer or your Noteable server), run the following cell to create clickable buttons under each exercise, which will allow you to reveal the solutions.


```python
%run scripts/create_widgets.py week04
```

---
### 📚 Book sections

- **ASC**: sections 4.1, ***4.2***, 4.7, 4.8
- **PCP**: sections 2.3, 2.4, 5.6

🚩 Section **4.2** of **ASC** is **mandatory reading** this week, particularly when working through sections 3 and 4 of this notebook. You probably have seen Gaussian elimination in your first year Linear Algebra course, so this should be familiar already -- but this will be a good refresher.

---
## 1. NumPy's `np.linalg`

Numpy has a **sub-module** called `linalg`, which contains many useful functions for linear algebra and matrix operations. If we imported Numpy as `np`, for example, then to use the functions in `linalg`, you will need to prefix them with `np.linalg.`. Some of the functions provided by the `np.linalg` submodule are:


```python
import numpy as np

# Create a random 3x3 matrix and a vector of three 1s
A = np.random.random([3, 3])
b = np.ones(3)

print(np.linalg.eigvals(A))              # Eigenvalues of a matrix: note the complex values here, j=sqrt(-1)
eig_val_A, eig_vec_A = np.linalg.eig(A)  # Eigenvalues and right eigenvectors
print("Eigenvalues: ", eig_val_A)
print("Eigenvectors: ", eig_vec_A)

print('\nInverse and determinant:')
print("A^(-1) =", np.linalg.inv(A))  # Inverse of a matrix
print("det(A) =", np.linalg.det(A))  # Determinant of a matrix

print('\nSolution of Ax = b:')
print("x =", np.linalg.solve(A, b))  # Solve Ax = b for x
```

    [1.69140282 0.04658435 0.25482363]
    Eigenvalues:  [1.69140282 0.04658435 0.25482363]
    Eigenvectors:  [[ 0.7159498   0.80182769  0.01513339]
     [ 0.66872147 -0.5709081  -0.24328789]
     [ 0.2005679   0.17645479  0.96983606]]
    
    Inverse and determinant:
    A^(-1) = [[ 13.24785302 -12.56174195  -3.29665699]
     [ -8.81604988   9.56430225   1.55239133]
     [  1.97232136  -2.88499882   3.16979119]]
    det(A) = 0.020078291652844076
    
    Solution of Ax = b:
    x = [-2.61054592  2.30064369  2.25711372]


---
**📚 Learn more:**
* [numpy.linalg](https://docs.scipy.org/doc/numpy/reference/routines.linalg.html)
* **ASC**: section 4.8
---

🚩 ***Exercise 1:*** Create two variables `M` and `y`, assigned with Numpy arrays representing the matrix $M$ and vector $y$ defined as (you can reuse your code from Exercise 4 in the Week 3 tutorial)

$$
M =
\begin{pmatrix}
9 & 3 & 0 \\
-2 & -2 & 1 \\
0 & -1 & 1
\end{pmatrix}, \qquad
y =
\begin{pmatrix}
0.4 \\ -3 \\ -0.3
\end{pmatrix}.
$$

Then, solve the system $Mx = y$ for $x$, using `np.linalg.solve()`.

*For checking:* the result should be `[-3.16666667  9.63333333  9.93333333]`.


```python
M = np.array([[9,3,0],[-2.0,-2.0,1.0],[0.0,-1.0,1.0]])
y = np.array([0.4,-3,-0.3])
np.linalg.solve(M, y)
```




    array([-2.56666667,  7.83333333,  7.53333333])




```python
%run scripts/show_solutions.py week04_ex1
```

---
## 2. Diagonal matrices

Diagonal matrices have elements off the leading diagonal equal to zero. Elements on the leading diagonal of a diagonal matrix may or may not be equal to zero. A diagonal matrix $A$ is invertible iff none of its diagonal elements are equal to zero.

### 2.1. Solving diagonal systems

When $A$ is a diagonal matrix, the linear system $Ax = b$ can be written as

$$
\begin{pmatrix}
a_{11} & & & \\
& a_{22} & & \\
& & \ddots & \\
& & & a_{nn}
\end{pmatrix}
\begin{pmatrix}
x_1 \\ x_2 \\ \vdots \\ x_n
\end{pmatrix}
=
\begin{pmatrix}
b_1 \\ b_2 \\ \vdots \\ b_n
\end{pmatrix}
\qquad \Leftrightarrow \qquad
\begin{cases}
a_{11} x_1 &= b_1 \\
a_{22} x_2 &= b_2 \\
&\vdots \\
a_{nn} x_n &= b_n
\end{cases},
$$

where $a_{ii}, b_{i}, i = 1, \dots, n$ are known. The matrix $A$ is invertible (and therefore the system $Ax = b$ has precisely one solution) iff all $a_{ii} \neq 0$.

---
🚩 ***Exercise 2:*** Write a function `linsolve_diag()` which solves the linear system $A x = b$, returning $x$ in the output variable `x` (as a NumPy array), without using `np.linalg.solve()`. Here the input `A` should be assumed to be an invertible **diagonal** square matrix, and `b` a column vector.

*Hints:*
- Use the `.shape` attribute of NumPy arrays to determine the size of the input matrix and vector.
- The solution may be computed using a `for` loop.
- There is also an efficient way to do this via a NumPy function which extracts the diagonal elements of a matrix.

*For checking:* the solution to the given example is $[20, 10]$.


```python
import numpy as np


def linsolve_diag(A, b):
    '''
    Solves the diagonal system Ax = b for x,
    assuming A is invertible.
    '''
    # your code here
    diag = np.diagonal(A)
    x = np.array([b[i] / diag[i] for i in range(len(b))])
    
    return x


# Expected solution: [20, 10]
A = np.array([[2, 0],
              [0, 0.5]])
b = np.array([40, 5])
print(linsolve_diag(A, b))
```

    [20. 10.]



```python
%run scripts/show_solutions.py week04_ex2
```

---
🚩 ***Exercise 3:*** Use your `linsolve_diag` function to solve the linear system

\begin{equation}
  \left( \begin{array}{ccc} 3 & 0 & 0 \\ 0 & -1 & 0 \\ 0
& 0 & 10 \end{array} \right) x = \left( \begin{array}{c} 3 \\ 1 \\ 1 \end{array}
\right),
\end{equation}

for $x$.


```python
linsolve_diag(np.array([[3,0,0], [0,-1,0], [0,0,10]]), np.array([3, 1, 1]))
```




    array([ 1. , -1. ,  0.1])




```python
%run scripts/show_solutions.py week04_ex3
```

### 2.2. Measuring computation time

The `time()` function in Python's `time` module allows Python to read the current time from your computer's clock. We can therefore use it to time how long it takes a section of code to run, as follows:

```python
import time

t0 = time.time()
# Code to time
t = time.time() - t0
print(f"Elapsed time: {t:.6f} seconds")
```

and the resulting time is stored in the variable `t`, as the time elapsed between the first and the second measurement.

---
**📚 Learn more:**
- [The `time` module](https://docs.python.org/3/library/time.html) - Python documentation
- [`time.time()`](https://docs.python.org/3/library/time.html#time.time) - Python documentation
- **PCP**: section 5.6, which discusses measuring computation time and efficiency, and provides examples using a different Python module called [`timeit`](https://docs.python.org/3/library/timeit.html)

---
🚩 ***Exercise 4:*** The following code generates a randomised invertible diagonal square matrix $A$ with dimension $N$, stored in the variable `A`, and a right-hand-side vector $b$, stored in the variable `b`, for a given value of `N`. Use `time.time()` to time how long it takes the `np.linalg.solve()` function to solve $A x = b$ for $x$. Compare this against the time it takes your `linsolve_diag()` function from Exercise 2 to solve for $x$, for different values of `N`.

Display the measured times in a way that is convenient to read (you can use an f-string, for instance; see the Week 1 workshop task).

*Hint:* limit `N` to less than $\sim 1,000$ to avoid using excessive memory.


```python
import time

# Create a randomised invertible diagonal matrix A and vector b
N = 500
A = np.diag(np.random.random([N])) + np.eye(N)
b = np.random.random([N])

# your code here
t1 = time.time()
np.linalg.solve(A,b)
t1 = time.time() - t1
t2 = time.time()
linsolve_diag(A,b)
t2 = time.time() - t2
print(f"numpy: {t1*1000:.2f}ms, linsolve_diag: {t2*1000:.2f}ms")
```

    numpy: 637.63ms, linsolve_diag: 0.59ms



```python
%run scripts/show_solutions.py week04_ex4
```

---

## 3. Forward and backward substitution

Gaussian elimination can be performed in 2 steps: forward substitution and backward substitution. In your previous courses on linear algebra, you probably have performed this by hand on small systems ($4\times 4$ or so). We can *implement* (program) the procedure in Python to be able to solve systems of any size much more quickly.

### 3.1. Lower triangular systems: forward substitution

**Lower triangular matrices** have elements above the leading diagonal equal to zero. Elements on or below the leading diagonal may or may not be equal to zero.

Linear systems involving lower triangular invertible square matrices can be solved via **forward substitution**. For example for the linear system

\begin{equation}
  \left( \begin{array}{ccc}  2 & 0 & 0 \\ -1 & 1 & 0 \\ -1 & 1 & 2 \end{array} \right)
  \left( \begin{array}{c} x_1 \\ x_2 \\ x_3 \end{array} \right)
  = \left( \begin{array}{c} 4 \\ 1 \\ 4 \end{array} \right),
\end{equation}

applying the matrix multiplication gives

\begin{equation}
	\left( \begin{array}{c} 2 x_1 \\ -x_1 + x_2 \\ -x_1 + x_2 + 2 x_3 \end{array} \right)
	= \left( \begin{array}{c} 4 \\ 1 \\ 4 \end{array} \right),
\end{equation}

where, for instance, $-x_1 + x_2 + 2 x_3$ is the 3rd element of the vector $Ax$. Comparing the first elements gives $x_1$. Since $x_1$ is now known, comparing the second elements gives $x_2$. Since $x_1$ and $x_2$ are now known, comparing the third elements gives $x_3$.

In other words, $x_1$ is trivial to compute, and is then *substituted* into the next equation, which means that $x_2$ is now trivial to compute, etc. The substitutions cascade *forward*.

#### Forward substitution in Python

The function `linsolve_lt()` below solves the linear system $A x = b$ using forward substitution, returning $x$ in the output variable `x`. Here the input `A` should be assumed to be an invertible **lower triangular** square matrix, and `b` a column vector.


```python
def linsolve_lt(A, b):
    '''
    Solves the lower triangular system Ax = b.
    '''
    N = b.shape[0]
    x = np.zeros(N)
    for i in range(N):
        x[i] = (b[i] - A[i, :i] @ x[:i]) / A[i, i]
    return x

# Solving the system in the example above
A = np.array([[2, 0, 0],
              [-1, 1, 0],
              [-1, 1, 2]], dtype=float)
b = np.array([4, 1, 4], dtype=float)
x = linsolve_lt(A, b)
print(x)
```

    [2.  3.  1.5]


---
🚩 ***Exercise 5:*** Examine the function `linsolve_lt()` carefully to understand how and why it works. Add code comments in the function definition to explain each step.

*Hint:* pen and paper will be useful here! Write (or sketch) what line 8 achieves depending on the value of `row`. For instance, what happens at the first iteration of the loop (when `row` is `0`)? at the second iteration (when `row` is `1`)? etc.


```python

```


```python
%run scripts/show_solutions.py week04_ex5
```

---
### 3.2.  Upper triangular systems: backward substitution

**Upper triangular matrices** have elements below the leading diagonal equal to zero. Elements on or above the leading diagonal may or may not be equal to zero.

Linear systems involving upper triangular invertible square matrices can be solved via **backward substitution**. Backward substitution is similar to forward substitution, but starts from the last row, and substitutions cascade backward until the first row.

#### Backward substitution in Python

🚩 ***Exercise 6:*** Write a function `linsolve_ut()` which solves the linear system $A x = b$ using backward substitution, returning $x$ in the output variable `x`. Here the input `A` should be assumed to be an invertible **upper triangular** square matrix, and `b` a column vector.

You can start from `linsolve_lt()` above and adapt it to use backward substitution.

*For checking:* The solution to the given example is $[-1, 2]$.


```python
def linsolve_ut(A, b):
    '''
    Solves the upper triangular system Ax = b.
    '''
    # your code here
    N = b.shape[0]
    x = np.zeros(N)
    for i in range(N):
        j = -i-1
        x[j] = (b[j] - A[j,j:] @ x[j:]) / A[j,j]
    
    return x


# Testing with an example
A = np.array([[1, 1],
              [0, 0.5]])
b = np.array([1, 1])
x = linsolve_ut(A, b)
print(x)
```

    [-1.  2.]



```python
%run scripts/show_solutions.py week04_ex6
```

---
🚩 ***Exercise 7:*** The following code generates an invertible upper triangular square matrix $A$ with dimension $N$, stored in the variable `A`, and a right-hand-side vector $b$, stored in the variable `b`, for a given value of `N`. Use `time.time()` to time how long it takes the `np.linalg.solve()` function to solve $A x = b$ for $x$. Compare this against the time it takes your `linsolve_ut()` function to solve for $x$, for different values of `N`.

*Hint:* Limit `N` to less than $\sim 1,000$ to avoid using excessive memory.


```python
import time

# Create a randomised invertible upper triangular matrix A and vector b
N = 800
A = np.triu(np.random.random([N])) + np.eye(N)
b = np.random.random([N])

# your code here
t1 = time.time()
np.linalg.solve(A,b)
t1 = time.time() - t1
t2 = time.time()
linsolve_ut(A,b)
t2 = time.time() - t2
print(f"numpy: {t1*1000:.2f}ms, linsolve_ut: {t2*1000:.2f}ms")
```

    numpy: 823.70ms, linsolve_ut: 3.99ms



```python
%run scripts/show_solutions.py week04_ex7
```

## 4. Gaussian elimination

We now know how to solve lower and upper triangular systems. Now, consider a system which is not triangular -- for instance:

$$
\begin{pmatrix}
1 & 1 & 1 \\ 2 & 1 & -1 \\ 1 & 1 & 2
\end{pmatrix}
\begin{pmatrix}
x_1 \\ x_2 \\ x_3
\end{pmatrix}
\begin{pmatrix}
2 \\ 1 \\ 0
\end{pmatrix}.
$$

We can build the *augmented matrix* by adding $b$ as an extra column in $A$:

$$
\begin{pmatrix} 1 & 1 & 1 & 2 \\ 2 & 1 & -1 & 1 \\ 1 & 1 & 2 & 0 \end{pmatrix}.
$$

The goal is now to **reduce** this augmented matrix into **reduced row echelon form** (RREF), i.e.

$$
\begin{pmatrix}
1 & 0 & 0 & x_1 \\ 0 & 1 & 0 & x_2 \\ 0 & 0 & 1 & x_3 \end{pmatrix},
$$

and the final column is then the solution of the original linear problem. We do this by applying **elementary row operations** to the augmented matrix, to create zeros under each diagonal element:

  \begin{align*}
    \left( \begin{array}{cccc} 1 & 1 & 1 & 2 \\ 2 & 1 & -1 & 1 \\ 1 & 1 & 2 & 0 \end{array} \right)
    \underset{R_2 - 2 R_1}{\rightarrow} 
    & \left( \begin{array}{cccc} 1 & 1 & 1 & 2 \\ 0 & -1 & -3 & -3 \\ 1 & 1 & 2 & 0 \end{array} \right) \nonumber \\
    \underset{R_3 - R_1}{\rightarrow} 
    & \left( \begin{array}{cccc} 1 & 1 & 1 & 2 \\ 0 & -1 & -3 & -3 \\ 0 & 0 & 1 & -2 \end{array} \right) \nonumber \\
  \end{align*}
  
This is equivalent to the linear equations:

  \begin{align*}
    1 x_1 + 1 x_2 + 1 x_3 & = 2, \nonumber \\
    0 x_1 - 1 x_2 - 3 x_3 & = -3, \nonumber \\
    0 x_1 + 0 x_2 + 1 x_3 & = -2.
  \end{align*}
  
We could keep going, and apply further elementary row operations to the augmented matrix... but this system is now **upper triangular**, and therefore we can solve it using **backward substitution**!

Time to tie it all together -- remember that you can check section **4.2** in **ASC** for help for these final problems.

### 4.1. Elementary row operations

🚩 ***Exercise 8:*** Write a function `row_op()` which applies the elementary row operation

\begin{equation}
  \left( \textrm{Row} j \right) \rightarrow \beta \times \left( \textrm{Row } j \right) + \alpha \times \left( \textrm{Row } i \right),
\end{equation}

where $\alpha, \beta \in \mathbb{R}$.

*For checking:* The solution to the given example is $[[0, 4], [1, 2]]$.

*Hint:* Input arguments of functions can be modified if they are e.g. lists or NumPy arrays (remember section 4 of the Week 3 tutorial), so you can apply this operation to `A` itself, and thus change it. As long as you don't redefine `A` from scratch inside the function, you don't even need to return it, as it will be changed in place. Since you don't return it (i.e. you return `None`), there is no result to assign to a new variable -- so simply calling your function (as in the example), without assigning the output to `A` for instance, will still work.

Here is a simpler example to illustrate this:


```python
def change_in_place(x):
    '''
    Change the first element of x in-place.
    '''
    x[0] = 12345   # note that we don't return anything here!

    
# Test our function
z = np.array([10, 15, 20, 25])
print(z)
change_in_place(z)   # this changes z itself, no "output" to store in a variable here
print(z)
```

    [10 15 20 25]
    [12345    15    20    25]



```python
def row_op(A, alpha, i, beta, j):
    '''
    Applies row operation beta*A_j + alpha*A_i to A_j,
    the jth row of the matrix A.
    Changes A in place.
    '''
    # your code here
    

# Testing with an example
A = np.array([[2, 0],
              [1, 2]])
alpha, beta = 2, -1
i, j = 1, 0

# If you don't return A, it will be changed in-place when the function is executed
print(A)
row_op(A, alpha, i, beta, j)   # this changes A
print(A)
```

    [[2 0]
     [1 2]]
    [[2 0]
     [1 2]]



```python
%run scripts/show_solutions.py week04_ex8
```

### 4.2. Row echelon form

🚩 ***Exercise 9 (challenging):*** Write a function `REF()` which takes as inputs `A` and `b`, a square invertible matrix and a vector, and returns `C` and `d`, which are respectively `A` and `b` transformed by successive elementary row operations, so that `C` is upper triangular (and the system $Cx = d$ is equivalent to $Ax = b$).

Your function should first build the augmented matrix $( A | b )$, and use elementary row operations as in the example above to reduce it to row echelon form. Finally, it should split the final augmented matrix into a square matrix `C` and a vector `d`.

Use your function `row_op()` to perform the row operations: **you do not need to re-define it here**, you can simply *call* it -- i.e. use the command `row_op(..)` with appropriate input arguments inside your function `REF()`.

You will have to calculate $\alpha$ and $\beta$ for each row operation. For instance, in the example above, the first row operation performed is $R_2 \to R_2 - 2R_1$, therefore we have $i=1$, $j=2$, $\alpha = -2$, and $\beta = 1$. How can you know that these values of $\alpha$ and $\beta$ will ensure that the element in the second row, first column becomes 0? (*hint: you should see similarities with your forward substitution algorithm.*)

*Hint:* think about how you would do this on paper. You will need to create zeros under the diagonal element in each column (one after another), and you will need a separate row operation for each row (in a given column) to make the leading element zero. You will need 2 nested loops.

*For checking:* `C` and `d` should be as in the example above.


```python
def REF(A, b):
    '''
    Reduces the augmented matrix (A|b) into
    row echelon form, returns (C|d).
    '''
    # your code here         
    C = np.array(A)
    d = np.array(b)
    N = C.shape[0]
    for j in range(N):
        if C[j,j] == 0:
            for k in range(j+1,N):
                if C[k,j] != 0:
                    C[j], C[k] = C[k], C[j]
                    break
        for i in range(j+1,N):
            r = C[i,j]/C[j,j]
            C[i,j:] -= r*C[j,j:]
            d[i] -= r*d[j]
    return C, d

# Testing with an example
A = np.array([[1, 1, 1],
              [2, 1, -1],
              [1, 1, 2]], dtype=float)
b = np.array([2, 1, 0], dtype=float)

C, d = REF(A, b)
print(C)
print(d)
```

    [[ 1.  1.  1.]
     [ 0. -1. -3.]
     [ 0.  0.  1.]]
    [ 2. -3. -2.]



```python
%run scripts/show_solutions.py week04_ex9
```

### 4.3. Completing Gaussian elimination

We have done all the hard work now, all that is left is to put it all together.

🚩 ***Exercise 10:*** Write a function `gauss()` which, given an invertible matrix `A` and a column vector `b`,  solves the system $Ax = b$ and returns the result as `x`. This function should make use of your previous functions `REF()` and `linsolve_ut()`. (Again, no need to define them again here, just call them.)

*For checking:* the result here should be $[-5, 9, -2]$.

*For further checking:* given an arbitrary `A` and `b`, how can you check that `x` is indeed the solution to $Ax = b$ (to machine precision)?


```python
def gauss(A, b):
    '''
    Solve the linear system Ax = b, given a square
    invertible matrix A and a vector b, using Gaussian elimination.
    '''
    # your code here
    C, d = REF(A, b)
    x = linsolve_ut(C, d)
    return x


# Test the function
A = np.array([[1, 1, 1],
              [2, 1, -1],
              [1, 1, 2]], dtype=float)
b = np.array([2, 1, 0], dtype=float)

x = gauss(A, b)
print(x)
y = np.linalg.solve(A, b)
print(y)
```

    [-5.  9. -2.]
    [-5.  9. -2.]



```python
%run scripts/show_solutions.py week04_ex9
```


    Button(description='Reveal solution', style=ButtonStyle())



    Output(layout=Layout(border='1px solid green'))



```python
N = 2000
A = np.triu(np.random.random([N])) + np.eye(N)
b = np.random.random([N])

# your code here
t1 = time.time()
np.linalg.solve(A,b)
t1 = time.time() - t1
t2 = time.time()
gauss(A,b)
t2 = time.time() - t2
print(f"numpy: {t1*1000:.2f}ms, gauss: {t2*1000:.2f}ms")
```

    numpy: 1044.28ms, gauss: 10365.70ms



```python

```
