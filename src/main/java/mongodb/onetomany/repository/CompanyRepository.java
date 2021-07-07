package mongodb.onetomany.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mongodb.onetomany.document.Company;
@Repository
public interface CompanyRepository extends MongoRepository<Company, Integer> {

}
