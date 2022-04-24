package in.nareshit.rafey.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="doctor_tab")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
	
	@Id
	@Column(name="doc_id_col")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="doc_fn_col")
	private String firstName;
	
	@Column(name="doc_ln_col")
	private String lastName;
	
	@Column(name="doc_email_col")
	private String email;
	
	@Column(name="doc_addr_col")
	private String address;

	@Column(name="doc_mob_col")
	private String mobile;
	
	@Column(name="doc_gen_col")
	private String gender;
	
	@Column(name="doc_note_col")
	private String note;
	
	@Column(name="doc_img_col")
	private String photoLoc;        //url store to db
	
	@Column(name="image")
	private String photos;			//local storage
	
	@Transient
	private String photosImagePath;	//url store to db for path
	
	public String getPhotosImagePath() {
		if (photos == null || id == null) 
			return null;
		return "/user-photos/" + id + "/" + photos;
		}

}
