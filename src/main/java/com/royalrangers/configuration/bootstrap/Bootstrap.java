package com.royalrangers.configuration.bootstrap;

import com.esotericsoftware.yamlbeans.YamlReader;
import com.royalrangers.dto.user.UserRegistrationDto;
import com.royalrangers.enums.AuthorityName;
import com.royalrangers.enums.UserAgeGroup;
import com.royalrangers.model.Authority;
import com.royalrangers.model.Country;
import com.royalrangers.model.User;
import com.royalrangers.model.achievement.*;
import com.royalrangers.repository.AuthorityRepository;
import com.royalrangers.repository.CountryRepository;
import com.royalrangers.repository.UserRepository;
import com.royalrangers.repository.achievement.*;
import com.royalrangers.service.UserService;
import com.royalrangers.service.achievement.RewardService;
import com.royalrangers.service.achievement.TestService;
import com.royalrangers.service.achievement.TwelveYearAchievementService;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.royalrangers.enums.AuthorityName.ROLE_USER;

@Slf4j
@Component
public class Bootstrap {
    private final String COUNTRY_FILE = "init/ukraine.yml";
    private final String USERS_FILE = "init/initial_users.yml";

    @Value("${spring.datasource.url}")
    String db_url;

    @Value("${spring.datasource.username}")
    String db_username;

    @Value("${spring.datasource.password}")
    String db_password;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

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

    private YearAchievementBootstrap yearAchievementBootstrap = new YearAchievementBootstrap();
    private QuarterAchievementBootstrap quarterAchievementBootstrap = new QuarterAchievementBootstrap();
    private AchievementBootstrap achievementBootstrap = new AchievementBootstrap();

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

        Flyway flyway = new Flyway();
        flyway.setDataSource(db_url, db_username, db_password);
        flyway.migrate();


        if (countryRepository.count() == 0) {
            initCountry();
        }

        if (authorityRepository.count() == 0) {
            initAuthorities();
        }

        if (userRepository.count() == 0) {
            initUsers();
            initTwelveYear();
            initReward();
        }
    }

    private void initUsers() {
        List<User> users = new ArrayList<>();

        try {
            YamlReader reader = getYamlReader(USERS_FILE);
            while (true) {
                UserRegistrationDto userDto = reader.read(UserRegistrationDto.class);
                if (userDto == null) break;
                User user = userService.createUser(userDto);
                user.setEnabled(true);
                user.setConfirmed(true);
                user.setApproved(true);
                user.setLastPasswordResetDate(new Date(new GregorianCalendar(
                        2017, Calendar.FEBRUARY, 9)
                        .getTimeInMillis()));
                users.add(user);
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        userRepository.save(users);
    }

    private void initCountry() {
        try {
            YamlReader reader = getYamlReader(COUNTRY_FILE);
            Country country = reader.read(Country.class);
            countryRepository.save(country);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Used for read file with correct encoding
    private YamlReader getYamlReader(String file) throws IOException {
        Resource resource = new ClassPathResource(file);
        InputStreamReader isr = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
        return new YamlReader(isr);
    }

    private void initAuthorities() {
        Authority userAuthority = new Authority();
        userAuthority.setName(ROLE_USER);
        authorityRepository.save(userAuthority);

        Authority adminAuthority = new Authority();
        adminAuthority.setName(AuthorityName.ROLE_ADMIN);
        authorityRepository.save(adminAuthority);

        Authority superAdminAuthority = new Authority();
        superAdminAuthority.setName(AuthorityName.ROLE_SUPER_ADMIN);
        authorityRepository.save(superAdminAuthority);
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
            yearAchievementRepository.saveAndFlush((YearAchievement) yearAchievements);
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
            quarterAchievementRepository.saveAndFlush((QuarterAchievement) quarter);
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

    private void initTest() {
        Stream.of(testBootstrap.createTest().toArray()).forEach(test -> {
            testRepository.saveAndFlush((Test) test);
        });
        initTask();
    }

    private void initTask() {
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
