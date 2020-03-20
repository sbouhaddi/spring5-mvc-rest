/**
 * 
 */
package com.sbouhaddi.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sbouhaddi.domain.Category;
import com.sbouhaddi.domain.Customer;
import com.sbouhaddi.domain.Vendor;
import com.sbouhaddi.repositories.CategoryRepository;
import com.sbouhaddi.repositories.CustomerRepository;
import com.sbouhaddi.repositories.VendorRepository;

/**
 * @author bouhaddisabri
 *
 */
@Component
public class Bootstrap implements CommandLineRunner {

	private final CategoryRepository categoryRespository;
	private final CustomerRepository customerRepository;
	private final VendorRepository vendorRepository;

	public Bootstrap(CategoryRepository categoryRespository, CustomerRepository customerRepository,
			VendorRepository vendorRepository) {
		this.categoryRespository = categoryRespository;
		this.customerRepository = customerRepository;
		this.vendorRepository = vendorRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Category fruits = new Category();
		fruits.setName("Fruits");

		Category dried = new Category();
		dried.setName("Dried");

		Category fresh = new Category();
		fresh.setName("Fresh");

		Category exotic = new Category();
		exotic.setName("Exotic");

		Category nuts = new Category();
		nuts.setName("Nuts");

		categoryRespository.save(fruits);
		categoryRespository.save(dried);
		categoryRespository.save(fresh);
		categoryRespository.save(exotic);
		categoryRespository.save(nuts);

		Customer sabri = new Customer();
		sabri.setFirstName("sabri");
		sabri.setLastName("bhd");

		Customer sam = new Customer();
		sam.setFirstName("sam");
		sam.setLastName("bhd");

		Customer amn = new Customer();
		amn.setFirstName("amn");
		amn.setLastName("bhd");

		Customer ragnar = new Customer();
		ragnar.setFirstName("ragnar");
		ragnar.setLastName("lothbrok");

		customerRepository.save(sabri);
		customerRepository.save(sam);
		customerRepository.save(amn);
		customerRepository.save(ragnar);

		Vendor vendor1 = new Vendor();
		vendor1.setName("Vendor 1");
		vendorRepository.save(vendor1);

		Vendor vendor2 = new Vendor();
		vendor2.setName("Vendor 2");
		vendorRepository.save(vendor2);

		System.out.println("Categories Loaded = " + categoryRespository.count());
		System.out.println("Customers Loaded = " + customerRepository.count());
	}

}
