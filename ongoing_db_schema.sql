DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

create extension if not exists unaccent;

create or replace function remove_accents(field varchar)
	returns text
as
$BODY$
	select unaccent(field);
$BODY$
language sql
immutable;

CREATE TABLE user_gender
(
    id integer PRIMARY KEY NOT NULL,
    name character varying(256) NOT NULL
);

CREATE TABLE users
(
    id integer PRIMARY KEY NOT NULL,
    email character varying(256) NOT NULL,
    pseudo character varying(256) NOT NULL,
    password character varying(256) NOT NULL,
    firstname character varying(256) NOT NULL,
    lastname character varying(256) NOT NULL,
    phone character varying(10),
    birthday timestamp,
    sign_up timestamp NOT NULL,
    push_notification boolean,
    active_localisation boolean,
    display_real_name boolean,
    fk_id_gender integer REFERENCES user_gender(id)
);

CREATE TABLE follow_relation
(
    fk_id_follower integer REFERENCES users(id) NOT NULL,
    fk_id_user integer REFERENCES users(id) NOT NULL,
    PRIMARY KEY(fk_id_follower, fk_id_user)
);

CREATE TABLE animal_gender
(
    id integer PRIMARY KEY NOT NULL,
    name character varying(256) NOT NULL
);

CREATE TABLE animal_type
(
    id integer PRIMARY KEY NOT NULL,
    name character varying(256) NOT NULL
);

CREATE TABLE animal_temper
(
    id integer PRIMARY KEY NOT NULL,
    name character varying(256) NOT NULL
);

CREATE TABLE race
(
    id integer PRIMARY KEY NOT NULL,
    name character varying(256) NOT NULL,
    fk_id_type integer REFERENCES animal_type(id) NOT NULL
);

CREATE TABLE animal
(
    id integer PRIMARY KEY NOT NULL,
    name character varying(256) NOT NULL,
    birthday timestamp,
    fk_id_owner integer REFERENCES users(id) NOT NULL,
    fk_id_gender integer REFERENCES animal_gender(id) NOT NULL,
    fk_id_type integer REFERENCES animal_type(id) NOT NULL,
    fk_id_race integer REFERENCES race(id),
    fk_id_temper integer REFERENCES animal_temper(id)
);

CREATE TABLE event_type
(
    id integer PRIMARY KEY NOT NULL,
    name character varying(256) NOT NULL
);

CREATE TABLE event
(
    id integer PRIMARY KEY NOT NULL,
    name character varying(256) NOT NULL,
    description character varying(256) NOT NULL,
    localisation character varying(256) NOT NULL,
    date timestamp NOT NULL,
    fk_id_owner integer REFERENCES users (id) NOT NULL,
    fk_id_type integer REFERENCES event_type (id) NOT NULL
);

CREATE TABLE event_member
(
    fk_id_member integer REFERENCES users(id) NOT NULL,
    fk_id_event integer REFERENCES event(id) NOT NULL,
    PRIMARY KEY(fk_id_member, fk_id_event)
);

CREATE TABLE tag
(
    id integer PRIMARY KEY NOT NULL,
    name character varying(256) NOT NULL
);

CREATE TABLE event_tags
(
    fk_id_tag integer REFERENCES tag(id) NOT NULL,
    fk_id_event integer REFERENCES event(id) NOT NULL,
    PRIMARY KEY(fk_id_tag, fk_id_event)
);

CREATE SEQUENCE user_gender_sequence START 1;
CREATE SEQUENCE users_sequence START 1;
CREATE SEQUENCE animal_gender_sequence START 1;
CREATE SEQUENCE animal_type_sequence START 1;
CREATE SEQUENCE race_sequence START 1;
CREATE SEQUENCE animal_sequence START 1;
CREATE SEQUENCE event_type_sequence START 1;
CREATE SEQUENCE event_sequence START 1;
CREATE SEQUENCE tag_sequence START 1;
CREATE SEQUENCE animal_temper_sequence START 1;

-- INSERT INTO table(row)
-- VALUES
-- (),
-- (),
-- ()
-- ;

INSERT INTO user_gender(id, name)
VALUES
(1, 'Femme'),
(2, 'Homme'),
(3, 'Autre')
;

