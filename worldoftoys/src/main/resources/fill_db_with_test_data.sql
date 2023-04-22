USE world_of_toys;
SET FOREIGN_KEY_CHECKS=0;

TRUNCATE TABLE age_category;
TRUNCATE TABLE brand_category;
TRUNCATE TABLE gender_category;
TRUNCATE TABLE origin_category;
TRUNCATE TABLE type_category;
TRUNCATE TABLE product;
TRUNCATE TABLE product_age_category;
TRUNCATE TABLE blog_post;

INSERT INTO age_category(id, name)
VALUES (1, '0-1 роки'),
       (2, '2-3 роки'),
       (3, '4-6 років'),
       (4, '7-10 років'),
       (5, '10-13 років'),
       (6, 'від 14 років');
       
INSERT INTO brand_category(id, name)
VALUES (1, 'DJECO'),
       (2, 'Chicco'),
       (3, 'Fisher-Price'),
       (4, 'LOL Surprise'),
       (5, 'Barbie'),
       (6, 'Corolle'),
       (7, 'Smoby'),
       (8, 'Shantou'),
       (9, 'Jinxing'),
       (10, 'Spin Master'),
       (11, 'Dragons'),
       (12, 'Infantino'),
       (13, 'Chi Chi Love'),
       (14, 'Wizarding World'),
       (15, 'Baby Born');
       
INSERT INTO gender_category(id, name)
VALUES (1, 'для дівчаток'),
       (2, 'для хлопчиків'),
       (3, 'унісекс');
       
INSERT INTO origin_category(id, name)
VALUES (1, 'Китай'),
       (2, 'Україна'),
       (3, 'Німечина'),
       (4, 'Польша'),
       (5, 'Іспанія'),
       (6, 'Чехія'),
       (7, 'Угорщина');
       
INSERT INTO type_category(id, name)
VALUES (1, 'Ляльки та пупси'),
       (2, 'Конструктори'),
       (3, 'Машинки, моделі техніка'),
       (4, 'Настільні ігри та пазли'),
       (5, 'Іграшкова зброя'),
       (6, 'Роботи та трансформери'),
       (7, 'М\'які іграшки');
       
