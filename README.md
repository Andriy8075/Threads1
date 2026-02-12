# ЛР1. Потоки в Java (ПЗВПКС)

Паралельна програма з трьома потоками T1, T2, T3 для обчислення функцій F1 (1.10), F2 (2.3), F3 (3.5).

## Збірка

```bash
javac -encoding UTF-8 src/Lab1.java -d out
```

## Запуск

- За замовчуванням (N=4):  
  `java -cp out Lab1`

- Задати N та режим введення для великого N:  
  `java -cp out Lab1 3`  
  `java -cp out Lab1 1000`  
  `java -cp out Lab1 1000 RANDOM`  
  `java -cp out Lab1 1000 FILE`

Режими для N>4: `CONSTANT` (за замовчуванням), `RANDOM`, `FILE`.

## Структура

- **Data** — ресурси: N, операції з векторами/матрицями, введення (для N≤4 — заповнення 1/2/3; для великого N — константа, random або файл).
- **T1** — F1: A = B*(MA*MD)*d
- **T2** — F2: MF = MF*MG*k
- **T3** — F3: O = (SORT(MP*MR))*S
- **Lab1** — main: створення потоків, start(), join().
