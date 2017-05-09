CREATE TABLE person(
    _id	INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    surname TEXT NOT NULL,
    email TEXT NOT NULL,
    password TEXT,
    description TEXT NOT NULL,
    female INTEGER,
    picture BLOB

);
-- TODO hacer una db bien

CREATE TABLE fav_place(
    _id	INTEGER PRIMARY KEY AUTOINCREMENT,
    email TEXT NOT NULL,
    name TEXT NOT NULL,
    type TEXT NOT NULL,
    lat FLOAT,
    lon FLOAT,
    address TEXT NOT NULL,
    description TEXT,
    openning TEXT,
    closing TEXT,
    review FLOAT NOT NULL
);



CREATE TABLE recent_search(
    id_busqueda INTEGER PRIMARY KEY AUTOINCREMENT,
    busqueda TEXT NOT NULL,
    id_person INTEGER,
    FOREIGN KEY (id_person) REFERENCES person(_id)
);


