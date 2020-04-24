package org.sid.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.Version;

/**
 * 
 * 
 * @author Anass
 * OneToMany: LAZY
   ManyToOne: EAGER
   ManyToMany: LAZY
   OneToOne: EAGER
 *
 */

//Relation oneTMany , ManyToOne sans préciser MappedBy: creer une table de jointure(id_Student,id_idBook)
//Relation oneTMany , ManyToOne avec  MappedBy: creer un colone de jointure dans la table Book.
//Relation oneToMany sans ManyToOne: creer une table de jointure(id_Student,id_idBook)
//A l'appel du web service student la boucle infini ne resulte pas de la maniere dont on fait le chargement,solution @JsonIgnore
// le web service n'utilise pas la méthode toString() pour ecrire l'objet, il uitlise jackson pour tranfomer l'objet en json
//Une execption LazyInitializationException explose parce qu'au moment où toString veut afficher book la session est fermé,et le onToMany est par default est un fetche.Lazy
//----------------------------------------------------------------
////problème N+1:FetchType.EAGER: pour recupuer 2 student il lance une requete pour les student et pour chaque student 
                                  //il lance une requete pour recuperer les book donc ca fait une 1 + 2 requete
                                  //Puisque  book n'est pas initialisée lors de l'extraction des student (bad for performance)
                                  //la solution @Query("from Student s join fetch s.books") une seul une requete suffit
//Bonne pratique:il faut toujours utiliser dans relation bidirectionnel car les relation 
  // unidirectionne cree des table de jointure donc des requtes en plus en cas d'insertion ou supression...

@Entity
public class Student implements Serializable {
	
	// imporant pour mettre à jour une collection d'objet il vaut mieux le faire avec un seul requete Sql
	// si on le fait avec Hibernate,il charge tout la collection et pour chaque entité doit engendre une requete pour la mettre à jour
	
	
	//quand hibernate charge une entité dans le contexte de parsitance(session) il cree entité reference de cette entité
	// et il la stocke le cache de la session et il cree une autre dans le contexte de persitance de la session
	//quand cette dernier subi des modifications hibrnate n'ecrit pas aussistot ces changement dans la base de données.
	//il retard cette mise à jour jusqu'au dernier moment, quand on fait un commit de la transaction hibernate declenche un 
	//mécanisme appelé Dirty checking qui lui permet de savoir quels sont les entitées qui ont été modifiée après il fait un
	// flush pour mettre a jour les entités references qui sont sotckeé dans le cache de la session et enfin il envoi les modifs 
	//à la base de donées
	//il faut tjrs laisse l'operation flush à la derniere minute parce que le flush declenche le dirty cheking 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull//hibernate verifie si id egal null, nullable c'est la base de donnee qui verfie si id egal nullet non hibernate
	@Column(updatable=false,nullable=false)
	private Long id;
	
	@NaturalId
    @Column(nullable = false, unique = true)
	private String nom;
	
	@Column(updatable=false)
	private String prenom;
	
	//dateNaissance ne changera jamais de valeur donc il est conseille d'utiliser updatable parce que ça désactive 
	//(on gagne en performance) le dirty cheking qui permet à hibernate de savoir si une entité attachée a une session s'il a été modifier ou non
	//  attention si on modifie dateNaissance la modification ne sera jamais ecrit sur la base de donnés 
    @Column(updatable=false)
	private LocalDateTime  dateNaissance;
    
//	@JsonManagedReference
	@OneToMany (mappedBy="student",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	//il faut uiliser Set  pour eviter le problème MultipleBagFetchException
	private Set<Book> books = new HashSet<Book>();
	
	//verouiller l'entité Student au moment de mise à jour
	@Version
    private Integer version;
	
	@PreRemove
	public void verifier(){
		if(this.books.isEmpty()){
			throw new RuntimeException("student à des books");
		}
	}
	
	public Student() {
		super();
	}
	


	public Student(Long id, String nom, String prenom, LocalDateTime  dateNaissance, Set<Book> books) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.books = books;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public LocalDateTime   getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(LocalDateTime   dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance
				+ ", books=" + books + "]";
	}

}