INSERT INTO animal_gender(id, name)
VALUES
(1, 'Femelle'),
(2, 'Mâle')
;

INSERT INTO animal_type(id, name)
VALUES
(1, 'Chien'),
(2, 'Chat')
;

INSERT INTO event_type(id, name)
VALUES
(1, 'Promenade'),
(2, 'Randonnée'),
(3, 'Rencontre pour jouer'),
(4, 'Concours amical'),
(5, 'Pique nique'),
(6, 'Sortie en mer')
;

INSERT INTO animal_temper(id, name)
VALUES
(1, 'DISTRACTED'),
(2, 'AGGRESSIVE'),
(3, 'SOFT'),
(4, 'COMPETITOR'),
(5, 'FUN'),
(6, 'ASOCIAL'),
(7, 'TALKATIVE'),
(8, 'RESERVED'),
(9, 'BRAVE'),
(10, 'CALM'),
(11, 'BOOSTED'),
(12, 'ANGRY'),
(13, 'CURIOUS'),
(14, 'DELICATE'),
(15, 'ANXIOUS'),
(16, 'DISCIPLINED'),
(17, 'ATTENTIVE'),
(18, 'EXPLORER'),
(19, 'LAZY'),
(20, 'FAITHFUL'),
(21, 'FOUGUEUX'),
(22, 'GAFFEUR'),
(23, 'GOURMAND'),
(24, 'IMPATIENT'),
(25, 'IMPUDENT'),
(26, 'INTELLIGENT'),
(27, 'JEALOUS'),
(28, 'PLAYER'),
(29, 'LEADER'),
(30, 'AWKWARD'),
(31, 'SUSPICIOUS'),
(32, 'NERVOUS'),
(33, 'FOLLOWER'),
(34, 'OBSERVER'),
(35, 'FEARFUL'),
(36, 'SUSCEPTIBLE'),
(37, 'SHY')
;

