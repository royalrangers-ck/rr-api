package com.royalrangers.configuration.bootstrap;

import com.royalrangers.enums.AuthorityName;
import com.royalrangers.enums.UserAgeGroup;
import com.royalrangers.model.*;
import com.royalrangers.model.achievement.*;
import com.royalrangers.repository.AuthorityRepository;
import com.royalrangers.repository.CountryRepository;
import com.royalrangers.repository.UserRepository;
import com.royalrangers.repository.achievement.*;
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
    private TwelveYearAchievementService twelveYearAchievementService;

    @Autowired
    private ThreeYearAchievementRepository threeYearAchievementRepository;

    @Autowired
    private YearAchievementRepository yearAchievementRepository;

    @Autowired
    private QuarterAchievementRepository quarterAchievementRepository;

    public YearAchievementBootstrap yearAchievementBootstrap = new YearAchievementBootstrap();
    public QuarterAchievementBootstrap quarterAchievementBootstrap = new QuarterAchievementBootstrap();
    public AchievementBootstrap achievementBootstrap = new AchievementBootstrap();

    TestBootstrap testBootstrap = new TestBootstrap();

    @Autowired
    private TestService testService;

    @Autowired
    private TestRepository testRepository;

    TaskBootstrap taskBootstrap = new TaskBootstrap();

    @Autowired
    private RewardService rewardService;

    RewardBootstrap rewardBootstrap = new RewardBootstrap();

    @Autowired
    private TaskRepository taskRepository;


    @PostConstruct
    public void init() {
        if (DDL_AUTO_CREATE.equals(ddlAuto) || DDL_AUTO_CREATE_DROP.equals(ddlAuto)) {
            initAuthorities();
            initUsers();
            try {
                initCountry("Україна", UKRAINE_CITIES);
            } catch (IOException e) {
                log.error("Error in loading file " + e.getMessage(), e);
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
        Stream.of(rewardBootstrap.createReward().toArray()).forEach(element -> {
            rewardService.addReward((Reward) element);
        });
    }

    private void initTwelveYear() {
        twelveYearAchievementService.addTwelveYearAchievement(achievementBootstrap.createTwelveYear());
        initThreeYear();
    }

    private void initThreeYear() {
        Stream.of(twelveYearAchievementService.getAllTwelveYearAchievement().toArray()).forEach(twelveYearId -> {
            TwelveYearAchievement twelveYearAchievement = (TwelveYearAchievement) twelveYearId;
            Stream.of(achievementBootstrap.createThreeYear().toArray()).forEach(element -> {
                ThreeYearAchievement threeYearAchievement = (ThreeYearAchievement) element;
                threeYearAchievement.setTwelveYearAchievement(twelveYearAchievement);
                threeYearAchievementRepository.saveAndFlush(threeYearAchievement);
            });
        });
        initYear();
    }

    private void initYear() {
        Stream.of(yearAchievementBootstrap.createYear().toArray()).forEach(yearAchievements -> {
            YearAchievement yearAchievement = (YearAchievement) yearAchievements;
            yearAchievementRepository.saveAndFlush(yearAchievement);
        });
        Stream.of(threeYearAchievementRepository.findAll().toArray()).forEach(threeYearAchievements -> {
            ThreeYearAchievement threeYearAchievement = (ThreeYearAchievement) threeYearAchievements;
            List<YearAchievement> yearAchievementList = yearAchievementRepository.findByUserAgeGroup(threeYearAchievement.getUserAgeGroup());
            Stream.of(yearAchievementList.toArray()).forEach(year -> {
                YearAchievement yearAchievement = (YearAchievement) year;
                yearAchievement.setThreeYearAchievement(threeYearAchievement);
                yearAchievementRepository.saveAndFlush(yearAchievement);
            });
        });
        initQuarter();
    }

    private void initQuarter() {
        Stream.of(quarterAchievementBootstrap.createQuarter().toArray()).forEach(quarter -> {
            QuarterAchievement quarterAchievement = (QuarterAchievement) quarter;
            quarterAchievementRepository.saveAndFlush(quarterAchievement);
        });
        Stream.of(UserAgeGroup.values()).forEach(ageGroup -> {
            List<YearAchievement> yearAchievementList = yearAchievementRepository.findByUserAgeGroup(ageGroup);
            IntStream.range(1, 4).forEach(index -> {
                List<QuarterAchievement> quarterAchievementList = quarterAchievementRepository.findByUserAgeGroup(ageGroup);
                if (yearAchievementList.size() != 0 && quarterAchievementList.size() != 0) {
                    YearAchievement yearAchievement = yearAchievementList.get(index - 1);
                    List<QuarterAchievement> subList = null;
                    switch (index) {
                        case 1: {
                            subList = quarterAchievementList.subList(0, 4);
                            break;
                        }
                        case 2: {
                            subList = quarterAchievementList.subList(4, 8);
                            break;
                        }
                        case 3: {
                            subList = quarterAchievementList.subList(8, 12);
                            break;
                        }
                    }
                    Stream.of(subList.toArray()).forEach(quarterAchievement -> {
                        QuarterAchievement savedQuarterAchievement = (QuarterAchievement) quarterAchievement;
                        savedQuarterAchievement.setYearAchievement(yearAchievement);
                        quarterAchievementRepository.saveAndFlush(savedQuarterAchievement);
                    });
                }
            });
        });
        initTest();
    }

    public void initTest() {
        Stream.of(testBootstrap.createTest().toArray()).forEach(test -> {
            Test savedTest = (Test) test;
            testRepository.saveAndFlush(savedTest);
        });
        initTask();
    }

    public void initTask() {
        IntStream.range(1, testService.getAllTest().size()).forEach(testId -> {
            Test test = testService.getTestById((long) testId);
            Map<Integer, Object> map = taskBootstrap.createTask();
            List<Task> list = (List<Task>) map.get(testId);
            while (testId <= map.size()) {
                if (list.size() != 0) {
                    Stream.of(list.toArray()).forEach(item -> {
                        Task task = (Task) item;
                        task.setTest(test);
                        taskRepository.saveAndFlush(task);
                    });
                    return;
                }
            }
        });
    }

}
