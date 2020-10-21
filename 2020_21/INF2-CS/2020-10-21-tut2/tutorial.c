void question1(int x, int y, int *p, int *q) {
  register int t0, t1, t2;
  t0 = p + x;
  t1 = q + y;
  x  = *((int *)t0);
  t2 = t0 + 4;
  t0 = *((int *)t2);
  t0 += x;
  *((int *)t1) = t0;
}

void memcpy(int *p, int *q, int n) {
  while (n--) {
    *q = *p;
    p++;
    q++;
  }
}

int main(void) {
}
