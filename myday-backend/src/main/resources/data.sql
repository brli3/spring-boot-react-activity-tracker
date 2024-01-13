# username: admin, password: admin
INSERT INTO user(id, username, password, email, roles, avatar, is_active, registry_time)
VALUES (1, 'admin', '$2a$10$tKrM7LQEu4cWS73DlSn0WOyC0PF42ZdmBksDEWidtT11.Au.YRC9m',
        'admin@email.com', 'ROLE_ADMIN',
        'https://www.pngkey.com/png/full/115-1150152_default-profile-picture-avatar-png-green.png',
        true, '2022-09-22 14:20:10');

# username: user, password: user
INSERT INTO user(id, username, password, email, roles, avatar, is_active, registry_time)
VALUES (2, 'user', '$2a$10$1V0lHt37OWdLJ4MetTll3e9R5hSQKf8HVuxEdAaYi6CjF62HnPIre',
        'user@email.com', 'ROLE_USER',
        'https://www.pngkey.com/png/full/115-1150152_default-profile-picture-avatar-png-green.png',
        true, '2022-09-23 12:10:00');

INSERT INTO measurement(user_id, weight, created_on) VALUES (1, 65, '2022-01-20');
INSERT INTO measurement(user_id, weight, created_on) VALUES (1, 62, '2022-02-10');
INSERT INTO measurement(user_id, weight, created_on) VALUES (1, 67, '2022-03-01');
INSERT INTO measurement(user_id, weight, created_on) VALUES (1, 69, '2022-03-23');

INSERT INTO measurement(user_id, weight, created_on) VALUES (2, 45, '2022-03-20');
INSERT INTO measurement(user_id, weight, created_on) VALUES (2, 42, '2022-04-13');
INSERT INTO measurement(user_id, weight, created_on) VALUES (2, 44, '2022-05-21');
INSERT INTO measurement(user_id, weight, created_on) VALUES (2, 43, '2022-03-23');

INSERT INTO exercise_category(id, name, description, avatar, visible) VALUES (1, 'Cardio', 'Burn fat', 'https://www.kindpng.com/picc/m/208-2085501_running-on-treadmill-cartoon-hd-png-download.png', true);
INSERT INTO exercise_category(id, name, description, avatar, visible) VALUES (2, 'Strength', 'Build mussels', 'https://clipart.world/wp-content/uploads/2021/06/Exercise-clipart-2.png', true);
INSERT INTO exercise_category(id, name, description, avatar, visible) VALUES (3, 'Flexibility', 'Good posture', 'https://st4.depositphotos.com/27811286/38650/v/600/depositphotos_386509266-stock-illustration-happy-cute-little-kid-boy.jpg', true);
INSERT INTO exercise_category(id, name, description, avatar, visible) VALUES (4, 'Stretching', 'Cool down', 'https://freesvg.org/img/1521724945.png', true);
INSERT INTO exercise_category(id, name, description, avatar, visible) VALUES (5, 'Sports', 'Have fun', 'https://marktwain.silverfallsschools.org/wp-content/uploads/sites/4/2022/05/sports-clipart-sport-ball.jpg', true);
INSERT INTO exercise_category(id, name, description, avatar, visible) VALUES (6, 'Leisure', 'Relax', 'https://thumbs.dreamstime.com/b/cartoon-man-deckchair-tablet-juice-his-hand-vector-picture-cartoon-man-deckchair-tablet-128683381.jpg', true);


