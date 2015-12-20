import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.gov.inmetro.enumerator.GrandezaMedida;
import br.gov.inmetro.model.Medidor;
import br.gov.inmetro.model.MedidorPK;
import br.gov.inmetro.util.JPAUtil;

public class teste {

	public static void main(String[] args) {
		List<GrandezaMedida> lista = new ArrayList<GrandezaMedida>();
		Medidor medidor = new Medidor();
		MedidorPK id = new MedidorPK();
		
		id.setCodSITAD("1111");
		id.setNumeroSerie(1);
		
		lista.add(GrandezaMedida.ENERGIA_ATIVA);
		lista.add(GrandezaMedida.ENERGIA_REATIVA);
		
		medidor.setId(id);
		
		EntityManager em = JPAUtil.getEntityManager();
		
        EntityTransaction trx = em.getTransaction();	
		
		trx.begin();
		
		em.persist(medidor);	

		trx.commit();		
	}

}
