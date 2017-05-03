package com.royalrangers.configuration.bootstrap;

import com.royalrangers.dto.achievement.TaskRequestDto;
import com.royalrangers.model.achievement.Task;
import com.royalrangers.repository.achievement.TestRepository;
import com.royalrangers.service.achievement.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class TaskBootstrap {

    public Map<String, Object> createTask() {
        Map<String, Object> map = new HashMap<>();
        List<Task> tasksForTest1 = new ArrayList<>();
        List<Task> tasksForTest2 = new ArrayList<>();
        List<Task> tasksForTest3 = new ArrayList<>();
        IntStream.range(1, 9).forEach(taskElement -> {
            Task task = new Task();
            switch (taskElement) {
                case 1: {
                    task.setName("Наведи визначення терміну охорона природи.");
                    task.setDescription("");
                    task.setTest(null);
                    break;
                }
                case 2: {
                    task.setName("Опиши дві проблеми, котрі впливають на дику природу твого краю (області, регіону).");
                    task.setDescription("");
                    task.setTest(null);
                    break;
                }
                case 3: {
                    task.setName("Опиши дві проблеми, котрі впливають на дику природу твого краю (області, регіону).");
                    task.setDescription("");
                    task.setTest(null);
                    break;
                }
                case 4: {
                    task.setName("Під час походу на природу для спостереження за нею записуй таку інформацію:");
                    task.setDescription(
                            "a. Дату, час початку і закінчення спостереження.\n" +
                                    "b. Місце, де виконувалось спостереження.\n" +
                                    "c. За якою флорою чи фауною виконувалось спостереження.\n" +
                                    "d. Місця мешкання кожного виду, за яким велося спостереження.\n" +
                                    "e. Мета спостереження: про що ти планував дізнатись? Чи дізнався ти про це?\n" +
                                    "Виконайте це завдання на окремому аркушіпаперу і вставте в Робочий Зошит.");
                    task.setTest(null);
                    break;
                }
                case 5: {
                    task.setName("Зроби гіпсовий зліпок сліду тварини під час походу або зборів Royal Rangers, використовуючи спеціальний набір. (Цей набір можна придбати або виготовити в загоні).");
                    task.setDescription("");
                    task.setTest(null);
                    break;
                }
                case 6: {
                    task.setName("Обери й виконай два з запропонованих завдань і покажи їх Командиру:");
                    task.setDescription("a. Сфотографуй два види диких тварин. Напиши звіт на 75-100 слів, який би " +
                            "описував кожну тварину та місця її мешкання. Кожен звіт крім того мусить містити " +
                            "замальовку слідів тварини, мапу, на якій вказана територія мешкання і список поживи, якою харчується ця тварина.\n" +
                            "b. В інтернеті знайди описи восьми диких тварин, котрі мешкають у твоїй місцевості. Роздрукуй їхні зображення й напиши основну інформацію.\n" +
                            "c. Підготуй альбом з вирізками, який містивби описи та місця мешкання чотирьох видів тварин дикої природи в твоїй місцевості.\n" +
                            "Вклади світлину, малюнок чи зображення кожного виду.\n" +
                            "Обведіть два варіанти. Вставте аркуш з отриманою інформацією до Робочого Зошита.");
                    task.setTest(null);
                    break;
                }
                case 7: {
                    task.setName("Навчись розпізнавати по світлинах, малюнках чи зображеннях щонайменше 25 ссавців, характерних для твоєї країни.");
                    task.setDescription("");
                    task.setTest(null);
                    ;
                    break;
                }
                case 8: {
                    task.setName("Розкажи напам’ять три вірші з Біблії, в яких йдеться про трьох різних тварин.");
                    task.setDescription("");
                    task.setTest(null);
                    break;
                }
            }
            tasksForTest1.add(task);
        });

        IntStream.range(1, 7).forEach(element -> {
            Task task = new Task();
            switch (element) {
                case 1: {
                    task.setName("Породи й фізичні характеристики.");
                    task.setDescription(
                            "a. Назви й опиши деякі з основних відмінностей між, щонайменше, чотирма основними групами собак." +
                                    "b. Обери 8 різних порід собак і перерахуй деякі з характерних особливостей, які роблять кожну породу унікальною та відмінною від інших порід." +
                                    "c. Опиши, за якими характерними ознаками оцінюють собак на великих змаганнях." +
                                    "d. Правильно назви, щонайменше, 12 частин тіла собаки, зображеного на малюнку. " +
                                    "Для виконання цієї Вимоги заповніть РЗ 1 “Частини собаки”."
                    );
                    task.setTest(null);
                    break;
                }
                case 2: {
                    task.setName("Історія");
                    task.setDescription(
                            "a. Склади твір на 1 сторінку про історію твоєї улюбленої породи собак. Виконайте це завдання на окремому аркуші й уставте до Робочого Зошита." +
                                    "b. Виконай ОДНЕ з наступних завдань:" +
                                    "i) Склади твір на 1 сторінку про переваги собак та про їхнє ставлення до людей." +
                                    "Виконайте це завдання на окремому аркуші й уставте до Робочого Зошита." +
                                    "ii) Склади твір на 1 сторінку про те, чому собаки такі популярні в якості домашніх тварин. " +
                                    "Виконайте це завдання на окремому аркуші й уставте до Робочого Зошита."
                    );
                    task.setTest(null);
                    break;
                }
                case 3: {
                    task.setName("Опиши дві проблеми, котрі впливають на дику природу твого краю (області, регіону).");
                    task.setDescription("");
                    task.setTest(null);
                    break;
                }
                case 4: {
                    task.setName("Під час походу на природу для спостереження за нею записуй таку інформацію:");
                    task.setDescription(
                            "a. Дату, час початку і закінчення спостереження." +
                                    "b. Місце, де виконувалось спостереження." +
                                    "c. За якою флорою чи фауною виконувалось спостереження." +
                                    "d. Місця мешкання кожного виду, за яким велося спостереження." +
                                    "e. Мета спостереження: про що ти планував дізнатись? Чи дізнався ти про це?" +
                                    "Виконайте це завдання на окремому аркушіпаперу і вставте в Робочий Зошит.");
                    task.setTest(null);
                    break;
                }
                case 5: {
                    task.setName("Зроби гіпсовий зліпок сліду тварини під час походу або зборів Royal Rangers, використовуючи спеціальний набір. (Цей набір можна придбати або виготовити в загоні).");
                    task.setDescription("");
                    task.setTest(null);
                    break;
                }
                case 6: {
                    task.setName("Обери й виконай два з запропонованих завдань і покажи їх Командиру:");
                    task.setDescription("a. Сфотографуй два види диких тварин. Напиши звіт на 75-100 слів, який би " +
                            "описував кожну тварину та місця її мешкання. Кожен звіт крім того мусить містити " +
                            "замальовку слідів тварини, мапу, на якій вказана територія мешкання і список поживи, якою харчується ця тварина." +
                            "b. В інтернеті знайди описи восьми диких тварин, котрі мешкають у твоїй місцевості. Роздрукуй їхні зображення й напиши основну інформацію." +
                            "c. Підготуй альбом з вирізками, який містивби описи та місця мешкання чотирьох видів тварин дикої природи в твоїй місцевості." +
                            "Вклади світлину, малюнок чи зображення кожного виду." +
                            "Обведіть два варіанти. Вставте аркуш з отриманою інформацією до Робочого Зошита.");
                    task.setTest(null);
                    break;
                }
            }
            tasksForTest2.add(task);
        });

        IntStream.range(1, 7).forEach(element -> {
            Task task = new Task();
            switch (element) {
                case 1: {
                    task.setName("Правильно продемонструй квадратний лешинг.");
                    task.setDescription("");
                    task.setTest(null);
                    break;
                }
                case 2: {
                    task.setName("Правильно продемонструй діагональний лешинг.");
                    task.setDescription("");
                    task.setTest(null);
                    break;
                }
                case 3: {
                    task.setName("Правильно продемонструй паралельний лешинг..");
                    task.setDescription("");
                    task.setTest(null);
                    break;
                }
                case 4: {
                    task.setName("Правильно продемонструй круговий лешинг.");
                    task.setDescription("");
                    task.setTest(null);
                    break;
                }
                case 5: {
                    task.setName("Правильно продемонструй безперервний лешинг.");
                    task.setDescription("");
                    task.setTest(null);
                    break;
                }
                case 6: {
                    task.setName("Правильно виконай ОДИН з наступних проектів. (Твій командир повинен схвалити твій вибір). Можеш домовитись з іншим рейнджером, виконати однакову кількість роботи й отримати однакову оцінку за проект з лешингу.");
                    task.setDescription(
                            " a. Стіл" +
                                    " b. Поличка для інструменту" +
                                    " c. Індіанська плита (інша назва – тринога)"
                    );
                    task.setTest(null);
                    break;
                }
            }
            tasksForTest3.add(task);
        });
        map.put("listForTest1", tasksForTest1);
        map.put("listForTest2", tasksForTest2);
        map.put("listForTest3", tasksForTest3);
        return map;
    }

}
