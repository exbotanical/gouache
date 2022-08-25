package com.github.exbotanical.resource.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDBTable(tableName = "resource")
public class Resource {

  @DynamoDBHashKey(attributeName = "Id")
  @DynamoDBAutoGeneratedKey
  private String id;

  // @todo composite key
  @NotEmpty(message = "title is required")
  @Length(max = 128, message = "length must be less than or equal to 128 characters")
  @DynamoDBAttribute(attributeName = "Title")
  private String title;

  @DynamoDBAttribute(attributeName = "Tags")
  private List<String> tags;

  @DynamoDBAutoGeneratedTimestamp(strategy = DynamoDBAutoGenerateStrategy.CREATE)
  @DynamoDBAttribute(attributeName = "CreatedAt")
  private String createdAt;

  @DynamoDBAutoGeneratedTimestamp(strategy = DynamoDBAutoGenerateStrategy.ALWAYS)
  @DynamoDBAttribute(attributeName = "UpdatedAt")
  private String updatedAt;
}