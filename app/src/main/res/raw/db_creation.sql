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


CREATE TABLE fav_place(
    _id INTEGER AUTOINCREMENT,
    email TEXT NOT NULL,
    name TEXT NOT NULL,
    type TEXT NOT NULL,
    lat FLOAT,
    comment TEXT,
    lon FLOAT,
    address TEXT NOT NULL,
    description TEXT,
    openning TEXT,
    closing TEXT,
    review FLOAT NOT NULL,
    PRIMARY KEY (_id, email)
);



CREATE TABLE recent_search(
    id_busqueda INTEGER AUTOINCREMENT,
    busqueda TEXT NOT NULL,
    id_person INTEGER,
    PRIMARY KEY (id_busqueda, id_person),
    FOREIGN KEY (id_person) REFERENCES person(_id)
);


