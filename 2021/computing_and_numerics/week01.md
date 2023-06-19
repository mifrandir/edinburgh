# Week 1 worksheet: Introduction to Python

This is a first notebook to get yourself used to the idea of doing some programming in Python, and to familiarise yourself with the Jupyter notebook format.

The best way to learn programming is to write code. Don't hesitate to edit the code in the example cells, or add your own code, to test your understanding. You will find practice exercises throughout the notebook, denoted by 🚩 ***Exercise $x$:***.

#### Suggested reading

You will see lists of links to further reading and resources throughout the worksheets, in sections titled **📚 Learn more:**. These will include links to the Python documentation on the topic at hand, or links to relevant book sections or other online resources. Unless explicitly indicated, these are not mandatory reading, although of course we strongly recommend that you consult them!

In each worksheet, we'll also use these acronyms to indicate relevant sections of the two recommended textbooks:
- **PCP**: S. Linge and H. P. Langtangen, ***Programming for Computations - Python***, Springer, 2016.
- **ASCP**: P.R. Turner, T. Arildsen, and K. Kavanagh, ***Applied Scientific Computing with Python***, Springer, 2018.

#### Displaying solutions

Solutions will be released at the end of each week, as a new `.txt` file in the same GitHub repository. After pulling the file to your computer, **run the following cell** to create clickable buttons under each exercise, which will allow you to reveal the solutions.


```python
%run scripts/create_widgets.py week01
```

    Solutions not yet released!


*How it works: You will see cells located below each exercise, each containing a command starting with `%run scripts/show_solutions.py`. You don't need to run those yourself; the command above runs a script which automatically runs these specific cells for you. The commands in each of these cells each create the button for the corresponding exercise. The Python code to achieve this is contained in `scripts/show_solutions.py`, and relies on [IPython widgets](https://ipywidgets.readthedocs.io/en/latest/examples/Widget%20Basics.html) --- feel free to take a look at the code if you are curious.*

---
### Book sections

- **PCP**: sections 1.1, 1.2, 1.3, 1.4 -- particularly if you are a new programmer!
- **ASCP**: section 2 -- this is about how numbers are represented in the computer's memory, particularly floating point numbers. The later sections are a little bit advanced for now, but we will get back to it later in the semester.

---
# 1. Basic concepts

## 1.1. Jupyter notebooks

A **Jupyter notebook** allows to present text, typeset maths, images, and interactive Python code together in one document. Jupyter notebooks are edited and viewed in a web browser.

A notebook consists of several **cells**, which can be of 2 main types:
* **Markdown cells**, like this one, contain text formatted using Markdown. They can be edited by double-clicking on them. Markdown syntax is straightforward -- you can double-click on the Markdown cells in this notebook to view the source text. Markdown syntax also supports LaTeX typesetting for maths, both inline using `$...$`, e.g. $f: \mathbb{R}^2 \to \mathbb{R}$, and in display mode using `$$...$$`, e.g.

$$ \frac{\partial f}{\partial y} = 2e^{-x} \cos(y). $$

* **Code cells**, like the one below, in which we can type and run Python code interactively. They are indicated by `In [ ]:` on the left hand side:


```python
print('This is a Python code cell!')
```

To **run** a cell (i.e. to typeset Markdown cells or to execute code cells), click the "<kbd>>| Run</kbd>" button on the toolbar above, or press <kbd>Ctrl</kbd> + <kbd>Enter</kbd>.

