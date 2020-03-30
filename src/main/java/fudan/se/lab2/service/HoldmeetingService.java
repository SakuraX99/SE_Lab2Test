package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.HoldmeetingRequest;
import fudan.se.lab2.domain.Meeting;
import fudan.se.lab2.domain.User;
import fudan.se.lab2.exception.MeetingShortNameHasbeenregisteredException;
import fudan.se.lab2.repository.AuthorityRepository;
import fudan.se.lab2.repository.MeetingRepository;
import fudan.se.lab2.repository.UserRepository;
import fudan.se.lab2.security.jwt.JwtRequestFilter;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class HoldmeetingService {
    private HoldmeetingRequest request;
    private JwtRequestFilter jwtRequestFilter;
    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;
    private MeetingRepository meetingRepository;
//    private AtomicLong counter;

    public HoldmeetingService(HoldmeetingRequest req, UserRepository userRepository, AuthorityRepository authorityRepository, MeetingRepository meetingRepo) {
        this.request = req;
        this.jwtRequestFilter = jwtRequestFilter;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.meetingRepository = meetingRepo;
//        this.counter = counterforau;
    }

    public Meeting holdMeeting(){
        if(meetingRepository.findByShortName(request.getShortName())==null){
        String aprv = "Unprocessed";
        Meeting meeting = new Meeting(request.getShortName(), request.getFullName(), request.getDate(), request.getLocation(), request.getDeadline(), request.getDateForResults(), aprv, new HashSet<User>());
        meetingRepository.save(meeting);
        return meeting;}
        else{
            throw new MeetingShortNameHasbeenregisteredException(request.getShortName());
        }
    }
}
