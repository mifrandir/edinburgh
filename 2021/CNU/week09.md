# Week 9: Fixed point iteration and Newton's method
This week, we continue exploring **root-finding methods**, this time looking at **fixed-point iteration** and **Newton's method**.

The best way to learn programming is to write code. Don't hesitate to edit the code in the example cells, or add your own code, to test your understanding. You will find practice exercises throughout the notebook, denoted by 🚩 ***Exercise $x$:***.

#### Displaying solutions

Solutions will be released one week after the worksheets are released, as a new `.txt` file in the same GitHub repository. After pulling the file to your workspace (either your computer or your Noteable server), run the following cell to create clickable buttons under each exercise, which will allow you to reveal the solutions.


```python
%run scripts/create_widgets.py week09
```

    Solutions not yet released!


---
### 📚 Book sections

- **ASC**: sections **5.3**, **5.4**, **5.6**
- **PCP**: section 7.2

---

## 1. Fixed-point iteration

In Week 8, we implemented the bisection and regula falsi methods to find solutions of nonlinear equations; they are both *bracketing* methods. The methods we will see this week are different: they all start with an initial guess $x_0$, a single value (not an interval), and seek to refine this guess iteratively to eventually converge to the solution.

### 1.1. General construction

Fixed-point iteration seeks a root to a function $F \left( x \right)$ by defining a sequence

$$
  x_{k + 1} = G \left( x_k \right) \quad \textrm{ for } k = 0, 1, 2, \ldots
$$

where $G \left( x_k \right)$ is an **iteration function**, designed so that

$$
F \left( x_* \right) = 0 \quad \text{if and only if} \quad x_* = G \left( x_* \right).
$$

That is, the roots of $F \left( x \right)$ are *fixed-points* of the sequence, and conversely the fixed-points of the sequence are roots of $F \left( x \right)$.

Given an "initial guess" $x_0$, if the sequence converges, i.e. if

$$
  \lim_{k \rightarrow \infty} \left| x_{k + 1} - x_k \right| = 0,
$$

then it must, by construction, converge to a root of the original function $F \left( x \right)$.

---
🚩 ***Exercise 1***: Consider the quadratic polynomial

$$
F(x) = -x^2 - 3x + 2.
$$

Plot $F(x)$ over $[-5, 2]$. Compute and display the exact value of both roots $x_\ast$ of $F(x)$.


```python
import numpy as np
import matplotlib.pyplot as plt

F=np.vectorize(lambda x: -x**2 - 3*x + 2)
xs = np.linspace(-5, 2, 100)
ys = F(xs)
r1, r2 = (3-np.sqrt(9+4*2))/(-2), (3+np.sqrt(9+4*2))/(-2)
fig, ax = plt.subplots()
fig.set_dpi(100)
ax.plot(xs, ys)
ax.hlines(0, xmin=-5, xmax=2)
ax.grid()
```


    
![png](week09_files/week09_3_0.png)
    



```python
%run scripts/show_solutions.py week09_ex1
```


    Button(description='Reveal solution', style=ButtonStyle())



    Output(layout=Layout(border='1px solid green'))


---
🚩 ***Exercise 2***: The roots $x_\ast$ both satisfy

$$
F(x_\ast) = -x_\ast^2 - 3x_\ast + 2 = 0.
$$

Two of the many possible ways to rearrange this equation to something of the form $x_\ast = G(x_\ast)$ are:

\begin{align}
x_\ast &= G_1(x_\ast) = \frac{2}{x_\ast + 3}, \\
x_\ast &= G_2(x_\ast) = -x_\ast^2 - 2x_\ast + 2.
\end{align}

Define two functions `G1(x)`, `G2(x)` corresponding to the above. Then, with a starting guess of $x_0 = -4$, compute $x_{k+1} = G_i(x_k)$ for $k=1, 2, \dots, 7$, and $i=1, 2$. Display all successive values of the guesses $x_k$ for both methods.

Do you observe convergence for both methods? If so, to which root?


