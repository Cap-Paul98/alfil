import java.util.LinkedList;

public class App {
    public static int cant = 0;

    public static void main(String[] args) throws Exception {
        int m[][] = new int[3][3];
        laberintoAlfil(m, 0, 1, 2, 2, 1);
        System.out.println("cantidad de soluciones: " + cant);
    }

    private static void mostrar(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == -1) {
                    System.out.print("X" + " ");
                } else {
                    System.out.print(m[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean posValida(int[][] m, int i, int j) {
        return i >= 0 && i < m.length &&
                j >= 0 && j < m[i].length &&
                m[i][j] == 0;
    }

    public static void laberintoAlfil(int m[][], int i, int j, int i1, int j1, int paso) {
        if (!posValida(m, i, j)) {
            return;
        }

        m[i][j] = paso;

        if (i == i1 && j == j1) {
            mostrar(m);
            cant++;
        }

        LinkedList<Regla> L1 = reglasAlfil(m, i, j);

        while (!L1.isEmpty()) {
            Regla r = L1.removeFirst();
            laberintoAlfil(m, r.fil, r.col, i1, j1, paso + 1);
            m[r.fil][r.col] = 0;
        }

    }

    public static LinkedList<Regla> reglasAlfil(int[][] A, int fila, int columna) {
        LinkedList<Regla> l1 = new LinkedList<>();

        int i, j;
        // 1 izq arriba
        i = fila - 1;
        j = columna - 1;
        while (posValida(A, i, j)) {
            l1.add(new Regla(i, j));
            i--;
            j--;
        }
        // 2 der arriba
        i = fila - 1;
        j = columna + 1;
        while (posValida(A, i, j)) {
            l1.add(new Regla(i, j));
            i--;
            j++;
        }
        // 3 Der abajo
        i = fila + 1;
        j = columna + 1;
        while (posValida(A, i, j)) {
            l1.add(new Regla(i, j));
            i++;
            j++;
        }
        // 4 Izq abajo
        i = fila + 1;
        j = columna - 1;
        while (posValida(A, i, j)) {
            l1.add(new Regla(i, j));
            i++;
            j--;
        }
        return l1;
    }
}
