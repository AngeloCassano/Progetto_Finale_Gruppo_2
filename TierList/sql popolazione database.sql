-- Inserimento Categorie
INSERT INTO category (category_name, descrizione) VALUES
('Film', 'I migliori film di tutti i tempi, dai classici ai blockbuster moderni'),
('Serie TV', 'Le serie televisive più amate e seguite dal pubblico'),
('Videogiochi', 'I videogiochi più iconici e divertenti di ogni genere'),
('Anime', 'Gli anime più popolari e apprezzati dalla community'),
('Cibo', 'Piatti, ingredienti e specialità culinarie da tutto il mondo'),
('Musica', 'Artisti, album e canzoni che hanno fatto la storia della musica'),
('Sport', 'Discipline sportive, squadre e atleti leggendari'),
('Automobili', 'Le auto più iconiche e desiderate di ogni epoca');

-- Inserimento Elementi per categoria Film (category_id = 1)
INSERT INTO element (name, image_url, category_id) VALUES
('Il Padrino', 'https://upload.wikimedia.org/wikipedia/en/1/1c/Godfather_ver1.jpg', 1),
('Pulp Fiction', 'https://upload.wikimedia.org/wikipedia/en/3/3b/Pulp_Fiction_%281994%29_poster.jpg', 1),
('Il Signore degli Anelli', 'https://upload.wikimedia.org/wikipedia/en/8/87/Ringstrilogyposter.jpg', 1),
('Inception', 'https://upload.wikimedia.org/wikipedia/en/2/2e/Inception_%282010%29_theatrical_poster.jpg', 1),
('The Dark Knight', 'https://upload.wikimedia.org/wikipedia/en/8/8a/Dark_Knight.jpg', 1),
('Forrest Gump', 'https://upload.wikimedia.org/wikipedia/en/6/67/Forrest_Gump_poster.jpg', 1),
('Matrix', 'https://upload.wikimedia.org/wikipedia/en/c/c1/The_Matrix_Poster.jpg', 1),
('Titanic', 'https://upload.wikimedia.org/wikipedia/en/1/19/Titanic_%28Official_Film_Poster%29.png', 1),
('Goodfellas', 'https://upload.wikimedia.org/wikipedia/en/7/7b/Goodfellas.jpg', 1),
('Interstellar', 'https://upload.wikimedia.org/wikipedia/en/b/bc/Interstellar_film_poster.jpg', 1);

-- Inserimento Elementi per categoria Serie TV (category_id = 2)
INSERT INTO element (name, image_url, category_id) VALUES
('Breaking Bad', 'https://upload.wikimedia.org/wikipedia/en/6/61/Breaking_Bad_title_card.png', 2),
('Game of Thrones', 'https://upload.wikimedia.org/wikipedia/en/d/d8/Game_of_Thrones_title_card.jpg', 2),
('The Office', 'https://upload.wikimedia.org/wikipedia/en/0/02/The_Office_%28U.S._TV_series%29_logo.jpg', 2),
('Friends', 'https://upload.wikimedia.org/wikipedia/en/0/0a/Friends_Season_One_DVD.jpg', 2),
('Stranger Things', 'https://upload.wikimedia.org/wikipedia/en/7/7a/Stranger_Things_season_1.jpg', 2),
('The Sopranos', 'https://upload.wikimedia.org/wikipedia/en/3/3f/The_Sopranos_season_1.jpg', 2),
('Better Call Saul', 'https://upload.wikimedia.org/wikipedia/en/4/4a/Better_Call_Saul_season_1.jpg', 2),
('The Wire', 'https://upload.wikimedia.org/wikipedia/en/6/6e/The_Wire_-_Season_1.jpg', 2),
('Lost', 'https://upload.wikimedia.org/wikipedia/en/2/27/Lost_season_1_dvd.jpg', 2),
('House of Cards', 'https://upload.wikimedia.org/wikipedia/en/2/2a/House_of_Cards_season_1.jpg', 2);

