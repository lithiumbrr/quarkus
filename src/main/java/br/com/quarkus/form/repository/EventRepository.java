package br.com.quarkus.form.repository;

import br.com.quarkus.form.entity.Event;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EventRepository implements PanacheRepository<Event> {
}
