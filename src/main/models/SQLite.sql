CREATE TABLE IF NOT EXISTS Etudiant (
	cin INTEGER PRIMARY KEY,
	archive TEXT NOT NULL,
	nom TEXT NOT NULL,
	prenom TEXT NOT NULL,
	classe TEXT NOT NULL,
	cond TEXT NOT NULL
	FOREIGN KEY(cinDoc) REFERENCES Classe(cin) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Document (
	idDoc INTEGER PRIMARY KEY AUTOINCREMENT,
	cinDoc INTEGER NOT NULL,
	nomDoc TEXT NOT NULL,
	FOREIGN KEY(cinDoc) REFERENCES Etudiant(cin) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Classe (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	classe TEXT NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS Settings (
	label TEXT PRIMARY KEY,
	value TEXT NOT NULL
);

INSERT INTO Classe(classe) VALUES("1GLSI1"), ("1GLSI2"), ("1GLSI3"), ("1GLSI4"), ("2GLSI1"), ("2GLSI2"), ("2GLSI3"), ("3TMW"), ("1MIDS"), ("2MIDS");
INSERT INTO Settings VALUES("language", "french");