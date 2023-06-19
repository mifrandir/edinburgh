# Week 3 worksheet: Conditional statements, NumPy arrays, and linear algebra

In this worksheet, we introduce conditional statements, another *control flow* tool, which allows us to specify *condition(s)* under which to execute certain parts of our code. We also (re-)introduce the **NumPy** module -- and specifically, how we can use **NumPy arrays** to handle mathematical problems.

The best way to learn programming is to write code. Don't hesitate to edit the code in the example cells, or add your own code, to test your understanding. You will find practice exercises throughout the notebook, denoted by 🚩 ***Exercise $x$:***.

#### Displaying solutions

Solutions will be released one week after the worksheets are released, as a new `.txt` file in the same GitHub repository. After pulling the file to your workspace (either your computer or your Noteable server), run the following cell to create clickable buttons under each exercise, which will allow you to reveal the solutions.


```python
%run scripts/create_widgets.py week03
```

    Solutions not yet released!


---
## 1. Conditional statements

Conditional statements allow us to create different branches in our code, to separate different instructions to be executed under specific conditions.

### 1.1. `if` statements

Booleans can be used to execute or skip certain instructions under given conditions, using `if` statements. The syntax is as follows:
```python
if my_condition:
    [some instructions]
```
where `my_condition` is a Boolean object whose value is either `True` or `False`. A few examples -- after running the cell, you can **change** the values of the variables and check that you can still see what you expected to see:


```python
# Define some variables
a = 4.3
b = 5.1
c = 'hello'
i = 1
j = 8
z = True

if i == j:
    # This is not true, so any instructions
    # in this block are ignored
    print('i and j are equal')

if i < j:
    print('i is less than j')
    
if type(i) == int:
    print('i is an integer')

if type(c) == str and type(j) != float:
    print('c is a string and j is not a float')

if (a + b) > 7:
    print(a + b)

if z:
    print(a)

if j:
    # Why does this work ?
    print('j is not zero nor empty')
```

    i is less than j
    i is an integer
    c is a string and j is not a float
    9.399999999999999
    4.3
    j is not zero nor empty


In the last example, although `j` does not point to a Boolean object, it is *interpreted* as a Boolean, because it follows the `if` keyword. Non-zero numbers and non-empty containers are interpreted as `True`, whereas `0`, `0.0`, `None`, and empty containers (e.g. an empty list `[]` or an empty string `''`) are interpreted as `False`.

### 1.2. `if`-`elif`-`else` blocks

To check multiple conditions one after another, we can use `if`-`elif`-`else` blocks (`elif` is short for "else if"). The syntax is
```python
if cond_1:
    # [some instructions, executed if cond_1 is true]
elif cond_2:
    # [other instructions, executed if cond_1 is false,
    # but cond_2 is true]
else:
    # [other instructions, executed if both cond_1 and cond_2
    # are false]
```
Note, in particular, that:
- The conditions in an `if`-`elif`-`else` block are checked in order, and **only one** branch is executed.
- The `elif` and `else` statements are both **optional**; depending on what you want to do, you might only need the `if` branch (like in section 1.1), or just `if`-`else`, or sometimes just `if`-`elif`.
- You can have more than one `elif` between `if` and `else`, if you want to check more than 3 different cases.

Here is an example:


```python
a = 4.9
b = 5.4

if a > b:
    print('a is greater than b')
elif a < b:
    print('a is smaller than b')
else:
    print('a is equal to b')
```

    a is smaller than b


