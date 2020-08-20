package br.appjsf3.util;



import br.appjsf3.dao.CidadeDao;
import br.appjsf3.model.address.Cidade;

public class ProgramCidade {

	public static void main(String[] args) throws appjsf3Exception {
		// TODO Auto-generated method stub
		
		salvar();

	}
	
	public static void salvar() throws appjsf3Exception {
		Cidade c = new Cidade("Macaé");
		CidadeDao dao = new CidadeDao();
		dao.salvar(c);
	}

}
