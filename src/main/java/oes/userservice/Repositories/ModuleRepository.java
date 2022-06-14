package oes.userservice.Repositories;

import oes.userservice.Entities.Module;
import org.springframework.boot.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module, String> {
}