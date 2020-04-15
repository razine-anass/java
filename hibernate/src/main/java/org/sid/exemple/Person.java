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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.Version;

@NamedQueries(
	   @NamedQuery(
	       name = "Person.chercherNamedQuerie",
	       query = "from Person p left join fetch p.phones phones join fetch p.addresses adresses  where p.name like :name"
	    )
	)
@Entity
public class Person {

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    private String name;

	    private String nickName;

	    private String address;

	    @Temporal(TemporalType.TIMESTAMP )
	    private Date createdOn;

	    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	    @OrderColumn(name = "order_id")
	    private List<Phone> phones = new ArrayList<>();

	    @ElementCollection(fetch=FetchType.LAZY)
	    @MapKeyEnumerated(EnumType.STRING)
	    private Map<AddressType, String> addresses = new HashMap<>();

	    @Version
	    private int version;
	    
	    

		public Person() {
			super();
		}

		public Person(Long id, String name, String nickName, String address, Date createdOn, List<Phone> phones,
				Map<AddressType, String> addresses, int version) {
			super();
			this.id = id;
			this.name = name;
			this.nickName = nickName;
			this.address = address;
			this.createdOn = createdOn;
			this.phones = phones;
			this.addresses = addresses;
			this.version = version;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getNickName() {
			return nickName;
		}

		public void setNickName(String nickName) {
			this.nickName = nickName;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public Date getCreatedOn() {
			return createdOn;
		}

		public void setCreatedOn(Date createdOn) {
			this.createdOn = createdOn;
		}

		public List<Phone> getPhones() {
			return phones;
		}

		public void setPhones(List<Phone> phones) {
			this.phones = phones;
		}

		public Map<AddressType, String> getAddresses() {
			return addresses;
		}

		public void setAddresses(Map<AddressType, String> addresses) {
			this.addresses = addresses;
		}

		public int getVersion() {
			return version;
		}

		public void setVersion(int version) {
			this.version = version;
		}

		@Override
		public String toString() {
			return "Person [id=" + id + ", name=" + name + ", nickName=" + nickName + ", address=" + address
					+ ", createdOn=" + createdOn + ", phones=" + phones + ", addresses=" + addresses + ", version="
					+ version + "]";
		}
 
		
	    
	}
