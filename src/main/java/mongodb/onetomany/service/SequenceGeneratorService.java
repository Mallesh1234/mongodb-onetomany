package mongodb.onetomany.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import mongodb.onetomany.document.DatabaseSequence;

@Service
public class SequenceGeneratorService {

	
	private MongoOperations mongoOperations;

	@Autowired
	public SequenceGeneratorService(MongoOperations mongoOperations) {
		super();
		this.mongoOperations = mongoOperations;
	}
	
	public Integer generateSequence(String seqName)
	{
		Query query = new Query();
		
		query.addCriteria(Criteria.where("_id").is(seqName));
		
		DatabaseSequence sequence = mongoOperations.findAndModify(query, new Update().inc("seq", 1),
				FindAndModifyOptions.options().upsert(true).returnNew(true),
				DatabaseSequence.class);
		
		return !Objects.isNull(sequence) ? sequence.getSeq() : 1;
	}
}
