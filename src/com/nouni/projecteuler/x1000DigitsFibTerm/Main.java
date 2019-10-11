package com.nouni.projecteuler.x1000DigitsFibTerm;

import java.math.BigInteger;

//java 11
/*
Index : 4782
Value : 
107006626638275893676498058445739688508368389663215166501323520337531452060469404062
188914758248979265780469488817759195748433646667256995951299603046126274809248218614
406943305123477444275027378175308757939166619214925918675955396642283714894311307469
950343954700198543260972306729019287052644724372611771582182554849112052501320147861
296593138179223555965745203950613755146783754322911960212993404826070617539770684706
820289548690266618543512452190036948064135744747091170761976694569107009802439343961
747410373691250323136553216477369702316775505159517351846057995491941096777837322966
579658164651390348815425631018422419025984608800011018625555024549393711365165703944
762958471454852342595042858242530608354443542821261100899286379504800689433030977321
783486454311320576565986845628861680871869383529735064398629764066000072356291790520
705116407761481249188583094594056668833910935094445657635766615161931775379289166158
1327159616877487983821820492520348473874384736771934512787029218636250627816

Time : 1.178 sec
*/
public class Main {
    public static void main(String... args) {
        int size = 1000;
        Fib fb = new FibImpl();
        fb.init(BigInteger.ONE, BigInteger.ONE);
        
        while(fb.size() < size) {
            fb.next();
        }

        System.out.println("Index : " + fb.index());
        System.out.println("Size : " + fb.size());
        System.out.println("Term : " + fb.current());
    }


    static interface Fib {
        void init(BigInteger x, BigInteger y);
        Fib next();
        BigInteger current();
        int index();
        int size();
    }

    static class FibImpl implements Fib {
        protected BigInteger a;
        protected BigInteger b;
        protected int index;

        public void init(BigInteger x, BigInteger y) {
            this.a = x;
            this.b = y;
            this.index = 2;
        }

        public Fib next() {
            BigInteger tmp = b.add(a);
            this.a = b;
            this.b = tmp;
            this.index++;

            return this;
        }

        public BigInteger current() {
            return this.b;
        }

        public int index() {return this.index;}

        public int size() {
            return this.b.toString().length();
        }
    }
}