-- insert initial test data
-- the IDs are hardcoded to enable references between further test data
-- negative IDs are used to not interfere with user-entered data and allow clean deletion of test data


DELETE FROM sport where ID < 0;
INSERT INTO sport (ID, NAME)
VALUES (-1, 'Polo')
       , (-2, 'Dressage')
       , (-3, 'Foxhunting')
      ;

