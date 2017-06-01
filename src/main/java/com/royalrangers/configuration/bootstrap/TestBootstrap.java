package com.royalrangers.configuration.bootstrap;

import com.royalrangers.enums.UserAgeGroup;
import com.royalrangers.enums.achivement.TestType;
import com.royalrangers.model.achievement.Test;

import java.util.*;
import java.util.stream.IntStream;

public class TestBootstrap {

    private void createGreenTest(List<Test> tests) {
        IntStream.range(1, 61).forEach(index -> {
            Test greenTest = new Test();
            greenTest.setTestType(TestType.GREEN);
            greenTest.setUserAgeGroups(new ArrayList<>(Arrays.asList(UserAgeGroup.PATHFINDER, UserAgeGroup.PIONEER)));
            switch (index) {
                case 1: {
                    greenTest.setName("Academics");
                    break;
                }
                case 2: {
                    greenTest.setName("Advanced Disability Awareness");
                    break;
                }
                case 3: {
                    greenTest.setName("Advanced Marksmanship");
                    break;
                }
                case 4: {
                    greenTest.setName("Advanced Swimming");
                    break;
                }
                case 5: {
                    greenTest.setName("Air Rifle");
                    break;
                }
                case 6: {
                    greenTest.setName("American History");
                    break;
                }
                case 7: {
                    greenTest.setName("Aviation");
                    break;
                }
                case 8: {
                    greenTest.setName("Bachelor");
                    break;
                }
                case 9: {
                    greenTest.setName("Backpacking");
                    break;
                }
                case 10: {
                    greenTest.setName("Baseball");
                    break;
                }
                case 11: {
                    greenTest.setName("Basketball");
                    break;
                }
                case 12: {
                    greenTest.setName("BB Gun");
                    break;
                }
                case 13: {
                    greenTest.setName("Bible Quiz");
                    break;
                }
                case 14: {
                    greenTest.setName("Bible Reading");
                    break;
                }
                case 15: {
                    greenTest.setName("Camp Safety");
                    break;
                }
                case 16: {
                    greenTest.setName("Canoeing");
                    break;
                }
                case 17: {
                    greenTest.setName("Carpentry");
                    break;
                }
                case 18: {
                    greenTest.setName("Christian Missions");
                    break;
                }
                case 19: {
                    greenTest.setName("Church History");
                    break;
                }
                case 20: {
                    greenTest.setName("Communications");
                    break;
                }
                case 21: {
                    greenTest.setName("Computers");
                    break;
                }
                case 22: {
                    greenTest.setName("Convoy of Hope");
                    break;
                }
                case 23: {
                    greenTest.setName("Crime Prevention");
                    break;
                }
                case 24: {
                    greenTest.setName("Cycling");
                    break;
                }
                case 25: {
                    greenTest.setName("Emergency Preparedness");
                    break;
                }
                case 26: {
                    greenTest.setName("Energy");
                    break;
                }
                case 27: {
                    greenTest.setName("Environmental Science");
                    break;
                }
                case 28: {
                    greenTest.setName("Family History");
                    break;
                }
                case 29: {
                    greenTest.setName("First Aid–CPR");
                    break;
                }
                case 30: {
                    greenTest.setName("Football");
                    break;
                }
                case 31: {
                    greenTest.setName("Forestry");
                    break;
                }
                case 32: {
                    greenTest.setName("Gardening");
                    break;
                }
                case 33: {
                    greenTest.setName("Global Missions");
                    break;
                }
                case 34: {
                    greenTest.setName("Healthy Body");
                    break;
                }
                case 35: {
                    greenTest.setName("Hiking");
                    break;
                }
                case 36: {
                    greenTest.setName("Home Repair");
                    break;
                }
                case 37: {
                    greenTest.setName("Home Safety");
                    break;
                }
                case 38: {
                    greenTest.setName("Horsemanship");
                    break;
                }
                case 39: {
                    greenTest.setName("Indian Lore");
                    break;
                }
                case 40: {
                    greenTest.setName("International Service");
                    break;
                }
                case 41: {
                    greenTest.setName("Leather Craft");
                    break;
                }
                case 42: {
                    greenTest.setName("Nature Study");
                    break;
                }
                case 43: {
                    greenTest.setName("Orienteering");
                    break;
                }
                case 44: {
                    greenTest.setName("Photography");
                    break;
                }
                case 45: {
                    greenTest.setName("Pioneering");
                    break;
                }
                case 46: {
                    greenTest.setName("Plant Science");
                    break;
                }
                case 47: {
                    greenTest.setName("Pottery");
                    break;
                }
                case 48: {
                    greenTest.setName("Public Speaking");
                    break;
                }
                case 49: {
                    greenTest.setName("Puppeteer");
                    break;
                }
                case 50: {
                    greenTest.setName("Reptile Study");
                    break;
                }
                case 51: {
                    greenTest.setName("Salesmanship");
                    break;
                }
                case 52: {
                    greenTest.setName("Skateboarding");
                    break;
                }
                case 53: {
                    greenTest.setName("Soccer");
                    break;
                }
                case 54: {
                    greenTest.setName("Sports");
                    break;
                }
                case 55: {
                    greenTest.setName("Stamp Collecting");
                    break;
                }
                case 56: {
                    greenTest.setName("Swimming");
                    break;
                }
                case 57: {
                    greenTest.setName("Tennis");
                    break;
                }
                case 58: {
                    greenTest.setName("Truck Transportation");
                    break;
                }
                case 59: {
                    greenTest.setName("Wood Carving");
                    break;
                }
                case 60: {
                    greenTest.setName("Wrestling");
                    break;
                }
            }
            tests.add(greenTest);
        });
    }

