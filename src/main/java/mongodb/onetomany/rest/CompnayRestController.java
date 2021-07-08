package mongodb.onetomany.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mongodb.onetomany.document.Company;
import mongodb.onetomany.repository.CompanyRepository;
import mongodb.onetomany.service.SequenceGeneratorService;

@RestController()
@RequestMapping("/rest")
public class CompnayRestController {

	@Autowired
	private CompanyRepository repo;
	
	@Autowired
	private SequenceGeneratorService seqService;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Company>> findAll()
	{
		
		return new ResponseEntity<List<Company>>(repo.findAll(),HttpStatus.OK);
	}
	@PostMapping("/save")
	public ResponseEntity<Company> save(@RequestBody Company company)
	{
		company.setId(seqService.generateSequence(Company.SEQUENCE_NAME));
		repo.save(company);
		return new ResponseEntity<Company>(company,HttpStatus.OK);
	}
	@PostMapping("/update")
	public ResponseEntity<Company> update(@RequestBody Company company)
	{
		Optional<Company> companyRecord = repo.findById(company.getId());
		return new ResponseEntity<Company>(companyRecord.get(),HttpStatus.OK);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<Company> delete(@RequestBody Company company)
	{
		repo.deleteAll();
		return new ResponseEntity<Company>(company,HttpStatus.OK);
	}
}
