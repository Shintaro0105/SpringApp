CREATE TABLE user(
    user_id INT AUTO_INCREMENT,
    user_name VARCHAR(255),
    password VARCHAR(255),
    PRIMARY KEY(user_id)
);

desc user;

INSERT INTO user (user_name, password) VALUES
(
    'user1',
    'pass1+'
),
(
    'yama',
    'kawa'
);

select * from user;