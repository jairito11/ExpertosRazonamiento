/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package razonamiento;

import java.io.PrintStream;
import java.util.ArrayList;
import razonamiento.CustomOutputStream1;
import javax.swing.JOptionPane;

/**
 *
 * @author iron
 */
public class B11 extends javax.swing.JFrame {

    /**
     * Creates new form B1
     */
    String mNombre[][]; //matriz que contiene a todas las h
    String vNombre[]; //vector que contiene los precedencias de cada regla h 
    String BaseCon[]; //vector base de conociiento
    static String objetivo; //objetivo 
    static String sobjetivo; //objetivo 

    //Matrices con alores numericos 
    int mValores[][]; //matriz con valores numericos correspondientes a la de nombre 
    int vValores[]; //vector con valores numéricos correspondiente al vector nombre 
    int vIndices[]; //vector de indices, auxiliar para guardar la Regla y el resultado de condicion y condicion conocida 
    int auxiliar[];
    int reglas[];
    //String elementos[];
    static int guardaPos;
    static int banderin = 0;
    static int band2;
    static int capt;
    static int reglaUsada;
    static int i;
    static int valors;
    static boolean esta;
    public String total;
    private PrintStream standardOut;
    Thread t1, t2;
    public String obj;
    public String base;
    public int num;
    String r0[] = {"h8", "h6", "h5", "h4"};
    String r1[] = {"h6", "h3", "h9"};
    String r2[] = {"h7", "h4", "h9"};
    String r3[] = {"h8", "h1"};
    String r4[] = {"h6", "h5"};
    String r5[] = {"h9", "h1", "h2"};
    String r6[] = {"h7", "h6"};
    String r7[] = {"h1", "h7", "h9"};
    String r8[] = {"h1", "h8", "h6"};
    ArrayList<String> l;
    boolean bandera1 = false;
    boolean bandera2 = false;
    boolean bandera3 = false;
    boolean bandera4 = false;
    boolean bandera5 = false;
    boolean bandera6 = false;
    boolean bandera7 = false;
    boolean bandera8 = false;
    boolean bandera9 = false;
    boolean verdadero = false;                                              //inicializa la variable verdadero en false

    public B11() {
        initComponents();
        this.setLocationRelativeTo(null);
        limpiar();
        PrintStream printStream = null;
        System.setOut(printStream);
        System.setErr(printStream);
        standardOut = System.out;
    }

    public void limpiar() {
        txaObjetivo.setText("");
        txaElementos.setText("");
        txaBC.setText("");
        txtReglas.setText("Condiciones:\n"
                + "R0. |h8|h6|h5| ==> h4\n"
                + "R1. |h6|h3|    | ==> h9\n"
                + "R2. |h7|h4|    | ==> h9\n"
                + "R3. |h8|    |    | ==> h1\n"
                + "R4. |h6|    |    | ==> h5\n"
                + "R5. |h9|h1|    | ==> h2\n"
                + "R6. |h7|    |    | ==> h6\n"
                + "R7. |h1|h7|    | ==> h9\n"
                + "R8. |h1|h8|    | ==> h6");
        txtReglas.setEditable(false);
        txtAdelante.setText("");
        txtAtras.setText("");
        txtAdelante.setEditable(false);
        txtAtras.setEditable(false);        
    }