```python
def iterate(G, x0, kmax=7):
    x = x0
    for k in range(kmax):
        x = G(x)
        print(x)

G1=lambda x: 2/(x+3)
G2=lambda x: -x**2-2*x+2
iterate(G1, -4)
iterate(G2, -4)
```

    -2.0
    2.0
    0.4
    0.5882352941176471
    0.5573770491803278
    0.5622119815668203
    0.5614489003880984
    -6
    -22
    -438
    -190966
    -36467631222
    -1329888126870853950838
    -1768602429992068534155014726612412013000566



```python
%run scripts/show_solutions.py week09_ex2
```


    Button(description='Reveal solution', style=ButtonStyle())



    Output(layout=Layout(border='1px solid green'))


---
### 1.2. Conditions for convergence

Although there are often many ways to write $F(x) = 0$ as $x = G(x)$, not all of these lead to convergence.

---
#### 🚩 Theorem: Conditions for convergence of fixed point iteration

Let $x_\ast$ be a solution of $F(x) = 0$, and an iteration function $G(x)$ be defined such that $x_\ast$ is a fixed point of $G$, i.e. we have $x_\ast = G(x_\ast)$.

If $G(x)$ is differentiable, the fixed-point iteration $x_{k+1} = G(x_k)$ will converge to $x_\ast$ if
- $|G'(x)| < 1$ in some neighbourhood of $x_\ast$,
- and either:
    - the initial guess $x_0$ is chosen in this neighbourhood, or
    - some later iteration value $x_k$ is in this neighbourhood.
    
---
The proof is given in **ASC section 5.3**, and can be established using the Mean Value Theorem (more about this in Video 1 this week) to write the error $e_{k+1} = x_{k+1} - x_\ast$ in terms of $e_k$, the error at the previous iteration:

$$
|e_{k+1}| = |G'(\eta)| |e_k|, \qquad \text{for some} \: \eta \in (\min(x_k, x_\ast), \max(x_k, x_\ast)).
$$

As long as $|G'(x)| < 1$ in a neighbourhood of $x_\ast$ (which includes $\eta$), then for any $x_k$ in that neighbourhood, we have $|e_{k+1}| < |e_k|$, meaning that the absolute error decreases monotonically for all subsequent iterations, guaranteeing convergence to $x_\ast$.

---
🚩 ***Exercise 3***: Plot $|G_1'(x)|$ and $|G_2'(x)|$ over $[-5, 5]$, with the exact roots of $F(x)$ indicated on the same plot. For either method, are there possible initial guesses which guarantee convergence towards either root?


```python
dG1 = np.vectorize(lambda x: -2/(x+3)**2)
dG2 = np.vectorize(lambda x: -2*x - 2)

xs = np.linspace(-5,5,100)
fig, ax = plt.subplots()
ax.plot(xs, np.abs(dG1(xs)), label="$|G_1'|$")
ax.plot(xs, np.abs(dG2(xs)), label="$|G_2'|$")
ax.set_ylim(0, 1)
ax.vlines([r1, r2], ymin=0, ymax=1000)
ax.legend()
```




    <matplotlib.legend.Legend at 0x7f523a1e8bb0>




    
![png](week09_files/week09_9_1.png)
    



```python
%run scripts/show_solutions.py week09_ex3
```


    Button(description='Reveal solution', style=ButtonStyle())



    Output(layout=Layout(border='1px solid green'))


### 1.3. Convergence rate for fixed point iteration

The next few exercises explore the rate of convergence of fixed point iteration.

---
🚩 ***Exercise 4***: Consider the function

\begin{equation}
  F \left( x \right) = x - x^2 \sin x.
\end{equation}

Plot this function in the interval $x \in \left[ -2, 2 \right]$ and identify the three roots in this interval. 


```python
F = np.vectorize(lambda x: x - x**2 *np.sin(x))
xs = np.linspace(-2,2,100)
plt.plot(xs, F(xs))
plt.hlines(0, -2, 2)
plt.grid()
```


    
![png](week09_files/week09_12_0.png)
    



```python
%run scripts/show_solutions.py week09_ex4
```


    Button(description='Reveal solution', style=ButtonStyle())



    Output(layout=Layout(border='1px solid green'))