    private void createOrangeTest(List<Test> tests) {
        IntStream.range(1, 16).forEach(index -> {
            Test orangeTest = new Test();
            orangeTest.setTestType(TestType.ORANGE);
            orangeTest.setUserAgeGroups(new ArrayList<>(Arrays.asList(UserAgeGroup.PIONEER)));
            orangeTest.setName("Оранжевий біблійний тест 0" + index);
            tests.add(orangeTest);
        });
    }

    private void createBrownTest(List<Test> tests) {
        IntStream.range(1, 16).forEach(index -> {
            Test brownTest = new Test();
            brownTest.setTestType(TestType.BROWN);
            brownTest.setUserAgeGroups(new ArrayList<>(Arrays.asList(UserAgeGroup.PATHFINDER)));
            brownTest.setName("Коричневий біблійний тест 0" + index);
            tests.add(brownTest);
        });
    }

    private void createPlatinumTest(List<Test> tests) {
        IntStream.range(1, 61).forEach(index -> {
            Test platinumTest = new Test();
            platinumTest.setTestType(TestType.PLATINUM);
            platinumTest.setUserAgeGroups(new ArrayList<>(Arrays.asList(UserAgeGroup.PATHFINDER, UserAgeGroup.RANGER)));
            switch (index) {
                case 1: {
                    platinumTest.setName("Advanced Backpacking");
                    break;
                }
                case 2: {
                    platinumTest.setName("Air Rifle");
                    break;
                }
                case 3: {
                    platinumTest.setName("American Cultures");
                    break;
                }
                case 4: {
                    platinumTest.setName("Animal Husbandry");
                    break;
                }
                case 5: {
                    platinumTest.setName("Architecture");
                    break;
                }
                case 6: {
                    platinumTest.setName("Atomic Energy");
                    break;
                }
                case 7: {
                    platinumTest.setName("Auto Mechanics");
                    break;
                }
                case 8: {
                    platinumTest.setName("Bible Reading");
                    break;
                }
                case 9: {
                    platinumTest.setName("Boating");
                    break;
                }
                case 10: {
                    platinumTest.setName("Chemistry");
                    break;
                }
                case 11: {
                    platinumTest.setName("Cinematography");
                    break;
                }
                case 12: {
                    platinumTest.setName("Citizenship");
                    break;
                }
                case 13: {
                    platinumTest.setName("Dentistry");
                    break;
                }
                case 14: {
                    platinumTest.setName("Drafting");
                    break;
                }
                case 15: {
                    platinumTest.setName("Economics");
                    break;
                }
                case 16: {
                    platinumTest.setName("Electricity");
                    break;
                }
                case 17: {
                    platinumTest.setName("Electronics");
                    break;
                }
                case 18: {
                    platinumTest.setName("Engineering");
                    break;
                }
                case 19: {
                    platinumTest.setName("Farm & Ranch Management");
                    break;
                }
                case 20: {
                    platinumTest.setName("Farm Mechanics");
                    break;
                }
                case 21: {
                    platinumTest.setName("Fly‐Fishing");
                    break;
                }
                case 22: {
                    platinumTest.setName("Foreign Language");
                    break;
                }
                case 23: {
                    platinumTest.setName("Geology");
                    break;
                }
                case 24: {
                    platinumTest.setName("Golf");
                    break;
                }
                case 25: {
                    platinumTest.setName("Graphic Arts");
                    break;
                }
                case 26: {
                    platinumTest.setName("Hide Tanning");
                    break;
                }
                case 27: {
                    platinumTest.setName("Home Missions Construction");
                    break;
                }
                case 28: {
                    platinumTest.setName("Hunter Education/Safety");
                    break;
                }
                case 29: {
                    platinumTest.setName("Journalism");
                    break;
                }
                case 30: {
                    platinumTest.setName("Kayaking");
                    break;
                }
                case 31: {
                    platinumTest.setName("Landscape Architecture");
                    break;
                }
                case 32: {
                    platinumTest.setName("Law");
                    break;
                }
                case 33: {
                    platinumTest.setName("Lifesaving");
                    break;
                }
                case 34: {
                    platinumTest.setName("Mammals");
                    break;
                }
                case 35: {
                    platinumTest.setName("Masonry");
                    break;
                }
                case 36: {
                    platinumTest.setName("Medicine");
                    break;
                }
                case 37: {
                    platinumTest.setName("Metalwork");
                    break;
                }
                case 38: {
                    platinumTest.setName("Model Rocketry");
                    break;
                }
                case 39: {
                    platinumTest.setName("Motor Boating");
                    break;
                }
                case 40: {
                    platinumTest.setName("Mountain Biking");
                    break;
                }
                case 41: {
                    platinumTest.setName("Oceanography");
                    break;
                }
                case 42: {
                    platinumTest.setName("Pageantry");
                    break;
                }
                case 43: {
                    platinumTest.setName("Paintball");
                    break;
                }
                case 44: {
                    platinumTest.setName("Plumbing");
                    break;
                }
                case 45: {
                    platinumTest.setName("Public Health");
                    break;
                }
                case 46: {
                    platinumTest.setName("Rappelling");
                    break;
                }
                case 47: {
                    platinumTest.setName("Rock Climbing");
                    break;
                }
                case 48: {
                    platinumTest.setName("Sailing");
                    break;
                }
                case 49: {
                    platinumTest.setName("Scholarship");
                    break;
                }
                case 50: {
                    platinumTest.setName("Scuba Diving");
                    break;
                }
                case 51: {
                    platinumTest.setName("Sign Language");
                    break;
                }
                case 52: {
                    platinumTest.setName("Sixteen Fundamental Truths");
                    break;
                }
                case 53: {
                    platinumTest.setName("Skiing");
                    break;
                }
                case 54: {
                    platinumTest.setName("Skin Diving");
                    break;
                }
                case 55: {
                    platinumTest.setName("Snowboarding");
                    break;
                }
                case 56: {
                    platinumTest.setName("Soil & Water Conservation");
                    break;
                }
                case 57: {
                    platinumTest.setName("Solar Science");
                    break;
                }
                case 58: {
                    platinumTest.setName("Surveying");
                    break;
                }
                case 59: {
                    platinumTest.setName("Track");
                    break;
                }
                case 60: {
                    platinumTest.setName("Traffic Safety");
                    break;
                }
                case 61: {
                    platinumTest.setName("Veterinary Medicine");
                    break;
                }
                case 62: {
                    platinumTest.setName("Water Safety Instructor");
                    break;
                }
                case 63: {
                    platinumTest.setName("Water Skiing");
                    break;
                }
                case 64: {
                    platinumTest.setName("Whitewater Rafting");
                    break;
                }
                case 65: {
                    platinumTest.setName("Winter Camping");
                    break;
                }
                case 66: {
                    platinumTest.setName("Woodworking");
                    break;
                }
                case 67: {
                    platinumTest.setName("World Missions");
                    break;
                }
                case 68: {
                    platinumTest.setName("World Missions Construction");
                    break;
                }
                case 69: {
                    platinumTest.setName("Youth Missions");
                    break;
                }
            }
            tests.add(platinumTest);
        });
    }

