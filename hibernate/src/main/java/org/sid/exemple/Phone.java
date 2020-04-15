package org.sid.exemple;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MapKeyTemporal;
import javax.persistence.OneToMany;
import javax.persistence.TemporalType;



@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    private String number;

    @Enumerated(EnumType.STRING)
    private PhoneType type;

    @OneToMany(mappedBy = "phone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Call> calls = new ArrayList<>();

    @OneToMany(mappedBy = "phone")
    @MapKey(name = "timestamp")
    @MapKeyTemporal(TemporalType.TIMESTAMP )
    private Map<Date, Call> callHistory = new HashMap<>();

    @ElementCollection
    private List<Date> repairTimestamps = new ArrayList<>();
    
	public Phone() {
		super();
	}

	public Phone(Long id, Person person, String number, PhoneType type, List<Call> calls, Map<Date, Call> callHistory,
			List<Date> repairTimestamps) {
		super();
		this.id = id;
		this.person = person;
		this.number = number;
		this.type = type;
		this.calls = calls;
		this.callHistory = callHistory;
		this.repairTimestamps = repairTimestamps;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public PhoneType getType() {
		return type;
	}

	public void setType(PhoneType type) {
		this.type = type;
	}

	public List<Call> getCalls() {
		return calls;
	}

	public void setCalls(List<Call> calls) {
		this.calls = calls;
	}

	public Map<Date, Call> getCallHistory() {
		return callHistory;
	}

	public void setCallHistory(Map<Date, Call> callHistory) {
		this.callHistory = callHistory;
	}

	public List<Date> getRepairTimestamps() {
		return repairTimestamps;
	}

	public void setRepairTimestamps(List<Date> repairTimestamps) {
		this.repairTimestamps = repairTimestamps;
	}

	@Override
	public String toString() {
		return "Phone [id=" + id +", number=" + number + ", type=" + type + ", calls=" + calls
				+ "]";
	}

}