---
**📚 Learn more:**
* [Jupyter Notebook documentation](https://jupyter-notebook.readthedocs.io/en/stable/)
* [Project Jupyter](https://jupyter.org/)
* [A gallery of interesting Jupyter notebooks](https://github.com/jupyter/jupyter/wiki/A-gallery-of-interesting-Jupyter-Notebooks)
* [Markdown cheat sheet](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet)
---

## 1.2. Python code cells

Let's look at a Python code cell. Select the cell below by clicking on it, and run it:


```python
print(21 + 456)
```

    477


If this worked correctly, you should now see the result displayed just under the code cell. You can edit the code -- try changing the numbers and running the cell again.

How it works:
* `print()` is a Python **function**.
* A function performs some predefined tasks given some *input arguments*.
* Here, we have one input argument (the result of `21 + 456`).
* The function `print()` performs the task of displaying that input on the screen.

Note that each time you run the cell, the number inside `In [x]:` (on the left of the cell) increments by 1 -- this indicates the number of inputs made to Python in a session.

While we are here, let's do the typical first program that you are usually directed to write in a new programming language -- a "Hello World!" program. This is to get the computer to print out
```
Hello World!
```

to the screen. It is a task which has become a cliche to write as a first program (see [here](http://helloworldcollection.de/) for lots of different examples). In Python, it looks like:


```python
print('Hello World!')
```

    Hello World!


Here, the quotation marks `'...'` around the text to be printed out indicate a **string** object --- more on this later.

---

🚩 ***Exercise 1:*** In the empty code cell below, type Python code to display the string `Exercise 1`, and run the cell.


```python
print("Exercise 1")
```

    Exercise 1



```python
%run scripts/show_solutions.py week01_ex1
```


    Button(description='Reveal solution', style=ButtonStyle())



    Output(layout=Layout(border='1px solid green'))


---
## 1.3. Using Python as a calculator

Python understand basic operations, like addition, subtraction, multiplication, etc.

---

🚩 ***Exercise 2:*** Try to figure out what the following operators do:


```python
print(2 + 3)
print(2 - 3)
print(2 * 3)
print(2 / 3)
print(2 ** 3)

print(38 / 5)
print(38 // 5)
print(38 % 5)
```

    5
    -1
    6
    0.6666666666666666
    8
    7.6
    7
    3



```python
%run scripts/show_solutions.py week01_ex2
```

---
## 1.4. Importing modules for more functionality

Now, we would like to do more than these basic mathematical operations. Python extends its basic functionality by having many additional **modules**, which provide more commands (e.g. functions, constants...).

For mathematical computations, the `numpy` module is particularly useful (we will see it a lot this semester!). In order to use the commands provided by a module, we need to **import** it first, as follows:


```python
import numpy
print(numpy.sin(numpy.pi / 4))
```

    0.7071067811865475


Running this cell should display the value of $\sin(\pi/4)$ (in radians). Note that the functions, constants, etc. contained in a module are accessed by prefixing them with the name of the module, followed by a `.` -- in the example above, we tell Python to find the `sin()` function in the `numpy` module, by typing `numpy.sin(...)`.

Module names are typically abbreviated, in order to avoid cluttered code -- for example, `numpy` is usually abbreviated to `np`. To do this, the `import` statement is modified to


```python
import numpy as np
print(np.sin(np.pi / 4))
```

    0.7071067811865475


You can think of `np` here as a nickname we give to `numpy`.

---
**📚 Learn more:**
* [Mathematical functions - Numpy](https://docs.scipy.org/doc/numpy/reference/routines.math.html)
* [Modules - The Python Tutorial](https://docs.python.org/3/tutorial/modules.html)
---

🚩 ***Exercise 3:*** Using Numpy functions and constants, compute and print $\sqrt{2} \cos(\frac{2\pi}{5})$.


```python
print(np.sqrt(2)*np.cos(2*np.pi/5))
```

    0.43701602444882115



```python
%run scripts/show_solutions.py week01_ex3
```

---
# 2. Variables and objects

In Python, all data is represented as **objects** of different **types**. So far, we have used *number objects* to do some basic arithmetic (e.g. `0.5`, `np.pi`...); these are understood by Python to represent numbers. We have also used *string objects* to manipulate text (`'Hello World!'`).

A **variable** in Python is simply a *label* to a location in memory, where an object is stored -- we often say that a variable *points* or *refers* to an object. Variables are what allows you, as the programmer, to interact with data.

---
**📚 Learn more:**
* [Basic Python semantics: Variables and objects](https://jakevdp.github.io/WhirlwindTourOfPython/03-semantics-variables.html) - Whirlwind Tour of Python, Jake VanderPlas
* [Data model: objects, values, and types](https://docs.python.org/3/reference/datamodel.html) - Python documentation
* [Introducing Python object types](https://www.oreilly.com/library/view/learning-python-3rd/9780596513986/ch04.html) - Learning Python, 3rd ed., Mark Lutz, O'Reilly
* [Naming and binding](https://docs.python.org/3/reference/executionmodel.html#naming-and-binding) - Python documentation
---


## 2.1. Variable assignment

The equal sign `=` is used to **assign** an object with a particular value to a variable. In Python, this operation is referred to as *name binding* --- a name is bound to an object.

For example, let's create 3 variables `a`, `b`, and `c`, and assign them the values $1/2$, $6$, and $1/2 + 6$ respectively:


```python
a = 0.5
b = 6
c = a + b
```

What happens exactly? We can visualise the computer's memory as a lot of "boxes" or "drawers", in which we can store some objects:

<div style="width:90%;">

| `a = 0.5` | `b = 6` |
|:---:|:---:|
| ![Assigning 0.5 to a](graphics/var1.png) | ![Assigning 6 to b](graphics/var2.png) |
| **1.** A number object is created with value 0.5, and stored somewhere in memory. We label its location `a` -- we *assign* the value 0.5 to the variable `a`. | **2.** A number object is created with value 6, and assigned to the variable `b`. |
    
---
    
| `a + b` | `c = a + b` |
|:---:|:---:|
| ![Reading a and b from memory](graphics/var3.png) | ![Computing a+b, assigning result to c](graphics/var4.png) |
| **3.** Two number objects are *read* from the memory using their *names*, `a` and `b`. | **4.** Their values are added together, and the result (a new number object, with value 6.5) is assigned to the variable `c`. |

</div>

The memory now holds three number objects, with values $0.5$, $6$, and $6.5$, in locations labelled `a`, `b`, and `c` respectively:

<img alt="Final state of the memory" src="graphics/var5.png" width=400/>

To display these values on the screen, we must explicitly instruct Python to `print()` them, as before:


```python
print(b)
print(c)
```

    6
    6.5


The `print()` function will look for the locations in memory labelled `b` and `c`, and display the contents on the screen.

Note that in an assignment, the **right-hand side** of the `=` sign is always *evaluated* (calculated) **first**; then, the resulting value is assigned to the variable on the left-hand side. That is, Python first creates the object and stores it in memory, and then sticks the label on the drawer.

---
**Note:** when starting a Jupyter Notebook, we start an interactive *session*. This means that if a variable is created somewhere in the notebook, then it will be available for the rest of the session (unless explicitly deleted), and accessible from any code cell.

For example, after assigning values to `a`, `b`, and `c` here (and running the cell), typing `print(a)` in the first (or any other) code cell in the notebook will display `0.5` -- try it for yourself.

---

🚩 ***Exercise 4:*** Try to work out, before running the cell, what this will display. Run the cell to verify.


```python
x = 1
y = x
x = 2
print(y)

a = 8
a = a + 1
print(a)
```

    1
    9


Let's go through the first example with the drawer analogy:

| `x = 1 ` | `y = x` | `x = 2` |
|:---:|:---:|:---:|
| ![x = 1](graphics/w1_ex4_1.png) | ![y = x](graphics/w1_ex4_2.png) | ![x = 2](graphics/w1_ex4_3.png) |

The key here is that `y = x` does *not* mathematically equate `x` and `y`. Think like a computer (or a robot), not a mathematician --- these are commands, with specific results, which Python executes in the order you specify.

There is another interesting feature here which is particular to Python, and will have important consequences later. Here, when executing the line `y = x`, **no new object is actually created**, because that is simply not necessary. We are not asking Python to compute (or *interpret*) any new values: the right-hand side of `y = x` is just `x`, a variable -- that is, if you recall, a direct reference to an object in memory. Python simply follows that reference to find the drawer where that object is stored, and adds an extra label to it. Now, both `x` and `y` refer to the same object.

Then, on the next line, the right-hand side of `x = 2` is a number --- now, Python does need to create a new object with value 2. Since `x` cannot refer to two different objects at once (otherwise, for instance, what would be the result of `print(x)`?), the label `x` is removed from the first object, and assigned to the new object. Now, only `y` is left pointing at 1.

---

## 2.2. Data types

All Python objects have a specific **type**, meaning that they contain a certain type of data (e.g. letters, numbers, arrays...). Objects of different types are stored differently in memory, have different *attributes*, and different operations can be performed with them.

The function `type()` can be used to find out the type of an object.

### Strings

You have already seen **string** objects -- they are defined as a collection of text characters, surrounded by quotation marks, single or double. The string type is denoted by `str`. For example:


```python
test_string = "Hello I am a string of letters!"
print(test_string)
print(type(test_string))

second_test_string = 'I am also a string'
print(second_test_string)
```

It is possible to access a particular character in a string given its position, by **indexing** the string variable. The command `test_string[i]` returns the $i+1$th character in `test_string` (indices start from zero), and the command `test_string[-j]` returns the $j$th character, *starting from the end*. For example


```python
print(test_string[0])
print(test_string[4])
print(test_string[-1])
print(test_string[-3])
```

---

🚩 ***Exercise 5:*** Create a string variable `my_string`, holding some text characters of your choice. Create an integer variable `m` with some value $m$, and an integer variable `n` with some value $n$, with $m>n$. Then, use these to print the $(m+n)$th character and the $(m-n)$th character of `my_string`.

For example, if $m=3$ and $n=2$, print the 5th and the 1st characters.

*Hint: You can use two separate `print()` commands. Remember that indices start from zero!*


```python

```


```python
%run scripts/show_solutions.py week01_ex5
```

---
### Booleans

A Boolean object can only take one of two values: `True` or `False`. The Boolean type is denoted by `bool`.

We can use simple logical operators such as `and`, `or`, and `not` with Booleans. For example


```python
a = True
b = False

print(type(b))
print(a and b)
print(a or b)
print((not a) or b)
```

We can also use **comparison operators** on number and string objects to construct Boolean objects. For example


```python
x = 3
y = 4

print(x < y)
print(x == y)
print(x == y or 5 >= 2)
print(x != y)

z = x > y
print(z)
```

Note, in particular, that `==` tests whether two values are equal, and `!=` tests whether they are different.

---
🚩 ***Exercise 6:*** Create two variables `u` and `v`, assigned with real non-zero values $u$, $v$ of your choice. Create a third variable, called `same_sign`, assigned with a Boolean object which is `True` if $u$ and $v$ have the same sign, and `False` if they have different signs. Test that `same_sign` is assigned with the correct value for different choices of `u` and `v`.


```python
u = v = 1.0
same_sign = u > 0 == v > 0
```


```python
%run scripts/show_solutions.py week01_ex6
```

---

### Integers

Python can represent integers with unlimited precision (i.e., arbitrarily large, as long as there is space left in memory to store them!). The integer type is denoted by `int`. We can add, subtract, multiply, and divide integers in the usual way:


```python
print(3 + 7)
print(375 - 2558)
print(913875 * 2193748502)
print(34 ** 312)

print(35 // 5)
print(39 // 5)
print(35 % 5)
print(39 % 5)
```

    10
    -2183
    2004811912265250
    6628604650429381411030994053290341677785852032240966734916752011875046065226849212544518749011235196437080686738791703396456346507989122736407730087175025745973944468858394831115453205834567163802135857322817343707111647064949082721543445931003408907702695401012081967252498211853048757323682650680092972869568712201407858929208808823038702433692420753857902772125744915235844644837505472070180195471621626366348486168500589857359485796827554403611932753308905789688138019373056
    7
    7
    0
    4


---
### Floating point numbers

Floating point numbers are the ubiquitous way to represent real numbers on a computer.

A bit of background: Python's `float` type corresponds to the IEEE 754 double-precision format. In this representation, a fixed amount of memory (64 bits) is allocated to store a binary number (a number made of 0s and 1s): 1 bit for its sign, 52 bits for its *significand* (corresponding to ~16 decimal digits), and 11 bits for its *exponent*.

This representation is similar to what we call "scientific notation" for decimal numbers. A rational number $x$ can be represented in scientific notation as

$$
x = m \times 10^n,
$$

where $m$ is the significand and $n$ the exponent. Representing a real number in double-precision floating-point format can be compared to writing $m$ using only the first significant digits of $x$ -- for example, $x = 1234567 \approx 1.23 \times 10^6$.

Let's try using these `float` number objects:


```python
print(3.456 + 11.888)
print(99.9 / 0.1)
print(2.0 * 11.4)
print(1.5e-5 + 1.0e-6)

print(type(2.0 * 11.4))

import numpy as np
print(np.sqrt(2.0))
```

Here, we have done some basic calculations using floating point numbers. Note that the way to write, e.g., $1 \times 10^{-5}$ is to write `1.0e-5`, the `e` here meaning "$\times 10$ to a power" (*not* $e$ as in $e^x$).

---
**📚 Learn more:**
* [Double-precision floating-point format - Wikipedia](https://en.wikipedia.org/wiki/Double-precision_floating-point_format)
* [What Every Computer Scientist Should Know About Floating-Point Arithmetic](https://docs.oracle.com/cd/E19957-01/806-3568/ncg_goldberg.html) -- a great reference!
* [Floating point arithmetic: Issues and limitations - Python documentation](https://docs.python.org/3/tutorial/floatingpoint.html)
* **ASCP** section 2
---

🚩 ***Exercise 7:*** Find a *small* floating-point value `x` such that `print(x == 0)` displays `False`, but `print(x + 1 == 1)` displays `True`. Can you explain what you think is happening?

*Hint:* this section of the notebook contains a clue as to what the order of magnitude of `x` may be.


```python
x = 1.0
while x + 1 != 1:
    x /= 10.0
if x != 0.0:
    print(f"{x:.16f}")
```

    0.0000000000000001



```python
%run scripts/show_solutions.py week01_ex7
```
