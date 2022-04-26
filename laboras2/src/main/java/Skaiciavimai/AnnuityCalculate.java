package Skaiciavimai;

import java.util.ArrayList;

import static java.lang.Math.pow;

public class AnnuityCalculate extends Skaiciavimai{

    private double sum, percent, credit, interest, perMonth, leftovers;
    private int term, postpone;

    public ArrayList<Numbers> numbers = new ArrayList<>(term);

    public AnnuityCalculate(double sum, double percent, int term, int postpone) {
        this.sum = sum;
        this.percent = percent;
        this.term = term;
        this.postpone = postpone;
        calculateMonth();
    }

    public void calculateMonth() {
        int i = 0;

        interest = sum * (percent / 12);

        for(; i < postpone; i++) {
            numbers.add(new Numbers(i + 1, interest, interest, 0, sum));
        }

        perMonth = interest / (1 - (1 / pow(1 + interest / sum, term - postpone)));

        for(; i < term; i++) {
            leftovers = sum;
            interest = sum * (percent / 12);
            sum -= perMonth - interest;
            credit = perMonth - interest;

            numbers.add(new Numbers(i + 1, perMonth, interest, credit, leftovers));
        }
    }

    public Double getPerMonth() { return perMonth; }
}