INSERT INTO product(id, name, slug, description, image, price, origin_id, gender_id, brand_id, type_id)
VALUES (1, 'Лялька Клаймбер і кошеня Сієста', 'lalka-klajmber-i-kosena-siesta',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/552024_1.jpg',
        650, 1, 1, 1, 1),
       (2, 'Лялька Олениця Деніса', 'lalka-olenica-denisa',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/542030_1.jpg',
        850, 2, 1, 2, 1),
        (3, 'Лялька Русалочка Маура', 'lalka-rusalocka-maura',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/563036_1.jpg',
        350, 3, 1, 3, 1),
        (4, 'Лялька Barbie Color reveal Вечірка', 'lalka-barbie-color-reveal-vecirka',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/568737_0.jpg',
        899, 4, 1, 5, 1),
        (5, 'Пупс NBB Simba 30 см з аксесуарами', 'pups-nbb-simba-30-sm-z-aksesuarami',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products_photos/size_400/520990_2.jpg',
        1286, 2, 1, 4, 1),
        (6, 'Пупс Чарівна дівчинка', 'pups-carivna-divcinka',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/566148_1.jpg',
        3200, 4, 1, 1, 1),
        (7, 'Конструктор Battat Архітектор 54', 'konstruktor-battat-arhitektor-54',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/533517_1.jpg',
        1199, 4, 2, 6, 2),
        (8, 'Конструктор Mega Bloks Потяг', 'konstruktor-mega-bloks-potag',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/519406_1.jpg',
        949, 5, 2, 7, 2),
        (9, 'Конструктор Animal', 'konstruktor-animal',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/550514_1.jpg',
        549, 5, 3, 8, 2),
        (10, 'Конструктор Вантажівка', 'konstruktor-vantazivka',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/569946_1.jpg',
        750, 6, 3, 9, 2),
        (11, 'Машинка Cars Тачки 3', 'masinka-cars-tacki-3',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/569946_1.jpg',
        200, 7, 2, 10, 3),
        (12, 'Машинка Pre cool', 'masinka-pre-cool',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/569946_1.jpg',
        1500, 6, 2, 11, 3),
        (13, 'Пожежна машина з драбиною', 'pozezna-masina-z-drabinou',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/558582_1.jpg',
        500, 5, 3, 11, 3),
        (14, 'Товарний поїзд з ландшафтом', 'tovarnij-poizd-z-landsaftom',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/527606_1.jpg',
        6200, 3, 3, 12, 3),
        (15, 'Монополія Неперевершений електронний банкін', 'monopolia-nepereversenij-elektronnij-bankin',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/558285_1.jpg',
        1999, 4, 3, 13, 4),
        (16, 'Noris Морський бій', 'noris-morskij-bij',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/555300_1.jpg',
        999, 5, 3, 14, 4),
        (17, 'Гра-пазл Протилежності', 'gra-pazl-protileznosti',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/353555_1.jpg',
        350, 6, 2, 15, 4),
        (18, 'Пазл Професії', 'pazl-profesii',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/558973_1.jpg',
        450, 6, 3, 7, 4),
        (19, 'Пазл У дорозі', 'pazl-u-dorozi',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/560163_1.jpg',
        899, 4, 3, 9, 4),
        (20, 'Пазли Цукерки 1000 елементів', 'pazli-cukerki-1000-elementiv',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/535810_1.jpg',
        899, 4, 1, 11, 4),
        (21, 'Бластер іграшковий', 'blaster-igraskovij',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/557835_1.jpg',
        899, 5, 2, 10, 5),
        (22, 'Ігрова електронна мішень', 'igrova-elektronna-misen',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/559368_1.jpg',
        99, 5, 2, 12, 5),
        (23, 'Швидкострільний бластер', 'svidkostrilnij-blaster',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/559850_1.jpg',
        1799, 1, 2, 13, 5),
        (24, 'Робот-собака', 'robot-sobaka',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/559295_1.jpg',
        2999, 5, 3, 14, 6),
        (25, 'Робот-трансформер', 'robot-transformer',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/561660_1.jpg',
        2999, 1, 2, 15, 6),
        (26, 'Ведмідь білий', 'vedmid-bilij',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/547373_1.jpg',
        299, 1, 3, 1, 7),
        (27, 'Панда', 'panda',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/556114_1.jpg',
        86, 1, 3, 1, 7),
        (28, 'Пінгвін', 'pingvin',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products_photos/size_400/556117_2.jpg',
        86, 2, 3, 11, 7),
        (29, 'Цуценя', 'cucena',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/549817_1.jpg',
        850, 2, 3, 11, 7),
        (30, 'Єдиноріг', 'edinorig',
		'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi!', 
		'https://bi.ua/uploaded-images/products/size_400/555562_1.jpg',
        850, 2, 3, 12, 7);
        
INSERT INTO product_age_category(products_id, age_category_id)
VALUES (1, 2),
       (1, 3),
       (1, 4),
       (2, 2),
       (2, 3),
       (3, 2),
       (3, 3),
       (4, 2),
       (5, 2),
       (6, 1),
       (6, 2),
       (7, 4),
       (7, 5),
       (8, 5),
       (9, 4),
       (9, 5),
       (10, 4),
       (10, 5),
       (11, 2),
       (11, 3),
       (11, 4),
       (12, 4),
       (12, 3),
       (13, 3),
       (14, 3),
       (14, 4),
       (15, 6),
       (15, 7),
       (16, 6),
       (16, 7),
       (17, 6),
       (17, 7),
       (18, 3),
       (18, 2),
       (19, 2),
       (19, 3),
       (20, 3),
       (20, 4),
       (21, 3),
       (21, 4),
       (22, 4),
       (22, 3),
       (23, 3),
       (23, 4),
       (23, 5),
       (24, 6),
       (24, 7),
       (25, 5),
       (25, 4),
       (26, 2),
       (26, 3),
       (26, 4),
       (27, 4),
       (27, 3),
       (27, 2),
       (28, 2),
       (28, 3),
       (28, 4),
       (29, 4),
       (29, 3),
       (29, 2),
       (30, 2),
       (30, 3);