---
**📚 Learn more:**
* [More flow control tools - Python 3.7 documentation](https://docs.python.org/3/tutorial/controlflow.html)
* [Boolean operations - Python 3.7 documentation](https://docs.python.org/3/reference/expressions.html#boolean-operations)

---
🚩 ***Exercise 1:*** The following code generates a random integer `n` between 1 and 1000. Complete the code such that running the cell displays a sentence indicating whether `n` is a multiple of both 3 and 7, either 3 or 7 (but not both), or neither.

*Note: the generated random number will be different every time you run the cell. It is generated using [NumPy's random number functionality](https://numpy.org/doc/stable/reference/random/generated/numpy.random.randint.html).*


```python
import numpy as np

n = np.random.randint(1, 1001)
fs = [3,7]
lcf = 1
for f in fs:
    if n % f == 0:
        lcf *= f
if lcf == 1:
    print(f"n={n} is not divisible by any of the factors {{{', '.join(map(str ,fs))}}}")
else:
    print(f'n={n} is divisible by {lcf}')

```

    n=774 is divisible by 3



```python
%run scripts/show_solutions.py week03_ex1
```


    Button(description='Reveal solution', style=ButtonStyle())



    Output(layout=Layout(border='1px solid green'))


---
🚩 ***Exercise 2:*** Construct a loop over all words in the string `zen`. For each word:
* if it contains an `e`, print the word.
* if it does not contain an `e`, but contains an `i`, print the first character of the word.
* if it does not contain an `e` nor an `i`, increment `count` by 1.

*Notes:*
- *You will first need to create a list of words from the string --- luckily, you should find a convenient [method](https://docs.python.org/3/library/stdtypes.html#string-methods) for this if you search the documentation.*
- *The text is from the Zen of Python: https://www.python.org/dev/peps/pep-0020/*


```python
zen = 'If the implementation is hard to explain, it is a bad idea. If the implementation is easy to explain, it may be a good idea.'
count = 0
words = zen.split()
for w in words:
    if 'e' in w:
        print(w)
    elif 'i' in w:
        print(w[0])
    else:
        count += 1

```

    the
    implementation
    i
    explain,
    i
    i
    idea.
    the
    implementation
    i
    easy
    explain,
    i
    be
    idea.



```python
%run scripts/show_solutions.py week03_ex2
```


    Button(description='Reveal solution', style=ButtonStyle())



    Output(layout=Layout(border='1px solid green'))


---
### 1.3. The `break`  statement in loops

Sometimes, we may wish to exit a loop early -- for example, when we try to find the first element in a sequence which matches a condition. Once we find the element, we don't want to keep looping through the rest of the sequence.

The `break` statement, together with a condition, can be used to exit a loop conditionally. Here is an example:


```python
list_of_strings = ['hello', 'this', 'is', 'a', 'lot', 'of', 'text', 'in', 'a', 'list.']

# Find and display the first word which starts with an i
for word in list_of_strings:
    if word[0] == 'i':
        print(word)
        break    # This stops the loop immediately
```

    is


---
**Note:** It is easy to see that a `while` loop can potentially run forever. When this happens, in Jupyter/IPython, `In [*]:` will appear on the top left of the code cell -- click the square button on the toolbar above to interrupt the kernel.

It can also be a good idea to count iterations when using `while` loops, for instance by incrementing a counting variable at every iteration. To safeguard against infinite loops, you can then `break` the loop conditionally, for example if the counter exceeds some maximum number of iterations.

---
**📚 Learn more:**
* [More flow control tools - Python 3.7 documentation](https://docs.python.org/3/tutorial/controlflow.html)
* [`break` and `continue` statements, and `else` clauses on loops - Python 3.7 documentation](https://docs.python.org/3/tutorial/controlflow.html#break-and-continue-statements-and-else-clauses-on-loops)


---
🚩 ***Exercise 3:*** The following is an example of *nested loops*. Try to predict the displayed output, and run the cell to verify. How does `break` behave within nested loops?

*Hint: it's a good time to pick up a pen and paper! Try to figure out what happens in the first few iterations of each loop, by substituting `i` and `j` by their first few values.*


```python
count = 0

for i in range(10):
    for j in range(5):
        count += 1
        if count > 17:
            break
    print(count)
```

    5
    10
    15
    18
    19
    20
    21
    22
    23
    24



```python
%run scripts/show_solutions.py week03_ex3
```


    Button(description='Reveal solution', style=ButtonStyle())



    Output(layout=Layout(border='1px solid green'))


---
## 2. NumPy arrays

We introduced the Numpy module when we needed to use some mathematical functions to compute values (e.g. `np.cos()`, `np.sqrt()`...). Another one of Numpy's useful functionalities is the **`ndarray` type** (stands for "N-dimensional array"). Numpy arrays are *containers*, like lists, which allow us to store and handle vectors and matrices efficiently. (If you are familiar with MATLAB, Numpy arrays are quite similar to MATLAB arrays.)

### 2.1. Creating arrays manually

The function `np.array()` is used to create an array. It takes a **list** as an input argument, and returns an array where the rows are the elements of the list. For example, to create a vector or a matrix:


```python
# Start by importing Numpy
import numpy as np

# Create a vector
v = np.array([3, 4, -2.8, 0])
print(v)
print(type(v))

# Create a matrix: pass a list of lists to np.array(),
# each element of which is a row of the matrix
id_4 = np.array([[1, 0, 0, 0],
                 [0, 1, 0, 0],
                 [0, 0, 1, 0],
                 [0, 0, 0, 1]])
print(id_4)

# Use the second (optional) input argument of np.array()
# to specify the type of its elements:
id_4_float = np.array([[1, 0, 0, 0],
                       [0, 1, 0, 0],
                       [0, 0, 1, 0],
                       [0, 0, 0, 1]], dtype=float)
print(id_4_float)
```

    [ 3.   4.  -2.8  0. ]
    <class 'numpy.ndarray'>
    [[1 0 0 0]
     [0 1 0 0]
     [0 0 1 0]
     [0 0 0 1]]
    [[1. 0. 0. 0.]
     [0. 1. 0. 0.]
     [0. 0. 1. 0.]
     [0. 0. 0. 1.]]


---
**Note:** 1-D Numpy arrays (vectors) are *not* row vectors or column vectors -- they are similar to lists, in this sense.

---
### 2.2. Some useful functions to construct arrays

Numpy also has many useful functions to construct arrays with particular properties, for example:
- `np.zeros()`, `np.ones()` to create arrays full of zeros or ones
- `np.eye()` for the identity matrix
- `np.random.random()`, `np.random.randint()` for matrices of random real numbers or random integers
- `np.arange()`, `np.linspace()` for vectors of equally spaced values


```python
# Create a matrix of zeros
A = np.zeros([3, 7])
print(A)

# Create a vector of ones
u = np.ones(5)
print(f'u = {u}')

# Create the 4x4 identity matrix, as above
id_4_mat = np.eye(4)
print(id_4_mat)

# Create a matrix of pseudo-random numbers between 0 and 1,
# sampled from a uniform distribution
B = np.random.random([3, 3])
print(f'B = {B}')

# Create a 1D array (a vector) with a range of floats
v = np.arange(3.1, 5.2, 0.3)
print(f'v = {v}')

# Create a 1D array (a vector) of 21 linearly spaced values
w = np.linspace(0, 4, 21)
print(f'w = {w}')

# Retrieve the dimensions of an array, as a tuple
print(f'The shape of A is {A.shape}.')
```

    [[0. 0. 0. 0. 0. 0. 0.]
     [0. 0. 0. 0. 0. 0. 0.]
     [0. 0. 0. 0. 0. 0. 0.]]
    u = [1. 1. 1. 1. 1.]
    [[1. 0. 0. 0.]
     [0. 1. 0. 0.]
     [0. 0. 1. 0.]
     [0. 0. 0. 1.]]
    B = [[0.71372147 0.08840284 0.27755839]
     [0.11634375 0.52524419 0.11695342]
     [0.27221839 0.08911891 0.61180208]]
    v = [3.1 3.4 3.7 4.  4.3 4.6 4.9 5.2]
    w = [0.  0.2 0.4 0.6 0.8 1.  1.2 1.4 1.6 1.8 2.  2.2 2.4 2.6 2.8 3.  3.2 3.4
     3.6 3.8 4. ]
    The shape of A is (3, 7).


Note that the `.shape` *attribute* of `ndarray` objects is a *tuple* `(n_rows, n_columns)` (a little bit like a list -- we'll see those in more detail in a few weeks), taking as values the dimensions of an array. (`.shape` is *not* a method -- it doesn't perform any operations, like a function would do, it is simply a property of `ndarray` objects, a value associated with the object. Note that it is **not** followed by parentheses, like a method would be.)

For those functions which require to specify the dimensions of the target array, these dimensions are given as *one argument* -- a **sequence of integers**. For example, a *range* and a *list* are two different types of sequence -- here, we've used a list, `[n_rows, n_columns]` when creating `A` and `B`:

```python
A = np.zeros([3, 7])
B = np.random.random([3, 3])
```


Another useful functionality for constructing arrays is the `.reshape()` *method* of the `ndarray` type. It takes one input argument, the shape of the target array, and broadcasts the values in the array to which it is applied into this new shape. For instance, here are four ways to construct the matrix

$$
M =
\begin{pmatrix}
0.1 & 0.2 & 0.3 \\
0.4 & 0.5 & 0.6 \\
0.7 & 0.8 & 0.9
\end{pmatrix}
$$


```python
# First way: giving the list of rows explicitly
M1 = np.array([[0.1, 0.2, 0.3],
               [0.4, 0.5, 0.6],
               [0.7, 0.8, 0.9]])
print(M1)

# Second way: using range() and .reshape()
# Note that range() returns a sequence, which we can therefore use
# directly as the input argument for np.array()
M2 = 0.1 * np.array(range(1, 10)).reshape([3, 3])
print(M2)

# Third way: using np.arange() and .reshape
M3 = np.arange(0.1, 1, 0.1).reshape([3, 3])
print(M3)

# Fourth way: using np.linspace() and .reshape()
M4 = np.linspace(0.1, 0.9, 9).reshape([3, 3])
print(M4)
```

    [[0.1 0.2 0.3]
     [0.4 0.5 0.6]
     [0.7 0.8 0.9]]
    [[0.1 0.2 0.3]
     [0.4 0.5 0.6]
     [0.7 0.8 0.9]]
    [[0.1 0.2 0.3]
     [0.4 0.5 0.6]
     [0.7 0.8 0.9]]
    [[0.1 0.2 0.3]
     [0.4 0.5 0.6]
     [0.7 0.8 0.9]]


---
**📚 Learn more:**
* [NumPy: the absolute basics for beginners](https://numpy.org/doc/stable/user/absolute_beginners.html)
* [NumPy User Guide](https://docs.scipy.org/doc/numpy/user/)
* [NumPy Reference](https://docs.scipy.org/doc/numpy/reference/index.html)
* [NumPy array function](https://docs.scipy.org/doc/numpy/reference/generated/numpy.array.html)
* [The N-dimensional array (ndarray)](https://docs.scipy.org/doc/numpy-1.12.0/reference/arrays.ndarray.html)
* [Array creation routines](https://docs.scipy.org/doc/numpy-1.12.0/reference/routines.array-creation.html#routines-array-creation)
* [Generate random arrays with Numpy](https://numpy.org/doc/stable/reference/random/generated/numpy.random.Generator.random.html#numpy.random.Generator.random)

---

🚩 ***Exercise 4:*** Create two variables `M` and `y`, assigned with Numpy arrays representing the matrix $M$ and vector $y$ defined as

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


```python
M = np.array([[9,3,0],[-2,-2,1],[0,-1,1]])
y = np.array([0.4,-3,-0.3])
```


```python
%run scripts/show_solutions.py week03_ex4
```

---
### 2.3. Element-wise operations

The basic operators `+`, `-`, `*`, `/`, and `**` can be used to perform **element-wise** operations between
* two arrays of the *same size*, or
* an array and a scalar.


```python
# Yet another way to construct the matrix M from earlier...
A = 0.1 * np.array([[1, 2, 3],
                    [4, 5, 6],
                    [7, 8, 9]])
print("A = ")
print(A)

B = np.eye(3)

print("\nA + B =")
print(A + B)

print("\nA - B =")
print(A - B)

print("\nA * B =")
print(A * B)

print("\nA ** 2 =")
print(A ** 2)

print("\n B / A =")
print(B / A)
```

    A = 
    [[0.1 0.2 0.3]
     [0.4 0.5 0.6]
     [0.7 0.8 0.9]]
    
    A + B =
    [[1.1 0.2 0.3]
     [0.4 1.5 0.6]
     [0.7 0.8 1.9]]
    
    A - B =
    [[-0.9  0.2  0.3]
     [ 0.4 -0.5  0.6]
     [ 0.7  0.8 -0.1]]
    
    A * B =
    [[0.1 0.  0. ]
     [0.  0.5 0. ]
     [0.  0.  0.9]]
    
    A ** 2 =
    [[0.01 0.04 0.09]
     [0.16 0.25 0.36]
     [0.49 0.64 0.81]]
    
     B / A =
    [[10.          0.          0.        ]
     [ 0.          2.          0.        ]
     [ 0.          0.          1.11111111]]


---
🚩 ***Exercise 5:*** Write a function `dot_prod()`, which takes 2 input arguments `u` and `v`, both 1D NumPy arrays (vectors), and returns the dot product of the 2 vectors. You are not allowed to use the function `np.dot()` from NumPy -- code it yourself. You can test your function on any two vectors by comparing the result to the output of `np.dot(u, v)`.

The dot product of 2 vectors $\mathbf{u}, \mathbf{v} \in \mathbb{R}^n$ is given by
$$
\mathbf{u} \cdot \mathbf{v} = \sum_{i=1}^n u_i v_i.
$$


```python
def dot_prod(u, v):
    return sum([ui*vi for (ui,vi) in zip(u,v)])

for _ in range(100):
    u = np.random.random(10)
    v = np.random.random(10)
    assert np.dot(u,v) == dot_prod(u,v)
```


```python
%run scripts/show_solutions.py week03_ex5
```

---
## 3. Indexing and slicing Numpy arrays

To access an element in a Numpy array in a given position, we can use **indexing**, just like for other sequences. The main difference is that an element in an $N$-dimensional Numpy array is indexed by $N$ integers, each representing the element's position along a dimension.

Concretely:
* `v[i]` is the $i+1$th element of the vector `v`.
* `A[i, j]` is the element in the $i+1$th row and $j+1$th column of the matrix `A`.
* `X[i, j, k, h, ...]` is used to index elements for tensors in higher dimensions.

Everything we have seen when **slicing** lists and strings also works on Numpy arrays. A few examples:


```python
# Create a 4x4 matrix
A = np.arange(1/4, 17/4, 1/4).reshape((4, 4))
print(A)

# Print some slices
print(A[1, 3])
print(A[0, :])    # row 0, all elements
print(A[:, 2])    # all elements, column 2
print(A[2:, :-1]) # rows from 2 until the last, columns from 0 until the second-to-last

print(A[0::2, 1]) # every second row starting from 0, column 1
```

---
**📚 Learn more:**

- [Indexing](https://numpy.org/doc/stable/reference/arrays.indexing.html) - Numpy documentation
- [Indexing basics](https://numpy.org/doc/stable/user/basics.indexing.html#basics-indexing) - Numpy documentation

---
🚩 ***Exercise 6:*** Define a variable `n` with some integer value $2 \leq n \leq 50$ of your choice. Using two nested for-loops, create an array `A` representing a matrix $A \in \mathbb{R}^{n\times n}$, where the element $(i, j)$ in row $i$, column $j$ is given by
$$
a_{ij} = \begin{cases}
i + 2j, \quad & \text{if } i < j, \\
ij, \quad &\text{otherwise,}
\end{cases}
\quad \text{for } i, j = 0, 1, 2, \dots, n-1.
$$

To check your code, for example, for $n=4$, you should have
$$
A = \begin{pmatrix}
0 & 2 & 4 & 6 \\
0 & 1 & 5 & 7 \\
0 & 2 & 4 & 8 \\
0 & 3 & 6 & 9
\end{pmatrix}.
$$

*Hint: before being able to set individual values in the array, you will first need to create an array of the correct shape. For instance, you could start with an array full of zeros.*


```python
n = 10
A = np.zeros((n,n))
for i in range(n):
    for j in range(n):
        if i < j:
            A[i, j] = i + 2*j
        else:
            A[i, j] = i * j
print(A)
```

    [[ 0.  2.  4.  6.  8. 10. 12. 14. 16. 18.]
     [ 0.  1.  5.  7.  9. 11. 13. 15. 17. 19.]
     [ 0.  2.  4.  8. 10. 12. 14. 16. 18. 20.]
     [ 0.  3.  6.  9. 11. 13. 15. 17. 19. 21.]
     [ 0.  4.  8. 12. 16. 14. 16. 18. 20. 22.]
     [ 0.  5. 10. 15. 20. 25. 17. 19. 21. 23.]
     [ 0.  6. 12. 18. 24. 30. 36. 20. 22. 24.]
     [ 0.  7. 14. 21. 28. 35. 42. 49. 23. 25.]
     [ 0.  8. 16. 24. 32. 40. 48. 56. 64. 26.]
     [ 0.  9. 18. 27. 36. 45. 54. 63. 72. 81.]]



```python
%run scripts/show_solutions.py week03_ex6
```

---
## 4. Creating arrays by copying other arrays

Sometimes, it's convenient to create a copy of an existing array. The **safest** way to do this is by using the `.copy()` method:


```python
# Create a vector A
A = np.linspace(0, 100, 11)

# Create another vector B, with the same elements as A
B = A.copy()

# Display both
print(A)
print(B)
```

This makes a **copy** of all the numbers in memory currently stored in the box labelled `A`, puts them in their own drawers, and creates another box called `B` to put them in. Then, we can make changes to the elements of `A` or `B` without affecting the other:


```python
# Change the first 3 elements of A
A[:3] = 1

# Display A and B
print(A)
print(B)
```

But what happens if we don't use `.copy()`?


```python
# Create a vector A
A = np.linspace(0, 100, 11)

# Create another vector B, with the same elements as A
B = A

# Display both; so far, so good
print(A)
print(B)

# Change the first 3 elements of A
A[:3] = 1

# Display A and B; B has also been changed!
print(A)
print(B)
```

Remember ***Exercise 4*** from the Week 1 tutorial sheet. If we don't explicitly ask Python to create a copy of the values in `A` (by using `.copy()`), it simply *reuses* the values that are already stored in memory in the box labelled `A`, and just sticks another label on the same box, called `B`. Now, the **same objects** can be referred to using either the label `A` or the label `B`; this means that if we change some of the contents of `A`, we automatically change the same elements in `B`.

---
**📚 Learn more:**
- [ndarray.copy()](https://numpy.org/doc/stable/reference/generated/numpy.ndarray.copy.html)
- [Copies and Views](https://numpy.org/doc/stable/user/quickstart.html#copies-and-views) - NumPy documentation
- [How to create an array from existing data](https://numpy.org/doc/stable/user/absolute_beginners.html#how-to-create-an-array-from-existing-data) - NumPy beginner's guide

---
## 5. Matrix/vector products

We saw in section 2.3 that `A * B` returns the *element-wise* product of `A` and `B`. The function `np.matmul()` (and its operator alias, `@`) allows to compute **matrix products**:


```python
A = 2 * np.ones([2, 4])
print("A =")
print(A)

B = 0.4 * np.eye(4)
print("B =")
print(B)

v = np.random.random(4)
print("v =")
print(v)

# Products AB and BA^T
print(np.matmul(A, B))
print(np.matmul(B, A.T))
print(np.matmul(B, A.transpose()))

# Products Av and v^T B
print(np.matmul(A, v))
print(np.matmul(v, B))

# Dot product of v with itself
print(np.matmul(v, v))

# We can also use the operator @ to do exactly the same thing:
print(B @ A.T)
print(A @ v)
print(v @ B)
print(v @ v)
```

Note the different behaviours of `np.matmul()`, depending on the dimensions of its arguments. All cases are outlined in the documentation linked below. Also note the `.T` notation used to **transpose** arrays.

---
🚩 ***Exercise 7:*** Write a function `mat_power()` which takes 2 input arguments:
- a square array `A` representing a square matrix $A$,
- a positive integer `n`,

and returns an array the same size as `A`, representing $A^n$, the $n$th power of the matrix $A$ (that is, the matrix $A$ multiplied by itself $n$ times). You are **not** allowed to use `np.linalg.matrix_power()` -- code it yourself using repeated matrix multiplication.

Then, define a variable `A` equal to the matrix

$$A = \left( \begin{array}{cc} 0 & 1 \\ -1 & 0 \end{array} \right),$$

and use your function to compute and display $A^n$ for different positive integer values of $n$. What happens when $n$ is divisible by $4$?


```python
def mat_power(A, n):
    An = np.copy(A)
    size = A.shape[0]
    for i in range(size): 
        for j in range(size):
            A[i, j] = np.dot(A[i, :], A[:, j])
    return An
A = np.array([[0, 1], [-1, 0]])
for n in range(10):
    print(f"n)
```

    [[ 0  1]
     [-1  0]]



```python
%run scripts/show_solutions.py week03_ex7
```