    public void Radelante() {
        //Algoritmo de razonamiento hacia adelante 
        //Scanner entrada = new Scanner (System.in);

        int pos, //apuntdor de vIndices 
                pos1, //apuntador de vIndices 
                m = 0, //contador 
                n, //contador 
                c = 0, //condicion 
                cc = 0, //condicion conocida 
                numDatos,//numero de datos conocidos 
                finales = 0,//si encuentra el objetivo 
                k,//indice de condicion while 
                apuntador,
                band,//banderas de estado 
                band1,
                j,
                valor,
                valMin,
                h,
                l,
                aux,
                a = 0,
                captar,
                regla;

        boolean empate = false,
                esMenor = false;

        //iniciar los vectores y las matrices 
        String mNombre[][] = new String[9][3];
        String vNombre[] = new String[9];
        String Basecon[] = new String[15];
        /**
         * *
         */
        int mValores[][] = new int[9][3];
        int vValores[] = new int[9];
        int vIndices[] = new int[9];
        int auxiliar[] = new int[9];
        int reglas[] = new int[9];

        //llenar la matriz de nombre y el vector de nombre 
        //mNombre;                                                          //vNombre
        mNombre[0][0] = "h8";
        mNombre[0][1] = "h6";
        mNombre[0][2] = "h5";
        vNombre[0] = "h4";
        mNombre[1][0] = "h6";
        mNombre[1][1] = "h3";
        mNombre[1][2] = "  ";
        vNombre[1] = "h9";
        mNombre[2][0] = "h7";
        mNombre[2][1] = "h4";
        mNombre[2][2] = "  ";
        vNombre[2] = "h9";
        mNombre[3][0] = "h8";
        mNombre[3][1] = "  ";
        mNombre[3][2] = "  ";
        vNombre[3] = "h1";
        mNombre[4][0] = "h6";
        mNombre[4][1] = "  ";
        mNombre[4][2] = "  ";
        vNombre[4] = "h5";
        mNombre[5][0] = "h9";
        mNombre[5][1] = "h1";
        mNombre[5][2] = "  ";
        vNombre[5] = "h2";
        mNombre[6][0] = "h7";
        mNombre[6][1] = "  ";
        mNombre[6][2] = "  ";
        vNombre[6] = "h6";
        mNombre[7][0] = "h1";
        mNombre[7][1] = "h7";
        mNombre[7][2] = "  ";
        vNombre[7] = "h9";
        mNombre[8][0] = "h1";
        mNombre[8][1] = "h8";
        mNombre[8][2] = "  ";
        vNombre[8] = "h6";

        //iniciar el vector de base de conocimiento 
        Basecon[0] = " ";
        Basecon[1] = " ";
        Basecon[2] = " ";
        Basecon[3] = " ";
        Basecon[4] = " ";
        Basecon[5] = " ";
        Basecon[6] = " ";
        Basecon[7] = " ";
        Basecon[8] = " ";
        Basecon[9] = " ";
        Basecon[10] = " ";
        Basecon[11] = " ";
        Basecon[12] = " ";
        Basecon[13] = " ";
        Basecon[14] = " ";
        //llenar la matriz de valores y el vector de valores 

        for (i = 0; i < 9; i++) {
            vValores[i] = 0;
            for (j = 0; j < 3; j++) {
                mValores[i][j] = 0;
            }
        }

        for (i = 0; i < 9; i++) {
            auxiliar[i] = 11;
            reglas[i] = 11;
        }

        //inicia el programa de Razonamiento hacia adelante 
        //Pedir datos para la BasedeConocimeinto y el objetivo
        System.out.println("Condiciones");
        for (i = 0; i < 9; i++) {
            System.out.print("R" + i + ". |");
            for (j = 0; j < 3; j++) {
                System.out.print(mNombre[i][j] + "|");
            }
            System.out.print(" ==> " + vNombre[i]);
            System.out.println();
        }

        System.out.println();
        System.out.println("Valores de las condiciones");
        for (i = 0; i < 9; i++) {
            System.out.print("R" + i + ". ");
            for (j = 0; j < 3; j++) {
                System.out.print(mValores[i][j] + " ");
            }
            System.out.print(" ==> " + vValores[i]);
            System.out.println();
        }

        System.out.println();
        //inicia el programa 

////////////////////////////////////////
        numDatos = Integer.parseInt(txaElementos.getText());
////////////////////////////////////////
        base = txaBC.getText();
        String Baseaux[] = base.split(",");
        String b;
        for (int o = 0; o < numDatos; o++) {
            b = Baseaux[o];
            System.out.println("|" + b + "|");
            Basecon[o] = (String) b;
        }
        objetivo = txaObjetivo.getText();

        System.out.println("Valores de la base de conocimiento: ");
        System.out.print("BC = {");
        for (i = 0; i < numDatos; i++) {
            System.out.print(Basecon[i] + ", ");
        }
        System.out.print("}");
        System.out.println();

        //segun la base de conocimiento cambiar 0 por uno en la matriz y el vector de valores 
        for (i = 0; i < 9; i++) {
            for (j = 0; j < 3; j++) {
                for (k = 0; k < numDatos; k++) {
                    if (mNombre[i][j].equals(Basecon[k])) {
                        mValores[i][j] = 1;
                    }
                    if (vNombre[i].equals(Basecon[k])) {
                        vValores[i] = 1;
                    }
                }
            }
        }

        System.out.println("Condiciones            Valores de las condiciones");
        for (i = 0; i < 9; i++) {
            System.out.print("R" + i + ". |");
            for (j = 0; j < 3; j++) {
                System.out.print(mNombre[i][j] + "|");
            }
            System.out.print(" ==> " + vNombre[i] + "       ");
            for (j = 0; j < 3; j++) {
                System.out.print(mValores[i][j] + " ");
            }
            System.out.print(" ==> " + vValores[i]);
            System.out.println();
        }
        System.out.println();
        //iniia la busqueda del objetivo, hasta no llegar al objetivo o si este no se encuentra se finaliza el programa 
        k = 0;
        while ((finales == 0) && (k < 9)) {
            m = 0;
            System.out.println("Dato de la base de conocimiento: " + Basecon[k]);
            empate = false;
            System.out.println("Reglas donde esta " + Basecon[k] + ":");

            //Buscando donde aparece el primer elemento de la base de conocimiento
            //cada que se encuentra se agrega su renglon a el vector de indices
            band2 = 0;
            for (i = 0; i < 9; i++) {
                for (j = 0; j < 3; j++) {
                    if (mNombre[i][j].equals(Basecon[k])) {

                        vIndices[m] = i;
                        System.out.print("R" + i + ". ");
                        for (l = 0; l < 3; l++) {
                            System.out.print(mNombre[i][l] + ", ");
                        }
                        System.out.print(" ==> " + vNombre[i]);
                        System.out.println();
                        m = m + 1;
                    } else {
                        band2 = band2 + 1;
                    }

                }
            }

            //una vez obtenido las R condiciones se restan sus variables con las variables conocidas 
            //aquella que tenga como resultado cero sera la regla a considerar para comparar con el objetivo 
            pos1 = m;
            pos = m;
            System.out.println("m" + m);
            if (band2 < 27) {
                for (i = 0; i < pos1; i++) {
                    for (j = 0; j < pos1; j++) {
                        //contar las variables por regla 
                        apuntador = vIndices[i];
                        if (mNombre[apuntador][j] != "  ") {
                            c = c + 1; //cuenta una condicion
                        }
                        if (mValores[apuntador][j] == 1) {
                            cc = cc + 1; //cuenta una condicion conocida 
                        }
                    }
                    System.out.println();
                    System.out.println("Condiciones en R" + vIndices[i] + ". : -------"
                            + "\n                    |           Condiciones = " + c
                            + "\n                    | Condiciones conocidas = " + cc
                            + "\n                     -------");

                    auxiliar[i] = c - cc;
                    c = 0;
                    cc = 0;//reiniciamos contadores 
                }

                //comparar para decidir cual es el que tiene mas variables conocidas y el indice de la regla
                //Determinar el valor minimo de las condiciones h
                valMin = 3;
                for (i = 0; i < 9; i++) {
                    for (j = i; j < 9; j++) {
                        if (auxiliar[i] < auxiliar[j]) {
                            if (auxiliar[i] < valMin) {
                                valMin = auxiliar[i];
                            }
                        }

                        if (auxiliar[j] < auxiliar[i]) {
                            if (auxiliar[j] < valMin) {
                                valMin = auxiliar[j];
                            }
                        }

                        if (auxiliar[i] == auxiliar[j]) {
                            if (auxiliar[i] < valMin) {
                                valMin = auxiliar[i];
                            }
                        }
                    }
                }

                //determinar la regla a usar para la siguiente inferencia, en caso de qeu la regla se haya usado 
                //se tomara la siguiente bajo los criterios de el indice y condiciones conocidas 
                i = 0;
                j = 0;
                esta = true;
                reglaUsada = 0;
                valors = 11;

                while (esta == true) {
                    if (auxiliar[j] == valMin) {
                        capt = j;
                        System.out.println("Indice " + vIndices[capt] + "\n reglas " + reglas[i]);
                        captar = vIndices[capt];
                        regla = reglas[i];
                        while (captar != reglas[i] && reglas[i] != valors) {
                            i = i + 1;
                            System.out.println("i " + i);
                        }

                        if (reglas[i] == valors) {
                            esta = false;
                        } else {
                            if (vIndices[capt] == reglas[i]) {
                                j = j + 1;
                                i = 0;
                            }
                        }
                    } else {
                        if (auxiliar[j] > valMin) {
                            vIndices[pos] = j;
                            pos = pos + 1;
                            j = j + 1;
                        } else {
                            if (auxiliar[j] == valors) {
                                System.out.println("Todas las reglas ya fueron usadas");
                                reglaUsada = 1;
                                esta = false;
                            }
                        }
                    }
                    System.out.println("boleano " + esta);
                }

                System.out.println("Reglas usadas" + reglaUsada + "\n esta" + esta);

                if ((reglaUsada == 0) && (esta == false)) {

                    i = 0;
                    while (reglas[i] != 11) {
                        i = i + 1;
                    }
                    reglas[i] = vIndices[capt];

                    System.out.println("Regla elegida; R" + vIndices[capt]);
                    band1 = vIndices[capt];
                    a = a + 1;
                    System.out.println("Condición agregada a la BC: " + vNombre[band1]);
                    //buscando el espacio en BC
                    n = 0;
                    while (Basecon[n] != " ") {
                        n = n + 1;
                    }

                    Basecon[n] = vNombre[band1];

                    System.out.print("BC = ");
                    System.out.print("{");
                    for (i = 0; i < (numDatos + k + 1); i++) {
                        System.out.print(Basecon[i] + ", ");
                    }
                    System.out.print("}");
                    System.out.println();
                    System.out.println();

                    //Verificar si el valor encontrado es el objetivo 
                    if (vNombre[band1].equals(objetivo)) {
                        finales = 1;
                        System.out.println("Objetivo encontrado en la regla R" + band1 + ". El objetivo es: " + objetivo);

                    }
                    k = k + 1;
                    //como se considero un nuevo elemento a Basecon, se cambia su valor de cero por valor de uno 

                    for (i = 0; i < 9; i++) {
                        for (j = 0; j < 3; j++) {
                            if (mNombre[i][j].equals(Basecon[n])) {
                                mValores[i][j] = 1;

                            }
                            if (vNombre[i].equals(Basecon[n])) {
                                vValores[i] = 1;
                            }

                        }
                    }
                    System.out.println("Condiciones            Valores de las condiciones");
                    for (i = 0; i < 9; i++) {
                        System.out.print("R" + i + ". |");
                        for (j = 0; j < 3; j++) {
                            System.out.print(mNombre[i][j] + "|");
                        }
                        System.out.print(" ==> " + vNombre[i] + "       ");
                        for (j = 0; j < 3; j++) {
                            System.out.print(mValores[i][j] + " ");
                        }
                        System.out.print(" ==> " + vValores[i]);
                        System.out.println();
                    }
                    System.out.println();

                } else {
                    if (reglaUsada == 1) {
                        band2 = 27;
                    }
                }
            } else {
                finales = 1;
            }
            for (i = 0; i < 9; i++) {
                vIndices[i] = 10;
                auxiliar[i] = 11;
            }

            for (i = 0; i < 9; i++) {
                vIndices[i] = 10;
                auxiliar[i] = 11;
            }
        }

        if (band2 == 27) {
            System.out.println("Objetivo " + objetivo + " no encontrado");
        }
    }
    