INSERT INTO race(id, name, fk_id_type)
VALUES
(1,'Aïdi Ou Chien De L’atlas',1),
(2,'Akita Inu',1),
(3,'Airedale-terrier',1),
(4,'American Staaffordshire Terrier',1),
(5,'Barzoï - Lévrier Russe',1),
(6,'Basset Artesien-normand',1),
(7,'Basset Hound',1),
(8,'Bearded Collie Ou Collie À Barbe',1),
(9,'Beauceron - Berger De Beauce',1),
(10,'Bedlington Terrier',1),
(11,'Bergamasque',1),
(12,'Berger Allemand',1),
(13,'Berger Australien',1),
(14,'Berger Belge Laekenois',1),
(15,'Berger Catalan',1),
(16,'Berger Blanc Suisse',1),
(17,'Berger D’anatolie',1),
(18,'Berger Croate',1),
(19,'Berger D’écosse a Poil Ras',1),
(20,'Berger D’écosse a Poil Long - Colley',1),
(21,'Berger De Hollande',1),
(22,'Berger D’islande',1),
(23,'Berger De Picardie',1),
(24,'Berger De Karst',1),
(25,'Berger Des Shetland',1),
(26,'Berger De Vallée',1),
(27,'Berger Espagnol Ou Mastin Espagnol',1),
(28,'Berger Des Tatras',1),
(29,'Bobtail',1),
(30,'Bichon a Poil Frise',1),
(31,'Border Collie',1),
(32,'Border - Terrier',1),
(33,'Bouvier Australien',1),
(34,'Bouledogue Francais',1),
(35,'Bouvier D’appenzell - Bouvier Des Alpes',1),
(36,'Bouvier Bernois',1),
(37,'Bouvier De L’entlebuch',1),
(38,'Bouvier De L’alentejo',1),
(39,'Boxer',1),
(40,'Bouvier Des Flandres',1),
(41,'Braque De Weimar',1),
(42,'Briard',1),
(43,'Bull-mastiff',1),
(44,'Bull Dog Ou Bouledogue Anglais',1),
(45,'Bull-terrier',1),
(46,'Cairn-terrier',1),
(47,'Cane-corso',1),
(48,'Caniche',1),
(49,'Cao Da Serra Da Estrela',1),
(50,'Cao De Agua - Chien D’eau Portugais',1),
(51,'Cao De Castro Laboreiro',1),
(52,'Carlin',1),
(53,'Cavalier King-charles',1),
(54,'Charplaninatz',1),
(55,'Chien Chinois a Toupet',1),
(56,'Chien De Canaan',1),
(57,'Chien Esquimau',1),
(58,'Chien Léopard Catahoula',1),
(59,'Chien Loup Tchéque',1),
(60,'Cocker Americain',1),
(61,'Cocker-spaniel',1),
(62,'Corgi',1),
(63,'Coton De Tulear',1),
(64,'Dalmatien',1),
(65,'Dandie-dinmont -terrier',1),
(66,'Dobermann',1),
(67,'Dogo-canario Ou Dogue Des Canaries',1),
(68,'Dogue Allemand',1),
(69,'Dogue De Bordeaux',1),
(70,'Dogue Du Tibet',1),
(71,'English Springer Spaniel',1),
(72,'Epagneul Breton',1),
(73,'Epagneul King-charles',1),
(74,'Eurasier',1),
(75,'Fila Brasileiro',1),
(76,'Fox-terrier a Poil Dur',1),
(77,'Fox-terrier a Poil Lisse',1),
(78,'Galgo Ou Levrier Espagnol',1),
(79,'Golden-retriever',1),
(80,'Grand Bouvier Suisse',1),
(81,'Greyhound - Lévrier Anglais À Poil Ras',1),
(82,'Groenendael Ou Berger Belge À Poil Long',1),
(83,'Groenlandais',1),
(84,'Hokkaidoken',1),
(85,'Hovawart',1),
(86,'Husky Sibérien',1),
(87,'Irish - Terrier',1),
(88,'Jack-russell Terrier',1),
(89,'Keeshond Ou Spitz-loup',1),
(90,'Kelpie',1),
(91,'Kerry Blue Terrier',1),
(92,'Komondor Ou Bouvier Hongrois',1),
(93,'Kuwatz',1),
(94,'Kyushu',1),
(95,'Labrador',1),
(96,'Labrit Ou Berger De Pyrénées',1),
(97,'Lakeland Terrier',1),
(98,'Landseer',1),
(99,'Lapinporokoira',1),
(100,'Chien De Rennes De Laponie',1),
(101,'Lapphund',1),
(102,'Chien De Garde Des Lapons',1),
(103,'Leonberg',1),
(104,'Lhassa-apso',1),
(105,'Malamutes',1),
(106,'Malinois',1),
(107,'Manchester - Terrier',1),
(108,'Maremmano Ou Berger Des Abruzzes',1),
(109,'Mastiff Ou Dogue Anglais',1),
(110,'Mâtin De Naples',1),
(111,'Mudi',1),
(112,'Norfolk Terrier',1),
(113,'Norrbottenspets Ou Spitz De Norbotten',1),
(114,'Norsk Buhund Ou Berger Norvégien',1),
(115,'Norwich Terrier',1),
(116,'Papillon',1),
(117,'Pekinois',1),
(118,'Pinscher Autrichien a Poil Court',1),
(119,'Puli',1),
(120,'Pumi',1),
(121,'Pyrénéen - Chien De Montagne Des Pyrénées',1),
(122,'Rottweiller Ou Bouvier Allemand',1),
(123,'Saint -bernard a Poil Court',1),
(124,'Saint-bernard a Poil Long',1),
(125,'Samoyède',1),
(126,'Sanshu',1),
(127,'Schnauzer Géant',1),
(128,'Schnauzer Moyen',1),
(129,'Schnauzer Nain',1),
(130,'Scottish Terrier',1),
(131,'Shar-pei - Chien De Combat Chinois',1),
(132,'Shiba Inu',1),
(133,'Shih - Tzu',1),
(134,'Staffordshire Bull Terrier',1),
(135,'Tchouvatch Slovaque',1),
(136,'Teckel a Poil Long',1),
(137,'Teckel a Poil Ras',1),
(138,'Terre-neuve',1),
(139,'Tervueren',1),
(140,'Tosa',1),
(141,'Vastgotaspets Ou Berger Suédois',1),
(142,'Welsh Terrier',1),
(143,'Abyssin',2),
(144,'American Curl',2),
(145,'Américain À Poil Dur Ou American Wirehair',2),
(146,'Américain À Poil Court Ou American Shorthair',2),
(147,'Angora Turc',2),
(148,'Asian',2),
(149,'Australian Mist',2),
(150,'Balinais - Siamois À Poils Longs',2),
(151,'Bengal',2),
(152,'Bleu Russe',2),
(153,'Bobcat Ou Lynx De La Baie ',2),
(154,'Bobtail Américain',2),
(155,'Bobtail Japonais',2),
(156,'Bombay',2),
(157,'British Longhair',2),
(158,'British Shorthair',2),
(159,'Burmese',2),
(160,'Burmilla',2),
(161,'California Spangled Cat',2),
(162,'Caméo',2),
(163,'Caracal',2),
(164,'Chartreux',2),
(165,'Chat À Tête Plate',2),
(166,'Chat Pêcheur',2),
(167,'Chat Rubigineux',2),
(168,'Chat Sauvage',2),
(169,'Chat Tacheté Ou Européen Tacheté',2),
(170,'Chaus ',2),
(171,'Chausie',2),
(172,'Cymric',2),
(173,'Européen À Poil Court',2),
(174,'Exotique À Poil Court - Exotic Shorthair',2),
(175,'Gatto Di Ceylon - Chat Du Sri Lanka',2),
(176,'German Rex',2),
(177,'Havana Brown',2),
(178,'Highland Fold',2),
(179,'Highland Lynx',2),
(180,'Himbur',2),
(181,'Honeybear',2),
(182,'Himalayan - Colorpoint À Poil Long',2),
(183,'Jaguarondi',2),
(184,'Javanais',2),
(185,'Kodkod',2),
(186,'Korat',2),
(187,'Kurilian Bobtail Ou Bobtail Des Kouriles',2),
(188,'La Perm',2),
(189,'Lynx Domestique',2),
(190,'Maine Coon',2),
(191,'Maltese',2),
(192,'Mandarin Ou Oriental Longhair',2),
(193,'Manx - Rumpy',2),
(194,'Manxamese',2),
(195,'Margay',2),
(196,'Mau Égyptien',2),
(197,'Munchkin',2),
(198,'Nebelung - Bleu Russe À Poil Long',2),
(199,'Norvégien',2),
(200,'Ocelot',2),
(201,'Ocicat',2),
(202,'Ojos Azules',2),
(203,'Oncille',2),
(204,'Oriental À Poil Court Ou Oriental Shorthair',2),
(205,'Oriental Blanc',2),
(206,'Oriental Lavender',2),
(207,'Persan',2),
(208,'Persan À Face De Pékinois',2),
(209,'Persan Chinchilla',2),
(210,'Persan Colourpoint Ou Persan Himalayen',2),
(211,'Pixie Bob',2),
(212,'Poodle Ou Rex Fold',2),
(213,'Pudelkatze - Chat Caniche',2),
(214,'Ragamuffin',2),
(215,'Ragdoll',2),
(216,'Rex Cornish',2),
(217,'Rex Devon',2),
(218,'Rex Selkirk',2),
(219,'Sacré De Birmanie Ou Birman',2),
(220,'Savannah',2),
(221,'Scottish Fold Ou Highland Fold',2),
(222,'Serval',2),
(223,'Siamois',2),
(224,'Siamois Traditionnel Ou Siamois Thaï',2),
(225,'Sibérien',2),
(226,'Singapura',2),
(227,'Smoke À Poil Court',2),
(228,'Snowshoe',2),
(229,'Sokoke',2),
(230,'Solid Red',2),
(231,'Somali - Abyssin À Poils Longs',2),
(232,'Sphynx',2),
(233,'Supilak Ou Cooper',2),
(234,'Tiffanie',2),
(235,'Tiffany',2),
(236,'Tonkinois',2),
(237,'Turc Van',2),
(238,'York Chocolate',2),
(239,'Batard',1)
;
