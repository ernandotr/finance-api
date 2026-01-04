package dev.ernandorezende.financeapi.application.requests;

import dev.ernandorezende.financeapi.domain.RoleEnum;

public record NewUserRequest(String name, String username, String password, RoleEnum role) {
}
