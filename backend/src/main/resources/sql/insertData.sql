-- insert initial test data
-- the IDs are hardcoded to enable references between further test data
-- negative IDs are used to not interfere with user-entered data and allow clean deletion of test data

-- Remove all foreign keys
UPDATE horse
SET mother=null,
    father=null,
    favoriteSport=null
WHERE id < 0;

DELETE
FROM horse
where id < 0;

DELETE
FROM sport
where id < 0;

INSERT INTO sport (id, name, description)
VALUES (-1, 'Polo', NULL)
     , (-2, 'Dressage', 'The sport for beautiful horses.')
     , (-3, 'Foxhunting', 'Cause foxes arent dying left and right.')
     , (-4, 'Studying', 'Cause we have still so much to learn.')
;

INSERT INTO horse (id, name, description, birthday, sex, favoriteSport, mother, father)
VALUES
       (-4, 'Honey Sugarman',
        NULL, '1905-01-01', 'female', -3, NULL, NULL),
       (-5, 'Joseph Sugerman',
        'Joseph Sugarman was the maternal grandfather of BoJack, the husband of Honey, and the father of Beatrice and Crackerjack. He was the owner of the Sugarman Sugar Cube Company.',
        '1890-01-01', 'male', -2, NULL, NULL),
       (-6, 'Henrietta Platchkey',
        'Henrietta Platchkey is mentioned numerous times throughout Season 4 by Beatrice Horseman and shown through flashbacks in Time''s Arrow.\n She was the Horseman household''s maid in the late 1990s, Butterscotch Horseman''s former mistress, and the biological mother of Hollyhock, who she conceived with Butterscotch.',
        '1970-01-01', 'female', NULL, NULL, NULL),
       (-8, 'Butterscotch Horseman',
        'Butterscotch Horseman was the abusive father of BoJack Horseman, the biological father of Hollyhock, and the husband of Beatrice Horseman. He was an alcoholic failed novelist who appears as a recurring character (through flashbacks) throughout the series.',
        '1935-01-01', 'male', NULL, NULL, NULL),
       (-7, 'Hollyhock Manheim-Mannheim-Guerrero-Robinson-Zilberschlag-Hsung-Fonzerelli-McQuack',
        'After making a brief appearance in the season three finale, was introduced as a new major character in the fourth season of BoJack Horseman, and became a reccurring character in the following seasons.',
        '2000-09-24', 'female', -4, -6, -8),
       (-9, 'Beatrice Horseman',
        'Throughout BoJacks flashbacks, Beatrice was passive-aggressive, sardonic, neglectful, cynical, bitter, jaded, and verbally abusive. She was an overall atrocious mother. ',
        '1938-2-1', 'female', -2, -4, -5)
        ,
       (-10, 'Bojack Horseman',
        'BoJack F. Horseman is the eponymous character of BoJack Horseman. The show primarily follows BoJack and his life after his successful 90s sitcom, Horsin Around.',
        '1964-01-02', 'male', -1, -9, -8)
;

