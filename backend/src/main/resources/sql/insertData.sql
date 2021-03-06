-- insert initial test data
-- the IDs are hardcoded to enable references between further test data
-- negative IDs are used to not interfere with user-entered data and allow clean deletion of test data


DELETE FROM sport where id < 0;
INSERT INTO sport (id, name, description)
VALUES (-1, 'Polo', 'A sport for the rich british.')
       , (-2, 'Dressage', 'The sport for beautiful horses.')
       , (-3, 'Foxhunting', 'Cause foxes arent dying left and right')
      ;

