package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the JUGADORES database table.
 * 
 */
@Entity
@Table(name="JUGADORES")
@NamedQuery(name="Jugadores.findAll", query="SELECT j FROM Jugadores j")
public class Jugadores implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Column(name="PLAYER_ID")
	private int playerId;

	@Column(name="COD_POSTAL")
	private String codPostal;

	@Column(name="DORSAL")
	private int dorsal;

	@Column(name="EDAD")
	private int edad;

	@Column(name="NOMBRE")
	private String nombre;

	//bi-directional many-to-one association to Equipo
	@ManyToOne
	@JoinColumn(name="TEAM_ID")
	private Equipo equipo;

	public Jugadores() {
	}

	public int getPlayerId() {
		return this.playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getCodPostal() {
		return this.codPostal;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	public int getDorsal() {
		return this.dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public int getEdad() {
		return this.edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Equipo getEquipo() {
		return this.equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

}