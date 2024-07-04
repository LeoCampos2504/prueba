package ar.edu.unju.fi.service;

import java.util.List;
import ar.edu.unju.fi.dto.MateriaDto;

public interface IMateriaService {
List<MateriaDto> findAll();
	
	MateriaDto findById(Integer codigo);
	
	void saveMateriaDto (MateriaDto materiaDto);
	
	void deleteByID(Integer codigo);
	
	void edit(MateriaDto materiaDto);
}
