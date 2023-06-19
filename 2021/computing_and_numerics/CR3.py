import numpy as np
import matplotlib.pyplot as plt


def oscillator(w0, u0, v0, nmax, dt):
    STABILITY_LIMIT = 2  # Naming the constant for readability
    U = np.zeros(nmax)  # The final output
    # Warning messages
    if w0 * dt == STABILITY_LIMIT:
        print(
            f"WARNING: The chosen dt ({w0*dt:.2f}) is at the stability limit.")
    elif w0 * dt > STABILITY_LIMIT:
        print(
            f"WARNING: The chosen dt ({w0*dt:.2f}) exceeds the stability limit ({STABILITY_LIMIT:.2f})."
        )
    # Initial conditions
    U[0] = u0  # iff U_0 = u0
    U[1] = u0 + v0 * dt  # iff (U_1-U_0)/dt = v0
    # Main calculations
    for i in range(2, nmax):
        # We have: U[i] = U_n+1, U[i-1] = U_n and U[i-2] = U_n-1
        U[i] = (2 - w0 * w0 * dt * dt) * U[i - 1] - U[i - 2]
    return U


def test_with_params(w0, u0, v0, nmax, dt):
    # nmax points from 0 with distance dt
    xs = np.linspace(0, nmax * dt, nmax)

    # exact solution u(t)
    u = lambda t: u0 * np.cos(w0 * t) + (v0 / w0) * np.sin(w0 * t)

    # applying exact to the xs
    exact = np.vectorize(u)(xs)

    # computing estimate
    approx = oscillator(w0, u0, v0, nmax, dt)

    fig, ax = plt.subplots()

    # making it look nice on big screens
    fig.set_dpi(200)

    # Proper labelling
    ax.scatter(xs, exact, label="$y=u(t)$", s=1)
    ax.scatter(xs, approx, label=f"$y=U_n$ for each $t=n\Delta t$", s=1)
    ax.set_title(
        f"$\omega_0={w0}$, $u_0={u0}$, $v_0={v0}$, $n_{{max}}={nmax}$ and $\Delta t={dt}$"
    )
    ax.set_xlabel(f"$t$")
    ax.set_ylabel(f"$y$")

    # max/min values to set axis limits properly
    ymax = max(np.max(exact), np.max(approx))
    ymin = min(np.min(exact), np.min(approx))

    # fitting the legend on the plot
    ax.set_ylim(1.1 * ymin, 1.2 * ymax)

    ax.legend()
    plt.show()


# As required by spec
def test1():
    test_with_params(w0=5, u0=0.2, v0=5, nmax=500, dt=0.03)


# Testing with w0*dt = 2
def test2():
    xmax = 2 * np.pi  # keeping x scale consistent
    dt = 0.4
    test_with_params(w0=5, u0=0.2, v0=5, nmax=round(xmax / dt), dt=dt)


# Testing with w0*dt > 2
def test3():
    xmax = 2 * np.pi  # keeping x scale consistent
    dt = 0.6
    test_with_params(w0=5, u0=0.2, v0=5, nmax=round(xmax / dt), dt=dt)


# Testing with low dt
def test4():
    xmax = 2 * np.pi  # keeping x scale consistent
    dt = 0.001
    test_with_params(w0=5, u0=0.2, v0=5, nmax=round(xmax / dt), dt=dt)


if __name__ == "__main__":
    # Feel free to comment/uncomment tests; don't want to spam you
    test1()
    #test2()
    #test3()
    #test4()