INSERT INTO blog_post(id, title, slug, image, content)
VALUES (1, '10 найпопулярніших іграшок 2023 року', '10-naypopulyarnishykh-ihrashok-2023-roku', 'https://karapuzov.com.ua/image/catalog/blog/2020-November/razvivajuschie-igrushki/razvivayuschie-igrushki-dlya-malyshey-blog-karapuzov.jpg',
        'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi
Lorem, ipsum dolor sit amet consectetur adipisicing elit. Dignissimos, perferendis, sunt! Eum est dolorum vero sequi iure mollitia odit nam consequatur corporis? Rerum doloribus quis tempore nesciunt quidem quia sit.
Lorem ipsum dolor sit amet consectetur adipisicing elit. Commodi molestias, doloremque assumenda est nesciunt! Pariatur, dolorum, molestiae maiores nihil exercitationem, quos eos culpa fugiat quaerat magni expedita iusto impedit aut.
Lorem, ipsum dolor, sit amet consectetur adipisicing elit. Maiores molestiae laborum rerum praesentium iste dolores, harum, quis, voluptate minus quod adipisci. Quasi esse necessitatibus, optio vitae explicabo voluptatem autem et.'),
       (2, 'Як вибрати іграшку відповідно до віку вашої дитини', 'yak-vybraty-ihrashku-vidpovidno-do-viku-vashoyi-dytyny', 'https://t1.ua/photos/articles/2019/11/35795_1_1097.jpg',
        'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi
Lorem, ipsum dolor sit amet consectetur adipisicing elit. Dignissimos, perferendis, sunt! Eum est dolorum vero sequi iure mollitia odit nam consequatur corporis? Rerum doloribus quis tempore nesciunt quidem quia sit.
Lorem ipsum dolor sit amet consectetur adipisicing elit. Commodi molestias, doloremque assumenda est nesciunt! Pariatur, dolorum, molestiae maiores nihil exercitationem, quos eos culpa fugiat quaerat magni expedita iusto impedit aut.
Lorem, ipsum dolor, sit amet consectetur adipisicing elit. Maiores molestiae laborum rerum praesentium iste dolores, harum, quis, voluptate minus quod adipisci. Quasi esse necessitatibus, optio vitae explicabo voluptatem autem et.'),
       (3, 'Історія найпопулярнішої іграшки в світі', 'istoriya-naypopulyarnishoyi-ihrashky-v-sviti','https://www.iphones.ru/wp-content/uploads/2018/08/lego_best_hobby-14.jpg',
        'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi
Lorem, ipsum dolor sit amet consectetur adipisicing elit. Dignissimos, perferendis, sunt! Eum est dolorum vero sequi iure mollitia odit nam consequatur corporis? Rerum doloribus quis tempore nesciunt quidem quia sit.
Lorem ipsum dolor sit amet consectetur adipisicing elit. Commodi molestias, doloremque assumenda est nesciunt! Pariatur, dolorum, molestiae maiores nihil exercitationem, quos eos culpa fugiat quaerat magni expedita iusto impedit aut.
Lorem, ipsum dolor, sit amet consectetur adipisicing elit. Maiores molestiae laborum rerum praesentium iste dolores, harum, quis, voluptate minus quod adipisci. Quasi esse necessitatibus, optio vitae explicabo voluptatem autem et.'),
       (4, 'Як іграшки впливають на розвиток дитини', 'yak-ihrashky-vplyvayut-na-rozvytok-dytyny','https://zhzh.info/_pu/127/72415546.jpg',
        'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi
Lorem, ipsum dolor sit amet consectetur adipisicing elit. Dignissimos, perferendis, sunt! Eum est dolorum vero sequi iure mollitia odit nam consequatur corporis? Rerum doloribus quis tempore nesciunt quidem quia sit.
Lorem ipsum dolor sit amet consectetur adipisicing elit. Commodi molestias, doloremque assumenda est nesciunt! Pariatur, dolorum, molestiae maiores nihil exercitationem, quos eos culpa fugiat quaerat magni expedita iusto impedit aut.
Lorem, ipsum dolor, sit amet consectetur adipisicing elit. Maiores molestiae laborum rerum praesentium iste dolores, harum, quis, voluptate minus quod adipisci. Quasi esse necessitatibus, optio vitae explicabo voluptatem autem et.'),
       (5, '5 ігрових ідей для активного відпочинку з дітьми', '5-ihrovykh-idey-dlya-aktyvnoho-vidpochynku-z-ditmy','https://img.tsn.ua/cached/213/tsn-044a03ab1c6615c0753d2de60e24b711/thumbs/1200x630/a3/e8/af41912fcca6928330d037904237e8a3.jpeg',
        'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi
Lorem, ipsum dolor sit amet consectetur adipisicing elit. Dignissimos, perferendis, sunt! Eum est dolorum vero sequi iure mollitia odit nam consequatur corporis? Rerum doloribus quis tempore nesciunt quidem quia sit.
Lorem ipsum dolor sit amet consectetur adipisicing elit. Commodi molestias, doloremque assumenda est nesciunt! Pariatur, dolorum, molestiae maiores nihil exercitationem, quos eos culpa fugiat quaerat magni expedita iusto impedit aut.
Lorem, ipsum dolor, sit amet consectetur adipisicing elit. Maiores molestiae laborum rerum praesentium iste dolores, harum, quis, voluptate minus quod adipisci. Quasi esse necessitatibus, optio vitae explicabo voluptatem autem et.'),
       (6, 'Новинки іграшок у 2023 році', 'novynky-ihrashok-u-2023-rotsi','https://tsikavi-fakty.com.ua/wp-content/uploads/2020/02/6f2aacf7d87b674d21b29614559e7f39.jpg',
        'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi
Lorem, ipsum dolor sit amet consectetur adipisicing elit. Dignissimos, perferendis, sunt! Eum est dolorum vero sequi iure mollitia odit nam consequatur corporis? Rerum doloribus quis tempore nesciunt quidem quia sit.
Lorem ipsum dolor sit amet consectetur adipisicing elit. Commodi molestias, doloremque assumenda est nesciunt! Pariatur, dolorum, molestiae maiores nihil exercitationem, quos eos culpa fugiat quaerat magni expedita iusto impedit aut.
Lorem, ipsum dolor, sit amet consectetur adipisicing elit. Maiores molestiae laborum rerum praesentium iste dolores, harum, quis, voluptate minus quod adipisci. Quasi esse necessitatibus, optio vitae explicabo voluptatem autem et.'),
       (7, '5 іграшок, які допоможуть розвинути творчість та уяву дитини','5-ihrashok-yaki-dopomozhut-rozvynuty-tvorchist-ta-uyavu-dytyny', 'https://vikna.if.ua/assets/gallery/2019/11/12/104062/44412458__large.jpg',
        'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi
Lorem, ipsum dolor sit amet consectetur adipisicing elit. Dignissimos, perferendis, sunt! Eum est dolorum vero sequi iure mollitia odit nam consequatur corporis? Rerum doloribus quis tempore nesciunt quidem quia sit.
Lorem ipsum dolor sit amet consectetur adipisicing elit. Commodi molestias, doloremque assumenda est nesciunt! Pariatur, dolorum, molestiae maiores nihil exercitationem, quos eos culpa fugiat quaerat magni expedita iusto impedit aut.
Lorem, ipsum dolor, sit amet consectetur adipisicing elit. Maiores molestiae laborum rerum praesentium iste dolores, harum, quis, voluptate minus quod adipisci. Quasi esse necessitatibus, optio vitae explicabo voluptatem autem et.'),
       (8, 'Іграшки для вивчення іноземної мови', 'ihrashky-dlya-vyvchennya-inozemnoyi-movy','https://arhpress.ru/uploads/posts/2018-11/1541022564_7843.jpg',
        'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi
Lorem, ipsum dolor sit amet consectetur adipisicing elit. Dignissimos, perferendis, sunt! Eum est dolorum vero sequi iure mollitia odit nam consequatur corporis? Rerum doloribus quis tempore nesciunt quidem quia sit.
Lorem ipsum dolor sit amet consectetur adipisicing elit. Commodi molestias, doloremque assumenda est nesciunt! Pariatur, dolorum, molestiae maiores nihil exercitationem, quos eos culpa fugiat quaerat magni expedita iusto impedit aut.
Lorem, ipsum dolor, sit amet consectetur adipisicing elit. Maiores molestiae laborum rerum praesentium iste dolores, harum, quis, voluptate minus quod adipisci. Quasi esse necessitatibus, optio vitae explicabo voluptatem autem et.'),
       (9, 'Як створювати розвивальні ігри для дітей вдома', 'yak-stvoryuvaty-rozvyvalni-ihry-dlya-ditey-vdoma','https://smarttoys.com.ua/upload/iblock/07c/Igrashki-dlya-khlopchikiv.jpg',
        'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi
Lorem, ipsum dolor sit amet consectetur adipisicing elit. Dignissimos, perferendis, sunt! Eum est dolorum vero sequi iure mollitia odit nam consequatur corporis? Rerum doloribus quis tempore nesciunt quidem quia sit.
Lorem ipsum dolor sit amet consectetur adipisicing elit. Commodi molestias, doloremque assumenda est nesciunt! Pariatur, dolorum, molestiae maiores nihil exercitationem, quos eos culpa fugiat quaerat magni expedita iusto impedit aut.
Lorem, ipsum dolor, sit amet consectetur adipisicing elit. Maiores molestiae laborum rerum praesentium iste dolores, harum, quis, voluptate minus quod adipisci. Quasi esse necessitatibus, optio vitae explicabo voluptatem autem et.'),
       (10, 'Найбільш креативні іграшки для розвитку логіки у дітей', 'naybilsh-kreatyvni-ihrashky-dlya-rozvytku-lohiky-u-ditey','https://www.hitfm.ua/static/img/content/info/7/32/1200x630.jpg',
        'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatum in, asperiores. Mollitia voluptate, fuga facilis quod, quidem iure blanditiis soluta veniam nobis praesentium ex quo ut officiis consequuntur sapiente nisi
Lorem, ipsum dolor sit amet consectetur adipisicing elit. Dignissimos, perferendis, sunt! Eum est dolorum vero sequi iure mollitia odit nam consequatur corporis? Rerum doloribus quis tempore nesciunt quidem quia sit.
Lorem ipsum dolor sit amet consectetur adipisicing elit. Commodi molestias, doloremque assumenda est nesciunt! Pariatur, dolorum, molestiae maiores nihil exercitationem, quos eos culpa fugiat quaerat magni expedita iusto impedit aut.
Lorem, ipsum dolor, sit amet consectetur adipisicing elit. Maiores molestiae laborum rerum praesentium iste dolores, harum, quis, voluptate minus quod adipisci. Quasi esse necessitatibus, optio vitae explicabo voluptatem autem et.');