package com.wisdom.tutorial.bootstrap;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.wisdom.tutorial.domain.Product;
import com.wisdom.tutorial.repository.ProductRepository;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private ProductRepository productRepository;

	private Logger log = LogManager.getLogger(SpringJpaBootstrap.class);

	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		loadProducts();
	}

	private void loadProducts() {
		Product shirt = new Product();
		shirt.setDescription("Wisdom Academy Shirt");
		shirt.setPrice(new BigDecimal("1000"));
		shirt.setProductId("235268845711068308");
		productRepository.save(shirt);

		log.info("Saved Shirt - id: " + shirt.getId());

		Product mug = new Product();
		mug.setDescription("Wisdom Academy Mug");
		mug.setProductId("168639393495335947");
		mug.setPrice(new BigDecimal("200"));
		productRepository.save(mug);

		log.info("Saved Mug - id:" + mug.getId());
	}

}
