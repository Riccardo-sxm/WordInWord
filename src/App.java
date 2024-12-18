/*
passo 1: estrarre una parola in maniera casuale, visualizzarla all'utente e chiedere di inserire la sua risposta. Mostrare se la risposta è esatta o errata (utilizzare la funzione indexOf delle stringhe)

Esempio:

    String parola1="sole";
    String parola2="insolente";
    String parola3="sola";
    //indexOf restistuisce la posizione della parola1 all'interno di parola2, oppure -1 se non è contenuta
    if (parola2.indexOf(parola1))   //in questo caso l'if è vero
        System.out.println("è contenuta");  
    else
        System.out.println("non è contenuta");

    if (parola2.indexOf(parola3))   //in questo caso l'if è false
        System.out.println("è contenuta");  
    else
        System.out.println("non è contenuta");
passo 2: mostrare il turno e il giocatore corrente. Per ogni turno devono giocare entrambi i giocatori. Ripetere per 5 turni

passo 3: memorizzare in due arraylist diverse le risposte corrette dei due giocatori */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        int turno = 5;
        String[] parole = { "sole", "mare", "neve", "pane", "ente", "ore", "ere", "io", "gli", "per" };
        ArrayList<String> rispostaG1 = new ArrayList<>();
        ArrayList<String> rispostaG2 = new ArrayList<>();
        int tempoG1 = 0;
        int tempoG2 = 0;

        ArrayList<String> paroleUtilizzate = new ArrayList<>();

        String nomeFile = "280000_parole_italiane.txt";

        ArrayList<String> paroleAmmesse = new ArrayList<>();

        // controllo parole ammesse 
        try {
            FileReader fileReader = new FileReader(nomeFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String riga;

            while ((riga = bufferedReader.readLine()) != null)
                paroleAmmesse.add(riga.trim());

            bufferedReader.close();

        } catch (IOException e) {
            System.err.println("Errore durante la lettura del file: " + e.getMessage());
        }
         
        //inizio turni di gioco
        for (int i = 1; i <= turno; i++) {
            //turno giocatore 1
            System.out.println("E' il turno " + i + " del giocatore 1");
            int indice_parola_casuale = r.nextInt(parole.length);
            String parolaTrovata = parole[indice_parola_casuale];

            System.out.println("La parola estratta è " + parolaTrovata);
            System.out.println("Inserisci una parola che contiene " + parolaTrovata);
            long inizioTempoMillisecondi = System.currentTimeMillis();
            // Fai qualcosa...
            String risposta = sc.nextLine();

            long fineTempoMillisecondi = System.currentTimeMillis();
            long secondiTrascorsi = (fineTempoMillisecondi - inizioTempoMillisecondi) / 1000;
            System.out.println("Secondi trascorsi: " + secondiTrascorsi);

            //casisitiche risposte giocatore 1
            if (secondiTrascorsi <= 10) {

                if (paroleUtilizzate.contains(risposta)) {
                    System.out.println("La parola è già stata utilizzata. Perdi il turno.");
                } else {
                    if ((risposta.indexOf(parolaTrovata) != -1) && (paroleAmmesse.contains(risposta)) && (!risposta.equalsIgnoreCase(parolaTrovata))) {
                        System.out.println("La parola è contenuta");
                        rispostaG1.add(risposta);
                        paroleUtilizzate.add(risposta);
                    } else {
                        System.out.println("La parola non è valida");

                    }
                }
            } else {
                System.out.println("Ci hai messo troppo tempo. Risultato non valido.");
            }

            tempoG1 += secondiTrascorsi;

            //turno giocatore 2
            System.out.println("E' il turno " + i + " del giocatore 2");

            System.out.println("Inserisci una parola che contiene " + parolaTrovata);
            long inizioTempoMillisecondi2 = System.currentTimeMillis();
            // Fai qualcosa...
            String risposta2 = sc.nextLine();

            long fineTempoMillisecondi2 = System.currentTimeMillis();
            long secondiTrascorsi2 = (fineTempoMillisecondi2 - inizioTempoMillisecondi2) / 1000;
            System.out.println("Secondi trascorsi: " + secondiTrascorsi2);

            //casistiche risposte giocatore 2
            if (secondiTrascorsi2 <= 10) {
                if (paroleUtilizzate.contains(risposta2)) {
                    System.out.println("La parola è già stata utilizzata. Perdi il turno");
                }else{
                    if ((risposta2.indexOf(parolaTrovata) != -1) && (paroleAmmesse.contains(risposta2))&& (!risposta2.equalsIgnoreCase(parolaTrovata))) {
                        System.out.println("La parola è contenuta");
                        rispostaG2.add(risposta2);
                        paroleUtilizzate.add(risposta2);
                    } else {
                        System.out.println("La parola non è valida");
    
                    }
                }
                
            } else {
                System.out.println("Ci hai messo troppo tempo. Risultato non valido.");
            }
            tempoG2 += secondiTrascorsi2;

        }

        //stampa le risposte e il tempo impiegato
        System.out.println("Le parole indovinate dal giocatore 1 sono: " + rispostaG1 + ". Tempo impiegato: " + tempoG1
                + " secondi");
        System.out.println("Le parole indovinate dal giocatore 2 sono: " + rispostaG2 + ". Tempo impiegato: " + tempoG2
                + " secondi");

        int risultatoG1 = rispostaG1.size();
        int risultatoG2 = rispostaG2.size();

        //casistiche di vincita
        if (risultatoG1 > risultatoG2) {
            System.out.println("Ha vinto il giocatore 1");
        } else if (risultatoG1 < risultatoG2) {
            System.out.println("Ha vinto il giocatore 2");
        } else {
            System.out.println("Pareggio ma... ");
            if (tempoG1 < tempoG2) {
                System.out.println("Ha vinto il giocatore 1!");
            } else if (tempoG1 > tempoG2) {
                System.out.println("Ha vinto il giocatore 2!");
            } else
                System.out.println("I due giocatori hanno impiegato lo stesso tempo!");
        }

        sc.close();
    }
}
