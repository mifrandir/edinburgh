# Week 2 worksheet: Lists, loops, and functions

So far, we have seen object types consisting of essentially a single item of data. It is useful to have data types that can represent more than a single value -- for instance, in mathematics, we might want to represent a matrix or a vector. Python offers a number of different built-in object types to do this, called **containers** -- because they can contain other objects with different types. This week, we introduce **lists** as our first type of container.

We have also seen that Python executes a set of commands from top to bottom, in the order in which you have scripted them. *Control flow* lets you decide *when*, *how many times*, or *under what condition(s)* to execute certain parts of your code, allowing you more control over the structure (or *flow*) of the code. This week, we also introduce our first control flow tools: **loops** and **functions**.

The best way to learn programming is to write code. Don't hesitate to edit the code in the example cells, or add your own code, to test your understanding. You will find practice exercises throughout the notebook, denoted by 🚩 ***Exercise $x$:***.

#### Displaying solutions

Solutions will be released one week after the worksheets are released, as a new `.txt` file in the same GitHub repository. After pulling the file to your workspace (either your computer or your Noteable server), run the following cell to create clickable buttons under each exercise, which will allow you to reveal the solutions.


```python
%run scripts/create_widgets.py week02
```

    Solutions not yet released!


---
### Book sections

- **PCP**: sections 3.1, 3.2, 4.1, 4.2, 5.1

---
## 1. Lists

Lists are what they sound like -- a list is an object which can contain a number of other objects, in a certain order.

### 1.1. Defining lists

A list can be defined by listing its elements inside square brackets `[...]`, separated by commas. For example, let us create a few lists, and assign them to variables:


```python
a = [1, 2, 3, 10, 6]
b = ['Hi', 'how', 'are', 'you?']
c = ['my', 1, 4.5, ['you', 'they'], 432, -2.3, 33]
d = []

print(a)
print(b[0])
print(c[-3])
```

A few important things to note here:
* An **empty list** is created by typing square brackets with nothing in between `[]`.
* A list can contain **mixed data** -- its elements need not have the same type.
* A list can contain other lists -- in fact, in general, containers may be **nested** in other containers.
* The individual elements of a list are **indexed** -- they can be accessed in a similar way to the characters in a string, by referring to their position in square brackets. Remember that indexing in Python starts from `0`.

We can go back to the drawer analogy to try and visualise how lists are stored in memory. For instance, consider the list `a` here:

<div style="width:70%;margin:auto;">

| `a = [1, 2, 3, 10, 6]` |
|:--:|
| ![The list a in memory](graphics/lists.png) |

</div>

The objects are created and stored in memory as before, but now they are put in a list container labelled `a` (the yellow box/drawer), in a specific order. To access any one particular object, we use its *index* in the list (the yellow label), which indicates its position.

The following examples demonstrate some of the ways to manipulate lists -- make sure to run the previous code cell first to define `a`, `b`, and `c`. Don't hesitate to try more examples to test your understanding.


```python
# Join two lists together
print(a + b)

# Find the length of a list
print(len(b))

# Append (add) an item to the end of a list
c.append('extra')
print(c)

# Print the 2nd element of the 4th element of c
print(c[3][1])

# Sort a list
print(sorted(a))

# Create a list with 12 repetitions of the same sequence
print(12 * [3, 0, 'y'])

# Check if something is in a list
print('my' in c)
print('you' in c)
print('you' in c[3])
print(7 in a)
```

---
🚩 ***Exercise 1:*** A list of numbers can be used to represent, for example, a vector. Create a list `my_mat` to represent the $2 \times 2$ identity matrix, that is

$$
\begin{bmatrix}
1 & 0 \\
0 & 1
\end{bmatrix}.
$$

Then, modify `my_mat` to represent the $3\times 3$ identity matrix, using the `.append()` method.

*Hint:* you can think of a matrix as a collection of row vectors.


```python
my_mat = [[1, 0], [0, 1]]
my_mat.append([0,0,1])
my_mat[0].append(0)
my_mat[1].append(1)
```


