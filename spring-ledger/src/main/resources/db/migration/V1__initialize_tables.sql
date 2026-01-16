CREATE TABLE BANKS (
                       ID INT PRIMARY KEY,
                       reference_id UUID,
                       bank_name TEXT,
                       created_at DATE,
                       updated_at DATE
);

CREATE TABLE USERS (
                       ID INT PRIMARY KEY,
                     reference_id TEXT,
                       user_name TEXT,
                       created_at DATE,
                       updated_at DATE
);

CREATE TABLE LOANS (
                       ID INT PRIMARY KEY,
                       reference_id TEXT,
                       user_id INT REFERENCES users(id),
                       bank_id INT REFERENCES banks(id),
                       principal INT,
                       years INT,
                       roi INT,
                       created_at DATE,
                       updated_at DATE
);