    private void createRedTest(List<Test> tests) {
        IntStream.range(1, 7).forEach(index -> {
            Test redTest = new Test();
            redTest.setTestType(TestType.RED);
            redTest.setUserAgeGroups(new ArrayList<>(Arrays.asList(UserAgeGroup.PATHFINDER, UserAgeGroup.PIONEER)));
            redTest.setName("Червоний тест лідерство 10" + index);
            tests.add(redTest);
        });
    }

    private void createYellowTest(List<Test> tests) {
        IntStream.range(1, 7).forEach(index -> {
            Test yellowTest = new Test();
            yellowTest.setTestType(TestType.YELLOW);
            yellowTest.setUserAgeGroups(new ArrayList<>(Arrays.asList(UserAgeGroup.PATHFINDER, UserAgeGroup.PIONEER, UserAgeGroup.RANGER)));
            yellowTest.setName("Жовтий тест лідерство 20" + index);
            tests.add(yellowTest);
        });
    }

    private void createLightBlueTest(List<Test> tests) {
        IntStream.range(1, 7).forEach(index -> {
            Test lightBlueTest = new Test();
            lightBlueTest.setTestType(TestType.LIGHTBLUE);
            lightBlueTest.setUserAgeGroups(new ArrayList<>(Arrays.asList(UserAgeGroup.PATHFINDER, UserAgeGroup.RANGER)));
            lightBlueTest.setName("Голубий тест лідерство 30" + index);
            tests.add(lightBlueTest);
        });
    }

