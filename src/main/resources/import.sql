INSERT INTO `photos`(`description`, `title`, `url`, `visible`) VALUES ('descrizione della foto1','Clouds','https://picsum.photos/id/272/200/300',true)
INSERT INTO `photos`(`description`, `title`, `url`, `visible`) VALUES ('descrizione della foto2','Stairway','https://picsum.photos/id/276/200/300',true)
INSERT INTO `photos`(`description`, `title`, `url`, `visible`) VALUES ('descrizione della foto3','City','https://picsum.photos/id/321/200/300',true)
INSERT INTO `photos`(`description`, `title`, `url`, `visible`) VALUES ('descrizione della foto4','Lighthouse','https://picsum.photos/id/58/200/300',true)
INSERT INTO `photos`(`description`, `title`, `url`, `visible`) VALUES ('descrizione della foto5','Bridge','https://picsum.photos/id/84/200/300',true)
INSERT INTO `photos`(`description`, `title`, `url`, `visible`) VALUES ('descrizione della foto6','Trees in fog','https://picsum.photos/id/95/200/300',true)
INSERT INTO `photos`(`description`, `title`, `url`, `visible`) VALUES ('descrizione della foto7','Sea','https://picsum.photos/id/135/200/300',true)
INSERT INTO `photos`(`description`, `title`, `url`, `visible`) VALUES ('descrizione della foto7','Railway','https://picsum.photos/id/227/200/300',true)
INSERT INTO `photos`(`description`, `title`, `url`, `visible`) VALUES ('descrizione della foto7','Snow','https://picsum.photos/id/260/200/300',true)



INSERT INTO `categories`(`name`) VALUES ('selfie')
INSERT INTO `categories`(`name`) VALUES ('natura')
INSERT INTO `categories`(`name`) VALUES ('tecnologia')
INSERT INTO `categories`(`name`) VALUES ('citta')


INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (1,1)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (1,2)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (1,3)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (1,4)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (2,1)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (3,2)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (3,4)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (4,3)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (5,1)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (6,4)
INSERT INTO `photo_category`(`photo_id`, `category_id`) VALUES (7,4)