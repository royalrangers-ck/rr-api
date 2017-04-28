package com.royalrangers.configuration.bootstrap;

import com.royalrangers.dto.achievement.TaskRequestDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TaskBootstrap {

    public List<TaskRequestDto> createTask() {
        List<TaskRequestDto> tasks = new ArrayList<>();
        for (int taskElement = 1; taskElement < 9; taskElement++) {
            TaskRequestDto taskRequestDto = new TaskRequestDto();
            switch (taskElement) {
                case 1: {
                    taskRequestDto.setName("Наведи визначення терміну охорона природи.");
                    taskRequestDto.setDescription("");
                    taskRequestDto.setTestId(1);
                    break;
                }
                case 2: {
                    taskRequestDto.setName("Опиши дві проблеми, котрі впливають на дику природу твого краю (області, регіону).");
                    taskRequestDto.setDescription("");
                    taskRequestDto.setTestId(1);
                    break;
                }
                case 3: {
                    taskRequestDto.setName("Опиши дві проблеми, котрі впливають на дику природу твого краю (області, регіону).");
                    taskRequestDto.setDescription("");
                    taskRequestDto.setTestId(1);
                    break;
                }
                case 4: {
                    taskRequestDto.setName("Під час походу на природу для спостереження за нею записуй таку інформацію:");
                    taskRequestDto.setDescription(
                            "a. Дату, час початку і закінчення спостереження.\n" +
                                    "b. Місце, де виконувалось спостереження.\n" +
                                    "c. За якою флорою чи фауною виконувалось спостереження.\n" +
                                    "d. Місця мешкання кожного виду, за яким велося спостереження.\n" +
                                    "e. Мета спостереження: про що ти планував дізнатись? Чи дізнався ти про це?\n" +
                                    "Виконайте це завдання на окремому аркушіпаперу і вставте в Робочий Зошит.");
                    taskRequestDto.setTestId(1);
                    break;
                }
                case 5: {
                    taskRequestDto.setName("Зроби гіпсовий зліпок сліду тварини під час походу або зборів Royal Rangers, використовуючи спеціальний набір. (Цей набір можна придбати або виготовити в загоні).");
                    taskRequestDto.setDescription("");
                    taskRequestDto.setTestId(1);
                    break;
                }
                case 6: {
                    taskRequestDto.setName("Обери й виконай два з запропонованих завдань і покажи їх Командиру:");
                    taskRequestDto.setDescription("a. Сфотографуй два види диких тварин. Напиши звіт на 75-100 слів, який би " +
                            "описував кожну тварину та місця її мешкання. Кожен звіт крім того мусить містити " +
                            "замальовку слідів тварини, мапу, на якій вказана територія мешкання і список поживи, якою харчується ця тварина.\n" +
                            "b. В інтернеті знайди описи восьми диких тварин, котрі мешкають у твоїй місцевості. Роздрукуй їхні зображення й напиши основну інформацію.\n" +
                            "c. Підготуй альбом з вирізками, який містивби описи та місця мешкання чотирьох видів тварин дикої природи в твоїй місцевості.\n" +
                            "Вклади світлину, малюнок чи зображення кожного виду.\n" +
                            "Обведіть два варіанти. Вставте аркуш з отриманою інформацією до Робочого Зошита.");
                    taskRequestDto.setTestId(1);
                    break;
                }
                case 7: {
                    taskRequestDto.setName("Навчись розпізнавати по світлинах, малюнках чи зображеннях щонайменше 25 ссавців, характерних для твоєї країни.");
                    taskRequestDto.setDescription("");
                    taskRequestDto.setTestId(1);
                    break;
                }
                case 8: {
                    taskRequestDto.setName("Розкажи напам’ять три вірші з Біблії, в яких йдеться про трьох різних тварин.");
                    taskRequestDto.setDescription("");
                    taskRequestDto.setTestId(1);
                    break;
                }
            }
            tasks.add(taskRequestDto);
        }
        ;

        IntStream.range(1, 7).forEach(element -> {
            TaskRequestDto taskRequestDto = new TaskRequestDto();
            switch (element) {
                case 1: {
                    taskRequestDto.setName("Породи й фізичні характеристики.");
                    taskRequestDto.setDescription(
                            "a. Назви й опиши деякі з основних відмінностей між, щонайменше, чотирма основними групами собак." +
                                    "b. Обери 8 різних порід собак і перерахуй деякі з характерних особливостей, які роблять кожну породу унікальною та відмінною від інших порід." +
                                    "c. Опиши, за якими характерними ознаками оцінюють собак на великих змаганнях." +
                                    "d. Правильно назви, щонайменше, 12 частин тіла собаки, зображеного на малюнку. " +
                                    "Для виконання цієї Вимоги заповніть РЗ 1 “Частини собаки”."
                    );
                    taskRequestDto.setTestId(2);
                    break;
                }
                case 2: {
                    taskRequestDto.setName("Історія");
                    taskRequestDto.setDescription(
                            "a. Склади твір на 1 сторінку про історію твоєї улюбленої породи собак. Виконайте це завдання на окремому аркуші й уставте до Робочого Зошита." +
                                    "b. Виконай ОДНЕ з наступних завдань:" +
                                    "i) Склади твір на 1 сторінку про переваги собак та про їхнє ставлення до людей." +
                                    "Виконайте це завдання на окремому аркуші й уставте до Робочого Зошита." +
                                    "ii) Склади твір на 1 сторінку про те, чому собаки такі популярні в якості домашніх тварин. " +
                                    "Виконайте це завдання на окремому аркуші й уставте до Робочого Зошита."
                    );
                    taskRequestDto.setTestId(2);
                    break;
                }
                case 3: {
                    taskRequestDto.setName("Опиши дві проблеми, котрі впливають на дику природу твого краю (області, регіону).");
                    taskRequestDto.setDescription("");
                    taskRequestDto.setTestId(2);
                    break;
                }
                case 4: {
                    taskRequestDto.setName("Під час походу на природу для спостереження за нею записуй таку інформацію:");
                    taskRequestDto.setDescription(
                            "a. Дату, час початку і закінчення спостереження." +
                                    "b. Місце, де виконувалось спостереження." +
                                    "c. За якою флорою чи фауною виконувалось спостереження." +
                                    "d. Місця мешкання кожного виду, за яким велося спостереження." +
                                    "e. Мета спостереження: про що ти планував дізнатись? Чи дізнався ти про це?" +
                                    "Виконайте це завдання на окремому аркушіпаперу і вставте в Робочий Зошит.");
                    taskRequestDto.setTestId(2);
                    break;
                }
                case 5: {
                    taskRequestDto.setName("Зроби гіпсовий зліпок сліду тварини під час походу або зборів Royal Rangers, використовуючи спеціальний набір. (Цей набір можна придбати або виготовити в загоні).");
                    taskRequestDto.setDescription("");
                    taskRequestDto.setTestId(2);
                    break;
                }
                case 6: {
                    taskRequestDto.setName("Обери й виконай два з запропонованих завдань і покажи їх Командиру:");
                    taskRequestDto.setDescription("a. Сфотографуй два види диких тварин. Напиши звіт на 75-100 слів, який би " +
                            "описував кожну тварину та місця її мешкання. Кожен звіт крім того мусить містити " +
                            "замальовку слідів тварини, мапу, на якій вказана територія мешкання і список поживи, якою харчується ця тварина." +
                            "b. В інтернеті знайди описи восьми диких тварин, котрі мешкають у твоїй місцевості. Роздрукуй їхні зображення й напиши основну інформацію." +
                            "c. Підготуй альбом з вирізками, який містивби описи та місця мешкання чотирьох видів тварин дикої природи в твоїй місцевості." +
                            "Вклади світлину, малюнок чи зображення кожного виду." +
                            "Обведіть два варіанти. Вставте аркуш з отриманою інформацією до Робочого Зошита.");
                    taskRequestDto.setTestId(2);
                    break;
                }
            }
            tasks.add(taskRequestDto);
        });

        IntStream.range(1, 7).forEach(element -> {
            TaskRequestDto taskRequestDto = new TaskRequestDto();
            switch (element) {
                case 1: {
                    taskRequestDto.setName("Правильно продемонструй квадратний лешинг.");
                    taskRequestDto.setDescription("");
                    taskRequestDto.setTestId(3);
                    break;
                }
                case 2: {
                    taskRequestDto.setName("Правильно продемонструй діагональний лешинг.");
                    taskRequestDto.setDescription("");
                    taskRequestDto.setTestId(3);
                    break;
                }
                case 3: {
                    taskRequestDto.setName("Правильно продемонструй паралельний лешинг..");
                    taskRequestDto.setDescription("");
                    taskRequestDto.setTestId(3);
                    break;
                }
                case 4: {
                    taskRequestDto.setName("Правильно продемонструй круговий лешинг.");
                    taskRequestDto.setDescription("");
                    taskRequestDto.setTestId(3);
                    break;
                }
                case 5: {
                    taskRequestDto.setName("Правильно продемонструй безперервний лешинг.");
                    taskRequestDto.setDescription("");
                    taskRequestDto.setTestId(3);
                    break;
                }
                case 6: {
                    taskRequestDto.setName("Правильно виконай ОДИН з наступних проектів. (Твій командир повинен схвалити твій вибір). Можеш домовитись з іншим рейнджером, виконати однакову кількість роботи й отримати однакову оцінку за проект з лешингу.");
                    taskRequestDto.setDescription(
                            " a. Стіл" +
                                    " b. Поличка для інструменту" +
                                    " c. Індіанська плита (інша назва – тринога)"
                    );
                    taskRequestDto.setTestId(3);
                    break;
                }
            }
            tasks.add(taskRequestDto);
        });

        return tasks;
    }

}