-- Inserimento Elementi per categoria Videogiochi (category_id = 3)
INSERT INTO element (name, image_url, category_id) VALUES
('The Legend of Zelda: Breath of the Wild', 'https://upload.wikimedia.org/wikipedia/en/c/c6/The_Legend_of_Zelda_Breath_of_the_Wild.jpg', 3),
('Grand Theft Auto V', 'https://upload.wikimedia.org/wikipedia/en/a/a5/Grand_Theft_Auto_V.png', 3),
('Minecraft', 'https://upload.wikimedia.org/wikipedia/en/5/51/Minecraft_cover.png', 3),
('The Witcher 3', 'https://upload.wikimedia.org/wikipedia/en/0/0c/Witcher_3_cover_art.jpg', 3),
('Super Mario Odyssey', 'https://upload.wikimedia.org/wikipedia/en/8/8d/Super_Mario_Odyssey.jpg', 3),
('Red Dead Redemption 2', 'https://upload.wikimedia.org/wikipedia/en/4/44/Red_Dead_Redemption_II.jpg', 3),
('Call of Duty: Modern Warfare', 'https://upload.wikimedia.org/wikipedia/en/0/05/Call_of_Duty_Modern_Warfare_%282019%29.jpg', 3),
('FIFA 23', 'https://upload.wikimedia.org/wikipedia/en/6/6a/FIFA_23_Cover.jpg', 3),
('Fortnite', 'https://upload.wikimedia.org/wikipedia/en/7/7e/Fortnite_Battle_Royale.jpg', 3),
('Elden Ring', 'https://upload.wikimedia.org/wikipedia/en/b/b9/Elden_Ring_Box_art.jpg', 3);

-- Inserimento Elementi per categoria Anime (category_id = 4)
INSERT INTO element (name, image_url, category_id) VALUES
('Attack on Titan', 'https://upload.wikimedia.org/wikipedia/en/6/63/Shingeki_no_Kyojin_manga_volume_1.jpg', 4),
('Naruto', 'https://upload.wikimedia.org/wikipedia/en/9/94/NarutoCoverTankobon1.jpg', 4),
('One Piece', 'https://upload.wikimedia.org/wikipedia/en/9/90/One_Piece%2C_Volume_61_Cover_%28Japanese%29.jpg', 4),
('Dragon Ball Z', 'https://upload.wikimedia.org/wikipedia/en/4/4a/Dragon_Ball_Z_Volume_1.png', 4),
('Death Note', 'https://upload.wikimedia.org/wikipedia/en/6/6f/Death_Note_Vol_1.jpg', 4),
('My Hero Academia', 'https://upload.wikimedia.org/wikipedia/en/6/60/My_Hero_Academia_Volume_1.png', 4),
('Demon Slayer', 'https://upload.wikimedia.org/wikipedia/en/0/09/Demon_Slayer_-_Kimetsu_no_Yaiba%2C_volume_1.jpg', 4),
('One Punch Man', 'https://upload.wikimedia.org/wikipedia/en/4/4d/OnePunchMan_manga_cover.png', 4),
('Fullmetal Alchemist', 'https://upload.wikimedia.org/wikipedia/en/7/7f/Fullmetal_Alchemist_manga_vol_1.jpg', 4),
('Spirited Away', 'https://upload.wikimedia.org/wikipedia/en/d/db/Spirited_Away_Japanese_poster.png', 4);

-- Inserimento Elementi per categoria Cibo (category_id = 5)
INSERT INTO element (name, image_url, category_id) VALUES
('Pizza Margherita', 'https://upload.wikimedia.org/wikipedia/commons/a/a3/Eq_it-na_pizza-margherita_sep2005_sml.jpg', 5),
('Sushi', 'https://upload.wikimedia.org/wikipedia/commons/6/60/Sushi_platter.jpg', 5),
('Hamburger', 'https://upload.wikimedia.org/wikipedia/commons/4/47/Hamburger_%28black_bg%29.jpg', 5),
('Pasta Carbonara', 'https://upload.wikimedia.org/wikipedia/commons/3/33/Espaguetis_carbonara.jpg', 5),
('Tacos', 'https://upload.wikimedia.org/wikipedia/commons/7/73/001_Tacos_de_carnitas%2C_carne_asada_y_al_pastor.jpg', 5),
('Ramen', 'https://upload.wikimedia.org/wikipedia/commons/4/41/Sun_ramen.jpg', 5),
('Gelato', 'https://upload.wikimedia.org/wikipedia/commons/3/31/Gelato_%28Ice_Cream%29.jpg', 5),
('Lasagne', 'https://upload.wikimedia.org/wikipedia/commons/b/ba/Lasagne_-_stonesoup.jpg', 5),
('Pad Thai', 'https://upload.wikimedia.org/wikipedia/commons/3/39/Phat_Thai_kung_Chang_Khien_street_stall.jpg', 5),
('Tiramisù', 'https://upload.wikimedia.org/wikipedia/commons/5/5a/Tiramisu_with_blueberries_and_raspberries%2C_July_2011.jpg', 5);