---
🚩 ***Exercise 5***: At a root $x_*$ of $F \left( x \right)$ we have

\begin{equation}
  x_* - x_*^2 \sin x_* = 0.
\end{equation}

Given an $\alpha \ne 0$ this is equivalent to

\begin{equation}
  x_* = \left( 1 + \alpha \right) x_* - \alpha x_*^2 \sin x_*.
\end{equation}

This suggests the fixed-point iteration

\begin{equation}\label{eqn:fp2}
  x_{n + 1} = \left( 1 + \alpha \right) x_n - \alpha x_n^2 \sin x_n \quad \textrm{ for } n = 0, 1, 2, \ldots.
\end{equation}


Use a `while` loop to implement fixed-point iteration using the scheme above with an initial guess $x_0 = 1$ and with $\alpha = 1.2$.  You will need to choose an appropriate condition on which to exit the loop.

Hint: you may find it convenient to define a function `G` representing the iteration function.


```python
def iterate_conv(G, x0, e=10**(-5)):
    x = np.inf
    xs = []
    while abs(x - x0) >= e:
        x = x0
        xs.append(x)
        x0 = G(x)
    return x0, np.array(xs)

G=lambda x, a=1.2: (1+a)*x-a*x**2*np.sin(x)
iterate_conv(G, 1)
```




    (1.114152733300346,
     array([1.        , 1.19023482, 1.04014966, 1.16857703, 1.06295896,
            1.15376332, 1.07778144, 1.14318758, 1.08796481, 1.13549645,
            1.09515793, 1.12985775, 1.10031621, 1.12571017, 1.10404756,
            1.1226562 , 1.10676076, 1.12040736, 1.10873999, 1.11875197,
            1.11018675, 1.11753398, 1.11124571, 1.11663824, 1.11202152,
            1.11597974, 1.11259023, 1.11549581, 1.1130073 , 1.11514025,
            1.11331326, 1.11487907, 1.11353775, 1.11468724, 1.11370249,
            1.11454636, 1.11382341, 1.11444292, 1.11391215, 1.11436696,
            1.1139773 , 1.11431118, 1.11402512, 1.11427024, 1.11406022,
            1.11424017, 1.11408599, 1.1142181 , 1.11410491, 1.11420189,
            1.11411879, 1.11419   , 1.11412899, 1.11418126, 1.11413647,
            1.11417485, 1.11414197, 1.11417014, 1.114146  , 1.11416669,
            1.11414896, 1.11416415, 1.11415114, 1.11416228]))




```python
%run scripts/show_solutions.py week09_ex5
```


    Button(description='Reveal solution', style=ButtonStyle())



    Output(layout=Layout(border='1px solid green'))


---
🚩 ***Exercise 6***: Use the function `fsolve()` from `scipy.optimize` to find the root of $F \left( x \right)$ near $x = 1$. Create a plot of error magnitude against iteration number, where the error is the difference between the solution obtained via `fsolve()` (taken as the "ground truth"), and the values for $x_k$ obtained via the fixed-point iteration used in Exercise 5. Use a logarithmic scale for the y-axis.


```python
import scipy.optimize as sop

r = sop.fsolve(F, 1)
_, xks = iterate_conv(G, 1)
xs = list(range(len(xks)))
plt.plot(xs, np.log(np.abs(xks-r)))
```




    [<matplotlib.lines.Line2D at 0x7f521ed70d00>]




    
![png](week09_files/week09_18_1.png)
    



```python
%run scripts/show_solutions.py week09_ex6
```


    Button(description='Reveal solution', style=ButtonStyle())



    Output(layout=Layout(border='1px solid green'))


---
🚩 ***Exercise 7***: Find the root near $x = 1$ using bisection, with an initial interval defined by $a = 0.5$ and $b = 1.5$. In the same figure as the plot for Exercise 6, plot the magnitude of the difference between the root obtained via `fsolve()`, and the values of $\left( a + b \right) / 2$ obtained in each bisection iteration, plotting this error magnitude against iteration number. Which of bisection and fixed point iteration converges faster to the root?


