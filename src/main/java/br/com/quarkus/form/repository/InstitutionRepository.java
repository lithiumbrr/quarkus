package br.com.quarkus.form.repository;

import br.com.quarkus.form.entity.Institution;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InstitutionRepository implements PanacheRepository<Institution> {
}
