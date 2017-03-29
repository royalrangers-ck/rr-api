package com.royalrangers.controller;

import com.royalrangers.bean.ResponseResult;
import com.royalrangers.service.TestService;
import com.royalrangers.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/achievements/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping
    public ResponseResult getAllTasks() {
        try {
            return ResponseBuilder.success(testService.getAllTest());
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get Tests");
        }
    }

    @PostMapping
    public ResponseResult addTask(@RequestBody Map<String, Object> params) {
        try {
            testService.addTest(params);
            return ResponseBuilder.success("Test saved successfully");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed adding Test");
        }
    }

    @GetMapping("/{testId}")
    public ResponseResult getTaskById(@PathVariable Long testId) {
        try {
            return ResponseBuilder.success(testService.getTestById(testId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed get Test by id");
        }
    }

    @DeleteMapping("/{testId}")
    public ResponseResult deleteTaskById(@PathVariable Long testId) {
        try {
            testService.deleteTestById(testId);
            return ResponseBuilder.success("Test was successfully deleted");
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed delete Test");
        }
    }

    @PutMapping("/{testId}")
    public ResponseResult editTaskById(@RequestBody Map<String, Object> params, @PathVariable Long testId) {
        try {
            return ResponseBuilder.success(testService.editTest(params, testId));
        } catch (Exception ex) {
            return ResponseBuilder.fail("Failed edit Task");
        }
    }
}