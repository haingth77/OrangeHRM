package common;

public class SubUnit {
    String subUnitName;
    Integer amount;
    double percentage;

    public SubUnit (String subUnitName, Integer amount, double percentage) {
        this.subUnitName = subUnitName;
        this.amount = amount;
        this.percentage = percentage;
    }

    public void infor() {
        System.out.println(this.subUnitName);
        System.out.println(this.amount);
        System.out.println(this.percentage);
    }

    public String getSubUnitName() {
        return subUnitName;
    }

    public Integer getAmount() {
        return amount;
    }

    public double getPercentage() {
        return percentage;
    }
}
