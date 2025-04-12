// LoginLogRepository.java
package com.inscription.login.repositories;

import com.inscription.login.models.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginLogRepository extends JpaRepository<LoginLog, Long> {}