    private void createBlueTest(List<Test> tests) {
        IntStream.range(1, 48).forEach(index -> {
            Test blueTest = new Test();
            blueTest.setTestType(TestType.BLUE);
            blueTest.setUserAgeGroups(new ArrayList<>(Arrays.asList(UserAgeGroup.PIONEER)));
            switch (index) {
                case 1: {
                    blueTest.setName("Art");
                    break;
                }
                case 2: {
                    blueTest.setName("Astronomy");
                    break;
                }
                case 3: {
                    blueTest.setName("Basic Sign Language");
                    break;
                }
                case 4: {
                    blueTest.setName("Basketry");
                    break;
                }
                case 5: {
                    blueTest.setName("BB Gun");
                    break;
                }
                case 6: {
                    blueTest.setName("Bible Reading");
                    break;
                }
                case 7: {
                    blueTest.setName("Bird Study");
                    break;
                }
                case 8: {
                    blueTest.setName("Chess");
                    break;
                }
                case 9: {
                    blueTest.setName("Coin Collecting");
                    break;
                }
                case 10: {
                    blueTest.setName("Collections");
                    break;
                }
                case 11: {
                    blueTest.setName("Compass");
                    break;
                }
                case 12: {
                    blueTest.setName("Darts");
                    break;
                }
                case 13: {
                    blueTest.setName("Disability");
                    break;
                }
                case 14: {
                    blueTest.setName("Awareness");
                    break;
                }
                case 15: {
                    blueTest.setName("Dog Care");
                    break;
                }
                case 16: {
                    blueTest.setName("Family Life");
                    break;
                }
                case 17: {
                    blueTest.setName("Fingerprinting");
                    break;
                }
                case 18: {
                    blueTest.setName("Firearm Safety");
                    break;
                }
                case 19: {
                    blueTest.setName("Fishing");
                    break;
                }
                case 20: {
                    blueTest.setName("Global Missions");
                    break;
                }
                case 21: {
                    blueTest.setName("Hobby");
                    break;
                }
                case 22: {
                    blueTest.setName("Ice Skating");
                    break;
                }
                case 23: {
                    blueTest.setName("In‐Line Skating");
                    break;
                }
                case 24: {
                    blueTest.setName("Insect Study");
                    break;
                }
                case 25: {
                    blueTest.setName("Junior Bible Quiz");
                    break;
                }
                case 26: {
                    blueTest.setName("Lashing");
                    break;
                }
                case 27: {
                    blueTest.setName("Law Enforcement");
                    break;
                }
                case 28: {
                    blueTest.setName("Marksmanship");
                    break;
                }
                case 29: {
                    blueTest.setName("Models & Design");
                    break;
                }
                case 30: {
                    blueTest.setName("Music");
                    break;
                }
                case 31: {
                    blueTest.setName("National Prayer Center");
                    break;
                }
                case 32: {
                    blueTest.setName("Painting");
                    break;
                }
                case 33: {
                    blueTest.setName("Pets");
                    break;
                }
                case 34: {
                    blueTest.setName("Pioneer Lore");
                    break;
                }
                case 35: {
                    blueTest.setName("Presidents");
                    break;
                }
                case 36: {
                    blueTest.setName("Railroading");
                    break;
                }
                case 37: {
                    blueTest.setName("Reading");
                    break;
                }
                case 38: {
                    blueTest.setName("Rocketry");
                    break;
                }
                case 39: {
                    blueTest.setName("Roller Skating");
                    break;
                }
                case 40: {
                    blueTest.setName("Rowing");
                    break;
                }
                case 41: {
                    blueTest.setName("Safety");
                    break;
                }
                case 42: {
                    blueTest.setName("Sculpture");
                    break;
                }
                case 43: {
                    blueTest.setName("Senior Citizens");
                    break;
                }
                case 44: {
                    blueTest.setName("Space Exploration");
                    break;
                }
                case 45: {
                    blueTest.setName("Weather");
                    break;
                }
                case 47: {
                    blueTest.setName("Wildlife");
                    break;
                }
            }
            tests.add(blueTest);
        });
    }

