package com.example.springexam.model.entity.enums;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Getter
public enum TopicType {
  TESTING("Testing", Category.SPRING),
  CONTAINER_ORCHESTRATION("Container Orchestration", Category.KUBERNETES),
  CLOUD_NATIVE_APPLICATION_DELIVERY("Cloud Native Application Delivery", Category.KUBERNETES),
  CLOUD_NATIVE_ARCHITECTURE("Cloud Native Architecture", Category.KUBERNETES),
  CLOUD_NATIVE_OBSERVABILITY("Cloud Native Observability", Category.KUBERNETES),
  KUBERNETES_FUNDAMENTALS("Kubernetes Fundamentals", Category.KUBERNETES),
  HANDLING_EXCEPTIONS("Handling Exceptions", Category.JAVA),
  WORKING_WITH_JAVA_CLASSES("Working with Selected Classes from the Java API", Category.JAVA),
  CREATING_AND_USING_ARRAYS("Creating and Using Arrays", Category.JAVA),
  WORKING_WITH_JAVA_DATA_TYPES("Working with Java Data Types", Category.JAVA),
  USING_OPERATORS_AND_DECISION_CONSTRUCTS("Using Operators and Decision Constructs", Category.JAVA),
  JAVA_BASICS("Java Basics", Category.JAVA),
  WORKING_WITH_METHODS_AND_ENCAPSULATION("Working with Methods and Encapsulation", Category.JAVA),
  WORKING_WITH_INHERITANCE("Working with Inheritance", Category.JAVA),
  USING_LOOP_CONSTRUCTS("Using Loop Constructs", Category.JAVA),
  SPRING_CORE("Spring Core", Category.SPRING),
  SECURITY("Security", Category.SPRING),
  SPRING_AOP("Spring AOP", Category.SPRING),
  SPRING_MVC("Spring MVC", Category.SPRING),
  SPRING_SECURITY("Spring Security", Category.SPRING),
  SPRING_BOOT("Spring Boot", Category.SPRING),
  SPRING_BOOT_ACTUATOR("Spring Boot Actuator", Category.SPRING),
  DATA_MANAGEMENT("Data Management", Category.SPRING),
  CONFIGURATION("Configuration", Category.SPRING),
  SPRING_ACTUATOR("Spring Actuator", Category.SPRING),
  SQL_STATEMENTS("SQL Statements", Category.DATABASE),
  DATABASE_UTILITIES("Database Utilities", Category.DATABASE),
  DATABASE_SQL_REFERENCE("Database SQL Reference", Category.DATABASE),
  SYSTEM_PRIVILEGES("System Privileges", Category.DATABASE),
  SEQUENCES("Sequences", Category.DATABASE),
  SUBQUERIES("Subqueries", Category.DATABASE),
  SYNONYMS("Synonyms", Category.DATABASE),
  CONVERSION("Conversion", Category.DATABASE),
  GLOBAL_TEMPORARY_TABLES("Global Temporary Tables", Category.DATABASE),
  DATA_DICTIONARY_AND_DYNAMIC_PERFORMANCE_VIEWS("Data Dictionary and Dynamic Performance Views", Category.DATABASE),
  CONSTRAINT("Constraint", Category.DATABASE),
  ALTER_TABLE("Alter Table", Category.DATABASE),
  ALTER("Alter", Category.DATABASE),
  BETWEEN("Between", Category.DATABASE),
  ORDER_BY("Order By", Category.DATABASE),
  DEFINE("Define", Category.DATABASE),
  VIEW("View", Category.DATABASE),
  TO_DATE("To Date", Category.DATABASE),
  COUNT("Count", Category.DATABASE),
  INTERSECT("Intersect", Category.DATABASE),
  WITH_GRANT("With Grant", Category.DATABASE),
  TIMEZONE("Timezone", Category.DATABASE),
  DATABASE_NORMAL_FORMS("Database Normal Forms", Category.DATABASE),
  PROMPT_USER_FOR_VALUE("Prompt User for Value", Category.DATABASE),
  CREATE_TABLE_AS("Create Table As", Category.DATABASE),
  ROUND("Round", Category.DATABASE),
  NVL("NVL", Category.DATABASE),
  UPDATE("Update", Category.DATABASE),
  TRUNCATE("Truncate", Category.DATABASE),
  JOIN("Join", Category.DATABASE),
  INSTR("Instr", Category.DATABASE),
  LONG("Long", Category.DATABASE),
  DROP_TABLE("Drop Table", Category.DATABASE),
  INSERT("Insert", Category.DATABASE),
  INITCAP("Initcap", Category.DATABASE),
  DELETE("Delete", Category.DATABASE),
  SUBSTR("Substr", Category.DATABASE),
  SQL_REFERENCE("SQL Reference", Category.DATABASE),
  SQL("SQL", Category.DATABASE),
  INDEXES("Indexes", Category.DATABASE),
  INDEX("Index", Category.DATABASE),
  GROUP_BY("Group By", Category.DATABASE),
  CHECK("Check", Category.DATABASE),
  CONVERSIONS("Conversions", Category.DATABASE),
  TOP_N_QUERIES("Top-N Queries", Category.DATABASE),
  MEMORY_STRUCTURES("Memory Structures", Category.DATABASE),
  GRANT_REVOKE_PRIVILEGES("Grant/Revoke Privileges", Category.DATABASE),
  FOREIGN_KEYS("Foreign Keys", Category.DATABASE),
  DATA_MANIPULATION_LANGUAGE("Data Manipulation Language", Category.DATABASE),
  SUBSTITUTION_VARIABLES("Substitution Variables", Category.DATABASE),
  COALESCE("Coalesce", Category.DATABASE),
  AGGREGATE_FUNCTION("Aggregate Function", Category.DATABASE),
  MERGE("Merge", Category.DATABASE),
  ALIAS("Alias", Category.DATABASE),
  WHERE("Where", Category.DATABASE),
  MINUS("Minus", Category.DATABASE),
  TRUNCATE_TABLE("Truncate Table", Category.DATABASE),
  OPERATOR_PRECEDENCES("Operator Precedences", Category.DATABASE),
  NVL_FUNCTION("NVL Function", Category.DATABASE),
  TO_CHAR("To Char", Category.DATABASE),
  GROUPING("Grouping", Category.DATABASE),
  AVG("Avg", Category.DATABASE),
  STRING_LITERALS("String Literals", Category.DATABASE),
  EXPRESSIONS("Expressions", Category.DATABASE),
  LIKE("Like", Category.DATABASE),
  DML_STATEMENTS("DML Statements", Category.DATABASE),
  DATA_DEFINITION_LANGUAGE("Data Definition Language", Category.DATABASE),
  EXTERNAL_TABLES("External Tables", Category.DATABASE),
  EXISTS("Exists", Category.DATABASE),
  ROLLBACK("Rollback", Category.DATABASE),
  HAVING("Having", Category.DATABASE),
  NOT_EXISTS("Not Exists", Category.DATABASE),
  FUNCTIONS("Functions", Category.DATABASE),
  LITERALS("Literals", Category.DATABASE),
  WILDCARDS("Wildcards", Category.DATABASE),
  INTERVAL("Interval", Category.DATABASE),
  JOINS("Joins", Category.DATABASE),
  CASE("Case", Category.DATABASE),
  EXPLICIT_CONVERSION("Explicit Conversion", Category.DATABASE),
  VARCHAR2("Varchar2", Category.DATABASE),
  UNION("Union", Category.DATABASE),
  WHERE_IN("Where In", Category.DATABASE),
  DISTINCT("Distinct", Category.DATABASE),
  RELATIONAL_DATABASES("Relational Databases", Category.DATABASE),
  MANAGING_EXTERNAL_TABLES("Managing External Tables", Category.DATABASE),
  CORRELATED_SUBQUERIES("Correlated Subqueries", Category.DATABASE),
  DATA_DICTIONARY("Data Dictionary", Category.DATABASE),
  DYNAMIC_PERFORMANCE_VIEWS("Dynamic Performance Views", Category.DATABASE),
  GLOBAL_TEMPORARY_TABLE("Global Temporary Table", Category.DATABASE),
  SYSTEM_PRIVILEGE("System Privilege", Category.DATABASE),
  SUBQUERY("Subquery", Category.DATABASE),
  SYNONYM("Synonym", Category.DATABASE),
  CONSTRAINT_TYPE("Constraint Type", Category.DATABASE),
  ALTER_COMMAND("Alter Command", Category.DATABASE),
  BETWEEN_OPERATOR("Between Operator", Category.DATABASE),
  ORDER_BY_CLAUSE("Order By Clause", Category.DATABASE),
  DEFINE_COMMAND("Define Command", Category.DATABASE),
  VIEW_OBJECT("View Object", Category.DATABASE),
  TO_DATE_FUNCTION("To Date Function", Category.DATABASE),
  COUNT_FUNCTION("Count Function", Category.DATABASE),
  INTERSECT_OPERATOR("Intersect Operator", Category.DATABASE),
  WITH_GRANT_OPTION("With Grant Option", Category.DATABASE),
  TIMEZONE_FUNCTION("Timezone Function", Category.DATABASE),
  DATABASE_NORMAL_FORM("Database Normal Form", Category.DATABASE),
  PROMPT_USER("Prompt User", Category.DATABASE),
  CREATE_TABLE("Create Table", Category.DATABASE),
  ROUND_FUNCTION("Round Function", Category.DATABASE),
  UPDATE_STATEMENT("Update Statement", Category.DATABASE),
  TRUNCATE_COMMAND("Truncate Command", Category.DATABASE),
  JOIN_OPERATOR("Join Operator", Category.DATABASE),
  INSTR_FUNCTION("Instr Function", Category.DATABASE),
  LONG_DATA_TYPE("Long Data Type", Category.DATABASE),
  DROP_TABLE_COMMAND("Drop Table Command", Category.DATABASE),
  INSERT_STATEMENT("Insert Statement", Category.DATABASE),
  INITCAP_FUNCTION("Initcap Function", Category.DATABASE),
  DELETE_STATEMENT("Delete Statement", Category.DATABASE),
  SYSDATE("SYSDATE", Category.DATABASE),
  SUBSTR_FUNCTION("Substr Function", Category.DATABASE);

  private final String displayName;
  private final Category category;

  TopicType(String displayName, Category category) {
    this.displayName = displayName;
    this.category = category;
  }

  public static TopicType fromDisplayName(String displayName) {
    if (displayName == null || displayName.isBlank()) {
      return null;
    }
    String normalizedInput = displayName.trim().toLowerCase();
    return Arrays.stream(values())
            .filter(topic -> topic.displayName.toLowerCase().equals(normalizedInput) ||
                    topic.name().toLowerCase().equals(normalizedInput) ||
                    topic.displayName.toLowerCase().contains(normalizedInput))
            .findFirst()
            .orElseGet(() -> {
              log.warn("Unknown topic type: '{}'. Defaulting to SQL.", displayName);
              return SQL;
            });
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
      case "sql" -> isDatabaseRelated();
      default -> false;
    };
  }

  private boolean isJavaRelated() {
    return category == Category.JAVA;
  }

  private boolean isSpringRelated() {
    return category == Category.SPRING;
  }

  private boolean isKubernetesRelated() {
    return category == Category.KUBERNETES;
  }

  private boolean isDatabaseRelated() {
    return category == Category.DATABASE;
  }

  public enum Category {
    JAVA,
    SPRING,
    KUBERNETES,
    DATABASE
  }
}