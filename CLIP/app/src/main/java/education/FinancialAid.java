package education;

import java.io.Serializable;

public class FinancialAid implements Serializable {

    private long id;
    private String loan;
    private String scholarship;
    private String grants;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLoan() {
        return loan;
    }

    public void setLoan(String loan) {
        this.loan = loan;
    }

    public String getScholarship() {
        return scholarship;
    }

    public void setScholarship(String scholarship) {
        this.scholarship = scholarship;
    }

    public String getGrants() {
        return grants;
    }

    public void setGrants(String grants) {
        this.grants = grants;
    }

    @Override
    public String toString() {
        return "Loan: "+loan+"\n"+
                "Scholarship: "+scholarship+"\n"+
                "Grant: "+grants;
    }
}