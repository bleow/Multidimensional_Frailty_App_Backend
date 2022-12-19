package com.frailty.backend.output;

import org.springframework.stereotype.Service;
import static org.springframework.util.StringUtils.capitalize;

@Service
public class Localiser {
    public String invalid(String fieldName, String userInput) {
        return String.format("%s is not a valid %s", userInput, fieldName);
    }

    public String notFound(String fieldName, String userInput) {
        return String.format("No such %s found with %s", fieldName, userInput);
    }

    public String duplicate(String fieldName, String userInput) {
        return String.format("There is already an existing %s with %s", fieldName, userInput);
    }

    public String serviceFail(String svc) {
        return String.format("%s service failed.", svc);
    }

    public String success(String successObj) {
        return String.format("%s successful!", successObj);
    }

    public String fail(String reason) {
        return String.format("Operation failed. Reason: %s", reason);
    }
}
