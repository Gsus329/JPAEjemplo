package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the EQUIPO database table.
 * 
 */
@Entity
@Table(name="EQUIPO")
@NamedQuery(name="Equipo.findAll", query="SELECT e FROM Equipo e")
public class Equipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TEAM_ID")
	private int teamId;

	@Column(name="COD_POSTAL")
	private String codPostal;

	@Column(name="EQ_NOMBRE")
	private String eqNombre;

	@Column(name="ESTADIO")
	private String estadio;

	@Column(name="POBLACION")
	private String poblacion;

	@Column(name="PROVINCIA")
	private String provincia;

	//bi-directional many-to-one association to Jugadore
	@OneToMany(mappedBy="equipo")
	private List<Jugadores> jugadores;

	public Equipo() {
	}

	public int getTeamId() {
		return this.teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getCodPostal() {
		return this.codPostal;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	public String getEqNombre() {
		return this.eqNombre;
	}

	public void setEqNombre(String eqNombre) {
		this.eqNombre = eqNombre;
	}

	public String getEstadio() {
		return this.estadio;
	}

	public void setEstadio(String estadio) {
		this.estadio = estadio;
	}

	public String getPoblacion() {
		return this.poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public List<Jugadores> getJugadores() {
		return this.jugadores;
	}

	public void setJugadores(List<Jugadores> jugadores) {
		this.jugadores = jugadores;
	}

	public Jugadores addJugadore(Jugadores jugadore) {
		getJugadores().add(jugadore);
		jugadore.setEquipo(this);

		return jugadore;
	}

	public Jugadores removeJugadore(Jugadores jugadore) {
		getJugadores().remove(jugadore);
		jugadore.setEquipo(null);

		return jugadore;
	}

}