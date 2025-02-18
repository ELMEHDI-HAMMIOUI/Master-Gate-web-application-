This folder contains all required objects for the client side of MonMstr application (Etudiant side).
How to use it?
1-copy & paste object definitions from files to your shema, or just use: @file-path
2-respect this order when creating objects:
    create_tables.sql --> tables_constraints.sql  --> create_sequence.sql  --> create_views.sql  --> create_triggers.sql --> required_insert.sql  [ --> test_insert.sql ]
3- make sure you give your shema the sufficant quota (> 50m) 