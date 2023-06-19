# Exercise 1


def the_list():
    return [6 + 2 * n for n in range((134 - 5) // 2)]


# Exercise 2


def sub_initial(s):
    return [s[:i] for i in range(len(s) + 1)]


def sub_final(s):
    return [s[i:] for i in range(len(s) + 1)]


def sub_full(s):
    return [s[j:i] for i in range(len(s) + 1) for j in range(i)]


# Exercise 3


def trips():
    return [(i, j, 10 - i - j) for i in range(11) for j in range(11 - i)]


if __name__ == "__main__":
    print(the_list())
    print(sub_initial("python"))
    print(sub_final("python"))
    print(sub_full("python"))
    print(trips())