#include<stdio.h>

int main (int c, char** v) {
    int x = 5;
    printf("starting value; x = %d\n", x);

    # pragma omp parallel firstprivate(x)
    {
        printf("test1; x = %d\n", x);
        x = 1;
        printf("test2; x = %d\n", x);
    }

    printf("ending value; x= %d\n", x);
    return 0;
}