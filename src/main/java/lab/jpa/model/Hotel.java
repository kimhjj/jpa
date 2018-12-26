package lab.jpa.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Hotel {
	@Id
    private String id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Embedded
    private Address address;

    protected Hotel() {
    }

    public Hotel(String id, String name, Grade grade, Address address) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Grade getGrade() {
        return grade;
    }

    public Address getAddress() {
        return address;
    }

    public void changeAddress(Address newAddress) {
        this.address = newAddress;
    }

	@Override
	public String toString() {
		return "Hotel [id=" + id + ", name=" + name + ", grade=" + grade + ", address=" + address + "]";
	}
}
