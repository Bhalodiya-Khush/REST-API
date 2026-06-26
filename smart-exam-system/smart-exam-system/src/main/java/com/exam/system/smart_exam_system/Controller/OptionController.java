package com.exam.system.smart_exam_system.Controller;

import com.exam.system.smart_exam_system.Entity.*;
import com.exam.system.smart_exam_system.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;;import java.util.List;

@RestController
@RequestMapping("/api/options")
public class OptionController {

    @Autowired
    private OptionService optionService;

    // Create Option for a Question
    @PostMapping("/question/{questionId}")
    public ResponseEntity<Option> createOption(
            @PathVariable Long questionId,
            @RequestBody Option request) {

        Option option = optionService.createOption(
                questionId,
                request.getOptionText(),
                request.isCorrect()
        );

        return ResponseEntity.ok(option);
    }

    // Get Option by ID
    @GetMapping("/{optionId}")
    public ResponseEntity<Option> getOptionById(@PathVariable Long optionId) {

        return ResponseEntity.ok(
                optionService.getOptionById(optionId)
        );
    }

    // Get all Options for a Question
    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<Option>> getOptionsByQuestion(@PathVariable Long questionId) {

        return ResponseEntity.ok(
                optionService.getOptionsByQuestion(questionId)
        );
    }

    // Update Option
    @PutMapping("/{optionId}")
    public ResponseEntity<Option> updateOption(
            @PathVariable Long optionId,
            @RequestBody Option request) {

        Option updatedOption = optionService.updateOption(
                optionId,
                request.getOptionText(),
                request.isCorrect()
        );

        return ResponseEntity.ok(updatedOption);
    }

    // Delete Option
    @DeleteMapping("/{optionId}")
    public ResponseEntity<String> deleteOption(@PathVariable Long optionId) {

        optionService.deleteOption(optionId);
        return ResponseEntity.ok("Option deleted successfully");
    }
}