package fudan.se.lab2.repository;

import fudan.se.lab2.domain.Meeting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ZTY
 */
@Repository
public interface MeetingRepository extends CrudRepository<Meeting, Long> {
    Meeting findByShortName(String shortName);
}
