package com.github.exbotanical.resource.repositories;

import com.amazonaws.util.json.Jackson;
import com.github.exbotanical.resource.entities.Session;
import javax.annotation.Resource;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * A repository for managing readonly Session data via Redis.
 */
@Repository
public class SessionRepository {

  @Resource(name = "redisTemplate")
  private ValueOperations<String, String> valueOperations;

  /**
   * Retrieve a Session by its session id.
   *
   * @param sid The session id.
   *
   * @return The deserialized Session, or null if not extant.
   */
  public Session getById(String sid) {
    String jsonSession = valueOperations.get(sid);

    return Jackson.fromJsonString(jsonSession, Session.class);
  }
}
