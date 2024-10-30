package com.aston.frontendpracticeservice.constants;

public class ConstantsForTest {

    public static String URL_TO_GET_ALL_USERS = "http://localhost:8080/api/v1/users/all";
    public static String URL_TO_GET_USER_BY_ID = "http://localhost:8080/api/v1/users/";
    public static String NOT_CORRECT_URL = "http://localhost:8080/api/v1/users/empty";
    public static String CORRECT_JSON_FOR_ALL_USERS = "[{\"id\":1,\"firstName\":\"Sergey\",\"lastName\":\"Bichan\",\"birthDate\":\"1990-07-03\",\"inn\":\"123461234562\",\"snils\":\"13465443243\",\"passportNumber\":\"dsad1231231\",\"login\":\"admin\",\"password\":\"admin\"}]";
    public static String CORRECT_JSON_FOR_USER = "{\n" +
            "    \"id\": 1,\n" +
            "    \"firstName\": \"Sergey\",\n" +
            "    \"lastName\": \"Bichan\",\n" +
            "    \"birthDate\": \"1990-07-03\",\n" +
            "    \"inn\": \"123461234562\",\n" +
            "    \"snils\": \"13465443243\",\n" +
            "    \"passportNumber\": \"dsad1231231\",\n" +
            "    \"login\": \"admin\",\n" +
            "    \"password\": \"admin\"\n" +
            "}";
}
