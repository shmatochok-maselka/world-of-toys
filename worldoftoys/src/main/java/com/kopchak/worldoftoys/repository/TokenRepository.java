package com.kopchak.worldoftoys.repository;

import java.util.List;
import java.util.Optional;

import com.kopchak.worldoftoys.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {
    @Query(value = """
      select t from AuthenticationToken t inner join User u\s
      on t.user.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<AuthenticationToken> findAllValidTokenByUser(Integer id);

    Optional<AuthenticationToken> findByToken(String authToken);
}
