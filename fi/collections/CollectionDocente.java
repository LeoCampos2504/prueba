package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Docente;

@Component
public class CollectionDocente {

	private static List<Docente> docentes = new ArrayList<Docente>();
	
	public static void cargarDocentes(List<Docente> docentes) {
		docentes.add(new Docente(1526, 2, "Marcelo", "Perez", (long)388425168, null));
		docentes.add(new Docente(1432, 3, "Ariel", "Vega", (long)388458746, null));
		docentes.add(new Docente(1365, 4, "Juan", "Rodriguez", (long)388498524, null));
	}
	
	public static List<Docente> getDocentes() {
		if (docentes.isEmpty()) {
			cargarDocentes(docentes);
		}
		return docentes;
	}
	
	public static void agregarDocente(Docente docente) {
		docentes.add(docente);
	}
	
	public static void elminarDocente(int legajo) {
		Iterator<Docente> iterator = docentes.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getLegajo() == legajo) {
				iterator.remove();
			}
		}
	}
	
	public static void modificarDocente(Docente docente) {
		int buscado = docente.getLegajo();
		for (Docente docen : docentes) {
			if (docen.getLegajo()==buscado) {
				docen.setNombre(docente.getNombre());
				docen.setApellido(docente.getApellido());
				docen.setTelefono(docente.getTelefono());
			}
		}
	}
	
	public static Docente buscarDocente(int legajo) {
		Predicate<Docente> filterLegajo = d -> d.getLegajo() == legajo;
		Optional<Docente> docente = docentes.stream().filter(filterLegajo).findFirst();
        if (docente.isPresent()) {
            return docente.get();
        } else {
            return null;
        }
	}

}