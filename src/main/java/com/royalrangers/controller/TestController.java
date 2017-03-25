package com.royalrangers.controller;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.service.QuarterAchievementService;
import com.royalrangers.service.TestService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    QuarterAchievementService quarterAchievementService;

    @RequestMapping(value = "/achievements/test", method = RequestMethod.GET)
    public ResponseResult getAllTasks() {
        try {
            return ResponseBuilder.success(testService.getAllTest());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get Tests");
        }
    }

    @RequestMapping(value = "/achievements/test", method = RequestMethod.POST)
    public ResponseResult addTask(@RequestBody Map<String, Object> params) {
        try {
            testService.addTest(params);
            return ResponseBuilder.success("Test saved successfully");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed adding Test");
        }
    }

    @RequestMapping(value = "/achievements/test/{testId}", method = RequestMethod.GET)
    public ResponseResult getTaskById(@PathVariable Long testId) {
        try {
            return ResponseBuilder.success(testService.getTestById(testId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get Test by id");
        }
    }

    @RequestMapping(value = "/achievements/test/{testId}", method = RequestMethod.DELETE)
    public ResponseResult deleteTaskById(@PathVariable Long testId) {
        try {
            testService.deleteTestById(testId);
            return ResponseBuilder.success("Test was successfully deleted");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed delete Test");
        }
    }

    @RequestMapping(value = "/achievements/test/{testId}", method = RequestMethod.PUT)
    public ResponseResult editTaskById(@RequestBody Map<String, Object> params, @PathVariable Long testId) {
        try {
            return ResponseBuilder.success(testService.editTest(params, testId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit Task");
        }
    }
}