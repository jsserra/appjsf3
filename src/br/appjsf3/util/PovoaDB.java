package br.appjsf3.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import br.appjsf3.dao.AlunoDao;
import br.appjsf3.dao.BairroDao;
import br.appjsf3.dao.CidadeDao;
import br.appjsf3.dao.CursoDao;
import br.appjsf3.dao.EstadoDao;
import br.appjsf3.dao.ModuloDao;
import br.appjsf3.dao.RuaDao;
import br.appjsf3.model.Aluno;
import br.appjsf3.model.Curso;
import br.appjsf3.model.Endereco;
import br.appjsf3.model.Modulo;
import br.appjsf3.model.address.Bairro;
import br.appjsf3.model.address.Cidade;
import br.appjsf3.model.address.Estado;
import br.appjsf3.model.address.Rua;
import br.appjsf3.model.enums.Desempenho;
import br.appjsf3.model.enums.EstadoCivil;

public class PovoaDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dados();
	}
	
	public static void dados() {
		
		try {
			AlunoDao alunoDao = new AlunoDao();
			BairroDao bairroDao = new BairroDao();
			CidadeDao cidadeDao = new CidadeDao();
			CursoDao cursoDao = new CursoDao();
			EstadoDao estadoDao = new EstadoDao();
			ModuloDao moduloDao = new ModuloDao();
			RuaDao ruaDao = new RuaDao();
			
			String[] arrayRua = new String[] {"Nilton Oliveira Santos", "Alceo Amoroso Lima", "JJ Seabra", "31 de Março"};
			String[] arrayCidade = new String[] {"Santaluz", "Salvador", "Alfenas", "Feira de Santana"};
			String[] arrayBairro = new String[] {"Centro", "Parque IPe", "Caminho das Árvores", "Capuchinos"};
			String[] arrayEstado = new String[] {"AC", "AL", "AM", "AP", "BA", "CE", "DF","ES","GO","MA","MG","MS","MT","PA",
					"PB","PE","PE","PR","RJ","RN","RO","RR","RS","SC","SE","SP","TO"};
			
			String[] arrayModuloDanca = new String[] {"Forró", "Lambada", "Valsa", "Samba"};
			List<Modulo> listaModuloDanca = new ArrayList<>();
			for(String modulo : arrayModuloDanca) {
				listaModuloDanca.add(new Modulo(modulo));
			}
			
			
			String[] arrayModuloViolao = new String[] {"Rock", "Classico", "Sernatejo", "MPB"};
			List<Modulo> listaModuloViolao = new ArrayList<>();
			for(String modulo: arrayModuloViolao) {
				listaModuloViolao.add(new Modulo(modulo));
			}
			
			String[] arrayModuloTeatro = new String[] {"Humor", "Drama", "Comédia", "Suspense"};
			List<Modulo> listaModuloTeatro = new ArrayList<>();
			for(String modulo : arrayModuloTeatro) {
				listaModuloTeatro.add(new Modulo(modulo));
			}
			
			for(int i=0; i <= arrayRua.length; i++ ) {
				ruaDao.salvar(new Rua(arrayRua[i]));
				bairroDao.salvar(new Bairro(arrayBairro[i]));
				cidadeDao.salvar(new Cidade(arrayCidade[i]));
			}
			
			for(int i=0; i <= arrayEstado.length; i++) {
				estadoDao.salvar(new Estado(arrayEstado[i]));
			}
			
			System.out.println("Salvou : RUA - BAIRRO - CIDADE - ESTADO");
			
			Curso curso = new Curso("Dança", 200.00, new HashSet<>(listaModuloDanca));
			cursoDao.salvar(curso);
			cursoDao.salvar(new Curso("Violao", 180.00, new HashSet<>(listaModuloViolao)));
			cursoDao.salvar(new Curso("Teatro", 300.50, new HashSet<>(listaModuloTeatro)));
			
			System.out.println("Salvou Cursos e Módulos");
			
			Endereco endereco = new Endereco(
					ruaDao.buscarPorId(1),
					bairroDao.buscarPorId(2),
					cidadeDao.buscarPorId(2),
					estadoDao.buscarPorId(2),
					"23.433-78");
			
			System.out.println("Associou as tabelas : Rua, Bairro, Cidade, Estado a um endereço");
			
			Aluno aluno = new Aluno("Romuel", "Dias de Oliveira", new Date(), 'M', EstadoCivil.CASADO, 
					"12.345.678-90", "001.222.333-99", endereco, "romuel@gmail.com",
					"(75)99999-7676", curso, true, Desempenho.EXCELENTE, 100.00, "teste");
			alunoDao.salvar(aluno);
					
			System.exit(0);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