-- Inserimento Elementi per categoria Musica (category_id = 6)
INSERT INTO element (name, image_url, category_id) VALUES
('The Beatles', 'https://upload.wikimedia.org/wikipedia/en/4/42/Beatles_-_Abbey_Road.jpg', 6),
('Queen', 'https://upload.wikimedia.org/wikipedia/en/4/4d/Queen_A_Night_At_The_Opera.png', 6),
('Michael Jackson', 'https://upload.wikimedia.org/wikipedia/en/5/5c/Michael_Jackson_-_Thriller.png', 6),
('Led Zeppelin', 'https://upload.wikimedia.org/wikipedia/en/2/20/Led_Zeppelin_-_Led_Zeppelin_IV.jpg', 6),
('Pink Floyd', 'https://upload.wikimedia.org/wikipedia/en/3/3b/Dark_Side_of_the_Moon.png', 6),
('Elvis Presley', 'https://upload.wikimedia.org/wikipedia/en/6/6b/Elvis_Presley_-_Elvis%27_Gold_Records_Volume_2.jpg', 6),
('Bob Dylan', 'https://upload.wikimedia.org/wikipedia/en/4/4b/Bob_Dylan_-_Highway_61_Revisited.jpg', 6),
('Nirvana', 'https://upload.wikimedia.org/wikipedia/en/b/b7/NirvanaNevermindalbumcover.jpg', 6),
('The Rolling Stones', 'https://upload.wikimedia.org/wikipedia/en/4/4a/Let_It_Bleed_-_UK_cover.jpg', 6),
('AC/DC', 'https://upload.wikimedia.org/wikipedia/en/8/8b/Back_in_Black.png', 6);

-- Inserimento Elementi per categoria Sport (category_id = 7)
INSERT INTO element (name, image_url, category_id) VALUES
('Calcio', 'https://upload.wikimedia.org/wikipedia/commons/b/b9/Football_iu_1996.jpg', 7),
('Basket', 'https://upload.wikimedia.org/wikipedia/commons/7/7a/Basketball_game.jpg', 7),
('Tennis', 'https://upload.wikimedia.org/wikipedia/commons/3/3e/Tennis_Racket_and_Balls.jpg', 7),
('Formula 1', 'https://upload.wikimedia.org/wikipedia/commons/3/33/F1_car_2014_Malaysia_2.jpg', 7),
('Nuoto', 'https://upload.wikimedia.org/wikipedia/commons/8/82/Swimming.breaststroke.arp.750pix.jpg', 7),
('Pallavolo', 'https://upload.wikimedia.org/wikipedia/commons/3/3f/Volleyball_game.jpg', 7),
('Golf', 'https://upload.wikimedia.org/wikipedia/commons/3/3c/Golfball.jpg', 7),
('MMA', 'https://upload.wikimedia.org/wikipedia/commons/9/9b/MMA_grappling.jpg', 7),
('Ciclismo', 'https://upload.wikimedia.org/wikipedia/commons/5/5d/Cycling_-_Track_cycling_%28sprint%29.jpg', 7),
('Atletica', 'https://upload.wikimedia.org/wikipedia/commons/5/53/Roger_Kingdom_1988.jpg', 7);

-- Inserimento Elementi per categoria Automobili (category_id = 8)
INSERT INTO element (name, image_url, category_id) VALUES
('Ferrari 488', 'https://upload.wikimedia.org/wikipedia/commons/7/75/Ferrari_488_GTB_%2820190390%29.jpg', 8),
('Lamborghini Huracán', 'https://upload.wikimedia.org/wikipedia/commons/5/5b/2015_Lamborghini_Hurac%C3%A1n_LP610-4_3.8.jpg', 8),
('Porsche 911', 'https://upload.wikimedia.org/wikipedia/commons/1/1c/Porsche_911_%28992%29_-_Frontansicht_%282%29%2C_5._April_2012%2C_D%C3%BCsseldorf.jpg', 8),
('BMW M3', 'https://upload.wikimedia.org/wikipedia/commons/5/5d/2018_BMW_M3_3.0.jpg', 8),
('Mercedes AMG GT', 'https://upload.wikimedia.org/wikipedia/commons/7/7b/Mercedes-AMG_GT_Coupe_%2842329713521%29.jpg', 8),
('Audi R8', 'https://upload.wikimedia.org/wikipedia/commons/7/78/Audi_R8_V10_plus_%284K%29.jpg', 8),
('McLaren 720S', 'https://upload.wikimedia.org/wikipedia/commons/3/3e/McLaren_720S_Velocity_%2842427444542%29.jpg', 8),
('Bugatti Chiron', 'https://upload.wikimedia.org/wikipedia/commons/1/1a/Bugatti_Chiron_%2836557846546%29.jpg', 8),
('Tesla Model S', 'https://upload.wikimedia.org/wikipedia/commons/1/14/2016_Tesla_Model_S_70.jpg', 8),
('Ford Mustang', 'https://upload.wikimedia.org/wikipedia/commons/7/7b/2019_Ford_Mustang_GT_5.0.jpg', 8);