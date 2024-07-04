package ar.edu.unju.fi.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MateriaDto {

		private Integer codigo;
		private String nombre;
		private String curso;
		private Byte cantHoras;
		private Boolean modalidad;
		@Autowired
		private DocenteDto docenteDto;
		@Autowired
		private CarreraDTO carreraDto;
		
	}
