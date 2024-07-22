package com.grupo2.kanbanboard.requests;



public record CreateTaskInput(String name, String status, int projectId) {

}
