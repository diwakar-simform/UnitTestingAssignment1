INSERT INTO course
    (id,name)
VALUES
    (1001,'java'),
    (1002,'sql'),
    (1003,'hibernate'),
    (1004,'microservices'),
    (1005,'PostgreSQL'),
    (1006,'Postman'),
    (1007,'NoSQL'),
    (1008,'MySQL');

INSERT INTO review
    (id,rating,description,course_id)
VALUES
    (4001,'FIVE','Great course',1001),
    (4002,'FOUR','Wonderful Course',1001),
    (4003,'FIVE','Awesome course',1003),
    (4004,'THREE','Good course',1004),
    (4005,'ONE','Worst course',1004),
    (4006,'TWO','Poor course',1004);