    public void banderas(){
        bandera1 = false;
        bandera2 = false;
        bandera3 = false;
        bandera4 = false;
        bandera5 = false;
        bandera6 = false;
        bandera7 = false;
        bandera8 = false;
        bandera9 = false;
    }

    public void Ratras() {

        num = Integer.parseInt(txaElementos.getText());                         //Numero de elementos conocidos
        String[] elementosI = new String[num];                                  //Arreglo de elementos de la base de conocimiento
        String[] elementosF;                                                    //Declaración de arreglo de elementos temporal que contiene los antecedentes del objetivo
        l = new ArrayList<String>();                                            //lista dinamica de elementos encontrados

        base = txaBC.getText();                                                 //Elementos de la base ingresados en la interfaz 
        String Baseaux[] = base.split(",");                                     //Separa los elementos obtenidos y los ingresa en un arreglo
        String b;                                                               //Crea una variable 
        
        for (int o = 0; o < num; o++) {                                         //inicia ciclo desde 0 hasta num
            b = Baseaux[o];                                                     //El elemento separado en la posición o se copia en la variable b
            elementosI[o] = (String) b;                                         //Se copia el elemento b casteado en la posición o del arreglo de elementos de la base de conocimiento
        }
        objetivo = txaObjetivo.getText();                                       //se obtiene el objetivo y se ingresa a la variable objetivo

        for (int j = 0; j < r0.length; j++) {             /*imprime las reglas*/
            System.out.print(r0[j] + " ");
            if (j == r0.length - 2) {
                System.out.print("==>");
            }
        }
        System.out.println();
        for (int j = 0; j < r1.length; j++) {
            System.out.print(r1[j] + " ");
            if (j == r1.length - 2) {
                System.out.print("==>");
            }
        }
        System.out.println();
        for (int j = 0; j < r2.length; j++) {
            System.out.print(r2[j] + " ");
            if (j == r2.length - 2) {
                System.out.print("==>");
            }
        }
        System.out.println();
        for (int j = 0; j < r3.length; j++) {
            System.out.print(r3[j] + " ");
            if (j == r3.length - 2) {
                System.out.print("==>");
            }
        }
        System.out.println();
        for (int j = 0; j < r4.length; j++) {
            System.out.print(r4[j] + " ");
            if (j == r4.length - 2) {
                System.out.print("==>");
            }
        }
        System.out.println();
        for (int j = 0; j < r5.length; j++) {
            System.out.print(r5[j] + " ");
            if (j == r5.length - 2) {
                System.out.print("==>");
            }
        }
        System.out.println();
        for (int j = 0; j < r6.length; j++) {
            System.out.print(r6[j] + " ");
            if (j == r6.length - 2) {
                System.out.print("==>");
            }
        }
        System.out.println();
        for (int j = 0; j < r7.length; j++) {
            System.out.print(r7[j] + " ");
            if (j == r7.length - 2) {
                System.out.print("==>");
            }
        }
        System.out.println();
        for (int j = 0; j < r8.length; j++) {
            System.out.print(r8[j] + " ");
            if (j == r8.length - 2) {
                System.out.print("==>");
            }
        }                                               /*fin impresion reglas*/
        System.out.println();
        System.out.println("\nElementos en la base de conocimiento");           
        for (int j = 0; j < num; j++) {                                         //inicia el ciclo para imprimir los elementos en la base de conocimiento
            System.out.println(" " + elementosI[j]);                            //Pone en pantalla el elemento encontrado en la base
            l.add(elementosI[j]);                                               //Agrega el elemento encontrado a la lista dinamica
        }
        System.out.println();
        System.out.println("Objetivo\n" + objetivo);                            //Se pone en pantalla el objetivo
        /**/
        //OBJETIVO "h4"                                                         //Se inicia con R0
        if (objetivo.equals(r0[3]) && verdadero == false) {                     //Se espera que objetivo sea igual a h4
            elementosF = new String[r0.length - 1];                             //El arreglo temporal se crea de un tamaño R0 - 1
            for (int j = 0; j < l.size(); j++) {                                //Se recorre la lista dinamica
                if (l.get(j).equals("h4")) {                                    //Se verifica si el elemento que buscamos ya se encuentra en la bc
                    verdadero = true;                                           //Si es verdadero la variable "verdadero" cambia a true
                    System.out.println("El objetivo " + objetivo + " es verdadero");//Se imprime que el objetivo es verdadero
                    break;                                                      //Se rompe el ciclo
                }

            }
            //ANTECEDENTES "h4"
            if (verdadero == false) {                                           //Se checa si aún no se encuentra el objetivo 
                for (int j = 0; j < r0.length - 1; j++) {                       //Se crea un ciclo que recorre todos los antecedentes
                    elementosF[j] = r0[j];                                      //copia cada uno de los antecedentes al arreglo temporal
                    System.out.println("Antecedente R0: " + elementosF[j]);     //imprime todos los antecedentes de h4
                }
                for (int j = 0; j < elementosF.length; j++) {                   //Recorre todos los elementos de R0
                    for (int k = 0; k < l.size(); k++) {                        //Recorre todos los elementos conocidos
                        if (l.get(k).equals(elementosF[j]) && elementosF[j].equals("h8") && bandera8 == false) {//Activa las banderas elementosConocidos
                            bandera8 = true;
                            System.out.println("h8 se encuentra en la base de conocimiento");
                        }
                        if (l.get(k).equals(elementosF[j]) && elementosF[j].equals("h6") && bandera6 == false) {
                            bandera6 = true;
                            System.out.println("h6 se encuentra en la base de conocimiento");
                        }
                        if (l.get(k).equals(elementosF[j]) && elementosF[j].equals("h5") && bandera3 == false) {
                            bandera5 = true;
                            System.out.println("h5 se encuentra en la base de conocimiento");
                        }
                    }
                }
                //BANDERAS "h4" 
                //7 1.1.1
                if (bandera8 && bandera6 && bandera5 && verdadero == false) {   //Verifica si el objetivo es verdadero
                    System.out.println("\nElementos en la base de conocimiento");//Imprime los elementos en la bc
                    for (int q = 0; q < l.size(); q++) {                        //Recorre la lista dinamica 
                          System.out.println("Elemento " + l.get(q) + ",");       //Imprime la base de conocimiento 
                    }
                    System.out.println();
                    System.out.println("\nEl objetivo " + objetivo + " es VERDADERO");//Indica que el objetivo es verdadero
                    verdadero = true;
                    banderas();
                }
                //0 0.0.0
                if (verdadero == false && bandera8 == false && bandera6 == false && bandera5 == false) {
                    otro("h8");                                                 //Se agrega h8 a la base de conocimiento
                    otro("h6");                                                 //Se procede a encontrar h6                    
                    otro("h5");                                                 //Se procede a encontrar h5
                    System.out.println();
                    System.out.println("\nElementos en la base de conocimiento");//Imprime los elementos en la bc
                    for (int q = 0; q < l.size(); q++) {                        //Recorre la lista dinamica 
                          System.out.println("Elemento " + l.get(q) + ",");     //Imprime la base de conocimiento 
                    }
                    System.out.println();                    
                    System.out.println("\nEl objetivo " + objetivo + " es VERDADERO");//Indica que el objetivo es verdadero
                    System.out.println("R0. |h8|h6|h5| ==> h4");
                    verdadero = true;
                    banderas();
                }
                //1 0.0.1
                if (verdadero == false && bandera8 == false && bandera6 == false && bandera5 == true) {
                    otro("h8");                                                 //Se procede a encontrar h8
                    otro("h6");                                                 //Se procede a encontrar h6
                    System.out.println("h5 se encuentra en la base de conocimiento");
                    System.out.println();
                    System.out.println("\nElementos en la base de conocimiento");//Imprime los elementos en la bc
                    for (int q = 0; q < l.size(); q++) {                        //Recorre la lista dinamica 
                          System.out.println("Elemento " + l.get(q) + ",");     //Imprime la base de conocimiento 
                    }
                    System.out.println();                    
                    System.out.println("\nEl objetivo " + objetivo + " es VERDADERO");//Indica que el objetivo es verdadero
                    System.out.println("R0. |h8|h6|h5| ==> h4");
                    verdadero = true;
                    banderas();
                }
                //2 0.1.0
                if (verdadero == false && bandera8 == false && bandera6 == true && bandera5 == false) {
                    otro("h8");                                                 //Se procede a encontrar h8                    
                    otro("h5");                                                 //Se procede a encontrar h5
                    System.out.println("h6 se encuentra en la base de conocimiento");
                    System.out.println();  
                    System.out.println("\nElementos en la base de conocimiento");//Imprime los elementos en la bc
                    for (int q = 0; q < l.size(); q++) {                        //Recorre la lista dinamica 
                          System.out.println("Elemento " + l.get(q) + ",");     //Imprime la base de conocimiento 
                    }                    
                    System.out.println();
                    System.out.println("\nEl objetivo " + objetivo + " es VERDADERO");//Indica que el objetivo es verdadero
                    System.out.println("R0. |h8|h6|h5| ==> h4");
                    verdadero = true;
                    banderas();
                }
                //3 0.1.1
                if (verdadero == false && bandera8 == false && bandera6 == true && bandera5 == true) {
                    otro("h8");                                                 //Se procede a encontrar h8 
                    System.out.println("h6 se encuentra en la base de conocimiento");
                    System.out.println();
                    System.out.println("h5 se encuentra en la base de conocimiento");
                    System.out.println();  
                    System.out.println("\nElementos en la base de conocimiento");//Imprime los elementos en la bc
                    for (int q = 0; q < l.size(); q++) {                        //Recorre la lista dinamica 
                          System.out.println("Elemento " + l.get(q) + ",");     //Imprime la base de conocimiento 
                    }                    
                    System.out.println();
                    System.out.println("\nEl objetivo " + objetivo + " es VERDADERO");//Indica que el objetivo es verdadero
                    System.out.println("R0. |h8|h6|h5| ==> h4");
                    verdadero = true;
                    banderas();
                }                
                //4 1.0.0
                if (verdadero == false && bandera8 == true && bandera6 == false && bandera5 == false) {
                    otro("h6");                                                 //Se procede a encontrar h6                    
                    otro("h5");                                                 //Se procede a encontrar h5
                    System.out.println("h8 se encuentra en la base de conocimiento");
                    System.out.println();  
                    System.out.println("\nElementos en la base de conocimiento");//Imprime los elementos en la bc
                    for (int q = 0; q < l.size(); q++) {                        //Recorre la lista dinamica 
                          System.out.println("Elemento " + l.get(q) + ",");     //Imprime la base de conocimiento 
                    }                    
                    System.out.println();
                    System.out.println("\nEl objetivo " + objetivo + " es VERDADERO");//Indica que el objetivo es verdadero
                    System.out.println("R0. |h8|h6|h5| ==> h4");
                    verdadero = true;
                    banderas();
                }
                //5 1.0.1
                if (verdadero == false && bandera8 == true && bandera6 == false && bandera5 == true) {
                    otro("h6");                                                 //Se procede a encontrar h6
                    System.out.println("h8 se encuentra en la base de conocimiento");
                    System.out.println();  
                    System.out.println("h5 se encuentra en la base de conocimiento");
                    System.out.println();  
                    System.out.println("\nElementos en la base de conocimiento");//Imprime los elementos en la bc
                    for (int q = 0; q < l.size(); q++) {                        //Recorre la lista dinamica 
                          System.out.println("Elemento " + l.get(q) + ",");     //Imprime la base de conocimiento 
                    }                    
                    System.out.println();
                    System.out.println("\nEl objetivo " + objetivo + " es VERDADERO");//Indica que el objetivo es verdadero
                    System.out.println("R0. |h8|h6|h5| ==> h4");
                    verdadero = true;
                    banderas();
                }
                //6 1.1.0
                if (verdadero == false && bandera8 == true && bandera6 == true && bandera5 == false) {
                    otro("h5");                                                 //Se procede a encontrar h6
                    System.out.println("h8 se encuentra en la base de conocimiento");
                    System.out.println();  
                    System.out.println("h6 se encuentra en la base de conocimiento");
                    System.out.println();  
                    System.out.println("\nElementos en la base de conocimiento");//Imprime los elementos en la bc
                    for (int q = 0; q < l.size(); q++) {                        //Recorre la lista dinamica 
                          System.out.println("Elemento " + l.get(q) + ",");     //Imprime la base de conocimiento 
                    }                    
                    System.out.println();
                    System.out.println("\nEl objetivo " + objetivo + " es VERDADERO");//Indica que el objetivo es verdadero
                    System.out.println("R0. |h8|h6|h5| ==> h4");
                    verdadero = true;
                    banderas();
                }
            }
        }
        // OBJETIVO "h9"
        if (objetivo.equals(r1[2]) && verdadero == false) {
            elementosF = new String[r1.length - 1];                             //El arreglo temporal se crea de un tamaño r1
            for (int j = 0; j < l.size(); j++) {                                //Se recorre la lista dinamica
                if (l.get(j).equals("h9")) {                                    //Se verifica si el elemento que buscamos ya se encuentra en la bc
                    verdadero = true;                                           //Si es verdadero la variable "verdadero" cambia a true
                    System.out.println("El objetivo " + objetivo + " es verdadero");//Se imprime que el objetivo es verdadero
                    break;                                                      //Se rompe el ciclo
                }

            }
            //ANTECEDENTES "h9"
            if (verdadero == false) {                                           //Se checa si aún no se encuentra el objetivo 
                for (int j = 0; j < r1.length - 1; j++) {                       //Se crea un ciclo que recorre todos los antecedentes
                    elementosF[j] = r1[j];                                      //copia cada uno de los antecedentes al arreglo temporal
                    System.out.println("Antecedente R1: " + elementosF[j]);     //imprime todos los antecedentes de h1
                }
                for (int j = 0; j < elementosF.length; j++) {                   //Recorre todos los elementos de R0
                    for (int k = 0; k < l.size(); k++) {                        //Recorre todos los elementos conocidos
                        if (l.get(k).equals(elementosF[j]) && elementosF[j].equals("h3") && bandera3 == false) {//Activa las banderas elementosConocidos
                            bandera3 = true;
                            System.out.println("h3 se encuentra en la base de conocimiento");
                        }
                        if (l.get(k).equals(elementosF[j]) && elementosF[j].equals("h6") && bandera6 == false) {
                            bandera6 = true;
                            System.out.println("h6 se encuentra en la base de conocimiento");
                        }
                    }
                }
                //BANDERAS "h9" 
                //7 1.1
                if (bandera3 && bandera6 && verdadero == false) {   //Verifica si el objetivo es verdadero
                    System.out.println("\nElementos en la base de conocimiento");//Imprime los elementos en la bc
                    for (int q = 0; q < l.size(); q++) {                        //Recorre la lista dinamica 
                          System.out.println("Elemento " + l.get(q) + ",");       //Imprime la base de conocimiento 
                    }
                    System.out.println();
                    System.out.println("\nEl objetivo " + objetivo + " es VERDADERO");//Indica que el objetivo es verdadero
                    System.out.println("R1. |h6|h3| ==> h9");
                    verdadero = true;
                    banderas();
                }
                //0 0.0
                if (verdadero == false && bandera3 == false && bandera6 == false) {
                    otro("h3");                                                 //Se agrega h3 a la base de conocimiento
                    otro("h6");                                                 //Se procede a encontrar h6
                    System.out.println();
                    System.out.println("\nElementos en la base de conocimiento");//Imprime los elementos en la bc
                    for (int q = 0; q < l.size(); q++) {                        //Recorre la lista dinamica 
                          System.out.println("Elemento " + l.get(q) + ",");     //Imprime la base de conocimiento 
                    }
                    System.out.println();                    
                    System.out.println("\nEl objetivo " + objetivo + " es VERDADERO");//Indica que el objetivo es verdadero
                    System.out.println("R1. |h6|h3| ==> h9");
                    verdadero = true;
                    banderas();
                }
                //1 0.1
                if (verdadero == false && bandera3 == false && bandera6 == true) {
                    otro("h3");                                                 //Se procede a encontrar h3
                    System.out.println("h6 se encuentra en la base de conocimiento");
                    System.out.println();
                    System.out.println("\nElementos en la base de conocimiento");//Imprime los elementos en la bc
                    for (int q = 0; q < l.size(); q++) {                        //Recorre la lista dinamica 
                          System.out.println("Elemento " + l.get(q) + ",");     //Imprime la base de conocimiento 
                    }
                    System.out.println();                    
                    System.out.println("\nEl objetivo " + objetivo + " es VERDADERO");//Indica que el objetivo es verdadero
                    System.out.println("R1. |h6|h3| ==> h9");
                    verdadero = true;
                    banderas();
                }
                //2 1.0
                if (verdadero == false && bandera3 == true && bandera6 == false) {
                    otro("h6");                                                 //Se procede a encontrar h6                    
                    System.out.println("h3 se encuentra en la base de conocimiento");
                    System.out.println();  
                    System.out.println("\nElementos en la base de conocimiento");//Imprime los elementos en la bc
                    for (int q = 0; q < l.size(); q++) {                        //Recorre la lista dinamica 
                          System.out.println("Elemento " + l.get(q) + ",");     //Imprime la base de conocimiento 
                    }                    
                    System.out.println();
                    System.out.println("\nEl objetivo " + objetivo + " es VERDADERO");//Indica que el objetivo es verdadero
                    System.out.println("R0. |h8|h6|h5| ==> h4");
                    verdadero = true;
                    banderas();
                }
            }            
        }
        //OBJETIVO "h1"///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (objetivo.equals(r3[1]) && verdadero == false) {
            elementosF = new String[r3.length - 1];                             //El arreglo temporal se crea de un tamaño r1
            for (int j = 0; j < l.size(); j++) {                                //Se recorre la lista dinamica
                if (l.get(j).equals("h1")) {                                    //Se verifica si el elemento que buscamos ya se encuentra en la bc
                    verdadero = true;                                           //Si es verdadero la variable "verdadero" cambia a true
                    System.out.println("El objetivo " + objetivo + " es verdadero");//Se imprime que el objetivo es verdadero
                    break;                                                      //Se rompe el ciclo
                }

            }
            //ANTECEDENTES "h1"
            if (verdadero == false) {                                           //Se checa si aún no se encuentra el objetivo 
                for (int j = 0; j < r3.length - 1; j++) {                       //Se crea un ciclo que recorre todos los antecedentes
                    elementosF[j] = r3[j];                                      //copia cada uno de los antecedentes al arreglo temporal
                    System.out.println("Antecedente R3: " + elementosF[j]);     //imprime todos los antecedentes de h1
                }
                for (int j = 0; j < elementosF.length; j++) {                   //Recorre todos los elementos de R0
                    for (int k = 0; k < l.size(); k++) {                        //Recorre todos los elementos conocidos
                        if (l.get(k).equals(elementosF[j]) && elementosF[j].equals("h8") && bandera8 == false) {//Activa las banderas elementosConocidos
                            bandera8 = true;
                            System.out.println("h8 se encuentra en la base de conocimiento");
                        }
                    }
                }
                //BANDERAS "h1" 
                if (bandera8 && verdadero == false) {   //Verifica si el objetivo es verdadero
                    System.out.println("\nElementos en la base de conocimiento");//Imprime los elementos en la bc
                    for (int q = 0; q < l.size(); q++) {                        //Recorre la lista dinamica 
                          System.out.println("Elemento " + l.get(q) + ",");       //Imprime la base de conocimiento 
                    }
                    System.out.println();
                    System.out.println("\nEl objetivo " + objetivo + " es VERDADERO");//Indica que el objetivo es verdadero
                    System.out.println("R3. |h8| ==> h1");
                    verdadero = true;
                    banderas();
                }
                //0 
                if (verdadero == false && bandera8 == false) {
                    otro("h8");                                                 //Se agrega h8 a la base de conocimiento
                    System.out.println();
                    System.out.println("\nElementos en la base de conocimiento");//Imprime los elementos en la bc
                    for (int q = 0; q < l.size(); q++) {                        //Recorre la lista dinamica 
                          System.out.println("Elemento " + l.get(q) + ",");     //Imprime la base de conocimiento 
                    }
                    System.out.println();                    
                    System.out.println("\nEl objetivo " + objetivo + " es VERDADERO");//Indica que el objetivo es verdadero
                    System.out.println("R3. |h8| ==> h1");
                    verdadero = true;
                    banderas();
                }
            }
        }
        //Objetivo H5
        if (objetivo.equals(r4[1]) && verdadero == false) {
            elementosF = new String[r4.length - 1];                             //El arreglo temporal se crea de un tamaño r4
            for (int j = 0; j < l.size(); j++) {                                //Se recorre la lista dinamica
                if (l.get(j).equals("h5")) {                                    //Se verifica si el elemento que buscamos ya se encuentra en la bc
                    verdadero = true;                                           //Si es verdadero la variable "verdadero" cambia a true
                    System.out.println("El objetivo " + objetivo + " es verdadero");//Se imprime que el objetivo es verdadero
                    break;                                                      //Se rompe el ciclo
                }

            }
            //ANTECEDENTES "h5"
            if (verdadero == false) {                                           //Se checa si aún no se encuentra el objetivo 
                for (int j = 0; j < r4.length - 1; j++) {                       //Se crea un ciclo que recorre todos los antecedentes
                    elementosF[j] = r4[j];                                      //copia cada uno de los antecedentes al arreglo temporal
                    System.out.println("Antecedente R4: " + elementosF[j]);     //imprime todos los antecedentes de h5
                }
                for (int j = 0; j < elementosF.length; j++) {                   //Recorre todos los elementos de R0
                    for (int k = 0; k < l.size(); k++) {                        //Recorre todos los elementos conocidos
                        if (l.get(k).equals(elementosF[j]) && elementosF[j].equals("h6") && bandera6 == false) {//Activa las banderas elementosConocidos
                            bandera6 = true;
                            System.out.println("h6 se encuentra en la base de conocimiento");
                        }
                    }
                }
                //BANDERAS "h5" 
                if (bandera6 && verdadero == false) {   //Verifica si el objetivo es verdadero
                    System.out.println("\nElementos en la base de conocimiento");//Imprime los elementos en la bc
                    for (int q = 0; q < l.size(); q++) {                        //Recorre la lista dinamica 
                          System.out.println("Elemento " + l.get(q) + ",");       //Imprime la base de conocimiento 
                    }
                    System.out.println();
                    System.out.println("\nEl objetivo " + objetivo + " es VERDADERO");//Indica que el objetivo es verdadero
                    System.out.println("R4. |h6| ==> h5");
                    verdadero = true;
                    banderas();
                }
                //0 
                if (verdadero == false && bandera6 == false) {
                    otro("h6");                                                 //Se agrega h6 a la base de conocimiento
                    System.out.println();
                    System.out.println("\nElementos en la base de conocimiento");//Imprime los elementos en la bc
                    for (int q = 0; q < l.size(); q++) {                        //Recorre la lista dinamica 
                          System.out.println("Elemento " + l.get(q) + ",");     //Imprime la base de conocimiento 
                    }
                    System.out.println();                    
                    System.out.println("\nEl objetivo " + objetivo + " es VERDADERO");//Indica que el objetivo es verdadero
                    System.out.println("R4. |h6| ==> h5");
                    verdadero = true;
                    banderas();
                }
            }
        }
        //Objetivo "H2"
        if (objetivo.equals(r5[2]) && verdadero == false) {
            elementosF = new String[r5.length - 1];                             //El arreglo temporal se crea de un tamaño r5
            for (int j = 0; j < l.size(); j++) {                                //Se recorre la lista dinamica
                if (l.get(j).equals("h2")) {                                    //Se verifica si el elemento que buscamos ya se encuentra en la bc
                    verdadero = true;                                           //Si es verdadero la variable "verdadero" cambia a true
                    System.out.println("El objetivo " + objetivo + " es verdadero");//Se imprime que el objetivo es verdadero
                    break;                                                      //Se rompe el ciclo
                }
            }
            //ANTECEDENTES "h2"
            if (verdadero == false) {                                           //Se checa si aún no se encuentra el objetivo 
                for (int j = 0; j < r5.length - 1; j++) {                       //Se crea un ciclo que recorre todos los antecedentes
                    elementosF[j] = r5[j];                                      //copia cada uno de los antecedentes al arreglo temporal
                    System.out.println("Antecedente R5: " + elementosF[j]);     //imprime todos los antecedentes de h2
                }
                for (int j = 0; j < elementosF.length; j++) {                   //Recorre todos los elementos de R5
                    for (int k = 0; k < l.size(); k++) {                        //Recorre todos los elementos conocidos
                        if (l.get(k).equals(elementosF[j]) && elementosF[j].equals("h1") && bandera1 == false) {//Activa las banderas elementosConocidos
                            bandera1 = true;
                            System.out.println("h1 se encuentra en la base de conocimiento");
                        }
                        if (l.get(k).equals(elementosF[j]) && elementosF[j].equals("h9") && bandera9 == false) {
                            bandera9 = true;
                            System.out.println("h9 se encuentra en la base de conocimiento");
                        }
                    }
                }
                //BANDERAS "h2" 
                //7 1.1
                if (bandera1 && bandera9 && verdadero == false) {   //Verifica si el objetivo es verdadero
                    System.out.println("\nElementos en la base de conocimiento");//Imprime los elementos en la bc
                    for (int q = 0; q < l.size(); q++) {                        //Recorre la lista dinamica 
                          System.out.println("Elemento " + l.get(q) + ",");       //Imprime la base de conocimiento 
                    }
                    System.out.println();
                    System.out.println("\nEl objetivo " + objetivo + " es VERDADERO");//Indica que el objetivo es verdadero
                    System.out.println("R5. |h9|h1| ==> h2");
                    verdadero = true;
                    banderas();
                }
                //0 0.0
                if (verdadero == false && bandera1 == false && bandera9 == false) {
                    otro("h1");                                                 //Se agrega h1 a la base de conocimiento
                    otro("h9");                                                 //Se procede a encontrar h9
                    System.out.println();
                    System.out.println("\nElementos en la base de conocimiento");//Imprime los elementos en la bc
                    for (int q = 0; q < l.size(); q++) {                        //Recorre la lista dinamica 
                          System.out.println("Elemento " + l.get(q) + ",");     //Imprime la base de conocimiento 
                    }
                    System.out.println();                    
                    System.out.println("\nEl objetivo " + objetivo + " es VERDADERO");//Indica que el objetivo es verdadero
                    System.out.println("R5. |h9|h1| ==> h2");
                    verdadero = true;
                    banderas();
                }
                //1 0.1
                if (verdadero == false && bandera1 == false && bandera9 == true) {
                    otro("h1");                                                 //Se procede a encontrar h1
                    System.out.println("h9 se encuentra en la base de conocimiento");
                    System.out.println();
                    System.out.println("\nElementos en la base de conocimiento");//Imprime los elementos en la bc
                    for (int q = 0; q < l.size(); q++) {                        //Recorre la lista dinamica 
                          System.out.println("Elemento " + l.get(q) + ",");     //Imprime la base de conocimiento 
                    }
                    System.out.println();                    
                    System.out.println("\nEl objetivo " + objetivo + " es VERDADERO");//Indica que el objetivo es verdadero
                    System.out.println("R5. |h9|h1| ==> h2");
                    verdadero = true;
                    banderas();
                }
                //2 1.0
                if (verdadero == false && bandera1 == true && bandera9 == false) {
                    otro("h9");                                                 //Se procede a encontrar h9
                    System.out.println("h1 se encuentra en la base de conocimiento");
                    System.out.println();  
                    System.out.println("\nElementos en la base de conocimiento");//Imprime los elementos en la bc
                    for (int q = 0; q < l.size(); q++) {                        //Recorre la lista dinamica 
                          System.out.println("Elemento " + l.get(q) + ",");     //Imprime la base de conocimiento 
                    }                    
                    System.out.println();
                    System.out.println("\nEl objetivo " + objetivo + " es VERDADERO");//Indica que el objetivo es verdadero
                    System.out.println("R5. |h9|h1| ==> h2");
                    verdadero = true;
                    banderas();
                }
            }
        }
        if (objetivo.equals(r6[1]) && verdadero == false) {

        }
        if (objetivo.equals(r7[2]) && verdadero == false) {

        }
        if (objetivo.equals(r8[2]) && verdadero == false) {

        }
    }

    public void otro(String otro) {//recibe un parametro 
        boolean verdadero = false;
        //R0
        if (otro.equals("h4") && verdadero == false) {
            System.out.println("h4 ingresado a la BC");
            l.add("h4");
            bandera4=true;
            verdadero=true;
        }
        //R1,R2,R7
        if (otro.equals("h9") && verdadero == false) {
            System.out.println("h9 ingresado a la BC");
            l.add("h9");
            bandera9=true;
            verdadero=true;
        }
        //R3
        if (otro.equals("h1") && verdadero == false) {
            System.out.println("h1 ingresado a la BC");
            l.add("h1");
            bandera1=true;   
            verdadero=true;
        }
        //r4
        if (otro.equals("h5") && verdadero == false) {
            System.out.println("h5 ingresado a la BC");
            l.add("h5");
            bandera5=true;
            verdadero=true;            
        }
        //h2
        if (otro.equals("h2") && verdadero == false) {
            System.out.println("h2 ingresado a la BC");
            l.add("h2");
            bandera2=true;
            verdadero=true;             
        }
        //R6,R8
        if (otro.equals("h6") && verdadero == false) {
            System.out.println("h6 ingresado a la BC");
            l.add("h6");
            bandera6=true;
            verdadero=true;             
        }
        //SE CONSIDERAN VERDADEROS PARA CONTINUAR
        if (otro.equals("h3") && verdadero == false) {
            System.out.println("h3 SE CONSIDERA VERDADERO PARA CONTINUAR");
            l.add("h3");
            bandera3=true;
        }
        if (otro.equals("h7") && verdadero == false) {
            System.out.println("h7 SE CONSIDERA VERDADERO PARA CONTINUAR");
            l.add("h7");
            bandera7=true;
        }
        if (otro.equals("h8") && verdadero == false) {
            System.out.println("h8 SE CONSIDERA VERDADERO PARA CONTINUAR");
            l.add("h8");
            bandera8=true;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtReglas = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAdelante = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAtras = new javax.swing.JTextArea();
        jAdelante = new javax.swing.JCheckBox();
        jAtras = new javax.swing.JCheckBox();
        jAmbos = new javax.swing.JCheckBox();
        btnLimpiar = new javax.swing.JButton();
        btnIniciar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        txaObjetivo = new javax.swing.JTextField();
        txaBC = new javax.swing.JTextField();
        txaElementos = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("R. Adelante y Atras"));

        jLabel1.setText("Objetivo");

        jLabel2.setText("B. Conocimiento");

        jLabel3.setText("Elementos Conocidos");

        jLabel4.setText("Reglas:");

        jLabel5.setText("R Adelante");

        jLabel6.setText("R Atras");

        txtReglas.setColumns(20);
        txtReglas.setRows(5);
        jScrollPane1.setViewportView(txtReglas);

        txtAdelante.setColumns(20);
        txtAdelante.setRows(5);
        jScrollPane2.setViewportView(txtAdelante);

        txtAtras.setColumns(20);
        txtAtras.setRows(5);
        jScrollPane3.setViewportView(txtAtras);

        jAdelante.setText("Adelante");

        jAtras.setText("Atras");

        jAmbos.setText("Ambos");

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txaBC, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(txaObjetivo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(153, 153, 153)
                                        .addComponent(jLabel6))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txaElementos, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jAdelante)
                            .addComponent(jAtras)
                            .addComponent(jAmbos)
                            .addComponent(btnLimpiar)
                            .addComponent(btnIniciar)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel3)
                        .addComponent(txaElementos, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txaObjetivo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txaBC, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jAdelante)
                        .addGap(18, 18, 18)
                        .addComponent(jAtras)
                        .addGap(18, 18, 18)
                        .addComponent(jAmbos)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiar)
                        .addGap(18, 18, 18)
                        .addComponent(btnIniciar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        // TODO add your handling code here:
        verdadero = false;
        if (jAdelante.isSelected() == true || jAmbos.isSelected() == true) {
            Hadelante();
        }
        if (jAtras.isSelected() == true) {
            Hatras();
        }

    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }//GEN-LAST:event_btnSalirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(B11.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(B11.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(B11.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(B11.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new B11().setVisible(true);
            }
        });
    }

    private void Hadelante() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean continua = true;
                while (continua) {
                    try {
                        PrintStream p = new PrintStream(new CustomOutputStream1(txtAdelante));
                        System.setOut(p);
                        System.setErr(p);
                        Radelante();
                        //System.out.println("El objetivo h2 es verdadero por R6");
                        if (jAmbos.isSelected() == true) {
                            Hatras();
                        }
                        Thread.sleep(3000);
                        continua = false;
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        t1.start();
    }

    private void Hatras() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean continua = true;
                while (continua) {
                    try {
                        PrintStream a = new PrintStream(new CustomOutputStream1(txtAtras));
                        System.setOut(a);
                        System.setErr(a);
                        Ratras();
                        Thread.sleep(3000);
                        continua = false;
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        t2.start();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox jAdelante;
    private javax.swing.JCheckBox jAmbos;
    private javax.swing.JCheckBox jAtras;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField txaBC;
    private javax.swing.JTextField txaElementos;
    private javax.swing.JTextField txaObjetivo;
    private javax.swing.JTextArea txtAdelante;
    private javax.swing.JTextArea txtAtras;
    private javax.swing.JTextArea txtReglas;
    // End of variables declaration//GEN-END:variables
}
