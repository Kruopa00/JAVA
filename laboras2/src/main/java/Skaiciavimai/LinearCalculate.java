package Skaiciavimai;

import java.util.ArrayList;

public class LinearCalculate extends Skaiciavimai{

    private double sum, percent, credit, interest, perMonth, leftovers;
    private int term, postpone;
    public ArrayList<Numbers> numbers = new ArrayList<>(term);

    public LinearCalculate(double sum, double percent, int term, int postpone) {
        this.sum = sum;
        this.percent = percent;
        this.term = term;
        this.postpone = postpone;
        calculateMonth();
    }

    @Override
    public void calculateMonth() {
        int i = 0;

        interest = sum * (percent / 12);

        for(; i < postpone; i++) {
            numbers.add(new Numbers(i + 1, interest, interest, 0, sum));
        }

        credit = sum / (term - postpone);

        for(; i < term; i++) {
            interest = sum * (percent / 12);
            perMonth = interest + credit;
            leftovers = sum;
            sum -= credit;

            numbers.add(new Numbers(i + 1, perMonth, interest, credit, leftovers));
        }
    }
}
