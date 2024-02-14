#include<stdio.h>

int main (int c, char** v) {
    int x = 5;
    printf("starting value; x = %d\n", x);

    # pragma omp parallel for lastprivate(x)
    for (int i = 0; i < 10; i++) {
        printf("x = %d\n", x);
        x++;
        x++;
    }

    printf("ending value; x= %d\n", x);
    return 0;
}