```python
import scipy.optimize as sop

def bisect(F,a,b,e=10**(-5)):
    if F(a) > F(b):
        a,b=b,a
    xs = []
    while True:
        c = (a+b)/2
        print(c)
        xs.append(c)
        if abs(F(c))<e:
            break    
        if F(c) > 0:
            b = c
        else:
            a = c
    return np.array(xs)

r = sop.fsolve(F, 1)
_, xks = iterate_conv(G, 1, e=10**(-5))
xbs = bisect(F,0.5, 1.5)
plt.plot(list(range(len(xks))), np.log(np.abs(xks-r)), label="$\log(|x_k-x*|)$")
plt.plot(list(range(len(xbs))), np.log(np.abs(xbs-r)), label="$\log(|x_b-x*|)$")
plt.legend()
```

    1.0
    1.25
    1.125
    1.0625
    1.09375
    1.109375
    1.1171875
    1.11328125
    1.115234375
    1.1142578125
    1.11376953125
    1.114013671875
    1.1141357421875
    1.11419677734375
    1.114166259765625
    1.1141510009765625





    <matplotlib.legend.Legend at 0x7f521ed0fd30>




    
![png](week09_files/week09_21_2.png)
    



```python
%run scripts/show_solutions.py week09_ex7
```


    Button(description='Reveal solution', style=ButtonStyle())



    Output(layout=Layout(border='1px solid green'))


---
🚩 ***Exercise 8***: Repeat Exercise 6 with $\alpha = 0.8$. Plot the error magnitude on the same graph as that from Exercises 6 and 7. What do you observe?


```python
G=lambda x, a=0.8: (1+a)*x-a*x**2*np.sin(x)
_, xks = iterate_conv(G, 1, e=10**(-5))
xbs = bisect(F,0.5, 1.5)
plt.plot(list(range(len(xks))), np.log(np.abs(xks-r)), label="$\log(|x_k-x*|)$")
plt.plot(list(range(len(xbs))), np.log(np.abs(xbs-r)), label="$\log(|x_b-x*|)$")
plt.legend()
```

    1.0
    1.25
    1.125
    1.0625
    1.09375
    1.109375
    1.1171875
    1.11328125
    1.115234375
    1.1142578125
    1.11376953125
    1.114013671875
    1.1141357421875
    1.11419677734375
    1.114166259765625
    1.1141510009765625





    <matplotlib.legend.Legend at 0x7f521eb9e970>




    
![png](week09_files/week09_24_2.png)
    



```python
%run scripts/show_solutions.py week09_ex8
```

---
## 2. Newton's method

Newton's method proceeds by repeatedly finding the root $x_{k+1}$ of a linear approximation to the function $F$ around the current guess $x_k$. The algorithm proceeds with the following steps until convergence:
1. Start from a guess $x_k$.
2. Draw the tangent to $F(x)$ at $x = x_k$.
3. Find the point of intersection of this tangent with the x-axis. This is the next guess $x_{k+1}$.
4. Advance to the next iteration, i.e. set $x_k = x_{k+1}$ and go back to step 1.

| Initial guess ($x_0 = 0.5$) | Drawing the tangent |
|:-:|:-:|
| ![Initial guess](graphics/newton_example_2.png) | ![Drawing the tangent](graphics/newton_example_3.png) | 
| **New guess $x_1$ - first iteration complete** | **Second iteration: draw the tangent at $x = x_1$ to find $x_2$** |
| ![First iteration](graphics/newton_iteration_1.png) | ![Second iteration](graphics/newton_iteration_2.png) |

### 2.1. Derivation

Consider the Taylor expansion of $F(x)$ around $x_k$:

$$
F(x) = F(x_k) + (x - x_k) F'(x_k) + \dots
$$

The goal is to find a root of $F$ at a future iteration. Ignoring the higher order terms in the Taylor expansion lets us approximate $F$ with a linear function -- the tangent of $F$ at $x = x_k$:

$$
F(x) \approx F(x_k) + (x - x_k) F'(x_k).
$$

