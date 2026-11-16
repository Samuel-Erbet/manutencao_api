create table maintenance_ticket(

    id bigint auto_increment primary key,
    description VARCHAR(200) NOT NULL,
    location VARCHAR(50) NOT NULL,
    title VARCHAR(50) NOT NULL,
    resolution VARCHAR(255),
    solved varchar(10),
    status varchar(20) NOT NULL DEFAULT "ABERTO"

);