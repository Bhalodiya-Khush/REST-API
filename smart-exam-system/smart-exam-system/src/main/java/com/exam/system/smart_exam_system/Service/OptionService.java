package com.exam.system.smart_exam_system.Service;
import com.exam.system.smart_exam_system.Entity.*;
import com.exam.system.smart_exam_system.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OptionService {

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    // Create a new option for a question
    public Option createOption(Long questionId, String optionText, boolean isCorrect) {

        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        List<Option> existingOptions =
                optionRepository.findByQuestionId(questionId);

        if(existingOptions.size() >= 4) {
            throw new RuntimeException(
                    "Maximum 4 options allowed."
            );
        }
        boolean exists =
                optionRepository.existsByQuestionIdAndOptionTextIgnoreCase(
                        questionId,
                        optionText
                );

        if (exists) {
            throw new RuntimeException("Duplicate options are not allowed");
        }
        if (isCorrect) {

            boolean correctExists = optionRepository.existsByQuestionIdAndIsCorrectTrue(questionId);

            if (correctExists) {
                throw new RuntimeException(
                        "Only one correct answer is allowed"
                );
            }
        }
        if (optionalQuestion.isPresent()) {

            Option option = new Option();
            option.setQuestion(optionalQuestion.get());
            if(optionText.trim().isEmpty()) {
                throw new RuntimeException("Option cannot be empty");
            }
            option.setOptionText(optionText);
            option.setCorrect(isCorrect);

            return optionRepository.save(option);

        } else {
            throw new RuntimeException("Question not found");
        }
    }

    // Get all options for a question
    public List<Option> getOptionsByQuestion(Long questionId) {

        Optional<Question> optionalQuestion = questionRepository.findById(questionId);

        if (optionalQuestion.isPresent()) {
            return optionRepository.findByQuestion(optionalQuestion.get());
        } else {
            throw new RuntimeException("Question not found");
        }
    }

    // Get option by id
    public Option getOptionById(Long optionId) {

        return optionRepository.findById(optionId)
                .orElseThrow(() -> new RuntimeException("Option not found"));
    }

    // Update an option
    public Option updateOption(Long optionId, String optionText, Boolean isCorrect) {

        Optional<Option> optionalOption = optionRepository.findById(optionId);

        if (optionalOption.isPresent()) {

            Option option = optionalOption.get();

            if (optionText != null) option.setOptionText(optionText);
            if (isCorrect != null) option.setCorrect(isCorrect);

            return optionRepository.save(option);

        } else {
            throw new RuntimeException("Option not found");
        }
    }

    // Delete an option
    public void deleteOption(Long optionId) {

        Optional<Option> optionalOption = optionRepository.findById(optionId);

        if (optionalOption.isPresent()) {
            optionRepository.delete(optionalOption.get());
        } else {
            throw new RuntimeException("Option not found");
        }
    }
}