package com.example.springexam.model.entity.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public enum TopicType {
  TESTING("Testing"),
  CONTAINER_ORCHESTRATION("Container Orchestration"),
  CLOUD_NATIVE_APPLICATION_DELIVERY("Cloud Native Application Delivery"),
  CLOUD_NATIVE_ARCHITECTURE("Cloud Native Architecture"),
  CLOUD_NATIVE_OBSERVABILITY("Cloud Native Observability"),
  KUBERNETES_FUNDAMENTALS("Kubernetes Fundamentals"),
  HANDLING_EXCEPTIONS("Handling Exceptions"),
  WORKING_WITH_JAVA_CLASSES("Working with Selected Classes from the Java API"),
  CREATING_AND_USING_ARRAYS("Creating and Using Arrays"),
  WORKING_WITH_JAVA_DATA_TYPES("Working with Java Data Types"),
  USING_OPERATORS_AND_DECISION_CONSTRUCTS("Using Operators and Decision Constructs"),
  JAVA_BASICS("Java Basics"),
  WORKING_WITH_METHODS_AND_ENCAPSULATION("Working with Methods and Encapsulation"),
  WORKING_WITH_INHERITANCE("Working with Inheritance"),
  USING_LOOP_CONSTRUCTS("Using Loop Constructs"),
  SPRING_CORE("Spring Core"),
  SECURITY("Security"),
  SPRING_AOP("Spring AOP"),
  SPRING_MVC("Spring MVC"),
  SPRING_SECURITY("Spring Security"),
  SPRING_BOOT("Spring Boot"),
  SPRING_BOOT_ACTUATOR("Spring Boot Actuator"),
  DATA_MANAGEMENT("Data Management"),
  CONFIGURATION("Configuration"),
  SPRING_ACTUATOR("Spring Actuator");

  private final String displayName;

  TopicType(String displayName) {
    this.displayName = displayName;
  }

  public static TopicType fromDisplayName(String displayName) {
    if (displayName == null || displayName.isBlank()) {
      return null;
    }
    String normalizedInput = displayName.trim().toLowerCase();
    return Arrays.stream(values())
            .filter(topic -> topic.displayName.toLowerCase().equals(normalizedInput))
            .findFirst().orElseThrow();
  }

  public static Set<TopicType> findByKeyword(String keyword) {
    if (keyword == null || keyword.isBlank()) {
      return Set.of();
    }
    String normalizedKeyword = keyword.trim().toLowerCase();

    return Arrays.stream(values())
            .filter(topic -> topic.matchesKeyword(normalizedKeyword))
            .collect(Collectors.toSet());
  }

  private boolean matchesKeyword(String keyword) {
    String dn = displayName.toLowerCase();
    String nm = name().toLowerCase();

    if (dn.contains(keyword) || nm.contains(keyword)) {
      return true;
    }

    return switch (keyword) {
      case "java" -> isJavaRelated();
      case "spring" -> isSpringRelated();
      case "k8s" -> isKubernetesRelated();
      default -> false;
    };
  }


  private boolean isJavaRelated() {
    return this == HANDLING_EXCEPTIONS ||
            this == WORKING_WITH_JAVA_CLASSES ||
            this == CREATING_AND_USING_ARRAYS ||
            this == WORKING_WITH_JAVA_DATA_TYPES ||
            this == USING_OPERATORS_AND_DECISION_CONSTRUCTS ||
            this == JAVA_BASICS ||
            this == WORKING_WITH_METHODS_AND_ENCAPSULATION ||
            this == WORKING_WITH_INHERITANCE ||
            this == USING_LOOP_CONSTRUCTS ||
            this.displayName.toLowerCase().contains("java") ||
            this.name().toLowerCase().contains("java");
  }

  private boolean isSpringRelated() {
    return this == SPRING_CORE ||
            this == SPRING_AOP ||
            this == SPRING_MVC ||
            this == SPRING_SECURITY ||
            this == SPRING_BOOT ||
            this == SPRING_BOOT_ACTUATOR ||
            this == SPRING_ACTUATOR ||
            this.displayName.toLowerCase().contains("spring") ||
            this.name().toLowerCase().contains("spring");
  }


  private boolean isKubernetesRelated() {
    return this == KUBERNETES_FUNDAMENTALS ||
            this == CONTAINER_ORCHESTRATION ||
            this == CLOUD_NATIVE_APPLICATION_DELIVERY ||
            this == CLOUD_NATIVE_ARCHITECTURE ||
            this == CLOUD_NATIVE_OBSERVABILITY ||
            this.displayName.toLowerCase().contains("kubernetes") ||
            this.displayName.toLowerCase().contains("container") ||
            this.displayName.toLowerCase().contains("cloud") ||
            this.name().toLowerCase().contains("kubernetes") ||
            this.name().toLowerCase().contains("container") ||
            this.name().toLowerCase().contains("cloud");
  }

}
