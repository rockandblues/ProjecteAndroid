CREATE TABLE person (
    _id	INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    surname TEXT NOT NULL,
    email TEXT NOT NULL,
    password TEXT,
    description TEXT NOT NULL,
    female INTEGER,
    picture BLOB

);

CREATE TABLE place_person(
    id_person INTEGER,
    id_place INTEGER,
    isFavourite INT, -- -1 SI ES FAVOURITE
    comment TEXT,
    PRIMARY KEY (id_person, id_place),
    FOREIGN KEY (id_person) REFERENCES person(_id),
    FOREIGN KEY (id_place) REFERENCES place(_id)
);

CREATE TABLE place (
    _id INTEGER PRIMARY KEY AUTOINCREMENT,
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
    id_busqueda INTEGER AUTOINCREMENT,
    busqueda TEXT NOT NULL,
    id_person INTEGER,
    PRIMARY KEY (id_busqueda, id_person),
    FOREIGN KEY (id_person) REFERENCES person(_id)
);


