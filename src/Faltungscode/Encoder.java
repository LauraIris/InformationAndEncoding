package Faltungscode;

public class Encoder {
    String K; //u_k
    String C;
    
    public Encoder(String K) {
        this.K = K;
        C = "";
    }
    
    public static void main(String[] args) {
        String K = "1011";
        Encoder encoder = new Encoder(K);
        encoder.takt(2);
        
    }
    
    public void takt(int m) {
        int N = K.length() + m;
        String eingang_u_k = K + "0".repeat(m) + "?";
        int k = N - 1;
        String[] zustand = getZustand(eingang_u_k, N, k);
        String[] ausgang = getAusgang(eingang_u_k, zustand,N, k);
        
        String C = getCodewort(ausgang,N);
        
        printEncoding(K,m,eingang_u_k,zustand,ausgang,C,N);
        
    }
    
    public String[] getZustand(String eingang_u_k, int N, int k) {
        String zustand_u_kmin1 = "";
        String zustand_u_kmin2 = "";
        for (int takt_k = 0; takt_k < N; takt_k++) {
            int u_k = takt_k - 1;
            if (u_k == -1) {
                u_k = k;
                zustand_u_kmin1 += eingang_u_k.charAt(u_k);
            } else {
                zustand_u_kmin1 += eingang_u_k.charAt(u_k);
            }
        }
    
        for (int takt_k = 0; takt_k < N; takt_k++) {
            int u_k = takt_k - 2;
            if (u_k == -2) {
                u_k = k - 1;
                zustand_u_kmin2 += eingang_u_k.charAt(u_k);
            } else if (u_k == -1) {
                u_k = k;
                zustand_u_kmin2 += eingang_u_k.charAt(u_k);
            } else {
                zustand_u_kmin2 += eingang_u_k.charAt(u_k);
            }
        }
        
        String[] zustand = new String[2];
        zustand[0] = zustand_u_kmin1;
        zustand[1] = zustand_u_kmin2;
        return zustand;
    }
    
    public String[] getAusgang(String eingang_u_k, String[] zustand, int N, int k) {
        String ausgang_c_2k = "";
        String ausgang_c_2kplus1 = "";
        for (int takt_k = 0; takt_k < N; takt_k++) {
            int u_k = (int) eingang_u_k.charAt(takt_k);
            int u_kmin1 = (int) zustand[0].charAt(takt_k);
            int u_kmin2 = (int) zustand[1].charAt(takt_k);
            int c_2k = (u_k + u_kmin1 + u_kmin2) % 2;
            int c_2kplus1 = (u_k + u_kmin2) % 2;
            ausgang_c_2k += Integer.toString(c_2k);
            ausgang_c_2kplus1 += Integer.toString(c_2kplus1);
        }
        
        String[] ausgang = new String[2];
        ausgang[0] = ausgang_c_2k;
        ausgang[1] = ausgang_c_2kplus1;
        
        return ausgang;
    }
    
    public String getCodewort(String [] ausgang, int N) {
        String codwort="";
        for (int takt_k = 0; takt_k < N; takt_k++) {
            codwort += ausgang[0].charAt(takt_k) + "" + ausgang[1].charAt(takt_k) + " ";
        }
        
        return codwort;
    }
    
    public void printEncoding(String K, int m,String eingang_u_k, String zustand[], String ausgang[], String C,int N){
        System.out.println("Codewort K = " + K);
        System.out.println("m =" + m);
        System.out.println("u = " + eingang_u_k);
        System.out.printf("%4s %7s %14s %14s %n", "Takt", "Eingang", "getZustand", "getAusgang");
        System.out.printf("%4s%7s%14s%14s%n", "|____", "|_______", "|_______________", "|______________|");
        System.out.printf("%4s %7s %7s %7s %7s %7s %n", "k", "u_k", "u_k-1", "u_k-2", "c_2k", "c_2k+1");
        System.out.printf("%4s%7s%7s%7s%7s%7s%n", "|____", "|_______", "|_______", "|_______", "|_______", "|______|");
        for (int takt_k = 0; takt_k < N; takt_k++) {
            System.out.printf("%4s %7s %7s %7s %7s %7s %n",
                    Integer.toString(takt_k),
                    eingang_u_k.charAt(takt_k),
                    zustand[0].charAt(takt_k),
                    zustand[1].charAt(takt_k),
                    ausgang[0].charAt(takt_k),
                    ausgang[1].charAt(takt_k));
            System.out.printf("%4s%7s%7s%7s%7s%7s%n", "|____", "|_______", "|_______", "|_______", "|_______", "|______|");
        }
        System.out.println("Codewort C = " + C);
    }
    

}
