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
// 이것을 써줘야 조인
// sight_detail에 sight_id가 FK로 생성됨
// SecondaryMain.java에서 하나씩 넣음. 현재는 일대일
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
    private Long id;	// 자동 증가
	
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
    		// 프로퍼티 명과 컬럼 명 이구나..
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
