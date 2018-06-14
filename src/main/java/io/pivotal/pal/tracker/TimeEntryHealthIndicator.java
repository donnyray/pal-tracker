package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class TimeEntryHealthIndicator implements HealthIndicator {

    public static final int MAX_ROWS_IN_DB = 5;
    @Autowired
    private JdbcTimeEntryRepository jdbcTimeEntryRepository;

    @Override
    public Health health() {
        Health.Builder builder = new Health.Builder();
        // call list
        if(jdbcTimeEntryRepository.list().size() < MAX_ROWS_IN_DB) {
            builder.up();
        } else {
            builder.down();
        }
        return builder.build();
    }
}