Newton's method then proceeds by finding the root of this linear approximation, i.e. the point of intersection between the tangent at $x = x_k$ and the x-axis, by setting

$$
F(x) \approx F(x_k) + (x - x_k) F'(x_k) = 0.
$$

Solving the above for $x$ gives us the next guess $x_{k+1}$:

$$
x_{k+1} = x_k - \frac{F(x_k)}{F'(x_k)}.
$$

---

Note that **Newton's method** is a particular type of **fixed-point iteration**. Starting from an initial guess $x_0$, we have

$$
x_{k+1} = G(x_k) \quad \textrm{ for } k = 0, 1, 2, \ldots
$$

where the iteration function $G(x)$ takes the form

$$
G(x) = x - \frac{F(x)}{F'(x)}.
$$

---
🚩 ***Exercise 9***:
Consider the function

$$
  F \left( x \right) = e^{0.1 x} \sin \left( 4 \pi x \right) + x^2 + 0.5.
$$

Define a function `F` to represent $F \left( x \right)$. Use this function `F` to plot $F(x)$ in the interval $x \in \left[ 0, 1 \right]$, and identify the approximate values of the two roots in this interval.


```python
F = np.vectorize(lambda x: np.e**(0.1*x)*np.sin(4*np.pi*x)+x**2 + 0.5)
xs = np.linspace(0,1, 100)
plt.plot(xs, F(xs))
plt.hlines(0, 0, 1)
```




    <matplotlib.collections.LineCollection at 0x7f5242689f10>




    
![png](week09_files/week09_27_1.png)
    



```python
%run scripts/show_solutions.py week09_ex9
```

---
🚩 ***Exercise 10***: Define a second function `Fp` which can be used to evaluate the derivative $F' \left( x \right)$.

Then, define a function `G` which can be used to evaluate the iteration function associated with using Newton's method to find a root of $F \left( x \right)$.

Plot $G \left( x \right) - x$. By visual comparison with the plot from Exercise 9, verify graphically that, for $x \in \left[ 0, 1 \right]$, $G \left( x \right) = x$ at the same values of $x$ at which $F \left( x \right) = 0$ (i.e. that the fixed points of $G(x)$ are the same as the roots of $F(x)$).


```python
Fp = np.vectorize(lambda x: 2*x+np.e**(0.1*x)*(0.1*np.sin(np.pi*4*x)+4*np.pi*np.cos(4*np.pi*x)))
G = np.vectorize(lambda x: x - F(x)/Fp(x))
xs = np.linspace(0,1, 100)
plt.plot(xs, G(xs)-xs)
plt.plot(xs, F(xs))
plt.hlines(0, 0, 1)
```




    <matplotlib.collections.LineCollection at 0x7f521ea37c10>




    
![png](week09_files/week09_30_1.png)
    



```python
%run scripts/show_solutions.py week09_ex10
```

---
🚩 ***Exercise 11***: For each of the two roots, choose an appropriate initial guess, and use Newton's method to compute them.


```python
print(iterate_conv(G, 0.3, e=10**(-10)))
print(iterate_conv(G, 0.5, e=10**(-10)))
```

    (array(0.29843309), array([0.3       , 0.29842139, 0.29843309, 0.29843309]))
    (array(0.44207026), array([0.5       , 0.44722273, 0.44219896, 0.44207035, 0.44207026]))



```python
%run scripts/show_solutions.py week09_ex11
```

---
### 2.2. Convergence of Newton's method

Newton's method is a particular type of fixed point iteration. As we've seen in Section 1.2 earlier, the absolute error at iterations $k$ and $k+1$ are related by

$$
|e_{k+1}| = |G'(\eta)| |e_k|, \qquad \text{for some} \: \eta \in (\min(x_k, x_\ast), \max(x_k, x_\ast)).
$$

Consider the case where $F$ is twice differentiable (at least in a neighbourhood of $x_\ast$). What is the value of $G'(x)$ at the root $x = x_\ast$?

$$
G'(x_\ast) = 1 - \frac{F'(x_\ast)}{F'(x_\ast)} + \frac{F(x_\ast) F''(x_\ast)}{\left(F'(x_\ast)\right)^2}
= 0, \qquad \text{since by definition,} \: F(x_\ast) = 0.
$$

