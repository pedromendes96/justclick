package org.justclick.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No valid redirect")
public class KeyNotFoundException  extends RuntimeException{}
