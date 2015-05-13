package education;


import java.io.Serializable;

public class SchoolCons implements Serializable {
    private long id;
    private String name;
    private String degree;
    private String area;
    private String cost;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Name of School: "+name+"\n"+
                "Degree: "+degree+"\n"+
                "Area of Study: "+area+"\n"+
                "Yearly Cost: "+cost;
    }
}
