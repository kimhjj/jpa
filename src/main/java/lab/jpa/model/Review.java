package lab.jpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "hotel_review")
public class Review {
	@Id
    @SequenceGenerator (name="review_seq_gen", sequenceName="hotel_review_seq", allocationSize=1)
	@GeneratedValue(generator="review_seq_gen")
	private Long id;
    @Column(name = "hotel_id")
    private String hotelId;
    private int rate;
    private String comments;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "rating_date")
    private Date ratingDate;
    
	public Review(String hotelId, int rate, String comments, Date ratingDate) {
		super();
		this.id = id;
		this.hotelId = hotelId;
		this.rate = rate;
		this.comments = comments;
		this.ratingDate = ratingDate;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getRatingDate() {
		return ratingDate;
	}
	public void setRatingDate(Date ratingDate) {
		this.ratingDate = ratingDate;
	}
    
}