```python
%run scripts/show_solutions.py week02_ex1
```

---
### 1.2. Slicing

So far, we have seen how to create and manipulate lists, and how to operate on any single element of a list. It is often convenient to access several list elements at a time. **Index slicing** allows us to extract a new list from any subsequence of an existing list. For example:


```python
a = [2, 5, 4, 8, 8]
print(a[1:3])
print(a[2:])
print(a[:-2])
```

    [5, 4]
    [4, 8, 8]
    [2, 5, 4]


Index slicing uses the colon `:` character. For instance, the command `print(a[2:])` displayed all elements of `a`, starting from the third element (with index `2`). The general syntax to access a subsequence of a list `l` is as follows:
```python
l[start:stop]        # from start to stop-1
l[start:]            # from start to len(l)-1
l[:stop]             # from 0 to stop-1
l[start:stop:step]   # from start to stop-1, with increment step
l[::step]            # from 0 to len(l)-1, with increment step
l[::], l[:]          # all the elements
```

A couple of things to note:
* As was the case for indexing single elements, we can use negative indices when slicing to indicate positions starting from the end of the list.
* The second index `stop` indicates the **first element of `l` which is not accessed**. In other words, `l[i:j]` returns `[l[i], l[i+1], ..., l[j-2], l[j-1]]`, and **excludes** `l[j]`. You can see this in the first example above: `a[1:3]` returned `[a[1], a[2]]`.
* If `j` $=$ `i`, then `a[i:j]` and `a[i:j:k]` are empty lists, for any value of `k`.
* If `j` $<$ `i`, then `a[i:j]` and `a[i:j:k]` are empty lists, for *positive* values of `k`.

*Note:* index slicing works on any type of *sequence*, not just lists. For instance, a string is another type of sequence in Python.

