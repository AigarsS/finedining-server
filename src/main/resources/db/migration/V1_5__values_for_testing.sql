INSERT INTO products (name, product_category_id) VALUES ('kviešu milti', 3);
INSERT INTO products (name, product_category_id) VALUES ('ūdens', 8);
INSERT INTO products (name, product_category_id) VALUES ('eļļa', 8);
INSERT INTO products (name, product_category_id) VALUES ('olas', 8);
INSERT INTO products (name, product_category_id) VALUES ('piens', 1);
INSERT INTO products (name, product_category_id) VALUES ('sāls', 7);

INSERT INTO recipes (title, description, cooking_time, usage_frequency, servings_per_recipe, image_id, user_id)
    SELECT 'Franču crepes', 'Viegli pagatavojamas plānās pankūkas', 2700, 1, 2, id, 1
        FROM images WHERE name = 'french_crepe.jpg';

INSERT INTO ingredients (product_id, quantity, unit_id, recipe_id) VALUES (1, 200, 1, 1);
INSERT INTO ingredients (product_id, quantity, unit_id, recipe_id) VALUES (2, 0.125, 2, 1);
INSERT INTO ingredients (product_id, quantity, unit_id, recipe_id) VALUES (3, 2, 4, 1);
INSERT INTO ingredients (product_id, quantity, unit_id, recipe_id) VALUES (4, 3, 3, 1);
INSERT INTO ingredients (product_id, quantity, unit_id, recipe_id) VALUES (5, 0.250, 2, 1);
INSERT INTO ingredients (product_id, quantity, unit_id, recipe_id) VALUES (6, 0.5, 5, 1);

INSERT INTO cooking_steps (step_number, description, recipe_id) VALUES (1, 'Jāņem salātu bļoda un tajā jāsaputo (var arī samaisīt) miltus ar pienu. Ļoti jācenšas bez kunkuļiem.', 1);
INSERT INTO cooking_steps (step_number, description, recipe_id) VALUES (2, 'Pie tikko gatavotās masas jāiesit olas un jāsakuļ.', 1);
INSERT INTO cooking_steps (step_number, description, recipe_id) VALUES (3, 'Tūlīt pēc tam pievieno masai sāli, eļļu un atkal samaisa.', 1);
INSERT INTO cooking_steps (step_number, description, recipe_id) VALUES (4, 'Pielej ūdeni un ļauj mīklai nostāvēties 15 min. Nemaisa. Bet! Mīklai jāsanāk ļoti, ļoti šķidrai, par to nevajag uztraukties, un miltus vēl papildus klāt nevajag bērt. Tāpat arī ar cukuru – to klāt bērt nevajag.', 1);
INSERT INTO cooking_steps (step_number, description, recipe_id) VALUES (5, 'Ietauko pannu, bet ļoti maz jāpievieno eļļa. Ir pat tādas īpašās pannas kurām ir tādas kā pumpiņas uz iekšu, tām vispār eļļu nevajag. Pannu var ietaukot ar papīra salveti, vai to darīt ar parasto pārtikas otiņu.', 1);
INSERT INTO cooking_steps (step_number, description, recipe_id) VALUES (6, 'Uz pannas liek 1 kausiņu mīklas, un turot pannu rokā, mīklu izlaista pa visu pannu, tā lai sanāk liels aplis.', 1);
INSERT INTO cooking_steps (step_number, description, recipe_id) VALUES (7, 'Kad apļa maliņas sāk čokuroties un celties uz augšu, tad pankūka jāgriež otrādi. To var darīt ar lāpstiņu, bet īstiem profesionāļiem, ieteiktu pankūku mest riņķī – tomēr process jau arī uzlabo garšu.', 1);


INSERT INTO products (name, product_category_id) VALUES ('ziedkāposts', 2);
INSERT INTO products (name, product_category_id) VALUES ('sviests', 1);
INSERT INTO products (name, product_category_id) VALUES ('milti', 3);
INSERT INTO products (name, product_category_id) VALUES ('pipari', 7);
INSERT INTO products (name, product_category_id) VALUES ('malts muskatrieksts', 7);
INSERT INTO products (name, product_category_id) VALUES ('siers krievijas', 1);
INSERT INTO products (name, product_category_id) VALUES ('siers parmezāns', 1);

INSERT INTO recipes (title, description, cooking_time, usage_frequency, servings_per_recipe, image_id, user_id)
    SELECT 'Ziedkāpostu un siera sacepums', 'Vienkāršs ziedkāpostu sacepums, kas papildināts ar siera mērci un sarīvētu sieru virskārtā. Vari izmantot kā Parmezāna sieru, tā rīvētu Krievijas, Holandes vai kādu citu labi kūstošu sieru', 3600, 1, 4, id, 1
        FROM images WHERE name = 'caulifower_cheese_oven_baked.jpg';

INSERT INTO ingredients (product_id, quantity, unit_id, recipe_id) VALUES (7, 1, 3, 2);
INSERT INTO ingredients (product_id, quantity, unit_id, recipe_id) VALUES (6, 0.5, 5, 2);
INSERT INTO ingredients (product_id, quantity, unit_id, recipe_id) VALUES (8, 2, 4, 2);
INSERT INTO ingredients (product_id, quantity, unit_id, recipe_id) VALUES (9, 3, 4, 2);
INSERT INTO ingredients (product_id, quantity, unit_id, recipe_id) VALUES (5, 0.4, 2, 2);
INSERT INTO ingredients (product_id, quantity, unit_id, recipe_id) VALUES (10, 0.125, 5, 2);
INSERT INTO ingredients (product_id, quantity, unit_id, recipe_id) VALUES (11, 0.125, 5, 2);
INSERT INTO ingredients (product_id, quantity, unit_id, recipe_id) VALUES (12, 2, 6, 2);
INSERT INTO ingredients (product_id, quantity, unit_id, recipe_id) VALUES (13, 1, 6, 2);

INSERT INTO cooking_steps (step_number, description, recipe_id) VALUES (1, 'Ziedkāpostu sadala sīkākos gabaliņos un saber katlā. Pārlej tiem ūdeni, pievieno nedaudz sāli un novāra 5–6 minūtes, līdz dārzeņa gabaliņi sāk kļūt mīksti. Pēc tam nokāš un pataupa vēlākam.', 2);
INSERT INTO cooking_steps (step_number, description, recipe_id) VALUES (2, 'Pannā izkausē sviestu un pievieno tam miltus – maisot apcep 2 minūtes. Pēc tam miltiem pielej pienu un maisot karsē, līdz šķidrums sāk burbuļot. Mērci uz lēnas uguns vāra minūti, līdz tā sāk sabiezēt. Tad pannu noņem no plīts un iemaisa mērcē tējkaroti sāli, piparus, muskatriekstu un pusi no visa nepieciešamā siera.', 2);
INSERT INTO cooking_steps (step_number, description, recipe_id) VALUES (3, 'Pusi no pagatavotās mērces lej cepamtrauka pamatnē. Virsū tai izkārto nokāstos ziedkāposta gabaliņus, bet tos pārlej ar atlikušo mērci. Pietaupīto sieru uzkaisa sacepumam virsū un to liek krāsnī 190°C cepties apmēram 25–30 minūtes, līdz virsa apbrūnējusi.', 2);
