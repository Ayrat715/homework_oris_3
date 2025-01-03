class User {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String licenseNumber;
    private String experienceYears;
    private String city;

    public User(Long id, String firstName, String lastName, Integer age, String licenseNumber, String experienceYears, String city) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.licenseNumber = licenseNumber;
        this.experienceYears = experienceYears;
        this.city = city;
    }

    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Integer getAge() { return age; }
    public String getLicenseNumber() { return licenseNumber; }
    public String getExperienceYears() { return experienceYears; }
    public String getCity() { return city; }
}