package com.github.exbotanical.resource.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.github.exbotanical.resource.entities.Resource;
import com.github.exbotanical.resource.models.ResourceModel;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * A repository for managing Resource data via DynamoDB.
 */
@Repository
public class ResourceRepository {

  @Autowired
  private DynamoDBMapper dynamoMapper;

  /**
   * Persist a given Resource.
   *
   * @param newResource The new Resource to persist.
   * @return The new Resource, with updated Dynamo-generated fields.
   */
  public Resource save(Resource newResource) {
    dynamoMapper.save(newResource);

    return newResource;
  }

  /**
   * Retrieve a Resource by its id.
   *
   * @param id A unique Resource id identifying the Resource to retrieve.
   * @return A Resource, or null if not found.
   */
  public Resource getById(String id) {
    return dynamoMapper.load(Resource.class, id);
  }

  /**
   * Retrieve all Resources.
   *
   *
   * @return List of Resources.
   * @todo Paginate
   */
  public ArrayList<Resource> getAll() {
    PaginatedScanList<Resource> ret =
        dynamoMapper.scan(Resource.class, new DynamoDBScanExpression());

    ArrayList<Resource> list = new ArrayList();
    list.addAll(ret);

    return list;
  }

  /**
   * Delete a Resource by its id.
   *
   * @param id A unique Resource id identifying the Resource to delete.
   */
  public void deleteById(String id) {
    Resource resource = dynamoMapper.load(Resource.class, id);

    dynamoMapper.delete(resource);
  }

  /**
   * Update a Resource by its id.
   *
   * @param id A unique Resource id identifying the Resource to update.
   * @param resourceModel A ResourceModel containing the data to patch into the id-resolved
   *        Resource.
   */
  public void updateById(String id, ResourceModel resourceModel) {
    Resource updatedResource = Resource.builder()
        .id(id)
        .title(resourceModel.getTitle())
        .tags(resourceModel.getTags())
        .build();

    dynamoMapper.save(
        updatedResource,
        new DynamoDBSaveExpression().withExpectedEntry(
            "Id",
            new ExpectedAttributeValue(
                new AttributeValue().withS(id))));
  }
}
