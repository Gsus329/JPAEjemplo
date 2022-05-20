package test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Equipo;
import model.Jugadores;

public class Launch {

	public static void main(String[] args) {
		//obtencionUnaSolaEntidad();
		//obtencionUnaSolaEntidadSegundoMetodo();
		//obtencionUnaSolaEntidadTercerMetodo();
		//listadoEntidades();
		//listadoEntidadesSegundoMetodo();
		//listadoEntidadesTercerMetodo();
		//creacionEntidad();
		//modificacionEntidad();
		//eliminacionEntidad();

	}

	private static void obtencionUnaSolaEntidad() {
		// Necesitamos un EntityManager para trabajar con las entidades, lo obtenemos a través del 
				// EntityManagerFactory y este se obtiene por el nombre de la unidad de persistencia, que
				// coincide con la del fichero XML
				EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAEjemplo");

				EntityManager em = entityManagerFactory.createEntityManager(); // Obtengo el EntityManager

				Equipo eq = (Equipo) em.find(Equipo.class, 1); // Con el EntityManager puedo buscar una entidad de un tipo y un identificador
				Jugadores ju = (Jugadores) em.find(Jugadores.class, 2); // Con el EntityManager puedo buscar una entidad de un tipo y un identificador
				
				// Para demostrar que he accedido a la BBDD, muestro en pantalla.
				System.out.println("Equipo localizado -> " + eq.getTeamId() + " nombre: " + eq.getEqNombre() + ", Localidad " + eq.getPoblacion());
				//para demostrar que funciona llamada entre tablas
				System.out.println("Jugador localizado -> " + ju.getPlayerId()+ " nombre: "
				+ ju.getNombre()+", juega en equipo "+ ju.getEquipo().getEqNombre() + ", Localidad " + ju.getEquipo().getPoblacion());
												
				em.close(); // Cierro el objeto EntityManager.
		
	}
	/**
	 * Para no repetir mucho el código, creo este método, a utilizar en todos los demás.
	 * @return
	 */
	private static EntityManager getNewEntityManager () {
		// Obtengo un objeto de tipo EntityManager
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPAEjemplo");
		EntityManager em = entityManagerFactory.createEntityManager();
		return em;
	}
	
	
	/**
	 * 
	 * @param ju
	 */
	private static void muestraEnLaConsola (Jugadores ju) {
		if (ju!= null) {
			System.out.println("Jugador localizado -> " + ju.getPlayerId()+ " nombre: "
					+ ju.getNombre()+", juega en equipo "+ ju.getEquipo().getEqNombre() + ", Localidad " + ju.getEquipo().getPoblacion());}
		else {
			// Si el usuario fuese null significaría que no se ha encontrado.
			System.out.println("Jugador no encontrador");
		}
	}
	