Therefore, if $G(x)$ is *continuously differentiable* in some neighbourhood of $x_\ast$, we have

$$
\lim_{x \to x_\ast} G'(x) = 0.
$$

This means that for $\eta$ in this neighbourhood, $|G'(\eta)|$ decreases more and more as we approach the root, and convergence **accelerates**!

---
#### Conditions for convergence of Newton's method

To guarantee convergence for fixed-point iteration, we saw earlier that it is sufficient to choose an initial guess $x_0$ in a neighbourhood of $x_\ast$ where $G(x)$ is differentiable and $|G'(x)|<1$.

For Newton's method, we have $G'(x_\ast) = 0$. Therefore, if $G'(x)$ is continuously differentiable in some neighbourhood of $x_\ast$, then by the Intermediate Value Theorem applied to $G'(x)$, there must be a neighbourhood (possibly smaller) near the root for which $|G'(x)| < 1$.

Therefore, for Newton's method to converge, it is sufficient that
- $G(x)$ is continuously differentiable,
- and either
    - $x_0$ is chosen to be sufficiently close to $x_\ast$, or
    - some later iteration value $x_k$ is sufficiently close to $x_\ast$.

This ensures that all subsequent iterations after $x_0$ (or after $x_k$) will reduce the absolute error.

---
#### Order of convergence of Newton's method

In Week 8 we saw that the order of convergence $p$ of a root-finding method verified

$$
\lim_{k\to \infty} \frac{|e_{k+1}|}{|e_k|^p} = \alpha,
$$

where $\alpha$ is a constant.

---
🚩 ***Exercise 12***: Consider a function

$$
  F \left( x \right) = e^x - a,
$$

where $a$ is a given positive real number. The root of this equation is $x_\ast = \log a$.

Implement Newton's method to compute $\log 5$ as the root of $F(x)$ for $a = 5$, starting from an initial guess of $x_0 = 1$. How many iterations does Newton's method take until $\left| x_{k + 1} - x_k \right| < 10^{-14}$?


```python
F=np.vectorize(lambda x: np.e**x - 5)
Fp=np.vectorize(lambda x: np.e**x)
G = np.vectorize(lambda x: x - F(x)/Fp(x))
print(iterate_conv(G, 1, e=10**(-14)))
```

    (array(1.60943791), array([1.        , 1.83939721, 1.63396315, 1.60973621, 1.60943796,
           1.60943791]))



```python
%run scripts/show_solutions.py week09_ex12
```

---
🚩 ***Exercise 13***: Modify your code from Exercise 12 to store the successive values of the absolute error $|e_k| = |x_k - x_\ast|$. Use these values to determine numerically the order of convergence of Newton's method.


```python
F=np.vectorize(lambda x: np.e**x - 5)
Fp=np.vectorize(lambda x: np.e**x)
G = np.vectorize(lambda x: x - F(x)/Fp(x))
_, xks = iterate_conv(G, 1, e=10**(-26))
eks = np.abs(xks-np.log(5))
for p in [0.5,1,1.5,0.75,2]:
    ls = [e1/e2**p for e1, e2 in zip(eks[1:], eks)]
    plt.plot(ls, label=f"$p={p}$")
    plt.ylim(0,0.00000001)
plt.legend()
```




    <matplotlib.legend.Legend at 0x7f521c9b4e20>




    
![png](week09_files/week09_39_1.png)
    



```python
%run scripts/show_solutions.py week09_ex13
```


    Button(description='Reveal solution', style=ButtonStyle())



    Output(layout=Layout(border='1px solid green'))


---
🚩 ***Exercise 14***: Compute and display the successive values of the absolute error using different values of the initial guess $x_0$. How many iterations are needed to converge to the root for different $x_0$? What happens when $x_0$ is set further away from $x_\ast$?


```python

```


```python
%run scripts/show_solutions.py week09_ex14
```
