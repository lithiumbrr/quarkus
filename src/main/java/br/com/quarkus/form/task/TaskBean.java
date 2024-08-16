package br.com.quarkus.form.task;

import br.com.quarkus.form.entity.Event;
import br.com.quarkus.form.repository.EventRepository;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class TaskBean {

    @Inject
    EventRepository eventRepository;

    @Transactional
    @Scheduled(every = "360s", identity = "task-job")
    void activeStatus() {
        List<Event> events = eventRepository.listAll();
        if (!events.isEmpty()) {
            events.forEach(elem -> {
                if (elem.getInitialDate().equals(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))) {
                    elem.setActive(true);
                    eventRepository.persist(elem);
                }
            });
        }
    }
}