INSERT INTO exercise(name, category_id, description, avatar, met, visible) VALUES ('Football', 5, 'light effort', 'https://static.thenounproject.com/png/159842-200.png', 2.5, true);
INSERT INTO exercise(name, category_id, description, avatar, met, visible) VALUES ('Fishing', 6, 'from river bank, standing', 'https://icon-library.com/images/fish-icon-png/fish-icon-png-18.jpg', 3.5, true);
INSERT INTO exercise(name, category_id, description, avatar, met, visible) VALUES ('Yoga', 3, 'Hatha', 'https://icon-library.com/images/yoga-exercise-icon-2.png', 2.5, true);
INSERT INTO exercise(name, category_id, description, avatar, met, visible) VALUES ('Yoga', 3, 'Power', 'https://icon-library.com/images/yoga-exercise-icon-2.png', 3.0, true);
INSERT INTO exercise(name, category_id, description, avatar, met, visible) VALUES ('Swimming', 1, 'backstroke, recreational', 'https://icon-library.com/images/swimming-icon/swimming-icon-17.jpg', 4.8, true);
INSERT INTO exercise(name, category_id, description, avatar, met, visible) VALUES ('Swimming', 1, 'butterfly, general', 'https://icon-library.com/images/swimming-icon/swimming-icon-17.jpg', 4.8, true);
INSERT INTO exercise(name, category_id, description, avatar, met, visible) VALUES ('Walking', 1, 'backpacking', 'https://icon-library.com/images/person-walking-icon/person-walking-icon-11.jpg', 7.0, true);
INSERT INTO exercise(name, category_id, description, avatar, met, visible) VALUES ('Basketball', 5, 'non-game, general', 'https://icon-library.com/images/2018/2320061_basketball-icon-basketball-icon-png-png-download.png', 6.0, true);
INSERT INTO exercise(name, category_id, description, avatar, met, visible) VALUES ('Golf', 5, 'walking, carrying clubs', 'https://icon-library.com/images/free-golf-icon/free-golf-icon-4.jpg', 6.0, true);
INSERT INTO exercise(name, category_id, description, avatar, met, visible) VALUES ('Running', 1, 'jogging, general', 'https://icon-library.com/images/exercise-icon/exercise-icon-1.jpg', 7.0, true);
INSERT INTO exercise(name, category_id, description, avatar, met, visible) VALUES ('Running', 1, '4 mph (13 min/mile)', 'https://icon-library.com/images/exercise-icon/exercise-icon-1.jpg', 6.0, true);
INSERT INTO exercise(name, category_id, description, avatar, met, visible) VALUES ('Running', 1, '6 mph (10 min/mile)', 'https://icon-library.com/images/exercise-icon/exercise-icon-1.jpg', 9.8, true);
INSERT INTO exercise(name, category_id, description, avatar, met, visible) VALUES ('Running', 1, '8 mph (7.5 min/mile)', 'https://icon-library.com/images/exercise-icon/exercise-icon-1.jpg', 11.8, true);
INSERT INTO exercise(name, category_id, description, avatar, met, visible) VALUES ('Running', 1, 'stairs, up', 'https://icon-library.com/images/exercise-icon/exercise-icon-1.jpg', 11.8, true);
INSERT INTO exercise(name, category_id, description, avatar, met, visible) VALUES ('Dancing', 1, 'general', 'https://icon-library.com/images/icon-dancing/icon-dancing-6.jpg', 7.3, true);
INSERT INTO exercise(name, category_id, description, avatar, met, visible) VALUES ('Pilate', 3, 'general', 'https://icon-library.com/images/pilates-icon/pilates-icon-12.jpg', 3.0, true);
INSERT INTO exercise(name, category_id, description, avatar, met, visible) VALUES ('Rope skipping', 1, 'general', 'https://icon-library.com/images/jump-rope-icon/jump-rope-icon-7.jpg', 12.3, true);
INSERT INTO exercise(name, category_id, description, avatar, met, visible) VALUES ('Stretching', 4, 'mild', 'https://icon-library.com/images/exercise-icon/exercise-icon-28.jpg', 2.3, true);
INSERT INTO exercise(name, category_id, description, avatar, met, visible) VALUES ('Weight training', 2, 'vigorous effort', 'https://icon-library.com/images/exercise-icon/exercise-icon-17.jpg', 6.0, true);
INSERT INTO exercise(name, category_id, description, avatar, met, visible) VALUES ('Push-ups', 2, 'vigorous effort', 'https://icon-library.com/images/push-icon/push-icon-28.jpg', 8.0, true);
INSERT INTO exercise(name, category_id, description, avatar, met, visible) VALUES ('Sit-ups', 2, 'light effort', 'https://icon-library.com/images/sit-up-icon/sit-up-icon-13.jpg', 2.8, true);



