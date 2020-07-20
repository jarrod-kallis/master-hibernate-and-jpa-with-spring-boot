# JPA and Hibernate in Depth

### Section 5: JPA and Hibernate in Depth

All code can be found here: https://github.com/in28minutes/jpa-with-hibernate/tree/master/02-jpa-advanced

![Initialise Spring App](README_images/01-Spring-Initializr.png)

## H2 In-Memory Database

Refer to the `spring-jdbc-to-jpa` project's section on H2

## JPA/Hibernate statistics

Add the following to `application.properties`:
```
spring.jpa.properties.hibernate.generate_statistics=true
logging.level.org.hibernate.stat=debug
```
It will display stats like this:
```
Session Metrics {
    15600 nanoseconds spent acquiring 1 JDBC connections;
    0 nanoseconds spent releasing 0 JDBC connections;
    416700 nanoseconds spent preparing 1 JDBC statements;
    659500 nanoseconds spent executing 1 JDBC statements;
    0 nanoseconds spent executing 0 JDBC batches;
    0 nanoseconds spent performing 0 L2C puts;
    0 nanoseconds spent performing 0 L2C hits;
    0 nanoseconds spent performing 0 L2C misses;
    160300 nanoseconds spent executing 1 flushes (flushing a total of 1 entities and 0 collections);
    0 nanoseconds spent executing 0 partial-flushes (flushing a total of 0 entities and 0 collections)
}
```

## SQL Statements in the Console

Add the following to `application.properties`:
```
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
logging.level.org.hibernate.type=trace
```

It will display the SQL statements like this:
```
Hibernate: 
    select
        course0_.id as id1_0_0_,
        course0_.name as name2_0_0_ 
    from
        course course0_ 
    where
        course0_.id=?
o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [BIGINT] - **[1]**
o.h.type.descriptor.sql.BasicExtractor   : extracted value ([name2_0_0_] : [VARCHAR]) - **[Science]**
```

## Reasons For Using Native Queries

If you need to do a mass update
