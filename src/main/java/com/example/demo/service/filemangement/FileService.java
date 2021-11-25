package com.example.demo.service.filemangement;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.demo.constants.Constants;
import com.example.demo.exception.BadRequestException;
import com.example.demo.model.filemanagement.FileModel;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoCommandException;
import com.mongodb.MongoException;
import com.mongodb.MongoQueryException;

@Service
public class FileService {
	private MongoTemplate mongoTemplate;

	@Autowired
	private MongoOperations mongoOperations;

	@Autowired
	public FileService(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;

	}

	public FileModel addFile(FileModel filemodel) {

		if (filemodel.getFilesubdocument().isEmpty()) {
			throw new BadRequestException("Could not read any file");
		} else {

			Update update = new Update().addToSet(Constants.FILE_SUB_DOCUMENT, filemodel.getFilesubdocument().get(0));

			Query q = new Query();
			q.addCriteria(Criteria.where(Constants.DEFECT_ID).is(filemodel.getDefect_id()));

			return mongoOperations.findAndModify(q, update, options().returnNew(true).upsert(true), FileModel.class);

		}
	}

	public FileModel updateFileByIdAndAssetId(FileModel filemodel, String defect_id, String asset_id) {
		try {
			if (filemodel.getFilesubdocument().isEmpty()) {
				throw new BadRequestException("Could not read any file");
			} else {

				Query query = new Query();
				query.addCriteria(Criteria.where(Constants.DEFECT_ID).is(defect_id).and("filesubdocument")
						.elemMatch(Criteria.where(Constants.ASSET_ID).is(asset_id)));

				Update update = new Update().set("filesubdocument.$", filemodel.getFilesubdocument().get(0));

				return mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true),
						FileModel.class);
			}
		} catch (MongoCommandException e) {
			throw new MongoQueryException(e.getServerAddress(), e.getErrorCode(), e.getErrorMessage());

		}

	}

	public List<FileModel> getAllFiles() {

		return mongoTemplate.findAll(FileModel.class);
	}

	public FileModel getFileById(String defect_id) {

		Query q = new Query();
		q.addCriteria(Criteria.where(Constants.DEFECT_ID).is(defect_id));

		return mongoTemplate.findOne(q, FileModel.class);
	}

	public FileModel getFileByAssetId(String defect_id, String asset_id) {
		Query query = new Query();
		query.addCriteria(Criteria.where(Constants.DEFECT_ID).is(defect_id));
		query.addCriteria(
				Criteria.where(Constants.FILE_SUB_DOCUMENT).elemMatch(Criteria.where(Constants.ASSET_ID).is(asset_id)));

		return mongoTemplate.findOne(query, FileModel.class);
	}

	public String deleteAllFiles(String defect_id) {

		Query q = new Query();
		q.addCriteria(Criteria.where(Constants.DEFECT_ID).is(defect_id));
		FileModel deletedEntity = mongoTemplate.findAndRemove(q, FileModel.class);

		return (deletedEntity != null) ? "FILE WITH" + deletedEntity.getDefect_id() + " deleted from the database"
				: "File not found";

	}

	public String deleteFileByAssetId(String defect_id, String asset_id) {

		Query query = new Query();
		query.addCriteria(Criteria.where(Constants.DEFECT_ID).is(defect_id));

		Update update = new Update().pull(Constants.FILE_SUB_DOCUMENT, new BasicDBObject(Constants.ASSET_ID, asset_id));

		mongoOperations.upsert(query, update, FileModel.class);
		return "FILE WITH defect_id" + defect_id + " is Removed";
	}

}