    private void createExampleTest(List<Test> tests) {
        IntStream.range(1, 6).forEach(element -> {
            Test test = new Test();
            switch (element) {
                case 1: {
                    test.setName("Дика природа");
                    test.setShortDescription("Синій тест майстерності. Ти дізнаєшся багато нового про дику природу та зможеш\n"
                            + "здійснити невеликий похід зі своєю ланкою.");
                    test.setDescription(
                            "Ти дізнаєшся що таке охорона природи." +
                                    "Ти дізнаєшся наскільки важливою була дика природа для виживання людини протягом усієї історії." +
                                    "Ти дізнаєшся чому так важливо зберігати природу." +
                                    "Ти навчишся розпізнавати сліди тварин для того, щоб дізнатись про них більше." +
                                    "Ти навчишся виготовлятии гіпсовий зліпок сліду, знайденого на природі." +
                                    "Ти дізнаєшся про різні місця мешкання, а також описати те, як диким тваринам може" +
                                    "загрожувати вимирання, якщо не виконуватимуться закони про Охорону." +
                                    "Ти дізнаєшся про дві проблеми, які впливають на дику природу краю (області, країни)." +
                                    "Ти дізнаєшся, чому охорона й збереження природи є корисними для нас самих." +
                                    "Ти дізнаєшся про способи охорони природи."
                    );
                    test.setLogoUrl(null);
                    test.setTestType(TestType.BLUE);
                    test.setUserAgeGroups(new ArrayList<>(Arrays.asList(UserAgeGroup.PIONEER, UserAgeGroup.RANGER)));
                    break;
                }
                case 2: {
                    test.setName("Догляд заСобакою");
                    test.setShortDescription("Ти дізнаєшся багато нового про собаку та догляд про неї.");
                    test.setDescription(
                            "Ти зможеш назвати частини тіла собаки й підписати на малюнку." +
                                    "Ти зможеш обговорити користь собак для людей." +
                                    "Ти зможеш розпізнати деякі з основних відмінностей між деякими групами собак." +
                                    "Ти зможеш розповісти, за якими характерними ознаками оцінюють собак на великих змаганнях." +
                                    "Ти зможеш назвати деякі породи собак, а також деякі характеристики, які кожну з цих порід роблять унікальною." +
                                    "Ти зможеш назвати деякі породи собак, а також деякі характеристики, які кожну з цих порід роблять унікальною." +
                                    "Ти зможеш пояснити, що значить доглядати за собакою: годувати, забезпечувати водою, грумінг, фізичні вправи й купання." +
                                    "Ти зможеш розповісти про виховання та дресирування собаки."
                    );
                    test.setLogoUrl(null);
                    test.setTestType(TestType.BLUE);
                    test.setUserAgeGroups(new ArrayList<>(Arrays.asList(UserAgeGroup.PIONEER)));
                    break;
                }
                case 3: {
                    test.setName("Лешинг");
                    test.setShortDescription("Червоний тест майстерності. Для його виконання, тобі потрібно закінчити тест Робота з " +
                            "Мотузкою. Ти навчишся робити багато корисних в поході речей за допомогою лешингу.");
                    test.setDescription(
                            "Ти вивчиш квадратний, діагональний, круговий, паралельний та безперервний лешинг. Ти навчишся робити туристичні меблі." +
                                    "Діти зможуть правильно виконати квадратне кріплення.");
                    test.setLogoUrl(null);
                    test.setTestType(TestType.RED);
                    test.setUserAgeGroups(new ArrayList<>(Arrays.asList(UserAgeGroup.BEGINNER)));
                    break;
                }
                case 4: {
                    test.setName("Приготування їжї");
                    test.setShortDescription("Червоний тест майстерності. Цей Тест Майстерності є обов’язковим для отримання Золотої Медалі Досягнень. Ти навчишся складати меню для походу та готувати їжу у фользі.");
                    test.setDescription(
                            "Діти дізнаються про харчову піраміду. 2. Діти дізнаються про розмір рекомендованої порції на одну людину. 3. Діти почнуть планувати меню для походу на 6 дітей. знаються про харчову піраміду. Діти дізнаються про харчову піраміду. 1. Діти дізнаються про харчову піраміду. 2. Діти дізнаються про розмір рекомендованої порції на одну людину. 3. Діти почнуть планувати меню для походу на 6 дітей. 1. Діти дізнаються про харчову піраміду. 2. Діти дізнаються про розмір рекомендованої порції на одну людину. 3. Діти почнуть планувати меню для походу на 6 дітей. Діти почнуть планувати меню для походу на 6 дітей. Ти дізнаєшся про харчові піраміду" +
                                    "Ти дізнаєшся про розмір рекомендованої порції на одну людину" +
                                    "Ти навчишся планувати меню для походу" +
                                    "Ти навчишся розраховувати вартість меню для походу" +
                                    "Ти навчишся обладнувати місце для миття посуду способом «мий, промивай, промивай»" +
                                    "Діти навчаться обладнувати місце для миття посуду способом «помий і двічі сполосни». Ти навчишся обладнувати місце для миття рук\n" +
                                    "Ти навчишся випікати кекси на багатті" +
                                    "Ти навчишся готувати в фользі");
                    test.setLogoUrl(null);
                    test.setUserAgeGroups(new ArrayList<>(Arrays.asList(UserAgeGroup.BEGINNER)));
                    test.setTestType(TestType.RED);
                    break;
                }
                case 5: {
                    test.setName("Розведення багаття");
                    test.setShortDescription("Червоний тест майстерності. Ти навчишся розпалювати багаття 6 видів.");
                    test.setDescription(
                            "Ти зможеш назвати правила техніки безпеки при розпалюванні багаття." +
                                    "Ти знатимеш про 4 види трута, хмиз та дрова." +
                                    "Ти зможеш показати, як складається багаття Тіпі." +
                                    "Ти зможут зібрати протипожежне обладнання для походу." +
                                    "Ти зможеш пояснити, як боротися з вогнем у різних умовах: вдома, в лісі, на відкритому просторі." +
                                    "Ти зможеш зібрати табірне протипожежне обладнання й пояснити його призначення." +
                                    "Ти зможеш скласти багаття в вигляді літери А." +
                                    "Ти зможеш назвати правила безпеки при поводженні з газовою плиткою." +
                                    "Ти зможеш показати, як складається мисливське багаття та багаття ради." +
                                    "Ти зможеш показати, як складається канавне багаття." +
                                    "Ти навчишся виконувати всі правила безпеки." +
                                    "Ти зможеш показати, як правильно гасити багаття.");
                    test.setLogoUrl(null);
                    test.setTestType(TestType.RED);
                    test.setUserAgeGroups(new ArrayList<>(Arrays.asList(UserAgeGroup.PATHFINDER, UserAgeGroup.RANGER)));
                    break;
                }
            }
            tests.add(test);
        });
    }

    public List<Test> createTest() {
        List<Test> tests = new ArrayList<>();
        createGreenTest(tests);
        createOrangeTest(tests);
        createBrownTest(tests);
        createPlatinumTest(tests);
        createRedTest(tests);
        createYellowTest(tests);
        createLightBlueTest(tests);
        createBlueTest(tests);
        createExampleTest(tests);
        return tests;
    }
}
