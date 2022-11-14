package br.com.miniautorization.mensageria;

import br.com.miniautorization.mensageria.producer.card.CardProducerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MensageriaApplication implements ApplicationRunner {

	@Autowired
	private CardProducerImpl cardProducer;

	public static void main(String[] args) {
		SpringApplication.run(MensageriaApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		cardProducer.create();
	}
}
