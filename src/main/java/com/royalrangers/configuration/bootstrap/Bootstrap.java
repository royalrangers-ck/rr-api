package com.royalrangers.configuration.bootstrap;

import com.royalrangers.dto.achievement.AchievementRequestDto;
import com.royalrangers.dto.achievement.TaskRequestDto;
import com.royalrangers.dto.achievement.TestRequestDto;
import com.royalrangers.dto.achievement.ThreeYearRequestDto;
import com.royalrangers.enums.AuthorityName;
import com.royalrangers.model.*;
import com.royalrangers.repository.AuthorityRepository;
import com.royalrangers.repository.CountryRepository;
import com.royalrangers.repository.UserRepository;
import com.royalrangers.repository.achievement.RewardRepository;
import com.royalrangers.repository.achievement.TestRepository;
import com.royalrangers.service.achievement.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
@Component
public class Bootstrap {
    private final String DDL_AUTO_CREATE = "create";
    private final String DDL_AUTO_CREATE_DROP = "create-drop";
    private final String UKRAINE_CITIES = "src/main/resources/init/ukraine.cities";

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private TwelveYearAchievementService twelveYearAchievementService;

    @Autowired
    private ThreeYearAchievementService threeYearAchievementService;

    @Autowired
    private YearAchievementService yearAchievementService;

    @Autowired
    private QuarterAchievementService quarterAchievementService;

    @Autowired
    private TestRepository testRepository;

    public AchievementBootstrap achievementBootstrap = new AchievementBootstrap();

    TestBootstrap testBootstrap = new TestBootstrap();

    @Autowired
    private TestService testService;

    @Autowired
    private TaskService taskService;

    TaskBootstrap taskBootstrap = new TaskBootstrap();


    @PostConstruct
    public void init() {
        if (DDL_AUTO_CREATE.equals(ddlAuto) || DDL_AUTO_CREATE_DROP.equals(ddlAuto)) {
            initAuthorities();
            initUsers();
            try {
                initCountry("Україна", UKRAINE_CITIES);
            } catch (IOException e) {
                log.error("Error in loading file " + e.getMessage());
            }
            initTwelveYear();
            initReward();
        }
    }

    private void initUsers() {
        List<User> users = new ArrayList<>();
        IntStream.range(1, 4).forEach(element -> {
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            User user = new User();
            user.setEmail("email" + element + "@mail.test");
            user.setPassword(encoder.encode("password" + element));
            user.setFirstName("first " + element);
            user.setLastName("last " + element);
            user.setGender("gender " + element);
            user.setEnabled(true);
            user.setConfirmed(true);
            user.setApproved(true);
            user.setCountry(new Country("Ukraine" + element));
            user.setCity(new City(user.getCountry(), "Cherkasy" + element));
            user.setGroup(new Group(user.getCity(), "group " + element));
            user.setPlatoon(new Platoon(user.getGroup(), "platoon " + element));
            user.setSection(new Section(user.getPlatoon(), "section " + element));
            user.setLastPasswordResetDate(new Date(new GregorianCalendar(2016,
                    Calendar.FEBRUARY, 9).getTimeInMillis()));
            Authority userAuthority = authorityRepository.findOne(1L);
            Authority adminAuthority = authorityRepository.findOne(2L);
            Authority superAdminAuthority = authorityRepository.findOne(3L);
            switch (element) {
                case 1:
                    user.setAuthorities(new HashSet<Authority>() {{
                        add(userAuthority);
                    }});
                    break;
                case 2:
                    user.setAuthorities(new HashSet<Authority>() {{
                        add(userAuthority);
                        add(adminAuthority);
                    }});
                    break;
                case 3:
                    user.setAuthorities(new HashSet<Authority>() {{
                        add(userAuthority);
                        add(adminAuthority);
                        add(superAdminAuthority);
                    }});
                    break;
            }
            users.add(user);
        });
        userRepository.save(users);
    }

    private void initAuthorities() {
        Authority userAuthority = new Authority();
        userAuthority.setName(AuthorityName.ROLE_USER);
        authorityRepository.save(userAuthority);

        Authority adminAuthority = new Authority();
        adminAuthority.setName(AuthorityName.ROLE_ADMIN);
        authorityRepository.save(adminAuthority);

        Authority superAdminAuthority = new Authority();
        superAdminAuthority.setName(AuthorityName.ROLE_SUPER_ADMIN);
        authorityRepository.save(superAdminAuthority);
        authorityRepository.save(superAdminAuthority);
    }

    private void initCountry(String countryName, String path) throws IOException {
        Country country = new Country(countryName);
        Set<City> citySet = new HashSet<>();
        Files.lines(Paths.get(path), StandardCharsets.UTF_8)
                .forEach(element -> citySet.add(new City(country, element)));
        country.setCity(citySet);
        countryRepository.save(country);

    }

    private void initReward() {
        Stream.of(achievementBootstrap.createReward()).forEach(element -> {
            rewardRepository.save(element);
            rewardRepository.flush();
        });
    }

    ;

    private void initTwelveYear() {
        twelveYearAchievementService.addTwelveYearAchievement(achievementBootstrap.createTwelveYear());
        initThreeYear();
    }

    private void initThreeYear() {
        for (ThreeYearRequestDto element : achievementBootstrap.createThreeYear()) {
            threeYearAchievementService.addThreeYearAchievement(element);
        }
        initYear();
    }

    private void initYear() {
        for (AchievementRequestDto element : achievementBootstrap.createYear()) {
            yearAchievementService.addYearAchievement(element);
        }
        initQuarter();
    }

    private void initQuarter() {
        for (AchievementRequestDto test : achievementBootstrap.createQuarter()) {
            quarterAchievementService.addQuarterAchievement(test);
        }
        initTest();
    }

    public void initTest() {
        for (TestRequestDto test : testBootstrap.createTest()) {
            testService.addTest(test);
        }
        initTask();
    }

    public void initTask() {
        for (TaskRequestDto element : taskBootstrap.createTask()) {
            taskService.addTask(element);
        }
    }

    ;

}
