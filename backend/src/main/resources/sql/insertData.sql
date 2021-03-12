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
     , (-3, 'Foxhunting', 'Cause foxes arent dying left and right')
;

INSERT INTO horse (id, name, description, birthday, sex, favoriteSport, mother, father)
VALUES (-1, 'Beatrice Horseman',
        'Throughout BoJacks flashbacks, Beatrice was passive-aggressive, sardonic, neglectful, cynical, bitter, jaded, and verbally abusive. She was an overall atrocious mother. ',
        '1938-2-1', 'female', -2, NULL, NULL)
     , (-2, 'Bojack Horseman', 'Failed filmstar.', '1999-8-7', 'male', -1, -1, NULL)
;