INSERT INTO meal_category(id, name, description, visible) VALUES (1, 'Other', '', true);
INSERT INTO meal_category(id, name, description, visible) VALUES (2, 'Salads', '', true);
INSERT INTO meal_category(id, name, description, visible) VALUES (3, 'Pasta', '', true);
INSERT INTO meal_category(id, name, description, visible) VALUES (4, 'Desserts', '', true);
INSERT INTO meal_category(id, name, description, visible) VALUES (5, 'Sandwiches', '', true);
INSERT INTO meal_category(id, name, description, visible) VALUES (6, 'Breakfast', '', true);
INSERT INTO meal_category(id, name, description, visible) VALUES (7, 'Main dishes', '', true);
INSERT INTO meal_category(id, name, description, visible) VALUES (8, 'Side dishes', '', true);



INSERT INTO meal(name, description, category_id, cal_per_serving, avatar, visible) VALUES ('Mac & Cheese', '', 7, 473, 'https://images.eatthismuch.com/img/336332_missvictoria07_55d337fc-673a-482f-bd39-a940259ce38a.png', true);
INSERT INTO meal(name, description, category_id, cal_per_serving, avatar, visible) VALUES ('Rice Pudding', '', 4, 381, 'https://images.eatthismuch.com/img/906790_Shamarie84_c59b239b-db1a-4f60-a4ba-da1acef0f536.png', true);
INSERT INTO meal(name, description, category_id, cal_per_serving, avatar, visible) VALUES ('Classic BLT', '', 5, 914, 'https://images.eatthismuch.com/img/284786_lavitzman1_cf1529e4-a00b-453c-94d9-e36bdd5020a8.png', true);
INSERT INTO meal(name, description, category_id, cal_per_serving, avatar, visible) VALUES ('Miso Soup', '', 8, 69, 'https://images.eatthismuch.com/img/45136_erin_m_f1c1bdfa-50fe-4f60-95bd-18b0b6b298b7.png', true);
INSERT INTO meal(name, description, category_id, cal_per_serving, avatar, visible) VALUES ('Chicken Pad Thai', '', 7, 414, 'https://images.eatthismuch.com/img/285434_Attycakes_085a6b48-ab24-4ae7-bbd1-cbe2ba7bba98.png', true);
INSERT INTO meal(name, description, category_id, cal_per_serving, avatar, visible) VALUES ('Carbonara', '', 3, 764, 'https://images.eatthismuch.com/img/717890_CalvinStark_fecc862a-e740-4e9d-84d4-f7f666b3805a.png', true);
INSERT INTO meal(name, description, category_id, cal_per_serving, avatar, visible) VALUES ('Tiramisu', '', 4, 162, 'https://images.eatthismuch.com/img/211128_Mz_bfde5cbc-358b-4215-af82-44d78b7f0b2f.png', true);
INSERT INTO meal(name, description, category_id, cal_per_serving, avatar, visible) VALUES ('Chicken Curry', '', 7, 622, 'https://images.eatthismuch.com/img/280824_simmyras_d989e44e-708b-4281-a0bc-110681f588ca.png', true);
INSERT INTO meal(name, description, category_id, cal_per_serving, avatar, visible) VALUES ('Cheddar Omelet', '', 6, 352, 'https://images.eatthismuch.com/img/206118_SunTzu_2aea3094-5f68-4ca9-a3a5-d5fe828594f4.png', true);
INSERT INTO meal(name, description, category_id, cal_per_serving, avatar, visible) VALUES ('Salmon Salad', '', 2, 568, 'https://images.eatthismuch.com/img/356886_AlexanderCary_b49a3056-3836-4461-a012-097bb4ba02c0.png', true);


