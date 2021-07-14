INSERT INTO `roles` (`id`, `name`) VALUES (NULL, 'ROLE_USER');

INSERT INTO `users` (`id`, `email`, `password`, `user_name`) VALUES (NULL, 'test@test.pl', '$2y$12$rUXkHkIyCNL/MhysC95mKugBXgwD59Gqb6u/cj3tMJc5gC0m6MhIC ', 'test');
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (1, 1);