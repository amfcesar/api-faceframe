package br.com.anodes.apifaceframe;


import org.apache.tika.Tika;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiFaceframeApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public Tika tika() {
		return new Tika();
	}


	public static void main(String[] args) {

		SpringApplication.run(ApiFaceframeApplication.class, args);
	}

}
