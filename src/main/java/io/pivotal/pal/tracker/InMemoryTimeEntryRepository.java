package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> map = new HashMap<Long, TimeEntry>();

    private long counter = 1l;
    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry timeEntry1 = new TimeEntry(getAutoID(),
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours());

        map.put(timeEntry1.getId(),timeEntry1);
        return timeEntry1;
    }

    private long getAutoID() {
        return counter++;
    }


    @Override
    public TimeEntry find(long id) {
       return map.get(id);
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
      TimeEntry entry = map.get(id);
      entry = new TimeEntry(id,timeEntry.getProjectId(),timeEntry.getUserId(),timeEntry.getDate(),timeEntry.getHours());
      map.put(id, entry);
      return entry;
    }

    @Override
    public void delete(long id) {
        map.remove(id);

    }

    @Override
    public List<TimeEntry> list() {
       return map.values().stream().collect(Collectors.toList());
    }
}
