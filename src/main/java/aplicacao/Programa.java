package aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

public class Programa {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();
		
		
		//para remover um objeto instanciado no banco de dados ele precisa estar "monitorado", ou seja, o objeto acabou de ser inserido ou foi encontrado no banco de dados e o ainda não fechou o EntityManager.
		
		Pessoa p = em.find(Pessoa.class, 2);
		
		//Além disso, transações que não são simples consulta devem ser feitas usando o getTransaction
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
		
		System.out.println("concluído");
		em.close();
		emf.close();
	}

}
