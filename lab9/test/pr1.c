#include<stdio.h>

int main (int c, char** v) {
    int x = 5;
    printf("starting value; x = %d\n", x);

    # pragma omp parallel private(x)
    printf("x = %d\n", x);

    printf("ending value; x= %d\n", x);
    return 0;
}