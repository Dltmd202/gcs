package com.gcs.monolith.domain.context.configuration.repository;

import com.gcs.supporter.domain.context.entity.AgentContextEntity;
import com.gcs.supporter.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgentContextRepository extends JpaRepository<AgentContextEntity, Long> {
    List<AgentContextEntity> getAgentContextEntitiesByUser(UserEntity user);

    Optional<AgentContextEntity> getAgentContextEntitiesByIdAndUser(Long id, UserEntity user);
}
