INSERT INTO `photos`(`description`, `title`, `url`, `visible`) VALUES ('descrizione della foto1','Clouds','https://picsum.photos/id/272/200/300',true)
INSERT INTO `photos`(`description`, `title`, `url`, `visible`) VALUES ('descrizione della foto2','Stairway','https://picsum.photos/id/276/200/300',true)
INSERT INTO `photos`(`description`, `title`, `url`, `visible`) VALUES ('descrizione della foto3','City','https://picsum.photos/id/321/200/300',true)
INSERT INTO `photos`(`description`, `title`, `url`, `visible`) VALUES ('descrizione della foto4','Lighthouse','https://picsum.photos/id/58/200/300',true)
INSERT INTO `photos`(`description`, `title`, `url`, `visible`) VALUES ('descrizione della foto5','Bridge','https://picsum.photos/id/84/200/300',true)
INSERT INTO `photos`(`description`, `title`, `url`, `visible`) VALUES ('descrizione della foto6','Trees in fog','https://picsum.photos/id/95/200/300',true)
INSERT INTO `photos`(`description`, `title`, `url`, `visible`) VALUES ('descrizione della foto7','Sea','https://picsum.photos/id/135/200/300',true)
INSERT INTO `photos`(`description`, `title`, `url`, `visible`) VALUES ('descrizione della foto7','Railway','https://picsum.photos/id/227/200/300',true)
INSERT INTO `photos`(`description`, `title`, `url`, `visible`) VALUES ('descrizione della foto7','Snow','https://picsum.photos/id/260/200/300',true)
INSERT INTO `photos`(`description`, `title`, `url`, `visible`) VALUES ('descrizione della foto8','Train','https://picsum.photos/id/363/200/300',true)



INSERT INTO `categories`(`name`,`description`) VALUES ('Black and White',"black and white photos")
INSERT INTO `categories`(`name`,`description`) VALUES ('Nature','Photos with nature subject')
INSERT INTO `categories`(`name`,`description`) VALUES ('Technology',"Photos with technological focus")
INSERT INTO `categories`(`name`,`description`) VALUES ('Urban','Photos with cities subject')


INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (1,1)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (1,2)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (2,1)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (2,4)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (3,1)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (3,4)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (4,1)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (4,4)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (5,4)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (6,2)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (7,2)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (8,2)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (8,4)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (9,1)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (9,2)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (10,1)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (10,3)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (10,4)

INSERT INTO `users`(`id`,`age`, `email`, `first_name`, `last_name`, `password`) VALUES (1,18,'john@email.com','John','Doe','{noop}john')
INSERT INTO `users`(`id`,`age`, `email`, `first_name`, `last_name`, `password`) VALUES (2,31,'jane@email.com','Jane','Doe','{noop}jane')
INSERT INTO `users`(`id`,`age`, `email`, `first_name`, `last_name`, `password`) VALUES (3,34,'pippo@email.com','Pippo','Pippo','{noop}Pippo')

INSERT INTO `roles`(`id`, `name`) VALUES (1,'ADMIN')
INSERT INTO `roles`(`id`, `name`) VALUES (2,'USER')

INSERT INTO `users_roles`(`user_id`, `roles_id`) VALUES (1,1)
INSERT INTO `users_roles`(`user_id`, `roles_id`) VALUES (2,2)
INSERT INTO `users_roles`(`user_id`, `roles_id`) VALUES (3,2)