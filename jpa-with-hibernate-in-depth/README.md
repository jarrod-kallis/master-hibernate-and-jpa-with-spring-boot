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

## Entity Inheritance

### SINGLE TABLE
1. Default inheritance if none is provided
2. Creates a column called DTYPE (Discriminator Type). Name can be changed.

**Pros:** Fast, no joins<br>
**Cons:** Bad data integrity due to nullable fields (which shouldn't be) 

```
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "EmployeeType")
public abstract class Employee {
...
```

![Single Table Inheritance](README_images/02-JPA-Entity-Inheritance-Single-Table.png)

### TABLE PER CLASS
1. Creates a table per concrete entity class
2. A union is done to retrieve all rows from all tables, which is not such a big performance hit.

**Pros:** Fast & Good data integrity<br>
**Cons:** Common columns are repeated in both tables.

    select
        employee0_.id as id1_1_,
        employee0_.name as name2_1_,
        employee0_.salary as salary1_2_,
        employee0_.hourly_wage as hourly_w1_3_,
        employee0_.clazz_ as clazz_ 
    from
        ( select
            id,
            name,
            salary,
            null as hourly_wage,
            1 as clazz_ 
        from
            full_time_employee 
        union
        all select
            id,
            name,
            null as salary,
            hourly_wage,
            2 as clazz_ 
        from
            part_time_employee 
    ) employee0_

```
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Employee {
...
```

![Table Per Class Inheritance](README_images/03-JPA-Entity-Inheritance-Table-Per-Class.png)

### JOINED
1. Creates a table for the super class, as well as separate tables for all sub classes

**Pros:** No duplication, good data integrity<br>
**Cons:** Slight performance hit due to joins across tables

    select
        employee0_.id as id1_1_,
        employee0_.name as name2_1_,
        employee0_1_.salary as salary1_2_,
        employee0_2_.hourly_wage as hourly_w1_3_,
        case 
            when employee0_1_.id is not null then 1 
            when employee0_2_.id is not null then 2 
            when employee0_.id is not null then 0 
        end as clazz_ 
    from
        employee employee0_ 
    left outer join
        full_time_employee employee0_1_ 
            on employee0_.id=employee0_1_.id 
    left outer join
        part_time_employee employee0_2_ 
            on employee0_.id=employee0_2_.id
           
```
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Employee {
...
```
![Joined Inheritance](README_images/04-JPA-Entity-Inheritance-Joined.png)

### Mapped Super Class
1. This annotation sits on the parent class
2. The parent class does not have a table representing it, therefore it can't have @Entity annotation
3. The parent class is only used to hold common properties
4. Separate tables are created for each subclass - like TABLE_PER_CLASS
5. You can't query on the parent class. You have to query on the individual sub classes

**Pros:** Fast & Good data integrity<br>
**Cons:** Common columns are repeated in both tables.<br>
          You have to do the join yourself.

I don't see any advantages of this over TABLE_PER_CLASS, because with TABLE_PER_CLASS you can query on the parent class to get data for both sub classes through a union, or you can query the sub classes separately.

```
@MappedSuperclass
public abstract class Employee {
...
```

![Mapped Super Class](README_images/03-JPA-Entity-Inheritance-Table-Per-Class.png)
