package ar.edu.unju.fi.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;

import ar.edu.unju.fi.dto.MateriaDto;
import ar.edu.unju.fi.model.Materia;

public interface MateriaMapper {
MateriaDto toMateriaDto(Materia materia);
	
	@InheritInverseConfiguration
	Materia toMateria(MateriaDto materiaDto);
	
	List<MateriaDto> toMateriaDtoList(List<Materia> materias);
	
	List<Materia> toMateriaList(List<MateriaDto> materiaDto);
}
