package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import ar.edu.unju.fi.dto.CarreraDTO;
import ar.edu.unju.fi.dto.DocenteDto;
import ar.edu.unju.fi.dto.MateriaDto;
import ar.edu.unju.fi.service.ICarreraService;
import ar.edu.unju.fi.service.IDocenteService;
import ar.edu.unju.fi.service.IMateriaService;

@Controller
@RequestMapping("/materia")
public class MateriaController {
	@Autowired
	private MateriaDto materiaDto;
	@Autowired
	private DocenteDto docenteDto;
	@Autowired
	private CarreraDTO carreraDTO;
	@Autowired
	private IMateriaService materiaService;
	@Autowired
	private ICarreraService carreraService;
	@Autowired
	private IDocenteService docenteService;
	
	@GetMapping("/listado")
	public String getMateriaPage(Model model) {
		model.addAttribute("materias", materiaService.findAll());
		model.addAttribute("titulo", "Materias");
		return "materias";
	}
	
	@GetMapping("/alta")
	public String getMateriaAltaPage(Model model) {
		boolean edicion = false;
		model.addAttribute("titulo", "Nueva Materia");
		model.addAttribute("materia", materiaDto);
		model.addAttribute("edicion", edicion);
		model.addAttribute("docentes", docenteService.findAll());
		model.addAttribute("carreras", carreraService.findAll());
		return "materiasForm";
	}
	
	@PostMapping("/guardar")
	public ModelAndView guardarNuevaMateria(@ModelAttribute("materia") MateriaDto materiaDto) {
		docenteDto = docenteService.findById(materiaDto.getDocenteDto().getLegajo());
		carreraDTO = carreraService.findById(materiaDto.getCarreraDto().getCodigo());
		materiaDto.setDocenteDto(docenteDto);
		materiaDto.setCarreraDto(carreraDTO);
		ModelAndView modelView = new ModelAndView("materias");
		materiaService.saveMateriaDto(materiaDto);
		modelView.addObject("materias", materiaService.findAll());
		return modelView;
	}
	
	@GetMapping("/modificar/{codigo}")
	public String getMateriasModificarPage(Model model, @PathVariable(value="codigo")int codigo) {
		MateriaDto materiaEncontrada = new MateriaDto();
		boolean edicion = true;
		materiaEncontrada = materiaService.findById(codigo);
		model.addAttribute("edicion", edicion);
		model.addAttribute("materia", materiaEncontrada);
		model.addAttribute("titulo", "Modificar Materia");
		model.addAttribute("docentes", docenteService.findAll());
		model.addAttribute("carreras", carreraService.findAll());
		return "materiasForm";
	}
	
	@PostMapping("/modificar")
	public String modificarMateria(@ModelAttribute("materia") MateriaDto materiaDto) {
		docenteDto = docenteService.findById(materiaDto.getDocenteDto().getLegajo());
		carreraDTO = carreraService.findById(materiaDto.getCarreraDto().getCodigo());
		materiaDto.setDocenteDto(docenteDto);
		materiaDto.setCarreraDto(carreraDTO);
		materiaService.edit(materiaDto);
		return "redirect:/materia/listado";
	}
	
	@GetMapping("/eliminar/{codigo}")
	public String eliminarMateria(@PathVariable(value="codigo")int codigo) {
		materiaService.deleteByID(codigo);
		return "redirect:/materia/listado";
	}
}