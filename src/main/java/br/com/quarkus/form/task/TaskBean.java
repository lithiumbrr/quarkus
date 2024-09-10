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
    @Scheduled(cron = "0 0 * * * ?", identity = "task-job")
    void activeStatus() {
        List<Event> events = eventRepository.listAll();
        if (!events.isEmpty()) {
            events.forEach(elem -> {
                if (!elem.isActive() && elem.getInitialDate().equals(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))) {
                    elem.setActive(true);
                    eventRepository.persist(elem);
                }
                if (elem.isActive() && !elem.getFinalDate().equals(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))) {
                    elem.setActive(false);
                    eventRepository.persist(elem);
                }
            });
        }
    }
}
