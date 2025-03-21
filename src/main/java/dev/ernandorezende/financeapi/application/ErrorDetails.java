package dev.ernandorezende.financeapi.application;

import java.util.Date;

public record ErrorDetails(Date timestamp, String message, String details) {

}
