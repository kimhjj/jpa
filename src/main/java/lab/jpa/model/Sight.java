package lab.jpa.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SequenceGenerator;

@Entity
@SecondaryTable(
	name = "sight_detail",
	pkJoinColumns = @PrimaryKeyJoinColumn(
	    name = "sight_id",
	    referencedColumnName = "id"
	)
)
public class Sight {
	@Id
    @SequenceGenerator(name="sight_seq_gen", sequenceName="sight_seq", allocationSize=1)
    @GeneratedValue(generator="sight_seq_gen")
    private Long id;
    private String name;

    @Embedded
    private Address korAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "zipcode", column = @Column(name = "eng_zipcode")),
            @AttributeOverride(name = "address1", column = @Column(name = "eng_addr1")),
            @AttributeOverride(name = "address2", column = @Column(name = "eng_addr2"))
    })
    private Address engAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="hoursOfOperation", column = @Column(name = "hours_op", table="sight_detail")),
            @AttributeOverride(name="holidays", column = @Column(table="sight_detail")),
            @AttributeOverride(name="facilities", column = @Column(table="sight_detail"))
    })
    private SightDetail detail;

    public Sight() {
    }

    public Sight(String name, Address korAddress, Address engAddress) {
        this.name = name;
        this.korAddress = korAddress;
        this.engAddress = engAddress;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getKorAddress() {
        return korAddress;
    }

    public Address getEngAddress() {
        return engAddress;
    }

    public SightDetail getDetail() {
        return detail;
    }

    public void setDetail(SightDetail detail) {
        this.detail = detail;
    }
}
