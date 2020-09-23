#include "expmod.h"

static size_t expmod_internal(size_t a, size_t n, size_t m) {
  if (n == 1)  // At the top because it occurs more often.
    return a;
  size_t d = expmod_internal(a, n / 2, m);
  if (d == 0) {
    return 0;
  }
  size_t r = d * d;
  if (n & 1) {
    r *= a;
  }
  return r % m;
}

size_t expmod(size_t a, size_t n, size_t m) {
  if (n == 0)
    return 1;
  if (m == 1)
    return 0;
  return expmod_internal(a, n, m);
}
