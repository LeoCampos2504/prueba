package ar.edu.unju.fi.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.MateriaDto;
import ar.edu.unju.fi.mapper.MateriaMapper;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.IMateriaService;

@Service
public class MateriaServiceImpl implements IMateriaService{
	@Autowired
	private MateriaMapper materiaMapper;
	
	@Autowired
	private MateriaRepository materiaRepository;
	@Override
	public List<MateriaDto> findAll() {
		List<Materia> materias = materiaRepository.findAll();
		List<MateriaDto> materiaDto = materiaMapper.toMateriaDtoList(materias);
		return materiaDto;
	}

	@Override
	public MateriaDto findById(Integer codigo) {
		Optional<Materia> materiaBuscada = materiaRepository.findById(codigo);
		return materiaBuscada.map(materiaMapper::toMateriaDto).orElse(null);
	}

	@Override
	public void saveMateriaDto(MateriaDto materiaDto) {
		Materia materia = materiaMapper.toMateria(materiaDto);
		materiaRepository.save(materia);
		
	}

	@Override
	public void deleteByID(Integer codigo) {
		materiaRepository.deleteById(codigo);
		
	}

	@Override
	public void edit(MateriaDto materiaDto) {
		Materia materia = materiaMapper.toMateria(materiaDto);
		materiaRepository.save(materia);
		
	}

}
