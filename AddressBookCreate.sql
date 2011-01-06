    create table "TEST"."ADDRESSES"(
        "ADDRESSID" BIGINT not null generated always as identity,
       "FIRSTNAME" VARCHAR(30) not null,
       "LASTNAME" VARCHAR(30) not null,
       "STREET" VARCHAR(150) not null,
       "CITY" VARCHAR(30) not null,
       "STATE" VARCHAR(2) not null,
       "ZIP" VARCHAR(5) not null,
       "EMAILADDRESS" VARCHAR(50) not null,
       "PHONENUMBER" VARCHAR(30) not null,
        primary key ("ADDRESSID")
    );

    