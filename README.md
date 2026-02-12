# Lab 1. Threads in Java (HPC Software)

Parallel program with three threads T1, T2, T3 that compute functions F1 (1.10), F2 (2.3), F3 (3.5).

## Build

```bash
javac -encoding UTF-8 src/Lab1.java -d out
```

## Run

- Default (N=4):  
  `java -cp out Lab1`

- Set N and input mode for large N:  
  `java -cp out Lab1 3`  
  `java -cp out Lab1 1000`  
  `java -cp out Lab1 1000 RANDOM`  
  `java -cp out Lab1 1000 FILE`

Input modes for N>4: `CONSTANT` (default), `RANDOM`, `FILE`.

## Structure

- **Data** — resources: N, vector/matrix operations, input helpers (for N≤4 — filling with 1/2/3; for large N — constant, random or file-based data).
- **T1** — F1: A = B*(MA*MD)*d
- **T2** — F2: MF = MF*MG*k
- **T3** — F3: O = (SORT(MP*MR))*S
- **Lab1** — main: creates threads, calls start() and join().
