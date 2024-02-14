#include<stdio.h>

int main (int c, char** v) {
    int x = 5;
    printf("starting value; x = %d\n", x);

    # pragma omp parallel for firstprivate(x) lastprivate(x)
    for (int i = 0; i < 10; i++) {
        printf("x = %d\n", x);
        if (i == 9) {
            x = -123;
        }
    }

    printf("ending value; x= %d\n", x);
    return 0;
}