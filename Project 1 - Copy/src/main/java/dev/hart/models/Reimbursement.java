package dev.hart.models;

/**
 * This concrete Reimbursement class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>Description</li>
 *     <li>Creation Date</li>
 *     <li>Resolution Date</li>
 *     <li>Receipt Image</li>
 * </ul>
 *
 */
public class Reimbursement extends AbstractReimbursement {
    private int id;
    private Status status;
    private String statusString;
    private String authorString;
    private String resolverString;
    private User author;
    private User resolver;
    private int amount;

    private int cost;
    private String name;
    private String grade;
    private String date;
    private String location;
    private String description;
    private String gradingFormat;
    private String eventType;

    public Reimbursement() {
        super();
    }

    /**
     * This includes the minimum parameters needed for the {@link /*com.revature.models.AbstractReimbursement} class.
     * If other fields are needed, please create additional constructors.
     */
   /* public Reimbursement(int id, Status status, User author, User resolver, double amount) {
        super(id, status, author, resolver, amount);
    }*/

    public Reimbursement(int id, Status status, User author, User resolver, double amount) {
    }

    public Reimbursement(int id, Status status, User author, User resolver, int amount, int cost, String name,
                         String grade, String date, String location, String description, String gradingFormat, String eventType) {
        this.id = id;
        this.status = status;
        this.author = author;
        this.resolver = resolver;
        this.amount = amount;
        this.cost = cost;
        this.name = name;
        this.grade = grade;
        this.date = date;
        this.location = location;
        this.description = description;
        this.gradingFormat = gradingFormat;
        this.eventType = eventType;

    }

    public Reimbursement(int id, String statusString, String authorString, String resolverString, int amount, int cost,
                         String name, String grade, String date, String location, String description, String gradingFormat, String eventType) {
        this.id = id;
        this.statusString = statusString;
        this.authorString = authorString;
        this.resolverString = resolverString;
        this.amount = amount;
        this.cost = cost;
        this.name = name;
        this.grade = grade;
        this.date = date;
        this.location = location;
        this.description = description;
        this.gradingFormat = gradingFormat;
        this.eventType = eventType;
    }


    public Reimbursement(int id, Status status, String authorString, String resolverString, int amount, int cost, String name, String grade, String date, String location, String description, String gradingFormat, String eventType) {
        this.id = id;
        this.status = status;
        this.authorString = authorString;
        this.resolverString = resolverString;
        this.amount = amount;
        this.cost = cost;
        this.name = name;
        this.grade = grade;
        this.date = date;
        this.location = location;
        this.description = description;
        this.gradingFormat = gradingFormat;
        this.eventType = eventType;
    }



    /*public Reimbursement(String name, String statusString, String description, double amount, int id, String authorString, double cost, String location,
                         String gradingformat, String eventtype, String resolverString, String grade, String date) {
        this.id = id;
        this.statusString = statusString;
        this.authorString = authorString;
        this.resolverString = resolverString;
        this.amount = amount;
        this.cost = cost;
        this.name = name;
        this.grade = grade;
        this.date = date;
        this.location = location;
        this.description = description;
        this.gradingFormat = gradingFormat;
        this.eventType = eventType;
    }*/

    public String getStatusString() {
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getAuthorString() {
        return authorString;
    }

    public void setAuthorString(String authorString) {
        this.authorString = authorString;
    }

    public String getResolverString() {
        return resolverString;
    }

    public void setResolverString(String resolverString) {
        this.resolverString = resolverString;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Status getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }


    public User getAuthor() {
        return this.author;
    }

    @Override
    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public User getResolver() {
        return this.resolver;
    }

    @Override
    public void setResolver(User resolver) {
        this.resolver=resolver;
    }


    public int getIntAmount() {
        return this.amount;
    }


    public void setIntAmount(int amount) {
        this.amount=amount;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGradingFormat() {
        return gradingFormat;
    }

    public void setGradingFormat(String gradingFormat) {
        this.gradingFormat = gradingFormat;
    }

    /* @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public Status getStatus() {
        return super.getStatus();
    }

    @Override
    public User getAuthor() {
        return super.getAuthor();
    }

    @Override
    public User getResolver() {
        return super.getResolver();
    }

    @Override
    public double getAmount() {
        return super.getAmount();
    }*/

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", status=" + status +
                ", author=" + authorString +
                ", resolver=" + resolverString +
                ", amount=" + amount +
                ", cost=" + cost +
                ", name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                ", date='" + date + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", gradingFormat='" + gradingFormat + '\'' +
                '}';
    }
}