---
**📚 Learn more:**
* [Lists - An informal introduction to Python - Python 3.7 documentation](https://docs.python.org/3/tutorial/introduction.html#lists)
* [More on lists - Python 3.7 documentation](https://docs.python.org/3/tutorial/datastructures.html#more-on-lists) - more examples of list methods, to perform different operations on lists.
* **PCP**: section 5.1

---

🚩 ***Exercise 2:*** Consider the list `m` below. What is the most concise way to create a new list `m_back`, which takes as value the list `m`, backwards? In other words, `print(m_back)` should display
```
['e', 'd', 'c', 'b', 'a']
```


```python
m = ['a', 'b', 'c', 'd', 'e']
m_back = list(reversed(m))
print(m_back)

# or
m_back = m[::-1]
print(m_back)

```

    ['e', 'd', 'c', 'b', 'a']
    ['e', 'd', 'c', 'b', 'a']



```python
%run scripts/show_solutions.py week02_ex2
```

---
🚩 ***Exercise 3:*** Create a variable `n`, with value $n \in \mathbb{N}$ such that $5 \leq n \leq 20$. Create a list `my_list` of $n - 1$ ones. Then, append $\pi$ to the list -- its length should now be $n$. Finally, change the value of the $3$rd element of `my_list` to be the sum of its last $n - 4$ elements.

For example, for $n=6$, `print(my_list)` should display
```
[1, 1, 4.141592653589793, 1, 1, 3.141592653589793]
```

*Hint:* You may wish to use the [sum()](https://docs.python.org/3/library/functions.html#sum) function (a list is a type of *iterable*).


```python
import math
n = 6
my_list = [1 for _ in range(n-1)]
my_list.append(math.pi)
my_list[2] = sum(my_list[-(n-4):])
print(my_list)
```

    [1, 1, 4.141592653589793, 1, 1, 3.141592653589793]



```python
%run scripts/show_solutions.py week02_ex3
```

---
## 2. Loops

More often than not, an important motivation for using programming to solve problems is the computer's ability (relative to the human brain's struggle) to perform a given set of computations *many* times over, very quickly. Loops allow us to do just that: give the computer a set of instructions and a stopping point, and it will execute these instructions over and over until that point is reached.

There are two types of loop available in Python:
* **`for`** loops allow us to perform a set of instructions *for* a pre-determined number of *iterations*;
* **`while`** loops allow us to perform a set of instructions *while* a given condition is true. The number of iterations need not be known in advance.

---
### 2.1. `for` loops

`for` loops iterate over the elements of a **sequence** (for example a list or a string), in the order in which they appear in that sequence. The syntax is as follows:
```python
for i in my_sequence:
    [some instructions]
```
The `for` loop uses a *placeholder variable* (`i` in the example above), which is assigned with each element of `my_sequence`, in turn.

For example, we can loop over a list to print each of its elements separately:


```python
a = [1, 2, 3, 10, 6]

for element in a:
    print(element)
    
print('These are all the elements of a.')
```

When the loop starts, `element` is assigned the value `a[0]`, and the instructions in the loop are performed. Here, we just have one instruction, `print(element)`: the value `a[0]` is printed.

When all instructions have been executed, we go back to the start of the loop, and `element` is assigned the next value `a[1]`. The instructions are executed, and we start again. The loop **terminates** when we run out of elements in `a` -- here, after 5 iterations. The rest of the code is then executed as usual.

<div style="width:60%;margin:auto;">

![Looping over the elements of a](graphics/forloop.png)

</div>

Note that after the loop terminates, the variable `element` is not deleted; it remains assigned with the last value it was assigned by the loop --- here, `a[4]`. (You can see this by printing its value once *after* the loop.)

#### Delimiting code blocks

A very important thing to note here is **indentation**. The way Python knows where the loop instructions start and end is by looking at the indentation level of the commands following the `for` statement. As soon as the indentation level is brought back to the same level as the `for` statement, we are back in the main part of the code; any commands from then on are not part of the loop.

---
**Note:** Briefly going back to code style, it is standard in most style guides to count 4 spaces as 1 indentation level. Most of the time, Jupyter indents and de-dents code automatically, by adding or removing 4 spaces at the start of lines when it deems it relevant.

You can select one or more lines in any code cell, and press <kbd>Tab</kbd> to indent by 1 level.

---
🚩 ***Exercise 4:*** Indent the last line in the code cell above, by adding 4 spaces (either manually, or by inserting a <kbd>Tab</kbd> character, which will automatically be converted to 4 spaces) at the start of the line. Run the cell again -- what happens?

---

### 2.2. Ranges

There is another *sequence type* which we haven't mentioned so far, but is often useful in conjunction with `for` loops: the `range` type. A range is a sequence of increasing or decreasing integers, and can be created using the `range()` function, as follows:
```python
range(j)             # 0, 1, 2, ..., j-1
range(i, j)          # i, i+1, i+2, ..., j-1
range(i, j, k)       # i, i+k, i+2k, ..., i+m
```
where `m` is the largest multiple of `k` such that `i + m` $\leq$ `j - 1`. Note that, as was the case for index slicing, **the stopping index `j` is the first value which is *not* included in the range**. A few examples:


```python
print(range(5))
print(list(range(5)))
print(list(range(10, -10, -3)))
```

Note that, to print all elements of a range object, we first **cast** it to a list (i.e. manually change its *type* to `list`, by using the function `list()`).

Now, let us see how ranges can be helpful with `for` loops. For example, consider the sum
$$
S = \sum_{i=0}^{10} i.
$$
One way to compute this sum would be as follows:


```python
# Start our sum at zero
S = 0

# Loop over indices 0 to 10, inclusive, to add each i one by one
for i in range(11):
    S += i    # this is a shortcut for S = S + i

print(S)
```

---
***Note:*** when looping over a **sequence** (e.g. a list or a string --- anything with elements indexed by number), you can either loop over the elements or over the indices. The two loops here produce the same result:


```python
a = [2, 5, 7, 2, 1]

# Looping over the list by element
for element in a:
    print(element)
    
# Looping over the list by index
for idx in range(len(a)):
    print(a[idx])
```

- At the $n$th iteration in the **first loop**, `element` is assigned the value of the $n$th element of `a`. For instance, at iteration 2 (starting from 0 for convenience), `element` is `7`.
- At the $n$th iteration in the **second loop**, `idx` is assigned the value $n$. For instance, at iteration 2, `idx` is `2`. (Here, strictly speaking, we don't actually loop over `a`, but over a `range` of numbers from 0 to 4, which we use to index the elements of `a` from inside the loop.)

The choice of either looping by element or by index usually depends on the problem --- in general, if the index (the position) of each element is not needed for the computations inside the loop, the first form is preferred.

---
**📚 Learn more:**
* [The `for` statement - Python 3.7 documentation](https://docs.python.org/3/tutorial/controlflow.html#for-statements)
* [Ranges - Python 3.7 documentation](https://docs.python.org/3/library/stdtypes.html#typesseq-range)
* [Looping techniques - Python 3.7 documentation](https://docs.python.org/3/tutorial/datastructures.html#looping-techniques) -- some useful tricks to loop over sequences, including the `enumerate()` and `zip()` functions.
* **PCP**: section 3.1

---
🚩 ***Exercise 5:*** Using a `for` loop, compute and print the product
$$
P = \prod_{j=2}^{n} \left(j^3 + 5j^2 - 3\right),
$$
where $n \geq 2$ is an integer value of your choice. To check your code, the result for $n=20$ is
```
102608796359678464673256924713629769626423839617879814815625.
```


```python
n = 20
p = 1
for i in range(2,n+1):
    p *= i**3 + 5*i**2-3
print(p)
```

    102608796359678464673256924713629769626423839617879814815625



```python
%run scripts/show_solutions.py week02_ex5
```

---
### 2.3. `while` loops

`while` loops are used to repeat a set of instructions *while* a given condition is true. The `while` statement does *not* use any placeholder variables; instead, it must be given a Boolean object (i.e., an expression which evaluates to either `True` or `False`). The syntax is as follows:
```python
while my_condition:
    [some instructions]
```
where `my_condition` has type `bool`. The instructions in the loop are executed repeatedly, until `my_condition` evaluates to `False`, after which the loop terminates.

For example, we can calculate the same sum $S$ as in Week 2, using a `while` loop:
$$
S = \sum_{i=0}^{10} i
$$


```python
S = 0
i = 0

while i <= 10:
    S += i
    i += 1

print(S)
```

Let's break this down:

- We start with assigning `S = 0`, as with the `for` loop. Here, because `while` loops don't assign the placeholder variable by themselves, we need to assign and increment `i` manually.

- We get to the start of the loop, and the condition is checked. Since we start with `i = 0`, the expression `i <= 10` evaluates to `True`, and we can proceed with the first iteration. `i` is incremented by 1 inside the loop.

- For the next iteration, `i = 1`, and `i <= 10` still evaluates to `True` -- we proceed again with the instructions in the loop.

- The 11th iteration ends by assigning `i = 11`. Going back up to the `while` statement, the expression `i <= 10` now evaluates to `False`, and the loop terminates immediately.

For this example, the `for` loop is clearly the better choice, as we already know how many iterations we need to complete the calculation.

---
**📚 Learn more:**
* [First steps towards programming - The `while` loop - Python 3.7 documentation](https://docs.python.org/3/tutorial/introduction.html#first-steps-towards-programming)
* [The `while` statement](https://docs.python.org/3/reference/compound_stmts.html#while)
* **PCP**: section 3.2

---
🚩 ***Exercise 6:*** The Maclaurin series for the exponential function is

$$
e^x = \sum_{n=0}^\infty \frac{x^n}{n!} = 1 + x + \frac{x^2}{2!} + \frac{x^3}{3!} + \frac{x^4}{4!} + \dots
$$

Using a `while` loop, find out how many terms of this series are needed to obtain an approximation of $e^1$ which is accurate to 6 significant digits.

[The documentation for the `math` module](https://docs.python.org/3/library/math.html) may be helpful.


```python
import math
my_e = 0
n = 0
while abs(my_e-math.e) > 10**(-6):
    my_e += 1 / math.factorial(n)
    n += 1
print(n, my_e, math.e)
```

    10 2.7182815255731922 2.718281828459045



```python
%run scripts/show_solutions.py week02_ex6
```

---
## 3. Functions

So far, we have used some of the built-in Python functions (e.g. `print()`, `sorted()`), and functions like `numpy.sin()` or `numpy.sqrt()` which come with the `numpy` module.

You can also define your own custom functions to encapsulate specific subtasks and structure your programs. A **function** is essentially a block of code which only *executes* when the function is *called*.

### 3.1. Defining functions

A Python function can be defined using
```python
def my_func(inputs):
    [function body]
    return outputs
```
where
* `my_func` is the *name* of your function,
* `inputs` are the (zero or more) **input arguments**,
* `[function body]` are the commands to execute upon *calling* the function,
* `outputs` are the (zero or more) **return values** or **output values**.

For example, we can define Python functions to represent mathematical functions. Consider the function

$$
f(x) = \frac{3x - 2}{\sqrt{2x + 1}}.
$$

We can write a function `f()`, which takes a variable `x` as an input argument, with some value $x$, and returns the value of $f(x)$:


```python
import math

def f(x):
    y = (3*x - 2) / math.sqrt(2*x + 1)
    return y
```

The function `f()` is now defined, but note that nothing seems to happen when running the above cell. The function hasn't been **called** (i.e. used), so the instructions in the function body are not executed. Simply speaking, we are merely providing the *Python interpreter* with a set of instructions, so that it knows what to do in case we ask it, for instance, to compute `f(5)`. Functions, like everything else in Python, are also **objects** --- it may be helpful to visualise them as recipes or blueprints.

Now, we can *call* our function if we want to compute $f(x)$ for different values of $x$, instead of having to write the formula several times:


```python
import math

a = 3
b = -0.2
c = math.pi

# Display return values on the screen
print(f(a), f(b), f(c))

# Assign return values to variables
f_pi = f(c)
print('f(x) evaluated at x = pi is', f_pi)
```

The statement `f(a)` *calls* the function `f()`, with the input value `a`. The instructions in the body of the function are then executed; the result is computed using the value of `a`, and is then assigned to a variable `y`. The *value* of `y` is finally *returned* by the function, and can be assigned to variables and manipulated as usual.

| <code>def f(x):<br/>&nbsp;&nbsp;&nbsp;&nbsp;y = (3\*x - 2) / math.sqrt(2\*x + 1)<br/>&nbsp;&nbsp;&nbsp;&nbsp;return y</code> | <code>a = 3<br/>b = -0.2<br/>c = math.pi<br/></code>  | `f_pi = f(c)` |
|:---|:---|:---:|
| ![Defining the function f](graphics/def.png) | ![Assigning a, b, c](graphics/abc.png) | ![Assigning f(c) to f_pi](graphics/func_store.png) |
| **1.** A function object is created as a set of instructions, and named `f`. | **2.** Three number objects are created and respectively named `a, b, c`. | **3.** The function `f` is *called* with input value `c`; the instructions are executed. `f` *returns* a new number object, which we store in memory with the name `f_pi`. |

### 3.2. Return values

When the `return` statement is reached, we **exit** the function -- we *return* to the main program with some output value(s).

Recall that *code blocks* in Python are delimited with indentation levels -- the body of the function is everything under the definition `def my_func(inputs):`, indented by one level. However, when the `return` statement is reached, the function exits immediately, and any code written after it is simply *not executed* -- even if it is still indented, and part of the function body. This is somewhat analogous to how the `break` statement works in loops.

The `return` statement is optional -- a function can perform a set of instructions without returning any value. For instance, the `print()` function itself doesn't *return* any value -- it simply prints the value of its input argument(s) on the screen.

---
**Note:** strictly speaking, all functions return a value. If you omit the `return` statement when writing your function, or if you don't specify any output values, then by default the return value will be `None`. In Python, `None` is the object that represents the absence of a value (although it is, itself, a value).

For example, if we try to assign the output value of `print()` to a variable, we get


```python
a = print('Hello World!')
print(a)
```

When the `print()` function is called the first time, it does its job -- it displays its input argument on the screen. However, it doesn't return anything -- strictly speaking, it returns `None`. The value `None` is therefore assigned to the variable `a`.

<div style="width:50%;margin:auto;">

| `a = print('Hello World!')` |
|:--:|
| ![print displays on screen, but returns None](graphics/print.png) |
| Here, the red card represents a string object with value `Hello World!`. Note that it is never assigned to a variable, and therefore not kept in memory.|

</div>

---
### 3.3. Returning multiple values

To return multiple values from a function, we can list them after the `return` statement, one after another, separated by commas. For instance, let us define a function `sum_diff_prod()` which computes and returns the sum, difference, and product of two given numbers:


```python
def sum_diff_prod(a, b):
    '''
    Computes and returns the sum, difference,
    and product of a and b.
    '''
    return a+b, a-b, a*b

# Print the output of the function as a tuple
print(sum_diff_prod(12, 4))
print(sum_diff_prod(0, -1.2))

# Unpack the output into different variables
s, d, p = sum_diff_prod(-4, -3)
print(s)
print(p)
```

Two things to note:
* Just below the `def` statement, the red string between a pair of triple quotes is called the **docstring** of the function. It describes what the function does -- think of it as a short version of the function's documentation. It is *always* a good idea to write docstrings when defining functions. When the input arguments and output values are not trivially defined, the docstring should list them and give a short description for each.
* If a function returns several output values, they can be **unpacked** to assign the different return values to different variables. For instance, here,
```python
s, d, p = sum_diff_prod(-4, -3)
```
assigns the 3 values returned by `sum_diff_prod()` to the 3 variables `s`, `d`, and `p` respectively.

---
**📚 Learn more:**
* [Defining Functions - Python 3 documentation](https://docs.python.org/3/tutorial/controlflow.html#defining-functions)
* [More on Defining Functions - Python 3 documentation](https://docs.python.org/3/tutorial/controlflow.html#more-on-defining-functions)
* [PEP 257 - Docstring Conventions](https://www.python.org/dev/peps/pep-0257/)
* **PCP**: sections 4.1, 4.2

---

---
🚩 ***Exercise 7:*** Write a function `compute_P()`, which takes 1 input argument `n`, representing an integer $n \geq 2$, and returns the product $P$ defined earlier for that particular value of $n$:
$$
P = \prod_{j=2}^{n} \left(j^3 + 5j^2 - 3\right).
$$

For example, `print(compute_P(7))` should display `13811904975375`.

You can reuse your code from Exercise 5 inside your function.


```python
def compute_P(n):
    p = 1
    for i in range(2,n+1):
        p *= i**3 + 5*i**2-3
    return p
print(compute_P(7))
```

    13811904975375



```python
%run scripts/show_solutions.py week02_ex7
```

---
## Coderunner

Now that you know how to write functions, we can make use of a tool called Coderunner, which hosts the homework quizzes. The quizzes consist of a number of programming questions, which are automatically graded. (If you're familiar with STACK quizzes, these are similar, and use the same system.)

For each question, you will be asked to write functions to perform specific tasks. The question will always state
* the name of the function,
* the number and the type of input arguments it should take (if any), and
* the number and the type of output arguments it should return (if any).

Your function will be tested against a number of test input values, and the output will be compared to the expected output values, to determine whether your function is correct.

This week, there is a practice quiz Q0 on Coderunner, which will **not** count for credit. This is for you to become familiar with how the assessed quizzes work, and how they are graded. You can access it through the link on the Learn page in the Week 2 course materials.
