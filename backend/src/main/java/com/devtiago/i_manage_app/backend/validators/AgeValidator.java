package com.devtiago.i_manage_app.backend.validators;

import com.devtiago.i_manage_app.backend.entity.Employee;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class AgeValidator implements ConstraintValidator<ValidAgeEmployee, Employee> {

    @Override
    public void initialize(ValidAgeEmployee constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Employee emp, ConstraintValidatorContext context) {
        if (emp == null || emp.getBirthdayDate() == null){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("birthdayDate is required.")
                    .addConstraintViolation();
            return false;
        }

        try{
            int age = Period.between(emp.getBirthdayDate(), LocalDate.now()).getYears();
            emp.setAge(age);

            if (age >= 18 && age <= 67){
                return true;
            }else{
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Age must be between 18 and 67")
                        .addPropertyNode("birthdayDate")
                        .addConstraintViolation();
                return false;
            }
        }catch (Exception ex){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Unexpected error " + ex.getMessage())
                    .addConstraintViolation();

            return false;
        }
    }
}