	/**
	 * Ejemplo de como obtener una entidad a partir de una consulta SQL nativa.
	 */
	private static void obtencionUnaSolaEntidadSegundoMetodo () {
		EntityManager em = getNewEntityManager(); 
		
		// Realizo una consulta SQL nativa. La fila resultante de la consulta se mapeará automáticamente en un usuario,
		// una instancia de la clase Usuario.
		Query q = em.createNativeQuery("SELECT * from JUGADORES where PLAYER_ID= ?", Jugadores.class);
		q.setParameter(1, 1); // Ejemplo de como introducir parámetros en la consulta
		Jugadores ju = (Jugadores) q.getSingleResult(); // Utilizo getSingleResult porque sé que sólo hay un registro resultante de la consulta
		
		muestraEnLaConsola(ju); 
		
		em.close(); // Cierro el objeto entityManager
	}
//	
//	
//	
	/**
	 * Ejemplo de como obtener una entidad de tipo jugadore, utilizando JPQL, en lugar de SQL
	 * JPQL está preparado para el lenguaje de consulta de persistencia de Java
	 */
	private static void obtencionUnaSolaEntidadTercerMetodo () {
		EntityManager em = getNewEntityManager(); 

		Query q = em.createQuery("SELECT j from Jugadores j where j.playerId = :PLAYER_ID", Jugadores.class); // Consulta en JPQL, coge anotación NamedQuery
		q.setParameter("PLAYER_ID", 3); // Puedo introducir el parámetro a través del identificador
		Jugadores ju = (Jugadores) q.getSingleResult(); // Obtengo un registro
		
		muestraEnLaConsola(ju); 
		
		em.close();
	}
//	
//	
//	
	/**
	 * Al igual que puedo obtener una entidad, en este puedo obtener un listado.
	 */
	private static void listadoEntidades () {
		EntityManager em = getNewEntityManager(); 
		
		Query q = em.createNativeQuery("SELECT * FROM JUGADORES;", Jugadores.class); // Consulta con SQL nativa
		
		List<Jugadores> jugadores = (List<Jugadores>) q.getResultList(); // No utilizo getSingleResult
		
		// Muestro los usuarios hallados
		for (Jugadores ju : jugadores) {
			muestraEnLaConsola(ju); 
		}
		
		em.close();
	}
	
//	
	/**
	 * Ejemplo de como obtener un listado a través de JPQL
	 */
	private static void listadoEntidadesSegundoMetodo () {
		EntityManager em = getNewEntityManager(); 
		
		Query q = em.createQuery("SELECT j from Jugadores j", Jugadores.class); // Consulta con JPQL
		
		List<Jugadores> jugadores = (List<Jugadores>) q.getResultList();
		
		for (Jugadores ju : jugadores) {
			muestraEnLaConsola(ju); 
		}
		
		em.close(); // Cierro el entityManager
	}
//	
//	
	/**
	 * Ejemplo de uso de una namedQuery, las "namedQuery" se encuentra en la clase de la entidad. En este caso
	 * se encuentra en la clae Usuario.
	 */
	private static void listadoEntidadesTercerMetodo () {
		EntityManager em = getNewEntityManager(); 
		
		Query q = em.createNamedQuery("Jugadores.findAll"); // Ejecuto la NamedQuery de la clase Usuario
		
		List<Jugadores> jugadores = (List<Jugadores>) q.getResultList();
		
		for (Jugadores ju : jugadores) {
			muestraEnLaConsola(ju); 
		}
		
		em.close(); // Cierro el entityManager
	}
//	
//	
//
	/**
	 * Ejemplo de como se puede crear una entidad y almacenarla en el esquema de BBDD
	 * necesitamos tabla SEQUENCE en la BBDD
	 */
	private static void creacionEntidad () {
		EntityManager em = getNewEntityManager(); 

		// Creo un nuevo objeto, un nuevo usuario, y le asigno valores
		Jugadores ju = new Jugadores();
		ju.setNombre("Paco");
		ju.setDorsal(5);
		ju.setEdad(27);
		ju.setCodPostal("23005");
		ju.setEquipo(em.find(Equipo.class, 2)); // Busco equipo con id = 2 y se la asigno al jugador
				
		// Guardo el objeto mediante una transacción
		em.getTransaction().begin();
		em.persist(ju);
		em.getTransaction().commit();
		
		em.close(); // Cierro el entityManager
	}
	
//	
//	
	/**
	 * Ejemplo de modificación de una entidad
	 */
	private static void modificacionEntidad () {
		EntityManager em = getNewEntityManager(); 

		// Obtengo un usuario que voy a modificar, utilizo JPQL pero puedo usar otros métodos, recomiendo SQL nativo
		Query q = em.createQuery("SELECT u FROM Jugadores as u where u.nombre = 'Paco'", Jugadores.class);
		List<Jugadores> jugadores = q.getResultList();
		
		// Mediante una transacción modifico todos los registros que necesite
		em.getTransaction().begin();
		for (Jugadores ju : jugadores) {
			ju.setNombre("Francisco");
			em.persist(ju);
		}
		em.getTransaction().commit();
		
		em.close(); // Cierro el entityManager
	}
//	
//	
	/**
	 * Ejemplo de como eliminar una entidad
	 */
	private static void eliminacionEntidad () {
		EntityManager em = getNewEntityManager(); 

		Query q = em.createQuery("SELECT u FROM Jugadores as u where u.nombre = 'Francisco'", Jugadores.class);
		
		List<Jugadores> jugadores = q.getResultList(); // Obtengo la lista de posibles usuarios
		
		// Mediante la transacción, elimino la entidad y, por tanto, también elimino el registro en la BBDD
		em.getTransaction().begin();
		for (Jugadores ju : jugadores) {
			em.remove(ju);
		}
		em.getTransaction().commit();
		
		em.close(); // Cierro el entityManager
	}
	
//	

}
