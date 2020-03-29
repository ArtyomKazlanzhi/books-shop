CREATE TABLE `books` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(255) NOT NULL,
	`description` VARCHAR(255),
	`price` INT,
	PRIMARY KEY (`id`)
);

CREATE TABLE `authors` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`last_name` VARCHAR(255) NOT NULL,
	`first_name` VARCHAR(255) NOT NULL,
	`patronymic` VARCHAR(255),
	PRIMARY KEY (`id`)
);

CREATE TABLE `books_to_authors` (
	`book_id` INT,
	`author_id` INT
);

CREATE TABLE `genres` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `books_to_genres` (
	`book_id` INT,
	`genre_id` INT
);

CREATE TABLE `orders` (
    `id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	`last_name` VARCHAR(255) NOT NULL,
	`address` VARCHAR(255) NOT NULL,
	`quantity` INT NOT NULL,
	`book_id` INT,
	PRIMARY KEY (`id`)
);

ALTER TABLE `books_to_authors` ADD CONSTRAINT `books_to_authors_fk0` FOREIGN KEY (`book_id`) REFERENCES `books`(`id`);

ALTER TABLE `books_to_authors` ADD CONSTRAINT `books_to_authors_fk1` FOREIGN KEY (`author_id`) REFERENCES `authors`(`id`);

ALTER TABLE `books_to_genres` ADD CONSTRAINT `books_to_genres_fk0` FOREIGN KEY (`book_id`) REFERENCES `books`(`id`);

ALTER TABLE `books_to_genres` ADD CONSTRAINT `books_to_genres_fk1` FOREIGN KEY (`genre_id`) REFERENCES `genres`(`id`);

ALTER TABLE `orders` ADD CONSTRAINT `orders_fk0` FOREIGN KEY (`book_id`) REFERENCES `books`(`id`);
