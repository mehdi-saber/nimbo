#include<stdio.h>
#include<stdlib.h>
#include <time.h>

int *cols;

int *findNewBound(int sIndex, int eIndex, int localMaxIndex, int maxVal) {
    int lStart;
    for (lStart = sIndex; lStart < localMaxIndex - 1 && cols[lStart] == maxVal; lStart++);
    int lEnd;
    for (lEnd = localMaxIndex - 1; lEnd > lStart + 1 && cols[lEnd] == maxVal; lEnd--);

    int rStart;
    for (rStart = localMaxIndex + 1; rStart < eIndex && cols[rStart] == maxVal; rStart++);
    int rEnd;
    for (rEnd = eIndex; rEnd > rStart + 1 && cols[rEnd] == maxVal; rEnd--);

    int *bounds = (int *) malloc(4 * sizeof(int));

    bounds[0] = lStart;
    bounds[1] = lEnd;
    bounds[2] = rStart;
    bounds[3] = rEnd;
    return bounds;
}

long long int getSteps(int sIndex, int eIndex, int maxVal) {
    long long int steps = 0;
    int localMaxIndex = -1, localMax = -1;
    for (int i = sIndex; i <= eIndex; i++)
        if (cols[i] > localMax) {
            localMax = cols[i];
            localMaxIndex = i;
        }
    int delta = maxVal - cols[localMaxIndex];
    steps += delta;
    if (delta > 0 && localMax < maxVal)
        for (int i = sIndex; i <= eIndex; i++)
            cols[i] = cols[i] + delta;

    int *bounds = findNewBound(sIndex, eIndex, localMaxIndex, maxVal);
    int lStart = bounds[0], lEnd = bounds[1], rStart = bounds[2], rEnd = bounds[3];

    if (lStart <= lEnd)
        steps += getSteps(lStart, lEnd, maxVal);
    if (rStart <= rEnd)
        steps += getSteps(rStart, rEnd, maxVal);
    return steps;
}


int main() {
    int n;
    scanf("%d", &n);
    cols = (int *) malloc(n * sizeof(int));
    for (int i = 0; i < n; i++)
        scanf("%d", &cols[i]);
    int maxVal = 0;
    for (int i = 0; i < n; i++)
        if (cols[i] > maxVal)
            maxVal = cols[i];

    clock_t t;
    t = clock();
    printf("%lld", getSteps(0, n - 1, maxVal));
    t = clock() - t;
    double time_taken = ((double)t)/CLOCKS_PER_SEC; // in seconds
    printf("\n%f seconds\n", time_taken);
    return 0;
}