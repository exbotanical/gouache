package com.github.exbotanical.resource.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.exbotanical.resource.entities.Resource;
import com.github.exbotanical.resource.models.ResourceModel;
import com.github.exbotanical.resource.repositories.ResourceRepository;

@Service
public class ResourceDataService implements ResourceService {

  @Autowired
  private ResourceRepository resourceRepository;

  @Override
  public Resource getResourceById(String id) {
    return resourceRepository.getById(id);
  }

  @Override
  public Resource createResource(ResourceModel resourceModel) {

    Resource newResource = Resource.builder()
        .title(resourceModel.getTitle())
        .tags(resourceModel.getTags())
        .build();

    return resourceRepository.save(newResource);
  }

  @Override
  public void deleteResourceById(String id) {
    resourceRepository.deleteById(id);
  }

  @Override
  public void updateResourceById(String id, ResourceModel resourceModel) {
    resourceRepository.updateById(id, resourceModel);
  }
}
